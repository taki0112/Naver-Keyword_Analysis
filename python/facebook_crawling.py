# -*- coding: utf-8 -*-
import urllib.request
import json
import datetime

import time


app_id = "1729945547256885"
app_secret = "8da125c356d162bb87ab908c7e8cf9f7"  # DO NOT SHARE WITH ANYONE!

access_token = app_id + "|" + app_secret
access_token = "EAACEdEose0cBACOu2ZBZB4NUrAKxmSyJJlsZCfCfZAK39A7cDvJclZBvmM5rwyHRQTXLIpdzUr7e3IuR6ZCSW3F1AZAbZAY8BSvXlLuGYnAhkkdsANw99GedIvwFt4yJFcSL1ux8VzRCZC9YRWnEIyoy5k6dw9Jm2ZAGUPsZCnZAnOBJtXcRFVySOTNDW1Lw67OGTHkZD"
page_id = 'SNUBamboo'


def testFacebookPageData(page_id, access_token):
    # construct the URL string
    base = "https://graph.facebook.com/v2.4"
    node = "/" + page_id
    parameters = "/?access_token=%s" % access_token
    url = base + node + parameters

    # retrieve data
    req = urllib.request.Request(url)
    response = urllib.request.urlopen(req).read().decode('utf-8')
    data = json.loads(response)

    #print(json.dumps(data, indent=4, sort_keys=True))



def request_until_succeed(url):
    req = urllib.request.Request(url)
    success = False
    while success is False:
        try:
            response = urllib.request.urlopen(req)
            if response.getcode() == 200:
                success = True
        except Exception:
            time.sleep(5)

            print("Error for URL %s: %s" % (url, datetime.datetime.now()))
            return False

    return urllib.request.urlopen(req).read().decode('utf-8')




def testFacebookPageFeedData(page_id, access_token, _list):
    count = 400;
    # construct the URL string
    base = "https://graph.facebook.com/v2.9"
    node = "/" + page_id + "/feed"  # changed
    parameters = "/?access_token=%s" % access_token
    url = base + node + parameters
    next_url = url
    # print(url)
    # retrieve data
    while 1:
        if count == 0:
            break
        try:
            data = json.loads(request_until_succeed(next_url), encoding='utf-8')
            next_url = data['paging']['next']
            if data == False:
                break
            else:
                print(count)
                count = count - 1
                _list.append(data)
        except:
            return False

    return _list
    #print(json.dumps(data, indent=4, sort_keys=True))



testFacebookPageData(page_id, access_token)
ex_list = []
temp = testFacebookPageFeedData(page_id, access_token, ex_list)



output = "./output.txt"
with open(output, 'w', encoding='utf-8') as outfile :
    for outbox in temp :
        for inbox in outbox['data'] :
            try :
                message = inbox['message'].replace("\n", " ")
                outfile.write(message+"\n")
            except KeyError :
                pass

