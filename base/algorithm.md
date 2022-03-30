# 前言

## 刷题环境

> 开始使用leetcode-cli写算法了。`npm install -g ketankr9/leetcode-cli`
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


# 查找

# 排序

```
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
      if(null==head || null==head.next){
        return head;
      }
      ListNode newHead = recurReverseList(head,head.next);
      head.next=null;
      return newHead;
    }
    public static ListNode recurReverseList(ListNode node1,ListNode node2){
      // 递归往前遍历，同时将所在的两个node进行反转
      if(null==node2){
        return node1; // 返回最后一个节点，也就是新的head节点
      }
      ListNode newHead = recurReverseList(node2,node2.next);
      node2.next=node1;
      return newHead;
    }
}
```

# 双指针

## 面试题 02.08. 环路检测

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 
如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
      if(null==head){
        return null;
      }
      ListNode slow = head;
      ListNode fast = head;
      while(fast!=null&&fast.next!=null){
        fast = fast.next.next;
      }
    }
}

```

# 滑动窗口

[3.longest-substring-without-repeating-characters.0.cpp](./src/leetcode/3.longest-substring-without-repeating-characters.0.cpp)

# 数据结构

- 146.LRU 缓存机制

# 数学

# 位运算

# 分治算法

# 搜索与回溯

# 动态规划
