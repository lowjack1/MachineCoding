from flask import Flask, redirect, url_for, render_template, request
from flask_socketio import SocketIO, send
import os

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret_value'
socketio = SocketIO(app)
clients_map = dict()


@socketio.on('connect')
def handle_connect():
    clients_map[request.sid] = 0
    data = get_last_n_lines(request.sid)
    socketio.emit("message", data, room=request.sid)


@socketio.on('message')
def handle_message(msg):
    data = read_changes_in_log_file(request.sid)
    socketio.emit("message", data, room=request.sid)


@socketio.on('disconnect')
def handle_disconnect():
    if request.sid in clients_map:
        del clients_map[request.sid]


def read_changes_in_log_file(sid):
    data = []
    with open("app.log") as file:
        file.seek(clients_map[sid])
        max_try = 10
        while max_try > 0:
            curr_position = file.tell()
            line = file.readline()
            if not line:
                file.seek(curr_position)
            else:
                data.append(line)
            max_try -= 1

        clients_map[sid] = file.tell()

    return data


@app.route("/")
def home():
    return render_template("index.html")


def get_last_n_lines(sid):
    file_name = "app.log"
    N = 11

    list_of_lines = []
    with open(file_name, 'rb') as read_obj:
        read_obj.seek(0, os.SEEK_END)
        buffer = bytearray()
        pointer_location = read_obj.tell()
        clients_map[sid] = pointer_location
        while pointer_location >= 0:
            # Move the file pointer to the location pointed by pointer_location
            read_obj.seek(pointer_location)
            # Shift pointer location by -1
            pointer_location = pointer_location -1
            # read that byte / character
            new_byte = read_obj.read(1)
            # If the read byte is new line character then it means one line is read
            if new_byte == b'\n':
                # Save the line in list of lines
                list_of_lines.append(buffer.decode()[::-1])
                # If the size of list reaches N, then return the reversed list
                if len(list_of_lines) == N:
                    return list(reversed(list_of_lines))
                # Reinitialize the byte array to save next line
                buffer = bytearray()
            else:
                # If last read character is not eol then add it in buffer
                buffer.extend(new_byte)
        # As file is read completely, if there is still data in buffer, then its first line.
        if len(buffer) > 0:
            list_of_lines.append(buffer.decode()[::-1])
    # return the reversed list
    return list(reversed(list_of_lines))


if __name__ == "__main__":
    socketio.run(app, debug=True)
