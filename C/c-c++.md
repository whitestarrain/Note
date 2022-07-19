> 考虑到以前学的时候没做笔记，没有系统的认识。因此重新梳理一遍
>
> 偶尔会进行整理，但大多数时间可能在读资料

# 环境安装

# C基础

## 程序基本概念

### 程序基本概念

- 程序（Program）:
  - **告诉计算机应如何完成一个计算任务** 
  - 这里的计算可以是数学运算，比如解方程，也可以是符号运算，比如查找和替换文档中的某个单词
  - 从根本上说，计算机是由数字电路组成的运算机器，只能对数字做运算，程序之所以能做符号运算，是因为符号在计算机内部也是用数字表示的
  - 此外，程序还可以处理声音和图像，声音和图像在计算机内部必然也是用数字表示的，这些数字经过专门的硬件设备转换成人可以听到、看到的声音和图像。

- 指令（Instruction）:程序由一系列指令（Instruction）组成，指令是指示计算机做某种运算的命令，通常包括以下几类：
  - **输入（Input）**：从键盘、文件或者其它设备获取数据。
  - **输出（Output）**：把数据显示到屏幕，或者存入一个文件，或者发送到其它设备。
  - **基本运算**：执行最基本的数学运算（加减乘除）和数据存取。
  - **测试和分支**：测试某个条件，然后根据不同的测试结果执行不同的后续指令。
  - **循环**：重复执行一系列操作。

  > 对于程序来说，有上面这几类指令就足够了。你曾用过的任何一个程序，不管它有多么复杂，都是由这几类指令组成的。
  > 程序是那么的复杂，而编写程序可以用的指令却只有这么简单的几种，这中间巨大的落差就要由程序员去填了
  > 所以编写程序理应是一件相当复杂的工作。
  > **编写程序可以说就是这样一个过程：把复杂的任务分解成子任务，把子任务再分解成更简单的任务，层层分解，直到最后简单得可以用以上指令来完成。**

### 编程语言

#### 分类

- 编程语言（Programming Language）分为
  - 低级语言（Low-level Language）
    > 直接用计算机指令编写程序
    - 机器语言（Machine Language）
    - 汇编语言（Assembly Language）
  - 高级语言（High-level Language）
    > 用语句（Statement）编写程序，
    - C、C++、Java、Python 等属于高级语言

- 语句:是计算机指令的抽象表示。举个例子，同样一个语句用 C 语言、汇编语言和机器语言分别表示如下：

  | 编程语言 | 表示形式                                                    |
  | -------- | ----------------------------------------------------------- |
  | C 语言   | `a=b+1;`                                                    |
  | 汇编语言 | `mov 0x804a01c,%eax<br>add $0x1,%eax<br>mov %eax,0x804a018` |
  | 机器语言 | `a1 1c a0 04 08<br>83 c0 01<br>a3 18 a0 04 08`              |

#### 汇编器和编译器

- 汇编语言->机器语言
  - 计算机只能对数字做运算，符号、声音、图像在计算机内部都要用数字表示，指令也不例外
  - 上表中的机器语言完全由十六进制数字组成
  - 最早的程序员都是直接用机器语言编程，但是很麻烦，需要查大量的表格来确定每个数字表示什么意思，编写出来的程序很不直观，而且容易出错
  - 于是有了汇编语言，把机器语言中一组一组的数字用助记符（Mnemonic）表示，直接用这些助记符写出汇编程序
  - 然后让汇编器（Assembler）去查表把助记符替换成数字，也就把汇编语言翻译成了机器语言
    - 从上面的例子可以看出，汇编语言和机器语言的指令是一一对应的，汇编语言有三条指令，机器语言也有三条指令，汇编器就是做一个简单的替换工作
    - 例如在第一条指令中，把 `movl ?,%eax` 这种格式的指令替换成机器码 `a1 ?`，`?` 表示一个地址，在汇编指令中是 `0x804a01c`，转换成机器码之后是 `1c a0 04 08` 
      > （这是指令中的十六进制数的小端表示，小端表示将在**「目标文件」**章节 介绍）。

- 高级语言->机器语言
  - 从上面的例子还可以看出，C 语言的语句和低级语言的指令之间不是简单的一一对应关系，一条 `a=b+1;` 语句要翻译成三条汇编或机器指令
  - 这个过程称为 **编译（Compile）** ，由编译器（Compiler）来完成，显然编译器的功能比汇编器要复杂得多
  - 用 C 语言编写的程序必须经过编译转成机器指令才能被计算机执行，编译需要花一些时间，这是用高级语言编程的一个缺点，然而更多的是优点
    - 首先，用 C 语言编程更容易，写出来的代码更紧凑，可读性更强，出了错也更容易改正
    - 其次，C 语言是可移植的（Portable）或者称为平台无关的（Platform Independent）。

#### C语言的编译和可移植性

- 平台：
  - 平台这个词有很多种解释，可以指计算机体系结构（Architecture），也可以指操作系统（Operating System），也可以指开发平台（编译器、链接器等）
  - **不同的计算机体系结构有不同的指令集（Instruction Set）** ，可以识别的机器指令格式是不同的
- 编译保证可移植性
  - **直接用某种体系结构的汇编或机器指令写出来的程序只能在这种体系结构的计算机上运行** 
  - **而各种体系结构的计算机都有各自的 C 编译器** ，
    - 可以把 C 程序编译成各种不同体系结构的机器指令
    - 这意味着用 C 语言写的程序只需稍加修改甚至不用修改就可以在各种不同的计算机上 **编译** 运行
- 不同编译器
  - 还要注意一点， **即使在相同的体系结构和操作系统下，用不同的 C 编译器（或者同一个 C 编译器的不同版本）编译同一个程序得到的结果也有可能不同** 
  - **C 语言有些语法特性在 C 标准中并没有明确规定，各编译器有不同的实现，** 编译出来的指令的行为特性也会不同，应该尽量避免使用不可移植的语法特性。
- 其他高级语言
  - 各种高级语言都具有 C 语言的这些优点，所以绝大部分程序是用高级语言编写的
  - 只有和硬件关系密切的少数程序（例如驱动程序）才会用到低级语言

#### 编译执行和解释执行

- 总结一下编译执行的过程
  - 首先你用文本编辑器写一个 C 程序
  - 然后保存成一个文件，例如program.c（通常 C 程序的文件名后缀是 `.c`），这称为源代码（Source Code）或源文件
  - 然后运行编译器对它进行编译，编译的过程并不执行程序，而是把源代码全部翻译成机器指令，再加上一些描述信息，生成一个新的文件，例如 `a.out`，这称为可执行文件
  - 可执行文件可以被操作系统加载运行，计算机执行该文件中由编译器生成的指令，如下图所示：

  ![图 1.1. 编译执行的过程](./image/intro.compile.png)


- 有些高级语言以解释（Interpret）的方式执行
  - 解释执行过程和 C 语言的编译执行过程很不一样
  - 例如编写一个 Shell 脚本 `script.sh`，内容如下：

    ```bash
      #! /bin/sh
      VAR=1
      VAR=$(($VAR+1))
      echo $VAR
    ```

  - 定义 `Shell`变量 `VAR` 的初始值是 `1`，然后自增 `1`，然后打印 `VAR` 的值
  - 用 `Shell` 程序 `/bin/sh` 解释执行这个脚本，结果如下：

    ```bash
    $ /bin/sh script.sh
    2
    ```

  - 这里的 `/bin/sh` 称为解释器（Interpreter）
  - 它把脚本中的每一行当作一条命令解释执行，而不需要先生成包含机器指令的可执行文件再执行
  - 如果把脚本中的这三行当作三条命令直接敲到 `Shell` 提示符下，也能得到同样的结果：

    ```bash
    $ VAR=1
    $ VAR=$(($VAR+1))
    $ echo $VAR
    2
    ```

  ![图 1.2. 解释执行的过程](./image/intro.interpret.png)

### 编程语言发展

- 编程语言仍在发展演化
- 以上介绍的机器语言称为第一代语言（1GL，1st Generation Programming Language）
- 汇编语言称为第二代语言（2GL，2nd Generation Programming Language）
- C、C++、Java、Python 等可以称为第三代语言（3GL，3rd Generation Programming Language）
- 目前已经有了 4GL（4th Generation Programming Language）和 5GL（5th Generation Programming Language）的概念
- 3GL 的编程语言虽然是用语句编程而不直接用指令编程，但语句也分为输入、输出、基本运算、测试分支和循环等几种，和指令有直接的对应关系
- 而 4GL 以后的编程语言更多是描述要做什么（Declarative）而不描述具体一步一步怎么做（Imperative），具体一步一步怎么做完全由编译器或解释器决定
  - 例如 SQL 语言（SQL，Structured Query Language，结构化查询语言）就是这样的例子。

### 调试与错误

- 编程是一件复杂的工作，因为是人做的事情，所以难免经常出错
  - 据说有这样一个典故：早期的计算机体积都很大，有一次一台计算机不能正常工作，工程师们找了半天原因最后发现是一只臭虫钻进计算机中造成的
  - 从此以后，程序中的错误被叫做臭虫（Bug），而找到这些 Bug 并加以纠正的过程就叫做调试（Debug）
  - 有时候调试是一件非常复杂的工作，要求程序员概念明确、逻辑清晰、性格沉稳，还需要一点运气
  - 调试的技能我们在后续的学习中慢慢培养，但首先我们要区分清楚程序中的 Bug 分为哪几类。

- Bug分类
  - **编译时错误**：编译器只能翻译语法正确的程序，否则将导致编译失败，无法生成可执行文件。对于自然语言来说，一点语法错误不是很严重的问题，因为我们仍然可以读懂句子。而编译器就没那么宽容了，只要有哪怕一个很小的语法错误，编译器就会输出一条错误提示信息然后罢工，你就得不到你想要的结果。虽然大部分情况下编译器给出的错误提示信息就是你出错的代码行，但也有个别时候编译器给出的错误提示信息帮助不大，甚至会误导你。在开始学习编程的前几个星期，你可能会花大量的时间来纠正语法错误。等到有了一些经验之后，还是会犯这样的错误，不过会少得多，而且你能更快地发现错误原因。等到经验更丰富之后你就会觉得，语法错误是最简单最低级的错误，编译器的错误提示也就那么几种，即使错误提示是有误导的也能够立刻找出真正的错误原因是什么。相比下面两种错误，语法错误解决起来要容易得多。
  - **运行时错误**：编译器检查不出这类错误，仍然可以生成可执行文件，但在运行时会出错而导致程序崩溃。对于我们接下来的几章将编写的简单程序来说，运行时错误很少见，到了后面的章节你会遇到越来越多的运行时错误。读者在以后的学习中要时刻**注意区分编译时和运行时（Run-time）这两个概念**，不仅在调试时需要区分这两个概念，在学习 C 语言的很多语法时都需要区分这两个概念，有些事情在编译时做，有些事情则在运行时做。
  - **逻辑错误和语义错误**：第三类错误是逻辑错误和语义错误。如果程序里有逻辑错误，编译和运行都会很顺利，看上去也不产生任何错误信息，但是程序没有干它该干的事情，而是干了别的事情。当然不管怎么样，计算机只会按你写的程序去做，问题在于你写的程序不是你真正想要的，这意味着程序的意思（即语义）是错的。找到逻辑错误在哪需要十分清醒的头脑，要通过观察程序的输出回过头来判断它到底在做什么。

```
通过本书你将掌握的最重要的技巧之一就是调试。调试的过程可能会让你感到一些沮丧，但调试也是编程中最需要动脑的、最有挑战和乐趣的部分。
从某种角度看调试就像侦探工作，根据掌握的线索来推断是什么原因和过程导致了你所看到的结果。
调试也像是一门实验科学，每次想到哪里可能有错，就修改程序然后再试一次。如果假设是对的，就能得到预期的正确结果，就可以接着调试下一个 Bug，一步一步逼近正确的程序；
如果假设错误，只好另外再找思路再做假设。「当你把不可能的全部剔除，剩下的——即使看起来再怎么不可能——就一定是事实。」。

也有一种观点认为，编程和调试是一回事，编程的过程就是逐步调试直到获得期望的结果为止。
你应该总是从一个能正确运行的小规模程序开始，每做一步小的改动就立刻进行调试，
这样的好处是总有一个正确的程序做参考：如果正确就继续编程，如果不正确，那么一定是刚才的小改动出了问题。
例如，Linux 操作系统包含了成千上万行代码，但它也不是一开始就规划好了内存管理、设备管理、文件系统、网络等等大的模块，
一开始它仅仅是 Linus Torvalds 用来琢磨 Intel 80386 芯片而写的小程序。
据 Larry Greenfield 说，「 Linus 的早期工程之一是编写一个交替打印 AAAA 和 BBBB 的程序，这玩意儿后来进化成了 Linux。」
（引自 *The Linux User's Guide* Beta1 版）在后面的章节中会给出更多关于调试和编程实践的建议。
```

## 常量、变量和表达式

## 简单函数

## 分支语句

## 深入理解函数

## 循环语句

## 结构体

## 编码风格

## gdb

- 基本
  - backtrace（或 bt） 查看各级函数调用及参数
  - finish 连续运行到当前函数返回为止，然后停下来等待命令
  - frame（或 f） 帧编号 选择栈帧
  - info（或 i） locals 查看当前栈帧局部变量的值
  - list（或 l） 列出源代码，接着上次的位置往下列，每次列 10 行
  - list 行号 列出从第几行开始的源代码
  - list 函数名 列出某个函数的源代码
  - next（或 n） 执行下一行语句
  - print（或 p） 打印表达式的值，通过表达式可以修改变量的值或者调用函数
  - quit（或 q） 退出 gdb 调试环境
  - set var 修改变量的值
  - start 开始执行程序，停在 main 函数第一行语句前面等待命令
  - step（或 s） 执行下一行语句，如果有函数调用则进入到函数中
- 断点
  - break（或 b） 行号 在某一行设置断点
  - break 函数名 在某个函数开头设置断点
  - break ... if ... 设置条件断点
  - continue（或 c） 从当前位置开始连续运行程序
  - delete breakpoints 断点号 删除断点
  - display 变量名 跟踪查看某个变量，每次停下来都显示它的值
  - disable breakpoints 断点号 禁用断点
  - enable 断点号 启用断点
  - info（或 i） breakpoints 查看当前设置了哪些断点
  - run（或 r） 从头开始连续运行程序
  - undisplay 跟踪显示号 取消跟踪显示
- 其他
  - watch 设置观察点
  - info（或 i） watchpoints 查看当前设置了哪些观察点
  - x 从某个位置开始打印存储单元的内容，全部当成字节来看，而不区分哪个字节属于哪个变量

## 排序与查找

## 栈与队列

# C 语言本质

## 计算机中数的表示

### 二进制:OR,AND,NOR,NAND,XOR,inverter

### 全加器结构

### Sign and Magnitude 表示法(需要优化)

#### 减法器电路

#### 计算机中有负数的加法运算

### 1's Complement 表示法(需要优化)

### 2's Complement 表示法(优化结果，使用)

### 有符号数和无符号数

### 浮点数

## 数据类型详解

### 代码的可移植性

### Implementation-defined、Unspecified 和 Undefined

### 8 进制与 16 进制表示法

### int,long,long long 的选择

## 运算符详解

## 计算机体系结构基础

## x86 汇编程序基础

## 汇编与 C 之间的关系

## 链接详解

## 预处理

## Makefile 基础

## 指针

```c
// 指针数组和指向数组的指针

int a[] = {1,2,3,4,5};
// []的优先级比*高
int (*pa)[] = &a;

printf("%d",a == &a[0]);
printf("%d",pa == &a);                // ①

// `&a` 表示数组 `a` 的首地址
// `&a[0]` 表示数组 `a` 的首元素的首地址
// &a[0] 和 &a 数值相同，类型不同
// 同时 *(&a) =->= *(&a[0]) =->= a[0]    ②
//
// 根据① ② 得：
//              *pa =->= a[0]            ③
//
// 取数组a首元素值：a[0]
// pa == &a,则*pa = a
// 则：取元素a首元素：a[0] =->= (*pa)[0] ④
//
// 根据③ ④ 可得：a[0] = pa[0][0]
```


## 函数接口

## C 标准库

## 链表、二叉树和哈希表

# linux系统编程基础

## 文件与 I/O

## 文件系统

## 进程

## Shell 脚本

## 正则表达式

## 信号

## 终端、作业控制与守护进程

## 线程

## TCP/IP 协议基础

## socket 编程

# C到C++

## c++与c的联系以及标准

## 字符串与数组

```
1.字符串
1.1 初始化
1.2 大小
1.3 常见操作
2.数组
2.1 初始化
2.2 大小
2.3 遍历
2.4 auto与decltype
2.5 多维数组
```

## 函数

```
1.函数参数
1.1 形参
1.2 默认实参
2.函数返回
2.1 状态码
2.2 数组与函数的指针
3.函数重载
3.1 判断标准
3.2 函数匹配步骤
4.内联函数
5.constexpr函数
6.函数指针
6.1 函数类型与函数指针
6.2 如何赋值
6.3 如何调用
6.4 作为形参与返回值
```

## 类

```
1.关键字
2.向前声明
3.组成
3.1 友元声明
3.2 访问说明符
3.3 类型别名成员
3.4 静态成员
3.5 成员变量
3.6 成员函数
4.初始化
4.1 显示初始化
4.2 默认初始化
4.3 值初始化
4.4 成员的初始化
5.作用域与名字查找
5.1 作用域
5.2 名字查找
6.类型转换
6.1 隐式类型转换
7.类对象移动
7.1 右值引用
```

## 重载运算与类型转换

```
1.重载运算
1.1 重载为成员函数
1.2 重载为非成员函数
1.3 不应重载的运算符
1.4 可被重载的运算符
2.二义性类型转换
2.1 转换二义性
2.2 避免转换出现二义性
```

## 继承体系

```
1.虚函数
1.1 动态绑定
1.2 函数覆盖
1.3 纯虚函数与抽象基类
2.派生类的构造与拷贝控制
2.1 构造
2.2 拷贝控制
3.防止继承与防止覆盖
4.static与继承
5.类型转换与继承
5.1 指针或引用的转换
5.2 对象之间不存在转换
6.访问权限与继承
6.1 using修改继承自基类成员的权限
7.继承中的作用域
7.1 名字查找
7.2 名字继承与冲突
8.虚继承
8.1 重复继承
8.2 成员可见性
8.3 构造与拷贝控制
```

## 容器

```
1.容器通用操作
1.1 类型别名
1.2 构造
1.3 赋值与swap
1.4 大小
1.5 添加与删除元素
1.6 关系运算符
1.7 获取迭代器
2.顺序容器
2.1 种类
2.2 操作
2.3 迭代器失效
3.关联容器
3.1 有序关联容器
3.2 无序关联容器
3.3 pair
3.4 操作
容器适配器
1.通用的容器适配器操作
2.三个顺序容器适配器
2.1 stack
2.2 queue
2.3 priority_queue
```

## 泛型算法

```
1.常用算法
1.1 读容器(元素)算法
1.2 写容器(元素)算法
1.3 for_each算法
2.迭代器
2.1 按类型划分
2.2 按操作级别划分
3.调用对象
3.1 谓词
3.2 lambda
3.3 bind参数绑定
4.链表的算法
```

## 模板与泛型编程

```
1.模板函数
1.1 模板参数
1.2 函数形参
1.3 成员模板
2.类模板
2.1 与模板函数的区别
2.2 模板类名的使用
2.3 类模板的成员函数
2.4 类型成员
2.5 类模板和友元
2.6 模板类型别名
2.7 类模板的static成员
3.模板编译
3.1 实例化声明
3.2 实例化定义
4.模板参数
4.1 默认模板实参
4.2 模板实参推断
5.重载与模板
6.可变参数模板
7.模板特例化
```

## 内存管理

```
1.new和delete
1.1 new
1.2 delete
2.智能指针
2.1 通用操作
2.2 shared_ptr
2.3 unique_ptr
2.4 weak_ptr
```

## 输入输出

```
1.I/O流
2.文件流
2.1 文件模式
2.2 创建文件流
2.3 打开文件流
2.4 关闭文件流
3.字符串流
3.1 创建string流
3.2 返回string流
3.3 将string拷贝到string流
4.四个常用I/O对象
5.流状态
5.1 条件状态
5.2 格式状态
6.流操作
6.1 关联输入输出流
6.2 未格式化I/O操作
7.缓冲区管理
7.1 刷新缓冲区
```


## 输入输出

```
1.I/O流
2.文件流
2.1 文件模式
2.2 创建文件流
2.3 打开文件流
2.4 关闭文件流
3.字符串流
3.1 创建string流
3.2 返回string流
3.3 将string拷贝到string流
4.四个常用I/O对象
5.流状态
5.1 条件状态
5.2 格式状态
6.流操作
6.1 关联输入输出流
6.2 未格式化I/O操作
7.缓冲区管理
7.1 刷新缓冲区
```

# 参考资料

> c语言资料

- [ ] **[Linux C编程一站式学习](http://akaedu.github.io/book/)(重要)**
  - [markdown版本](https://github.com/52fhy/linux-c) **[本地](./资料/linux-c/README.md)**
- [ ] [关于C或C++的#include搜索路径，lib库搜索路径](https://blog.csdn.net/yjk13703623757/article/details/83154578)
- [ ] 雨痕C语言学习笔记
- [ ] [MinGW到底是个什么ghost](https://blog.csdn.net/lee_ham/article/details/81778581)
  - [ ] [MinGW中的头文件路径级环境变量设置](https://blog.csdn.net/wdjhzw/article/details/26576847)
  - [ ] [Window下g++搜索机制以及mingw环境配置](https://blog.csdn.net/weixin_34387284/article/details/91980027)
- [ ] [C语言菜鸟教程](https://www.runoob.com/cprogramming/c-environment-setup.html)
- [ ] [关于C语言](https://www.cnblogs.com/huya-edu/p/13920691.html)
- [ ] [Neovim C Cpp Lsp Integration Tips](https://ttys3.dev/post/neovim-c-cpp-lsp-integration-tips)

> c++资料

- [ ] 《Essential c++》
- [ ] [c++笔记(C++Primer以及各种乱七八糟的)](https://github.com/arkingc/note)
- [ ] [c++笔记(侯捷老师课程)](https://github.com/yangsoon/cpptest)
- [ ] 《现代C++教程》
- [ ] [CMake、CMakeLists.txt、GCC、Clang、LLVM、MinGW、交叉编译](https://zhupite.com/program/CMake-GCC-Clang-LLVM-MinGW-CrossCompile.html)
- [ ] [gcc与clang对比](https://blog.csdn.net/sinat_36629696/article/details/79979274)
- [ ] gcc,LLVM,clang,VC 关系：
  - [Clang、LLVM与GCC介绍](https://blog.csdn.net/weichuang_1/article/details/48632321)
  - [Clang、GCC和LLVM是什么](https://www.cnblogs.com/hercules-chung/p/12438808.html)
  - [LLVM,Clang,GCC](https://blog.csdn.net/ShockYu/article/details/102793708)
  - [VC, GCC, Clang/LLVM区别 ](https://www.cnblogs.com/xuesu/p/14542821.html)
- [ ] [cstdio和stdio.h的区别](https://blog.csdn.net/Abigial_/article/details/54799629)
- [ ] [Google的C++开源代码项目以及经典C++库](https://www.cnblogs.com/zhoug2020/p/5812578.html)
- [C/C++中关于静态链接库(.a)、动态链接库（.so）的编译与使用](https://blog.csdn.net/qq_27825451/article/details/105700361)