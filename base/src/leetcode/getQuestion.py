# 获取面试中出现高的题目
# 使用leetcode-cli & nvim 刷题

import json
import requests

file = open("question","w",encoding="utf-8")
question_str = ""
for repo in [requests.get("https://codetop.cc/api/questions/?page="+str(i)+"&search=&ordering=-frequency") for i in range(1,45)]:
    if 200 != repo.status_code:
        continue

    for question in json.loads(repo.text)["list"]:
        question_str = question_str + str(question["leetcode"]["question_id"]) + ":" + str(question["leetcode"]["title"])  + "\r\n"

file.write(question_str,)
