from flask import Flask, request
from flask_socketio import SocketIO
from utils import *


app = Flask(__name__)


@app.route("/")
def home():
    info = "Server is running"
    return "<h2>%s</h2>" % info


@app.route("/start")
def start_browser_handler():
    browser = request.args.get("browser")
    url = request.args.get("url")

    try:
        start_browser(browser, url)
        info = "Open %s in %s browser" % (url, browser) 
    except UnknownBrowserError:
        info = "Currently, this service supports only chrome and firefox browser"
    except InvalidURLError:
        info = "URL is invalid"

    return "<h2>%s</h2>" % info


@app.route("/stop")
def stop_browser_handler():
    browser = request.args.get("browser")

    try:
        stop_browser(browser)
        info = "%s browser has been closed successfully" % browser
    except BrowserNotRunningError:
        info = "%s browser instance is not running" % browser

    return "<h2>%s</h2>" % info


@app.route("/cleanup")
def browser_cleanup_hander():
    browser = request.args.get("browser")

    try:
        clear_browser_data(browser)
        info = "successfully cleared %s data" % (browser)
    except UnknownBrowserError:
        info = "Currently, this service supports only chrome and firefox browser"
    except RunningBrowserError:
        info = "chrome browser is currently running"

    return "<h2>%s</h2>" % info



@app.route("/geturl")
def get_active_url_handler():
    browser = request.args.get("browser")

    try:
        url = get_active_url(browser)

        if url == None:
            info = "No active URL"
        else:
            info = "URL - %s" % url
    except UnknownBrowserError:
        info = "Currently, this service supports only chrome and firefox browser"

    return "<h2>%s</h2>" % info


if __name__ == "__main__":
    create_browser_directories()
    app.run(debug=True)
