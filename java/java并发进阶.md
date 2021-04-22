# 1. 并发编程三大特性

# 2. JMM

## 2.1. 基础结构

## 2.2. JMM 与 Java 内存区域划分的区别与联系

## 2.3. 原子操作

## 2.4. 重排序

## 2.5. 编程规则

### 2.5.1. as-if-serial

### 2.5.2. happens-before

# 3. volatile

## 3.1. 保证内存可见性

- 说明
- 原理:<br /> MESI 缓存一致性协议<br /> cpu 总线嗅探机制<br /> lock
- 总线风暴

## 3.2. 禁止重排序

- 说明
- 原理:<br />内存屏障

## 3.3. 不保证原子性：

- 原因
- 解决
  - synchronized
  - Atomic(CAS)

# 4. 锁的变迁

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

# 5. synchronized

## 3 种使用方法

- 代码块
  - 自己指定对象锁
  - 底层原理：
    - monitorenter
    - monitorexit
    - 程序计数器
- 成员方法
  - this 对象锁
  - 底层原理：ACC_SYNCHRONIZED
- 静态方法
  - .class 类锁
  - 底层原理：ACC_SYNCHRONIZED，ACC_STATIC
- 注意：构造方法本身就属于线程安全的，不存在同步的构造方法一说。<br />不能加 synchronized

## 锁的升级(不可逆)

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



## synchronized 和 ReentrantLock 的区别

## synchronized 和 volatile 的区别

# 6. CAS

- 乐观锁与悲观锁
- 概念
- 底层原理
- CAS 的应用：UnSafe 和 Atomic
- 问题
  - ABA 问题
    - AtomicStampedReference
    - AtomicMarkableReference
  - 循环时间长开销大
    - 解决思路是让 JVM 支持处理器提供的 pause 指令
  - 只能保证一个共享变量的原子操作
    - 使用 JDK 1.5 开始就提供的 AtomicReference 类保证对象之间的原子性，<br />把多个变量放到一个对象里面进行 CAS 操作；
    - 使用锁。锁内的临界区代码可以保证只有当前线程能操作。

# 7. Atomic

- 组成
  - 基本类型
  - 数组类型
  - 引用类型
  - 对象的属性修改类型
- AtomicInteger
  - 示例
  - 基本原理

# 8. AQS

- 概念
- 底层
  - Unsafe(提供 CAS 操作)
  - LockSupport(提供 park/unpark 操作)
- 数据结构
- 资源共享模式/同步方式
- **模版设计模式**
- 源码分析
  - 获取资源流程
  - 释放资源流程
- 三个组件
  - Semaphore
  - CountDownLatch
  - CyclicBarrier
- 其他
  - AOS
  - AQLS

# 9. LockSupport

- park 底层使用的是`UNSAFE.park`
- 为什么 LockSupport 也是核心基础类? AQS 框架借助于两个类：Unsafe(提供 CAS 操作)和 LockSupport(提供 park/unpark 操作)
- 写出分别通过 wait/notify 和 LockSupport 的 park/unpark 实现同步?
- LockSupport.park()会释放锁资源吗? 那么 Condition.await()呢?
- Thread.sleep()、Object.wait()、Condition.await()、LockSupport.park()的区别? **重点**
- 如果在 wait()之前执行了 notify()会怎样? 如果在 park()之前执行了 unpark()会怎样?

# 10. 锁、通信工具类

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

# 11. 锁的种类

## 11.1. 锁的有无

- 乐观锁
- 悲观锁

## 11.2. synchronized 的锁

- 无锁
- 偏向锁
- 轻量锁
- 重量锁

## 11.3. 锁的性质分类

### 11.3.1. 可重入锁和非可重入锁

- 表现
- 原理：粒度（加锁范围）
- 实例
  - 可重入锁
  - 不可重入锁
  - 可以切换

### 11.3.2. 公平锁与非公平锁

- 表现
- 原理
- 实例
  - 公平锁
  - 非公平锁
  - 可以切换

### 11.3.3. 读写锁和排它锁

- 表现
- 原理
- 实例
  - 读写锁
  - 排它锁
  - 可以切换
- 是否可中断

# 12. 并集合容器

- [什么是同步容器和并发容器](https://juejin.cn/post/6844903954719965192)

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
      - [LinkedBlockingQueue 与 ConcurrentLinkedQueue 的区别](https://blog.csdn.net/lzxlfly/article/details/86710382)
  - ConcurrentMap
    - ConcurrentHashMap
    - ConcurrentNavigableMap
    - ConcurrentSkipListMap
  - CopyOnWrite
    - CopyOnWriteArrayList
    - CopyOnWriteArrayMap
    - CopyOnWriteArraySet

# 13. 线程池

## 13.1. 结构

- 任务(Runnable /Callable)
- 任务的执行(Executor)
- 异步计算的结果(Future)

## 13.2. 创建

### 13.2.1. ThreadPoolExecutor 构造方法参数的含义

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

### 13.2.2. Executors 默认实现

(底层使用 ThreadPoolExecutor)

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

### 13.2.3. 为什么不要用默认实现

(上面的默认实现有什么弊端)

## 13.3. 线程池工作流程

## 13.4. ThreadPool 状态转换

- RUNNING
- SHUTDOWN
- STOP
- TIDYING
- TERMINATED

## 13.5. ScheduledThreadPool:

- 继承了 ThreadPoolExecutor
- 主要用来在给定的延迟后运行任务，或者定期执行任务
- 实际项目中会使`用quartz`

## 13.6. 参数如何设置

## 13.7. 异常线程处理

[异常线程处理](https://mp.weixin.qq.com/s?__biz=Mzg3NjU3NTkwMQ==&mid=2247505057&idx=1&sn=621ebc409b589478e2e05388e079d8c0&source=41#wechat_redirect)

# 14. ThreadLocal(待做)

# 15. 常见区别

- sumbit() vs execute()
  - execute()方法不会返回结果
  - submit()会返回一个 FutureTask 对象，并可以获得结果
  - [异常处理](https://www.jianshu.com/p/29610984f1dd)
- Runnable vs Callable
- shutdown() vs shutdownNow()

# 16. 参考文献

![《深入浅出java多线程》](http://concurrent.redspider.group/RedSpider.html)
