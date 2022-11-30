# 刷题环境

> 开始使用leetcode-cli写算法了。`npm install -g ketankr9/leetcode-cli`
>
> 语言可能都用用，顺便语法复习
>
> 环境：
> - wezterm
> - leetcode-cli(questions)
> - nvim 0.6 (editor&lsp client)
>   - nvim-lspconfig
> - LLVM
>   - clangd (lsp server)
> - mingw-w64
>   - gcc 8.1.0 (compiler)
>   - gdb 8.1 (debugger)
> - cint 0.9.27(Run without compiling manually)
> - jdk:1.8.0_251
> - python: 3.7

> stl速查 [链接](../C/STL.md)


# 获得面试频率较高的题目

```python
  # https://codetop.cc/#/home
  # 获取面试中出现高的题目

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
```

# 查找

# 排序

- [206.reverse-linked-list.c](./src/leetcode/206.reverse-linked-list.c)

# 双指针

- [141.linked-list-cycle.c](./src/leetcode/141.linked-list-cycle.c)

# 滑动窗口

- [3.longest-substring-without-repeating-characters.0.cpp](./src/leetcode/3.longest-substring-without-repeating-characters.0.cpp)

# 数据结构

- 146.LRU 缓存机制

# 数学

# 位运算

# 分治算法

# 搜索与回溯

# 动态规划

