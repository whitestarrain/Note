TODO: [Operating Systems:Three Easy Pieces](https://pages.cs.wisc.edu/~remzi/OSTEP/Chinese/)

# 1. 操作系统基础

## 1.1. 什么是操作系统

- 操作系统本质上是一个运行在计算机上的软件程序 ，用于管理计算机硬件和软件资源，屏蔽了硬件层的复杂性。
- 电脑上的所有应用程序都通过操作系统来调用系统内存以及磁盘等等硬件。
- 另外，操作系统的内核（Kernel）是操作系统的核心部分，它负责系统的内存管理，硬件设备的管理，文件系统的管理以及应用程序的管理。 内核是连接应用程序和硬件的桥梁，决定着系统的性能和稳定性。

## 1.2. 用户态与内核态

为了确保操作系统的正确运行，必须区分操作**系统代码**和**用户代码**的执行。大多数计算机系统采用硬件支持，以便区分各种执行模式。
至少需要两种单独的运行模式：用户模式和内核模式

![os-1](./image/os-1.png)

- 用户态(user mode) : 用户态运行的进程可以直接读取用户程序的数据。
- 系统态(kernel mode):可以简单的理解系统态运行的进程或程序几乎可以访问计算机的任何资源，不受限制。

当计算机系统执行用户应用时，系统处于用户模式。然而，当用户应用通过系统调用，请求操作系统服务时，系统必须从用户模式切换到内核模式，以满足请求

# 2. 操作系统结构

## 2.1. 操作系统的服务有哪些

> 待完善

- 用户界面，包括命令行界面，批处理界面以及图形化界面
- 程序执行
- I/O 操作
- 文件系统操作
- 通信
- 错误检测
- 资源分配
- 记账
- 保护和安全

## 2.2. 什么是 shell

Shell 是一个应用程序，它连接了用户和 Linux 内核，让用户能够更加高效、安全、低成本地使用 Linux 内核，这就是 Shell 的本质。

Shell 不属于内核的一部分。大部分情况下 Shell 都会去调用内核暴露出来的接口，这就是在使用内核，只是这个过程被 Shell 隐藏了起来，它自己在背后默默进行，我们看不到而已。

## 2.3. 系统调用

系统调用提供操作系统服务的接口，凡是与系统态级别的资源有关的操作，都必须通过系统调用方式向操作系统提出服务请求，并由操作系统代为完成。

这些系统调用按功能大致可分为如下几类：

- 设备管理。完成设备的请求或释放，以及设备启动等功能。
- 文件管理。完成文件的读、写、创建及删除等功能。
- 进程控制。完成进程的创建、撤销、阻塞及唤醒等功能。
- 进程通信。完成进程之间的消息传递或信号传递等功能。
- 内存管理。完成内存的分配、回收以及获取作业占用内存区大小及地址等功能。

# 3. 进程

## 3.1. 什么是进程

首先说一下什么是程序，程序只是被动（passive)实体，如存储在磁盘上包含一系列指令的文件（经常称为可执行文件（executable file))。

相反，进程是活动（active)实体，具有一个程序计数器用于表示下个执行命令和一组相关资源。

当一个可执行文件被加载到内存时，这个程序就成为进程。 进程是一个程序及其数据在处理器上顺序执行时所发生的活动。

进程是系统进行资源分配和调度的基本单位。

一个进程由程序，数据和PCB组成。

## 3.2. 三种特殊进程

- 孤儿进程：如果父进程先退出,子进程还没退出那么子进程将被托孤给init进程,这是子进程的父进程就是init进程(1号进程).其实还是很好理解的

- 僵尸进程：如果我们了解过linux进程状态及转换关系,我们应该知道进程这么多状态中有一种状态是僵死状态,就是进程终止后进入僵死状态(zombie),等待告知父进程自己终止,后才能完全消失.但是如果一个进程已经终止了,但是其父进程还没有获取其状态,那么这个进程就称之为僵尸进程.僵尸进程还会消耗一定的系统资源,并且还保留一些概要信息供父进程查询子进程的状态可以提供父进程想要的信息.一旦父进程得到想要的信息,僵尸进程就会结束.

- 守护进程：同样我们需要了解一下什么是守护进程,守护进程就是在后台运行,不与任何终端关联的进程,通常情况下守护进程在系统启动时就在运行,它们以root用户或者其他特殊用户(apache和postfix)运行,并能处理一些系统级的任务.习惯上守护进程的名字通常以d结尾(sshd),但这些不是必须的.

## 3.3. 为什么要引入进程

早先的操作系统是单道批处理的，但是cpu是高效率的，而IO是低速的，就会出现cpu要等待IO的情况；从而降低了实际效率。后来就引入多道批处理；而程序在执行的过程中又会因为共享资源而导致程序在执行的过程中相互限制；所有后来引入进程用来给程序提供一个抽象的概念，他能申请道系统资源并且独立给程序提供资源，从而解决原来的程序之间因为资源共享而相互限制的问题。这样就可以提高系统资源的利用率以及系统的处理能力

## 3.4. 程序与进程之间的区别

- （1）进程更能真实地描述并发，而程序不能。
- （2）进程由程序和数据两部分组成，进程是竞争计算机系统有限资源的基本单位，也是进程处理机调度的基本单位。
- （3）程序是静态的概念；进程是程序在处理机上一次执行的过程，是动态的概念。
- （4）进程有生存周期，有诞生有消亡。是短暂的；而程序是相对长久的。
- （5）一个程序可以作为多个进程的运行程序；一个进程也可以运行多个程序。
- （6）进程具有创建其他进程的功能；而程序没有。

## 3.5. 作业与进程

一个进程是一个程序对某个数据集的执行过程，是分配资源的基本单位。作业是用户需要计算机完成的某项任务，是要求计算机所做工作的集合。一个作业的完成要经过作业提交、作业收容、作业执行和作业完成 4 个阶段。而进程是对已提交完毕的程序所执行过程的描述，是资源分配的基本单位。

- 其主要区别如下：

  - （1）作业是用户向计算机提交任务的任务实体。在用户向计算机提交作业后，系统将它放入外存中的作业等待队列中等待执行。而进程则是完成用户任务的执行实体，是向系统申请分配资源的基本单位。任一进程，只要它被创建，总有相应的部分存在于内存中。
  - （2）一个作业可由多个进程组成，且必须至少由一个进程组成，反过来则不成立。
  - （3）作业的概念主要用在批处理系统中，像 UNIX 这样的分时系统中就没有作业的概念。而进程的概念则用在几乎所有的多道程序系统中。

- 作业、进程和程序之间的联系：
  - 一个作业通常包括程序、数据和操作说明书 3 部分。每一个进程由 PCB、程序和数据集合组成。这说明程序是进程的一部分，是进程的实体。因此，一个作业可划分为若干个进程来完成，而每一个进程有其实体----程序和数据集合。

## 3.6. 操作系统进程的状态

![os-2](./image/os-2.png)

- 创建状态(new) ：进程正在被创建，尚未到就绪状态。
- 就绪状态(ready) ：进程已处于准备运行状态，即进程获得了除了处理器之外的一切所需资源，一旦得到处理器资源(处理器分配的时间片)即可运行。
- 运行状态(running) ：进程正在处理器上上运行(单核 CPU 下任意时刻只有一个进程处于运行状态)。
- 阻塞状态(waiting) ：又称为等待状态，进程正在等待某一事件而暂停运行如等待某资源为可用或等待 IO 操作完成。即使处理器空闲，该进程也不能运行。
- 结束状态(terminated) ：进程正在从系统中消失。可能是进程正常结束或其他原因中断退出运行。

<br /><br />

拓展：[java 线程状态](../java/java并发1-基础.md)

## 3.7. 进程运行相关系统调用

待做

## 3.8. 什么是 PCB

进程控制块（Process Contral Block），也称为任务控制块。

系统利用 PCB 来描述进程的基本情况和运行状态，进而控制和管理进程。

相应的，有程序段、相关数据段和 PCB 三部分构成了进程映像（进程实体）。

所谓创建进程，实质上是创建进程映像中的 PCB；而撤销进程，实质上是撤销进程的 PCB。指的注意的是，进程映像是静态的，而进程是动态的。

- 了解：PCB 内容
  - 进程状态（process state):状态可以包括新的、就绪、运行、等待、停止等。程序计数器（program counter):计数器表示进程将要执行的下个指令的地址。
  - CPU 寄存器（CPU register):根据计算机体系结构的不同，寄存器的类型和数量也会不同。它们包括累加器、索引寄存器、堆栈指针、通用寄存器和其他条件码信息寄存器。在发生中断时，这些状态信息与程序计数器一起需要保存，以便进程以后能正确地继续执行
  - CPU 调度信息（CPU-scheduling information):这类信息包括进程优先级、调度队列的指针和其他调度参数。（第 5 章讨论进程调度）。
  - 内存管理信息（memory-management information):根据操作系统使用的内存系统，这类信息可以包括基地址和界限寄存器的值、页表或段表（第 8 章）。
  - 记账信息（accounting information):这类信息包括 CPU 时间、实际使用时间、时间期限、记账数据、作业或进程数量等。
  - I/O 状态信息（I/O status information):这类信息包括分配给进程的 I/O 设备列表、打开文件列表等。

## 3.9. 进程调度

### 3.9.1. 目的

进程调度器选择一个可用的进程到 CPU 上执行，最大化 CPU 利用率。

### 3.9.2. 相关队列与调度器

- **作业队列(job queue)**:进程在进入系统时，会被加到作业队列（job queue),这个队列包括系统内的所有进程。（位于外存）
- **就绪队列(ready queue)** ：驻留在内存中的、就绪的、等待运行的进程保存在就绪队列（ready queue)

- 通常，对于批处理系统，提交的进程多于可以立即执行的。这些进程会被保存到大容量存储设备（通常为磁盘）的缓冲池，以便以后执行。
  - **长期调度程序（long-term scheduler)或作业调度程序（job scheduler)** 从该池中选择进程，加到内存，以便执行。
  - **短期调度程序(short-term scheduler)或 CPU 调度程序（CPU scheduler)** 从准备执行的进程中选择进程，并分配 CPU。

有的系统，可能没有或极少采用长期调度程序。例如， **UNIX 或微软 Windows 的分时系统通常没有长期调度程序** ，
只是简单将所有新进程放于内存，以供短期调度程序使用。
这些系统的稳定性取决于物理限制（如可用的终端数）或用户的自我调整。如果多用户系统性
能下降到令人难以接受，那么有的用户就会退出。

有的操作系统如分时系统，可能引入一个额外的 **中期调度程序（medium-term scheduler)** 。
中期调度程序的核心思想是可将进程从内存（或从 CPU 竞争）中移出，从而降低多道程序程度。
之后，进程可被重新调入内存，并从中断处继续执行。这种方案称为交换（swap)。
通过中期调度程序，进程可换出（swap out),并在后来可换入（swap in)。
为了改善进程组合，或者由于内存需求改变导致过度使用内存从而需要释放内存，就有必要使用交换。

## 3.10. 上下文切换

切换 CPU 到另一个进程需要保存当前进程状态和恢复另一个进程的状态，这个任务称为 **上下文切换（context switch)** 。

当进行上下文切换时，内核会将旧进程状态保存在其 PCB 中，然后加载经调度而要执行的新进程的上下文。

上下文切换的时间是纯粹的开销，因为在切换时系统并没有做任何有用工作。

## 3.11. 进程间通信

### 3.11.1. 整体分类

- **共享内存**
  - 概念： 在共享存储器系统中，相互通信的进程共享某些数据结构或共享存储区，进程之间能够通过这些空间进行通信。如生产者消费者问题。

- **消息传递**
  - 概念：消息传递提供一种机制，以便允许进程不必通过共享地址空间来实现通信和同步。
  - 种类
    - 直接通信方式：发送进程直接把消息发送给接收进程，并将它挂在接收进程的消息缓冲队列上，接收进程从消息缓冲队列中取得消息。
    - 间接通信方式：发送进程把消息发送到某个中间实体中，接收进程从中间实体中取得消息。这种中间实体一般称为信箱，这种通信方式又称为信箱通信方式。该通信方式广泛应用于计算机网络中，相应的通信系统称为电子邮件系统。

- **管道通信**
  - 所谓“管道”，是指用于连接一个读进程和一个写进程以实现它们之间通信的一个共享文件，又名pipe文件。
  - 向管道（共享文件）提供输入的发送进程（即写进程），以字符流形式将大量的数据送入（写）管道；
  - 而接收管道输出的接收进程（即读进程），则从管道中接收（读）数据。
  - 为了协调双方的通信，管道机制必须提供以下三方面的协调能力：互斥、同步和确定对方的存在。

### 3.11.2. 具体方式

[详细介绍](https://www.jianshu.com/p/c1015f5ffa74)

- **管道/匿名管道(Pipes)** ：用于具有亲缘关系的父子进程间或者兄弟进程之间的通信。
- **有名管道(Names Pipes)** : 匿名管道由于没有名字，只能用于亲缘关系的进程间通信。为了克服这个缺点，提出了有名管道。有名管道严格遵循 **先进先出(first in first out)** 。有名管道以磁盘文件的方式存在，可以实现本机任意两个进程通信。
- **信号(Signal)** ：信号是一种比较复杂的通信方式，用于通知接收进程某个事件已经发生；
- **消息队列(Message Queuing)** ：消息队列是消息的链表,具有特定的格式,存放在内存中并由消息队列标识符标识。管道和消息队列的通信数据都是先进先出的原则。与管道（无名管道：只存在于内存中的文件；命名管道：存在于实际的磁盘介质或者文件系统）不同的是消息队列存放在内核中，只有在内核重启(即，操作系统重启)或者显示地删除一个消息队列时，该消息队列才会被真正的删除。消息队列可以实现消息的随机查询,消息不一定要以先进先出的次序读取,也可以按消息的类型读取.比 FIFO 更有优势。消息队列克服了信号承载信息量少，管道只能承载无格式字 节流以及缓冲区大小受限等缺。
- **信号量(Semaphores)** ：信号量是一个计数器，用于多进程对共享数据的访问，信号量的意图在于进程间同步。这种通信方式主要用于解决与同步相关的问题并避免竞争条件。
- **共享内存(Shared memory)** ：使得多个进程可以访问同一块内存空间，不同进程可以及时看到对方进程中对共享内存中数据的更新。这种方式需要依靠某种同步操作，如互斥锁和信号量等。可以说这是最有用的进程间通信方式。
- **套接字(Sockets)** : 此方法主要用于在客户端和服务器之间通过网络进行通信。套接字是支持 TCP/IP 的网络通信的基本操作单元，可以看做是不同主机之间的进程进行双向通信的端点，简单的说就是通信的两方的一种约定，用套接字中的相关函数来完成通信过程。

# 4. 多线程编程

## 4.1. 进程和线程区别

![os-3](./image/os-3.png)

线程是资源分配的基本单位，进程是资源调度的基本单位。

一个进程中可以有多个线程，线程共享进程的代码段，数据，文件。但是每个线程有自己的寄存器和堆栈。

区别可以概括为以下几个方面：

- 1） 调度：在引入线程的操作系统中，线程是独立调度的基本单位，进程是资源拥有的基本单位。
- 2） 拥有资源：进程是拥有资源的基本单位，而线程不拥有系统资源，单线程可以防伪其隶属进程的系统资源。
- 3） 并发性：在引入线程的操作系统中，不仅进程之间可以并发执行，线程之间也可以并发执行，从而是操作系统具有更好的并发性，大大提高了系统的吞吐量。
- 4） 系统开销：线程开销极小。
- 5） 地址空间和其他资源：进程的地址空间之间相互独立，同一进程的各线程间共享进程的资源，进程内的线程对进程外的其他进程不可见。
- 6） 通信方面：进程间通信需要进程同步和互斥手段的辅助，以保证数据的一致性，而线程间可以直接读写进程数据段来进行通信。

<br /><br />

![os-4](./image/os-4.png)

如果从 JVM 角度来说，一个进程中可以有多个线程，多个线程共享进程的 **堆和方法区 (JDK1.8 之后的元空间)** 资源，但是每个线程有自己的 **程序计数器、虚拟机栈和本地方法栈** 。

TODO: [进程和线程 19 个问题](https://zhuanlan.zhihu.com/p/317144739)

## 4.2. 多线程模型

- 多对一模型。因为无法利用多个核，所以几乎没有系统在使用该模型。
- 一对一模型
- 多对多模型
- 双层模型

![os-5](./image/os-5.png)

![os-6](./image/os-6.png)

## 4.3. 多线程问题

待做

### 4.3.1. fork和exec

### 4.3.2. 信号处理

> 面试遇到过，ctrl+c

- 信号作用:
  - UNIX信号(signa)用于通知进程某个特定事件已经发生。

- 模式/特点:
  > 所有信号,无论是同步的还是异步的,遵循相同的模式
  - 信号是由特定事件的发生而产生的。
  - 信号被传递给某个进程。
  - 信号一旦收到就应处理。

- 产生:
  > **信号的接收可以是同步的或是异步的,这取决于事件信号的来源和原因**
  - 同步信号：
    - 同步信号的例子包括非法访问内存或被0所除。
    - 如果某个运行程序执行这类动作,那么就会产生信号。
    - 同步信号发送到由于执行操作导致这个信号的同一进程(这就是为什么被认为是同步的)。
  - 异步信号：
    - 当一个信号是由运行程序以外的事件产生的,该进程就异步接收这一信号。
    - 这种信号的例子包括使用特殊键(比如`control C`)来终止进程,或者定时器到期等。
    - 通常,异步信号发送到另一个进程。

- 信号处理程序可以分为两种
  - 缺省的信号处理程序。
    - 每个信号都有一个缺省信号处理程序( default signal handler);
    - 在处理信号时,它由内核来运行。
  - 用户定义的信号处理程序。
    - 缺省动作可以通过用户定义信号处理程序( user-defined signal handler)来改写。
    - 信号可按不同方式处理。有的信号可以忽略(如改变窗口大小),其他的(如非法内存访问)可能要通过终止程序来处理。

- 信号传递方法
  > **取决于产生信号的类型**
  - 同步异步
    - 同步信号需要传递到产生这一信号的线程,而不是进程的其他线程。
    - 不过,对于异步信号,情况就不是那么明显了。有的异步信号,如终止进程的信号(例如`ctrl c`),应该传递到所有线程。
  - 单线程多线程
    - 单线程程序的信号处理比较简单,信号总是传给进程的。
    - 不过,对于多线程程序,信号传递比较复杂,因为一个进程可能具有多个线程。通常具有如下选择:
      - 传递信号到信号所适用的线程。
      - 传递信号到进程内的每个线程。
      - 传递信号到进程内的某些线程。
      - 规定一个特定线程以接收进程的所有信号。
  - 普遍情况：
    - 传递信号的标准UNIX函数为kill(pid.t pid, int signal)
    - 这个函数指定将一个特定信号(signa1)传递到一个进程(pid)。
    - 大多数多线程版的UNX允许线程指定它接收什么信号和拒绝什么信号。
    - 因此,在有些情况下,一个异步信号只能传递给那些不拒绝它的线程。
    - 不过, **因为信号只能处理一次,所以信号通常传到第一个不拒绝它的线程** 。
    - POSIX Pthreads提供以下函数

- 扩展，windows
  - 虽然 Windows并不显式提供信号支持,但是它们允许通过异步过程调用( Asynchronous Procedure Call,APC)来模拟。
  - APC功能允许用户线程,指定一个函数以便在用户线程收到特定事件通知时能被调用。
  - 正如名称所示,APC与UNX的异步信号大致相当。不过,UNIX需要面对如何处理多线程环境下的信号,而APC较为简单,因为APC传给特定线程而非进程。

### 4.3.3. 线程撤销


### 4.3.4. 线程本地存储

### 4.3.5. 调度程序激活

### 4.3.6. 硬件规格与并发线程数关系

线程应该是操作系统的概念，cpu中的多线程指的是什么？

# 5. 进程调度

## 5.1. 调度程序与调度准则

- 调度程序作用
  - 切换上下文。
  - 切换到用户模式。
  - 跳转到用户程序的合适位置，以便重新启动程序
- 调度准则
  - CPU使用率
  - 吞吐量
  - 周转时间
  - 等待时间
  - 响应时间

## 5.2. 调度算法

- 先到先服务(FCFS)调度算法 :非抢占， 从就绪队列中选择一个最先进入该队列的进程为之分配资源，使它立即执行并一直执行到完成或发生某事件而被阻塞放弃占用 CPU 时再重新调度。
- 短作业优先(SJF)的调度算法 : 从就绪队列中选出一个估计运行时间最短的进程为之分配资源，使它立即执行并一直执行到完成或发生某事件而被阻塞放弃占用 CPU 时再重新调度。
  - 非抢占
  - 抢占(最短剩余时间优先)
- 优先级调度 ： 为每个流程分配优先级，首先执行具有最高优先级的进程，依此类推。具有相同优先级的进程以 FCFS 方式执行。可以根据内存要求，时间要求或任何其他资源要求来确定优先级。
  - 非抢占
  - 抢占
- 时间片轮转调度算法 :抢占，时间片轮转调度是一种最古老，最简单，最公平且使用最广的算法，又称 RR(Round robin)调度。每个进程被分配一个时间段，称作它的时间片，即该进程允许运行的时间。
- 多级反馈队列调度算法 ：前面介绍的几种进程调度的算法都有一定的局限性。如短进程优先的调度算法，仅照顾了短进程而忽略了长进程 。多级反馈队列调度算法**既能使高优先级的作业得到响应又能使短作业**（进程）迅速完成。，因而它是**目前被公认的一种较好的进程调度算法**，UNIX 操作系统采取的便是这种调度算法。
  <details>
  <summary style="color:red;">说明</summary>

  通常在使用多级队列调度算法时，进程进入系统时被永久地分配到某个队列。例如，如果前台和后台进程分别具有单独队列，那么进程并不从一个队列移到另一个队列，这是因为进程不会改变前台或后台的性质。这种设置的优点是调度开销低，缺点是不够灵活。

  相反，多级反馈队列调度算法允许进程在队列之间迁移。这种想法是，根据不同 CPU 执行的特点来区分进程。如果进程使用过多的 CPU 时间，那么它会被移到更低的优先级队列。这种方案将 I/O 密集型和交互进程放在更高优先级队列上。 此外，在较低优先级队列中等待过长的进程会被移到更高优先级队列。这种形式的老化可阻止饥饿的发生。

  ![os-22](./image/os-22.png)

  例如，一个多级反馈队列的调度程序有三个队列，从 0 到 2（图 1）。调度程序首先执行队列 0 内的所有进程。只有当队列 0 为空时，它才能执行队列 1 内的进程。类似地，只有队列 0 和 1 都为空时，队列 2 的进程才能执行。到达队列 1 的进程会抢占队列 2 的进程。同样，到达队列 0 的进程会抢占队列 1 的进程。

  每个进程在进入就绪队列后，就被添加到队列 0 内。队列 0 内的每个进程都有 8ms 的时间片。如果一个进程不能在这一时间片内完成，那么它就被移到队列 1 的尾部。如果队列 0 为空，队列 1 头部的进程会得到一个 16ms 的时间片。如果它不能完成，那么将被抢占，并添加到队列 2。只有当队列 0 和 1 为空时，队列 2 内的进程才可根据 FCFS 来运行。

  这种调度算法将给那些 CPU 执行不超过 8ms 的进程最高优先级。这类进程可以很快得到 CPU，完成 CPU 执行，并且处理下个 I/O 执行。

  [算法详细说明](http://c.biancheng.net/view/1249.html)
  </details>


---

- 完全公平调度算法（CFS）：Linux线程调度使用的调度算法，目前用得最多

# 6. 同步

## 6.1. 线程间同步的方式

线程同步是两个或多个共享关键资源的线程的并发执行。应该同步线程以避免关键的资源使用冲突。操作系统一般有下面三种线程同步的方式：

- **互斥量(Mutex)** ：采用互斥对象机制，只有拥有互斥对象的线程才有访问公共资源的权限。因为互斥对象只有一个，所以可以保证公共资源不会被多个线程同时访问。比如 Java 中的 synchronized 关键词和各种 Lock 都是这种机制。
- **信号量(Semphares)** ：它允许同一时刻多个线程访问同一资源，但是需要控制同一时刻访问此资源的最大线程数量
- **事件(Event)**:Wait/Notify：通过通知操作的方式来保持多线程同步，还可以方便的实现多线程优先级的比较操

## 6.2. 进程间同步的方式

原子操作、信号量机制、自旋锁管程、会合、分布式系统


## 6.3. 临界区与临界资源

- 临界区
  - 指的是一个访问共用资源（例如：共用设备或是共用存储器）的程序片段，而这些共用资源又无法同时被多个线程访问的特性。
  - 当有线程进入临界区段时，其他线程或是进程必须等待，有一些同步的机制必须在临界区段的进入点与离开点实现，以确保这些共用资源是被互斥获得使用。只能被单一线程访问的设备，例如：打印机。

- 临界资源：
  - 一次仅允许一个进程使用的资源称为临界资源。许多物理设备都属于临界资源，如输入机、打印机、磁带机等。

- 临界问题解决要求：
  - **互斥（mutual exclusion)**:如果进程P1在其临界区内执行，那么其他进程都不能在其临界区内执行。
  - **进步（progress)**:如果没有进程在其临界区内执行，并且有进程需要进入临界区，那么只有那些不在剩余区内执行的进程可以参加选择，以便确定谁能下次进入临界区，而且这种选择不能无限推迟。
  - **有限等待（bounded waiting)**:从一个进程做出进入临界区的请求直到这个请求允许为止，其他进程允许进入其临界区的次数具有上限。

## 6.4. 互斥锁

## 6.5. 信号量

## 6.6. 经典问题

### 6.6.1. 生产者消费者

### 6.6.2. 哲学家就餐

# 7. 死锁

## 7.1. 产生条件

- **互斥（mutual exclusion)**:至少有一个资源必须处于非共享模式，即一次只有一个进程可使用。如果另一进程申请该资源，那么申请进程应等到该资源释放为止。
- **占有并等待（hold and wait)**:一个进程应占有至少一个资源，并等待另一个资源，而该资源为其他进程所占有。
- **非抢占（no preemption)**:资源不能被抢占，即资源只能被进程在完成任务后自愿释放。
- **循环等待（circular wait)**:有一组等待进程（Po,P1,...,Pn},Po等待的资源为P1占有，P1等待的资源为P2占有，...，Pn-1等待的资源为Pn占有，Pn等待的资源为Po占有。

## 7.2. 死锁预防

> 死锁预防的基本思想是 只要确保死锁发生的四个必要条件中至少有一个不成立，就能预防死锁的发生，具体方法包括：

- 打破互斥条件：允许进程同时访问某些资源。但是，有些资源是不能被多个进程所共享的，这是由资源本身属性所决定的，因此，这种办法通常并无实用价值。
- 打破占有并等待条件：可以实行资源预先分配策略(进程在运行前一次性向系统申请它所需要的全部资源，若所需全部资源得不到满足，则不分配任何资源，此进程暂不运行；只有当系统能满足当前进程所需的全部资源时，才一次性将所申请资源全部分配给该线程)或者只允许进程在没有占用资源时才可以申请资源（一个进程可申请一些资源并使用它们，但是在当前进程申请更多资源之前，它必须全部释放当前所占有的资源）。但是这种策略也存在一些缺点：在很多情况下，无法预知一个进程执行前所需的全部资源，因为进程是动态执行的，不可预知的；同时，会降低资源利用率，导致降低了进程的并发性。
- 打破非抢占条件：允许进程强行从占有者哪里夺取某些资源。也就是说，但一个进程占有了一部分资源，在其申请新的资源且得不到满足时，它必须释放所有占有的资源以便让其它线程使用。这种预防死锁的方式实现起来困难，会降低系统性能。
- 打破循环等待条件：实行资源有序分配策略。对所有资源排序编号，所有进程对资源的请求必须严格按资源序号递增的顺序提出，即只有占用了小号资源才能申请大号资源，这样就不回产生环路，预防死锁的发生。

## 7.3. 死锁避免

死锁避免的基本思想是动态地检测资源分配状态，以确保循环等待条件不成立，从而确保系统处于安全状态。所谓安全状态是指：如果系统能按某个顺序为每个进程分配资源（不超过其最大值），那么系统状态是安全的，换句话说就是，如果存在一个安全序列，那么系统处于安全状态。资源分配图算法和银行家算法是两种经典的死锁避免的算法，其可以确保系统始终处于安全状态。其中，资源分配图算法应用场景为每种资源类型只有一个实例(申请边，分配边，需求边，不形成环才允许分配)，而银行家算法应用于每种资源类型可以有多个实例的场景。

### 7.3.1. 安全状态

如果系统能按一定顺序为每个进程分配资源（不超过它的最大需求）,仍然避免死锁，那么系统的状态就是安全的（safe)。

更为正式地说，只有存在一个安全序列（safe sequence),系统才处于安全状态。

### 7.3.2. 资源分配图

### 7.3.3. 银行家算法

## 7.4. 死锁检测

## 7.5. 死锁恢复

- **进程终止**，有两种方式：
  - 中止所有死锁进程。这种方法显然会打破死锁环，但是代价也大。这些死锁进程可能已计算了较长时间；这些部分计算的结果也要放弃，并且以后可能还要重新计算。
  - 一次中止一个进程，直到消除死锁循环为止。这种方法的开销会相当大，这是因为每次中止一个进程，都应调用死锁检测算法，以确定是否仍有进程处于死锁。
- **资源抢占**，需要解决三个问题：
  - 选择牺牲进程：即确定抢占的顺序，使代价最小。
  - 回滚：被抢占的进程应该回滚到安全状态。
  - 饥饿：如何确保不会发生饥饿？即如何保证资源不会总是从同一个进程中被抢占。

# 8. 内存管理策略

## 8.1. 两个寄存器

每个进程都有一个单独的内存空间，单独的内存空间可以保护进程而不互相影响。确保一个进程只能访问属于自己的合法的地址，是通过两个寄存器实现的：

- 基地址寄存器:好友最小的合法的物理内存地址
- 界限地址寄存器:指定了范围的大小

![os-7](./image/os-7.png)

## 8.2. 逻辑,物理地址空间与虚拟寻址

CPU生成的地址通常称为 **逻辑地址（logical address)** ,

而内存单元看到的地址（即加载到内存地址寄存器（memory-address register)的地址）通常称为 **物理地址（physical address)** 。

现代处理器使用的是一种称为 虚拟寻址(Virtual Addressing) 的寻址方式。使用虚拟寻址，CPU 需要将虚拟地址翻译成物理地址，这样才能访问到真实的物理内存。 实际上完成虚拟地址转换为物理地址转换的硬件是 CPU 中含有一个被称为 内存管理单元（Memory Management Unit, MMU） 的硬件

![os-8](./image/os-8.png)

---

为什么要有虚拟地址空间？

- 如果直接访问内存
  - 用户程序可以访问任意内存，寻址内存的每个字节，这样就**很容易（有意或者无意）破坏操作系统**，造成操作系统崩溃。
  - 想要同时运行多个程序特别困难，比如你想同时运行一个微信和一个 QQ 音乐都不行。为什么呢？举个简单的例子：微信在运行的时候给内存地址 1xxx 赋值后，QQ 音乐也同样给内存地址 1xxx 赋值，那么 QQ 音乐对内存的赋值就会覆盖微信之前所赋的值，这就造成了微信这个程序就会崩溃。

**总结来说：如果直接把物理地址暴露出来的话会带来严重问题，比如可能对操作系统造成伤害以及给同时运行多个程序造成困难。**

- 通过虚拟地址访问内存有以下优势：
  - 程序可以使用一系列相邻的虚拟地址来访问物理内存中不相邻的大内存缓冲区。
  - 程序可以使用一系列虚拟地址来访问大于可用物理内存的内存缓冲区。当物理内存的供应量变小时，内存管理器会将物理内存页（通常大小为 4 KB）保存到磁盘文件。数据或代码页会根据需要在物理内存与磁盘之间移动。
  - 不同进程使用的虚拟地址彼此隔离。一个进程中的代码无法更改正在由另一进程或操作系统使用的物理内存。

## 8.3. 交换

进程必须在内存中以便执行。

不过，进程可以暂时从内存交换（swap)到备份存储(backing store),当再次执行时再调回到内存中。

![os-9](./image/os-9.png)

## 8.4. 内存管理机制

### 8.4.1. 概述

简单分为**连续分配管理方式**和**非连续分配管理方式**这两种。连续分配管理方式是指为一个用户程序分配一个连续的内存空间，常见的如**块式管理**。同样地，非连续分配管理方式允许一个程序使用的内存分布在离散或者说不相邻的内存中，常见的如**页式管理**和**段式管理**。

### 8.4.2. 块式管理

> 或者称为**多分区方法**

块式管理 ： 远古时代的计算机操系统的内存管理方式。将内存分为几个固定大小的块，每个块中只包含一个进程。如果程序运行需要内存的话，操作系统就分配给它一块，如果程序运行只需要很小的空间的话，分配的这块内存很大一部分几乎被浪费了。这些在每个块中未被利用的空间，我们称之为碎片。

### 8.4.3. 可变分区相关概念

- 孔
  - 对于**可变分区**（variable-partition)方案
  - 操作系统有一个表，用于记录哪些内存可用和哪些内存已用。
  - 开始，所有内存都可用于用户进程，因此可以作为一大块的可用内存，称为孔（hole)。
  - 最后，正如将会看到的，内存有一个集合，以包含各种大小的孔。
- 动态分配问题：
  > 为进程分配孔的策略
  - 首次适应:分配首个足够大的孔
  - 最优适应:分配最小的足够大的孔
  - 最差适应:分配最大的孔
- 外部碎片
  - 概念：
    - 随着进程加载到内存和从内存退出，空闲内存空间被分为小的片段。
    - 当总的可用内存之和可以满足请求但并不连续时，这就出现了外部碎片问题:
    - 存储被分成了大量的小孔。这个问题可能很严重。在最坏情况下，每两个进程之间就有空闲（或浪费的）块。如果这些内存是一整块，那么可能可以再运行多个进程。
  - 解决：允许进程的逻辑地址空间是不连续的；这样，只要有物理内存可用，就允许为进程分配内存。两种互补的技术为**分页和分段**。
- 内部碎片
  - 假设有一个18464字节大小的孔，并采用多分区分配方案。假设有一个进程需要18462字节。
  - 如果只能分配所要求的块，那么还剩下一个2字节的孔。
  - **维护这一小孔的开销要比孔本身大很多。**
  - 因此，通常按固定大小的块为单位（而不是字节）来分配内存。
  - 采用这种方案，**进程所分配的内存可能比所需的要大**。这**两个数字之差称为内部碎片**（internal fragmentation)
  - 这部分内存在分区内部，但又不能用。

### 8.4.4. 页式管理

#### 8.4.4.1. 基本概念

页式管理 ：把主存分为大小相等且固定的一页一页的形式，页较小，相对相比于块式管理的划分力度更大，提高了内存利用率，减少了碎片。页式管理通过页表对应逻辑地址和物理地址。

分页允许物理内存是**非连续的**。(看下面的图中，页在物理内存中的分布)

实现分页的基本方法涉及将物理内存分为固定大小的块，称为**帧或页帧（frame)**;
而将逻辑内存也分为同样大小的块，称为 **页或页面（page)** 。

由CPU生成的每个地址分为两部分： **页码（page number)(p)和页偏移（page offset)(d)** 。

页码作为页表的索引。页表包含每页所在物理内存的**基地址**。

这个**基地址与页偏移**的组合就形成了物理内存地址。

![os-10](./image/os-10.png)

#### 8.4.4.2. 保护

- 有效位-无效位
- 共享页

#### 8.4.4.3. 硬件支持

- 页表硬件支持
  - 页表较小：使用寄存器
  - 页表较大：页表基地址寄存器存储页表基地址，页表存于内存。
    > 但是需要两次内存访问，太慢了
  - 解决两次访问的方式：转换表缓冲区（TLB,Translation Look-aside Buffer）
    > ![os-11](./image/os-11.png)

#### 8.4.4.4. 页表种类

- 快表（也就是上面的TLB）
  - 概念：特殊的高速缓冲存储器（Cache），其中的内容是页表的一部分或者全部内容。作为页表的 Cache，它的作用与页表相似，但是提高了访问速率。
  - 目的：采用页表做地址转换，读写内存数据时 CPU 要访问两次主存。有了快表，有时只要访问一次高速缓冲存储器，一次主存，这样可加速查找并提高指令执行速度。
  - 流程：
    - 根据虚拟地址中的页号查快表；
    - 如果该页在快表中，直接从快表中读取相应的物理地址；
    - 如果该页不在快表中，就访问内存中的页表，再从页表中得到物理地址，同时将页表中的该映射表项添加到快表中；
    - 当快表填满后，又要登记新页时，就按照一定的淘汰策略淘汰掉快表中的一个页。

- 分层页表/多级页表
  <details>
  <summary style="color:red;">图示</summary>

  ![os-12](./image/os-12.png)
  ![os-13](./image/os-13.png)
  </details>

  - 目的：
    - 当代计算机大多数都支持大逻辑空间，页表本身可以非常大。每个进程需要很大的空间来存储页表。为了避免在内存中连续分配页表，使用分层页表。
    - 引入多级页表的主要目的是为了避免把全部页表一直放在内存中占用过多空间，特别是那些根本就不需要的页表就不需要保留在内存中。利用局部性原理，把二级页表存放到磁盘中。
      > 多级页表如何节约内存：https://www.polarxiong.com/archives/多级页表如何节约内存.html

- 哈希页表
  > 处理大于32位地址空间的常用办法是使用哈希页表
  <details>
  <summary style="color:red;">图示</summary>

  ![os-14](./image/os-14.png)
  </details>

- 倒置页表
  > 解决页表使用大量内存的问题。对于每个真正的内存页或帧，倒置页表才有一个条目。每个条目包含保存在真正内存位置上的页的虚拟地址，
  <details>
  <summary style="color:red;">图示</summary>

  ![os-15](./image/os-15.png)
  </details>

### 8.4.5. 段式管理

段式管理 ： 页式管理虽然提高了内存利用率，但是页式管理其中的页实际并无任何实际意义。 段式管理把主存分为一段段的，每一段的空间又要比一页的空间小很多 。但是，最重要的是段是有实际意义的，每个段定义了一组逻辑信息，例如,有主程序段 MAIN、子程序段 X、数据段 D 及栈段 S 等。 段式管理通过段表对应逻辑地址和物理地址。

- 一个c编译器可能会创建如下段：
  > ![os-18](./image/os-18.png)

- 分段硬件
  > ![os-16](./image/os-16.png)

- 示例：
  > ![os-17](./image/os-17.png)

### 8.4.6. 段页式管理

段页式管理机制结合了段式管理和页式管理的优点。简单来说段页式管理机制就是把主存先分成若干段，每个段又分成若干页，也就是说 段页式管理机制 中段与段之间以及段的内部的都是离散的。

### 8.4.7. 分段和分页区别与共同点

- 共同点 ：
  - 分页机制和分段机制都是为了提高内存利用率，较少内存碎片。
  - 页和段都是离散存储的，所以两者都是离散分配内存的方式。但是，每个页和段中的内存是连续的。
- 区别 ：
  - 页的大小是固定的，由操作系统决定；而段的大小不固定，取决于我们当前运行的程序。
  - 分页仅仅是为了满足操作系统内存管理的需求，而段是逻辑信息的单位，在程序中可以体现为代码段，数据段，能够更好满足用户的需要。
  - 内存碎片：页式存储管理的优点是没有外碎片（因为页的大小固定），但会产生内碎片（一个页可能填充不满）；而段式管理的优点是没有内碎片（因为段大小可变，改变段大小来消除内碎片）。但段换入换出时，会产生外碎片（比如4k的段换5k的段，会产生1k的外碎片）。

# 9. 虚拟内存管理

## 9.1. 虚拟地址，线性地址，物理地址

> 待核实

如果是没有使用虚拟内存的页式/段式系统，没有虚拟地址这一说法，逻辑地址一定直接可以转换为物理地址，因为进程都在内存中存储。

如果是使用了虚拟内存的页式/段式系统，就有虚拟地址了，因为不一定在内存中存储，得到的进程地址不一定能在内存上找到，因为页表上对应的块号可能为空。

至于线性地址，仅存在于段页式，逻辑地址查找段表得到线性地址，线性地址查找页表得到物理地址

## 9.2. 什么是虚拟内存

很多时候我们使用点开了很多占内存的软件，这些软件占用的内存可能已经远远超出了我们电脑本身具有的物理内存。为什么可以这样呢？
正是因为**虚拟内存**的存在，通过 虚拟内存 可以让程序可以拥有超过系统物理内存大小的可用内存空间。

另外，虚拟内存为每个进程提供了一个一致的、私有的地址空间，它让每个进程产生了一种自己在独享主存的错觉（每个进程拥有一片连续完整的内存空间）。这样会更加有效地管理内存并减少出错。

对于进程而言，逻辑上似乎有很大的内存空间，实际上其中一部分对应物理内存上的一块(称为帧，通常页和帧大小相等)，还有一些没加载在内存中的对应在硬盘上

虚拟内存是计算机系统内存管理的一种技术，我们可以手动设置自己电脑的虚拟内存。**不要单纯认为虚拟内存只是“使用硬盘空间来扩展内存“的技术**。虚拟内存的重要意义是它**定义了一个连续的虚拟地址空间**，并且**把内存扩展到硬盘空间**。

> 维基：<br />
> 虚拟内存 使得应用程序认为它拥有连续的可用的内存（一个连续完整的地址空间），而实际上，它通常是被分隔成多个物理内存碎片，还有部分暂时存储在外部磁盘存储器上，在需要时进行数据交换。与没有使用虚拟内存技术的系统相比，使用这种技术的系统使得大型程序的编写变得更容易，对真正的物理内存（例如 RAM）的使用也更有效率。目前，大多数操作系统都使用了虚拟内存，如 Windows 家族的“虚拟内存”；Linux 的“交换空间”等。

## 9.3. 虚拟内存的技术实现

虚拟内存的实现需要**建立在离散分配的内存管理方式的基础上**。 虚拟内存的实现有以下三种方式：

- 请求分页存储管理 ：建立在分页管理之上，为了支持虚拟存储器功能而增加了请求调页功能和页面置换功能。请求分页是目前最常用的一种实现虚拟存储器的方法。请求分页存储管理系统中，在作业开始运行之前，仅装入当前要执行的部分段即可运行。假如在作业运行的过程中发现要访问的页面不在内存，则由处理器通知操作系统按照对应的页面置换算法将相应的页面调入到主存，同时操作系统也可以将暂时不用的页面置换到外存中。
- 请求分段存储管理 ：建立在分段存储管理之上，增加了请求调段功能、分段置换功能。请求分段储存管理方式就如同请求分页储存管理方式一样，在作业开始运行之前，仅装入当前要执行的部分段即可运行；在执行过程中，可使用请求调入中断动态装入要访问但又不在内存的程序段；当内存空间已满，而又需要装入新的段时，根据置换功能适当调出某个段，以便腾出空间而装入新的段。
- 请求段页式存储管理

## 9.4. 请求调页与缺页错误

- 请求调页：
  - 磁盘加载可执行程序到内存，一种策略是在程序执行时将整个程序加载到物理内存，
  - 另一种策略是，仅在需要时才加载页面。
  - 这种技术被称为**请求调页**（demand paging),常常用于虚拟内存系统。

- 调页程序与交换器区别：
  - 调页程序只涉及进程的页面
  - 而交换器操作整个进程

- 有效位，无效位
  - 当该位被设置为有效时，相关联的页面是合法的，并且在内存中。
  - 当该位被设置为无效时，页面无效（即不在进程的逻辑地址空间中）,或有效但只在磁盘上。

    <details>
    <summary style="color:red;">不在进程逻辑地址空间的情况</summary>

    例如，对于14位地址空间（0\~16383)的系统，假设有一个程序，它的有效地址空间
    为0\~10468。如果页的大小为2KB,那么页表如图8.15所示。页0、1、2、3、4和5的
    地址可以通过页表正常映射。然而，如果试图产生页表6或7内的地址时，则会发现有效一
    无效位为无效，这样操作系统就会捕捉到这一非法操作（无效页引用）。
    ![os-20](./image/os-20.png)
    </details>

    <details>
    <summary style="color:red;">没有在内存的情况</summary>

    ![os-19](./image/os-19.png)
    </details>



- 缺页错误/缺页中断：如果进程试图访问那些尚未调入内存中的页面时，对标记为无效的页面访问会产生缺页错误（page fault)。

## 9.5. 写时复制

![os-21](./image/os-21.png)

fork（）会产生一个和父进程完全相同的子进程，但子进程在此后多会exec系统调用，出于效率考虑，linux中引入了 写时复制 技术，

父进程与子进程最初共享相同的页面来工作，子进程的代码段、数据段、堆栈都是指向父进程的物理空间，也就是说，**两者的逻辑空间不同**，**但其对应的物理空间是同一个**。

当父子进程中有更改相应页的行为发生时，再为子进程相应的页分配物理空间。

## 9.6. 局部性原理

- 局部性原理表现在以下两个方面：
  - 时间局部性 ：如果程序中的某条指令一旦执行，不久以后该指令可能再次执行；如果某数据被访问过，不久以后该数据可能再次被访问。产生时间局部性的典型原因，是由于在程序中存在着大量的循环操作。
  - 空间局部性 ：一旦程序访问了某个存储单元，在不久之后，其附近的存储单元也将被访问，即程序在一段时间内所访问的地址，可能集中在一定的范围之内，这是因为指令通常是顺序存放、顺序执行的，数据也一般是以向量、数组、表等形式簇聚存储的。

时间局部性是通过将近来使用的指令和数据保存到高速缓存存储器中，并使用高速缓存的层次结构实现。空间局部性通常是使用较大的高速缓存，并将预取机制集成到高速缓存控制逻辑中实现。虚拟内存技术实际上就是建立了 “内存一外存”的两级存储器的结构，利用局部性原理实现髙速缓存。

## 9.7. 页面置换算法

当发生缺页中断时，如果当前内存中并没有空闲的页面，操作系统就必须在内存选择一个页面将其移出内存，以便为即将调入的页面让出空间。用来选择淘汰哪一页的规则叫做页面置换算法。

- FIFO（First In First Out） 页面置换算法（先进先出页面置换算法） : 总是淘汰最先进入内存的页面，即选择在内存中驻留时间最久的页面进行淘汰。
- OPT 页面置换算法（最优页面置换算法） ：最佳(Optimal, OPT)置换算法所选择的被淘汰页面将是以后永不使用的，或者是在最长时间内不再被访问的页面,这样可以保证获得最低的缺页率。但由于人们目前无法预知进程在内存下的若千页面中哪个是未来最长时间内不再被访问的，因而该算法无法实现。一般作为衡量其他置换算法的方法。
- LRU （Least Currently Used）页面置换算法（最近最少使用页面置换算法） ：LRU算法赋予每个页面一个访问字段，用来记录一个页面自上次被访问以来所经历的时间 T，当须淘汰一个页面时，选择现有页面中其 T 值最大的，即最近最久未使用的页面予以淘汰。
- LFU （Least Frequently Used）页面置换算法（最不常使用页面置换算法） : 该置换算法选择在之前时期使用最少的页面作为淘汰页。
- MFU （Most Frequently Used）页面置换算法（最常使用页面置换算法）： 该置换算法选择在之前时期使用最多的页面作为淘汰页。

## 9.8. 系统抖动

当CPU利用率低的时候，会通过引入新的程序的方式提高利用率。

如果物理内存中的帧不够，则会出现缺页错误，并且从其他进程那里获取帧，

被抢走帧的进程出现缺页错误后，又会向抢占另外进程的帧。

缺页错误发生后，发生缺页错误的进程必须等待调页设备将页面换进和换出。从而导致cpu利用率更低。

cpu利用率的降低又会增加多道程序，产生更多的缺页错误和更长的调页设备队列，恶性循环。

# 10. 文件系统

# 11. 文件系统实现

## 11.1. 分配方法

# 12. 大容量存储系统

# 13. I/O 系统

## 13.1. 基本概念

- 总线：
  - 作用：
    - 设备与计算机通过一个连接点，或者叫作端口(port)进行通信，
    - 如果设备共享一组通用线路，则这种连接称为 **总线**
  - 定义：
    - 是一组线路通过线路传输信息的一个协议
    - 电子术语来说，传输的信息为具有一定时序的电压模式。

- PC总线架构

  ![os-32](./image/os-32.png)

  - PCI总线将处理器内存子系统连接到快速设备
  - 扩展总线连接相对较慢的设备，如键盘和USB端口
  - 磁盘通过小型计算机系统接口(small compouter system interface)总线连接到SCSI控制器
  - ...


## 13.2. IO硬件交互方式

TODO: 操作系统概念 中断，io硬件交互方式

### 13.2.1. 轮询

### 13.2.2. 中断

- 工作原理
  - CPU硬件有一条线，称为中断请求线IRL（interrupt-request line）
  - CPU **在执行完每条指令后** ，都会检测IRL
  - 当CPU检测到控制器已在IRL上发出一个信号时，CPU会保存执行状态并且调到内存固定位置的中断处理程序。
  - 中断处理程序确定中断原因，执行必要处理，执行状态恢复，并且执行返回中断指令以便CPU回到中断前的执行状态。

- 采用中断驱动的I/O循环

  ![os-33](./image/os-33.png)

  - 设备控制器通过中断请求线发送信号而引起(raise)中断，
  - CPU捕获(catch)中断并且分派（dispatch)到中断处理程序，
  - 中断处理程序通过处理设备来清除(clear)中断

- CPU的中断请求线
  > 大多数CPU有两条中断请求线
  - 一条是 **非屏蔽中断(nonmaskable interrupt)**
    - 保留用于诸如不可恢复的内存错误等事件
  - 另一条中断线是 **可屏蔽中断（maskable interrupt)**
    - 在执行不得中断的关键指令序列之前，它可以由CPU关闭
    - 可屏蔽中断可由设备控制器用来请求服务

- 中断优先级
  - 中断机制实现了一个中断优先级(interrupt priority level)系统
  - 这些级别能使CPU延迟处理低优先级中断而不屏蔽所有中断，并且可以让高优先级中断抢占执行低优先级中断。

- 中断的其他用途
  - 页面错误处理
    - 页面错误会引发中断。中断挂起当前进程，并且转到内核的页面错误处理程序。
    - 这个处理程序保存进程状态，将中断进程加到等待队列，
    - 执行页面缓存管理，调度I/O操作以获取页面，调度另一进程恢复执行，然后从中断返回。
  - 系统调用的实现
    - 通常，程序使用库调用来执行系统调用。
    - 库程序检查应用程序给出的参数，构建数据结构以传递参数到内核，然后执行一个特殊指令
      - 称为 **软中断** (software interrupt)或者 **陷阱** (trap)
      - 这个指令有一参数，用于标识所需的内核服务。
    - 当进程执行陷阱指令时，中断硬件保存用户代码的状态，切换到内核模式，分派到实现请求服务的内核程序。
    - 陷阱所赋予的中断优先级低于设备所赋予的中断优先级
      - 因为应用程序执行系统调用与在FFO队列溢出并失去数据之前的处理设备控制器相比，后者更为紧迫。

### 13.2.3. 直接访问内存

### 13.2.4. Linux IO模型说明

## 13.3. 缓冲与缓存

- 缓冲(buffer):用于保存两个设备之间或在设备和应用程序之间传输的数据。
- 缓存(cache):是保存数据副本的告诉内存区域。访问缓存副本比访问原版更加有效。
- 假脱机:保存数据输出的缓冲区。如打印机，不能接收交叉的数据流。

## 13.4. 多路复用


- 说明： IO多路复用（IO Multiplexing）是指单个进程/线程就可以同时处理多个IO请求。

- 实现原理：
  - 用户将想要监视的文件描述符（File Descriptor）添加到select/poll/epoll函数中，由内核监视，函数阻塞。
  - 一旦有文件描述符就绪（读就绪或写就绪），或者超时（设置timeout），函数就会返回，然后该进程可以进行相应的读/写操作。

- 三种：
  - select：
    - 将文件描述符放入一个集合中，调用select时，将这个集合从用户空间拷贝到内核空间（缺点1：每次都要复制，**开销大**），由内核根据就绪状态修改该集合的内容。
    - （缺点2）**集合大小有限制**，32位机默认是1024（64位：2048）；
    - 采用水平触发机制。
    - select函数返回后，需要通过遍历这个集合，找到就绪的文件描述符（缺点3：**轮询的方式效率较低**），当文件描述符的数量增加时，效率会线性下降；
  - poll:
    - 和select几乎没有区别
    - 区别在于文件描述符的存储方式不同，poll采用链表的方式存储，没有最大存储数量的限制；
  - epoll：
    - 通过内核和用户空间共享内存，避免了不断复制的问题；
    - 支持的同时连接数上限很高（1G左右的内存支持10W左右的连接数）；
    - 文件描述符就绪时，采用回调机制，避免了轮询（回调函数将就绪的描述符添加到一个链表中，执行epoll_wait时，返回这个链表）
    - 支持水平触发和边缘触发，采用边缘触发机制时，只有活跃的描述符才会触发回调函数。

- 总结，区别主要在于：
  - 一个线程/进程所能打开的最大连接数
  - 文件描述符传递方式（是否复制）
  - 水平触发 or 边缘触发
  - 查询就绪的描述符时的效率（是否轮询）

- 选择使用
  - 当连接数较多并且有很多的不活跃连接时，epoll的效率比其它两者高很多；
  - 但是当连接数较少并且都十分活跃的情况下，由于epoll需要很多回调，因此性能可能低于其它两者。

- 什么是文件描述符？
  - 文件描述符在形式上是一个非负整数。
  - 实际上，它是一个索引值，指向内核为每一个进程所维护的该进程打开文件的记录表。
  - 当程序打开一个现有文件或者创建一个新文件时，内核向进程返回一个文件描述符。
  - 内核通过文件描述符来访问文件。文件描述符指向一个文件。

- 水平触发和边缘触发
  - 当监听的socket上有数据到来时:
    - 软中断会执行epoll的回调函数ep_poll_callback,
    - 在回调函数中会将epoll中描述socket信息的数据结构epitem插入到epoll中的就绪队列rdllist中。
    - 随后用户进程从epoll的等待队列中被唤醒，epoll_wait将IO就绪的socket返回给用户进程，
    - 随即epoll_wait会清空rdllist。
  - 水平触发和边缘触发最关键的区别就在于当socket中的接收缓冲区还有数据可读时。epoll_wait是否会清空rdllist。
    - 水平触发：
      - 在这种模式下，用户线程调用epoll_wait获取到IO就绪的socket后，对Socket进行系统IO调用读取数据，
      - 假设socket中的数据只读了一部分没有全部读完，这时再次调用epoll_wait，epoll_wait会检查这些Socket中的接收缓冲区是否还有数据可读，如果还有数据可读，就将socket重新放回rdllist。
      - 所以当socket上的IO没有被处理完时，再次调用epoll_wait依然可以获得这些socket，用户进程可以接着处理socket上的IO事件。
    - 边缘触发：
      - 在这种模式下，epoll_wait就会直接清空rdllist，不管socket上是否还有数据可读。
      - 所以在边缘触发模式下，当你没有来得及处理socket接收缓冲区的剩下可读数据时，再次调用epoll_wait，因为这时rdlist已经被清空了，socket不会再次从epoll_wait中返回，所以用户进程就不会再次获得这个socket了，也就无法在对它进行IO处理了。
      - 除非，这个socket上有新的IO数据到达，根据epoll的工作过程，该socket会被再次放入rdllist中。

## 13.5. 五种IO网络模型

### 13.5.1. 概念说明

在进行解释之前，首先要说明几个概念：

- 用户空间与内核空间
  - 现在操作系统都是采用虚拟存储器，那么对32位操作系统而言，它的寻址空间（虚拟存储空间）为4G（2的32次方）。
  - `操作系统的核心是内核，独立于普通的应用程序，可以访问受保护的内存空间，也有访问底层硬件设备的所有权限`。
  - 为了保证用户进程不能直接操作内核（kernel），保证内核的安全，`操作系统将虚拟空间划分为两部分，一部分为内核空间，一部分为用户空间`。
  - 针对linux操作系统而言，将最高的1G字节（从虚拟地址0xC0000000到0xFFFFFFFF），供内核使用，称为内核空间，而将较低的3G字节（从虚拟地址0x00000000到0xBFFFFFFF），供各个进程使用，称为用户空间。

- 进程切换
  - 为了控制进程的执行，内核必须有能力挂起正在CPU上运行的进程，并恢复以前挂起的某个进程的执行。
  - 这种行为被称为进程切换。因此可以说，任何进程都是在操作系统内核的支持下运行的，是与内核紧密相关的。
  - 从一个进程的运行转到另一个进程上运行，这个过程中经过下面这些变化：
    - 1. 保存处理机上下文，包括程序计数器和其他寄存器。
    - 2. 更新PCB信息。
    - 3. 把进程的PCB移入相应的队列，如就绪、在某事件阻塞等队列。
    - 4. 选择另一个进程执行，并更新其PCB。
    - 5. 更新内存管理的数据结构。
    - 6. 恢复处理机上下文。

    > 注：总而言之就是很耗资源，具体的可以参考这篇文章：[进程切换](https://links.jianshu.com/go?to=http%3A%2F%2Fguojing.me%2Flinux-kernel-architecture%2Fposts%2Fprocess-switch%2F)。

- 进程的阻塞
  - 正在执行的进程，由于期待的某些事件未发生，如请求系统资源失败、等待某种操作的完成、新数据尚未到达或无新工作做等，则由系统自动执行阻塞原语(Block)，使自己由运行状态变为阻塞状态。
  - 可见，进程的阻塞是进程自身的一种主动行为，也因此只有处于运行态的进程（获得CPU），才可能将其转为阻塞状态。`当进程进入阻塞状态，是不占用CPU资源的`。

- 文件描述符fd
  - 文件描述符（File descriptor）是计算机科学中的一个术语，`是一个用于表述指向文件的引用的抽象化概念`。
  - 文件描述符在形式上是一个非负整数。实际上，`它是一个索引值，指向内核为每一个进程所维护的该进程打开文件的记录表`。
  - 当程序打开一个现有文件或者创建一个新文件时，内核向进程返回一个文件描述符。
  - 在程序设计中，一些涉及底层的程序编写往往会围绕着文件描述符展开。但是文件描述符这一概念往往只适用于UNIX、Linux这样的操作系统。

- 缓存 IO
  - `缓存 IO 又被称作标准 IO，大多数文件系统的默认 IO 操作都是缓存 IO`。
  - 在 Linux 的缓存 IO 机制中，操作系统会将 IO 的数据缓存在文件系统的页缓存（ page cache ）中
  - 也就是说，`数据会先被拷贝到操作系统内核的缓冲区中，然后才会从操作系统内核的缓冲区拷贝到应用程序的地址空间`。
  - **缓存 IO 的缺点：**
    - `数据在传输过程中需要在应用程序地址空间和内核进行多次数据拷贝操作`，
    - 这些数据拷贝操作所带来的 CPU 以及内存开销是非常大的。


`网络IO的本质是socket的读取，socket在linux系统被抽象为流，IO可以理解为对流的操作`刚才说了，对于一次IO访问（以read举例），`数据会先被拷贝到操作系统内核的缓冲区中，然后才会从操作系统内核的缓冲区拷贝到应用程序的地址空间`。

- 所以说，当一个read操作发生时，它会经历两个阶段：
  - 第一阶段：等待数据准备 (Waiting for the data to be ready)。
  - 第二阶段：将数据从内核拷贝到进程中 (Copying the data from the kernel to the process)。

- 对于socket流而言，
  - 第一步：通常涉及等待网络上的数据分组到达，然后被复制到内核的某个缓冲区。
  - 第二步：把数据从内核缓冲区复制到应用进程缓冲区。

- 网络IO的模型大致有如下几种：
  > 网络应用需要处理的无非就是两大类问题，
  > `网络IO，数据计算`。相对于后者，网络IO的延迟，给应用带来的性能瓶颈大于后者。
  - **同步模型（synchronous IO）**
  - 阻塞IO（bloking IO）
  - 非阻塞IO（non-blocking IO）
  - 多路复用IO（multiplexing IO）
  - 信号驱动式IO（signal-driven IO）
  - **异步IO（asynchronous IO）**

  > **注：由于signal driven IO在实际中并不常用，下面只提及剩下的四种IO Model。**

- 在深入介绍Linux IO各种模型之前，让我们先来探索一下基本 Linux IO 模型的简单矩阵。如下图所示：
  > ![os-23](./image/os-23.png)

### 13.5.2. 五种简单说明

[来源](http://cxd2014.github.io/2018/12/09/aio/)

- 同步阻塞IO（Blocking IO）：用户线程发起IO读/写操作之后，线程阻塞，直到可以开始处理数据；对CPU资源的利用率不够；
- 同步非阻塞IO（Non-blocking IO）：发起IO请求之后可以立即返回，如果没有就绪的数据，需要不断地发起IO请求直到数据就绪；不断重复请求消耗了大量的CPU资源；
- IO多路复用: 看上面
- 异步IO（Asynchronous IO）：用户线程发出IO请求之后，继续执行，由内核进行数据的读取并放在用户指定的缓冲区内，在IO完成之后通知用户线程直接使用。

### 13.5.3. 五种详细说明

<details>
<summary style="color:red;">详细说明</summary>

#### 13.5.3.1. 同步阻塞 IO（blocking IO）

##### 场景描述

> 我和女友点完餐后，不知道什么时候能做好，只好坐在餐厅里面等，直到做好，然后吃完才离开。女友本想还和我一起逛街的，但是不知道饭能什么时候做好，只好和我一起在餐厅等，而不能去逛街，直到吃完饭才能去逛街，中间等待做饭的时间浪费掉了。`这就是典型的阻塞`。

##### 网络模型

`同步阻塞 IO 模型是最常用的一个模型，也是最简单的模型`。在linux中，`默认情况下所有的socket都是blocking`。它符合人们最常见的思考逻辑。`阻塞就是进程 "被" 休息, CPU处理其它进程去了`。

在这个IO模型中，用户空间的应用程序执行一个系统调用（recvform），这会导致应用程序阻塞，什么也不干，直到数据准备好，并且将数据从内核复制到用户进程，最后进程再处理数据，`在等待数据到处理数据的两个阶段`，整个进程都被阻塞。不能处理别的网络IO。`调用应用程序处于一种不再消费 CPU 而只是简单等待响应的状态`，因此从处理的角度来看，这是非常有效的。在调用recv()/recvfrom()函数时，发生在内核中等待数据和复制数据的过程，大致如下图：

![os-24](./image/os-24.png)

##### 流程描述

当用户进程调用了recv()/recvfrom()这个系统调用，`kernel就开始了IO的第一个阶段：准备数据`（对于网络IO来说，很多时候数据在一开始还没有到达。比如，还没有收到一个完整的UDP包。这个时候kernel就要等待足够的数据到来）。这个过程需要等待，也就是说数据被拷贝到操作系统内核的缓冲区中是需要一个过程的。而在用户进程这边，整个进程会被阻塞（当然，是进程自己选择的阻塞）。`第二个阶段：当kernel一直等到数据准备好了，它就会将数据从kernel中拷贝到用户内存`，然后kernel返回结果，用户进程才解除block的状态，重新运行起来。

> 所以，blocking IO的特点就是在IO执行的两个阶段都被block了。

**优点：**

> 1. 能够及时返回数据，无延迟；
> 2. 对内核开发者来说这是省事了；

**缺点：**

> 1. 对用户来说处于等待就要付出性能的代价了；

#### 13.5.3.2. 同步非阻塞 IO（nonblocking IO）

##### 场景描述

> 我女友不甘心白白在这等，又想去逛商场，又担心饭好了。所以我们逛一会，回来询问服务员饭好了没有，来来回回好多次，饭都还没吃都快累死了啦。`这就是非阻塞`。需要不断的询问，是否准备好了。

##### 网络模型

`同步非阻塞就是 “每隔一会儿瞄一眼进度条” 的轮询（polling）方式`。在这种模型中，`设备是以非阻塞的形式打开的`。这意味着 IO 操作不会立即完成，read 操作可能会返回一个错误代码，说明这个命令不能立即满足（EAGAIN 或 EWOULDBLOCK）。

在网络IO时候，非阻塞IO也会进行recvform系统调用，检查数据是否准备好，与阻塞IO不一样，"非阻塞将大的整片时间的阻塞分成N多的小的阻塞, 所以进程不断地有机会 '被' CPU光顾"。

`也就是说非阻塞的recvform系统调用调用之后，进程并没有被阻塞，内核马上返回给进程，如果数据还没准备好，此时会返回一个error`。进程在返回之后，可以干点别的事情，然后再发起recvform系统调用。重复上面的过程，循环往复的进行recvform系统调用。`这个过程通常被称之为轮询`。轮询检查内核数据，直到数据准备好，再拷贝数据到进程，进行数据处理。**需要注意，拷贝数据整个过程，进程仍然是属于阻塞的状态**。

在linux下，可以通过设置socket使其变为non-blocking。`当对一个non-blocking socket执行读操作时`，流程如图所示：

![os-25](./image/os-25.png)

##### 流程描述

当用户进程发出read操作时，如果kernel中的数据还没有准备好，那么它并不会block用户进程，而是立刻返回一个error。从用户进程角度讲，它发起一个read操作后，并不需要等待，而是马上就得到了一个结果。用户进程判断结果是一个error时，它就知道数据还没有准备好，于是它可以再次发送read操作。一旦kernel中的数据准备好了，并且又再次收到了用户进程的system call，那么它马上就将数据拷贝到了用户内存，然后返回。

> 所以，nonblocking IO的特点是用户进程需要不断的主动询问kernel数据好了没有。

**同步非阻塞方式相比同步阻塞方式：**

> 优点：能够在等待任务完成的时间里干其他活了（包括提交其他任务，也就是 “后台” 可以有多个任务在同时执行）。
>
> 缺点：任务完成的响应延迟增大了，因为每过一段时间才去轮询一次read操作，而任务可能在两次轮询之间的任意时间完成。这会导致整体数据吞吐量的降低。

#### 13.5.3.3. IO 多路复用（ IO multiplexing）

##### 场景描述

> 与第二个方案差不多，餐厅安装了电子屏幕用来显示点餐的状态，这样我和女友逛街一会，回来就不用去询问服务员了，直接看电子屏幕就可以了。这样每个人的餐是否好了，都直接看电子屏幕就可以了，`这就是典型的IO多路复用`。

##### 网络模型

多路复用模型相较于非阻塞IO模型，最大的区别在于 **把频繁的轮询操作交给操作系统内核来替我们完成，这样就避免了在用户空间频繁的去使用系统调用来轮询所带来的性能开销**

由于同步非阻塞方式需要 **不断主动轮询** ，轮询占据了很大一部分过程，轮询会消耗大量的CPU时间，而 “后台” 可能有多个任务在同时进行，人们就想到了循环查询多个任务的完成状态，只要有任何一个任务完成，就去处理它。如果轮询不是进程的用户态，而是有人帮忙就好了。`那么这就是所谓的 “IO 多路复用”`。UNIX/Linux 下的 select、poll、epoll 就是干这个的（epoll 比 poll、select 效率高，做的事情是一样的）。

`IO多路复用有两个特别的系统调用select、poll、epoll函数`。select调用是内核级别的，select轮询相对非阻塞的轮询的区别在于: `前者可以等待多个socket，能实现同时对多个IO端口进行监听`，当其中任何一个socket的数据准好了，`就能返回进行可读`，`然后进程再进行recvform系统调用，将数据由内核拷贝到用户进程，当然这个过程是阻塞的`。

select或poll调用之后，会阻塞进程，与blocking IO阻塞不同在于，`此时的select不是等到socket数据全部到达再处理, 而是有了一部分数据就会调用用户进程来处理`。如何知道有一部分数据到达了呢？`监视的事情交给了内核，内核负责数据到达的处理。也可以理解为"非阻塞"吧`。

`I/O复用模型会用到select、poll、epoll函数，这几个函数也会使进程阻塞，但是和阻塞I/O所不同的的，这两个函数可以同时阻塞多个I/O操作`。而且可以同时对多个读操作，多个写操作的I/O函数进行检测，直到有数据可读或可写时（注意不是全部数据可读或可写），才真正调用I/O操作函数。

对于多路复用，也就是轮询多个socket。`多路复用既然可以处理多个IO，也就带来了新的问题，多个IO之间的顺序变得不确定了`，当然也可以针对不同的编号。具体流程，如下图所示：

![os-26](./image/os-26.png)

##### 流程描述

IO multiplexing就是我们说的select，poll，epoll，有些地方也称这种IO方式为event driven IO。`select/epoll的好处就在于单个process就可以同时处理多个网络连接的IO`。它的基本原理就是select，poll，epoll这个function会不断的轮询所负责的所有socket，当某个socket有数据到达了，就通知用户进程。

`当用户进程调用了select，那么整个进程会被block`，而同时，kernel会“监视”所有select负责的socket，`当任何一个socket中的数据准备好了，select就会返回`。这个时候用户进程再调用read操作，将数据从kernel拷贝到用户进程。

> 多路复用的特点是`通过一种机制一个进程能同时等待IO文件描述符`，内核监视这些文件描述符（套接字描述符），其中的任意一个进入读就绪状态，select， poll，epoll函数就可以返回。对于监视的方式，又可以分为 select， poll， epoll三种方式。

上面的图和blocking IO的图其实并没有太大的不同，事实上，还更差一些。`因为这里需要使用两个system call (select 和 recvfrom)，而blocking IO只调用了一个system call (recvfrom)`。但是，`用select的优势在于它可以同时处理多个connection`。

所以，如果处理的连接数不是很高的话，使用select/epoll的web server不一定比使用multi-threading + blocking IO的web server性能更好，可能延迟还更大。（select/epoll的优势并不是对于单个连接能处理得更快，而是在于能处理更多的连接。）

在IO multiplexing Model中，`实际中，对于每一个socket，一般都设置成为non-blocking`，但是，如上图所示，整个用户的process其实是一直被block的。`只不过process是被select这个函数block，而不是被socket IO给block`。所以**IO多路复用是阻塞在select，epoll这样的系统调用之上，而没有阻塞在真正的I/O系统调用如recvfrom之上。**

在I/O编程过程中，`当需要同时处理多个客户端接入请求时，可以利用多线程或者I/O多路复用技术进行处理`。I/O多路复用技术`通过把多个I/O的阻塞复用到同一个select的阻塞上，从而使得系统在单线程的情况下可以同时处理多个客户端请求`。与传统的多线程/多进程模型比，`I/O多路复用的最大优势是系统开销小`，系统不需要创建新的额外进程或者线程，也不需要维护这些进程和线程的运行，降底了系统的维护工作量，节省了系统资源，I/O多路复用的主要应用场景如下：

> 服务器需要同时处理多个处于监听状态或者多个连接状态的套接字。
>
> 服务器需要同时处理多种网络协议的套接字。

了解了前面三种IO模式，在用户进程进行系统调用的时候，**他们在等待数据到来的时候，处理的方式不一样，直接等待，轮询，select或poll轮询**，两个阶段过程：

> 第一个阶段有的阻塞，有的不阻塞，有的可以阻塞又可以不阻塞。
>
> 第二个阶段都是阻塞的。

**从整个IO过程来看，他们都是顺序执行的，因此可以归为同步模型(synchronous)。都是进程主动等待且向内核检查状态。【此句很重要！！！】**

`高并发的程序一般使用同步非阻塞方式而非多线程 + 同步阻塞方式`。要理解这一点，首先要扯到并发和并行的区别。比如去某部门办事需要依次去几个窗口，`办事大厅里的人数就是并发数，而窗口个数就是并行度`。也就是说`并发数是指同时进行的任务数（如同时服务的 HTTP 请求）`，而`并行数是可以同时工作的物理资源数量（如 CPU 核数）`。通过合理调度任务的不同阶段，并发数可以远远大于并行度，这就是区区几个 CPU 可以支持上万个用户并发请求的奥秘。在这种高并发的情况下，为每个任务（用户请求）创建一个进程或线程的开销非常大。`而同步非阻塞方式可以把多个 IO 请求丢到后台去，这就可以在一个进程里服务大量的并发 IO 请求`。

**注意：IO多路复用是同步阻塞模型还是异步阻塞模型，在此给大家分析下：**

> 此处仍然不太清楚的，强烈建议大家在细究[《聊聊同步、异步、阻塞与非阻塞》](https://links.jianshu.com/go?to=http%3A%2F%2Fmy.oschina.net%2Fxianggao%2Fblog%2F661085)中讲同步与异步的根本性区别，`同步是需要主动等待消息通知，而异步则是被动接收消息通知，通过回调、通知、状态等方式来被动获取消息`。`IO多路复用在阻塞到select阶段时，用户进程是主动等待并调用select函数获取数据就绪状态消息，并且其进程状态为阻塞`。所以，`把IO多路复用归为同步阻塞模式`。

#### 13.5.3.4. 信号驱动式IO（signal-driven IO）

信号驱动式I/O：首先我们允许Socket进行信号驱动IO,并安装一个信号处理函数，进程继续运行并不阻塞。当数据准备好时，进程会收到一个SIGIO信号，可以在信号处理函数中调用I/O操作函数处理数据。过程如下图所示：

![os-27](./image/os-27.png)

#### 13.5.3.5. 异步非阻塞 IO（asynchronous IO）

##### 场景描述

> 女友不想逛街，又餐厅太吵了，回家好好休息一下。于是我们叫外卖，打个电话点餐，然后我和女友可以在家好好休息一下，饭好了送货员送到家里来。这就是典型的异步，只需要打个电话说一下，然后可以做自己的事情，饭好了就送来了。

##### 网络模型

相对于同步IO，异步IO不是顺序执行。`用户进程进行aio_read系统调用之后，无论内核数据是否准备好，都会直接返回给用户进程，然后用户态进程可以去做别的事情`。等到socket数据准备好了，内核直接复制数据给进程，`然后从内核向进程发送通知`。`IO两个阶段，进程都是非阻塞的`。

Linux提供了AIO库函数实现异步，但是用的很少。目前有很多开源的异步IO库，例如libevent、libev、libuv。异步过程如下图所示：

![os-28](./image/os-28.png)

##### 流程描述

用户进程发起aio_read操作之后，立刻就可以开始去做其它的事。而另一方面，从kernel的角度，当它受到一个asynchronous read之后，`首先它会立刻返回，所以不会对用户进程产生任何block`。然后，kernel会等待数据准备完成，然后将数据拷贝到用户内存，`当这一切都完成之后，kernel会给用户进程发送一个signal或执行一个基于线程的回调函数来完成这次 IO 处理过程`，告诉它read操作完成了。

**在 Linux 中，通知的方式是 “信号”：**

> `如果这个进程正在用户态忙着做别的事（例如在计算两个矩阵的乘积），那就强行打断之，调用事先注册的信号处理函数`，这个函数可以决定何时以及如何处理这个异步任务。由于信号处理函数是突然闯进来的，因此跟中断处理程序一样，有很多事情是不能做的，因此保险起见，`一般是把事件 “登记” 一下放进队列，然后返回该进程原来在做的事`。
>
> `如果这个进程正在内核态忙着做别的事`，例如以同步阻塞方式读写磁盘，`那就只好把这个通知挂起来了，等到内核态的事情忙完了，快要回到用户态的时候，再触发信号通知`。
>
> `如果这个进程现在被挂起了，例如无事可做 sleep 了，那就把这个进程唤醒`，下次有 CPU 空闲的时候，就会调度到这个进程，触发信号通知。

异步 API 说来轻巧，做来难，这主要是对 API 的实现者而言的。Linux 的异步 IO（AIO）支持是 2.6.22 才引入的，还有很多系统调用不支持异步 IO。Linux 的异步 IO 最初是为数据库设计的，`因此通过异步 IO 的读写操作不会被缓存或缓冲，这就无法利用操作系统的缓存与缓冲机制`。

**很多人把 Linux 的 O_NONBLOCK 认为是异步方式，但事实上这是前面讲的同步非阻塞方式。**需要指出的是，虽然 Linux 上的 IO API 略显粗糙，但每种编程框架都有封装好的异步 IO 实现。操作系统少做事，把更多的自由留给用户，正是 UNIX 的设计哲学，也是 Linux 上编程框架百花齐放的一个原因。

从前面 IO 模型的分类中，我们可以看出 AIO 的动机：

> 同步阻塞模型需要在 IO 操作开始时阻塞应用程序。这意味着不可能同时重叠进行处理和 IO 操作。
>
> 同步非阻塞模型允许处理和 IO 操作重叠进行，但是这需要应用程序根据重现的规则来检查 IO 操作的状态。
>
> 这样就剩下异步非阻塞 IO 了，它允许处理和 IO 操作重叠进行，包括 IO 操作完成的通知。

IO多路复用除了需要阻塞之外，`select 函数所提供的功能（异步阻塞 IO）与 AIO 类似`。不过，`它是对通知事件进行阻塞，而不是对 IO 调用进行阻塞`。

#### 13.5.3.6. 关于异步阻塞

有时我们的 API 只提供异步通知方式，例如在 node.js 里，`但业务逻辑需要的是做完一件事后做另一件事`，例如数据库连接初始化后才能开始接受用户的 HTTP 请求。`这样的业务逻辑就需要调用者是以阻塞方式来工作`。

`为了在异步环境里模拟 “顺序执行” 的效果，就需要把同步代码转换成异步形式，这称为 CPS（Continuation Passing Style）变换`。BYVoid 大神的 [continuation.js](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FBYVoid%2Fcontinuation) 库就是一个 CPS 变换的工具。`用户只需用比较符合人类常理的同步方式书写代码，CPS 变换器会把它转换成层层嵌套的异步回调形式`。

![os-29](./image/os-29.png)

![os-30](./image/os-30.png)

`另外一种使用阻塞方式的理由是降低响应延迟`。如果采用非阻塞方式，一个任务 A 被提交到后台，就开始做另一件事 B，但 B 还没做完，A 就完成了，这时要想让 A 的完成事件被尽快处理（比如 A 是个紧急事务），要么丢弃做到一半的 B，要么保存 B 的中间状态并切换回 A，任务的切换是需要时间的（不管是从磁盘载入到内存，还是从内存载入到高速缓存），这势必降低 A 的响应速度。`因此，对实时系统或者延迟敏感的事务，有时采用阻塞方式比非阻塞方式更好`。

</details>

### 13.5.4. 总结

#### 13.5.4.1. blocking和non-blocking区别

调用blocking IO会一直block住对应的进程直到操作完成，而non-blocking IO在kernel还准备数据的情况下会立刻返回。

#### 13.5.4.2. synchronous IO和asynchronous IO区别

在说明synchronous IO和asynchronous IO的区别之前，需要先给出两者的定义。POSIX的定义是这样子的：

> - A synchronous I/O operation causes the requesting process to be blocked until that I/O operation completes;
> - An asynchronous I/O operation does not cause the requesting process to be blocked;

- `两者的区别就在于synchronous IO做"IO operation"的时候会将process阻塞`。按照这个定义，之前所述的blocking IO，non-blocking IO，IO multiplexing都属于synchronous IO。

有人会说，non-blocking IO并没有被block啊。这里有个非常 狡猾 的地方，`定义中所指的”IO operation”是指真实的IO操作`，就是例子中的recvfrom这个system call。non-blocking IO在执行recvfrom这个system call的时候，如果kernel的数据没有准备好，这时候不会block进程。但是，`当kernel中数据准备好的时候，recvfrom会将数据从kernel拷贝到用户内存中，这个时候进程是被block了`，在这段时间内，进程是被block的。

而asynchronous IO则不一样，`当进程发起IO 操作之后，就直接返回再也不理睬了，直到kernel发送一个信号，告诉进程说IO完成`。在这整个过程中，进程完全没有被block。

#### 13.5.4.3. 比较图

![os-31](./image/os-31.png)

# 14. 系统保护

# 15. 系统安全

# 16. 参考资料

- [聊聊Linux 五种IO模型](https://www.jianshu.com/p/486b0965c296)
- [Linux中的异步I/O模型](http://cxd2014.github.io/2018/12/09/aio/)
- [Operating Systems:Three Easy Pieces](https://pages.cs.wisc.edu/~remzi/OSTEP/Chinese/)
- [操作系统笔记整理](https://blog.csdn.net/shangxingya/category_10045126.html)
- [操作系统思维导图](https://blog.csdn.net/gl620321/article/details/107128661)
