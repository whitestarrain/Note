> 临时起意打算用 vim 手写算法题，因此就以笔记的形式进行记录。大多数是二刷题目

# 查找

```java
public static void main(){

}
```

# 排序

# 递归

## 206.反转链表

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

# 分治算法

# 搜索与回溯

# 动态规划
