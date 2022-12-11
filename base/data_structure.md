TODO: 《算法》大多是一些比较基础的算法

TODO: 《算法导论》非常全面的算法书。(2021出了第四版)

TODO: 《算法珠玑》 有一些大数据处理的算法可以看看

# 1. DataStructure

## 1.1. Array

### 1.1.1. basic array

### 1.1.2. sds(from redis)

## 1.2. Linked List

### 1.2.1. basic list

### 1.2.2. ziplist(from redis)

### 1.2.3. skiplist(from redis)

### 1.2.4. quicklist(from redis)

## 1.3. Map

### 1.3.1. Hashmap

### 1.3.2. Treemap

## 1.4. Stack

## 1.5. Queue

### 1.5.1. Queue

### 1.5.2. Circular queue

### 1.5.3. Priority queue

## 1.6. Tree

> always used as index

### 1.6.1. Binary Search Tree

### 1.6.2. 2-3 Tree

### 1.6.3. Red-Black Tree(From 2-3 Tree)

### 1.6.4. AVL Tree

### 1.6.5. B Tree

### 1.6.6. B+ Tree

### 1.6.7. Splay Tree

### 1.6.8. Prefix Tree(Trie)

### 1.6.9. Radix Tree(Compact Trie)

### 1.6.10. Ternary Search Tree (Trie with BST of children)

## 1.7. Heap-Like

### 1.7.1. Heaps

### 1.7.2. Binomial Queues

### 1.7.3. Fibonacci Heaps

### 1.7.4. Leftist Heaps

### 1.7.5. Skew Heaps

## 1.8. Hashing

### 1.8.1. Open Hash Table

### 1.8.2. Closed Hash Table

### 1.8.3. Closed bucket Hash Table

## 1.9. Graph

# 2. Algorithm or The application of DataStructure

## 2.1. Sorting

### 2.1.1. Comparison Sorting

#### 2.1.1.1. Bubble Sort

#### 2.1.1.2. Selection Sort

#### 2.1.1.3. Insertion Sort

#### 2.1.1.4. Shell Sort

#### 2.1.1.5. Merge Sort

#### 2.1.1.6. Quck Sort

### 2.1.2. Bucket Sort

### 2.1.3. Counting Sort

### 2.1.4. Radix Sort

### 2.1.5. Heap Sort

## 2.2. Searching

### 2.2.1. Binary

### 2.2.2. Linear Search (of sorted list)

## 2.3. Substring search

### 2.3.1. 暴力查找

### 2.3.2. Knuth-Morris-Pratt

### 2.3.3. Boyer-karp 指纹字符串查找

## 2.4. Graph Algorithms

### 2.4.1. Breadth-First Search

### 2.4.2. Depth-First Search

### 2.4.3. Connected Components

### 2.4.4. Dijkstra's Shortest Path

### 2.4.5. Prim's Minimum Cost Spanning Tree

### 2.4.6. Topological Sort (Using Indegree array)

### 2.4.7. Topological Sort (Using DFS)

### 2.4.8. Floyd-Warshall (all pairs shortest paths)

### 2.4.9. Kruskal Minimum Cost Spanning Tree Algorithm


## 2.5. Index

### 2.5.1. Implemented by tree

- Binary and Linear Search (of sorted list)
- Binary Search Trees
- AVL Trees (Balanced binary search trees)
- Red-Black Trees
- Splay Trees
- Trie (Prefix Tree, 26-ary Tree)
- Radix Tree (Compact Trie)
- Ternary Search Tree (Trie with BST of children)
- B Trees
- B+ Trees

### 2.5.2. Implemented by hash table

- Open Hash Tables (Closed Addressing)
- Closed Hash Tables (Open Addressing)
- Closed Hash Tables, using buckets

### 2.5.3. z-order index

# 3. Other structure

## 3.1. LSM

> from k-v database, used by levelDB, HBase etc.

# 4. 参考资料

- [ ] [Indexing — Data Structures](https://medium.com/nerd-for-tech/indexing-data-structures-aa7363693c40)
- [ ] [可视化：Data Structure Visualizations](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html)
- [ ] 《算法（第四版）》
