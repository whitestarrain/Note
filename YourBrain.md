<!--请使用Markmap查看-->

<!-- 待整理：https://mp.weixin.qq.com/s?__biz=MzI0ODk2NDIyMQ==&mid=2247486785&idx=1&sn=4377e0e3764d0f4245cb74d83949aa2d&chksm=e999f853deee71452650dce82fd8efdd5854cd640d88cae22ff2a164cb0a6f584059a47932bd&scene=21#wechat_redirect -->

# 基础

## 设计模式<!-- fold -->

### 七大原则

- 强调功能的独立性
  - 单一职责原则
  - 接口隔离原则
- 强调依赖的合理性
  - 依赖倒转(倒置)原则
  - 迪米特法则
- 强调组合和依赖的使用
  - 里氏替换原则
  - 合成复用原则
- 核心
  - 开闭原则 ocp(核心)

### 设计模式

#### 创建型模式

#### 结构型模式

#### 行为型模式

#### J2EE 型模式

## 计算机网络 <!-- fold -->

### 理论(参考《计算<br />机网络》谢希仁) <!-- fold -->

- 概述
  - 基本概念
    - 结点
    - 链路
    - 主机
    - internet 和 Internet
    - ISP 和 IXP
  - 因特网组成
    - 边缘部分
      - client-server
      - p2p
    - 核心部分
      - 电路交换
      - 报文交换
      - 分组交换
  - 计算机网络类别
    - 广域网 WAN
    - 区域网 MAN
    - 城域网 LAN
    - 个人区域网 PAN
  - 计算机网络性能指标
    - 速率
    - 管带
    - 吞吐量
    - 时延
      - 发送
      - 处理
      - 传播
      - 排队
    - 时延带宽积
    - 往返时间 RRT
    - 利用率
  - 计算机网络体系结构
    - OSI 七层
    - TCP/IP 四层
    - 课本按照五层
- 物理层
- 链路层
  - 点对点信道
    - 基本概念
      - 链路
      - 数据链路
      - 网络适配器
    - 三个基本问题
      - 封装成帧:帧格式
      - 透明传输:原理
      - 差错检测
        - 循环冗余检验 CRC
        - 帧检验序列 FCS
    - ppp 协议
      - 协议特点
      - 协议组成
      - PPP 帧格式
      - 透明传输
        - 异步传输：字节填充
        - 同步传输：零比特填充
      - 确保边界：零比特填充
      - PPP 工作状态
  - 广播信道
    - 适配器作用
    - 三个基本问题
      - 封装成帧:帧格式
      - 透明传输:不需要
      - 差错检测
        - 循环冗余检验 CRC
        - 帧检验序列 FCS
    - 协议
      - **CSMA/CD 碰撞检测**
      - TDMA
      - FDMA
      - slotted ALOHA
      - ALOHA
    - MAC 地址
      - 概念
      - 作用
      - 地址格式
      - MAC 帧格式
  - 扩展以太网
    - 物理层
      - 集线器
    - 链路层
      - 网桥
        - **自学习和转发过程**
        - 生成树算法
      - 交换机(多接口网桥)
- 网络层

  - 网络层提供的两种服务
    - 面向连接：VC(虚电路)
    - 无连接：数据报
  - ip 协议
    - ip 配套协议(作用+说明)
      - ARP(链路层协议)(p123)
      - ICMP(p146)
      - IGMP(p171)
    - 虚拟互联网络
    - ip 地址种类
      - 分类的 ip 地址
      - 划分子网
      - 构成超网 CIDR
    - ip 地址与硬件地址
    - ip 数据报
      - ip 数据报格式
      - 分片
      - 首部检验和
    - 网际控制报文协议 ICMP(P146)
      - 种类
        - 差错报告报文 <!-- fold -->
          - 终点不可达
          - 源点抑制
          - 时间超过
          - 参数问题
          - 改变路由(重定向)
        - 询问报文 <!-- fold -->
          - 回送请求和回答
          - 时间戳请求和回答
      - 示例：ping
        - 说明：
          - 应用层直接使用 ICMP
          - 没有通过运输层(TCP/UDP)
        - 流程
  - 路由协议与算法
    - 协议分类
      - 内部网关协议 IGP
        - RIP
          - 工作原理 <!-- fold -->
            - 相邻路由表的交换
          - 算法：距离向量算法(p135)
          - RIP 报文格式 <!-- fold -->
            - 在**运输层**进行路由信息的交换
            - 因为是运输层所以有 UDP 首部
          - 特点 <!-- fold -->
            - 快消息传得慢
          - 优缺点
        - OSPF
          - 工作原理
            - 洪泛法
          - 算法：Dijkstra 最短路径算法
          - 报文格式 <br /> 直接用**ip 数据报**传送 OSPF 分组
            - 5 种分组类型
            - 其他
          - 其他特点
      - 外部网关协议 EGP
        - BGP
    - 算法种类
      - 不分层
        - 全局/局部
        - (自适应)动态/静态
      - 分层
        - 自治系统内路由算法
        - 自治系统间路由算法
  - 路由器组成
  - ip 多播(了解) <!-- fold -->
    - 多播路由器
    - 基本原理
    - 协议
      - 网际组管理协议 IGMP
      - 多播路由协议

- 运输层

  - 概述
    - 进程通信
    - 运输层的分用和复用
    - 两个主要协议概述
  - UDP
    - 特点
    - UDP 首部格式
    - 检验和计算
  - TCP
    - 特点 <!-- fold -->
      - 面向连接
      - 每一条 TCP 链接只能有两个端点
      - TCP 提供可交付的服务
      - TCP 提供全双工通信
      - 面向字节流
    - 连接原理:socket
    - 报文段首部格式
    - 可靠传输
      - 工作原理 <br /> **ARQ**
        - 停止等待<br /> 自动重传 ARQ
          - 无差错情况
          - 出现差错
          - 确认丢失和确认迟到
        - 连续等待 ARQ
          - 滑动窗口
          - 按序到达的最后一个分组发送确认
          - go-back-n
      - 实现
        - 以字节为单位的滑动窗口 <!-- fold -->
          - 边界
            - 含义
              - 前沿
              - 后沿
            - 移动
              - 后沿前移
              - ~前沿收缩~
          - 数据发送流程
            - 窗口
            - 三个指针
          - 缓存与窗口关系
            - tcp 发送缓存
              - 存放内容 <!-- fold -->
                - 发送应用程序传送给发送方 TCP 准备发送的数据
                - TCP 已发送但尚未收到确认的数据
            - tcp 接收缓存
              - 存放内容 <!-- fold -->
                - 按序到达的、但尚未被接收应用程序读取的数据
                - 未按序到达的数据
              - 窗口与缓存对应 <!-- fold -->
                - 接收缓存填满，接收窗口为 0
            - 缓存特点
              - 环形缓存，循环使用
            - **注意点**
              - 发送窗口和接收窗口并不总是一样大的（时间滞后）
              - 不按序到达数据的处理
              - tcp 接收方必须要有累积确认的功能
        - 超时重传时间的选择 <!-- fold -->
          - 自适应算法
        - 选择确认 SACK <!-- fold -->
          - 目的 <!-- fold -->
            - 只重传缺少的数据
            - 不重传已经正确到达的数据
          - 原理与开启
          - 实际：大多数不使用 SACK
    - TCP 的流量控制
      - 利用滑动窗口
      - 传输效率的考虑 <br /> 报文段的发送时机
    - TCP 的拥塞控制
      - 一般原理
        - 开环控制
        - 闭环控制
      - 常用方法
        - 慢开始
        - 拥塞避免
        - 快重传
        - 快恢复
      - 随机早期检测
    - TCP 的连接管理
      - 三次挥手
      - 四次握手

- 应用层

### 基础问题 <!-- fold -->

- 基础
  - 计算机网络体系结构
  - 不同层数据包称呼
- 链路层：ARP
  - 网桥的自学习过程
    - 拓展：生成树算法了解吗
  - MAC 帧中的地址会变化吗
- 传输层：TCP 和 UDP
  - 什么是三次握手？
    - TCP 建立连接可以两次握手吗？为什么?
    - 可以采用四次握手吗？为什么？
    - 第三次握手中，如果客户端的 ACK 未送达服务器，会怎样？
    - 如果已经建立了连接，但客户端出现了故障怎么办？
    - 初始序列号是什么？
  - 什么是四次挥手？
    - 为什么不能把服务器发送的 ACK 和 FIN 合并起来<br />变成三次挥手（CLOSE_WAIT 状态意义是什么）？
    - 如果第二次挥手时服务器的 ACK 没有送达客户端，会怎样？
    - 客户端 TIME_WAIT 状态的意义是什么？
    - 服务器出现了大量 CLOSE_WAIT 状态如何解决
  - TCP 如何实现流量控制？
    - 什么是零窗口（接收窗口为 0 时会怎样）？
    - 什么是 go-back-n
  - TCP 的拥塞控制是怎么实现的？
  - TCP 如何最大利用带宽？
  - TCP 与 UDP 的区别
    - 什么时候选择 TCP，什么时候选 UDP？
    - HTTP 可以使用 UDP 吗？
    - 面向连接和无连接的区别(端到端和点对点)
  - TCP 如何保证传输的可靠性
  - 什么是 TCP 粘包？
  - 传输层网络攻击有哪些？解决策略有哪些 <!-- fold -->
    - syn 超时
    - 洪泛攻击
- 应用层：HTTP 和 HTTPS
  - HTTP 和 HTTPS 有什么区别？
    - Https 的连接过程？
    - HTTPS 连接的时候，怎么确定收到的包是服务器发来的（中间人攻击）？
    - https 如何保证连接的安全性？(连接过程说明)
    - SSL，HTTPS，TLS 三者的区别
    - 输入 www.baidu.com，怎么变成 `https://www.baidu.com` 的 <br/> 怎么确定用 HTTP 还是 HTTPS？
    - 什么是对称加密、非对称加密？区别是什么？
    - 数字签名、报文摘要的原理
    - 其他加密算法
  - HTTP1.0、1.1、2.0 之间的区别
    - HTTP2.0 的多路复用和 HTTP1.X 中的长连接复用有什么区别？
    - 为什么需要头部压缩？
    - HTTP2.0 多路复用有多好？
  - HTTP 请求有哪些常见状态码？
  - 请求行、请求头、请求体、响应行、响应头、响应体都包括什么
  - **从输入网址到获得页面的过程 (越详细越好)**？
    - DNS 使用的是什么协议
    - ARP 协议了解吗
  - GET 与 POST 的区别？
  - 什么是 cookie 和 session
    - Session 与 Cookie 的区别？
    - session 和 cookie 的攻击方式有哪些
    - 如何解决分布式 session 问题
    - 如果禁用浏览器 cookie，如何实现用户追踪和认证
  - 从输入网址到获得页面的过程 (越详细越好)？
  - HTTP 请求有哪些常见状态码？
  - 什么是 RIP (距离矢量路由协议)?
- 网络层协议 IP
  - IP 地址的分类？
  - 什么叫划分子网？
  - 什么是 ARP 协议？
    - 它位于哪层
  - 什么是 ICMP 协议？
    - 报文种类
    - ping 的流程是什么
  - 什么是 NAT (网络地址转换)？

## 操作系统<!-- fold -->

### 理论体系结构

### 基础问题

- 操作系统基础
  - 什么是操作系统
  - 用户态与内核态
- 操作系统结构
  - 操作系统的服务有哪些
  - 什么是 shell
  - 系统调用
- 进程
  - 什么是进程
  - 三种特殊进程
  - 为什么要引入进程
  - 程序与进程之间的区别
  - 作业与进程
  - 操作系统进程的状态
  - 进程运行相关系统调用
  - PCB
  - 进程调度
    - 目的
    - 相关队列与调度器
  - 上下文切换
  - 进程间通信
    - 整体分类
    - 具体方式
- 多线程编程
  - 进程和线程区别
  - 多线程模型
  - 多线程问题
    - fork 和 exec
    - 信号处理
    - 线程撤销
    - 线程本地存储
    - 调度程序激活
    - 进程调度
      - 调度程序与调度准则
      - 调度算法
- 同步
  - 线程间同步的方式
  - 进程间同步的方式
  - 临界区与临界资源
  - 互斥锁
  - 信号量
  - 经典问题
    - 生产者消费者
    - 哲学家就餐
- 死锁
  - 产生条件
  - 死锁预防
  - 死锁避免
    - 安全状态
    - 资源分配图
    - 银行家算法
  - 死锁检测
  - 死锁恢复
- 内存管理策略
  - 两个寄存器
  - 逻辑,物理地址空间与虚拟寻址
  - 交换
  - 内存管理机制
    - 概述
    - 块式管理
    - 可变分区相关概念
    - 页式管理
      - 基本概念
      - 保护
      - 硬件支持
      - 页表种类
    - 段式管理
    - 段页式管理
    - 分段和分页区别与共同点
- 虚拟内存管理
  - 虚拟地址，线性地址，物理地址
  - 什么是虚拟内存
  - 虚拟内存的技术实现
  - 请求调页与缺页错误
  - 写时复制
  - 局部性原理
  - 页面置换算法
  - 系统抖动
- 文件系统
- 文件系统实现
  - 分配方法
- 大容量存储系统
- I/O 系统
  - 缓冲与缓存
  - 多路复用
  - 五种 IO 网络模型
    - 概念说明
    - 具体模型
      - 同步阻塞 IO（blocking IO）
      - 同步非阻塞 IO（nonblocking IO）
      - IO 多路复用（ IO multiplexing）
      - 信号驱动式 IO（signal-driven IO）
      - 异步非阻塞 IO（asynchronous IO）
      - 关于异步阻塞
  - 对比
    - blocking 和 non-blocking 区别
    - synchronous IO 和 asynchronous IO 区别

## 算法<!-- fold -->

## Linux <!-- fold -->

### 常用命令

### shell

## 编码与加密算法 <!-- fold -->

# Java 知识栈

## Java 基础 <!-- fold -->

### 基础 <!-- fold -->

#### 基础中的基础

- 基本概念
  - JRE 与 JDK
  - Java 7 和 Java SE 7
  - JDK1.8 和 Java8

- Object 的 11 个方法
  - equals
    - `常量.equals`
    - `Objects.equals` **推荐**
    - [hashcode 和 equals(重要)](https://www.cnblogs.com/skywang12345/p/3324958.html)

- 面向对象
  - 封装
  - 继承
    - 不支持多继承 <!-- fold -->
      - 钻石问题
      - 可维护性和设计
  - 多态
    - 上转下转
    - 深入(JVM 内容)
      - 动态链接
      - 非虚方法和虚方法
      - 方法重写本质
      - 虚方法表

- Java 异常体系

- 常用关键字

- [静/非静态内部类](https://juejin.cn/post/6844903791863529480)
  - 是否懒加载
  - 是否可以创建静态成员
  - 能否调用调用外部类静态成员
  - 有没有指向外部类的引用<br />`外部类类名.this`
  - 创建方式

- 枚举
  - 本质
  - 常见用法

- 包装类

  - 缓存机制
    - Integer 当数值在-128 ~127 时，会将创建的 Integer 对象缓存起来
    - Character 当数值在 0-~127 时，会将创建的 Character 对象缓存起来
    - 因此，整型包装类对象之间值的比较，全部使用 equals 方法比较
  - BigDecimal
    - 浮点数之间的等值判断
      - 基本数据类型不能用==来比较（精度丢失）
      - 包装数据类型不能用 equals 来判断
      - **使用 BigDecimal 来定义浮点数的值，再进行浮点数的运算操作**
    - 使用 BigDecimal 来定义浮点数的值，再进行浮点数的运算操作。
    - 推荐使用 String 作为参数传入 BigDecimal 构造方法
  - 使用标准
    - 所有的 POJO 类属性必须使用包装数据类型。
    - RPC 方法的返回值和参数必须使用包装数据类型。
    - 所有的局部变量使用基本数据类型。

- Arrays.asList
  - Arrays.asList()将数组转换为集合后,底层其实还是数组
  - 传递的数组必须是对象数组，而不是基本类型。<br />**当传入一个原生数据类型数组时，Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身**
  - 使用集合的修改方法:`add()、remove()、clear()`会抛出异常。<br />Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,
  - 如何正确的将数组转换为 ArrayList?
    - `new ArrayList<>(Arrays.asList("a", "b", "c"))`(推荐)
    - 使用 Java8 的 Stream(推荐)
    - 使用 Guava(推荐)
    - 使用 Apache Commons Collections
    - 使用 Java9 的 List.of()方法

#### [反射](https://www.cnblogs.com/yougewe/p/10125073.html)

- 说明
- 原理
  - 如何获取
  - 成员的查找
  - 线程安全
  - 内存使用（软指针）
  - 数据隔离
  - 方法执行
- 应用
  - [重写 private 方法](https://www.cnblogs.com/lixinjie/p/combine-reflect-and-oo-in-java.html)
  - 框架编写

#### java 泛型

- 什么是泛型
- 原理与类型擦除
- 使用方式和场景
- 泛型通配符
- 泛型数组

- 常见问题：
  - T ,T extends xxx, ？,？extends xxx <br />和 ？super xxx 的区别？
  - 为何不能通过直接通过`T[] arr=new T[10]`的方式来创建数组<br />如何正确创建泛型数组。

#### String

- String,StringBuilder,StringBuffer 使用场景
- AbstractStringBilder,建造者
- synchronized
- 字符串拼接
  - 编译器优化
  - StringBuilder
- 字符串常量池

#### SPI

- 原理
  - api
  - spi
- 示例
  - java spi
    - 提供的 api 及使用
    - DriverManager
      - mysql 自动加载
      - oracle 必须手动加载
  - Spring spi 思想
    - scan
    - 自定义 scope
    - 自定义标签

#### java8 新特性

- Stream
- Function Interface
- Lambda
- Optional
- Data Time-api

#### 语法糖

- 双大括号初始化(不推荐)
- try-with-resources(针对 io 资源，推荐)

### 集合 <!-- fold -->

- 为什么要使用集合
- HashMap
  - 1.7
    - 存储结构：数组+链表
    - 扩容机制
      - 创建新的数组
      - 头插 <!-- fold -->
        - 使用头插可能与缓存的时间局部性原则有关
        - 最近访问过的数据下次大概率会再次访问
        - 把刚访问过的元素放在链表最前面可以直接被查询到，减少查找次数
      - [resize 死循环](https://juejin.cn/post/6844903554264596487)
      - resize 重新计算 hash
  - 1.8
    - 存储结构
      - 数组+链表+红黑树
      - 红黑树结构转换条件
        - 数组长度 64
        - [树化阀值 8 来源](https://juejin.cn/post/6921914880559677447])
        - 退化阀值 6
    - 插曲：[红黑树由来：2-3 树](https://blog.csdn.net/zhichaosong/article/details/88844371)
    - 扩容机制
      - 创建新的数组
      - 尾插
      - [如何解决的 resize 死循环](https://www.fangzhipeng.com/javainterview/2019/03/11/hashmap-dead-cycle.html) <!-- fold -->
        - transfer 调用 <!-- fold -->
          - 直接将原来 transfer()方法中的代码写在自己方法体内，不再调用
        - 使用尾插 <!-- fold -->
          - **扩容后，新数组中的链表顺序依然与旧数组中的链表顺序保持一致**
      - [resize 不重新计算 hash](https://juejin.cn/post/6844903682664824845) <!-- fold -->
        - 1.7 扩容时需要重新计算哈希值和索引位置
        - 1.8 并不重新计算哈希值，巧妙地采用和扩容后容量进行&操作来计算新的索引位置。
  - 线程不安全
    - put 的时候导致的多线程数据不一致
  - 初始化大小
  - 扩容时机
    - threshold = capacity \* loadFactor
    - Size>=threshold
    - [loadFactor 0.75 来源](https://www.cnblogs.com/aspirant/p/11470928.html)
  - put 流程
  - 容量为什么要 2 的幂
  - [遍历方法及性能](https://mp.weixin.qq.com/s/Zz6mofCtmYpABDL1ap04ow)
  - 有什么同步容器/并发容器
  - HashMap 漏洞导致变慢 <!-- fold -->
    - 中间对计算 hashcode 的成员变量做了修改，内存泄漏
    - hashcode 设计不标准，元素落到一个桶中
  - 如何为 key 设计 hashcode <!-- fold -->
    - 计算 hashCode 依赖的值是不可变
    - hashCode 必须基于对象的内容生成
    - hashCode 产生的散列码最好能均匀分布
  - HashMap,HashTable,ConcurrentHashMap 区别
    - 1.7
      - 三者数据结构
      - 同步机制/锁
      - `put(null)`
    - 1.8
      - 三者数据结构
      - 同步机制/锁
      - `put(null)`
- TreeMap
  - 底层结构：红黑树
  - 红黑树由来：[2-3 树](https://blog.csdn.net/zhichaosong/article/details/88844371)
    - 2-节点,3-节点
    - 4-节点分解
    - 不平衡时融合
- ConcurrentHashMap
  - [1.7](https://www.cnblogs.com/ITtangtang/p/3948786.html)
    - 数据结构:分段锁+数组+链表
    - 同步机制:
      - **segment 分段锁** 粒度为一段(几个 hash 槽)<br />分段锁继承了 ReentrantLock
      - 尝试获取锁存在并发，竞争，阻塞
    - 键值对:HashEntry
    - 操作
      - size
      - put
      - get
  - [1.8](https://blog.csdn.net/programmer_at/article/details/79715177)
    - 数据结构:数组+链表/红黑树
    - 同步机制
      - hash 槽。减小了加锁粒度
      - CAS+synchronized <!-- fold -->
        - CAS 失败自旋保证成功
        - 再失败就 synchronized
      - [使用 synchronized 原因](https://www.cnblogs.com/aspirant/p/8623864.html) <!-- fold -->
        - 锁粒度降低了，synchronized 并不比 ReentrantLock 差
        - synchronized 优化空间大
        - 大量数据操作，ReentrantLock 开销较多内存
    - 键值对：node
    - 操作
      - size
      - put
      - get
- [LinkedHashMap](https://www.jianshu.com/p/8f4f58b4b8ab)
  - 结构：HashMap+双向链表
  - 两种模式(accessOrder 变量)
    - 插入顺序模式(false)
    - 访问顺序模式(true)
      - `get`
      - 重排序
  - 扩容:遍历链表
  - 实现 LRU
    - 构造器:传入 capacity
    - removeEldestEntry:`return size() > capacity;`

- ArrayList
  - 底层结构：数组
  - 初始大小与扩容机制
  - RandomAccess 接口
  - `System.arraycopy()` 和 `Arrays.copyOf()`方法
  - 有什么同步容器/并发容器
  - **[fail-fast、fail-safe 机制](https://juejin.cn/post/6879291161274482695)**
- LinkedList
  - 底层结构：双向链表
- Set
  - comparable 和 Comparator 的区别
  - 无序性和不可重复性的含义是什么
  - 有什么同步容器/并发容器
  - HashMap 和 HashSet
  - TreeMap 和 TreeSet
- 集合比较问题
  - List,Set,Map 三者的区别？
  - Arraylist 和 Vector 的区别?
  - Arraylist 与 LinkedList 区别?
  - ConcurrentHashMap 和 Hashtable 的区别
  - 比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同
  - HashMap 和 HashTable 区别
  - HashMap 与 HashSet 区别（HashSet 底层基于 HashMap）
  - HashMap 和 TreeMap 区别

- [其他重要问题](https://www.jianshu.com/p/8bc28d6b0a5e)

### 并发编程 <!-- fold -->

#### **体系结构** <!-- fold -->

- 理论体系
  - 为什么需要多线程
  - 线程不安全案例
  - 并发出现问题的根源
    - 可见性
    - 原子性
    - 有序性
  - Java 并发问题解决方案
    - 关键字
      - volatile
      - synchronized
      - final
    - Java 内存模型: <br /> 共享内存并发模型
  - 线程安全不是绝对的
    - 不可变
    - 绝对线程安全
    - 相对线程安全
    - 线程兼容
    - 线程对立
  - Java 中线程安全的实现
    - 互斥同步
      - synchronized
      - ReentrantLock
    - 非阻塞同步
      - CAS
      - Atomic(CAS 的具体实现)
    - 无同步方案
      - 栈封闭
      - 线程本地存储-ThreadLocal
      - 可重入代码(ReentrantLock)
- 线程基础
  - 线程状态转换
    - new
    - runalbe
    - blocking
    - waiting
    - timed waiting
    - terminated
  - 线程使用方式
    - 实现 Runable 接口
    - 实现 Funture 接口
    - 继承 Thread 类
  - 基础线程机制
    - Executor
    - Daemon
    - sleep
    - yield
  - 线程中断
    - interrupteedException
    - interrupt()
    - Executor 的中断操作
  - ThreadGroup 处理未捕获异常
  - 线程互斥同步
    - synchronized
    - ReentrantLock
  - 线程协作
    - join
    - wait,notify,notifyAll
    - await,signal,signalAll
- JUC 工具类
  - Locks
    - Lock 接口
    - AQS
    - Condition
    - LockSupport
    - 重入锁：ReentrantLock
    - 读写锁：ReadWriteLock
  - 并发容器
  - Executors
    - Executor
    - ThreadPoolExecutor
    - FutureTask
  - Atomic
    - 基本类型
    - Array
    - Reference
    - FieldUpdator
  - Tools
    - CountDownLatch
    - CyclicBarrier
    - Semaphore
    - Exchanger
- 并发本质
  - 协作
    - 并发实现
      - 管程
        - Lock&Condition
        - synchronized
      - 信号量
        - Semaphore
      - CountDownLatch
      - CyclicBarrier
      - Phaser
      - Exchanger
  - 分工
    - 并发实现
      - Executor 与线程池
      - Fork/Join
      - Future
    - 并发模式
      - Guarded Suspension 模式
      - ThreadPreMessage 模式
      - Balking 模式
      - Worker Thread 模式
      - 两阶段终止模式
      - 生产消费模式
  - 互斥
    - 并发实现
      - 无锁
        - CAS
        - Atomic
        - 并发模式
      - 互斥锁
        - synchronized
        - lock
        - ReadWriteLock
    - 并发模式
      - Immutabliility 模式
      - CopyOnWrite 模式
      - 线程本地存储模式
- 框架案例
  - Guava RatelLimitor
  - Netty
  - Disruptor
  - HiKariCP

#### 详细讲解 <!-- fold -->

- 并发基础

  - [创建线程的方式](https://segmentfault.com/a/1190000037589073)
  - 调用`start()`和`run()`方法区别
  - 停止线程：interrupt
    - 原理
    - 正确的停止方式
    - 错误的停止方式
      - 被弃用的 stop,suspend 和 resume 方法
      - 用 volatile 设置标记位
  - 重要方法
    - Object(三个方法都要在 synchronized 内)
      - wait
      - notify
      - notifyAll
    - Thread
      - sleep
      - join
      - yield
  - yield 和 sleep 区别
  - java 线程状态的切换<br /> 延伸：操作系统进程状态的切换
  - 线程属性
  - 线程的未捕获异常处理
  - 线程组
    - 结构
      - 线程组是一个树状的结构，每个线程组下面可以有多个线程或者线程组
      - 默认将父线程（当前执行 new Thread 的线程）线程组设置为自己的线程组。
    - 线程组的优先级会限制线程的优先级
    - 作用
      - 统一控制线程的优先级
      - 检查线程的权限的作用。
      - 线程组统一异常处理
  - 常见问题
    - 并发与并行区别
    - 为什么要使用多线程
    - sleep() 方法和 wait() 方法区别和共同点

- **并发编程三大特性** <!-- fold -->
  - 原子性
  - 可见性
  - 有序性

- JMM
  - 基础结构
  - JMM 与 Java 内存区域划分的区别与联系
  - JMM 原子操作 <!-- fold -->
    - read(读取）:从主内存读取数据
    - load(载入）:将主内存读取到的数据写入工作内存
    - use(使用）:从工作内存读取数据来计算
    - assign(赋值）:将计算好的值重新赋值到工作内存中
    - store(存储）:将工作内存数据写入主内存
    - write(写入）:将 store 过去的变量值赋值给主内存中的变量
    - lock(锁定）:将主内存变量加锁，标识为线程独占状态。其他线程将无法读或写
    - unlock(解锁）:将主内存变量解锁，解锁后其他线程可以锁定该变量
  - 重排序
    - 组成 <!-- fold -->
      - 编译器优化重排
      - 指令并行重排
      - 内存系统重排
    - 编程规则
      - as-if-serial
      - happens-before

- [volatile](https://zhuanlan.zhihu.com/p/137193948)
  - 保证内存可见性
    - 说明
    - 原理
      - MESI 缓存一致性协议
      - cpu 总线嗅探机制
      - lock
    - 总线风暴
    - [修饰引用变量的陷阱](https://blog.csdn.net/u010454030/article/details/80800098)
    - [修饰数组](https://blog.csdn.net/u014674862/article/details/89168376)|[资料](https://stackoverflow.com/questions/53753792/java-volatile-array-my-test-results-do-not-match-the-expectations)
  - 禁止重排序
    - 说明
    - 原理(内存屏障) <!-- fold -->
      - volatile 写
        - 前面:storestore
        - 后面:sotreload
      - volatile 写
        - 后面 1:loadload
        - 后面 2:loadstore
  - 不保证原子性：
    - 原因
    - 解决
      - synchronized
      - Atomic(CAS)

- 锁的变迁
  - jdk1.5 之前:synchronized 重量锁
  - jdk1.5
    - 原因
    - 变化:增加
      - Lock 锁
      - 并发容器
      - 线程池
    - 依据原理
      - Atomic--UnSafe--CAS
      - AQS
      - LockSupport
      - volatile
  - jdk1.6
    - 变化:synchronized 锁的升级
    - 原因
  - jdk1.8:增加 StampedLock

- synchronized
  - 3 种使用方法
    - 代码块
      - 自己指定对象锁
      - **底层原理**：
        - monitorenter
        - monitorexit
        - 程序计数器
    - 成员方法
      - this 对象锁
      - **底层原理**：ACC_SYNCHRONIZED
    - 静态方法
      - .class 类锁
      - **底层原理**：ACC_SYNCHRONIZED，ACC_STATIC
    - 注意：构造方法本身就属于线程安全的，不存在同步的构造方法一说。<br />不能加 synchronized
  - 锁的升级(不可逆)
    - 无锁(CAS)
    - 偏向锁
      - 原理
      - 锁的性质
      - 对象头中的内容
      - 升级过程
    - 轻量锁
      - 原理
      - 锁的性质
      - 对象头中的内容
      - 升级过程
    - 重量锁
      - 原理
      - 锁的性质
      - 对象头中的内容
      - 阻塞的好处(cpu)与代价(内核态)
  - 特性保证
    - 可见性 <!-- fold -->
      - 获得锁，清空工作内存
      - 从主内存拷贝共享变量最新的值到工作内存成为副本
      - 执行代码，将修改后的副本的值刷新回主内存中，线程释放锁
      - 而获取不到锁的线程会阻塞等待，所以变量的值肯定一直都是最新的。
    - 原子性 <!-- fold -->
      - 单一线程持有
      - 可见性的强制刷新
    - 有序性 <!-- fold -->
      - as-if-serial
      - happends-before
  - synchronized 和 ReentrantLock 的区别 <!-- fold -->
    - jvm-api 层面
    - 是否可中断
    - 公平非公平
    - 锁绑定多个条件
  - synchronized 和 volatile 的区别

- CAS
  - 乐观锁与悲观锁
  - 概念
  - 底层原理
  - CAS 的实现：Unsafe
  - CAS 的应用：Atomic
  - 问题
    - ABA 问题
      - AtomicStampedReference
      - AtomicMarkableReference
    - 循环时间长开销大
      - 解决思路是让 JVM 支持处理器提供的 pause 指令
    - 只能保证一个共享变量的原子操作
      - 使用 JDK 1.5 开始就提供的 AtomicReference 类保证对象之间的原子性，<br />把多个变量放到一个对象里面进行 CAS 操作；
      - 使用锁。锁内的临界区代码可以保证只有当前线程能操作。

- AQS
  - 概念
  - 底层
    - Unsafe(提供 CAS 操作)
    - LockSupport(提供 park/unpark 操作)
  - CLH 数据结构
  - 资源共享模式/同步方式
  - **模版设计模式**
  - 源码分析
    - 获取资源流程
      - acquire
      - acquireShared
    - 释放资源流程
      - release
      - releaseShared
  - 两个队列
    - CLH 队列
    - 条件队列
  - AQS 组件
    - AQS 实现的锁(实现 Lock 接口)
      - ReentrantLock
        - 内部类 Sync 继承 AQS
        - Condition 底层使用 LockSupport
      - ReentrantReadWriteLock
        - 读锁和写锁都有继承 AQS 的内部类 Sync
    - AQS 通信工具类
      - Semaphore
        - 内部有一个继承了 AQS 的同步器 Sync
      - CountDownLatch
        - 内部有一个继承了 AQS 的同步器 Sync
      - CyclicBarrier
        - 内部使用 ReentrantLock
  - 其他
    - AOS
    - AQLS

- Atomic
  - 组成
    - 基本类型
    - 数组类型
    - 引用类型
    - 对象的属性修改类型
  - AtomicInteger
    - 示例
    - 基本原理

- LockSupport
  - 是什么
  - 使用原理
  - [底层原理](https://blog.csdn.net/saintyyu/article/details/107426428)
  - [常见问题](https://www.pdai.tech/md/java/thread/java-thread-x-lock-LockSupport.html)
    - park 底层使用的是`UNSAFE.park`
    - 为什么 LockSupport 也是核心基础类? AQS 框架借助于两个类：Unsafe(提供 CAS 操作)和 LockSupport(提供 park/unpark 操作)
    - 写出分别通过 wait/notify 和 LockSupport 的 park/unpark 实现同步?
    - LockSupport.park()会释放锁资源吗? 那么 Condition.await()呢?
    - Thread.sleep()、Object.wait()、Condition.await()、LockSupport.park()的区别? **重点**
    - 如果在 wait()之前执行了 notify()会怎样? 如果在 park()之前执行了 unpark()会怎样?

- 锁、通信工具类<br />和底层使用
  - AQS 实现的锁(实现 Lock 接口)
    - ReentrantLock
      - 内部类 Sync 继承 AQS
      - Condition 底层使用 LockSupport
    - ReentrantReadWriteLock
      - 读锁和写锁都有继承 AQS 的内部类 Sync
  - AQS 通信工具类
    - Semaphore
      - 内部有一个继承了 AQS 的同步器 Sync
    - CountDownLatch
      - 内部有一个继承了 AQS 的同步器 Sync
    - CyclicBarrier
      - 内部使用 ReentrantLock
  - 非 AQS 的通信工具类
    - Exchanger
      - LockSupport
      - CAS(Atomic)
    - Phaser
      - 有使用 LockSupport

- 锁的种类
  - 锁的有无
    - 乐观锁
    - 悲观锁
  - synchronized 的锁
    - 无锁
    - 偏向锁
    - 轻量锁
    - 重量锁
  - 锁的性质分类
    - 可重入锁和非可重入锁
      - 表现
      - 原理：粒度（加锁范围）
      - 实例 <!-- fold -->
        - 可重入锁
        - 不可重入锁
    - 公平锁与非公平锁 <!-- fold -->
      - 表现
      - 原理
      - 实例
        - 公平锁
        - 非公平锁
        - 可以切换:ReentrantLock
    - 读写锁和排它锁 <!-- fold -->
      - 表现
      - 原理
      - 实例
        - 读写锁
        - 排它锁
    - 是否可中断

- 并发集合容器
  - [什么是同步容器和并发容器](https://juejin.cn/post/6844903954719965192)
    - 同步容器：synchronized
    - 并发容器：AQS 锁、CAS、COW、分段锁
  - 同步容器
    - vector:在面对多线程下的复合操作的时候也是需要通过客户端加锁的方式保证原子性
    - HashTable
    - Collections 下的各种 SynchronizedXXX
  - 并发容器
    - Queue
      - BlockingQueue
        - ArrayBlockingQueue
        - LinkedBlockingQueue
        - DelayQueue
        - Priority BlockingQueue
        - SynchronousQueue
        - LinkedBlockingDeque
        - LinkedTransferQueue
      - ConcurrenLinkedQueue
        - Unsafe:CAS
        - [LinkedBlockingQueue 与 ConcurrentLinkedQueue 的区别](https://blog.csdn.net/lzxlfly/article/details/86710382)
    - ConcurrentMap
      - ConcurrentHashMap
        - 看 HashMap 那里
      - ConcurrentNavigableMap
      - ConcurrentSkipListMap
    - CopyOnWrite
      - CopyOnWriteArrayList
        - ReentrantLock 加锁
      - CopyOnWriteArrayMap
      - CopyOnWriteArraySet

- 线程池
  - 结构
    - 任务(Runnable /Callable)
    - 任务的执行(Executor)
    - 异步计算的结果(Future)
  - 创建
    - ThreadPoolExecutor 构造方法参数及含义 <!-- fold -->
      - int corePoolSize：该线程池中核心线程数最大值
      - int maximumPoolSize：该线程池中线程总数最大值 。
      - long keepAliveTime：非核心线程闲置超时时长。
      - TimeUnit unit：keepAliveTime 的单位。
      - BlockingQueue workQueue：阻塞队列，维护着等待执行的 Runnable 任务对象。
      - ThreadFactory threadFactory：<br />创建线程的工厂 ，用于批量创建线程，统一在创建线程时设置一些参数，<br/>如是否守护线程、线程的优先级等。<br />如果不指定，会新建一个默认的线程工厂。
      - RejectedExecutionHandler handler 拒绝策略
        - AbortPolicy：默认拒绝处理策略，丢弃任务并抛出 RejectedExecutionException 异常。
        - DiscardPolicy：丢弃新来的任务，但是不抛出异常。
        - DiscardOldestPolicy：丢弃队列头部（最旧的）的任务，然后重新尝试执行程序（如果再次失败，重复此过程）。
        - CallerRunsPolicy：由调用线程处理该任务。
    - Executors 默认实现(底层使用 ThreadPoolExecutor) <!-- fold -->
      - FixedThreadPool
        - 参数设置
        - 执行过程
        - 弊端
      - CachedThreadPool
        - 参数设置
        - 执行过程
        - 弊端
      - SingleThreadExecutor
        - 参数设置
        - 执行过程
        - 弊端
  - **线程池工作流程**
  - ThreadPool 状态转换 <!-- fold -->
    - RUNNING
    - SHUTDOWN
    - STOP
    - TIDYING
    - TERMINATED
  - ScheduledThreadPool <!-- fold -->
    - 继承了 ThreadPoolExecutor
    - 主要用来在给定的延迟后运行任务，或者定期执行任务
    - 实际项目中会使`用quartz`
  - **为什么不要用默认实现** <br/> (上面的默认实现有什么弊端)
  - 参数如何设置(N+1,2N)
  - [异常线程处理](https://mp.weixin.qq.com/s?__biz=Mzg3NjU3NTkwMQ==&mid=2247505057&idx=1&sn=621ebc409b589478e2e05388e079d8c0&source=41#wechat_redirect)
  - 常见区别
    - sumbit() vs execute()
      - execute()方法不会返回结果
      - submit()会返回一个 FutureTask 对象，并可以获得结果
      - [异常处理](https://www.jianshu.com/p/29610984f1dd)
    - Runnable vs Callable
    - shutdown() vs shutdownNow()

- **ThreadLocal(待做)**

#### JUC 整理 <!-- fold -->

#### 并发本质 <!-- fold -->

#### 并发模式 <!-- fold -->

#### 并发框架 <!-- fold -->

### IO/NIO/AIO <!-- fold -->

## JVM <!-- fold -->

### 基础知识 <!-- fold -->

- jvm 发展
- 特点：
  - 一次编译，到处运行
  - 自动内存管理
  - 自动垃圾回收
- 架构模型
  - 基于栈式架构
  - 基于寄存器架构
- JVM 生命周期
  - 启动
  - 执行
  - 退出

### 内存与垃圾回收 <!-- fold -->

#### 上层

- 画出详细架构
- 类加载过程
  - loading 加载(加载和链接交叉进行)
    - 目的
  - linking 链接(加载和链接交叉进行)
    - verify 验证
      - 目的
    - prepare 准备
      - 目的
    - Resolve 解析
      - 目的
      - 执行时机
  - initialization 初始化
    - 目的
    - 特点
      - 顺序执行
      - 父类先与子类
      - `<clint>`多线程同步加锁
- 类加载器
  - 分清关系
    - 继承关系
    - 上下层关系<br />(涉及双亲委派)
    - 包含关系<br />(自定义类加载器包含其他 java 编写类加载器)
  - 通常分类
    - BootStrap ClassLoader
      - 目的
      - 父类
      - 加载库
    - Extension ClassLoader
      - 目的
      - 父类
      - 加载库
    - AppClassLoader ClassLoader
      - 目的
      - 父类
      - 加载库
  - 按照规范
    - 引导类加载器（Bootstrap ClassLoader） c/c++编写
    - 自定义类加载器（User-Defined ClassLoader） java 编写
      - 为何要自定义类加载器
  - 抽象类 ClassLoader
    - 继承结构
    - 虚拟机的入口应用:`sun.misc.Launcher`
    - 获取 classloader 的方式
  - **双亲委派机制**
    - **原理**
    - 自定义 java.lang.String 是否会加载
    - 优势
      - 避免类的重复加载
      - 保护程序安全，防止核心 api 被随意篡改
    - 例外案例
  - 沙箱安全机制
  - 其他
    - jvm 中判断两个 class 对象是否为同一个类 2 个判断条件
    - 类加载器引用哦作为类型信息存于方法区
    - 引用类型转换时，要保证两个类加载器相同

#### 中层

- **Java 内存区域布局说明**
- 程序计数器
  - 是什么
    - 线程私有
    - 取值
    - 作用
    - 没有 OOM
    - 生命周期
  - 问题
    - 为什么使用 PC 寄存字器记录当当前线程的执行地址
    - pc 寄存器为什么被设定为线程私有
- 虚拟机栈
  - 是什么
    - 线程私有
    - 对应方法调用，入栈，出栈
    - 作用
    - 没有垃圾回收
    - 异常
  - 参数设置
    - `-Xss`
  - 栈帧
    - 是什么
      - Java 虚拟机栈（Java Virtual Machine Stack),早期也叫 Java 栈。
      - 每个线程在创建时都会创建一个虚拟机栈，<br />其内部保存一个个的栈帧 (Stack Frame),对应着一次次的 Java 方法调用。
      - 是线程私有的
    - 运行原理-栈帧弹出
    - 内部组成
      - 局部变量表
        - 作用
        - 生命周期
        - slot
          - 概念/作用
          - 32 位,64 位占用
          - 存储数据类型
          - 存储内容与顺序
          - 槽的重用
        - 内存分配(**编译期间**完成分配)
        - 线程私有不存在数据安全问题
        - 垃圾回收：局部变量表中的变量也是重要的垃圾回收根节点
      - 操作数栈
        - 编译时就已经确定深度`max_stacks`
        - 操作：出栈入栈
        - 具体流程示例
        - 栈顶缓存技术
      - 帧数据区
        - 动态链接
          - 什么是动态链接
          - **深入**：方法的调用：解析和分派
            - 绑定与链接
              - 早期绑定
              - 晚期绑定
              - 静态连接
              - 动态链接
            - **非虚方法与虚方法**
              - 什么是非虚方法 <!-- fold -->
                - 静态方法
                - 私有方法
                - final 方法
                - 构造器
                - 父类方法(使用 super 显式调用)
              - 什么是虚方法 <!-- fold -->
                - 非虚方法之外的方法
              - 无个调用指令 <!-- fold -->
                - invokespecial
                - invokestatic
                - invokevirtual
                - invokeinterface
                - invokedynamic
              - 方法重写本质
              - 虚方法表
                - 出现原因
                - 作用
                - 内存位置
                - 建立时期
                - 插入条目<br />(需要解释这些信息)
                  - 符号引用
                  - 直接引用
                  - 解析时机
        - 方法返回地址
          - 栈帧退出方式
          - 作用
        - 一些附加信息
  - 问题 <!-- fold -->
    - 栈中可能出现的异常
    - i++和++i 的区别。<br />从字节码，局部变量表，操作数栈层面解释
    - 举例栈溢出的情况？(StackOverflowError)
    - 调整栈大小，就能保证不出现溢出吗？
    - 分配的栈内存越大越好吗？
    - 垃圾回收是否会涉及到虚拟机栈？
    - 方法中定义的局部变量是否线程安全？
- 本地方法接口+库(非运行时数据区结构)
  - 定义:一个 Native Method 就是一个 Java 调用非 Java 代码的接口。
  - 特点:该方法的实现由非 Java 语言实现，比如 C。
  - 目的:
    - 与 java 环境外交互
    - 与操作系统交互
    - Sun's java
- 本地方法栈
  - 目的
  - 在 Hotspot JVM 中，直接将本地方法栈和虚拟机栈合二为一。

- 堆
  - 空间划分与比例`1:2` `6:1:1`
  - 自适应调节策略
  - 堆大小设置参数`-Xms` `-Xmx`
    - 默认 1/64,1/4
  - 对象分配
    - 一般过程
    - 特殊情况
  - 四种 GC
    - Minor GC
      - 回收区域
      - 触发机制
      - 耗时代价
      - Survivor 的被动收集
    - Major GC
      - 回收区域
      - 触发机制
      - 耗时代价
    - Mixed GC
      - 回收区域
      - 只有 G1 回收器有该 GC
    - Full GC
      - 回收区域
      - 触发机制
      - 耗时代价
  - TLAB:快速分配策略。
    - 作用
    - 分配流程：
      - JVM 确实是将 TLAB 作为内存分配的首选
      - 失败后，JVM 就会尝试着通过使用加锁机制确保数据操作的原子性，<br />从而直接在 Eden 空间中分配内存。
    - 大小：默认整个 Eden 空间的 1%<br /> `-XX:TLABWasteTargetPercent`设置
  - 逃逸分析<br />( **十分不成熟，Hotspot 也没用** )
    - 种类：
      - 方法逃逸
      - 线程逃逸
    - 基于逃逸分析的优化
      - 栈上分配(就没实现)
      - 同步省略
      - 标量替换
  - 堆是分配对象的唯一选择:否定->肯定

- 方法区
  - 特点
    - 线程共享
    - 本地内存
  - 存储内容：具体看 **细节演进**
  - 大小设置
    - 1.7
      - `-XX:PermSize`
      - `-XX:MaxPermSize`
    - 1.8
      - `-XX:MetaspaceSize`<br />初始分配空间大小，<br />同时也是水位线大小
      - `-XX:MaxMetaspaceSize`方法区最大大小，默认-1
      - 水位线--full GC--自动调整
  - **内部结构演进变迁**:看图去
  - 为什么要移除永久代
  - 常量池和运行时常量池
  - 为何要移除永久代
  - **什么是符号引用**<br />
    - 说明：
      - 以元祖 JVM:Sun JDK 1.0.2 的 32 位 x86 为例
      - 整体原理类似
    - 符号引用
      - 说明 <!-- fold -->
        - 通常是设计字符串的，是用文本形式来表示引用关系
        - 实际使用时可以藉此找到相应的位置
    - 直接引用
      - 表现形式 <!-- fold -->
        - 指向 methodblock 的指针
        - 虚方法表下标
      - 说明
    - 解析时机
  - 垃圾回收
    - 是否要回收
      - 《规范》提到过可以不要求虚拟机在方法区中实现垃圾收集
      - 回收条件苛刻，效果比较难令人满意。
    - 回收内容
      - 常量池中废弃的常量
        - 回收策略
        - 组成 <!-- fold -->
          - 字面量常量
          - 符号引用
      - 不再使用的类
        - 回收策略 <!-- fold -->
          - 该类所有的实例都已经被回收
          - 加载该类的类加载器已经被回收
- 问题 <!-- fold -->
  - 说一下 JVM 内存模型吧，有哪些区？分别干什么的？
  - Java8 的内存分代改进
  - JVM 内存分哪几个区，每个区的作用是什么？
  - JVM 内存分布/内存结构？栈和堆的区别？堆的结构？为什么两个 survivor 区？
  - Eden 和 Survior 的比例分配
  - jvm 内存分区，为什么要有新生代和老年代
  - Java 的内存分区
  - 讲讲 jvm 运行时数据库区
  - 什么时候对象会进入老年代？
  - JVM 内存为什么要分成新生代，老年代，持久代。<br />新生代中为什么要分为 Eden 和 Survivor。
  - Jvm 内存模型以及分区，需要详细到每个区放什么。
  - JVM 的内存模型，Java8 做了什么修改
  - JVM 内存分哪几个区，每个区的作用是什么？
  - java 内存分配
  - jvm 的永久代中会发生垃圾回收吗？
  - jvm 内存分区，为什么要有新生代和老年代？

- **对象的实例化内存布局和访问定位**
  - 创建对象的方式
  - 创建对象的步骤（六步） <!-- fold -->
    - 判断对象对应的类是否加载，链接，初始化。（加载类元信息）
    - 为对象分配内存
      - 指针碰撞
        - 原理
        - 对应算法
      - 空闲列表分配
        - 原理
        - 对应算法
    - 处理并发安全问题
      - CAS
      - TLAB
    - 初始化分配到的空间
    - 设置对象的对象头
    - 执行 init 方法进行初始化
      - 属性初始化方式
        - 1.默认初始化
        - 2.显式初始化
        - 3.代码块中初始化
        - 4.构造器中初始化
        - 5.对象.属性 初始化
      - init 中包括 2,3,4
  - 内部布局
    - 对象头(下面 3 项都是一字宽)
      - Mark Word--存储对象的 hashCode 或锁信息等
        - 不同锁状态内容不同
      - Class Metadata Address--存储到对象类型数据的指针
      - Array length--数组的长度（如果是数组）
    - 实例数据
    - 对齐填充:占位符
  - 定位访问
    - 句柄访问
      - 说明
      - 优点
      - 缺点
    - 直接指针
      - 说明
      - 优点
      - 缺点
  - 问题 <!-- fold -->
    - 对象在 JVM 中是怎么存储的？
    - 对象头信息里面有哪些东西？
    - java 对象头里有什么
- 直接内存

#### 下层

- 执行引擎
  - 作用
  - 相关概念
    - 编译原理
    - 机器码
    - 指令
    - 指令集
    - 汇编
    - 高级语言
  - 编译器分类
    - 前端编译器<br />java-->.class
    - 后端运行期编译器：JIT<br /> .class-->机器码
    - 静态提前编译器：AOT(Ahead of Time Compiler)编译器，<br /> .java-->机器码
  - Java 代码执行方式分类
    - 种类
      - 源代码编译成字节码文件，<br />然后在运行时通过解释器将字节码文件转为机器码执行
      - 直接编译成机器码
    - HotSpot 执行方式
      - `-Xint`:完全采用解释器模式执行程序；
      - `-Xcomp`:完全采用即时编译器模式执行程序。如果即时编译出现问题，解释器会介入执行。
      - `-Xmixed`(默认):采用解释器+即时编译器的混合模式共同执行程序。
  - Java 执行引擎组成
    - 解释器
      - 为什么 java 源码到机器语言中间要有个字节码
      - 工作机制
      - 种类
        - 字节码解释器
        - 模板解释器
    - 即时编译器
      - 工作机制
      - 热点代码
        - 概念
        - 热点探测
          - 方法调用计数器
            - 工作机制
            - 单位：方法
            - 默认使用相对频率<br />关闭热度衰减使用绝对次数
            - 阀值
            - 设置阀值`-XX:CompileThreshold`
            - 执行逻辑
            - 热度衰减
              - 半衰期：`-XX:CounterHalfLifeTime`
              - 关闭：`-XX:-UseCounterDecay`
          - 回边计数器
            - 工作机制
            - 栈上替换(OSR)
      - Java 内置 JIT 编译器
        - C1
          - client 模式下运行
          - 简单和可靠的优化，耗时短
          - 优化策略 <!-- fold -->
            - 方法内联：将引用的函数代码编译到引用点处，<br />这样可以减少栈帧的生成，减少参数传递以及跳转过程
            - 去虚拟化：对唯一的实现类进行内联
            - 沉余消除：在运行期间把一些不会执行的代码折叠掉
        - C2
          - server 模式下运行
          - 耗时较长的优化，以及激进优化
          - 优化策略 <!-- fold -->
            - 标量替换：用标量值代替聚合对象的属性值
            - 栈上分配：对于未逃逸的对象分配对象在栈而不是堆
            - 同步消除：清除同步操作，通常指 synchronized
      - java 中的实际优化策略：[分层编译(1.7 引入,1.8 默认开启)](http://zhongmingmao.me/2019/01/02/jvm-advanced-jit/)
  - 其他编译器：
    - Graa1
    - AOT

- String 与 StringTable(中间插曲)
  - 创建 String
    - 字面量
      - 一个对象
      - 返回的索引指向
    - new
      - 两个对象
      - 返回的索引指向
    - **StringBuilder 和 StringBuffer 的 toString**
    - (本质同上)new String("a")+new String("b")
  - String 的不可变性
  - String 底层存储结构
    - jdk8:final char[]
    - jdk9:final byte[]
  - String Table 相关
    - 特点
    - 长度
      - 默认长度
        - jdk6
        - jdk7
        - jdk8
      - 长度设置
    - 内存分配变迁
      - jdk6
      - jdk7
      - jdk8
  - **inter()的使用**
    - 1.7 之前
    - 1.7 及之后
  - StringTable 垃圾回收:<br />GC 时也会对 StringTable 进行垃圾回收
  - G1 中的 String 去重操作(了解)
  - **面试题**

- 垃圾回收器
  - 整体概述
  - 垃圾回收相关算法
    - 标记阶段
      - 说明
      - 算法
        - 引用计数算法
        - 可达性分析算法
    - 清除阶段
      - 说明
      - 算法
        - 分代收集算法
          - 标记一清除算法（Mark-Sweep)
          - 复制算法（Copying)
          - 标记-压缩算法（Mark-Compact )
        - 增量收集算法
        - 分区算法(G1)
    - 对象的 finalization 机制
    - 对象的三种状态
  - 垃圾回收相关概念
    - `System.gc()`
    - 内存溢出
    - 内存泄漏
    - Stop the world
    - 垃圾回收的串行，并发与并行
    - 安全点与安全区域
    - **java 中的引用**
      - 强引用
      - 软引用
      - 弱引用
      - 虚引用
      - `-------------------`
      - 终结器引用
  - 垃圾回收器
    - 分类
      - 按线程数
        - 串行
        - 并行
      - 按工作模式
        - 并发
        - 独占
      - 按碎片处理方式分
        - 压缩整理
        - 非压缩整理，空闲列表
      - 按工作的区间分
        - 新生代
        - 老年代
        - 混合
        - Full
    - 性能指标
      - 主要的三个：
        - **吞吐量**
        - 垃圾收集开销
        - **暂停时间**
        - 收集频率
        - **内存占用**
        - 快速
      - 吞吐量，暂停时间的相互限制
    - 为什么要有多种垃圾回收器
    - 7 中经典垃圾回收器
      - 时间顺序：
        - serial<br />parNew(是 serial 多线程版本)
        - Parallel<br />CMS
        - G1
      - **组合关系**
      - **详细说明** <br />
        - Serial
          - 说明
            - 分类
            - 作用位置
            - 使用算法
            - 吞吐/响应 优先
            - 适用场景
          - 工作流程
          - 触发:见中层的堆
        - Serial Old
          - 说明
            - 分类
            - 作用位置
            - 使用算法
            - 吞吐/响应 优先
            - 适用场景
          - 工作流程
          - 触发:见中层的堆
        - ParNew
          - 说明
            - 分类
            - 作用位置
            - 使用算法
            - 吞吐/响应 优先
            - 适用场景
          - 工作流程
          - 触发:见中层的堆
          - 背景：Serial 多线程版本
          - `单核/多核` `serial/parnew`
        - Parallel Scavenge
          - 说明
            - 分类
            - 作用位置
            - 使用算法
            - 吞吐/响应 优先
            - 适用场景
          - 工作流程
          - 触发:见中层的堆
          - 与 parNew 区别
        - Parallel Old
          - 说明
            - 分类
            - 作用位置
            - 使用算法
            - 吞吐/响应 优先
            - 适用场景
          - 工作流程
          - 触发:见中层的堆
        - CMS
          - 概述
            - 分类
            - 作用位置
            - 使用算法
            - 吞吐/响应 优先
            - 适用场景
          - 触发(特殊)
            - **达到阀值，而不是满了**
          - 垃圾回收机制
            - 回收流程 <!-- fold -->
              - 初始标记(STW):仅仅只是标记出 GC Roots 能直接关联到的对象。
              - 并发标记(不 STW):从 GC Roots 的直接关联对象开始遍历整个对象图的过程
              - 重新标记(STW):为了修正并发标记期间，因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录
              - 并发清除(不 STW):此阶段清理删除掉标记阶段判断的已经死亡的对象，释放内存空间
            - 后备方案
              - 情况：比如大对象导致空间不足
              - 错误：Concurrent Mode Failure
              - 解决：serial old fullGC
          - 搭配问题 <!-- fold -->
            - 无法与 Parallel Scavenge 搭配
          - 废弃原因 <!-- fold -->
            - 内存碎片，大对象无法分配
            - 后备 serial old 效率过低
            - 并发导致程序变慢
            - 浮动垃圾无法处理
        - G1
          - 概述
            - 分类
            - 作用位置
            - 吞吐/响应 优先
            - 使用算法 <!-- fold -->
              - 局部复制
              - 整体标记压缩
            - 适用场景
          - 出现原因
          - 缺点 <!-- fold -->
            - 额外内存占用:Remember Set
          - 分区算法，分代收集
            - region 组成
            - region 角色变换
            - Humongous region
            - region 分段(默认 8)
          - 参数调优
          - 垃圾回收机制
            - 预备知识
              - dirty card queue <!-- fold -->
                - 作用：用来更新 Rset，避免线程同步开销
              - Remember Set <!-- fold -->
                - 作用：准确的反映老年代对所在的内存分段中对象的引用
            - 回收种类
              - minorGC
                - 触发
                - 回收区域
                - 详细过程 <!-- fold -->
                  - 第一阶段，扫描根。
                  - 第二阶段，更新 RSet。
                  - 第三阶段，处理 RSet。
                  - 第四阶段，复制对象。
                  - 第五阶段，处理引用。
              - 老年代并发标记过程
                - 触发
                - 回收区域
                - 详细过程 <!-- fold -->
                  - 1.初始标记阶段
                  - 2.根区域扫描（Root Region Scanning)
                  - 3.并发标记（Concurrent Marking)
                  - 4.再次标记（Remark)
                  - 5.独占清理（cleanup,STW)
                  - 6.并发清理阶段
                - 回收结果 <!-- fold -->
                  - 百分百为垃圾的内存分段被回收
                  - 部分为垃圾的内存分段被计算
              - mixedGC
                - 触发
                - 回收区域
                - **可预测时间模型(重要)**
              - full GC(失败保护)
                - 触发
                - 性能 <!-- fold -->
                  - 单线程
                  - 独占式
                  - 高强度
                  - 总之很差
      - 垃圾回收器的选择

  - 问题 <!-- fold -->
    - 垃圾收集的算法有哪些？如何判断一个对象是否可以回收？
    - 垃圾收集器工作的基本流程。
    - JVMGC 算法有哪些，目前的 JDK 版本采用什么回收算法
    - G1 回收器讲下回收过程
    - GC 是什么？为什么要有 GC?
    - GC 的两种判定方法？CMS 收集器与 G1 收集器的特点。
    - 说一下 GC 算法，分代回收说下
    - 垃圾收集策略和算法
    - jvm GC 原理，JVM 怎么回收内存
    - CMS 特点，垃圾回收算法有哪些？各自的优缺点，他们共同的缺点是什么？
    - java 的垃圾回收器都有哪些，说下 g1 的应用场景，平时你是如何搭配使用垃圾回收器的
    - 你知道哪几种垃圾收集器，各自的优缺点，重点讲下 cms 和 G1,包括原理，流程，优缺点。
    - 垃圾回收算法的实现原理。
    - 讲一讲垃圾回收算法。
    - 什么情况下触发垃圾回收？
    - 如何选择合适的垃圾收集算法？
    - JVM 有哪三种垃圾回收器？
    - 常见的垃圾回收器算法有哪些，各有什么优劣？
    - system.gc()和 runtime.gc()会做什么事情？
    - Java GC 机制？GC Roots 有哪些？
    - Java 对象的回收方式，回收算法。
    - CMS 和 G1 了解么，CMS 解决什么问题，说一下回收的过程。
    - CMS 回收停顿了几次，为什么要停顿两次。

### 字节码与类加载子系统 <!-- fold -->

### jvm 调优 <!-- fold -->

## 常用框架 <!-- fold -->

### Spring<!-- fold -->

#### 基础

- 什么是 Spring 框架
- Spring 框架的重要模块
- 为什么要使用 Spring 框架
- Spring 框架的两大核心
  - IOC
  - AOP
- BeanFactory 和 ApplicationContextSpring

#### IOC

- 概念
- [IOC 容器初始化过程](https://javadoop.com/post/spring-ioc)

- 基于 xml 的 IOC

  - 存入容器方式
    - Bean 标签
      - 默认构造函数
      - 工厂类
      - 静态工厂方法
  - 依赖注入方式
    - 注入 Bean 类型
      - 使用构造函数(一般不用)
      - 使用 set 方法(常用)
      - 使用注解
    - 注入基本类型和集合类型
      - 通过 xml
  - 生命周期，类型等都通过 bean 标签属性

- 基于注解的 IOC

  - 存入容器的注解
    - @Component
    - @Controller
    - @Service
    - @Repository
  - 依赖注入的注解
    - 注入 Bean 类型
      - @AutoWired
      - @Qualifier
      - @Reasource
    - 注入基本类型
      - @Value
  - 生命周期相关注解
    - @PostConstruct
    - @PreDestroy
  - 改变作用范围<br />(和存入容器的注解搭配使用<br />上面四四个或@bean)
    - @Scope

- 注意：对于无法添加注解<br />如导入的第三方依赖<br />可以通过 xml 将其存入或者向其注入<br />或者使用下面的`@Bean`

#### 摆脱 xml 的注解

- @Configuration
- @ComponentScan
  - 对应`component:scan`
- @Bean
  - 对应 xml 中的工厂方法
  - 通常配置在 Config 类<br />返回指定对象的上方
- @Import
- @PropertySource

#### AOP

- 说明
- 相关概念
  - Joinpoint
  - Pointcut
  - Advice
    - 前置通知
    - 后置通知
    - 异常通知
    - 最终通知
    - 环绕通知
  - Introduction
  - Target
  - Weaving
  - Proxy
  - Aspect

- 实现原理
  - 静态代理 AspectJ
  - 动态代理
    - JDK
    - GBLib
    - 两者对比
  - 动态和静态对比

- 使用
  - xml
    - 配置步骤
    - 切入点表达式
  - 注解
    - 开启支持
      - xml：`aop:aspectj-autoproxy`
      - 注解：`@EnableAspectJAutoProxy`
    - 配置步骤
    - 切入点表达式 <!-- fold -->
      - execution：用于匹配方法执行的连接点；
      - within：用于匹配指定类型内的方法执行；
      - this：用于匹配当前 AOP 代理对象类型的执行方法；注意是 AOP 代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
      - target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
      - args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
      - @within：用于匹配所以持有指定注解类型内的方法；
      - @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
      - @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；
      - @annotation：用于匹配当前执行方法持有指定注解的方法；
      - bean：Spring AOP 扩展的，AspectJ 没有对于指示符，用于匹配特定名称的 Bean 对象的执行方法；
      - reference pointcut：表示引用其他命名切入点，只有@ApectJ 风格支持，Schema 风格不支持。
    - 参数传递

#### Bean

- 创建 Bean 的方式
  - 默认构造函数
  - 工厂类
  - 静态工厂

- Scope 作用范围
  - singleton：单例的（默认值）
  - prototype：多例的
  - request：作用于 web 应用的请求范围
  - session：作用于 web 应用的会话范围
  - global-session：Spring5 中已经没有了

- 生命周期

- 线程安全问题

#### 事务

- Spring 对事务的支持
  - 取决于数据库

- Spring 事务相关 API
  - PlatformTransactionManager
  - TransactionDefinition
  - TransactionStatus

- 事务管理方式
  - 编程式（基本不用，仅仅为了了解原理）
  - 声明式（基于 AOP）
    - 基于 xml
      - 配置流程
    - xml 开启支持+注解配置
      - 配置流程
    - 纯注解配置
      - 配置流程

- **事务属性**<br />tx:advice 标签内部可以配置<br />也可以使用注解配置
  - propagation(传播行为):(7)
    - 支持当前事务的情况(3)
      - required(默认)
      - supports
      - mandatory
    - 不支持当前事务的情况(3)
      - requires_new
      - not_supported
      - never
    - 其他情况(1)
      - nested
  - isolation(隔离级别)(5)
    - default
    - read_uncommitted
    - read_committed
    - repeatable_read
    - serializable
  - timeout(超时属性)
    - 用于指定事务的超时时间
    - 默认值是-1
  - read-only(只读属性)
    - 用于指定事务是否只读
    - 默认 false
  - 回滚规则
    - rollback-for
    - no-rollback-for

#### 设计模式

- 工厂设计模式
  - BeanFactory
  - ApplicationContext
- 单例设计模式
  - bean 默认作用域
  - 实现：ConcurrentHashMap 实现单例注册表的特殊方式
- 代理设计模式
  - 代理模式在 AOP 中的应用
  - 代理模式的实现
    - 静态代理 AspectJ
    - 动态代理
      - JDK
      - GBLib
      - 两者对比
    - 动态和静态对比
- 模板方法
  - jdbcTemplate
  - hibernateTemplate
  - RedisTemplate
- 观察者模式
  - Spring 事件驱动模型中的三种角色
    - 事件角色
    - 事件监听者角色
    - 事件发布者角色
  - Spring 的事件流程总结
- 适配器模式
  - spring AOP 中的适配器模式
  - spring MVC 中的适配器模式
- 装饰者模式

### SpringMVC <!-- fold -->

### SpringBoot <!-- fold -->

### Mybatis <!-- fold -->

- 基本使用
  - xml
    - 常见标签
      - select
      - insert
        - selectKey(获取插入数据的主键)
      - update
      - delete
    - 配置标签
      - typeAliases
        - package<br />(指定**实体类**包，用于起别名)
      - mapper
        - package<br />(指定**dao 类**包，避免往 SqlConfig 文件中的<br />mappers 中添加 mapper 标签)
    - 其他标签
      - resultMap
      - parameterMap
      - include
      - sql
      - selectKey
    - 动态 sql 标签
      - trim
      - where
      - set
      - foreach
      - if
      - choose
      - when
      - otherwise
      - bind
      - include
        - 搭配`sql`标签
  - 注解
    - 常见标签
      - @Select
      - @Insert
      - @Update
      - @Delete
    - 其他标签
      - @Results
        - 定义并使用
      - @ResultMap
        - 使用已定义的

- 映射关系
  - `<parameterMap>`---ParameterMap
  - `<resultMap>`---ResultMap
    - 子元素:ResultMapping
  - `<select>/<update>/<insert>/<delet>`---MappedStatement

- **执行流程**
- dao 接口到 MappedStatement 的映射
- 动态 sql
  - 原理
  - 应用:dao 方法重载
- 多表查询
  - 一对一(多对一)
  - 一对多
  - 多对多

- 加载
  - 立即加载
    - 使用场景
    - 说明
  - 延迟加载
    - 使用场景
    - 说明
    - 配置
    - 原理:拦截器

- 缓存
  - 说明
  - 种类
    - 一级缓存
      - 概念
      - 触发
    - 二级缓存
      - 概念
      - 配置开启
        - xml
          - config.xml
          - dao.xml
          - `<select>`
        - 注解
          - SqlMapConfig.xml
          - @CacheNamespace(blocking=true)
      - 触发

- 分页
  - RowBounds <!-- fold -->
    - 内存分页
    - 返回结果集的子集
  - 分页插件 <!-- fold -->
    - 拦截 sql,物理分页
    - `select _ from student`
    - 拦截 sql 后重写为：<br /> `select t._ from （select \* from student）t limit 0，10`

- 插件
  - 分页插件原理
  - 自定义插件步骤

- 注意 <!-- fold -->
  - `jdbcType=date`会导致时间插入时丢失时分秒

### Netty <!-- fold -->

### quartz <!-- fold -->

## 测试框架 <!-- fold -->

### Junit

### Mockito

## JNI <!-- fold -->

## 工具库 <!-- fold -->

### excel 处理

#### poi

#### easyExcel

### hutool

### lombok

### jsoup

### log4j

### junit

### [深度学习-djl](https://github.com/deepjavalibrary/djl)

### 函数式编程-vavr

# 数据库

## 关系型数据库 <!-- fold -->

### Mysql<!-- fold -->

#### [三大范式](https://blog.csdn.net/weixin_43649997/article/details/105835007)

- 第一范式
- 第二范式
- 第三范式

#### 事务

- 四大特征
  - A 原子性
    - 概述
    - 原理
      - undo log
      - 回滚
  - C 持久性
    - 概述
    - 原理:其他三个
  - I 隔离性
    - 概述
    - 原理
      - MVCC
      - 锁
  - D 一致性
    - 概述
    - 原理(双写缓冲)
      - buffer pool
      - redo log

- 并发事务的问题
  - 脏读
  - 不可重复读（虚读）
  - 幻读

- 隔离级别
  - read uncommitted:
    - 说明
    - 产生问题
  - read committed:
    - 说明
    - 产生问题
  - repeatalbe read:
    - 说明
    - 产生问题
  - serializable
    - 说明
    - 可以解决所有问题

#### 索引

- 概述
  - 索引是什么
  - 什么时候需要建索引 <!-- fold -->
    - 不修改
    - 经常作为条件查询，排序，分组
    - 外键
  - 什么时候不要建索引 <!-- fold -->
    - 用不到
    - 记录少
    - 频繁更新
    - 经常增删
    - 数据重复且分布平均的表字段
  - 什么时候创建复合索引 <!-- fold -->
    - 用得到
    - 高并发
    - 一个索引当几个用
  - 索引优点
    - 查询速度
    - 优化使用(MRR,ICP)
    - 创建维护消耗
    - 磁盘占用

- 索引分类
  - 按属性/作用分类
    - 普通索引
    - 唯一索引
    - 主键索引
    - 复合索引
    - 全文索引
  - 按引擎分类
    - B+
    - Hash
    - 全文
    - R-Tree
  - 按是否聚簇分类
    - 聚簇索引
    - 二级索引

- 索引语法
  - 创建
  - 删除
  - 查看

- 索引添加与 Cardinality
  - fast index creation
  - Cardinality

#### 存储引擎

- MyISAM
  - 索引树结构
  - 聚簇索引的选择
  - 二级索引检索过程
- Innodb
  - 索引树结构
  - 二级索引检索过程
- 常见问题
  - M 和 I 两者区别 <!-- fold -->
    - 事务
    - 主键
    - 外键
    - 锁
    - 索引
    - count(\*)
  - 为什么使用 B+树，不使用：
    - B 树
    - 红黑树

#### 调优

- explain 使用与分析
  - **id**
  - select_type
  - table
  - **type** <!-- fold -->
    - system
    - const
    - eq_ref
    - ref
    - range
    - index
    - ALL
  - possible_keys
  - **key**
  - **key_len**
  - **ref**
  - **rows**
  - **extra**
    - using filesort(危险)
    - using temporary(非常危险)
    - using index
    - using where
    - using join buffer
    - impossible where

- 最左前缀原则

- 索引失效原因
  - 不遵循最左前缀
  - 索引列上的任何操作
    - 计算
    - 函数
    - （自动 or 手动）类型转换
  - 范围查询
    - `between and;in`
    - `or`
    - `%...`(覆盖索引有奇效)
    - `is null;is not null`
    - `!=;<>`

- 小标驱动大表
  - 左表驱动右表
    - left join
    - exist
  - 右表驱动左表
    - right join
    - in

- 排序优化
  - mysql 排序方式
    - index sort
    - file sort
      - 单路
        - io 次数:2
        - 第二次随机 io
      - 双路
        - io 次数:1
        - 一次顺序 io
        - buffer 不够：多次 io
      - **参数优化策略**
  - order by 优化
    - 遵循最左前缀进行排序
      - order
      - where(const)+order
    - 不让索引失效
    - 不能一升一降
  - group by 优化
    - 实质是先排序后进行分组
    - 能写在 where 限定的条件就不要去 having 限定了

- 慢查询日志

#### 锁

- [Mysql 锁底层](https://i6448038.github.io/2019/02/23/mysql-lock/)
  - latch
  - lock

- 锁的分类
  - [乐观锁](https://www.cnblogs.com/laoyeye/p/8097684.html)
    - 时间戳
    - 版本号
    - 条件限制
  - 悲观锁
    - Myisam 表锁
      - 读锁
      - 写锁
    - Innodb 锁
      - 意向表锁
        - 意向共享锁
        - 意向排他锁
      - 普通行锁
        - 共享锁
        - 排它锁
      - 锁算法
        - 记录锁
        - 间隙锁
        - 临键锁
      - 插入意向锁
      - 自增锁

- **[加锁流程](https://blog.csdn.net/geekjoker/article/details/79444076)**
  - 需要了解
    - 聚集索引和二级索引
    - innodb 二级索引查询流程
    - innodb 的锁算法
  - 不同情景 <br />(**下列条件相互组合**)
    - 查询方式
      - 等值查询
      - 范围查询
    - 隔离级别
      - RC
      - RR
      - serializable
    - 操作
      - `select`(只用在 serializable 下考虑)
      - `delete`/`select...for update`
    - 使用索引
      - 主键索引<br />(注意临键锁会不会加在主键)
      - 唯一索引
      - 非唯一索引<br />(注意临键锁会不会加在主键)
      - 无索引

#### MVCC

- 快照读与当前读
- 原理
  - 版本链
    - 隐藏字段
    - undo log
  - readview

#### 主从复制

- 主从复制
  - 原理
- 读写分离

#### 底层优化

- MRR
- ICP

#### 大表调优

### oracle<!-- fold -->

## nosql <!-- fold -->

### Redis<!-- fold -->

- 基本数据类型
  - String
    - 说明
    - 应用场景
    - 数据结构:SDS

  - Hash
    - 说明
    - 应用场景
    - 数据结构 <!-- fold -->
      - 小于(64,512):ziplist
      - 否则：hashtable

  - List
    - 说明
    - 应用场景
    - 数据结构

  - Set
    - 说明
    - 应用场景
    - 数据结构 <!-- fold -->
      - 默认使用 IntSet
      - 一定情况下 IntSet 转换为 HashTable

  - ZSet
    - 说明
    - 应用场景
    - 数据结构 <!-- fold -->
      - 小于(64,512):ziplist
      - 否则：hashtable+skiplist

  - HyperLogLog
    - 说明
    - 应用场景
    - 数据结构
  - BitMap

- 高级算法
  - scan
  - GeoHash

- 过期淘汰
  - 过期删除策略
    - 定时删除
    - 惰性删除
    - 定期删除
  - 内存淘汰策略
    - no-eviction
    - allkeys
      - random
      - lru
      - lfu
    - volatile
      - ttl
      - random
      - lru
      - lfu

- 持久化
  - RDB
    - 持久化原理/过程
    - 配置
    - 触发
      - 自动
        - 默认的三个
        - 原理
      - 手动(4 种)
    - check 检查
    - 优缺点
  - AOF
    - 持久化原理/过程
    - 配置
    - 触发
      - 开启后自动
      - 手动`bgrewriteaof`
    - check 检查
    - AOF 重写
      - 原理
        - 读取服务器现有 KV
        - fork 子**进程**
      - 触发
      - 重写数据不一致问题
      - 重写数据不一致问题解决
    - 优缺点
    - (总结)AOF 的两个缓冲区
  - AOF+RDB 混合

- 事务
  - 基本命令
  - 乐观锁
  - 两种异常情况：
    - 全都不执行
    - 执行可以执行的

- 并发问题解决
  - 乐观锁
  - 分布式锁
  - 消息队列
  - 时间戳

- 消息发布订阅

- 集群
  - 主从复制
    - 说明
    - 配置
    - master 宕机
  - 哨兵模式
    - 说明
    - 配置
    - master 宕机
  - 分片 cluster
    - 说明
      - 两个端口
      - 集群总线
      - 数据存取 <!-- fold -->
        - CRC16
        - hash 槽
        - 重定向
      - 主从复制模型
    - 配置
    - master 宕机

- 缓存失效/更新问题
  - 缓存雪崩
    - 说明
    - 解决方案
  - 缓存穿透
    - 说明
    - 解决方案
  - 缓存击穿
    - 说明
    - 解决方案
  - 双写一致
    - 分析问题
      - 先更新数据库，<br />再删除缓存
        - 正常
        - 失败
        - 并发
      - 先删除缓存，<br />再更新数据库
        - 正常
        - 失败
        - 并发
        - `关联：缓存读写策略中的旁路缓存`
    - 解决方案

- 线程模型
  - 为什么使用单线程 <!-- fold -->
    - 可维护性，方便开发和调试
    - 单线程也能处理并发请求(多路复用)
    - 性能瓶颈不是 cpu，而是内存和网络
  - 为什么能单线程处理那么多客户端 <!-- fold -->
    - 纯内存
    - 单线程，无锁
    - 多路复用
    - 高效底层数据结构优化
  - 为什么 6.0 前不使用多线程
  - Redis 6.0 为什么使用多线程 <!-- fold -->
    - 提高网络 io 读写性能
    - `默认禁用`

- redis 应用
  - redis 分布式锁
    - 高效分布式锁条件
      - 互斥
      - 防止死锁
      - 性能
      - 可重入
    - 两个问题及解决思路
      - setnx,expire 原子性
      - 锁提前失效
    - redisson 锁
      - 流程/原理
      - 看门狗机制
      - lua 脚本
      - 实现可重入:Hash
      - 缺陷
  - 缓存
    - 旁路缓存模式 <!-- fold -->
      - 关联：双写一致问题
    - 读写穿透
    - 异步缓存写入
  - 布隆过滤器
  - 布谷鸟过滤器

### MongoDB<!-- fold -->

# 分布式系统

## 分布式理论 <!-- fold -->

### 基础理论

#### 分布式

#### 分布式和集群

#### 微服务

### 通信设计

#### RPC

#### REST

### 分布式缓存算法

#### 传统 hash 算法

#### 一致性 hash 算法

#### 集群算法

#### 分块 hash 算法

### 分布式事务算法

#### 一致性问题

#### 2PC

#### 3PC

#### paxos

#### raft

## 高可用 <!-- fold -->

## 分布式锁实现 <!-- fold -->

### 数据库

### redis

### zookeeper

### etcd

## 分布式协调框架 <!-- fold -->

### zookeeper

## RPC 框架 <!-- fold -->

### SpringCloud

### Dubbo<!-- fold -->

# 消息队列

## Kafka<!-- fold -->

## RocketMQ<!-- fold -->

# 大数据

## 数据采集和传输层 <!-- fold -->

### Flume

### Sqoop

### Kafka

## 数据存储层 <!-- fold -->

### HDFS

### Hive

### HBase

## 数据分析层 <!-- fold -->

### MapReduce

### Tez

### Storm

### Spark

### Flink

### Mahout

## OLAP 引擎 <!-- fold -->

### Druid(?)

### Kylin(?)

### Impala(?)

### Presto(?)

## 资源管理 <!-- fold -->

### Yarn

### Mesos

### Kubernetes

## 其他 <!-- fold -->

# 人工智能

## 机器学习 <!-- fold -->

### sklearn

#### 数据处理

#### 数据预处理

#### 特征工程

- 特征选择
- 特征降维

#### 转换器与估计器

### 基本算法

- 监督学习
  - 分类
  - 回归
  - 标注
- 无监督学习

### 模型评估

### XGBoost

## 深度学习 <!-- fold -->

## opencv

# 前端

## 基础<!-- fold -->

### html

### emmet

### css

### javascript

### 函数库-Jquery

## 深入<!-- fold -->

### ES6

### Promise

### scss、less

## 服务端<!-- fold -->

### NodeJs

### npm、yarn

## TypeScript

## 前端开发的组件化-webpack

## 框架 <!-- fold -->

### 组件化-Vue

- vue 
- 状态管理器-vuex

### 组件化-React

- React
- 状态管理器-redux

### 其他

#### 简单，可扩展的状态管理-Mobx

#### 数据处理-Lodash

### UI

# 其他

## 鉴权 <!-- fold -->

## vimscript

## Lisp

## bat
