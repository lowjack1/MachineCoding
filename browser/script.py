# import subprocess
# import time
# import shlex

# url = "https://google.com"
# cmd = "google-chrome --user-data-dir=browser/chrome " + url
# new_cmd = "google-chrome " + url

# process = subprocess.Popen(shlex.split(cmd), close_fds=True, stdin=subprocess.DEVNULL, stdout=subprocess.DEVNULL,)
# print(dir(process))

# time.sleep(2)

# process.call(shlex.split(cmd), close_fds=True)

# # time.sleep(10)
# # process.kill()


# import pickle
# import binascii
# import base64

# file_path = "browser/chrome/Default/Sessions/Tabs_13271177704632480"

# file = open(file_path, "rb")
# data = file.read()
# # base_64_data = binascii.b2a_base64(data)
# # print(base_64_data)
# print(data.decode('ISO-8859-1'))
# # print(base64.b64decode(base_64_data).decode('utf-8'))
# print()
# file.close()

import sqlite3
con = sqlite3.connect("browser/chrome/Default/History")

cursor = con.cursor()
cursor.execute("Select url FROM urls")
print(cursor.fetchall())
cursor.close()
con.close()