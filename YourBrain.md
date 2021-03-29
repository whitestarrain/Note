
<!--请使用Markmap查看-->

# Java 基础

## 基础

- java泛型原理及使用
  - T ,T extends xxx, ？,？extends xxx 和 ？super xxx 的区别？
  - 为何不能通过直接通过`T[] arr=new T[10]`的方式来创建数组<br />如何正确创建泛型数组。
- equals方法使用注意
  - `常量.equals`
  - `Objects.equals` **推荐**
  - hashcode和equals
- 包装类
  - Integer当数值在-128 ~127时，会将创建的 Integer 对象缓存起来
  - 包装数据类型不能用 equals 来判断
- BigDecimal
  - 浮点数之间的等值判断，基本数据类型不能用==来比较（精度丢失）
  - 使用 BigDecimal 来定义浮点数的值，再进行浮点数的运算操作。
  - 推荐使用String作为参数传入BigDecimal构造方法
- 类型选取
  - 所有的 POJO 类属性必须使用包装数据类型。
  - RPC 方法的返回值和参数必须使用包装数据类型。
  - 所有的局部变量使用基本数据类型。
- Arrays.asList
  - Arrays.asList()将数组转换为集合后,底层其实还是数组
  - 传递的数组必须是对象数组，而不是基本类型。<br />当传入一个原生数据类型数组时，Arrays.asList() 的真正得到的参数就不是数组中的元素，而是数组对象本身
  - 使用集合的修改方法:`add()、remove()、clear()`会抛出异常。<br />Arrays.asList() 方法返回的并不是 java.util.ArrayList ，而是 java.util.Arrays 的一个内部类,
  - 如何正确的将数组转换为ArrayList?
    - `new ArrayList<>(Arrays.asList("a", "b", "c"))`(推荐)
    - 使用 Java8 的Stream(推荐)
    - 使用 Guava(推荐)
    - 使用 Apache Commons Collections
    - 使用 Java9 的 List.of()方法
- [fail-fast、fail-safe机制](https://juejin.cn/post/6879291161274482695)
- 枚举
  - 本质
  - 常见用法
- 静态内部类与非静态内部类
- 什么是Java多态
- Java异常体系
- 常用关键字
- 什么是反射
- 静态代理+JDK/CGLIB 动态代理
- 常见的 IO 模型有哪些？Java 中的 BIO、NIO、AIO 有啥区别?
- 基本概念
  - JRE与JDK
  - Java 7 和 Java SE 7
  - JDK1.8和Java8
- java8新特性

## 集合

- 为什么要使用集合
- HashMap
  - 1.7
    - 存储结构：数组+链表
    - 头插
    - [resize死循环](https://juejin.cn/post/6844903554264596487)
  - 1.8
    - 存储结构：数组+链表+红黑树。红黑树结构转换条件
    - 尾插：为什么改了
    - 如何解决的resize死循环
  - 线程不安全：put的时候导致的多线程数据不一致
  - 初始化大小
  - 扩容机制，LoadFactory
  - 容量为什么要2的幂
  - [遍历方法及性能](https://mp.weixin.qq.com/s/Zz6mofCtmYpABDL1ap04ow)
  - 有什么同步容器/并发容器
  - HashMap,HashTable,ConcurrentHashMap区别
    - 1.7
      - 三者数据结构
      - 同步机制/锁
      - `put(null)`
    - 1.8
      - 三者数据结构
      - 同步机制/锁
      - `put(null)`

- ConcurrentHashMap
  - [快速失败，安全失败](https://segmentfault.com/a/1190000016969753)
  - 1.7
    - 分段锁+数组+链表
    - **segment分段锁** -- HashBucket--HashEntry
      - 继承了ReentrantLock
      - 尝试获取锁存在并发，竞争，阻塞
    - get高效，volatile修饰，不需要加锁
  - 1.8
    - 数组+链表/红黑树
    - CAS+synchronized
      - CAS失败自旋保证成功
      - 再失败就synchronized
    - node


- ArrayList
  - 底层结构：数组
  - 初始大小与扩容机制
  - RandomAccess 接口
  - `System.arraycopy()` 和 `Arrays.copyOf()`方法
  - 有什么同步容器/并发容器
- LinkedList
  - 底层结构：双向链表
- Set
  - comparable 和 Comparator 的区别
  - 无序性和不可重复性的含义是什么
  - 有什么同步容器/并发容器

- 集合比较问题
  - List,Set,Map 三者的区别？
  - Arraylist 和 Vector 的区别?
  - Arraylist 与 LinkedList 区别?
  - ConcurrentHashMap 和 Hashtable 的区别
  - 比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同
  - HashMap和HashTable区别
  - HashMap与HashSet区别（HashSet底层基于HashMap）
  - HashMap和TreeMap区别

## 多线程

- 基础
  - [创建线程的方式](https://segmentfault.com/a/1190000037589073)
  - 调用`start()`和`run()`方法区别
  - 停止线程：interrupt
    - 原理
    - 正确的停止方式
    - 错误的停止方式
      - 被弃用的stop,suspend和resume方法
      - 用volatile设置标记位
  - 重要方法
    - Object(三个方法都要在synchronized内)
      - wait
      - notify
      - notifyAll
    - Thread
      - sleep
      - join
      - yield
  - yield和sleep区别
  - java线程状态的切换<br /> 延伸：操作系统进程状态的切换
  - 线程属性
  - 线程的未捕获异常处理
  - 线程组
    - 结构
      - 线程组是一个树状的结构，每个线程组下面可以有多个线程或者线程组
      - 默认将父线程（当前执行new Thread的线程）线程组设置为自己的线程组。
    - 线程组的优先级会限制线程的优先级
    - 作用
      - 统一控制线程的优先级
      - 检查线程的权限的作用。
      - 线程组统一异常处理
  - 常见问题
    - 并发与并行区别
    - 为什么要使用多线程
    - sleep() 方法和 wait() 方法区别和共同点

- 并发编程三大特性
  - 原子性
  - 可见性
  - 有序性

- JMM
  - 基础结构
  - JMM与Java内存区域划分的区别与联系
  - 原子操作
  - 重排序
    - 组成
      - 编译器优化重排
      - 指令并行重排
      - 内存系统重排
    - 编程规则 
      - as-if-serial
      - happens-before

- volatile
  - 保证内存可见性
    - 说明
    - 原理:<br /> MESI缓存一致性协议<br /> cpu总线嗅探机制<br /> lock
    - 总线风暴
  - 禁止重排序
    - 说明
    - 原理:<br />内存屏障
  - 不保证原子性：
    - 原因
    - 解决
      - synchronized
      - Atomic(CAS)

- 锁的变迁
  - jdk1.5之前:synchronized重量锁
  - jdk1.5
    - 原因
    - 变化:增加
      - Lock锁
      - 并发容器
      - 线程池
    - 依据原理
      - Atomic--UnSafe--CAS
      - AQS
      - LockSupport
      - volatile
  - jdk1.6
    - 变化:synchronized锁的升级
    - 原因
  - jdk1.8:增加StampedLock

- synchronized
  - 3种使用方法
    - 代码块
      - 自己指定对象锁
      - 底层原理：
        - monitorenter
        - monitorexit
        - 程序计数器
    - 成员方法
      - this对象锁
      - 底层原理：ACC_SYNCHRONIZED
    - 静态方法
      - .class类锁
      - 底层原理：ACC_SYNCHRONIZED，ACC_STATIC
    - 注意：构造方法本身就属于线程安全的，不存在同步的构造方法一说。<br />不能加synchronized
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
  - synchronized 和 ReentrantLock 的区别
  - synchronized 和 volatile 的区别

- CAS
  - 乐观锁与悲观锁
  - 概念
  - 底层原理
  - CAS的应用：UnSafe和Atomic
  - 问题
    - ABA问题
      - AtomicStampedReference
      - AtomicMarkableReference
    - 循环时间长开销大
      - 解决思路是让JVM支持处理器提供的pause指令
    - 只能保证一个共享变量的原子操作
      - 使用JDK 1.5开始就提供的AtomicReference类保证对象之间的原子性，<br />把多个变量放到一个对象里面进行CAS操作；
      - 使用锁。锁内的临界区代码可以保证只有当前线程能操作。

- AQS
  - 概念
  - 底层
    - Unsafe(提供CAS操作)
    - LockSupport(提供park/unpark操作)
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
  - park底层使用的是`UNSAFE.park`
  - 为什么LockSupport也是核心基础类? AQS框架借助于两个类：Unsafe(提供CAS操作)和LockSupport(提供park/unpark操作)
  - 写出分别通过wait/notify和LockSupport的park/unpark实现同步?
  - LockSupport.park()会释放锁资源吗? 那么Condition.await()呢? 
  - Thread.sleep()、Object.wait()、Condition.await()、LockSupport.park()的区别? **重点**
  - 如果在wait()之前执行了notify()会怎样? 如果在park()之前执行了unpark()会怎样? 

- 锁、通信工具类<br />和底层使用
  - AQS实现的锁(实现Lock接口)
    - ReentrantLock
      - 内部类Sync继承AQS
      - Condition底层使用LockSupport
    - ReentrantReadWriteLock
      - 读锁和写锁都有继承AQS的内部类Sync
  - AQS通信工具类
    - Semaphore
      - 内部有一个继承了AQS的同步器Sync
    - CountDownLatch
      - 内部有一个继承了AQS的同步器Sync
    - CyclicBarrier
      - 内部使用ReentrantLock
  - 非AQS的通信工具类
    - Exchanger
      - LockSupport
      - CAS(Atomic)
    - Phaser
      - 有使用LockSupport

- 锁的种类
  - 锁的有无
    - 乐观锁
    - 悲观锁
  - synchronized的锁
    - 无锁
    - 偏向锁
    - 轻量锁
    - 重量锁
  - 锁的性质分类
    - 可重入锁和非可重入锁
      - 表现
      - 原理：粒度（加锁范围）
      - 实例
        - 可重入锁
        - 不可重入锁
        - 可以切换
    - 公平锁与非公平锁
      - 表现
      - 原理
      - 实例
        - 公平锁
        - 非公平锁
        - 可以切换
    - 读写锁和排它锁
      - 表现
      - 原理
      - 实例
        - 读写锁
        - 排它锁
        - 可以切换
    - 是否可中断

- 并发集合容器
  - [什么是同步容器和并发容器](https://juejin.cn/post/6844903954719965192)
    ```
    markmap中会隐藏
    什么是同步容器？
    同步容器通过synchronized关键字修饰容器保证同一时刻内只有一个线程在使用容器，从而使得容器线程安全。
    synchronized的意思是同步，它体现在将多线程变为串行等待执行。
    （但注意一点，复合操作不能保证线程安全。举例：A线程第一步获取尾节点，
    第二步将尾结点的值加1，但在A线程执行完第一步的时候，B线程删除了尾节点，在A线程执行第二步的时候就会报空指针）

    什么是并发容器？
    并发容器指的是允许多线程同时使用容器，并且保证线程安全。
    而为了达到尽可能提高并发，Java并发工具包中采用了多种优化方式来提高并发容器的执行效率，
    核心的就是：锁、CAS（无锁）、COW（读写分离）、分段锁。
    ```
  - 同步容器
    - vector:在面对多线程下的复合操作的时候也是需要通过客户端加锁的方式保证原子性
    - HashTable
    - Collections下的各种 SynchronizedXXX
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
        - [LinkedBlockingQueue与ConcurrentLinkedQueue的区别](https://blog.csdn.net/lzxlfly/article/details/86710382)
    - ConcurrentMap
      - ConcurrentHashMap
      - ConcurrentNavigableMap
      - ConcurrentSkipListMap
    - CopyOnWrite
      - CopyOnWriteArrayList
      - CopyOnWriteArrayMap
      - CopyOnWriteArraySet
- 线程池
  - 结构
    - 任务(Runnable /Callable) 
    - 任务的执行(Executor)
    - 异步计算的结果(Future)
  - 创建
    - ThreadPoolExecutor构造方法
    - Executors默认实现
  - 参数的含义
    - int corePoolSize：该线程池中核心线程数最大值
    - int maximumPoolSize：该线程池中线程总数最大值 。
    - long keepAliveTime：非核心线程闲置超时时长。
    - TimeUnit unit：keepAliveTime的单位。
    - BlockingQueue workQueue：阻塞队列，维护着等待执行的Runnable任务对象。
    - ThreadFactory threadFactory：<br />创建线程的工厂 ，用于批量创建线程，统一在创建线程时设置一些参数，<br/>如是否守护线程、线程的优先级等。<br />如果不指定，会新建一个默认的线程工厂。
    - RejectedExecutionHandler handler 拒绝策略
      - AbortPolicy：默认拒绝处理策略，丢弃任务并抛出RejectedExecutionException异常。
      - DiscardPolicy：丢弃新来的任务，但是不抛出异常。
      - DiscardOldestPolicy：丢弃队列头部（最旧的）的任务，然后重新尝试执行程序（如果再次失败，重复此过程）。
      - CallerRunsPolicy：由调用线程处理该任务。
  - 线程池工作流程
  - ThreadPool状态转换
    - RUNNING
    - SHUTDOWN
    - STOP
    - TIDYING
    - TERMINATED
  - 默认实现
    - ThreadPoolExecutor
      - 参数设置
      - 执行过程
      - 弊端
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
  - ScheduledThreadPool:
    - 继承了ThreadPoolExecutor
    - 主要用来在给定的延迟后运行任务，或者定期执行任务
    - 实际项目中会使`用quartz`
  - **为什么不要用默认实现** <br/> (上面的四个默认实现有什么弊端)
  - 参数如何设置
  - [异常线程处理](https://mp.weixin.qq.com/s?__biz=Mzg3NjU3NTkwMQ==&mid=2247505057&idx=1&sn=621ebc409b589478e2e05388e079d8c0&source=41#wechat_redirect)
  - 常见区别
    - sumbit() vs execute()
      - execute()方法不会返回结果
      - submit()会返回一个 FutureTask 对象，并可以获得结果
      - [异常处理](https://www.jianshu.com/p/29610984f1dd)
    - Runnable vs Callable
    - shutdown() vs shutdownNow()

- **ThreadLocal(待做)**

## IO/NIO/AIO

# JVM

## 基础知识

- jvm发展
- 特点：
  - 一次编译，到处运行
  - 自动内存管理
  - 自动垃圾回收
- 架构模型
  - 基于栈式架构
  - 基于寄存器架构
- JVM生命周期
  - 启动
  - 执行
  - 退出

## 内存与垃圾回收

### 上层

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
    - 包含关系<br />(自定义类加载器包含其他java编写类加载器)
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
    - 自定义类加载器（User-Defined ClassLoader） java编写
      - 为何要自定义类加载器
  - 抽象类
    - 继承结构
    - sun.misc.Launcher 
    - 获取classloader的方式
  - **双亲委派机制**
    - **原理**
    - 自定义java.lang.String是否会加载
    - 优势
      - 避免类的重复加载
      - 保护程序安全，防止核心api被随意篡改
    - 例外案例
  - 沙箱安全机制
  - 其他
    - jvm 中判断两个class对象是否为同一个类3个判断条件
    - 类加载器作为类型信息，引用类型转换

### 中层

- **Java内存区域布局说明**
- 程序计数器
  - 是什么
    - 线程私有
    - 取值
    - 作用
    - 没有OOM
    - 生命周期
  - 问题
    - 为什么使用PC寄存字器记录当当前线程的执行地址
    - pc寄存器为什么被设定为线程私有
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
      - Java虚拟机栈（Java Virtual Machine Stack),早期也叫Java栈。
      - 每个线程在创建时都会创建一个虚拟机栈，<br />其内部保存一个个的栈帧 (Stack Frame),对应着一次次的Java方法调用。
      - 是线程私有的
    - 运行原理-栈帧弹出
    - 内部组成
      - 局部变量表
        - 作用
        - 生命周期
        - slot
          - 概念
          - 类型
          - 存储内容与顺序
          - 重用
        - 内存分配(**编译期间**完成分配)
        - 垃圾回收：局部变量表中的变量也是重要的垃圾回收根节点
      - 操作数栈
        - 编译时就已经确定深度`max_stacks`
        - 操作：出栈入栈
        - 具体流程示例
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
              - 概念
              - 四个调用指令
              - 方法重写本质
              - 虚方法表
        - 方法返回地址
          - 栈帧退出方式
          - 作用
        - 一些附加信息
  - 问题
    - 栈中可能出现的异常
    - i++和++i的区别。<br />从字节码，局部变量表，操作数栈层面解释
    - 举例栈溢出的情况？(StackOverflowError)
    - 调整栈大小，就能保证不出现溢出吗？
    - 分配的栈内存越大越好吗？
    - 垃圾回收是否会涉及到虚拟机栈？
    - 方法中定义的局部变量是否线程安全？
- 本地方法接口+库(非运行时数据区结构)
  - 定义:一个Native Method就是一个Java调用非Java代码的接口。
  - 特点:该方法的实现由非Java语言实现，比如 C。
  - 目的:
    - 与java环境外交互
    - 与操作系统交互
    - Sun's java
- 本地方法栈
  - 目的
  - 在Hotspot JVM中，直接将本地方法栈和虚拟机栈合二为一。

- 堆
  - 空间划分与比例
  - 堆大小设置参数`-Xms``-Xmx`
  - 对象分配
    - 一般过程
    - 特殊情况
  - 四种GC
    - Minor GC
      - 回收区域
      - 触发机制
      - 耗时代价
      - Survivor的被动收集
    - Major GC
      - 回收区域
      - 触发机制
      - 耗时代价
    - Mixed GC
      - 回收区域
      - 只有G1回收器有该GC
    - Full GC
      - 回收区域
      - 触发机制
      - 耗时代价
  - TLAB:快速分配策略。
    - 作用
    - 分配流程：
      - JVM确实是将TLAB作为内存分配的首选
      - 失败后，JVM就会尝试着通过使用加锁机制确保数据操作的原子性，<br />从而直接在Eden空间中分配内存。
    - 大小：默认整个Eden空间的1%<br /> `-XX:TLABWasteTargetPercent`设置
  - 逃逸分析( **十分不成熟，Hotspot也没用** )
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
  - 常量池和运行时常量池
  - 为何要移除永久代
  - 垃圾回收
    - 是否要回收
      - 《规范》提到过可以不要求虚拟机在方法区中实现垃圾收集
      - 回收条件苛刻，效果比较难令人满意。
    - 回收内容
      - 常量池中废弃的常量
        - 回收策略
        - 组成
      - 不再使用的类
        - 回收策略
- 问题
  - 说一下JVM内存模型吧，有哪些区？分别干什么的？
  - Java8的内存分代改进
  - JVM内存分哪几个区，每个区的作用是什么？
  - JVM内存分布/内存结构？栈和堆的区别？堆的结构？为什么两个survivor区？
  - Eden和Survior的比例分配
  - jvm内存分区，为什么要有新生代和老年代
  - Java的内存分区
  - 讲讲jvm运行时数据库区
  - 什么时候对象会进入老年代？
  - JVM内存为什么要分成新生代，老年代，持久代。<br />新生代中为什么要分为Eden和Survivor。
  - Jvm内存模型以及分区，需要详细到每个区放什么。
  - JVM的内存模型，Java8做了什么修改
  - JVM内存分哪几个区，每个区的作用是什么？
  - java内存分配
  - jvm的永久代中会发生垃圾回收吗？
  - jvm内存分区，为什么要有新生代和老年代？

- **对象的实例化内存布局和访问定位**
  - 实例化步骤
    - 创建对象的方式
    - 创建对象的步骤（六步）
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
    - 执行init方法进行初始化
      - 属性初始化方式
        - 1.默认初始化
        - 2.显式初始化
        - 3.代码块中初始化
        - 4.构造器中初始化
        - 5.对象.属性 初始化
      - init中包括2,3,4
  - 内部布局
    - 对象头(下面3项都是一字宽)
      - Mark Word--存储对象的hashCode或锁信息等
        - 不同锁状态内容不同
      - Class Metadata Address--存储到对象类型数据的指针
      - Array length--数组的长度（如果是数组）
    - 实例数据:
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
  - 问题
    - 对象在JVM中是怎么存储的？
    - 对象头信息里面有哪些东西？
    - java对象头里有什么
- 直接内存

### 下层

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
  - Java代码执行方式分类
    - 种类
      - 源代码编译成字节码文件，<br />然后在运行时通过解释器将字节码文件转为机器码执行
      - 直接编译成机器码
    - HotSpot执行方式
      - `-Xint`:完全采用解释器模式执行程序；
      - `-Xcomp`:完全采用即时编译器模式执行程序。如果即时编译出现问题，解释器会介入执行。
      - `-Xmixed`(默认):采用解释器+即时编译器的混合模式共同执行程序。
  - Java执行引擎组成
    - 解释器
      - 为什么java源码到机器语言中间要有个字节码
      - 工作机制
      - 种类
        - 字节码解释器
        - 模板解释器
    - 即时编译器
      - 工作机制
      - 热点代码
        - 概念
        - 热点探测
          - 基于计数器的热点探测
            - 工作机制
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
      - Java内置JIT编译器
        - C1
          - client模式下运行
          - 简单和可靠的优化，耗时短
          - 优化策略
            - 方法内联：将引用的函数代码编译到引用点处，<br />这样可以减少栈帧的生成，减少参数传递以及跳转过程
            - 去虚拟化：对唯一的实现类进行内联
            - 沉余消除：在运行期间把一些不会执行的代码折叠掉
        - C2
          - server模式下运行
          - 耗时较长的优化，以及激进优化
          - 优化策略
            - 标量替换：用标量值代替聚合对象的属性值
            - 栈上分配：对于未逃逸的对象分配对象在栈而不是堆
            - 同步消除：清除同步操作，通常指synchronized
      - java中的实际优化策略：[分层编译(1.7引入,1.8默认开启)](http://zhongmingmao.me/2019/01/02/jvm-advanced-jit/)
  - 其他编译器：
    - Graa1
    - AOT

- String与StringTable(中间插曲)
  - 创建String
    - 字面量
      - 一个对象
      - 返回的索引指向
    - new
      - 两个对象
      - 返回的索引指向
    - **StringBuilder和StringBuffer的toString**
    - (本质同上)new String("a")+new String("b")
  - String的不可变性
  - String底层存储结构
    - jdk8:final char[]
    - jdk9:final byte[]
  - String Table相关
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
    - 1.7之前
    - 1.7及之后
  - StringTable垃圾回收:<br />GC时也会对StringTable进行垃圾回收
  - G1中的String去重操作(了解)
  - **面试题**

- 垃圾回收器
  - 整体概述
  - 垃圾回收相关算法
    - 标记阶段
      - 说明
      - 算法
        - 引用计数算法
        - 可达性分析算法。
    - 清除阶段
      - 说明
      - 算法
        - 标记一清除算法（Mark-Sweep)
        - 复制算法（Copying)
        - 标记-压缩算法（Mark-Compact )
    - 对象的finalization机制
    - 对象的三种状态
    - 其他算法
      - 清除阶段：分代收集算法
      - 清除阶段：增量收集算法
      - 清除阶段：分区算法
  - 垃圾回收相关概念
    - `System.gc()`
    - 内存溢出
    - 内存泄漏
    - Stop the world
    - 垃圾回收的串行，并发与并行
    - 安全点与安全区域
    - **java中的引用**
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
    - 7中经典垃圾回收器
      - 时间顺序：
        - serial<br />parNew(是serial多线程版本)
        - Parallel<br />CMS
        - G1
      - **组合关系**
      - **详细说明** <br /> **(分类，作用位置，使用算法，特点，适用场景)**
        - Serial
        - Serial Old
        - ParNew 
        - Parallel Scavenge
          - 与parNew区别
        - Parallel Old
        - CMS
          - 工作流程
          - 优缺点
        - G1
      - 垃圾回收器的选择
  - 问题：
    - 垃圾收集的算法有哪些？如何判断一个对象是否可以回收？
    - 垃圾收集器工作的基本流程。
    - JVMGC算法有哪些，目前的JDK版本采用什么回收算法
    - G1回收器讲下回收过程
    - GC是什么？为什么要有GC?
    - GC的两种判定方法？CMS收集器与G1收集器的特点。
    - 说一下GC算法，分代回收说下
    - 垃圾收集策略和算法
    - jvm GC原理，JVM怎么回收内存
    - CMS特点，垃圾回收算法有哪些？各自的优缺点，他们共同的缺点是什么？
    - java的垃圾回收器都有哪些，说下g1的应用场景，平时你是如何搭配使用垃圾回收器的
    - 你知道哪几种垃圾收集器，各自的优缺点，重点讲下cms和G1,包括原理，流程，优缺点。
    - 垃圾回收算法的实现原理。
    - 讲一讲垃圾回收算法。
    - 什么情况下触发垃圾回收？
    - 如何选择合适的垃圾收集算法？
    - JVM有哪三种垃圾回收器？
    - 常见的垃圾回收器算法有哪些，各有什么优劣？
    - system.gc()和runtime.gc()会做什么事情？
    - Java GC机制？GC Roots有哪些？
    - Java对象的回收方式，回收算法。
    - CMS和G1了解么，CMS解决什么问题，说一下回收的过程。
    - CMS回收停顿了几次，为什么要停顿两次。

## 字节码与类加载子系统

## 优化

# 常用框架

## Spring

## SpringMVC

## SpringBoot

## Mybatis

## Netty

## quartz

# 数据库

## 关系型数据库

### Mysql

- 存储引擎
  - MyISAM
  - Innodb
  - 两者区别

- 锁机制
  - MyISAM
  - Innodb

- 范式
  - 第一范式
  - 第二范式
  - 第三范式
  - Boyce-Codd 范式（BCNF）

- 事务
  - 四大特征
    - 原子性：是不可分割的最小操作单位，要么同时成功，要么同时失败
    - 持久性：如果事务一旦提交或者回滚，数据库会持久更新数据。
    - 隔离性：多个事务之间相互独立。但一般会相互影响。
    - 一致性：表示事务操作前后数据总量不变。
  - 隔离等级
    - 并发事务的问题
      - 脏读
      - 丢失修改
      - 不可重复读（虚读）
      - 幻读
    - 隔离级别<br />与问题解决
      - read uncommitted:
        - 说明
        - 产生问题：脏读，不可重复读，幻读
      - read committed:
        - 说明
        - 产生问题：不可重复读，幻读
      - repeatalbe read:
        - 说明
        - 产生问题：幻读
      - serializable
        - 说明
        - 可以解决所有问题
 

### oracle

## nosql

### Redis

### MongoDB

# 基础

## 设计模式

- 创建型模式
  - [单例模式](https://www.runoob.com/design-pattern/singleton-pattern.html)
    - 1、懒汉式，线程不安全
    - 2、懒汉式，线程安全
    - 3、饿汉式
    - 4、双检锁/双重校验锁（DCL，即 double-checked locking）
    - 5、登记式/静态内部类
    - 6、枚举
- 结构型模式
  - 代理模式
    - 静态代理
    - 动态代理
      - JDK
      - CGLIB
- 行为
- J2EE

## 计算机网络

- 理论<br />(参考《计算机网络》谢希仁)
  - 概述
    - 基本概念
      - 结点
      - 链路
      - 主机
      - internet和Internet
      - ISP和IXP
    - 因特网组成
      - 边缘部分
        - client-server
        - p2p
      - 核心部分
        - 电路交换
        - 报文交换
        - 分组交换
    - 计算机网络类别
      - 广域网WAN
      - 区域网MAN
      - 城域网LAN
      - 个人区域网PAN
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
      - 往返时间RRT
      - 利用率
    - 计算机网络体系结构
      - OSI七层
      - TCP/IP四层
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
          - 循环冗余检验CRC
          - 帧检验序列FCS
      - ppp协议
        - 协议特点
        - 协议组成
        - PPP帧格式
        - 透明传输
          - 异步传输：字节填充
          - 同步传输：零比特填充
        - 确保边界：零比特填充
        - PPP工作状态
    - 广播信道
      - 适配器作用
      - 三个基本问题
        - 封装成帧:帧格式
        - 透明传输:不需要
        - 差错检测
          - 循环冗余检验CRC
          - 帧检验序列FCS
      - 协议
        - **CSMA/CD碰撞检测**
        - TDMA
        - FDMA
        - slotted ALOHA
        - ALOHA
      - MAC地址
        - 概念
        - 作用
        - 地址格式
        - MAC帧格式
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
  - 运输层
  - 应用层
- 问题
  - 计算机网络体系结构
  - 传输层：TCP和UDP
    - 什么是三次握手？
    - 什么是四次挥手？
    - TCP如何实现流量控制？
    - TCP的拥塞控制是怎么实现的？
    - TCP如何最大利用带宽？
    - TCP与UDP的区别
    - TCP如何保证传输的可靠性
    - 什么是TCP粘包？
  - 应用层：HTTP和HTTPS
    - HTTP和HTTPS有什么区别？
    - GET与POST的区别？
    - Session与Cookie的区别？
    - 从输入网址到获得页面的过程 (越详细越好)？
    - HTTP请求有哪些常见状态码？
    - 什么是RIP (距离矢量路由协议)?
  - 网络层协议IP
    - IP地址的分类？
    - 什么叫划分子网？
    - 什么是ARP协议？
    - 什么是NAT (网络地址转换)？
  - 从 URL 输入到页面展现到底发生什么

## 操作系统

## 算法

# zookeeper

# 分布式框架

## SpringCloud

## Dubbo

# 消息队列

## Kafka

## RocketMQ

# 分布式锁

## 数据库

## redis

## zookeeper

## etcd
