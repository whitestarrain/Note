# 基础

## fail-fast 机制

[博客](https://juejin.cn/post/6879291161274482695)

fail-fast机制是为了防止 **迭代** 过程中，集合 **结构** 发生变化。

- 在创建迭代器的时候，迭代器中都会有一个expectedModCount，用来记录集合更改次数。
- 如果集合此时进行了增删等结构变化，modCount就会加一，与expectedModCount不同，抛出异常
  > 稍微想一下就知道，正在迭代遍历，不能乱改。
- 如果调用迭代器的remove方法(内部就是调用ArrayList的方法)，modCount也会发生变化，但是此时会有一个`expectedModCount = modCount`，确保expectedModCount也更新。
  > 如果是迭代器自己去改的话，改就改吧。

## 枚举

[java guide]()

[菜鸟教程](https://www.runoob.com/java/java-enum.html)

[Java 枚举(enum) 详解7种常见的用法](https://blog.csdn.net/qq_27093465/article/details/52180865)

```java
enum Color 
{ 
    RED, GREEN, BLUE; 
} 
```

**等价于**

```java
class Color
{
     public static final Color RED = new Color();
     public static final Color BLUE = new Color();
     public static final Color GREEN = new Color();
}
```

## 静态内部类与非静态内部类

[博客](https://blog.csdn.net/yaomingyang/article/details/79363631)


- 非静态内部类不可以使用static，只有静态内部类才能够定义静态的成员变量与成员方法。

- 在外部类的成员的访问上，有比较大的限制。
  - 一般的非静态内部类，可以随意的访问外部类中的成员变量与成员方法。即使这些成员方法被修饰为private(私有的成员变量或者方法)。
  - 静态内部类只能引用外部类中的静态的成员（变量或方法），而不能够访问非静态的变量。

- 最大区别
  - 通常情况下，在一个类中创建成员内部类的时候，有一个强制性的规定，即内部类的实例一定要绑定在外部类的实例中。非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围类，
  - 但是静态内部类没有指向外部类的引用,也就是说
    - 它的创建是不需要依赖外围类的创建。
    - 它不能使用任何外围类的非static成员变量和方法。
  - example
    <details>
    <summary>代码</summary>

    ```java
    public class Singleton {

        //声明为 private 避免调用默认构造方法创建对象
        private Singleton() {
        }

      // 声明为 private 表明静态内部该类只能在该 Singleton 类中被访问
        private static class SingletonHolder {
            private static final Singleton INSTANCE = new Singleton();
        }

        public static Singleton getUniqueInstance() {
            return SingletonHolder.INSTANCE;
        }
    }
    ```
    当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。只有当调用 getUniqueInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。

    这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持。
    </details>


```
牢记两个差别：

一、如是否可以创建静态的成员方法与成员变量(静态内部类可以创建静态的成员，而非静态的内部类不可以)

二、对于访问外部类的成员的限制(静态内部类只可以访问外部类中的静态成员变量与成员方法，而非静态的内部类即可以访问所有的外部类成员方法与成员变量)。
```


## hashcode与equals

两个不同的对象可能会返回相同的hashcode

Java 程序设计中一个重要原则：

如果两个对象是相等的，它们的 equals() 方法应该要返回 true，它们的 hashCode() 需要返回相同的结果。

但有时候面试不会问得这么直接，他会问你：两个对象的 hashCdoe() 相同，它的 equals() 方法一定要返回 true，对吗？

那答案肯定不对。因为我们不能保证每个程序设计者，都会遵循编码约定。

有可能两个不同对象的hashCode()会返回相同的结果，但是由于他们是不同的对象，他们的 equals() 方法会返回false。

如果两个对象的 hashCode() 相同，将来就会在散列表中产生哈希冲突，但是它们不一定是相同的对象呀。

当产生哈希冲突时，我们还得通过 equals() 方法进一步判断两个对象是否相同，equals() 方法不一定会返回 true。

这也是为什么 Java 官方推荐我们在一个类中，最好同时重写 hashCode() 和 equals() 方法的原因。


## IO

![key_points-5](./image/key_points-5.png)

bio(jdk1.0) -> nio(jdk1.4) -> aio(jdk1.7)

- bio
  > ![key_points-2](./image/key_points-2.png) 
  > ![key_points-1](./image/key_points-1.png)
- nio
  > ![key_points-3](./image/key_points-3.png) 
- aio
  > ![key_points-4](./image/key_points-4.png) 

## 集合

### 常见集合区别

- Arraylist 和 Vector 的区别?
- Arraylist 与 LinkedList 区别?
- ConcurrentHashMap 和 Hashtable 的区别
- 比较 HashSet、LinkedHashSet 和 TreeSet 三者的异同
- HashMap和HashTable区别
- HashMap与HashSet区别（HashSet底层基于HashMap）
- HashMap和TreeMap区别

### 线程安全(不安全--安全)

- ArrayList--vector
- LinkedList--SynchronizedList
- HashMap--ConcurrentHashMap/HashTable/Collections.synchronizedMap
  <details>
  <summary style="color:red;">说明</summary>

  Hashtable 源码中是使用 synchronized 来保证线程安全的

  ConcurrentHashMap沿用了与它同时期的HashMap版本的思想，底层依然由“数组”+链表+红黑树的方式思想，但是为了做到并发，又增加了很多辅助的类，例如TreeBin，Traverser等对象内部类。
  且与hashtable不同的是：
  ConcurrentHashMap没有对整个hash表进行锁定，而是采用了分离锁（segment）的方式进行局部锁定。具体体现在，它在代码中维护着一个segment数组。

  SynchronizedMap是Collectionis的内部类。
  在 SynchronizedMap 类中使用了 synchronized 同步关键字来保证对 Map 的操作是线程安全的。

  ---

  ConcurrentHashMap明显优于Hashtable和SynchronizedMap 。
  </details>


### 扩容机制

- ArrayList扩容机制
- HashMap扩容机制
  - put的时候导致的多线程数据不一致
   <details>
   <summary style="color:red;">说明</summary>

    - 比如有两个线程A和B，
    - 首先A希望插入一个key-value对到HashMap中，首先计算记录所要落到的 hash桶的索引坐标，然后获取到该桶里面的链表头结点，
    - 此时线程A的时间片用完了，而此时线程B被调度得以执行，和线程A一样执行，只不过线程B成功将记录插到了桶里面，
    - 假设线程A插入的记录计算出来的 hash桶索引和线程B要插入的记录计算出来的 hash桶索引是一样的，
    - 那么当线程B成功插入之后，线程A再次被调度运行时，它依然持有过期的链表头但是它对此一无所知，以至于它认为它应该这样做，如此一来就覆盖了线程B插入的记录，这样线程B插入的记录就凭空消失了，造成了数据不一致的行为。

    不论1.8的尾插还是1.7的头插都会有这个问题。
   </details>

  - 1.7中resize死循环
    > [详解博客](https://juejin.cn/post/6844903554264596487)


### 其他

- HashMap拉链法，以及链表-->红黑树的条件
- HashMap 的长度为什么是 2 的幂次方
  - 高效
  - 充分碰撞
- HashMap 1.7多线程死循环问题



## Java内存模型（简称JMM）

> Java Memory Model

### 摘抄1

![key_points-8](./image/key_points-8.png)

- 所有的共享变量都存在主内存中。
- 每个线程都保存了一份该线程使用到的共享变量的副本。
- 如果线程A与线程B之间要通信的话，必须经历下面2个步骤：
  - 线程A将本地内存A中更新过的共享变量刷新到主内存中去。
  - 线程B到主内存中去读取线程A之前已经更新过的共享变量。

**所以，线程A无法直接访问线程B的工作内存，线程间通信必须经过主内存。**

注意，**根据JMM的规定，线程对共享变量的所有操作都必须在自己的本地内存中进行，不能直接从主内存中读取**。

所以线程B并不是直接去主内存中读取共享变量的值，而是先在本地内存B中找到这个共享变量，发现这个共享变量已经被更新了，然后本地内存B去主内存中读取这个共享变量的新值，并拷贝到本地内存B中，最后线程B再读取本地内存B中的新值。

JMM通过控制主内存与每个线程的本地内存之间的交互，来提供内存可见性保证。

---

- JMM和Java运行时内存区域差别与联系：
  - 区别
    - 两者是不同的概念层次。
    - JMM是抽象的，他是用来描述一组规则，通过这个规则来控制各个变量的访问方式，围绕原子性、有序性、可见性等展开的。
    - 而Java运行时内存的划分是具体的，是JVM运行Java程序时，必要的内存划分。
  - 联系
    - 都存在私有数据区域和共享数据区域。
    - 一般来说，JMM中的主内存属于共享数据区域，他是包含了堆和方法区；
    - 同样，JMM中的本地内存属于私有数据区域，包含了程序计数器、本地方法栈、虚拟机栈。

---

多线程情况下的 运行时内存模型的表现？？？

---

### 笔记1

#### JMM与volatile

![key_points-10](./image/key_points-10.png)


- volatile作用:
    <details>
    <summary style="color:red;">如何答得全面</summary>

    ```
    解释一下volatile

    首先简单地说一下作用
    谈一下JMM
    谈一下JMM原子操作
    说一下volatile底层原理：MESI缓存一致性协议以及总线嗅探机制
    ```
    </details>

  - 保证变量的内存可见性
    - 保证多线程之间的共享变量的内存可见性
    - 如果一个变量用volatile修饰
    - 尽管每个线程都有该变量的副本，
    - 但是任一线程修改了自己变量副本的值
    - 其他线程都可以马上感知到
  - 禁止volatile变量与普通变量重排序
    <details>
    <summary style="color:red;">原理与说明</summary>

    ![key_points-14](./image/key_points-14.png)
    > 再逐个解释一下这几个屏障。注：下述Load代表读操作，Store代表写操作

    - LoadLoad屏障：对于这样的语句Load1; LoadLoad; Load2，在Load2及后续读取操作要读取的数据被访问前，保证Load1要读取的数据被读取完毕。
    - StoreStore屏障：对于这样的语句Store1; StoreStore; Store2，在Store2及后续写入操作执行前，这个屏障会把Store1强制刷新到内存，保证Store1的写入操作对其它处理器可见。
    - LoadStore屏障：对于这样的语句Load1; LoadStore; Store2，在Store2及后续写入操作被刷出前，保证Load1要读取的数据被读取完毕。
    - StoreLoad屏障：对于这样的语句Store1; StoreLoad; Load2，在Load2及后续所有读取操作执行前，保证Store1的写入对所有处理器可见。它的开销是四种屏障中最大的（冲刷写缓冲器，清空无效化队列）。在大多数处理器的实现中，这个屏障是个万能屏障，兼具其它三种内存屏障的功能

    <br /><br />

    - volatile与普通变量的重排序规则:
      - 如果第一个操作是volatile读，那无论第二个操作是什么，都不能重排序；
      - 如果第二个操作是volatile写，那无论第一个操作是什么，都不能重排序；
      - 如果第一个操作是volatile写，第二个操作是volatile读，那不能重排序。

    ```java
    // step 1，是普通变量的写，step 2是volatile变量的写，那符合第2个规则，这两个steps不能重排序。而step 3是volatile变量读，step 4是普通变量读，符合第1个规则，同样不能重排序。
    
    // 声明变量
    int a = 0; // 声明普通变量
    volatile boolean flag = false; // 声明volatile变量

    // 以下两个变量的读操作是可以重排序的
    int i = a; // 普通变量读
    boolean j = flag; // volatile变量读
    ```
    </details>

- volatile与锁
  - 在保证内存可见性这一点上，volatile有着与锁相同的内存语义，所以可以作为一个“轻量级”的锁来使用。
  - 但由于volatile仅仅保证对单个volatile变量的读/写具有原子性，而锁可以保证整个临界区代码的执行具有原子性。
  - **所以在功能上，锁比volatile更强大；在性能上，volatile更有优势。**

- JMM数据原子操作
  > <details>
  > <summary style="color:red;">示例展示</summary>
  > 
  > ![key_points-11](./image/key_points-11.png) 
  > 
  > > **线程与线程之间是无法交互的。只有通过主内存才可以交互**
  > </details>
  - read(读取）:从主内存读取数据
  - load(载入）:将主内存读取到的数据写入工作内存
  - use(使用）:从工作内存读取数据来计算
  - assign(赋值）:将计算好的值重新赋值到工作内存中
  - store(存储）:将工作内存数据写入主内存
  - write(写入）:将store过去的变量值赋值给主内存中的变量
  - lock(锁定）:将**主内存变量**加锁，标识为线程独占状态。其他线程将无法读或写
  - unlock(解锁）:将主内存变量解锁，解锁后其他线程可以锁定该变量

- JMM 缓存不一致性问题解决方式：
  - 总线加锁
    ```
    cpu从主内存读取数据到高速缓存，会在总线对这个数据加锁，这样其它cpu没法
    去读或写这个数据，直到这个cpu使用完数据释放锁之后其它cpu才能读取该数据
    ```
  - MESI缓存一致性协议+cpu总线嗅探机制

- volatile内存可见性原理：
  > 三个方面：MESI缓存一致性协议， cpu总线嗅探机制， lock
  > 
  > <details>
  > <summary style="color:red;">图解</summary>
  > 
  > ![key_points-12](./image/key_points-12.png) 
  > > 图解
  > </details>
  - volatile底层实现主要是通过汇编lock前缀指令。
  - lock会将当前处理器缓存行的数据**立即写回到系统内存**
    - cpu和内存间的数据交互都会经过总线
    - 当一个线程执行store原子操作时。数据会通过总线到主内存。
    - store时会进行一个lock原子操作
  - 这个写回内存的操作会引起在其他CPU里缓存了该内存地址的数据无效（MESI）
    - 上面也提到了，会经过总线
    - MESI缓存一致性性协议中有一个cpu总线嗅探机制
    - cpu总线嗅探机制会一直监控着经过总线的数据流动
    - 如果经过总线的数据，在其他的cpu的工作内存中也有副本，那么其他cpu中的副本就会被置为无效
    - 因为被设置为了无效，其他cpu会重新从主内存中重新read。如果有锁会等待
      > 注意，这就是上面进行lock的原因。如果不进行lock，在回写之前进行read，会读到原来的值
    - 回写(write原子操作)完后会进行unlock原子操作。之后unlock后才能被读取到

- 并发编程**三大特性**
  > volatile可以保证可见性与有序性，但不保证原子性。保证原子性需要synchronized锁机制
  - 原子性：就是说一个操作不能被打断，要么执行完要么不执行，类似事务操作，Java 基本类型数据的访问大都是原子操作，long 和 double 类型是 64 位，在 32 位 JVM 中会将 64 位数据的读写操作分成两次 32 位来处理，所以 long 和 double 在 32 位 JVM 中是非原子操作，也就是说在并发访问时是线程非安全的，要想保证原子性就得对访问该数据的地方进行同步操作，譬如 synchronized 等。
  - 可见性：就是说当一个线程对共享变量做了修改后其他线程可以立即感知到该共享变量的改变，从 Java 内存模型我们就能看出来多线程访问共享变量都要经过线程工作内存到主存的复制和主存到线程工作内存的复制操作，所以普通共享变量就无法保证可见性了；Java 提供了 volatile 修饰符来保证变量的可见性，每次使用 volatile 变量都会主动从主存中刷新，除此之外 synchronized、Lock、final 都可以保证变量的可见性。
  - 有序性：就是说 Java 内存模型中的指令重排不会影响单线程的执行顺序，但是会影响多线程并发执行的正确性，所以在并发中我们必须要想办法保证并发代码的有序性；在 Java 里可以通过 volatile 关键字保证一定的有序性，还可以通过 synchronized、Lock 来保证有序性，因为 synchronized、Lock 保证了每一时刻只有一个线程执行同步代码相当于单线程执行，所以自然不会有有序性的问题；除此之外 Java 内存模型通过 happens-before 原则如果能推导出来两个操作的执行顺序就能先天保证有序性，否则无法保证，关于 happens-before 原则可以查阅相关资料。

- volatile不保证原子性
  ```
  情景：10个线程，同时对一个volatile标志的变量num进行1000次++。最后num为多少？

  小于等于10000
  ```
  - 现在以2个线程A,B进行说明
  - A线程对工作内存中的num进行++，为1。
  - B线程也对工作内存中的num进行了++，为1
  - 可能A线程较快完成++操作，最先加锁，因此B线程工作内存中的num的值失效。此次失效，**导致了一次`++`的丢失。**
  - 如果下一次B进行第二次++（A和B总共三次），且抢到了锁，那么回写为2，而不是3（丢失一次++）
  - 也就是说**MESI缓存一致性协议中经过总线的数据会导致其他cpu工作内存副本失效**这一点会导致volatile不保证原子性

#### 重排序

- 指令重排序：
  > ![key_points-13](./image/key_points-13.png) 
  - 在不影响单线程程序执行结果的前提下，计算机为了最大限度的发挥机器性能，会对机器指令重排序优化
  - 代码示例
    ```java
    for(int i = 0;i<1000000;i++){
      x = 0;
      y = 0;
      a = 0;
      b = 0;
      Thread one = new Thread(()->{
        a = y;
        x = 1;
      });
      Thread two = new Thread(()->{
         b = x;
         y = 1;
      });
    }
    // 最终结果可能有三种：
    // a = 0;b = 0，两个线程争抢资源,执行了前面的代码，后才执行赋1操作
    // a = 1;b = 0，one线程执行了赋1操作后，再执行two线程的b = x
    // a = 1;b = 1，发生了重排序
    ```

##### 重排序规范：as-if-serial

不管怎么重排序，**单线程**程序的执行结果不能被改变。

也就是说编译器和处理器不会对**存在数据依赖关系**的操作做重排序。

比如以下代码：

```java
a = x;
y = a;
```

##### 重排序规范：happens-before

```
是一个给程序员使用的规则，只要程序员在写代码的时候遵循happens-before规则，JVM就能保证指令在多线程之间的顺序性符合程序员的预期。
```

- 引入：就像上面发生重排序的例子。
  - 一方面，程序员需要JMM提供一个强的内存模型来编写代码；
    > 指的是有更多规定，意外情况越少越好。
  - 另一方面，编译器和处理器希望JMM对它们的束缚越少越好，这样它们就可以最可能多的做优化来提高性能，希望的是一个弱的内存模型。
    > 指的是希望通过重排序优化性能
  - 而对于程序员，JMM提供了happens-before规则（JSR-133规范），满足了程序员的需求--**简单易懂，并且提供了足够强的内存可见性保证**。
  - 换言之，程序员只要遵循happens-before规则，那他写的程序就能保证在JMM中具有强的内存可见性。

- happens-before关系的定义如下：
  - 如果一个操作happens-before另一个操作，那么第一个操作的执行结果将对第二个操作可见，而且第一个操作的执行顺序排在第二个操作之前。
  - **两个操作之间存在happens-before关系，并不意味着Java平台的具体实现必须要按照happens-before关系指定的顺序来执行。如果重排序之后的执行结果，与按happens-before关系来执行的结果一致，那么JMM也允许这样的重排序。**

```
happens-before关系本质上和as-if-serial语义是一回事。

as-if-serial语义保证单线程内重排序后的执行结果和程序代码本身应有的结果是一致的，
happens-before关系保证正确同步的多线程程序的执行结果不被重排序改变。
```

- 原则：
  - 1.程序顺序原则：即在一个线程内必须保证语义串行性，也就是说按照代码顺序执行。
  - 2.锁规则：解锁（unlock)操作必然发生在后续的同一个锁的加锁（lock)之前，也就是说，如果对于一个锁解锁后，再加锁，那么加锁的动作必须在解锁动作之后（同一个锁）。
  - 3.volatile规则：volatile变量的写，先发生于读，这保证了volatile变量的可见性，简单的理解就是，volatile变量在每次被线程访问时，都强迫从主内存中读该变量的值，而当该变量发生变化时，又会强迫将最新的值刷新到主内存，任何时刻，不同的线程总是能够看到该变量的最新值。
  - 4.线程启动规则：线程的start()方法先于它的每一个动作，即如果线程A在执行线程B的start方法之前修改了共享变量的值，那么当线程B执行start方法时，线程A对共享变量的修改对线程B可见
  - 5.传递性：A先于B,B先于C那么A必然先于C
  - 6.线程终止规则：线程的所有操作先于线程的终结，Thread.join()方法的作用是等待当前执行的线程终止，假设在线程B终止之前，修改了共享变量，线程A从线程B的join方法成功返回后，线程B对共享变量的修改将对线程A可见。
  - 7.线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过Thread.interrupted()方法检测线程是否中断。
  - 8.对象终结规则：对象的构造函数执行，结束先于finalize()方法

```
程序次序规则：一段代码在单线程中执行的结果是有序的。注意是执行结果，因为虚拟机、处理器会对指令进行重排序（重排序后面会详细介绍）。虽然重排序了，但是并不会影响程序的执行结果，所以程序最终执行的结果与顺序执行的结果是一致的。故而这个规则只对单线程有效，在多线程环境下无法保证正确性。

锁定规则：这个规则比较好理解，无论是在单线程环境还是多线程环境，一个锁处于被锁定状态，那么必须先执行unlock操作后面才能进行lock操作。

volatile变量规则：这是一条比较重要的规则，它标志着volatile保证了线程可见性。通俗点讲就是如果一个线程先去写一个volatile变量，然后一个线程去读这个变量，那么这个写操作一定是happens-before读操作的。

传递规则：提现了happens-before原则具有传递性，即A happens-before B , B happens-before C，那么A happens-before C

线程启动规则：假定线程A在执行过程中，通过执行ThreadB.start()来启动线程B，那么线程A对共享变量的修改在接下来线程B开始执行后确保对线程B可见。

线程终结规则：假定线程A在执行的过程中，通过制定ThreadB.join()等待线程B终止，那么线程B在终止之前对共享变量的修改在线程A等待返回后可见。
```

# 设计模式

## 代理

- 静态态代理
- 动态代理
- cglib代理

# 并发

## 概述

- JUC包括哪些类
  - Lock框架和Tools类(把图中这两个放到一起理解)
    > ![key_points-17](./image/key_points-17.png) 
  - Collections: 并发集合
    > ![key_points-18](./image/key_points-18.png) 
  - Atomic: 原子类
  - Executors: 线程池
    > ![key_points-19](./image/key_points-19.png) 

## ThreadLocal

[ThreadLocal详解](https://www.cnblogs.com/fsmly/p/11020641.html)

<br /><br />


ThreadLocal提供了线程内存储变量的能力，这些变量不同之处在于每一个线程读取的变量是对应的互相独立的。通过get和set方法就可以得到当前线程对应的值。

ThreadLocal的静态内部类ThreadLocalMap为每个Thread都维护了一个数组table，ThreadLocal确定了一个数组下标，而这个下标就是value存储的对应位置。。

<br /><br />

- 对于某一ThreadLocal来讲，他的索引值i是确定的，在不同线程之间访问时访问的是不同的table数组的同一位置即都为table[i]，只不过这个不同线程之间的table是独立的。
- 对于同一线程的不同ThreadLocal来讲，这些ThreadLocal实例共享一个table数组，然后每个ThreadLocal实例在table中的索引i是不同的。

<br /><br />

- ThreadLocal和Synchronized都是为了解决多线程中相同变量的访问冲突问题，不同的点是
  - Synchronized是通过线程等待，牺牲时间来解决访问冲突
  - ThreadLocal是通过每个线程单独一份存储空间，牺牲空间来解决冲突，并且相比于Synchronized，ThreadLocal具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问到想要的值。

## Callable Future FutureTask

## java线程状态

![key_points-6](./image/key_points-6.png)

- NEW:处于NEW状态的线程此时尚未启动。这里的尚未启动指的是还没调用Thread实例的start()方法
- RUNNABLE:表示当前线程正在运行中。处于RUNNABLE状态的线程在Java虚拟机中运行，也有可能在等待CPU分配资源。
  > Java线程的RUNNABLE状态其实是包括了传统操作系统线程的ready和running两个状态的。
- BLOCKED:处于BLOCKED状态的线程正等待锁的释放以进入同步区
- WAITING:等待状态。处于等待状态的线程变成RUNNABLE状态需要其他线程唤醒。调用如下3个方法会使线程进入等待状态：
  - Object.wait()：使当前线程处于等待状态直到另一个线程唤醒它；
  - Thread.join()：等待线程执行完毕，底层调用的是Object实例的wait方法；
  - LockSupport.park()：除非获得调用许可，否则禁用当前线程进行线程调度。
- TIMED_WAITING:超时等待状态。线程等待一个具体的时间，时间到后会被自动唤醒。调用如下方法会使线程进入超时等待状态：
  - Thread.sleep(long millis)：使当前线程睡眠指定时间；
  - Object.wait(long timeout)：线程休眠指定时间，等待期间可以通过notify()/notifyAll()唤醒；
  - Thread.join(long millis)：等待当前线程最多执行millis毫秒，如果millis为0，则会一直执行；
  - LockSupport.parkNanos(long nanos)： 除非获得调用许可，否则禁用当前线程进行线程调度指定时间；
  - LockSupport.parkUntil(long deadline)：同上，也是禁止线程进行调度指定时间；
- TERMINATED:终止状态。此时线程已执行完毕。

![key_points-7](./image/key_points-7.png)

## ThreadGroup与优先级

默认将父线程（当前执行new Thread的线程）线程组设置为自己的线程组。

ThreadGroup管理着它下面的Thread，ThreadGroup是一个标准的向下引用的树状结构，这样设计的原因是防止"上级"线程被"下级"线程引用而无法有效地被GC回收。

Java中线程优先级可以指定，范围是1~10。但是并不是所有的操作系统都支持10级优先级的划分（比如有些操作系统只支持3级划分：低，中，高），Java只是给操作系统一个优先级的参考值，线程最终在操作系统的优先级是多少还是由操作系统决定。

Java默认的线程优先级为5，线程的执行顺序由调度程序来决定，线程的优先级会在线程**被调用之前设定**。

通常情况下，高优先级的线程将会比低优先级的线程有**更高的几率**得到执行。我们使用方法Thread类的setPriority()实例方法来设定线程的优先级。

**Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。而真正的调用顺序，是由操作系统的线程调度算法决定的。**

守护线程默认的优先级比较低。一个线程默认是非守护线程，可以通过Thread类的setDaemon(boolean on)来设置为守护线程。

线程组的优先级会限制线程的优先级。也就是说，如果某个线程优先级大于线程所在线程组的最大优先级，那么该线程的优先级将会失效，取而代之的是线程组的最大优先级。

- 线程组作用
  - 获取当前的线程组名字
  - 复制线程组 enumerate
  - 线程组统一异常处理。重写uncaughtException

总结来说，线程组是一个树状的结构，每个线程组下面可以有多个**线程**或者**线程组**。线程组可以起到统一控制线程的优先级和检查线程的权限的作用。

## 通信模型

- 有两种并发模型
  - 消息传递并发模型
  - 共享内存并发模型

![key_points-9](./image/key_points-9.png)

**在Java中，使用的是共享内存并发模型。**

## 锁的演进

### java1.6之前的synchronized锁

就是重量锁，使用OS底层的互斥量机制。

相关内容都放到后面整体讲解那里。

### java1.5中的新锁

#### 目的

**目的是JVM自身解决问题，不需要调用操作系统**

#### 原理

##### Atomic

- 概念：
  - 原子更新，两个线程对一个数字做递增，如果不上锁的话，会导致结果错误。
- 使用：
  - 在JDK1.5的时候，出现的乐观锁，也就是无锁
  - 不加锁，也能保证结果正确。

- 原理：
  - 使用的是`CompareAndSwap`，即比较和交换
  - CAS能够保证原子性。

##### CAS

- 概念：
  <details>
  <summary style="color:red;">例子</summary>

  如果有一个多个线程共享的变量i原本等于5，我现在在线程A中，想把它设置为新的值6;
  我们使用CAS来做这个事情；
  首先我们用i去与5对比，发现它等于5，说明没有被其它线程改过，那我就把它设置为新的值6，此次CAS成功，i的值被设置成了6；
  如果不等于5，说明i被其它线程改过了（比如现在i的值为2），那么我就什么也不做，此次CAS失败，i的值仍然为2。
  </details>

  - V ar
    > 可以理解成本地内存中变量的值
  - E xpected
    > 预期值E本质上指的是“旧值”。也就是主内存中的值
  - N ew
    > 要赋的新值。

- 保障：
  - 比较与修改是两个操作
  - 通过硬件底层保障CAS操作的原子性。

- Java实现CAS的原理 - Unsafe类
- CAS实现原子操作的三大问题
  - ABA问题
    - 概念：所谓ABA问题，就是一个值原来是A，变成了B，又变回了A。这个时候使用CAS是检查不出变化的，但实际上却被更新了两次。
    - 解决:
      - ABA问题的解决思路是在变量前面追加上版本号或者时间戳。从JDK 1.5开始，JDK的atomic包里提供了一个类AtomicStampedReference类来解决ABA问题。
      - 还有一个是AtomicMarkableReference，也可以解决ABA问题，不过不是加记录版本号，而是通过一个false记录是否有改动过
  - 循环时间长开销大。（轻量锁+自旋）
  - 只能保证一个共享变量的原子操作

##### AQS

- ReentrantLock流程
  > ![key_points-16](./image/key_points-16.png)


##### LockSupport

LockSupport用来创建锁和其他同步类的基本线程阻塞原语。简而言之，当调用LockSupport.park时，表示当前线程将会等待，直至获得许可，当调用LockSupport.unpark时，必须把等待获得许可的线程作为参数进行传递，好让此线程继续运行


**AQS框架借助于两个类：Unsafe(提供CAS操作)和LockSupport(提供park/unpark操作)**

因此把LockSupport归为核心基础类，比如在Condition中，await方法就是使用的LockSupport的park方法。
> 另外，LockSupport只负责阻塞当前线程，释放锁资源实际上是在Condition的await()方法中实现的。


- **许可默认是被占用的**，一开始就调用park()的话，无法获取到许可，进入阻塞状态。 先释放许可，再获取许可，才能正常运行
  > LockSupport许可的获取和释放，一般来说是对应的，如果多次unpark，只有一次park也不会出现什么问题，结果是许可处于可用状态。
  ```java
  LockSupport.park();
  System.out.println("block.");
  // 主线程一直处于阻塞状态
  ```

- **LockSupport是不可重入的**，如果一个线程连续2次调用LockSupport.park()，那么该线程一定会一直阻塞下去。
  ```java
  Thread thread = Thread.currentThread();
  
  LockSupport.unpark(thread);
  
  System.out.println("a");
  LockSupport.park();
  System.out.println("b");
  LockSupport.park();
  System.out.println("c");
  // 这段代码打印出a和b，不会打印c，因为第二次调用park的时候，线程无法获取许可出现死锁。
  ```
- **线程如果因为调用park而阻塞的话，能够响应中断请求(中断状态被设置成true)，但是不会抛出InterruptedException。仅仅会isInterrupted()返回true**


#### CAS+AQS+volatile+LockSupport的产物

**AQS框架借助于两个类：Unsafe(提供CAS操作)和LockSupport(提供park/unpark操作)**

##### ReentrantLock

- Object.wait()相当于把线程加入锁的 **唯一一个** 等待队列
  > 看下面重量锁的多个队列
- 而ReentrantLock中，获得的每个Condition都可以看作一个队列。
  - condition.await()相当于把线程加入该condition的等待队列

##### Latch


##### Semaphore

##### CycliBarrier

##### Phaser

#### Exchanger

### java1.6及之后的synchronized与锁

#### 优化内容

增加了锁的种类和锁的升级（不可逆，无法降级）

#### 具体内容

> 推荐看深入浅出Java多线程

- 偏向锁
  - 原理：CAS
  - 出现原因：大多数情况下**锁不仅不存在多线程竞争，而且总是由同一线程多次获得**
  - 作用：
    ```
    大白话就是对锁置个变量，如果发现为true，代表资源无竞争，则无需再走各种加锁/解锁流程。
    如果为false，代表存在其他线程竞争资源，那么就会走后面的流程。
    ```
    - 偏向锁会偏向于第一个访问锁的线程，
    - 如果在接下来的运行过程中，该锁没有被其他的线程访问，则持有偏向锁的线程将永远不需要触发同步。
    - 也就是说，**偏向锁在资源无竞争情况下消除了同步语句，连CAS操作都不做了，提高了程序的运行性能**。
  - 实现原理

- 轻量锁
  - 原理：CAS

- 重量锁
  - 原理：重量级锁依赖于操作系统的互斥量（mutex） 实现的
  - 效率：操作系统中线程间状态的转换需要相对比较长的时间，所以重量级锁效率很低，但被阻塞的线程**不会消耗CPU**。
    > 相对CAS不会调用操作系统，但会自旋CAS会消耗CPU
  - 实现原理：各种队列
    - Wait Set：那些 **调用wait方法** 被阻塞的线程被放置到Wait Set
    - Contention List：所有请求锁的线程将被首先放置到该竞争队列。调用 **notify** 后会放到竞争队列。
    - Entry List：Contention List中那些有资格成为候选人的线程被移到Entry List
    - OnDeck：任何时刻最多只能有一个线程正在竞争锁，该线程称为OnDeck
    - Owner：获得锁的线程称为Owner
    - !Owner：释放锁的线程

---

- 锁记录
  - 位置：JVM会为每个线程在**当前线程的栈帧中**创建用于存储**锁记录**的空间，我们称为Displaced Mark Word
  - 作用：对于轻量锁对象，如果锁

---

- synchronized是非公平锁
  - notify方法调用后，会根据操作系统的调度机制

#### 效率


## 锁的分类

- 锁的有无
  - 乐观锁:
    - 乐观锁又称为“无锁”，顾名思义，它是乐观派。
    - 乐观锁总是假设对共享资源的访问没有冲突，线程可以不停地执行，无需加锁也无需等待。
    - 而一旦多个线程发生冲突，乐观锁通常是使用一种称为CAS的技术来保证线程执行的安全性。
  - 悲观锁
    - 悲观锁就是我们常说的锁。
    - 对于悲观锁来说，它总是认为每次访问共享资源时会发生冲突，
    - 所以必须对每次数据操作加上锁，以保证临界区的程序同一时间只能有一个线程在执行。

- synchronized与锁
  - 偏向锁
  - 轻量锁
  - 重量锁

- 锁的整体分类
  - 可重入锁和非可重入锁
  - 公平锁与非公平锁
    > synchronized是公平锁
  - 读写锁和排它锁


## 线程池

### 基本说明

```
Executors中提供了四种创建的线程池的实现。比如Executors.newFixedThreadPool(5)。（当然，Executors底层使用的也是ThreadPoolExecutor）

四种常见的线程池基本够我们使用了，但是《阿里把把开发手册》不建议我们直接使用Executors类中的线程池，
而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学需要更加明确线程池的运行规则，规避资源耗尽的风险。

但如果你及团队本身对线程池非常熟悉，又确定业务规模不会大到资源耗尽的程度
（比如线程数量或任务队列长度可能达到Integer.MAX_VALUE）时，
其实是可以使用JDK提供的这几个接口的，它能让我们的代码具有更强的可读性。
```

- 种类：
  - ThreadPoolExecutor（最基本）
    - 组成：可以设置核心线程数，最大线程数，超时时间等。
    - 执行规则：
      > <details>
      > <summary style="color:red;">图示</summary>
      >
      > ![key_points-15](./image/key_points-15.png)
      > </details

      - 如果线程未达到核心线程数量，那么直接启动一个核心线程。
      - 如果线程达到核心线程的数量，任务会被插入到任务队列（workQueue）排队。
      - 如果任务队列已满导致步骤2无法插入到任务队列，那么开启一个非核心线程执行。
      - 如果步骤3的线程数量达到线程池规定数目（maxmumPoolSize），那么拒绝执行此任务。
  - FixedThreadPool（可重用固定线程数）
    - 组成：只有核心线程，无非核心线程，无超时机制，阻塞队列无界。
    - 使用：
      - 当线程处于空闲状态时，只要线程池不被关闭它们就并不会被回收。
      - 当所有线程都处于活动状态，新任务就会处于等待状态，直到有线程空闲出来。
      - 适用于执行长期任务。
    - 底层实现
      ```java
      public static ExecutorService newFixedThreadPool(int nThreads) {
              return new ThreadPoolExecutor(nThreads, nThreads,
                                            0L, TimeUnit.MILLISECONDS,
                                            new LinkedBlockingQueue<Runnable>());
      }
      ```
  - CachedThreadPool (按需创建)
    - 组成：线程数量不定，没有核心线程，只有非核心线程，每个线程空闲等待的时间为60s，采用SynchronousQueue队列。
    - 使用：
      - 当线程都处于活动状态时，线程池会创建新线程来执行任务，否则就会复用空闲的线程。
      - 当所有线程都处于闲置状态，线程会逐渐因为超时被停止，线程池中就没有线程，几乎不占系统资源。
      - 这种线程池适用于大量耗时少的任务
    - 底层实现
      ```java
      public static ExecutorService newCachedThreadPool() {
          return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                        60L, TimeUnit.SECONDS,
                                        new SynchronousQueue<Runnable>());
      }
      ```
  - ScheduledThreadPool(定时延时执行)
    - 组成：核心线程数固定，非核心线程数没有限制，非核心线程闲置时会被立即回收。
    - 使用：
      - 主要用于执行定时任务和周期性重复任务。
    - 底层实现
      ```java
      public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
          return new ScheduledThreadPoolExecutor(corePoolSize);
      }

      //ScheduledThreadPoolExecutor():
      public ScheduledThreadPoolExecutor(int corePoolSize) {
          super(corePoolSize, Integer.MAX_VALUE,
                DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
                new DelayedWorkQueue());
      }
      ```
  - SingleThreadExecutor（单核fixed）
    - 组成：只有一个核心线程，存活时间无限
    - 底层实现
      ```java
      public static ExecutorService newSingleThreadExecutor() {
          return new FinalizableDelegatedExecutorService
              (new ThreadPoolExecutor(1, 1,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>()));
      }
      ```

- 常用方法：
  - 1.shutDown()  关闭线程池，不影响已经提交的任务
  - 2.shutDownNow() 关闭线程池，并尝试去终止正在执行的线程
  - 3.allowCoreThreadTimeOut(boolean value) 允许核心线程闲置超时时被回收
  - 4.submit 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值。
  - 5.beforeExecute()  任务执行前执行的方法
  - 6.afterExecute() 任务执行结束后执行的方法
  - 7.terminated()  线程池关闭后执行的方法

- 线程池状态
  - RUNNING
  - SHUTDOWN
  - STOP
  - TIDYING 
  - TERMINATED



<details>
<summary style="color:red;">基础思考题</summary>

```
了解JDK Executors线程池吗?

知道JDK提供了哪些默认的实现吗？ 

看过阿里巴巴java开发手册吗？知道为啥不允许使用默认的实现吗？ 

你们没有用默认的吧?那来介绍一下你们自定义线程池的几个常用参数呗？ 

你这个几个参数的值是怎么得来的呀？算出来的？怎么算出来的？ 

好，现在我们有一个自定义线程池了，来说一下你这个线程池的工作流程呗？ 

那你这个线程池满了怎么办呀？拒绝？咋拒绝？有哪些拒绝策略呢？ 

别紧张,随便说两个就行。
```
</details>


## java同步方式

- synchronized
- volatile
- ReentrantLock
- ThreadLocal
- 阻塞队列，LinkedBlockingQueue 
- 原子变量AtomicInteger 等

## 分布式锁

- redis
- zookeeper
- etcd

## 并发容器

并发容器是Java 5 提供的在多线程编程下用于代替同步容器

- 同步容器：vector，HashTable
- 并发容器：
  > ![key_points-20](./image/key_points-20.png) 

## 面试题

(阿里)用两个线程，一个输出字母，一个输出数字，交替输出1A2B3D...26Z

- LockSupport
- synchronized
- ReentrantLock
- Latch
- 任务队列
- ....很多很多

