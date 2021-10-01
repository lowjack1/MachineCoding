import subprocess
import shlex
import os
import sqlite3
import shutil

CHROME_DIR = "browser/chrome/Default/"
FIREFOX_DIR = "browser/firefox/"

browser_map = {}

def start_browser(browser, url):
    if browser:
        browser = browser.lower()

    if not url:
        raise InvalidURLError

    if browser not in ["chrome", "firefox"]:
        raise UnknownBrowserError

    if browser == "chrome":
        cmd = "google-chrome --user-data-dir=browser/chrome " + url
    else:
        cmd = "firefox --profile browser/firefox " + url

    dev_null = subprocess.DEVNULL
    process = subprocess.Popen(shlex.split(cmd), close_fds=True, stdin=dev_null, stdout=dev_null, stderr=dev_null)
    browser_map[browser] = browser_map.get(browser, process)

def stop_browser(browser):
    if browser:
        browser = browser.lower()

    if browser not in browser_map:
        raise BrowserNotRunningError

    browser_map[browser].kill()
    del browser_map[browser]

def clear_browser_data(browser):

    if browser:
        browser = browser.lower()

    if browser not in ["chrome", "firefox"]:
        raise UnknownBrowserError

    if browser in browser_map:
        raise RunningBrowserError

    if browser == "chrome":
        clear_chrome_data()
    else:
        clear_firefox_data()


def clear_chrome_data():
    db_files = [
       CHROME_DIR + "History",
       CHROME_DIR + "Cookies",
       CHROME_DIR + "Favicons",
       CHROME_DIR + "Login Data",
       CHROME_DIR + "Login Data For Account",
       CHROME_DIR + "Media History",
       CHROME_DIR + "Shortcuts",
       CHROME_DIR + "Top Sites",
       CHROME_DIR + "Web Data",
    ]


    for file in db_files:
        if not os.path.exists(file):
            continue

        try:
            connection = sqlite3.connect(file)
            cursor = connection.cursor()

            cursor.execute("SELECT name FROM sqlite_master WHERE type='table';")
            db_tables = cursor.fetchall()

            for db_table in db_tables:
                cursor.execute("DELETE FROM %s;" % db_table[0])

            connection.commit()
            connection.close()
            os.remove(file)
        except Exception as e:
            print(e)

    # session_folders = [
    #     "Download Service",
    #     "Cache",
    #     "Sessions",
    #     "Session Storage",
    #     "Storage",
    #     "Local Storage"]

    # for folder_name in session_folders:
    #     folder_path = "browser/chrome/Default/" + folder_name
    #     if os.path.exists(folder_path):
    #         shutil.rmtree(folder_path)

    session_files = [
        # "DownloadMetadata",
        CHROME_DIR + "Bookmarks",
    ]

    for file_path in session_files:
        if os.path.exists(file_path):
            os.remove(file_path)


def clear_firefox_data():
    db_files = [
        FIREFOX_DIR + "cookies.sqlite",
        FIREFOX_DIR + "favicons.sqlite",
        FIREFOX_DIR + "places.sqlite",
        FIREFOX_DIR + "permissions.sqlite",
        FIREFOX_DIR + "content-prefs.sqlite",
        FIREFOX_DIR + "formhistory.sqlite",
        FIREFOX_DIR + "key4.db",
        FIREFOX_DIR + "cert9.db",
    ]

    for file in db_files:
        if not os.path.exists(file):
            continue

        try:
            connection = sqlite3.connect(file)
            cursor = connection.cursor()

            cursor.execute("SELECT name FROM sqlite_master WHERE type='table';")
            db_tables = cursor.fetchall()

            for db_table in db_tables:
                cursor.execute("DELETE FROM %s;" % db_table[0])

            cursor.close()
            connection.commit()
            connection.close()
        except Exception as e:
            print(e)

    json_files = [
        FIREFOX_DIR + "logins.json",
        FIREFOX_DIR + "handlers",
    ]

    for json_file in json_files:
        try:
            if os.path.exists(json_file):
                os.remove(json_file)

        except Exception:
            print(e)


def create_browser_directories():
    parent_browser_folder = "browser"
    if not os.path.exists(parent_browser_folder):
        os.mkdir("browser")


    for browser in ["chrome", "firefox"]:
        browser_folder_name = parent_browser_folder + "/" + browser
        if not os.path.exists(browser_folder_name):
            os.mkdir(browser_folder_name)


def get_active_url(browser):
    url = None
    if browser == "chrome":
        url = get_chrome_active_url()

    elif browser == "firefox":
        url = get_firefox_active_url()

    else:
        raise UnknownBrowserError

    return url


def get_chrome_active_url():
    HISTORY_PATH = CHROME_DIR + "History"
    temp_hisotry_file = CHROME_DIR + "temp_history"

    if not os.path.exists(HISTORY_PATH):
        return None

    try:
        shutil.copy(HISTORY_PATH, temp_hisotry_file)
    except shutil.SameFileError as e:
        print(e)

    url_query = "SELECT url FROM urls ORDER BY last_visit_time DESC LIMIT 1;"
    connection = sqlite3.connect(temp_hisotry_file)
    cursor = connection.cursor()
    cursor.execute(url_query)

    url = cursor.fetchone()
    if len(url) > 0:
        url = url[0]

    connection.close()

    os.remove(temp_hisotry_file)
    return url


def get_firefox_active_url():
    HISTORY_PATH = FIREFOX_DIR + "places.sqlite"
    temp_hisotry_file = FIREFOX_DIR + "temp_history.sqlite"

    if not os.path.exists(HISTORY_PATH):
        return None

    try:
        shutil.copy(HISTORY_PATH, temp_hisotry_file)
    except shutil.SameFileError as e:
        print(e)

    url_query = "SELECT url FROM moz_places ORDER BY last_visit_time DESC LIMIT 1;"
    connection = sqlite3.connect(temp_hisotry_file)
    cursor = connection.cursor()
    cursor.execute(url_query)

    url = cursor.fetchone()
    if len(url) > 0:
        url = url[0]

    connection.close()

    os.remove(temp_hisotry_file)
    return url


class Error(Exception):
    pass

class UnknownBrowserError(Error):
    pass

class RunningBrowserError(Error):
    pass

class BrowserNotRunningError(Error):
    pass


class InvalidURLError(Error):
    pass
