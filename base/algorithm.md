> 临时起意打算用 vim 手写算法题，因此就以笔记的形式进行记录。大多数是二刷题目。

> 开始使用leetcode-cli写算法了。装这一个：`npm install -g ketankr9/leetcode-cli`

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


# 数据结构

## 146.LRU 缓存机制

```
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
```

```java
// 通过java LinkedHashMap实现
class LRUCache {

    private int capacity;
    private Map<Integer,Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap(capacity,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest){
                return map.size()>capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        map.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

```java
// 手动实现
class LRUCache {
  // 双向链表节点
  private static class Node<K,V>{
    K k;
    V v;
    Node<K,V> prev;
    Node<K,V> next;
    public Node(K k,V v){
      this.k = k;
      this.v = v;
    }
  }

  // 双向链表
  private static class DoubleLinkeList<K,V>(
    
  )

  public LRUCache(int capacity) {

  }

  public int get(int key) {

  }

  public void put(int key, int value) {

  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

# 数学

# 位运算

## 

```
给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。
如果选中的元素下标位置不一样，则认为两个子集 不同 。
对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。

示例 1：
  输入：nums = [3,1]
  输出：2
  解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
  - [3]
  - [3,1]

示例 2：
  输入：nums = [2,2,2]
  输出：7
  解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 2^3 - 1 = 7 个子集。

示例 3：
  输入：nums = [3,2,1,5]
  输出：6
  解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
  - [3,5]
  - [3,1,5]
  - [3,2,5]
  - [3,2,1,5]
  - [2,5]
  - [2,1,5]
```

```java
class Solution {
  public int countMaxOrSubsets(int[] nums) {

  }
}
```

# 分治算法

# 搜索与回溯

# 动态规划
