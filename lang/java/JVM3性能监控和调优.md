- <mark> [官方文档](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/index.html) </mark>

至少完美掌握两三款。推荐先搞定Visual VM,Arthas。然后再看看JProfiler和eclipse MAT

# 1. 性能监控与调优概述

## 1.1. 调优背景

- 生产环境中的问题
  - 生产环境发生内存溢出该如何处理？
  - 生产环境应该给服务器分配多少内存合适？
  - 如何对垃圾回收器的性能进行调优？
  - 生产环境 CPU 负载飙高该如何处理？
  - 生产环境应该给应用分配多少线程合适？
  - 不加 log，如何确定请求是否执行了某一行代码？
  - 不加 log，如何实时查看某个方法的入参与返回值？

- 为什么要调优？
  - 防止出现 OOM
  - 解决 OOM
  - 减少 Full GC 出现的频率

- 不同阶段的考虑
  - 上线前:测试，jps降低，延迟，cpu使用率等问题
  - 项目运行阶段：线上出现 OOM，运行情况监控

## 1.2. 调优的概述

- 监控的依据
  - 运行日志
  - 异常堆栈
  - GC 日志
  - 线程快照
  - 堆转储快照

- 调优的大方向
  - 合理的编写代码
  - 充分并合理的使用硬件资源
  - 合理的进行 JVM 调优

## 1.3. 性能优化的步骤

- 第一步（发现问题）：**性能监控**
  - 说明：
    - 一种以非强行或者入侵方式**收集或查看**应用运行性能数据的活动
    - 监控通常是指一种在生产、质量评估或者开发环境下实施的带有**预防或主动性**的活动
  - 执行时期：
    - 当应用相关干系人提出性能问题却**没有提供足够多的线索时**，首先我们需要进行性能监控，随后是性能分析。
  - 发现的问题：
    - GC 频繁
    - CPU load 过高
    - OOM
    - 内存泄露
    - 死锁
    - 程序相应时间较长

- 第二步（排查问题）：**性能分析**
  - 说明：
    - 一种以**侵入方式**收集运行性能问题的答复结果，它会影响应用的吞吐量或响应性
    - 性能分析是针对性能问题的答复结果，关注的范围通常比性能监控更加集中
  - 执行环境：
    - 性能分析**很少在生产环境**下进行
    - 通常是在质量评估、**系统测试或者开发环境下进行**
  - 执行时期：
    - 是性能监控之后的步骤。
  - 行为：
    - 打印 GC 日志，通过 GCviewer 或者[gceasy](https://gceasy.io/)来分析日志信息
    - 灵活运用命令行工具，jstack、jmap、jinfo 等
    - dump 出堆文件，使用内存分析工具分析文件
    - 使用阿里 Arthas 或 jconsole，JVisualVM 来实时查看 JVM 状态
    - jstack 查看堆栈信息

- 第三步（解决问题）：**性能调优**
  - 说明：
    - 一种为改善应用响应性或吞吐量而更改参数、源代码、属性配置的活动
  - 执行时期：
    - 性能调优是在性能监控、性能分析之后的活动。
  - **性能调优的目的**
    - 减少 GC 的频率，以较少的内存获取更大的吞吐量和更低的延迟
  - 调优行为：
    - 适当增加内存，根据业务背景选择垃圾回收器
    - 优化代码，控制内存使用
    - 增加机器，分散节点压力
    - 合理设置线程池线程数量
    - 使用中间件提高程序效率，比如缓存，消息队列等
    - 其他…

    ```
    没有绝对的调优，具体问题具体分析
    ```

## 1.4. 评价指标

### 1.4.1. 停顿时间（或响应时间）

- 应用层面：
  - 提交请求和返回请求的响应之间使用的时间，一般比较关注平均响应时间。
  - 常用操作的响应时间列表

    | 操作                                | 响应时间 |
    | ----------------------------------- | -------- |
    | 打开一个站点                        | 几秒     |
    | 数据库查询一条记录（有索引）        | 十几毫秒 |
    | 机械硬盘一次寻址                    | 4 毫秒   |
    | 从机械硬盘顺序读取 1M 数据          | 2 毫秒   |
    | 从 SSD 磁盘顺序读取 1M 数据         | 0.3 毫秒 |
    | 从远程分布式换成 Redis 读取一个数据 | 0.5 毫秒 |
    | 从内存读取 1M 数据                  | 十几微秒 |
    | Java 程序本地方法调用               | 几微秒   |
    | 网络传输 2Kb 数据                   | 1 微秒   |

- 垃圾回收层面
  - 暂停时间： **执行垃圾收集时，程序的工作线程被暂停的时间**
  - 参数： `-XX:MaxGCPauseMills`

### 1.4.2. 吞吐量

- 对单位时间内完成的工作量（请求）的度量
- 在 GC 中：运行用户代码的时间占总运行时间的比例（总运行时间：程序运行时间+内存回收的时间n）吞吐量为 **`1- 1/(1+n)`**
  > n为参数`-XX:GCTimeRatio`获取的值

### 1.4.3. 并发数

- 同一时刻，对服务器有实际交互的请求数。
- 1000 个人同时在线，估计并发数在 5%-15%之间，也就是同时并发量：50-150 之间。

### 1.4.4. 内存占用

- Java 堆区所占的内存大小
- 主要是堆

# 2. JVM监控及诊断工具-命令行

## 2.1. 概述

[文档](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/index.html)

```
使用数据说明问题，使用知识分析问题，使用工具处理问题
无监控、不调优！
```

- Java作为最流行的编程语言之一，其应用性能诊断一直受到业界广泛关注
- 可能造成Java应用出现性能问题的因素非常多，例如
  - 线程控制
  - 磁盘读写
  - 数据库访问
  - 网络I/O
  - 垃圾收集
  - ... 
- 想要定位这些问题，一款优秀的性能诊断工具必不可少。

------

- 简单命令行工具
  - jdk的bin目录中还有一系列辅助工具。这些辅助工具用来获取目标JVM不同方面、不同层次的信息，帮助开发人员很好的解决Java应用程序的一些疑难杂症。

    ![jvm3-1.png](./image/jvm3-1.png)

  - exe执行文件类似快捷键方式，具体程序信息在 `tools.jar`下
  - 这些命令对应的源码地址：[源码](https://hg.openjdk.java.net/jdk/jdk11/file/1ddf9a99e4ad/src/jdk.jcmd/share/classes/sun/tools)

## 2.2. jps:查看正在运行的java进程

### 2.2.1. 基本说明

- jps：Java Process Status
  - 显示指定系统内所有的HotSpot虚拟机进程（查看虚拟机进程信息），可用于查询正在运行的虚拟机进程。
  - 对于本地虚拟机进程来说，**进程的本地虚拟机ID与操作系统的进程ID是一致的**，是唯一的。

- 示例

  ```java
  public class ScannerTest {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          String info = scanner.next();
      }
  }
  ```

  ![jvm3-3.png](./image/jvm3-3.png)

  > 另外注意：每次jps执行都是一个新的进程

### 2.2.2. 语法与参数

- jps的进本语法：`jps [options] [hostid]`

- **[options]：**
  - -q：仅仅显示LVMID（local virtual machine id），即本地虚拟机唯一id。不显示主类的名称等。
  - -l：输出应用程序主类的全类名 或 如果执行的是jar包，则输出jar包的完整路径
  - -m：输出虚拟机进程启动时传递给主类main()的参数
  - -v：列出虚拟机进程启动时的JVM参数。比如：`-Xms100m -Xmx100m`是启动程序指定的JVM参数

  > 说明：以上参数可以综合使用。

- **[hostid]：**
  - RMI注册表中注册的主机名
  - 如果想要**远程监控主机上的java程序，需要安装jstatd**
  - 对于具有更严格的安全实践的网络场所而言，可以使用一个自定义的策略文件来显式对特定的可信主机或网络的访问，尽管 **这种技术很容易受到IP地址欺诈攻击**
  - 如果安全问题无法使用一个定制的策略文件来处理，那么最安全的操作是不运行jstatd服务器，而是本地使用jstat和jps工具。

- 注意
  - 如果某Java进程关闭了默认开启的`-XX UsePerfData`参数（即使用参数-XX:-UsePerfData），那么jps命令（以及下面介绍的jstat）将无法探知该Java进程

- 示例

  <details>
  <summary style="color:red;">展开</summary>

  - 示例1

    ![jvm3-4.png](./image/jvm3-4.png)

  - 示例2
    - 在IDEA中配置参数如下：

      ![jvm3-5.png](./image/jvm3-5.png)

    - 执行：

      ```bash
      jps -m > a.txt
      ```

    - 生成文件的内容如下，可以看到刚才输入的参数

      ![jvm3-6.png](./image/jvm3-6.png)

  - 示例3
    - 在IDEA中配置参数如下：

      ![jvm3-7.png](./image/jvm3-7.png)

    - 执行：

      ```bash
      jps -v > b.txt
      ```

    - 生成文件的内容如下，可以看到刚才输入的参数

      ![jvm3-8.png](./image/jvm3-8.png)
  </details>

## 2.3. jstat:查看jvm统计信息

### 2.3.1. 基本说明

- jstat（JVM Statistics Monitoring Tool）：用于监视虚拟机各种运行状态信息的命令工具。它可以显示本地或者远程虚拟机中的类装载、内存、垃圾收集、JIT编译等运行数据。
- 在没有GUI图形界面，只提供了纯文本控制台环境的服务器上，它将是运行期间定位虚拟机性能问题的首选工具。常用于检测**垃圾回收**问题以及**内存泄露**问题。
- [官方文档](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html)

### 2.3.2. 基本语法

- 基本语法
  - options参数，用来指定展示哪方面的信息
  - interval参数：统计数据的周期
  - count参数：总查询次数
  - -t 参数：添加timestamp信息
  - -h 参数： 输出行信息

  ```bash
  jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
  ```
  ```bash
  Usage: jstat -help|-options
        jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]

  Definitions:
    <option>      An option reported by the -options option
    <vmid>        Virtual Machine Identifier. A vmid takes the following form:
                      <lvmid>[@<hostname>[:<port>]]
                  Where <lvmid> is the local vm identifier for the target
                  Java virtual machine, typically a process id; <hostname> is
                  the name of the host running the target Java virtual machine;
                  and <port> is the port number for the rmiregistry on the
                  target host. See the jvmstat documentation for a more complete
                  description of the Virtual Machine Identifier.
    <lines>       Number of samples between header lines.
    <interval>    Sampling interval. The following forms are allowed:
                      <n>["ms"|"s"]
                  Where <n> is an integer and the suffix specifies the units as
                  milliseconds("ms") or seconds("s"). The default units are "ms".
    <count>       Number of samples to take before terminating.
    -J<flag>      Pass <flag> directly to the runtime system.
  ```

  ![jvm3-9.png](./image/jvm3-9.png)

- 说明与示例

  ```java
  public class ScannerTest {
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
          String info = scanner.next();
      }
  }
  ```

  ![jvm3-10.png](./image/jvm3-10.png)

### 2.3.3. 所有参数

#### 2.3.3.1. options

- 测试代码

  ```java
  /**
    * -Xms60m -Xmx60m -XX:SurvivorRatio=8
    */
  public class GCTest {
      public static void main(String[] args) {
          ArrayList<byte[]> list = new ArrayList<>();
  
          for (int i = 0; i < 1000; i++) {
              byte[] arr = new byte[1024 * 100];  // 100KB
              list.add(arr);
              try {
                  Thread.sleep(120);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

> **类装载相关的：**

- -class：显示ClassLoader的相关信息：类的装载、卸载数量、总空间、类装载所消耗的时间等

 > **垃圾回收相关的：**

- -gc：显示与GC相关的堆信息。包括Eden区，两个Survivor区、老年代、永久代的用量、已用空间、GC时间合计等信息。

  ![jvm3-11.png](./image/jvm3-11.png)

  ![jvm3-12.png](./image/jvm3-12.png)

- -gccapacity：显示内容与-gc基本相同，但输出**主要关注Java堆各个区域使用的最大、最小空间**。

- -gcutil：显示内容与-gc基本相同，但输出**主要关注已使用空间占总空间的百分比**。

  ![jvm3-13.png](./image/jvm3-13.png)

- -gccause：与-gcutil功能一样，但是**会额外输出导致最后一次或当前正在发生的GC产生的原因**。

  ![jvm3-14.png](./image/jvm3-14.png)

- -gcnew：显示新生代GC状况

- -gcnewcapacity：显示内容与-gcnew基本相同，输出主要关注使用到的最大、最小空间。

- -gcold：显示老年代GC状况

- -gcoldcapacity：显示内容与-gcold基本相同，输出主要关注使用到的最大、最小空间。

- -gcpermcapacity：显示永久代用到的最大、最小空间。

> **JIT相关的：**

- -compiler：显示JIT编译器编译过的方法、耗时等信息
- -printcompilation：输出已被JIT编译的方法

![jvm3-15.png](./image/jvm3-15.png)

#### 2.3.3.2. 展示选项

- interval参数:用于指定输出统计数据的周期，单位为毫秒。即：查询间隔

- count参数:用于指定查询的总次数

- -t参数:可以在输出信息前面加上一个Timestamp列，显示程序的运行时间。单位：秒

- -h参数:可以在周期性数据输出时，输出多少行数据后输出一个表头信息

### 2.3.4. 经验之谈

- OOM隐患
  - 通过`-t`和`GCT`:
  - 比较Java进程的启动时间以及总GC时间（GCT列），或者两次测量的时间间隔以及总GC时间的增量，来得出GC时间栈运行时间的比例
  - 如果该比例超过20%，则说明目前堆的压力较大；如果该比例超过90%，则说明堆里几乎没有可用空间，随时都可能抛出OOM异常。

- 内存泄漏。
  - 第1步：在长时间运行的Java程序中，我们可以运行jstat命令连续获取多行性能数据，并取这几行数据中的OU列（即已占用的老年代内存）的最小值。
  - 第2步：
    - 然后，我们每隔一段较长的时间重复一次上述操作，来获取多组OU最小值
    - 如果这些值呈现上涨趋势，则说明该Java程序的老年代内存已使用量不断上涨，这意味着无法回收的对象在不断增加，因此有可能存在内存泄露。

  > 待做，shell脚本检测内存泄漏+报错

## 2.4. jinfo:实时查看和修改JVM配置参数

### 2.4.1. 基本说明

- jinfo（Configuration Info For Java）：查看虚拟机配置参数信息，也可以用于调整虚拟机的配置参数
- 在很多情况下，Java应用程序不会指定所有的Java虚拟机参数
  - 而此时，开发人员可能不知道某一个具体的Java虚拟机参数默认值
  - 在这种情况下，可能需要查找文档获取某个参数的默认值。这个查找过程可能是非常艰难的
  - 但是有了jinfo工具，开发人员可以很方便地找到Java虚拟机参数的当前值。
  - 同时也可以实时修改一些参数的值

- [官方文档](https://docs.oracle.com/en/java/javase/11/tools/jinfo.html)

### 2.4.2. 语法与参数

#### 2.4.2.1. 基本语法与参数

  ```bash
  jinfo [option] pid  # pid：进程的ID，必须加上
  ```

  ```
  Usage:
      jinfo [option] <pid>
          (to connect to running process)
      jinfo [option] <executable <core>
          (to connect to a core file)
      jinfo [option] [server_id@]<remote server IP or hostname>
          (to connect to remote debug server)

  where <option> is one of:
      -flag <name>         to print the value of the named VM flag
      -flag [+|-]<name>    to enable or disable the named VM flag
      -flag <name>=<value> to set the named VM flag to the given value
      -flags               to print VM flags
      -sysprops            to print Java system properties
      <no option>          to print both of the above
      -h | -help           to print this help message
  ```

- option列表：

  | 选项                    | 选项说明                                                                                    |
  | ----------------------- | ------------------------------------------------------------------------------------------- |
  | 查看： no option        | 输出全部参数和系统属性                                                                      |
  | 查看：-flag name        | 输出对应名称的参数                                                                          |
  | 查看： -flags           | 输出全部赋过值的参数                                                                        |
  | 查看： -sysprops        | 输出系统属性，可以由 System.getProperties()取得                                             |
  | 修改： -flag [±]name    | 针对 boolean 类型，开启或者关闭对应名称的参数， <br />只有被标为 manageable 的参数才可以被动态修改 |
  | 修改： -flag name=value | 针对非 boolean 类型，设置对应名称的参数                                                     |

#### 2.4.2.2. 示例：查看配置参数默认值

![jvm3-18.png](./image/jvm3-18.png)

![jvm3-19.png](./image/jvm3-19.png)

#### 2.4.2.3. 示例：修改配置参数值

> jinfo不仅可以查看运行时某一个Java虚拟机的实际取值，甚至可以在运行时修改部分参数，并使之立即生效。

- 注意：并非所有的参数都支持动态修改。参数只有被标记为manageable的flag可以实时修改。其实，这个修改能力是极其有限的。

  ```bash
  # 可以查看被标记为manageable的参数
  java -XX:+PrintFlagsFinal -version | grep manageable
  ```

![jvm3-20.png](./image/jvm3-20.png)

![jvm3-21.png](./image/jvm3-21.png)

### 2.4.3. 拓展:查看所有jvm参数与值

```bash
  # 查看所有JVM参数启动的初始值
  java -XX:+PrintFlagsInitial
  # 查看所有JVM参数的最终值
  java -XX:+PrintFlagsFinal
  # 查看哪些已经被用户或者JVM设置过的详细的XX参数的名称和值
  java -XX:+PrintCommandLineFlags
```

## 2.5. jmap:导出内存映像文件&内存使用情况

### 2.5.1. 基本说明

- jmap（JVM Memory Map）：
  - 作用一方面是获取dump文件（堆转储快照文件，二进制文件）
  - 另一方面是获取目标Java进程的内存相关信息，包括Java堆各区域的使用情况、堆中对象的统计信息、类加载信息等。
- 开发人员可以在控制台中输入命令`jmap -help`查阅jmap工具的具体使用方式和一些标准选项配置
- [官方文档](https://docs.oracle.com/en/java/javase/11/tools/jmap.html)

### 2.5.2. 语法与参数

- 基本语法

  ```bash
  jmap [option] <pid>
  jmap [option] <executable <core>
  jmap [option] [server_id@]<remote server IP or hostname>
  ```
  ```bash
  Usage:
      jmap [option] <pid>
          (to connect to running process)
      jmap [option] <executable <core>
          (to connect to a core file)
      jmap [option] [server_id@]<remote server IP or hostname>
          (to connect to remote debug server)

  where <option> is one of:
      <none>               to print same info as Solaris pmap
      -heap                to print java heap summary
      -histo[:live]        to print histogram of java object heap; if the "live"
                          suboption is specified, only count live objects
      -clstats             to print class loader statistics
      -finalizerinfo       to print information on objects awaiting finalization
      -dump:<dump-options> to dump java heap in hprof binary format
                          dump-options:
                            live         dump only live objects; if not specified,
                                          all objects in the heap are dumped.
                            format=b     binary format
                            file=<file>  dump heap to <file>
                          Example: jmap -dump:live,format=b,file=heap.bin <pid>
      -F                   force. Use with -dump:<dump-options> <pid> or -histo
                          to force a heap dump or histogram when <pid> does not
                          respond. The "live" suboption is not supported
                          in this mode.
      -h | -help           to print this help message
      -J<flag>             to pass <flag> directly to the runtime system
  ```

- [option]。 注意：这些参数和linux下输入显示的命令多少会有些不同，包括也受jdk版本的影响。

  | 选项           | 作用                                                                                                 |
  | -------------- | ---------------------------------------------------------------------------------------------------- |
  | **-dump**      | 生成 dump 文件，`-dump:live`，只保存堆中存活的对象                                                   |
  | **-heap**      | 输出整个堆空间的统计信息，包括 GC 的使用、堆配置信息，以及内存的使用信息等                           |
  | **-histo**     | 输出堆空间中的对象的统计信息，包括类、实例数量和合计容量。 <br />`-histo:live`只统计堆中的存活对象   |
  | -finalizerinfo | 仅显示在 F-Queue 中等待 Finalizer 方法的对象。 <br />**仅 linux/solaris 平台有效**                   |
  | -permstat      | 以 ClassLoader 为统计口径输出**永久代**的内存状态信息。 <br />**仅 linux/solaris 平台有效**          |
  | -F             | 当虚拟机进程对-dump 选项没有任何响应时，强制执行生成 dump 文件。 <br />**仅 linux/solaris 平台有效** |
  | `-J <flag>`    | 传递参数给 jmap 命令启动的 jvm                                                                       |

### 2.5.3. 具体使用示例

#### 2.5.3.1. 使用1：导出内存映像文件

> **说明**

- 注意：
  - （1）通常在写Heap Dump文件前会触发一次Full GC，所以heap dump文件里保存的都是Full GC后留下的对象信息。
  - （2）由于生成dump文件比较耗时，因此大家需要耐心等待，尤其是大内存镜像生成的dump文件则需要耗费更长的时间来完成。

- 示例代码

  ```java
  /**
   * -Xms60m -Xmx60m -XX:SurvivorRatio=8
   */
  public class GCTest {
      public static void main(String[] args) {
          ArrayList<byte[]> list = new ArrayList<>();
  
          for (int i = 0; i < 1000; i++) {
              byte[] arr = new byte[1024 * 100];  // 100KB
              list.add(arr);
              try {
                  Thread.sleep(60);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

> **分为两种导出的方式**

- 手动的方式

  ```bash
  # format=b 是为了jmap生成的文件能和hprof文件匹配起来

  # 文件会越来越大
  jmap -dump:format=b,file=<filename.hprof> <pid>
  # 因为只抓取存活对象，未来得及回收的对象不会抓取，可能会越来越小
  jmap -dump:live,format=b,file=<filename.hprof> <pid>
  ```

  ![jvm3-23.png](./image/jvm3-23.png)

  - 使用注意
    - 生产环境中的内存映像文件可能会很大
    - 加上`:live`比较好

- 自动的方式
  - 当程序发生OOM退出系统时，一些瞬时信息都随着程序的终止而消失，而OOM问题往往比较困难或者耗时。
  - 此时若能在OOM时，自动导出dump文件就显得非常迫切

  ```bash
  # 当程序发生OOM时，导出应用程序的当前堆快照
  -XX:+HeapDumpOnOutOfMemoryError
  # 可以指定堆快照的保存位置
  -XX:HeapDumpPath=<filename.hprof>
  ```
  ```bash
  # 比如：
  -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:\heap\oom.hprof
  ```
  - 操作示例

    <details>
    <summary style="color:red;">展开</summary>

    - 在IDEA中配置参数如下，执行程序，等到OOM是则会自动生成dump文件

      ![jvm3-24.png](./image/jvm3-24.png)

    - 生成的oom.hprof文件如下：

      ![jvm3-25.png](./image/jvm3-25.png)
    </details>

#### 2.5.3.2. 使用2：显示堆内存相关信息

- `jmap -heap pid`
  - 获取**某一时刻**整个堆空间的统计信息
  - 包括GC的使用、堆配置信息，以及内存的使用信息等

  ![jvm3-26.png](./image/jvm3-26.png)

- `jmap -histo pid`
  - 获取某一时刻堆空间中的对象的统计信息
  - 包括类、实例数量和合计容量

  ![jvm3-27.png](./image/jvm3-27.png)

#### 2.5.3.3. 使用3：其他作用

- jmap -permstat pid
  - 查看系统的ClassLoader信息
- jmap -finalizerinfo
  - 查看堆积在finalizer队列中的对象

### 2.5.4. 注意

- 由于jmap将访问堆中的所有对象，为了保证在此过程中不被应用线程干扰，jmap需要借助安全点机制，让所有线程都停留在不改变堆中数据的状态
  > 安全点机制在GC是有提
- 也就是说，由jmap导出的堆快照必定是安全点位置的。 **这可能导致基于该堆快照的分析结果存在偏差**
- 举个例子，假设在编译生成的机器码中，某些对象的生命周期在两个安全点之间，那么`:live`选项将无法探知到这些对象。
- 另外，如果某个线程长时间无法跑到安全点，jmap将一直等下去。

## 2.6. jhat:JDK自带堆分析工具

> 基本不会使用

### 2.6.1. 基本说明

- jhat（JVM Heap Analysis Tool）：
  - Sun JDK提供的jhat命令与jmap命令搭配使用，用于分析jmap生成的heap dump文件（堆转储快照）。
  - jhat内置了一个微型的HTTP/HTML服务器，生成dump文件的分析结果后，用户可以在浏览器中分析查看结果（分析虚拟机转储快照信息）。
- 使用了jhat命令，就启动了一个http服务，端口是7000，即`http://localhost:7000/`，既可以在服务器里分析。
- 说明：**jhat命令在JDK9，JDK10中已经被删除，官方建议使用VisualVM代替**

### 2.6.2. 语法与参数

```
$ jhat -h
Usage:  jhat [-stack <bool>] [-refs <bool>] [-port <port>] [-baseline <file>] [-debug <int>] [-version] [-h|-help] <file>

        -J<flag>          Pass <flag> directly to the runtime system. For
                          example, -J-mx512m to use a maximum heap size of 512MB
        -stack false:     Turn off tracking object allocation call stack.
        -refs false:      Turn off tracking of references to objects
        -port <port>:     Set the port for the HTTP server.  Defaults to 7000
        -exclude <file>:  Specify a file that lists data members that should
                          be excluded from the reachableFrom query.
        -baseline <file>: Specify a baseline object dump.  Objects in
                          both heap dumps with the same ID and same class will
                          be marked as not being "new".
        -debug <int>:     Set debug level.
                            0:  No debug output
                            1:  Debug hprof file parsing
                            2:  Debug hprof file parsing, no server
        -version          Report version number
        -h|-help          Print this help and exit
        <file>            The file to read

For a dump file that contains multiple heap dumps,
you may specify which dump in the file
by appending "#<number>" to the file name, i.e. "foo.hprof#3".

All boolean options default to "true"
```

![jvm3-36](./image/jvm3-36.png)

- 示例

  <details>
  <summary style="color:red;">展开</summary>

  - 启动服务器
    ![jvm3-30.png](./image/jvm3-30.png)
  - 关于OQL语句：
    ![jvm3-31.png](./image/jvm3-31.png)
  </details>

## 2.7. jstack

### 2.7.1. 基本说明

- jstack（JVM Stack Trace）：
  - 用于生成虚拟机指定进程当前时刻的线程快照（虚拟机堆栈跟踪）。
  - 线程快照就是当前虚拟机内指定进程的每一条线程正在执行的方法堆栈的集合。
- 生成线程快照的作用：
  - 可用于定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待问题。这些都是导致线程长时间停顿的常见原因
  - 也可以查看同步状态
  - 当线程出现停顿时，就可以用jstack显示各个线程调用的堆栈情况。
- 在thread dump中，要留意下面几种状态
  - **死锁，Deadlock（重点关注）**
  - **等待资源，Waiting on condition（重点关注）**
  - **等待获取监视器，Waiting on monitor entry（重点关注）**
  - **阻塞，Blocked（重点关注）**
  - 执行中，Runnable
  - 暂停，Suspended

### 2.7.2. 语法与参数

- 基本语法与参数

  ```
  Usage:
      jstack [-l] <pid>
          (to connect to running process)
      jstack -F [-m] [-l] <pid>
          (to connect to a hung process)
      jstack [-m] [-l] <executable> <core>
          (to connect to a core file)
      jstack [-m] [-l] [server_id@]<remote server IP or hostname>
          (to connect to a remote debug server)

  Options:
      -F  to force a thread dump. Use when jstack <pid> does not respond (process is hung)
      -m  to print both java and native frames (mixed mode)
      -l  long listing. Prints additional information about locks
      -h or -help to print this help message
  ```

- jstack远程管理的话，需要在远程程序的启动参数中增加：

  ```bash
  -Djava.rmi.server.hostname=......
  -Dcom.sun.management.jmxremote
  -Dcom.sun.management.jmxremote.port=8888
  -Dcom.sun.management.jmxremote.authenticate=false
  -Dcom.sun.management.jmxremote.ssl=false
  ```

- Java层面追踪当前进程中的所有的线程

  ```java
  public class AllStackTrace {
      public static void main(String[] args) {
          Map<Thread, StackTraceElement[]> all = Thread.getAllStackTraces();
          Set<Map.Entry<Thread, StackTraceElement[]>> entries = all.entrySet();
          for (Map.Entry<Thread, StackTraceElement[]> en : entries) {
              Thread t = en.getKey();
              StackTraceElement[] v = en.getValue();
              System.out.println("【Thread name is :" + t.getName() + "】");
              for (StackTraceElement s : v) {
                  System.out.println("\t" + s.toString());
              }
          }
      }
  }
  ```

### 2.7.3. 示例

<details>
<summary style="color:red;">代码展开</summary>

```java
/**
  * 演示线程的死锁问题
  */
public class ThreadDeadLock {

    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread() {
            @Override
            public void run() {

                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }

            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
</details>

![jvm3-32](./image/jvm3-32.png)

## 2.8. jcmd:多功能的命令行

### 2.8.1. 基本说明

- 在JDK1.7以后，新增了一个命令行工具jcmd
- 它是一个多功能的工具，**可以用来实现前面除了jstat之外所有命令的功能**。比如：
  - 导出堆，内存使用
  - 查看Java进程、导出线程信息
  - 执行GC、JVM运行时间等。
- jcmd拥有jmap的大部分功能，并且在Oracle的官方网站上页**推荐使用jcmd命令代替jmap命令**

### 2.8.2. 语法及参数

- 说明
  - `jcmd -h`:整体使用帮助
  - `jcmd <pid> help`:展示指定线程可执行的操作
  - `jcmd pid 具体命令` :执行具体命令

- 语法及参数
  ```
  Usage: jcmd <pid | main class> <command ...|PerfCounter.print|-f file>
    or: jcmd -l
    or: jcmd -h

    command must be a valid jcmd command for the selected jvm.
    Use the command "help" to see which commands are available.
    If the pid is 0, commands will be sent to all Java processes.
    The main class argument will be used to match (either partially
    or fully) the class used to start Java.
    If no options are given, lists Java processes (same as -p).

    PerfCounter.print display the counters exposed by this process
    -f  read and execute commands from the file
    -l  list JVM processes on the local machine
    -h  this help
  ```

- 示例

  ```bash
  # 使用如下命令可以替换jps，即列出所有的JVM进程，加不加-l运行结果一样
  jcmd -l
  # 针对指定的进程，列出支持的所有命令
  jcmd pid help
  # 显示指定进程的指令命令的数据
  jcmd pid 具体命令
  ```

  ![jvm3-34.png](./image/jvm3-34.png)

## 2.9. jstatd:远程主机信息收集

- 之前的命令值涉及到监控本机的Java应用程序，而在这些工具中，一些监控工具也支持对远程计算机的监控（如jps、jstat）。为了启用远程监控，则需要配合使用jstatd工具。
- 命令jstatd是一个**RMI服务端程序**，它的作用**相当于代理服务器**，建立本地计算机与远程监控工具的通信。jstatd服务器将本机的Java应用程序传递到远程计算机。

![jvm3-35.png](./image/jvm3-35.png)

# 3. JVM监控及诊断工具=GUI

> **吃透一两款就行**

## 3.1. 概述

- 命令行工具的局限性
  - 无法获取方法级别的分析数据，如方法间的调用关系、各方法的调用次数和调用时间（这对定位应用性能瓶颈至关重要）。
  - 要求用户登录到目标Java应用所在的宿主机上，使用起来不方便。
  - 分析数据通过终端输出，结果展示不够直观。

- **图形化综合诊断工具**
  - **JDK自带的工具**
    - jconsole：
      - JDK自带的可视化监视工具。查看Java应用程序的运行概况、监控堆信息、永久代（元空间）使用情况、类加载情况等。
      - 位置：jdk\bin\jconsole.exe
    - Visual VM：
      - Visual VM是一个工具，它提供了一个可视化界面，用于查看Java虚拟机上运行的基于Java技术的应用程序的详细信息。
      - 位置：jdk\bin\jvisualvm.exe，也可以单独安装
    - JMC：
      - Java Mission Control，内置Java Flight Recorder
      - 能够以极低的性能开销收集Java虚拟机的性能数据。
      - HotSpot合并JRocket时带过来的，一部分功能也放到了jcmd中
  - **第三方工具**
    - MAT：
      - MAT（Memory Analyzer Tool）是基于Eclipse的内存分析工具，是一个快速、功能丰富的Java heap分析工具，它可以帮助我们查找内存泄露和减少内存消耗。
      - 可以以Eclipse的插件形式安装，也可以单独安装
      - 老牌工具
    - JProfiler：
      - 商业软件，需要付费，功能强大。
      - 可以单独安装，然后集成到IDEA中
    - Arthas：
      - Alibaba开源的Java诊断工具。深受开发者喜爱。
    - Btrace：
      - Java运行时追踪工具。可以在不停机的情况下，跟踪执行的方法调用、构造函数和系统内存等信息。

## 3.2. JConsole

### 3.2.1. 基本概述

- 从Java5开始，是JDK中自带的java监控和管理控制台。
- 用于对JVM中内存、线程和类等的监控，是一个基于JMX（java management extensions）的GUI性能监控工具。
- [官方教程](https://docs.oracle.com/javase/7/docs/technotes/guides/management/jconsole.html)

### 3.2.2. 启动

- 执行jdk/bin下的 jconsole.exe 即可
- 也可以配置到环境变量中

- 启动页面

  ![jvm3-37.png](./image/jvm3-37.png)

### 3.2.3. 三种连接方式

- Local
  - 使用 jConsole连接一个正在本地系统运行的JVM，并且执行程序和运行 jConsole的需要时同一个用户。
  - jConsole使用文件系统的授权通过RMI连接器连接到平台的MBean服务器上。
  - 这种从本地连接的监控能力只有Sun的JDK具有。

- Remote 
  - 使用下面的URL通过RMI连接器连接到一个JMX代理：`service:jmx:rmi:///jndi/rmi://hostName:portNum/jmsrmi`
  - jConsole为建立连接，需要在环境变量中设置mx.remote.credentials来指定用户名和密码，从而进行授权。

- Advanced
  - 使用一个特殊的URL链接JMX代理。
  - 一般情况使用自己定制的连接器而不是RMI提供的连接器来连接JMX代理，或者是一个使用JDK1.4的实现了JMX和JMX Remote的应用。

### 3.2.4. 主要作用

- 监控内存、监控线程、监控死锁、类加载与虚拟机信息

-  示例

  ```java
  /**
  * -Xms600m -Xmx600m -XX:SurvivorRatio=8
  */
  public class HeapInstanceTest {
      byte[] buffer = new byte[new Random().nextInt(1024 * 100)];

      public static void main(String[] args) {
          try {
              Thread.sleep(3000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          ArrayList<HeapInstanceTest> list = new ArrayList<HeapInstanceTest>();
          while (true) {
              list.add(new HeapInstanceTest());
              try {
                  Thread.sleep(10);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

  <details>
  <summary style="color:red;">查看图片(去自己跑一下最好)</summary>

  ![jvm3-38.png](./image/jvm3-38.png)

  ![jvm3-39.png](./image/jvm3-39.png)

  > 内存区域会根据垃圾回收器的不同而改变

  ![jvm3-40.png](./image/jvm3-40.png)

  > 可检测死锁

  ![jvm3-41.png](./image/jvm3-41.png)

  ![jvm3-42.png](./image/jvm3-42.png)
  </details>

## 3.3. Visual VM(jvisualvm)

> 取代 JConsole，必须学习

### 3.3.1. 基本概述

- Visual VM是一个功能强大的多合一故障诊断和性能监控的可视化工具。
- 它集成了多个JDK命令行工具
  - 使用Visual VM可用于显示虚拟机进程及进程的配置和环境信息（jps，jinfo）
  - 监视应用程序的CPU、GC、堆、方法区及线程的信息（jstat、jstack）
  - 可以取代jConsole。
- 在JDK 6 Update 7之后，Visual VM便作为JDK的一部分发布（VIsualVM在JDK/bin目录下，jvisualvm）
  - Visual VM 和 JDK/bin目录下的 jvisualvm是一个东西。
  - 此外，Visual VM也可以作为独立的软件安装。

- [Visual VM网址](https://visualvm.github.io/index.html)

### 3.3.2. Visual VM 插件

- 说明:Visual VM的一大特点是支持插件扩展，并且插件安装非常方便

- Visual VM 功能扩展插件
  - 既可以通过离线下载文件*.nbm，然后再Plugin对话框的已下载页面下，添加已下载的插件
  - 也可以在可用插件页面下，在线安装插件
  - **（这里建议安装上：VisualGC）**
  - [插件地址](https://visualvm.github.io/pluginscenters.html)

- IDEA集成VisualVM Launcher插件

  <details>
  <summary style="color:red;">展开</summary>

  ![jvm3-43.png](./image/jvm3-43.png)

  > 重启后还需要做如下设置：

  ![jvm3-44.png](./image/jvm3-44.png)
  </details>

### 3.3.3. 连接方式

- 本地连接
- 远程连接
  - 确定远程服务器的ip地址
  - 添加JMX(通过JMX技术具体监控远端服务器哪个Java进程)
  - 修改bin/catalina.sh文件，连接远程的tomcat
  - 在.../conf中添加jmxremote.access和jmxremote.password文件
  - 将服务器地址改为公网ip地址
  - 设置阿里云安全策略和防火墙策略
  - 启动tomcat,查看tomcat启动日志和端口监听
  - JMX中输入端口号、用户名、密码登录

### 3.3.4. 主要功能

#### 3.3.4.1. 说明

- 生成/读取堆内存快照
- 查看JVM参数和系统属性
- 查看运行中的虚拟机进程
- 生成/读取线程快照
- 程序资源的实时监控
- 其他功能：JMX代理连接、远程环境监控、CPU分析和内存分析

#### 3.3.4.2. 示例

- 演示代码

  ```java
  /**
   * -Xms600m -Xmx600m -XX:SurvivorRatio=8
   */
  public class OOMTest {
      public static void main(String[] args) {
          ArrayList<Picture> list = new ArrayList<>();
          while (true) {
              try {
                  Thread.sleep(5);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              list.add(new Picture(new Random().nextInt(100 * 50)));
          }
      }
  }
  
  class Picture {
      private byte[] pixels;
  
      public Picture(int length) {
          this.pixels = new byte[length];
      }
  }
  ```

- **概览**

  <details>
  <summary style="color:red;">展开</summary>

  ![jvm3-46.png](./image/jvm3-46.png)

  ![jvm3-47.png](./image/jvm3-47.png)

  ![jvm3-48.png](./image/jvm3-48.png)

  ![jvm3-49.png](./image/jvm3-49.png)
  </details>

- **生成和查看堆dump文件**

  <details>
  <summary style="color:red;">展开</summary>

  ![jvm3-50.png](./image/jvm3-50.png)

  > 然后在快照上右键即可将快照（.hprof文件）保存到磁盘：

  ![jvm3-51.png](./image/jvm3-51.png)

  > 通过选择：文件---->装入，可以导入刚才保存的.hprof文件：

  ![jvm3-52.png](./image/jvm3-52.png)

  > 分析堆dump文件

  ![jvm3-53.png](./image/jvm3-53.png)
  </details>

- **生成和查看线程dump文件**

  <details>
  <summary style="color:red;">死锁示例代码与检测</summary>

  ```java
  /**
   * 演示线程的死锁问题
   */
  public class ThreadDeadLock {
  
      public static void main(String[] args) {
  
          StringBuilder s1 = new StringBuilder();
          StringBuilder s2 = new StringBuilder();
  
          new Thread() {
              @Override
              public void run() {
  
                  synchronized (s1) {
                      s1.append("a");
                      s2.append("1");
                      try {
                          Thread.sleep(100);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      synchronized (s2) {
                          s1.append("b");
                          s2.append("2");
                          System.out.println(s1);
                          System.out.println(s2);
                      }
                  }
              }
          }.start();
  
          new Thread(new Runnable() {
              @Override
              public void run() {
  
                  synchronized (s2) {
                      s1.append("c");
                      s2.append("3");
                      try {
                          Thread.sleep(100);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      synchronized (s1) {
                          s1.append("d");
                          s2.append("4");
                          System.out.println(s1);
                          System.out.println(s2);
                      }
                  }
              }
          }).start();
  
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }
  ```

  ![jvm3-54.png](./image/jvm3-54.png)
  </details>

- **CPU抽样和内存抽样**

  <details>
  <summary style="color:red;">展开</summary>

  ![jvm3-55.png](./image/jvm3-55.png)

  ![jvm3-56.png](./image/jvm3-56.png)
  </details>

## 3.4. eclipse MAT

> **dump文件分析，就用这个**

### 3.4.1. 概述

- MAT（Memory Analyzer Tool）工具是一款功能强大的Java堆内存分析工具。可以用于查找内存泄露以及查看内存消耗情况。
- MAT是基于Eclipse开发的，不仅可以单独使用，还可以作为插件的形式嵌入在Eclipse中使用。
- [下载地址](https://www.eclipse.org/mat/downloads.php)，下载之后解压可以直接使用，不用安装
- 两点说明
  - 说明1：
    - MAT不是一个万能工具，它并不能处理所有类型的堆转储文件。
    - 但是比较主流的厂家和格式，例如Sun，HP，SAP所采用的HPROF二进制堆转储文件，以及IBM的PHD堆转储文件等都能被很好的解析。
  - 说明2：
    - MAT最吸引人的还是能够快速地为开发人员生成**内存泄露报表**，方便定位问题和分析问题。
    - 虽然MAT有如此强大的功能，但是内存分析也没有简单到一键完成的程度，很多内存问题还是需要我们从MAT展现给我们的信息中通过经验和直觉来判断才能发现。

### 3.4.2. 获取dump文件(小结)

- dump文件内容

  ```
  MAT可以分析heap dump文件。
  进行内存分析时，只要获得了反应当前设备内存映像的hprof文件，通过MAT打开就可以直观的看到当前的内存信息。
  一般来说，这些内存信息包含：
  ```

  - 所有的对象信息，包括对象实例、成员变量、存储于栈中的基本数据类型值和存储于堆中的其他对象的引用值。
  - 所有的类信息，包括classloader、类名称、父类、静态变量等。
  - GCRoot到所有的这些对象的引用路径
  - 线程信息，包括线程的调用栈以及此线程的线程局部变量（TLS）

- 如何获取dump文件
  - 方法一：jmap工具生成，可以生成任意一个java进程的dump文件；
  - 方法二：通过配置JVM参数生成。
    - 选项`-XX:+HeapDumpOnOutOfMemoryError`或`-XX:+HeapDumpBeforeFullGC`
    - 选项`-XX:HeapDumpPath`所代表的含义就是当程序出现OutofMemory时，将会在相应的目录下生成一份dump文件。如果不指定选项`-XX:HeapDumpPath`则在当前目录下生成dump文件。
  - 方法三：使用VisualVM可以导出堆dump文件
  - 方法四：
    - 使用MAT既可以打开一个已有的快照，也可以通过MAT直接从活动Java程序中导出堆快照
    - 该功能将借助jps列出当前正在运行的Java进程，以供选择并获取快照。

### 3.4.3. 概念补充

#### 3.4.3.1. 深堆,浅堆,对象实际大小

>  **浅堆：**

- 浅堆（Shallow Heap）是指一个对象所消耗的内存。
- 在32位系统中，一个对象引用会占据4个字节，一个int类型会占据4个字节，long型变量占据8个字节。
- 根据堆快照格式不同，对象的大小可能会向8字节进行对齐。
- 以String为例：
  - 2个int值共占8个字节
  - 对象引用占用4字节，对象头占8字节
  - 合计20字节，向8字节对齐，故占24字节。（jdk7中）
  - 这24字节为String对象的浅堆大小。
  - 它与String的value实际取值无关，无论字符串长度如何，**浅堆大小始终是24字节**。

  | int  | hash32 | 0     |
  | ---- | ------ | ----- |
  | int  | hash   | 0     |
  | ref  | value  | hello |

> **深堆：**

- 保留集（Retained Set）：
  - 对象A的保留集指当对象A被垃圾回收后，可以被释放的所有对象集合（包括对象A本身）
  - 即对象A的保留集可以被认为是**只能通过**对象A被直接或者间接访问到的所有对象的集合。
  - 通俗的说，就是指仅被对象A所持有的对象的集合。

- 深堆（Retained Heap）：
  - 深堆是指对象的保留集中所有的对象的浅堆大小之和。
  - 注意：浅堆是指对象本身占用的内存，不包括其内部引用对象的大小。
  - 一个对象的深堆指**只能通过该对象**访问到的（直接或者间接）所有对象的浅堆之和
  - **即对象被回收后，可以释放的真实空间**

> **补充：对象的实际大小**

- 对象的实际大小定义为一个对象**所能触及的**所有对象的浅堆大小之和
- 也就是通常意义上我们所说的对象的大小。
- 与深堆相比，似乎这个在日常开发中更为直观和被人接收
- **但实际上，这个概念和垃圾回收无关**。

> **示例图**

![jvm3-60.png](./image/jvm3-60.png)

- 那么对象**A的浅堆大小**只是A本身，不包含C和D
- 而**A的实际大小**为A、C、D三者之和
- 而**A的深堆大小**为A和D之和
  > 这是因为由于对象C还可以通过对象B访问到，因此不再对象A的深堆范围内。

> **图例2**

- GC Roots直接引用了A和B两个对象。

  ![jvm3-61.png](./image/jvm3-61.png)

  - A对象的深堆大小 = A对象的浅堆大小
  - B对象的深堆大小 = B对象的浅堆大小 + C对象的浅堆大小

- 如果不包括GC Roots指向D对象的引用

  ![jvm3-62.png](./image/jvm3-62.png)

  - B对象的深堆大小 = B对象的浅堆大小 + C对象的浅堆大小 + D对象的浅堆大小

> **通过案例分析深堆和浅堆的大小**

- 代码：
  ```java
  /**
  * 有一个学生浏览网页的记录程序，它将记录 每个学生访问过的网站地址。
  * 它由三个部分组成：Student、WebPage和StudentTrace三个类
  * -XX:+HeapDumpBeforeFullGC -XX:HeapDumpPath=d:\student.hprof
  */
  public class StudentTrace {
      
      static List<WebPage> webpages = new ArrayList<>();
      
      public static void createWebPages() {
          for (int i = 0; i < 100; i++) {
              WebPage wp = new WebPage();
              wp.setUrl("http://www." + Integer.toString(i) + ".com");
              wp.setContent(Integer.toString(i));
              webpages.add(wp);
          }
      }
  
      public static void main(String[] args) {
          
          createWebPages();  // 创建了100个网页
          // 创建3个学生对象
          Student st3 = new Student(3, "Tom");
          Student st5 = new Student(5, "Jerry");
          Student st7 = new Student(7, "Lily");
  
          for (int i = 0; i < webpages.size(); i++) {
              if (i % st3.getId() == 0) st3.visit(webpages.get(i));
              if (i % st5.getId() == 0) st5.visit(webpages.get(i));
              if (i % st7.getId() == 0) st7.visit(webpages.get(i));
          }
          webpages.clear();
          System.gc();
      }
  }
  
  // Student浅堆大小：4B(id) + 4B(name) + 4B(history) + 8B(对象头) = 20B --> 填充4B --> 24B
  class Student {
      private int id;
      private String name;
      private List<WebPage> history = new ArrayList<>();
  
      public Student(int id, String name) {
          super();
          this.id = id;
          this.name = name;
      }
  
      public int getId() { return id; }
      public void setId(int id) { this.id = id; }
      public String getName() { return name; }
      public void setName(String name) { this.name = name; }
      public List<WebPage> getHistory() { return history; }
      public void setHistory(List<WebPage> history) { this.history = history; }
      public void visit(WebPage wp) { if (wp != null) history.add(wp); }
  }
  
  class WebPage {
      private String url;
      private String content;
  
      public String getUrl() { return url; }
      public void setUrl(String url) { this.url = url; }
      public String getContent() { return content; }
      public void setContent(String content) { this.content = content; }
  }
  ```

- 深堆示例：
  ![jvm3-63.png](./image/jvm3-63.png)

- 下面以Lily为例分析深堆的大小是如何计算出来的

  ![jvm3-64.png](./image/jvm3-64.png)

  ![jvm3-65.png](./image/jvm3-65.png)

#### 3.4.3.2. 支配树

> 支配树的概念来自于图论

- MAT提供了一个称为支配树（Dominator Tree）的对象图
  - 支配树体现了对象实例间的支配关系
  - 在对象引用图中，所有指向对象B的路径都经过对象A，则认为**对象A支配对象B**
  - 如果对象A是离对象B最近的一个支配对象，则认为对象A为对象B的**直接支配者**
  - 支配树是基于对象间的引用图所建立的，它有以下基本性质：
    - 对象A的子树（所有被对象A支配的对象的集合）表示对象A的保留集（retained set），即深堆。
    - 如果对象A支配对象B，那么对象A的直接支配者也支配对象B。
    - 支配树的边与对象引用图的边不直接对应。

- 示例
  - 左图表示对象引用图，右图表示左图所对应的支配树

    ![jvm3-66.png](./image/jvm3-66.png)

  - 对象A和B由根对象直接支配
  - 由于在到对象C的路径中，可以经过A，也可以经过B，因此对象C的直接支配者也是根对象。
  - 对象F和对象D相互引用，因为到对象F的所有路径必然经过对象D，因此，对象D是对象F的直接支配者
  - 而到对象D的所有路径中，必然经过对象C，即使是从对象F到对象D的引用，从根节点出发，也是经过对象C的，所以，对象D的直接支配者为对象C
  - 同理，对象E支配对象G。
  - 到达对象H可以通过对象D，也可以通过对象E，因此对象D和E都不能支配对象H，而经过对象C既可以到达对象D也可以到达E，因此对象C为对象H的直接支配者。

- 在MAT中，单击工具栏上的对象支配按钮，可以打开对象支配树视图。

  ![jvm3-67.png](./image/jvm3-67.png)

  - 该截图显示上面代码示例中部分学生Lily的history队列的直接支配对象
  - 即当Lily对象被回收，也会一并回收的所有对象
  - 显然能被3或者5整除的网页不会出现在该列表中，因为他们同时被另外两名学生对象所引用。

  ![jvm3-68.png](./image/jvm3-68.png)

- 问题: <p style="color:red;"> 软，弱，虚引用表现？ </p>

### 3.4.4. 分析dump文件

#### 3.4.4.1. 示例代码

```java
/**
  * -Xms600m -Xmx600m -XX:SurvivorRatio=8
  */
public class OOMTest {
    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(100 * 50)));
        }
    }
}

class Picture {
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
```

#### 3.4.4.2. histogram

- 展示了各个类的实例数目以及这些实例的Shallow heap或Retainedheap的总和，
- 在这个界面可以进行：分组，排序，写正则表达式，两个.hprof的对比，查看对象被谁引用，…

#### 3.4.4.3. thread overview

- 查看系统中的Java线程、查看局部变量的信息。获取对象相互引用关系

- **注意：** 
  - thread overview中显示的才是引用关系
  - 支配树中显示的是支配关系

#### 3.4.4.4. 获取对象引用关系

### 3.4.5. 案例解析-tomcat堆溢出分析

- 查看报告

  ![jvm3-69.png](./image/jvm3-69.png)

  ![jvm3-70.png](./image/jvm3-70.png)

- session对象，它占用了17MB空间

  ![jvm3-71.png](./image/jvm3-71.png)

- 可以看到sessions对象为ConcurrentHashMap，其内部分为16个Segment。从深堆大小看，每个Segment都比较平均，大约为1MB，合计17MB。

  ![jvm3-72.png](./image/jvm3-72.png)

- 内部value，都是Session

  ![jvm3-73.png](./image/jvm3-73.png)

- 当前堆中含有9941个session，并且每一个session的深堆为1592字节，合计越15MB，达到当前堆大小的50%。

- QQL查询

  ![jvm3-74.png](./image/jvm3-74.png)

- 查看属性：创建和结束时间

  ![jvm3-75.png](./image/jvm3-75.png)

- 根据当前的session综述，可以计算每秒的平均压力：9941/((1403324677648-1403324645728)/1000) = 311次/秒。

  ![jvm3-76.png](./image/jvm3-76.png)

- 由此推断
  - 在发生Tomcat堆溢出时，Tomcat在连续的30秒的时间内，平均接收了约311次不同客户端的请求
  - 创建了合计9941个session。导致出现堆溢出

### 3.4.6. 补充：内存泄漏

#### 3.4.6.1. 概述

> **基本说明**

- 何为内存泄露（memory leak）
  - 可达性分析算法来判断对象是否是不再使用的对象，本质上是判断一个对象是否还被引用
  - 那么对于这种情况，由于代码的实现不同就会出现很多内存泄露问题（让JVM误认为此对象还在引用中，无法回收，造成内存泄露）。

  ![jvm3-57.png](./image/jvm3-57.png)

- 内存泄露（memory leak）的理解

  - **严格来说** ， 
    - **只有对象不会再被程序用到了，但是GC用不能回收它们的情况，才叫内存泄露。**
  - **宽泛意义上** ，
    - **但实际情况很多时候一些不太好的实践（或疏忽）会导致对象的生命周期变得很长甚至导致OOM**
    - 也可以叫做内存泄露

> **示例说明**

- 对象X引用对象Y，X的生命周期比Y的生命周期长
- 那么当Y生命周期结束的时候，X依然引用着Y，这时候，垃圾回收是不会回收对象Y的
- 如果对象X还引用着生命周期比较短的A、B、C，对象A又引用着对象a、b、c
- 这样就可能造成大量无用的对象不能被回收，进而占据了内存资源，造成内存泄露，直至内存溢出。

![jvm3-58.png](./image/jvm3-58.png)

> **内存泄露与内存溢出的关系**

- 内存泄露（memory leak） 申请了内存用完了不释放
- 内存溢出（out of memory） 申请内存时，没有足够的内存可以使用；
- 内存泄露和内存溢出的关系：内存泄露的增多，最终导致内存溢出。

> **泄露的分类**

- **经常发生**：发生内存泄露的代码会被多次执行，每次执行，泄露一块内存；
- **偶然发生**：在某些特定情况下才会发生；(比如finally)
- **一次性**：内存泄露的方法只会被执行一次；
- **隐式泄露**：一直占着内存不释放，知道执行结束；严格的说这个不算内存泄露，因为最终释放掉了，但是如果执行时间特别长，也可能导致内存耗尽。

#### 3.4.6.2. 内存泄漏8种情况

> **1.静态集合类**

- 说明
  - 静态集合类，如HashMap、LinkedList等等
  - 如果这些容器为静态的，那么他们的生命周期与JVM程序一直，则容器中的对象在程序结束之前不会被释放，从而造成内存泄露
  - 简单而言，长生命周期的对象持有短生命周期对象的引用，尽管短生命周期的对象不再被使用，但是因为长生命周期对象持有它的引用而导致不能被回收。

- 示例代码

  <details>
  <summary style="color:red;">展开</summary>

   ```java
   public class MemeoryLeak {
       static List list = new ArrayList<>();
       
       public void oomTest() {
           Object obj = new Object();  // 局部变量
           list.add(obj);
       }
   }
   ```
  </details>

> **2.单例模式**

- 说明：
  - 单例模式，和静态集合导致内存泄露的原因类似，因为单例的静态特性，它的生命周期和JVM的生命周期一样长
  - 所以如果单例对象如果持有外部对象的引用，那么这个外部对象也不会被回收，那么就会造成内存泄露。

> **3.内部类持有外部类**

- 说明：
  - 如果一个外部类的实例对象的方法返回了一个内部类的实例对象，这个内部类对象被长期引用了
  - 即使那个外部类实例不再被使用，但是由于内部类持有外部类的实例对象，这个外部类对象将不会被垃圾回收，这也造成内存泄露。

> **4.各种连接，如数据库连接、网络连接和IO连接等**

- 说明：
  - 在对数据库进行操作的过程中，首先需要建立数据库的链接，当不再使用时，需要调用close方法来释放与数据库的连接
  - 只有连接被关闭后，垃圾回收器才会回收对应的对象。
  - 否则，如果在访问数据库的过程中，对Connection、Statement或ResultSet不显性地关闭，将会造成大量对象无法被回收，从而引起内存泄露。
- 示例代码

  <details>
  <summary style="color:red;">展开</summary>

   ```java
   public static void main(String[] args) {
       try {
           Connection conn = null;
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection("url", "", "");
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery("...");
       } catch (Exception e) {  // 异常日志
           
       } finally {
           // 1.关闭结果集
           // 2.关闭声明的对象
           // 3.关闭连接
       }
   }
   ```
  </details>

> **5.变量不合理的作用域**

- 说明：
  - 一般而言，一个变量的定义的作用范围大于其使用范围，很有可能造成内存泄露
  - 另一方面没有及时地把对象设置为null，很有可能导致内存泄露的发生。

- 示例代码
  <details>
  <summary style="color:red;">展开</summary>

   ```java
   public class UsingRandom {
       private String msg;
       public void receiveMsg() {
           readFromNet();  // 从网络上接收数据保存到msg中
           saveDB();  // 把msg保存到数据库中
       }
   }
   // 如上面这个伪代码，通过readReomNet方法把接收的消息保存在变量msg中，然后调用saveDB方法把msg的内容保存到数据库中，此时msg已经就没有用了
   // 由于msg的生命周期与对象的生命周期相同，此时msg还不能被回收，因此造成了内存泄露。
   // 实际上这个msg变量可以放在receiveMsg方法内部，当方法使用完，那么msg的生命周期也就结束，此时就可以回收了
   // 还有另一种方法，在使用完msg后，把msg设置为null，这样垃圾回收也会回收msg的内存空间。
   ```
  </details>

> **6.改变哈希值**

- 说明：
  - 当一个对象被存储进HashSet集合以后，就不能修改这个对象中那些参与计算的哈希值字段了
  - 否则，对象修改后的哈希值与最初存储进HashSet集合中的哈希值就不同了
  - 在这种情况下，即使contains方法使用该对象的当前引用作为参数去HashSet集合中检索对象，也将返回找不到对象结果
  - 这也会导致无法从HashSet集合中单独删除当前对象，造成内存泄露。
  - 这也是String为什么被设置为了不可变类型，我们可以放心地把String存入HashSet，或者把String当做HashMap的key值。

- 示例代码

  <details>
  <summary style="color:red;">展开</summary>

   ```java
   /**
    * 演示内存泄漏
    */
   public class ChangeHashCode {
       public static void main(String[] args) {
           HashSet set = new HashSet();
           Person p1 = new Person(1001, "AA");
           Person p2 = new Person(1002, "BB");
   
           set.add(p1);
           set.add(p2);
   
           p1.name = "CC";  // 导致了内存的泄漏
           set.remove(p1);  // 删除失败
           // [Person{id=1002, name='BB'}, Person{id=1001, name='CC'}]
           System.out.println(set);  
   
           set.add(new Person(1001, "CC"));
           // [Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}]
           System.out.println(set);
   
           set.add(new Person(1001, "AA"));
           // [Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}, Person{id=1001, name='AA'}]
           System.out.println(set);
       }
   }
   
   class Person {
       int id;
       String name;
   
       public Person(int id, String name) {
           this.id = id;
           this.name = name;
       }
   
       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (!(o instanceof Person)) return false;
           Person person = (Person) o;
           if (id != person.id) return false;
           return name != null ? name.equals(person.name) : person.name == null;
       }
   
       @Override
       public int hashCode() {
           int result = id;
           result = 31 * result + (name != null ? name.hashCode() : 0);
           return result;
       }
   
       @Override
       public String toString() {
           return "Person{" + "id=" + id + ", name='" + name + '\'' + '}';
       }
   }
   ```
  </details>

  <details>
  <summary style="color:red;">展开</summary>

   ```java
   /**
    * 演示内存泄漏
    *
    * @author shkstart
    * @create 14:47
    */
   public class ChangeHashCode1 {
       public static void main(String[] args) {
           HashSet<Point> hs = new HashSet<Point>();
           Point cc = new Point();
           cc.setX(10);  // hashCode = 41
           hs.add(cc);
   
           cc.setX(20);  // hashCode = 51  此行为导致了内存的泄漏
   
           System.out.println("hs.remove = " + hs.remove(cc));  // false
           hs.add(cc);
           System.out.println("hs.size = " + hs.size());  // size = 2
   
           System.out.println(hs);  // [Point{x=20}, Point{x=20}]
       }
   
   }
   
   class Point {
       int x;
   
       public int getX() {
           return x;
       }
   
       public void setX(int x) {
           this.x = x;
       }
   
       @Override
       public int hashCode() {
           final int prime = 31;
           int result = 1;
           result = prime * result + x;
           return result;
       }
   
       @Override
       public boolean equals(Object obj) {
           if (this == obj) return true;
           if (obj == null) return false;
           if (getClass() != obj.getClass()) return false;
           Point other = (Point) obj;
           if (x != other.x) return false;
           return true;
       }
   
       @Override
       public String toString() {
           return "Point{" + "x=" + x + '}';
       }
   }
   ```
  </details>

> **7.缓存泄露**

- 说明：
  - 内存泄露的另一个常见来源是缓存，一旦你把对象放入到缓存中，他就容易遗忘
    > 比如：之前项目在一次上线的时候，应用启动奇慢直到夯死
    > 就是因为代码中会加载一个表中的数据到缓存（内存）中，测试环境只有几百条数据，但是生产环境有几百万的数据。
  - 对于此问题，可以使用WeakHashMap代表缓存，此种Map的特点是，当除了自己有对key的引用外，此key没有其他引用那么此map会自动丢弃此值。

- 示例代码

  <details>
  <summary style="color:red;"></summary>

   ```java
   /**
    * 演示内存泄漏
    */
   public class MapTest {
       static Map wMap = new WeakHashMap();
       static Map map = new HashMap();
   
       public static void main(String[] args) throws Exception {
           init();
           System.out.println("---------------------------");
           testWeakHashMap();
           System.out.println("---------------------------");
           testHashMap();
       }
   
       public static void init() {
           String ref1 = new String("obejct1");
           String ref2 = new String("obejct2");
           String ref3 = new String("obejct3");
           String ref4 = new String("obejct4");
           wMap.put(ref1, "cacheObject1");
           wMap.put(ref2, "cacheObject2");
           map.put(ref3, "cacheObject3");
           map.put(ref4, "cacheObject4");
           System.out.println("String引用ref1，ref2，ref3，ref4 消失");
   
       }
   
       public static void testWeakHashMap() throws InterruptedException {
           System.out.println("WeakHashMap GC之前");
           for (Object o : wMap.entrySet()) System.out.println(o);
           System.gc();
           TimeUnit.SECONDS.sleep(2);
           System.out.println("WeakHashMap GC之后");
           for (Object o : wMap.entrySet()) System.out.println(o);
       }
   
       public static void testHashMap() throws InterruptedException {
           System.out.println("HashMap GC之前");
           for (Object o : map.entrySet()) System.out.println(o);
           System.gc();
           TimeUnit.SECONDS.sleep(2);
           System.out.println("HashMap GC之后");
           for (Object o : map.entrySet()) System.out.println(o);
       }
   
   }
   /**
    * 结果
    * String引用ref1，ref2，ref3，ref4 消失
    * ---------------------------
    * WeakHashMap GC之前
    * obejct2=cacheObject2
    * obejct1=cacheObject1
    * WeakHashMap GC之后
    * ---------------------------
    * HashMap GC之前
    * obejct4=cacheObject4
    * obejct3=cacheObject3
    * HashMap GC之后
    * obejct4=cacheObject4
    * obejct3=cacheObject3
    **/
   ```

  ![jvm3-59.png](./image/jvm3-59.png)

  - 上面代码和图示主要演示了WeakHashMap如何自动释放缓存对象
  - 当init函数执行完成后，局部变量字符串引用obejct1，obejct2，obejct3，obejct4都会消失
  - 此时只有静态map中保存了对字符串对象的引用，可以看到，调用gc之后，HashMap没有被回收，而WeakHashMap里面的缓存被回收了。
  </details>

> **8.监听器和回调**

- 说明
  - 内存泄露的另一个常见来源是监听器和其他回调
  - 如果客户端在你实现的API中注册回调，却没有显式的取消，那么就会聚集
  - 需要确保回调立即被当做垃圾回收的最佳方法是只保存它的弱引用，例如将它们保存成为WeakHashMap中的键。

#### 3.4.6.3. 案例1-过期引用

> **1. 代码**

```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {  // 入栈
        ensureCapacity();
        elements[size++] = e;
    }
    // 存在内存泄漏
    public Object pop() {  // 出栈
        if (size == 0) throw new EmptyStackException();
        return elements[--size];
    }

    /*public Object pop() {
        if (size == 0) throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }*/

    private void ensureCapacity() {
        if (elements.length == size) elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

> **2. 分析**

- 假设这个栈一致增长，增长后如下图所示：

  ![jvm3-77.png](./image/jvm3-77.png)

- 当进行大量的pop操作时，由于引用未进行置空，gc是不会释放的

  ![jvm3-78.png](./image/jvm3-78.png)

- 如果栈先增长，后收缩，那么从栈中弹出的对象将不会被当做垃圾被回收，即使程序不再使用栈中的这些对象，我们也不会回收
- 因为栈中仍然保存这些对象的引用，俗称**引用过期**，这个内存泄露相对较难发现。

> **3.解决办法**

- 将pop()这个函数改成如下函数即可：

  ```java
  public Object pop() {
      if (size == 0) throw new EmptyStackException();
      Object result = elements[--size];
      elements[size] = null;
      return result;
  }
  ```

#### 3.4.6.4. 案例2-匿名线程引用成员变量

> **1.代码与说明**

- Android开发中的一个界面，当退出这个界面时，因为TestActivity中中存在未结束的线程，导致无法回收该类，造成内存泄露。

  ```java
  public class TestActivity extends Activity {
      private static final Object key = new Object();
      
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          
          new Thread() {  // 匿名线程，退出页面是导致内存泄露
              public void run() {
                  synchronized (key) {
                      try {
                          key.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          }.start();
      }
  }
  ```

> **2.解决办法：**

- 使用线程时，一定要确保线程在周期性对象（如Activity）销毁时能正常结束
  - 如能正常结束，但是Activity销毁后还需要执行一段时间，也可能造成内存泄露
  - 此时可采用WeakReference方法来解决，另外在使用Hanlder的时候，如存在Delay操作，也可采用WeakReference；
- 使用Handler+HandlerThread时，记住在周期性对象销毁时调用looper.quit()方法。

### 3.4.7. 补充：QQL

> MAT支持一种类似于SQL的查询语言OQL（Object Query Language）。OQL使用类SQL语法，可以在堆中进行对象的查找和筛选。

- SELECT子句 在MAT中，Select子句的格式与SQL基本一致，用于指定要显示的列
  - Select子句中可以使用"*"，查看结果对象的引用实例（相当于outgoing references）。

    ```sql
    SELECT * FROM java.util.Vector v
    ```

  - 使用"OBJECTS"关键字，可以将返回结果集中的项以对象的形式显示。

    ```sql
    SELECT objects v.elementData FROM java.util.Vector v
    SELECT OBJECTS s.value FROM java.util.String s
    ```

  - 在Select子句中，使用"AS RETAINED SET"关键字可以得到所得对象的保留集。

    ```sql
    SELECT AS RETAINED SET * FROM com.atguigu.mat.Student
    ```

  - "DISTINCT"关键字用于在结果集中去除重复对象。

    ```sql
    SELECT DISTINCT OBJECTS classof(s) FROM java.lang.String s
    ```

- FROM子句
  - From子句用于指定查询范围，它可以指定类名、正则表达式或者对象地址。

    ```sql
    SELECT * FROM java.lang.String s
    ```

  - 下列使用正则表达式，限定搜索范围，输出所有com.atguigu包下所有类的实例

    ```sql
    SELECT * FROM "com\.atguigu\..*"
    ```

  - 也可以直接使用类的地址进行搜索。使用类的地址的好处是可以区分被不同ClassLoader加载的同一种类型。

    ```sql
    select * from 0x37a0b4d
    ```

- WHERE子句: Where子句用于指定OQL的查询条件。OQL查询将只返回满足Where子句指定条件的对象。 Where子句的格式和传统的SQL极为相似。

  - 下例返回长度大于10的char数组。

    ```sql
    SELECT * FROM char[] s WHERE s.@length>10
    ```

  - 下例返回包含"java"子字符串的所有字符串，使用"LIKE"操作符，"LIKE"操作符的操作参数为正则表达式。

    ```sql
    SELECT * FROM java.lang.String s WHERE toString(s) LIKE ".*java.*"
    ```

  - 下例返回所有value域不为null的字符串，使用"="操作符

    ```sql
    SELECT * FROM java.lang.String s where s.value!=null
    ```

  - Where子句支持多个条件的AND、OR运算。下例返回数组长度大于15，并且深堆大于1000字节的所有Vector对象

    ```sql
    SELECT * FROM java.util.Vector v WHERE v.elementData.@length>15 AND v.@retainedHeapSize>1000
    ```

- 内置对象和方法
  - OQL中可以访问堆内对象的属性，也可以访问堆内代理对象的属性。访问堆内对象的属性时，格式如下：

    ```sql
    # alias为对象名称
    [ <alias>. ] <field> . <field> . <field>
    ```

  - 访问`java.io.File`对象的path属性，并进一步访问path的value属性：

    ```sql
    SELECT toString(f.path.value) FROM java.io.File f
    ```

  - 下例显示了String对象的内容、objectid和objectAddress。

    ```sql
    SELECT s.toString(), s.@objectId, s.@objectAddress FROM java.lang.String s
    ```

  - 下例显示`java.util.Vector`内部数组的长度

    ```sql
    SELECT v.elementData.@length FROM java.util.Vector v
    ```

  - 下例显示了所有的`java.util.Vector`对象及其子类型

    ```sql
    select * from INSTANCEOF java.util.Vector
    ```

## 3.5. JProfiler

### 3.5.1. 基本概述

- 目的： 在运行Java的时候测试运行时占用内存情况
  - 在eclipse里面有Eclipse Memory Analyer tool（MAT）插件可以测试
  - 而在IDEA中可以使用JProfiler
  - JProfiler是由ej-technologies公司开发的一款Java应用性能诊断工具。功能强大，但是收费。
  - [官方下载地址](https://www.ej-technologies.com/products/jprofiler/overview.html)

- 特点
  - 使用方便、界面操作友好（简单且强大）
  - 对被分析的应用影响小（提供模板）
  - CPU，Thread，Memory分析功能尤其强大
  - 支持对jdbc，noSql，jsp，servlet，socket等进行分析
  - 支持多种模式（离线，在线）的分析
  - 支持监控本地、远程的JVM
  - 跨平台，拥有多种操作系统的安装版本

- 主要功能
  - **方法调用**：对方法调用的分析可以帮助您了解应用程序正在做什么，并找到提高其性能的方法
  - **内存分配**：通过分析堆上对象、引用链和垃圾收集能帮你修复内存泄露问题，优化内存使用
  - **线程和锁**：JProfiler提供多种针对线程和锁的分析视图助您发现多线程问题
  - **高级子系统**：许多性能问题都发生在更高的语义级别上。例如，对JDBC调用，您可能希望找出执行最慢的SQL语句。JProfiler支持对这些子系统进行集成分析

### 3.5.2. 具体使用

#### 3.5.2.1. 测试代码

```java
/**
 * -Xms600m -Xmx600m -XX:SurvivorRatio=8
 */
public class OOMTest {
    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(100 * 50)));
        }
    }
}

class Picture {
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
```

#### 3.5.2.2. 数据采集方式

- 打开Jprofile后需要选择使用的数据采集方式 

  ![jvm3-85.png](./image/jvm3-85.png)

  ![jvm3-86.png](./image/jvm3-86.png)

- JProfiler数据采集方式分为两种：Sampling（样本采集）和Instrumentation（重构模式）
  - Instrumentation：
    - 说明：
      - 这是JProfiler全功能模式
      - 在class加载之前，JProfiler把相关功能代码写入到需要分析的class的bytecode中，对正在运行的jvm有一定的影响。
    - 优点：功能强大。在此设置中，调用堆栈信息是准确的。
    - 缺点：若要分析的class较多，则对应的性能影响比较大，CPU开销可能很高（取决于Filter的控制）。因此使用此模式一般配合Filter使用，只对特定的类或包进行分析。
  - Sampling：
    - 说明：
      - 类似于样本统计
      - 每隔一定时间（5ms）将每个线程栈中方法的信息统计出来。
    - 优点：对CPU的开销非常低，对应用影响小（即使你不配置任何Filter）
    - 缺点：一些数据/特性不能提供（例如：方法的调用次数、执行时间）

  > 注：JProfilter本身没有指出数据的采集类型，这里的采集类型是针对方法调用的采集类型。 
  > 因为JProfiler的绝大数核心功能都依赖方法调用采集的数据，所以可以直接认为是JProfiler的数据采集类型。

#### 3.5.2.3. 遥感检测Telemetries

#### 3.5.2.4. 内存视图Live Memory

#### 3.5.2.5. 堆遍历heap walker

#### 3.5.2.6. cpu视图 cpu views

#### 3.5.2.7. 线程视图threads

#### 3.5.2.8. 监视器&锁 Monitors&locks

### 3.5.3. 案例分析

#### 3.5.3.1. 案例1

```java
/**
  * 功能演示测试
  * 不存在内存泄露的程序
  */
public class JProfilerTest {
    public static void main(String[] args) {
        while (true) {
            ArrayList list = new ArrayList();
            for (int i = 0; i < 500; i++) {
                Data data = new Data();
                list.add(data);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Data {
    private int size = 10;
    private byte[] buffer = new byte[1024 * 1024];//1mb
    private String info = "hello,atguigu";
}
```

#### 3.5.3.2. 案例2

```java
/**
  * 存在内存泄露
  */
public class MemoryLeak {

    public static void main(String[] args) {
        while (true) {
            ArrayList beanList = new ArrayList();
            for (int i = 0; i < 500; i++) {
                Bean data = new Bean();
                data.list.add(new byte[1024 * 10]);//10kb
                beanList.add(data);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Bean {
    int size = 10;
    String info = "hello,atguigu";
    // ArrayList list = new ArrayList();
    static ArrayList list = new ArrayList();  // 会存在内存泄露问题
}
```

## 3.6. Arthas

### 3.6.1. 基本概述

- 背景：
  - jvisualvm和JProfiler都必须在服务端项目进程中配置相关监控参数
  - 然后工具通过远程连接到项目进程，获取相关数据。
  - 这样就带来一些不便，比如线上环境的网络是隔离的，本地的监控工具根本连不上线上环境
  - 并且类似于Jprofiler这样的商业工具，是需要付费的。

- 概述:`Arthas` 是Alibaba开源的Java诊断工具，可以解决一下问题：
  - 这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
  - 我改的代码为什么没有执行到？难道是我没 commit？分支搞错了？
  - 遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗？
  - 线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现！
  - 是否有一个全局视角来查看系统的运行状况？
  - 有什么办法可以监控到JVM的实时运行状态？
  - 怎么快速定位应用的热点，生成火焰图？

  `Arthas`支持JDK 6+，支持Linux/Mac/Windows，采用命令行交互模式，同时提供丰富的 `Tab` 自动补全功能，进一步方便进行问题的定位和诊断。

- 依赖
  - greys-anatomy： **Arthas 代码基于 Greys 二次开发而来** 
  - termd： **Arthas 的命令行基于 termd 开发** ，是一款优秀的命令行程序开发框架
  - crash： **Arthas 的文本渲染功能基于 crash 中的文本渲染功能开发**，可以从这里看到源码
  - cli： **Arthas 的命令行界面** 基于 vert.x 提供的 cli 库进行开发
  - complier Arthas 里的 **内存编辑器代码** 来源
  - Apache Commons Net Arthas 里的 Telnet Client 代码来源
  - JavaAgent：运行在 main 方法之前的拦截器，它内定的方法名叫 permain，也就是说先执行 permain 然后再执行 main 方法
  - ASM：一个通用的 Java 字节码操作和分析框架。他可以用于修改现有的类或直接以二进制形式动态生成类。ASM 提供了一些常见的字节码转换和分析算法，可以从他们构建定制的复杂转换和代码分析工具。ASM 提供了与其他 Java 字节码框架类似的功能，但是主要关注性能。因为它被设计和实现得尽可能小和块，所以非常适合在动态系统中使用（当然也可以以静态方式使用，例如在编译器中）。

- 官方使用文档：[网址](https://arthas.aliyun.com/zh-cn)

  ![jvm3-87.png](./image/jvm3-87.png)

### 3.6.2. 安装与使用

> **安装**

- **安装方式一**：可以直接在Linux上通过命令下载

  - Github下载

    ```bash
    wget https://alibaba.github.io/arthas/arthas-boot.jar
    ```

  - Gitee下载

    ```bash
    wget https://arthas.gitee.io/arthas-boot.jar
    ```

- **安装方式二**

  也可以在浏览器中直接访问 [网址](https://alibaba.github.io/arthas/arthas-boot.jar)，等到下载成功后，上传到Linux服务器上。

> **卸载**

- 在Linux/Unix/mac平台，删除下面文件：

  ```bash
  rm -rf ~/.arthas/
  rm -rf ~/logs/arthas
  ```

- Windows平台直接删除user home下面的.arthas和logs/arthas目录

> **启动**

```bash
  # 方式一：检测当前服务器上的Java进程，并将进程列表展示出来，用户输入对应的编号进行选择，然后回车
  java -jar arthas-boot.jar
  # 方式二：运行时选择Java进程PID
  java -jar arthas-boot.jar [PID]
```

![jvm3-88.png](./image/jvm3-88.png)

> **web console**

- 除了在命令行查看外，Arthas目前还支持Web Console
- 在成功启动连接进程之后就已经启动
- 可以直接访问：`http://127.0.0.1:8563/` 访问，页面上的操作模式和控制台完全一样。

![jvm3-89.png](./image/jvm3-89.png)

> **其他**

```bash
  # 查看日志
  cat ~/logs/arthas/arthas.lg
  # 查看帮助
  java -jar arthas-boot.jar -h
  # 退出
  quit\exit  # 退出当前客户端
  stop\shutdown  # 关闭arthas服务端，并退出所有客户端
```

### 3.6.3. 相关诊断命令

- [命令帮助网址](https://arthas.gitee.io/advanced-use.html)

#### 3.6.3.1. 基础指令

- help:查看帮助命令信息
- cat:打印文件内容，和linux里的 cat 命令类似
- echo:打印参数，和linux里的 echo 命令类似
- grep:匹配查找，和linux里的 grep 命令类似
- tee:复制标准输入到标准输出和指定文件，和linux里的 tee 命令类似
- pwd:返回当前的工作目录，，和linux里的 pwd 命令类似
- cls:清空当前屏幕区域
- session:查看当前会话的信息
- reset:重置增强类，将被Arthas增强过的类全部还原，Arthas服务端关闭时会重置所有增强过的类
- version:输出当前目标Java进程所加载的Arthas版本号
- history:打印历史命令
- quit:退出当前Arthas客户端，其他Arthas客户端不受影响
- stop:关闭Arthas服务端，所有Arthas客户端不受影响
- keymap:Arthas快捷键列表及自定义快捷键

#### 3.6.3.2. jvm相关

- [dashboard](https://arthas.gitee.io/dashboard.html)——当前系统的实时数据面板
- [thread](https://arthas.gitee.io/thread.html)——查看当前 JVM 的线程堆栈信息
- [jvm](https://arthas.gitee.io/jvm.html)——查看当前 JVM 的信息
- [sysprop](https://arthas.gitee.io/sysprop.html)——查看和修改JVM的系统属性
- [sysenv](https://arthas.gitee.io/sysenv.html)——查看JVM的环境变量
- [vmoption](https://arthas.gitee.io/vmoption.html)——查看和修改JVM里诊断相关的option
- [perfcounter](https://arthas.gitee.io/perfcounter.html)——查看当前 JVM 的Perf Counter信息
- [logger](https://arthas.gitee.io/logger.html)——查看和修改logger
- [getstatic](https://arthas.gitee.io/getstatic.html)——查看类的静态属性
- [ognl](https://arthas.gitee.io/ognl.html)——执行ognl表达式
- [mbean](https://arthas.gitee.io/mbean.html)——查看 Mbean 的信息
- [heapdump](https://arthas.gitee.io/heapdump.html)——dump java heap, 类似jmap命令的heap dump功能

#### 3.6.3.3. class/classloader相关

- [sc](https://arthas.gitee.io/sc.html)——查看JVM已加载的类信息
- [sm](https://arthas.gitee.io/sm.html)——查看已加载类的方法信息
- [jad](https://arthas.gitee.io/jad.html)——反编译指定已加载类的源码
- [mc](https://arthas.gitee.io/mc.html)——内存编译器，内存编译`.java`文件为`.class`文件
- [retransform](https://arthas.gitee.io/retransform.html)——加载外部的`.class`文件，retransform到JVM里
- [redefine](https://arthas.gitee.io/redefine.html)——加载外部的`.class`文件，redefine到JVM里
- [dump](https://arthas.gitee.io/dump.html)——dump 已加载类的 byte code 到特定目录
- [classloader](https://arthas.gitee.io/classloader.html)——查看classloader的继承树，urls，类加载信息，使用classloader去getResource

#### 3.6.3.4. monitor/watch/trace相关

请注意，这些命令，都通过字节码增强技术来实现的，会在指定类的方法中插入一些切面来实现数据统计和观测，
因此在线上、预发使用时，请尽量明确需要观测的类、方法以及条件，诊断结束要执行 `stop` 或将增强过的类执行 `reset` 命令。

- [monitor](https://arthas.gitee.io/monitor.html)——方法执行监控
- [watch](https://arthas.gitee.io/watch.html)——方法执行数据观测
- [trace](https://arthas.gitee.io/trace.html)——方法内部调用路径，并输出方法路径上的每个节点上耗时
- [stack](https://arthas.gitee.io/stack.html)——输出当前方法被调用的调用路径
- [tt](https://arthas.gitee.io/tt.html)——方法执行数据的时空隧道，记录下指定方法每次调用的入参和返回信息，并能对这些不同的时间下调用进行观测

#### 3.6.3.5. 其他

- profiler:使用async-profiler对应用采样，生成火焰图
- options:查看或设置Arthas全局开关
- auth:鉴权

## 3.7. Java Mission Control

### 3.7.1. 历史

- 在Oracle收购Sun之前，Oracle的Jrocket虚拟机提供了一款叫做JRocket Mission Control的虚拟机诊断工具。
- 在Oracle收购Sun之后，Oracle公司同时拥有了Sun Hotspot和JRocket两款虚拟机。根据Oracle对于Java的战略，在今后的发展中，会将JRocket的优秀特性移植到Hotspot上，其中，一个重要的改进就是在Sun的JDK中加入了JRocket的支持。
- 在Oracle JDK 7u40之后，Mission Control这款工具已经绑定在Oracle JDK中发布。
- 自Java 11开始，本节介绍的JFR已经开源。但是之前的Java版本，JFR属于Commercial Feature，需要通过Java虚拟机参数`-XX:+UnlockCommercialFeatures`开启。
- 如果你有兴趣请可以查看OpenJDK的Mission Control项目：[网址](https://github.com/JDKMissionControl/jmc)

### 3.7.2. 启动

- Mission Control位于`%JAVA_HOME%/bin/jmc.exe`

### 3.7.3. 概述

- Java Mission Control（简称JMC），Java官方提供的性能强劲的工具。
- 是一个用于对Java应用程序进行管理、监视、概要分析和故障排除的工具套件。
- 它包含一个GUI客户端，以及众多用来收集Java虚拟机性能数据的插件
  - 如JMX Console（能够访问用来存放虚拟机各个子系统运行数据的MXBeans）
  - 以及虚拟机内置的高效profiling工具Java Flight Recorder（JFR）。
- JMC的另一个优点就是：
  - 采用取样，而不是传统的代码置入技术，对应用性能的影响非常非常小，
  - 完全可以开着JMC来做压测（唯一影响可能是full gc多了）。

### 3.7.4. 功能

- 实时监控JVM运行时的状态

  ![jvm3-90.png](./image/jvm3-90.png)

### 3.7.5. Java Flight Recorder

#### 3.7.5.1. 说明

- Java Flight Recorder是JMC其中的一个组件。
- Java Flight Recorder能够以 **极低的性能开销收集Java** 虚拟机的性能数据。
 - JFR的性能开销小，在默认配置下平均低于1%
 - 与其他工具相比，JFR能够直接访问虚拟机内的数据，并且不会影响虚拟机的优化
 - 因此， **它非常适用于生产环境下满负荷运行下的Java程序**
- JFR和JMC共同创建了一个完整的工具链
  - JDK Mission Control可对Java Flight Recorder连续收集低水平和详细的运行时信息进行高效详细的分析。

#### 3.7.5.2. 事件的类型

- 当启用时，JFR将记录运行过程中发生的一系列事件。
  - 其中包括Java层面的事件，如线程事件、锁事件
  - 以及Java虚拟机内部的事件，如新建对象、垃圾回收和即时编译时间。

- 按照事件发生时机以及持续时间来划分，JFR的事件供有四种类型，他们分别为以下四种。
  - （1）瞬时事件（Instant Event）：用户关心的是它们发生与否，例如异常、线程启动事件。
  - （2）持续事件（Duration Event）：用户关心的是它们的运行时间，例如垃圾回收事件。
  - （3）计时事件（Timed Event）：是时长超出指定阈值的持续事件。
  - （4）取样事件（Sample Event）：是周期性取样的事件。

- 取样事件的其中一个常见例子便是方法抽样（Method Sampling）
  - 即每隔一段时间统计各个线程的栈轨迹
  - 如果在这些抽样取得的栈轨迹中存在一个反复出现的方法，那么就可以推断该方法是热点方法。

#### 3.7.5.3. Java Flight Recorder取样分析

- 要采用取样，必须先添加参数

  ```bash
  -XX:+UnlockCommercialFeatures
  -XX:+FlightRecorder
  ```

  > 否则
  > ![jvm3-91.png](./image/jvm3-91.png)

#### 3.7.5.4. 启动方式

- 方式1：图形界面方式：jmc自带的飞行记录仪界面

- 方式2：在运行目标Java程序中添加`-XX:StartFlightRecording=参数`。
  - 示例
    - 下面命令中，JFR将会在Java虚拟机启动5s后（对应delay=5s）收集数据
    - 持续20s（对应duration=20s）。
    - 当收集完毕后，JFR会将收集到的数据保存至指定的文件中（对应filename=myrecording.jar）

    ```bash
    java -XX:StartFlightRecording=delay=5s,duration=20s,filename=myrecording.jar,settings=profile MyApp
    ```

  - 由于JFR将持续收集数据，如果不加以限制，那么JFR可能会填满硬盘的所有空间
  - 因此，我们有必要对这种模式下所收集的数据进行限制。比如：

    ```bash
    # 满足一个条件就会停止
    java -XX:StartFlightRecording=maxage=10m,maxsize=100m,name=SomeLabel MyApp
    ```

- 方式3：通过jcmd来让JFR开始收集数据、停止收集数据，或者保存所收集的数据，对应的子命令分别为

  ```bash
  JFR.start
  JFR.stop
  JFR.dump
  ```

  - 运行如下命令，可以让目标进程中的JFR开始收集数据

    ```bash
    jcmd <PID> JFR.start settings=profile maxage=10m maxsize=150m name=SomeLabel
    ```

  - 此时，可以通过如下命令来导出已经收集到的数据：

    ```bash
    jcmd <PID> JFR.dump name=SomeLabel filename=myrecording.jfr
    ```

  - 最后，可以通过下述命令关闭目标进程中的JFR：

    ```bash
    jcmd <PID> JFR.stop name=SomeLabel
    ```

## 3.8. 其他工具

![jvm3-92.png](./image/jvm3-92.png)

### 3.8.1. 火焰图(重要)

- 通过Arthas可以生成火焰图。
- 在追求极致性能的场景下，了解你的程序运行过程中CPU在干什么很重要
- 火焰图就是一种非常直观的展示CPU在程序整个生命周期过程中时间分配的工具。
- 现代程序员对于火焰图不应该陌生，这个工具可以非常直观的显示出调用栈中的CPU消耗瓶颈。
- 网上关于java火焰图的讲解大部分来自于Brendan Gregg的博客：[网址](http://www.brendangregg.com/flamegraphs.html)

### 3.8.2. Tprofiler

- 案例：
  ```
  使用JDK自身提供的工具进行JVM调优可以将TPS由2.5提升到了20（提升了7倍），并准确定位系统瓶颈。
  系统瓶颈有：应用里静态对象太多、有大量的业务线程在频繁创建一些生命周期很长的临时对象，代码里有问题。
  那么，如何在海量业务代码里边准确定位这些性能代码？这里使用了阿里开源工具TProfiler来定位这些性能代码，成功解决掉GC过于频繁的性能瓶颈，并最终在上次优化的基础上将TPS再提升4倍，即提升到100。
  ```

- TProfiler 配置部署、远程操作、日志阅读都不复杂，操作还是很简单的。
  - 但是其却能够起到一阵见血、立杆见影的效果，帮我们解决了GC过于频繁的性能瓶颈。

- TProfiler最重要的特性就是能够统计出你指定时间段内JVM的top method
  - 这些top method极有可能就是造成JVM性能瓶颈的元凶
  - 这是其他大多数JVM调优工具所不具备的，包括JRocket Mission Control
  - JRocket首席开发者Marcus Hirt在其私人博客《Low Overhead Method Profilering with Java Mission Control》下的评论中层明确指出JRMC并不支持TOP方法的统计。

- TProfiler下载：[网址](https://github.com/alibaba/TProfiler)

### 3.8.3. Btrace

### 3.8.4. YourKit

### 3.8.5. JProbe

### 3.8.6. Spring Insight

# 4. JVM运行时参数

## 4.1. JVM参数选项类型

### 4.1.1. 类型一：标准参数选项

- 特点：比较稳定，后续版本基本不会变化，**以-开头**
- 各种选项：运行java或者java -help可以看到所有的标准选项

  ![jvm3-93.png](./image/jvm3-93.png)

- 补充内容：-server与-client
  - Hotspot JVM有两种模式，分别是server和client，分别通过-server和-client模式设置
  - 在32位Windows系统上，默认使用Client类型的JVM。
    - 要想使用Server模式，则机器配置至少有2个以上的CPU和2G以上的物理内存
    - client模式适用于对内存较小的桌面应用程序，默认使用Serial串行垃圾收集器。
  - 64位机器上只支持server模式的JVM，使用于需要大内存的应用程序，默认抵用并行垃圾收集器

- 关于server和client的官网介绍：[网址](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/server-class.html)

### 4.1.2. 类型二：-X参数

- 特点：
  - 非标准化参数，功能还是比较稳定的
  - 但官方说后续版本可能会变更， **以-X开头**

- 各种选项：运行java -X命令可以看到所有的X选项

  ```bash
  $ java -X
      -Xmixed           混合模式执行 (默认)
      -Xint             仅解释模式执行
      -Xbootclasspath:<用 ; 分隔的目录和 zip/jar 文件>
                        设置搜索路径以引导类和资源
      -Xbootclasspath/a:<用 ; 分隔的目录和 zip/jar 文件>
                        附加在引导类路径末尾
      -Xbootclasspath/p:<用 ; 分隔的目录和 zip/jar 文件>
                        置于引导类路径之前
      -Xdiag            显示附加诊断消息
      -Xnoclassgc       禁用类垃圾收集
      -Xincgc           启用增量垃圾收集
      -Xloggc:<file>    将 GC 状态记录在文件中 (带时间戳)
      -Xbatch           禁用后台编译
      -Xms<size>        设置初始 Java 堆大小
      -Xmx<size>        设置最大 Java 堆大小
      -Xss<size>        设置 Java 线程堆栈大小
      -Xprof            输出 cpu 配置文件数据
      -Xfuture          启用最严格的检查, 预期将来的默认值
      -Xrs              减少 Java/VM 对操作系统信号的使用 (请参阅文档)
      -Xcheck:jni       对 JNI 函数执行其他检查
      -Xshare:off       不尝试使用共享类数据
      -Xshare:auto      在可能的情况下使用共享类数据 (默认)
      -Xshare:on        要求使用共享类数据, 否则将失败。
      -XshowSettings    显示所有设置并继续
      -XshowSettings:all
                        显示所有设置并继续
      -XshowSettings:vm 显示所有与 vm 相关的设置并继续
      -XshowSettings:properties
                        显示所有属性设置并继续
      -XshowSettings:locale
                        显示所有与区域设置相关的设置并继续

  -X 选项是非标准选项, 如有更改, 恕不另行通知。
  ```

- 部分说明:JVM的JIT编译模式相关的选项
  - -Xint 禁用JIT，所有字节码都被解释执行，这个模式的速度是最慢的
  - -Xcomp 所有字节码第一次使用都被编译成本地代码，然后再执行
  - -Xmixed 混合模式，默认模式，让JIT根据程序运行的情况，有选择地将某些代码编译成本地代码

- 注意： **-Xms -Xmx -Xss属于XX参数**
  - -Xms<size> 设置初始Java堆大小，等价于-XX:InitialHeapSize
  - -Xmx<size> 设置最大Java堆大小，等价于-XX:MaxHeapSize
  - -Xss<size> 设置Java线程堆栈大小，等价于-XX:ThreadStackSize

### 4.1.3. 类型三：-XX参数

- 特点：
  - 非标准化参数， **使用最多的参数选项**
  - 这类选项属于实验性，不稳定， **以-XX开头**

- 作用：用于开发和调试JVM

- 查看所有-XX参数

  ```bash
  -XX:+PrintFlagsFinal
  ```
  - 输出所有参数的名称和默认值
  - 默认不包括Diagnositc和Experiment的参数
  - 可以配合`-XX:+UnlockDiagnosticVMOptions`和`-XX:UnlockExperimentVMOptions`使用

todo

- **分类**：
  - Boolean类型格式
    - `-XX:+<option>`表示启动option属性
    - `-XX:-<option>`表示禁用option属性
  - 非Boolean类型格式(key-value类型)
    - 子类型1：数值型格式：`-XX:<option>=<number>`
    - 子类型2：非数值型格式：`-XX:<name>=<string>`

- 分类示例

  ```bash
  # Boolean类型格式
  -XX:+UseParallelGC			# 选择垃圾收集器为并行收集器
  -XX:+UseG1GC				# 表示启用G1收集器
  -XX:+UseAdaptiveSizePolicy	# 自动选择年轻代区大小和响相应的Survivor区比例
  
  # 非Boolean类型格式(key-value类型)
  # 子类型1：数值型格式 -XX:<option>=<number>
  # number表示数值，可带上单位：(兆：'m','M'),(KB：'k', 'K'), (GB：'g','G')
  -XX:NewSize=1024m			# 表示设置新生代初始大小为1024兆
  -XX:MaxGCPauseMills=500		# 表示设置GC停顿时间：500毫秒
  -XX:GCTimeRatio=19			# 表示设置吞吐量
  -XX:NewRatio=2				# 表示老年代和新生代的比例为2

  # 子类型2：非数值型格式 -XX:<option>=<string>
  -XX:HeapDumpPath=/usr/local/heapdump.hprof		# 用于指定heap转存文件的存储路径
  ```

## 4.2. 添加JVM参数选项

### 4.2.1. eclipse

### 4.2.2. IDEA

### 4.2.3. 运行jar包

```bash
java -Xms50m -Xmx50m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -jar demo.jar
```

### 4.2.4. 通过Tomcat运行war包

- Linux系统下可以在tomcat/bin/catalina.sh中添加如下配置：`JAVA_OPTS="-Xms512M -Xmx1024M"`
- Windows系统下在catalina.bat中添加类似如下配置：`set "JAVA_OPTS=-Xms512M -Xmx1024M"`

### 4.2.5. 程序运行过程中

- 使用`jinfo -flag <name>=<value> <pid>`设置非Boolean类型参数
- 使用`jinfo -flag [+|-]<name> <pid>`设置Boolean类型参数

## 4.3. 常用的JVM参数选项

> [命令参数官方网址](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html)

> 笔记为上述网址中参数的梳理

### 4.3.1. 打印设置的XX选项及值

- -XX:+PrintCommandLineFlags:可以让在程序运行前打印出用户手动设置或者JVM自动设置的XX选项
- -XX:+PrintFlagslnitial:表示打印出所有XX选项的默认值
- -XX:+PrintFlagsFinal:表示打印出XX选项在运行程序时生效的值
- -XX:+PrintVMOptions: 打印JVM的参数

### 4.3.2. 堆、栈、方法区等内存大小设置

- 栈
  - -Xss128k
    - 置每个线程的栈大小为128k
    - 等价于 -XX:ThreadStackSize=128k
- 堆内存
  - -Xms3550m 
    - 等价于-XX:InitialHeapSize,设置JVM初始堆内存为3550M
  - -Xmx3550m
    - 等价于-XX:MaxHeapSize,设置JVM最大堆内存为3550M
  - Xmn2g
    - 设置年轻代大小为2G
    - 官方推荐配置为整个堆大小的3/8
  - -XXNewsize=1024m
    - 设置年轻代初始值为1024M
  - -XX:MaxNewSize=1024m
    - 设置年轻代最大值为1024M
  - -XX:SurvivorRatio=8
    - 设置年轻代中Eden区与一个Survivor区的比值，默认为8
  - -XX:+UseAdaptiveSizePolicy
    - 自动选择各区大小比例
  - -XX:NewRatio=4 
    - 设置老年代与年轻代（包括1个Eden和2个Survivor区）的比值
  - -XX:PretenureSizeThreadshold=1024 
    - 设置让大于此阅值的对象直接分配在老年代，单位为字节
    - 只对Serial、ParNew收集器有效
  - -XX:MaxTenuringThreshod=15
    - 默认值为15
    - 新生代每次MinorGC后，还存活的对象年龄+1，当年龄大于这个值的时候就进入老年代
  - -XX:+PrintTenuringDistribution 
    - 让JVM在每次MinorGC后打印出当前使用的Survivor中对象的年龄分布
  - -XX:TargetSurvivorRatio:表示MinorGC结束后Survivor区域中占用空间的期望比例
- 方法区
  - 永久代
    - -XX:PermSize=256m
      - 设置永久代初始值为256M
        - -XX:MaxPermSize=256m
        - 设置永久代最大值为256M
  - 元空间
    - -XX:MetaspaceSize
      - 初始空间大小
    - -XX:MaxMetaspaceSize:
      - 最大空间，默认没有限制
    - -XX:+UseCompressedOops:
      - 压缩对象指针
    - -XX:+UseCompressedClassPointers
      - 压缩类指针c
    - -XX:CompressedClassSpaceSize
      - 设置Class Metaspace的大小，默认1G

- 直接内存
  - -XX:MaxDirectMemorysize:
    - 指定DirectMemory容量，若未指定，则默认与Java维最大值一样

---

- 注意点：
  - 虽然默认参数`-XX:SurvivorRatio=8`，但是如果不显式设置，实际上`Eden : S0 : S1 = 6 : 1 : 1`
  - 默认情况下，`-XX:+UseAdaptivePolicy`是开启的
  - 需要显式设置`-XX:SurvivorRatio=8`，才能`Eden : S0 : S1 = 8 : 1 : 1`

### 4.3.3. OutofMemory相关的选项

- -XX:+HeapDumpOnOutOfMemoryError
  - 表示在内存出现OOM的时候，把Heap转存（Dump)到文件以便后续分析
- -XX:+HeapDumpBeforeFullGC
  - 表示在出现FullGC之前，生成Heap转储文件
- `-XX:HeapDumpPath=<path>`
  - 指定heap转存文件的存储路径
- -XX:OnOutOfMemoryError
  - 指定一个可行性程序或者脚本的路径，当发生OOM的时候，去执行这个脚本8946

- 对OnOutOfMemoryError的运维处理
  - 以部署在linux系统/opt/Server目录下的Server.jar为例
  - （1）在run.sh启动脚本中添加jvm参数

    ```bash
    -XX:OnOutOfMemoryError=/opt/Server/restart.sh
    ```
  - （2）restart.sh脚本

    ```bash
    #!/bin/bash
    # linux环境
    pid=$(pf -ef | grep Server.jar | awk '{if($8=="java"){print $2}}')
    kill -9 $pid
    cd /opt/Server/;sh run.sh
    ```

    ```bash
    # windows环境
    echo off
    wmic process where Name='java.exe' delete
    cd D:\Server
    start run.bat
    ```

### 4.3.4. 垃圾收集器相关选项

#### 4.3.4.1. 垃圾回收器复习

- 7款经典垃圾收集器与垃圾分代之间的关系

  ![jvm3-94.png](./image/jvm3-94.png)

- 垃圾收集器的组合关系

  ![jvm3-95.png](./image/jvm3-95.png)

  - 该图已经更新到JKD14
    - 如果把图中的虚线均看做实线，这是JDK8（不包含JDK8）之前垃圾收集器的组合关系
    - 对CMS和MSC之间的线是指如果CMS失败，启动MSC（MSC相当于后备方案）
    - 在JDK8中废弃了两条**红色虚线**的组合（JEP 173），在JDK9中这两根红色虚线做了移除（JEP 214）。
    - 在JDK14中，弃用了**绿色虚线**的组合（JEP 366）。
    - 在JDK14中，删除了**青色虚线框**中的CMS垃圾回收器（JEP 363）。
  - JDK8中默认的垃圾回收器是：Parallel Scavenge GC + Parallel Old GC
  - 为什么Parallel Scanvenge GC不能和CMS搭配使用？
    - Parallel Scanvenge GC底层用的框架和其他不同，不兼容。

#### 4.3.4.2. 查看默认垃圾收集器

- jvm参数

  ```bash
  -XX:+PrintCommandLineFlags  # 查看命令行相关参数(包含使用的垃圾收集器)
  ```

- jinfo

  ```bash
  jinfo -flag 相关垃圾收集器参数 进程ID
  ```

#### 4.3.4.3. 垃圾回收器相关参数

- Serial回收器
  - -XX:+UseSerialGC
    - Serial收集器作为HotSpot中Client模式下的默认新生代垃圾收集器
    - Serial Old是运行在Client模式下默认的老年代垃圾回收器。
    - 指定年轻代和老年代都是用串行收集器。等价于新生代使用Serial GC，老年代用Serial Old GC。可以获得最高的单线程收集效率。

- ParNew回收器
  - -XX:UseParNewGC
    - 手动指定使用ParNew收集器执行内存回收任务。它表示年轻代使用并行收集器，不影响老年代。
  - -XX:ParallelGCThreads=N
    - 限制线程数量，默认开启和CPU数据相同的线程数。

- Parallel回收器（吞吐量优先）
  - -XX:+UseParallelGC
    - 手动指定新生代用Parallel并行收集器执行内存回收任务。
    - 默认jdk8是开启的。
    -  和UseParallelOldGC **相互激活** 
  - -XX:UseParallelOldGC
    - 手动指定老年代使用并行会收收集器。
    - 默认jdk8是开启的。
    - 和UseParallelGC **相互激活** 
  - -XX:ParallelGCThreads
    - 设置新生代并行收集器的线程数，一般的，最好与CPU数量相等，以避免过多的线程影响垃圾收集的性能。
    - 默认情况下，当CPU数量小于等于8个，ParallelGCThreads的值等于CPU数量。
    - 当CPU数量大于8个，ParallelGCThreads的值等于3+[5*CPU_count/8]。
  - -XX:MaxGCPauseMillis
    - 设计垃圾收集器的最大停顿时间（即STW的时间）。单位是毫秒。
    - 为了尽可能把停顿时间控制在MaxGCPauseMillis以内，收集器在工作时会调整Java堆大小或者其他一些参数。
    - 对于用户来说，停顿时间越短体验越好
    - 但是在服务器端，我们注重高并发，整体的吞吐量。所以服务器端适合Parallel，进行控制。
    - **该参数使用需谨慎。**
  - -XX:GCTimeRatio
    - 垃圾收集时间占总时间的比例（=1/（N+1））。用于衡量吞吐量的大小。
    - 取值范围（0，100）。默认99，也就是垃圾回收时间不超过1%。
    - 与前一个-XX:MaxGCPauseMillis参数有一定矛盾性。暂停时间越短，Ratio参数就越容易超过设定的比例。
  - -XX:+UseAdaptiveSizePolicy
    - 设置Parallel Scavenge收集器具有 自适应调节策略
    - 在这种模式下，新生代的大小，Eden和Survivor的比例，晋升老年代的对象年龄等参数会被自动调整，以达到在堆大小、吞吐量和停顿时间之间的平衡点。
    - 在手动调优比较困难的场合，可以直接使用这种自适应的方式，仅指定虚拟机的最大堆、目标吞吐量（GCTimeRatio）和停顿时间（MaxGCPauseMillis），让虚拟机自己完成调优工作。

- CMS回收器（低延迟）
  - -XX:UseConcMarkSweepGC
    - 手动指定使用CMS收集器执行内存回收任务。
    - 开启该参数后会自动将-XX:+UseParNewGC打开。即ParNew（Young区用）+CMS（Old区用）+Serial Old的组合。
  - -XX:CMSInitiatingOccupanyFraction
    - 设置堆内存使用率的阈值，一旦达到阈值，便开始回收。
    - JDK5及以前的版本的默认值为68，即当老年代的利用率达到68%时，会执行一次CMS回收。 **JDK6及以上版本默认值为92%** 
    - 如果内存增长缓慢，这可以设置一个较大的值，大的阈值可以有效降低CMS的出发频率，减少老年代回收的次数可以较为明显地改善应用程序性能。
    - 反之，如果内存使用率增长很快，则应该降低这个阈值，以避免频繁触发老年代串行收集器。因此**通过该选项可以有效降低Full GC的执行次数**。
  - -XX:UseCMSCompactAtFullCollection
    - 用于指定在执行完Full GC后对内存进行压缩整理，以此避免内存碎片的产生
    - 不过由于内存压缩整理过程无法并发执行，所带来的问题就是停顿时间变得更长了。
  - -XX:CMSFullGCsBeforeCompaction
    - 设置在执行多少次Full GC后对内存空间进行压缩整理。

  - -XX:ParallelCMSThreadse
    - 设置CMS的线程数量。
    - CMS默认启用的线程数是（ParallelGCThreads+3）/4
    - ParallelGCThreads是新生代并行收集器的线程数，当CPU资源比较紧张时，受到CMS收集器的影响，应用程序的性能在垃圾回收阶段可能会非常糟糕。

- 补充参数
  - -XX:ConcGCThreads
    - 设置并发垃圾收集的线程数
    - 默认该值是基于ParallelGCThreads计算出来的
  - -XX:+UseCMSInitiatingOccupancyOnly
    - 是否动态可调，用这个参数可以使CMS一直按CMSInitiatingoccupancyFraction设定的值启动
  - -XX:+CMSScavengeBeforeRemark
    - 强制hotspot虚拟机在cms remark阶段之前做一次minor gc
    - 用于提高remark阶段的速度；
  - -XX:+CMSClassUnloadingEnable
    - 如果有的话，启用回收Perm区（JDK8之前）
  - -XX:+CMSParallelInitialEnabled
    - 用于开启CMS initial-mark阶段采用多线程的方式进行标记
    - 用于提高标记速度，在Java8开始已经默认开启；
  - -XX:+CMSParallelRemarkEnabled
    - 用户开启CMS remark阶段采用多线程的方式进行重新标记
    - 默认开启；
  - -XX:+ExplicitGCInvokesConcurrent、-XX:+ExplicitGCInvokesConcurrentAndUnloadsclasses
    - 这两个参数用户指定hotspot虚拟在执行System.gc()时使用CMS周期；
  - -XX:+CMSPrecleaningEnabled
    - 指定CMS是否需要进行Pre cleaning这个阶段

- 特殊说明

  ![在这里插入图片描述](https://img-blog.csdnimg.cn/2021021623272678.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjYzODk0Ng==,size_16,color_FFFFFF,t_70#pic_center)

> G1回收器

- 参数设置

  - **-XX:UseG1GC** 手动指定使用G1收集器执行内存回收任务。
  - **-XX:G1HeapRegionSize** 设置每个Region的大小。值是2的幂，范围是1MB到32MB之间，目标是根据最小的Java堆大小划分出2048个区域。Region默认是堆内存的1/2000。
  - **-XX:MaxGCPauseMillis** 设置期望达到的最大GC停顿时间指标（JVM会尽力实现，但不保证达到）。默认是200ms。
  - **-XX:ParallelGCThread** 设置STW时GC线程数的值。最多设置为8。
  - **-XX:ConcGCThreads** 设置并发标记的线程数，建议将该参数设置为并行垃圾回收线程（ParallelGCThreads）的1/4左右。
  - **-XX:InitiatingHeapOccupancyPercent** 设置触发并发GC周期的Java堆占用阈值。超过此值，就触发GC。默认是45.
  - **-XX:G1NewSizePercent、-XX:G1MaxNewSizePercent** 新生代占整个堆内存的最小百分比（默认5%）、最大百分比（默认60%）
  - **-XX:G1ReservePercent** 保留内存区域，防止to space（Survivor中的to区）溢出

- Mixed GC调优参数

  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210216232740389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjYzODk0Ng==,size_16,color_FFFFFF,t_70#pic_center)

> 怎么选择垃圾回收器

Java垃圾回收器的配置对于JVM优化来说是一个很重要的选择，选择合适的垃圾回收器可以让JVM的性能有一个很大的提升。

1. 优先调整堆的大小让JVM自适应完成
2. 如果内存小于100MB，使用串行收集器
3. 如果单核、单机程序，并且没有停顿时间的要求，串行收集器
4. 如果是多CPU、需要高吞吐量、允许停顿时间超过1秒，选择并行或者JVM自行选择
5. 如果是多CPU、追求地停顿时间，需要快速响应（比如延迟不能超高1秒，如互联网应用），使用并发收集器；官方推荐G1，性能高。**现在互联网的项目，基本都是使用G1**。

- 最后明确一个观点
  - 没有最好的收集器，更没有万能的收集器；调优永远针对特定场景、特定需求，不存在一劳永逸的收集器。

### 4.3.5. GC日志参数

- 常用参数
  - -verbose:gc
    - 输出gc日志信息，默认输出到标准输出
    - 可以独立使用
  - -XX:+PrintGC
    - 等同于-verbose:gc，表示打开简化的gc日志
    - 可以独立使用
  - -XX:+PrintGCDetails
    - 在发生垃圾回收时打印内存回收详细的日击， 并在进程推出时输出当前内存各区域的分配情况
    - 可以独立使用
  - -XX:+PrintGCTimestamps
    - 输出GC发生时的时间戳
    - 不可以独立使用，需要配合-XX:+PrintGCDetails使用
  - -XX:+PrintGCDateStamps
    - 输出GC发生时的时间戳（以日期的形式，如2013-05-04T21:53:59.234+0800）
    - 不可以独立使用，需要配合-XX:+PrintGCDetails使用
  - -XX:+PrintHeapAtGC
    - 每一次GC前和GC后，都打印堆信息
    - 可以独立使用
  - `-Xloggc:<file>`
    - 把GC日志写入到一个文件中去，而不是打印到标准输出中

- 其他参数
  - -XX:+TraceClassLoading
    - 监控类的加载
  - -XX:+PrintGCApplicationStoppedTime
    - 打印GC时线程的停顿时间
  - -XX:+PrintGCApplicationConcurrentTime
    - 垃圾收集之前打印出应用未中断的执行时间
  - -XX:+PrintReferenceGC
    - 记录回收了多少种不同引用类型的引用
  - -XX:+PrintTenuringDistribution
    - 让JVM在每次MinorGC后打印出当前使用的Survivor中对象的年龄分布
  - -XX:+UseGCLogFileRotation
    - 启用GC日志文件的自动转储
  - -XX:NumberOfGClogFiles=1
    - GC日志文件的循环数目
  - -XX:GCLogFileSize=1M
    - 控制GC日志文件的大小

### 4.3.6. 其他参数

- -XX:+DisableExplicitGC
  - 禁止hotspot执行System.gc(),默认禁用
- `-XX:ReservedCodeCacheSize=<n>[g|m]k]、-XX:InitialCodeCacheSize=<n>[g|m]k]`
  - 指定代码缓存的大小
- -XX:+UseCodeCacheFlushing
  - 使用该参数让jvm放弃一些被编译的代码，
  - 避免代码缓存被占满时JVM切换到interpreted-only的情况
- -XX:+DoEscapeAnalysis
  - 开启逃逸分析
- -XX:+UseBiasedLocking
  - 开启偏向锁
- -XX:+UseLargePages
  - 开启使用大页面
- -XX:+UseTLAB
  - 使用TLAB,默认打开
- -XX:+PrintTLAB
  - 打印TLAB的使用情况
- -XX:TLABSize 
  - 设置TLAB大小

## 4.4. 通过Java代码获取JVM参数

- Java提供了java.lang.management包用于监视和管理Java虚拟机和Java运行时中的其他组件
- 它允许本地和远程监控和管理运行Java虚拟机
- 其中ManagementFactory这个类还是挺常用的
- 另外还有Runtime类也可以获取一些内存、CPU核数等相关数据。
- 通过这些api可以监控我们的应用服务器堆内存的使用情况，设置一些阈值进行报警处理。

```java
/**
 * 监控我们的应用服务器的堆内存使用情况，设置一些阈值进行报警等处理
 */
public class MemoryMonitor {
    public static void main(String[] args) {
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit() / 1024 / 1024 + "m");
        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "m");
        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "m");
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: " + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + memorymbean.getNonHeapMemoryUsage());

        System.out.println("=======================通过java来获取相关系统状态============================ ");
        System.out.println("当前堆内存大小totalMemory " + (int) Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m");// 当前堆内存大小
        System.out.println("空闲堆内存大小freeMemory " + (int) Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");// 空闲堆内存大小
        System.out.println("最大可用总堆内存maxMemory " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m");// 最大可用总堆内存大小
    }
}
/*
 INIT HEAP: 256m
 MAX HEAP: 3614m
 USE HEAP: 5m
 
 Full Information:
 Heap Memory Usage: init = 268435456(262144K) used = 5383480(5257K) committed = 257425408(251392K) max = 3790077952(3701248K)
 Non-Heap Memory Usage: init = 2555904(2496K) used = 4789360(4677K) committed = 8060928(7872K) max = -1(-1K)
 =======================通过java来获取相关系统状态============================ 
 当前堆内存大小totalMemory 245m
 空闲堆内存大小freeMemory 240m
 最大可用总堆内存maxMemory 3614m
 */
```

# 5. GC日志分析

## 5.1. GC日志参数

见上节

## 5.2. GC日志格式

### 5.2.1. GC分类

> 针对HotSpot VM的实现，它里面的GC按照回收区域又可分为辆大众类型：一种是部分收集（Partial GC），一种是整堆收集（Full GC）

- 部分收集：不是完整收集整个Java堆的垃圾收集。其中又分为
  - 新生代收集（Minor GC / Young GC）：只有新生代（Eden\S0,S1）的垃圾收集
  - 老年代收集（Major GC / Old GC）：只有老年代的垃圾收集。
    - 目前，只有CMS GC会有单独收集老年代的行为。
    - **注意，很多时候Major GC会和Full GC混淆使用，需要具体分辨是老年代还是整堆回收。**
  - 混合收集（Mixed GC）：收集整个新生代以及部分老年代的垃圾收集
    - 目前，只有G1 GC会有这种行为
- 整堆收集（Full GC）：收集整个Java堆和方法区的垃圾收集。

------

- 哪些情况会出发Full GC？
  - 老年代空间不足
  - 方法区空间不足
  - 显式调用System.gc()
  - Minor GC进入老年代的数据的平均大小 大于 老年代的可用内存
  - 大对象直接进入老年代，而老年代的可用空间不足

### 5.2.2. GC日志分类

- Minor GC

  ![jvm3-96.png](./image/jvm3-96.png)

- Full GC

  ![jvm3-97.png](./image/jvm3-97.png)

### 5.2.3. GC日志结构剖析

#### 5.2.3.1. 垃圾收集器

- 使用Serial收集器在新生代的名字是Default New Generation，因此显示的是"[DefNew"
- 使用ParNew收集器在新生代的名字会变成"[ParNew"，意思是：“Parallel New Generation”
- 使用Parallel Scavenage收集器在新生代的名字是"[PSYoungGen"，这里的JDK1.7使用的就是PSYoungGEN
- 使用Parallel Old Generation收集器在老年代的名字是"[ParOldGen"
- 使用G1收集器的话，会显示"garbage-fisrt heap"

---

**Allocation Failure**：表明本次引起GC的原因是因为在**新生代中没有足够的空间能够存储新的数据了**

#### 5.2.3.2. GC前后情况

- 通过图示，我们可以发现GC日志的规律一般都是：GC前内存占用---->GC后内存占用（该区域内存总大小）

  ```bash
  [PSYoungGen:5986K->696K(8704K)]5986K->704K(9216K)
  ```

  中括号内：GC回收前新生代占用内存大小，回收后大小（新生代堆总大小）

  中括号外：GC回收前新生代和老年代占用内存大小，回收后大小（新生代和老年代总大小）

#### 5.2.3.3. GC时间

> GC日志中有三个时间：user，sys和real

- user：
  - 进程执行用户态代码（核心之外）所使用的时间
  - **这是执行此进程所使用的实际CPU时间** ，其他进程和此进程阻塞的时间并不包括在内
  - 在垃圾收集的情况下，表示GC线程执行所使用的CPU总时间。
- sys：
  - 进程在内核态消耗的CPU时间
  - 即 **在内核执行系统调用或等待系统事件所使用的的CPU时间** 。
- real：
  - 程序从开始到结束所用的时钟时间
  - 这个时间包括其他进程使用的时间片和进程阻塞的时间（比如等待I/O完成）
  - 对于并行gc，这个数字应该接近（用户时间+系统时间）/ 垃圾收集器使用的线程数。

---

- 注意：
  - 由于多核的原因，一般的GC事件中，real time是小于sys+user time的
  - 因为一般是多个线程并发去做GC，所以rea time是要小于sys+user time的
  - 如果real>sys+user time的话，则代码可能存在下列问题：IO负载非常重或者CPU不够用。

### 5.2.4. Minor GC日志解析示例

```bash
2020-11-20T17:19:43.265-0800:0.822:[GC (ALLOCATION FAILURE)[PSYOUNGGEN:76800K->8843k(89600K)]76800K->8449K(29400K), 0.008371 SECS] [TIMES:USER=0.02 SYS=0.01, REAL=0.01 SECS]
```

![jvm3-98.png](./image/jvm3-98.png)

### 5.2.5. Full GC日志解析示例

```bash
2020-11-20T17:19:43.794-0800:1.352:[FULL GC (METADATA GC THREADHOLD)[PSYOUNGGEN:10082K->0K(89600K)][PAROLDGEN]:32K->9638K(204800K)]10114K->9638K(294400K),[METASPACE:20158K->20156K(1067008K)], 0.0285388 SECS][TIMES:USER=0.11 SYS=0.00, REAL=0.03 SECS]
```

![jvm3-99.png](./image/jvm3-99.png)

## 5.3. GC日志分析工具

### 5.3.1. 存储到文件

- 如果想把GC日志存到文件的话，是下面这个参数：

  ```bash
  -Xloggc:/path/to/gc.log
  ```

### 5.3.2. GCEasy

- GCEasy-------一款超好用的在线分析GC日志的网站
- GCEasy可以通过GC日志分析进行内存泄露检测、GC展厅原因分析、JVM配置建议优化等功能，而且是可以免费使用的（有一些服务是收费的）
- [官方网站](https://gceasy.io/)

  ![jvm3-100.png](./image/jvm3-100.png)

### 5.3.3. GCViewer

- 这是一款离线版的GC日志分析工具
- GCViewer是一个免费的、开源的分析小工具，可用于可视化查看由SUN/Oracle，IBM，HP和BEA Java虚拟机产生的垃圾收集器的日志。
- GCViewer可用于可视化Java VM选项-verbose:gc 和.NET生成的数据-Xloggc:<file>。它还计算与垃圾回收相关的性能指标（吞吐量，累积的暂停，最长的暂停等）。当通过更改世代大小或设置初始堆大小来调整特定应用程序的垃圾回收时，此功能非常有用。
- 下载GCViewer工具
  - 源码下载：[网址](https://github.com/chewiebug/GCViewer)
  - 运行版本下载：[网址](https://github.com/chewiebug/GCViewer/wiki/Changelog)
- 启动：运行`java -jar gcviewer-1.3x.jar`
  > 需要java 1.8

### 5.3.4. 其他工具

![jvm3-101.png](./image/jvm3-101.png)

# 6. 面试题

- 支付宝
  - 支付宝三面：JVM 性能调优都做了什么？
- 小米
  - 有做过 JVM 内存优化吗？
  - 从 SQL、JVM、架构、数据库四个方面讲讲优化思路
- 蚂蚁金服
  - JVM 的编译优化
  - JVM 性能调优都做了什么
  - JVM 诊断工具用过哪些？
  - 二面：JVM 怎样调优，堆内存、栈空间设置多少合适
  - 三面：JVM 相关的分析工具使用过哪些？具体的性能优化步骤如何？
- 字节跳动
  - 三面：JVM 如何调优、参数怎么调？
- 拼多多
  - 从 SQL、JVM、架构、数据库四个方面讲讲优化思路
- 京东
  - JVM 诊断工具用过哪些？
  - 每秒几十万并发的秒杀系统为什么会频繁发生 GC？
  - 日均百万级交易系统如何优化 JVM？
  - 线上生产系统 OOM 如何监控及定位与解决？
  - 高并发系统如何基于 G1 垃圾回收器优化性能？

# 7. 参考资料

- [ ][JVM调优的几种场景](https://mp.weixin.qq.com/s/VAvqirv2c22viJNZ92WiNg)
