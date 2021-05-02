# 方法论

## OOP

## AOP

## FP

# 原则和模式

详见：[其大原则与设计模式](../design_patterns/design_pattern.md)

# 范式

## GP

Generic Programming

## MP

Meta Programming

# 具体思想

## 分合思想

### 分

- 集中式服务发展到分布式服务
- 从Collections.synchronizedMap(x)到1.7ConcurrentHashMap再到1.8ConcurrentHashMap，细化锁的粒度的同时依旧保证线程安全
- 从AtomicInteger到LongAdder，ConcurrentHashMap的size()方法。用分散思想，减少cas次数，增强多线程对一个数的累加
- JVM的G1 GC算法，将堆分成很多Region来进行内存管理
- Hbase的RegionServer中，将数据分成多个Region进行管理
- 平时开发是不是线程池都资源隔离

### 合

- TLAB（Thread Local Allocation Buffers），线程本地分配缓存。避免多线程冲突，提高对象分配效率
- 逃逸分析，将变量的实例化内存直接在栈里分配，无需进入堆，线程结束栈空间被回收。减少临时对象在堆内分配数量
- CMS GC算法下，虽然使用标记清除，但是也有配置支持整理内存碎片。如：
  - -XX:UseCMS-CompactAtFullCollection（FullGC后是否整理，Stop The World会变长）
  - -XX:CMSFullGCs-BeforeCompaction（几次FullGC之后进行压缩整理）
- 锁粗化，当JIT发现一系列连续的操作都是对同一对象反复加锁和释放锁，会加大锁同步的范围
- kafka的网络数据传输有一些数据配置，减少网络开销。如：batch.size和linger.ms等等
- 平时开发是不是都个叫批量获取接口

# 参考资料

待补充

- [谈谈编程思想](https://blog.p2hp.com/archives/4978)
- 《冒号课堂》
