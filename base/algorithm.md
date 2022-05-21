# 前言

## 刷题环境

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


## 获得面试频率较高的题目

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

# leetcode

## 查找

## 排序

- [206.reverse-linked-list.c](./src/leetcode/206.reverse-linked-list.c)

## 双指针

- [141.linked-list-cycle.c](./src/leetcode/141.linked-list-cycle.c)

## 滑动窗口

- [3.longest-substring-without-repeating-characters.0.cpp](./src/leetcode/3.longest-substring-without-repeating-characters.0.cpp)

## 数据结构

- 146.LRU 缓存机制

## 数学

## 位运算

## 分治算法

## 搜索与回溯

## 动态规划

# 经典算法大全

## 河内之塔

## Algorithm Gossip: 费式数列

## 巴斯卡三角形

## Algorithm Gossip: 三色棋

## Algorithm Gossip: 老鼠走迷官（一）

## Algorithm Gossip: 老鼠走迷官（二）

## Algorithm Gossip: 骑士走棋盘

## Algorithm Gossip: 八皇后

## Algorithm Gossip: 八枚银币

## Algorithm Gossip: 生命游戏

## Algorithm Gossip: 字串核对

## Algorithm Gossip: 双色、三色河内塔

## Algorithm Gossip: 背包问题（Knapsack Problem）

## Algorithm Gossip: 蒙地卡罗法求 PI

## Algorithm Gossip: Eratosthenes 筛选求质数

## Algorithm Gossip: 超长整数运算（大数运算）

## Algorithm Gossip: 长 PI

## Algorithm Gossip: 最大公因数、最小公倍数、因式分解

## Algorithm Gossip: 完美数

## Algorithm Gossip: 阿姆斯壮数

## Algorithm Gossip: 最大访客数

## Algorithm Gossip: 中序式转后序式（前序式）

## Algorithm Gossip: 后序式的运算

## Algorithm Gossip: 洗扑克牌（乱数排列）

## Algorithm Gossip: Craps 赌博游戏

## Algorithm Gossip: 约瑟夫问题（Josephus Problem）

## Algorithm Gossip: 排列组合

## Algorithm Gossip: 格雷码（Gray Code）

## Algorithm Gossip: 产生可能的集合

## Algorithm Gossip: m 元素集合的 n 个元素子集

## Algorithm Gossip: 数字拆解

## Algorithm Gossip: 得分排行

## Algorithm Gossip: 选择、插入、气泡排序

## Algorithm Gossip: Shell 排序法 - 改良的插入排序

## Algorithm Gossip: Shaker 排序法 - 改良的气泡排序

## 排序法 - 改良的选择排序

## Algorithm Gossip: 快速排序法（一）

## Algorithm Gossip: 快速排序法（二）

## Algorithm Gossip: 快速排序法（三）

## Algorithm Gossip: 合并排序法

## Algorithm Gossip: 基数排序法

## Algorithm Gossip: 循序搜寻法（使用卫兵）

## Algorithm Gossip: 二分搜寻法（搜寻原则的代表）

## Algorithm Gossip: 插补搜寻法

## Algorithm Gossip: 费氏搜寻法

## Algorithm Gossip: 稀疏矩阵

## Algorithm Gossip: 多维矩阵转一维矩阵

## Algorithm Gossip: 上三角、下三角、对称矩阵

## Algorithm Gossip: 奇数魔方阵

## Algorithm Gossip: 4N 魔方阵

## Algorithm Gossip: 2(2N+1) 魔方阵
