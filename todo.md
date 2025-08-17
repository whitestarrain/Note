
> todo 和一些想之后看的资料

```
You can't connect the dots looking forward; you can only connect them looking backwards.
So you have to trust that the dots will somehow connect in future.
You have to trust in something - your gut, destiny, life, karma, whatever.
This approach has never let me down, and it has made all the difference in my life.

你展望人生的时候，不可能把这些点连起来；只有当你回顾人生的时候，才能发现它们之间的联系。
所以你必须有信心，相信这些点总会以某种方式，对你的未来产生影响。
你必须相信一些事情----你的勇气、命运、人生、缘分等等。
这样做从未令我失望，反而决定了我人生中所有与众不同之处。

> 乔布斯在斯坦福大学毕业典礼上的演讲
```

# 优先级较高项

- [x] [手把手教你构建 C 语言编译器](https://lotabout.me/2015/write-a-C-interpreter-0/)
  - [x] 编译原理复习

- [x] 迁移 nixos
  - 一段时间不 `pacman -Syu`，就会有些包因为版本问题没办法使用
  - 看看迁移成本，以及看看，以及 nix 管理方式下，在想尝试新东西的时候，会不会很繁琐
    - ok, nix shell, 完美
  - 流程：
    - [x] nix 基础
    - [x] 系统盘
    - [x] 初始化nix配置
    - [x] 数据备份
    - [x] 系统安装 & 配置
    - [ ] hardening app: qq, wechat ...

- [ ] 图灵完备通关

- [ ] CSAPP 《深入理解现代操作系统》

- [ ] assembly language
  - 协程实现

- [ ] hack

- [ ] 体验一下 rust
  - 音乐爬虫：`https://www.flac.life/`
  - 顺便： mpc + ncmpcpp

- [ ] emacs, elisp
  - common ansi lisp
  - [Emacs Lisp 简明教程](https://smacs.github.io/elisp/)
  - [99 lisp problems](https://www.ic.unicamp.br/~meidanis/courses/mc336/problemas-lisp/L-99_Ninety-Nine_Lisp_Problems.html)

- [ ] effective cpp
  - 主要是为了刷题，同时复习一下cpp

- [ ] 手机系统切 LineageOS
  - 参考: [用 Nix 编译自定义 Android 内核](https://lantian.pub/article/modify-computer/build-custom-android-kernel-with-nix.lantian/)

- [ ] [常用 bash 命令](https://github.com/jlevy/the-art-of-command-line/blob/master/README-zh.md)
  - star 不少，可以过一遍，看看有没有自己眼生的，总结到 linux.md 或者 bash.md 中

- [个人数据安全不完全指南](https://thiscute.world/posts/an-incomplete-guide-to-data-security/#%E9%9B%B6%E5%89%8D%E8%A8%80)

- [ ] NAS 搭建 && 数据备份
  - 参考：[ntzyz, nas 和数据备份](https://ntzyz.space/zh-cn/post/nas-and-backup/)
  - 主要是因为网易云音乐相关，想搞个公共存储
  - [ ] 网易云音乐解密+备份+同步 脚本

- [x] ~使用 make 安装 dotfiles~, use bashscript
  - 参考： https://github.com/jessfraz/dotfiles
  - 主要是为了解决 home 下软链目录还是软链文件的问题,stow 会自动 merge ，这会导致目录结构不同时，行为不一致

- [ ] csapp
  - [csapp labs](http://csapp.cs.cmu.edu/3e/labs.html)
  - [lab 资料](https://hansimov.gitbook.io/csapp/labs/labs-overview)

- ucore

- [ ] 博客
  - [ ] 完工遗留的 blog 草稿
  - [ ] 博客里面，写上今年规划，以及多写一些博客吧

- 安全或者CTF方面可以学下，应该能学到挺多东西: [pwn](https://pwn.college/)

- [ ] [emacs GTD 日程管理](https://www.cnblogs.com/yangruiGB2312/p/9101838.html)

- [ ] linux 系统编程

- [ ] [DN42 实验网络介绍及注册教程](https://lantian.pub/article/modify-website/dn42-experimental-network-2020.lantian/)

- 网站(空闲时翻翻)
  - [ ] [深入架构原理与事件](https://www.thebyte.com.cn/)
  - [ ] [OI wiki](https://oi-wiki.org/)
  - [ ] [CTF PWN入门](wiki.scuctf.com)

- [ ] [2021年-用更现代的方法使用PGP-上](https://ulyc.github.io/2021/01/13/2021年-用更现代的方法使用PGP-上/)

- [ ] [计算机教育中缺失的一课](https://missing-semester-cn.github.io/): 查一下漏

- [ ] 图形库
  - raylib
  - SDL

- 电路
  - [电子入坑百科全书](https://www.emoe.xyz/all-about-electronics/#53-gt)
  - [嘉立创](https://lceda.cn/)
  - [ ] 微机原理。使用电路模拟器实现一个计算器
  - [ ] 树莓派

- 深度学习进阶，自然语言处理
  - 主要是用于整理日记，进行总结

- 金融和量化相关的知识也可以看看
  - https://www.zhihu.com/question/402824103/answer/1314615280

# 小任务

- 计算机网络相关
  - https
    - [ ] c 实现https (tls)
    - [ ] c 实现文件上传下载
  - x11
    - picom support dynamic wallpaper
  - [ ] traceroute 实现
    - [优雅地在 Traceroute 里膜 拜大佬](https://lantian.pub/article/creations/traceroute-chain.lantian/)
  - [ ] eNSP: 网络仿真平台
  - [ ] 杂项资料
    - <https://github.com/crisxuan/bestJavaer/blob/master/computer-network/computer-howtolearn.md>
    - <https://www.cloudflare-cn.com/learning/>
    - <https://developer.mozilla.org/zh-CN/docs/Web/HTTP>
- [ ] nginx转发
  - [x] markdown preview
  - [ ] [awesome rss](https://github.com/HenryQW/Awesome-TTRSS)
    - [ ] cron 上传 rss 数据库文件
    - [ ] gnu 加密 dotfile 敏感文件
    - [ ] 容器中设置host代理

      ```bash
      docker run -it \
        -e http_proxy="http://host.docker.internal:6152" \
        -e https_proxy="http://host.docker.internal:6152" \
        -e all_proxy="socks5://host.docker.internal:6153" \
        ubuntu /bin/bash
      ```
- [ ] tempral 框架
- [x] [clashtui](https://github.com/JohanChane/clashtui)
  - 算了，还是直接wget 订阅链接比较简单
- [ ] dotfiles 实现 Dockerfile
- [ ] netty 框架源码
- [ ] Zmodem 协议，sz rz 实现远程图片上传
  - 怎么触发local host的sz
- [ ] tmux中使用nvim 时错位
  - 原因1：unicode 标准，不同版本，unicode字符宽度可能没有对齐，
    - [tmux status line width with unicode characters](https://stackoverflow.com/questions/66157606/tmux-status-line-width-with-unicode-characters)
  - 原因2：windows上， 隐藏仿真层 ConPTY 会重写输出
    - [tmux: ambiguous double width / single width characters rendered differently under tmux](https://github.com/wez/wezterm/issues/3704)
    - 应该是这个，mac上ssh链接，没有这个问题。windows上 可以复现。之后强制调用 openssh 试试
- [ ] 逆向工程
  - utools 禁用更新
- [ ] 通过network socket 控制 nvim unix domain socket 的 client 的 输入
  - nvim 启动server后，会有一个 unix domain socket 进行通信
  - remote 端，通过tcp socket和本地机器通信
  - 然后通过管道连接 unix domain socket client 的输入和输出
  - (总感觉还不如ssh呢，ssh至少人家有加密)
- 编程范式
  - [ ] [ocaml 函数式编程](https://ocaml.org/exercises)
- [x] c的热重载
- [ ] c 实现旋转面包圈
  - 计算机图形学，下面可以找到公开课
  - [Spinning Cube](https://www.youtube.com/watch?v=p09i_hoFdd0)
  - [旋转矩阵](https://zh.wikipedia.org/wiki/旋转矩阵)
- [ ] [hash可视化](https://superuser.com/questions/22535/what-is-randomart-produced-by-ssh-keygen)

# 首选资料、公开课

> - [CS自学指南](https://csdiy.wiki/)
> - [MIT的计算课程列表](https://github.com/forthespada/Awsome-Courses?tab=readme-ov-file)
> - [编程指北](https://csguide.cn/)

<details>
<summary style="color:red;">历史收集可简单参考。(推荐直接参考 CS自学指南)</summary>

---

## 入门课程

- [Crash Course Computer Scienc(计算机科学速成课)](https://www.youtube.com/watch?v=tpIctyqH29Q&list=PL8dPuuaLjXtNlUrzyH5r6jN9ulIgZBpdo)

  这门课有很多地方都适合小白，比如视频内容精炼但不冗余，该介绍到的都介绍到了，如果你担心这门课程过于简单，那你就大错特错了。

  - 从继电器讲到真空管，最后再到晶体管和集成电路；
  - 从最原始的纸袋打孔到机器语言的出现，再到汇编语言和高级语言，如Java、C++等；
  - 从与或非的逻辑运算到CPU运算是如何处理的；
  - 还有机器学习、深度学习以及人工智能等最新领域的介绍

  如果说唯一美中不足的点，那就是其中部分环节语速过快，对于大多数国人来说看起来比较吃力，建议英语不太好的同学看的时候调成0.75倍速去观看。

  这门课并不会教你如何从0开始学会编程，而是通过不断的抽象从底层到上层进行讲解，从高层次上纵览一系列的计算机话题。

  在这期间也会穿插着很多有意思的历史故事，比如在存储器的时候就会以算盘为楔子引入，告诉你算法大约出现在公元前2500年。

  适当穿插历史故事，确保小白不会枯燥&中途放弃，当讲到计算机网络的时候，则是以动图的形式告诉你交换机上是如何工作的；

- [CS-50](https://www.youtube.com/watch?v=8mAITcNt710)

  哈佛大学公开课：计算机科学cs50是经典的计算机入门课程，他还有一个广为人知的外号：计算机速成课。

- [6.0001: Introduction to Computer Science and Programming in Python](https://www.youtube.com/watch?v=xAcTmDO6NTI&list=PLUl4u3cNGP62A-ynp6v6-LGBCzeH3VAQB&index=1)

  该课程适合很少或根本没有编程经验的学生。它旨在让学生了解计算在解决问题方面可以发挥的作用，并帮助所有专业的学生都有理由相信他们有能力编写小程序并使他们能够完成有用的目标。

- [6.821 Structure and Interpretation of Computer Programs (SICP)](https://www.youtube.com/playlist?list=PLVFrD1dmDdvdvWFK8brOVNL7bKHpE-9w0)

  SICP这门课以前在很长一段时间都是MIT入门的第一课，配套的教材SICP《计算机程序的构造和解释》也一直被认为是程序设计的经典著作，
  其中 **研究生课程MIT 6.821** 的参考书也是这本书。

- [6.042: Mathematics for Computer Science](https://www.youtube.com/playlist?list=PLUl4u3cNGP60UlabZBeeqOuoLuj_KNphQ)

  本课程提供面向计算机科学和工程的离散数学的交互式介绍。主题覆盖范围大致分为三部分：

  - 数学的基本概念：定义，证明，集合，函数，关系。
  - 离散结构：图形，状态机，模块化算术，计数。
  - 离散概率理论。



## MIT 公开课

### 基础科目

- [6.004: Computation Structures](https://www.youtube.com/playlist?list=PLUl4u3cNGP62WVs95MNq3dQBqY2vGOtQ2)

  本课程介绍了数字系统和计算机架构的设计。
  强调以高级硬件语言表达所有硬件设计并综合设计，主题包括组合电路和顺序电路、可编程硬件指令集抽象、单周期和管道处理器实现、多级内存层次结构、虚拟内存、异常和 I/O 以及并行系统。

- [6.006: Introduction to Algorithms](https://www.youtube.com/playlist?list=PLUl4u3cNGP63EdVPNLG3ToM6LaEUuStEY)

  本课程介绍了计算问题的数学建模。它涵盖了用于解决这些问题的常见算法，算法范例和数据结构。
  该课程强调算法与编程之间的关系，并介绍针对这些问题的基本性能指标和分析技术。

- 6.009: Fundamentals of Programming

  本课程介绍编程的基本概念。旨在培养将基本方法从编程语言应用于抽象问题的技能。
  主题包括编程和 Python 基础知识、计算概念、软件工程、算法技术、数据类型和递归。实验组件包括软件设计、施工和设计实施。

- [MIT 6.004 Computation Structures](https://www.youtube.com/playlist?list=PLDSlqjcPpoL64CJdF0Qee5oWqGS6we_Yu)

- [6.005 Software Construction](https://ocw.mit.edu/courses/6-005-software-construction-spring-2016/)

  Java的基础课程，可以和6.031一起学习。

- [6.008 Introduction to Inference](https://web.mit.edu/6.008/www/videos/): 接口技术导论

- [6.009/6.1010 Fundamentals of Programming](https://py.mit.edu/fall25): 程序设计基础

- [6.033 Computer System Engineering](https://link.zhihu.com/?target=https%3A//ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-033-computer-system-engineering-spring-2018): 计算机系统

- [6.034 Artificial Intelligence](https://www.youtube.com/playlist?list=PLUl4u3cNGP63gFHB6xb-kVBiQHYe_4hSi): 人工智能

- [6.041 Probabilistic Systems Analysis and Applied Probability](https://www.youtube.com/playlist?list=PLUl4u3cNGP61MdtwGTqZA0MreSaDybji8): 概率系统分析和应用概率论

- [6.042J Mathematics for Computer Science](https://www.youtube.com/playlist?list=PLB7540DEDD482705B): 计算机科学中的数学（离散数学）

### 进阶科目

- 6.031Software Construction (Java)

  这门课主要学的是Java，没有参考书，课程链接有比较详细的线上教程；可以和6.005一起学习。

- [6.033 Computer System Engineering 2005](https://www.youtube.com/playlist?list=PL6535748F59DCA484)

- [6.035 Computer Language Engineering 2005](https://www.youtube.com/playlist?list=PL0300FE43396456C1)

- 6.036 Introduction to Machine Learning: 机器学习导论

- 6.045J Automata, Computability, and Complexity: 自动机、可计算性和复杂性

- 6.046J Design and Analysis of Algorithms: 算法的设计与分析

- 6.073 Creating Video Games: 视频游戏开发

- 6.080/6.089 Great Ideas in Theoretical Computer Science: 计算机科学的伟大理论

- 6.170 Software Studio（前端开发JavaScript）: 软件工程

  这门课以前是使用Ruby的Rails和JavaScript来搭建一个全栈开发项目。
  而现在则完全是使用JavaScript来做全栈开发，内容包含Node、MySQL、React等。

- 6.171 Software Engineering for Web Applications: Web应用开发与软件工程

  这门课可以和6.170一起学，主要如何构建高并发、安全、可靠等的Web应用

- [6.172 Performance Engineering of Software Systems](https://www.youtube.com/playlist?list=PLUl4u3cNGP63VIBQVWguXxZZi0566y7Wf): 软件系统的性能（C语言）

  这门课主要教你如何构建大型高性能的软件系统，包含性能分析、高性能、缓存优化、并行程序等，使用的是C语言。

  这门课没有参考教材，倒是有一堆文章需要你阅读，具体可以去官网的Readings了解。

- 6.175 Constructive Computer Architecture: 计算机体系结构

    学这门课需要你掌握一门面向对象的编程语言（Java或C++）或函数式编程语言（ML或Haskell）,以及6.004、6.005

- 6.207J Networks: 计算机网络

- 6.338J Parallel Computing: 并行计算

- 6.801 Machine Vision: 机器视觉

- 6.803 The Human Intelligence Enterprise: 人工智能事业

- 6.804J Computational Cognitive Science: 计算认知科学

- 6.811 Principles and Practice of Assistive Technology: 辅助技术原理与实战

- 6.813 User Interface Design and Implementation: 用户界面设计与实践

- 6.815/6.865 Digital and Computational Photography: 数字与计算成像

- 6.816/6.189 Multicore Programming Primer: 多处理器编程的艺术

  这门课没有对外公开，只了解到它使用的参考教材是《多处理器编程的艺术》

- 6.819/6.869：Advances in Computer Vision: 高级计算机视觉

- 6.820 Fundamentals of Program Analysis: 程序分析基础

- 6.837 Computer Graphics: 计算机图形学

- 18.404/6.840 Theory of Computation: 计算理论导引

### 专业科目

- 6.034: Artificial Intelligence

  本课程向学生介绍人工智能的基本知识表现、问题解决和学习方法。完成 6.034 后，学生应该能够通过组装解决方案来开发智能系统，从而解决具体的计算问题；了解知识表现、问题解决和学习在智能系统工程中的作用；并理解解决问题、视觉和语言在从计算角度理解人类智力方面的作用。

- 6.033: Computer Systems Engineering (CI-M)

  本课程包括有关计算机软件和硬件系统工程的主题。主题包括控制复杂性的技术；使用客户端 - 服务器设计、操作系统的强大模块化；性能，网络；命名；安全和隐私；容错系统、并发活动的原子性和协调性以及恢复；计算机系统对社会的影响。

- 6.031: Elements of Software Construction

  介绍软件开发的基本原则和技术：如何编写安全无错误、易于理解且易于更改的软件。主题包括规范和不变；测试、测试用例生成和覆盖范围；抽象数据类型和表示独立性；面向对象编程的设计模式；并发编程，包括消息传递和共享内存并发，死锁；函数编程，具有不可变的数据和高阶函数。

- 6.036 Introduction to Machine Learning

  从建模和预测的角度介绍机器学习的原则、算法和应用；制定学习问题；代表性、过度拟合、概括性；聚类、分类、概率建模；和诸如支持向量机、隐式马尔科夫模型和神经网络等方法。

- 6.045: Automata, Computability, and Complexity

  关于计算定义问题的数学介绍，以及计算机可以解决的问题。考虑通过有限的自动机，电路，图灵机和通信复杂性可以有效解决哪些问题。在某些情况下，为问题提供完整，严格的答案。建立根据难度对计算问题进行分类的技能。讨论其他基本问题，包括 Church-Turing 论文，P 与 NP 问题以及随机性。

- 6.046: Design and Analysis of Algorithms

  高效算法的设计与分析技术，强调在实践中有用的方法。主题包括排序；搜索树、堆和哈希；分而治之；动态编程；贪婪算法；摊销分析；图形算法；和最短的路径。高级主题可能包括网络流；计算几何；数字理论算法；多项式和矩阵计算；缓存；和并行计算。

### 研究生课程

- 6.254 Game Theory with Engineering Applications (Spring 2010) 游戏理论的工程应用

- 6.823 Computer System Architecture 计算机系统架构

- 6.824 Distributed Computer Systems Engineering 分布式计算机系统工程

  本课程介绍分布式系统设计的抽象和实现技术。主题包括：服务器设计、网络编程、命名、存储系统、安全性和容错能力。

  这门课的老师之一是Robert Morris，他是蠕虫的制造者，曾被评为5大黑客之一。

- 6.825 Techniques in Artificial Intelligence (SMA 5504) 人工智能技术

- 6.826 Principles of Computer Systems 计算机系统理论

- 6.827 Multithreaded Parallelism: Languages and Compilers 多线程并发：语言与编译器

- 6.828: Operating System Engineering 操作系统工程

  前置课程是 6.033 Computer System Engineering

  本课程研究操作系统工程的基本设计和实现理念。讲座以 UNIX 和研究论文的研究为基础。
  主题包括虚拟内存、线程、上下文切换、内核、中断、系统调用、进程间通信、协调和软件和硬件之间的交互。
  单个实验室任务涉及在 C 中实施小型操作系统，并带有一些 x86 组件。

  [MIT 6.828 JOS与NJU ICS NEMU源码精读](https://knowledgehive.github.io/6.828/#)

- 6.829: Computer Networks 计算机网络

  全球网络基础设施如何工作，其基础是什么设计原则？
  在实践中，这些设计原则在哪些方面受到了损害？我们如何使它在当今世界更好地工作？
  面对快速增长的规模和异质性，我们如何确保它在未来运作良好？
  应该如何编写 Internet 应用程序，以便它们能够为自己和使用基础结构的其他人获得最佳性能？
  这些是本课程中正在处理的一些问题。本课程将侧重于大型联网系统的设计、实施、分析和评估。

- 6.830/6.814 Database Systems 数据库系统

  本课程依托数据库社区的主要阅读资料，向研究生介绍数据库系统的基础，重点介绍关系代数和数据模型、架构规范化、查询优化和事务。

- 6.831 User Interface Design and Implementation 用户接口设计与应用

- 6.838 Algorithms for Computer Animation 计算机动画的算法

- 6.840J Theory of Computation 计算理论

- 6.841J Advanced Complexity Theory 计算复杂性理论

- 6.844 Computability Theory of and with Scheme（Scheme语言） 计算复杂性理论

- 6.851 Advanced Data Structures 高级数据结构

- 6.852J Distributed Algorithms 分布式算法

- 6.854J Advanced Algorithms 高级算法

- 6.855J Network Optimization 网络优化

- 6.856J Randomized Algorithms 随机算法

- 6.857 Network and Computer Security 网络与计算机安全

- 6.858 Computer Systems Security 计算机系统安全

- 6.859J Integer Programming and Combinatorial Optimization 整数规划与组合优化

- 6.863J Natural Language and the Computer Representation of Knowledge 自然语言与计算机表示

- 6.864 Advanced Natural Language Processing 高级自然语言处理

- 6.866 Machine Vision 机器视觉

- 6.867 Machine Learning 机器学习

- 6.871 Knowledge-Based Applications Systems  知识型应用系统

- 6.875 Cryptography and Cryptanalysis  密码学与密码分析

- 6.876J Advanced Topics in Cryptography 高级密码学

- 6.881 Representation and Modeling for Image Analysis 图像表示与分析方法

- 6.883 Pervasive Human Centric Computing  普遍计算技术

- 6.883 Program Analysis 程序分析

- 6.890 Algorithmic Lower Bounds 算法复杂度分析

- 6.892 Computational Models of Discourse 语篇分析的计算机模型

- 6.895 Essential Coding Theory 编码要素理论

- 6.895 Theory of Parallel Systems 平行系统理论

- 6.896 Theory of Parallel Hardware 平行硬件理论

- 6.897 Selected Topics in Cryptography 密码学论文选读

### 实验课

MIT针对不同的编程语言都有配套的实验课Lab，如C语言、Java、C++等

- 6.087 Practical Programming in C C语言实战

- 6.088 Introduction to C Memory Management and C++ Object-Oriented Programming C语言内存管理与C++面向对象

- 6.090 Building Programming Experience: A Lead-In to 6.001  软件开发经验

- 6.092 Introduction to Programming in Java  Java程序导论

- 6.096 Introduction to C++  C++程序开发

- 6.370 The Battlecode Programming Competition  人工智能竞赛

## others

- 操作系统
  - [MIT 大名鼎鼎的6.828](<https://pdos.csail.mit.edu/6.828/2018/schedule.html>)
  - [清华大学的OS课程 ucore,视频在学堂在线和bilibili均有](<http://os.cs.tsinghua.edu.cn/oscourse/OS2017spring#A.2Bi.2F56C4nGmJE->)
  - [rust 版本ucore rcore](https://rcore-os.github.io/rCore_tutorial_doc/)
  - [南京大学 ICS PA](https://nju-projectn.github.io/ics-pa-gitbook/ics2019/)
  - NJU ICS PA  [Bilibili链接](https://www.bilibili.com/video/BV1qa4y1j7xk)
  - [NJU OS](https://www.bilibili.com/video/BV1HN41197Ko?p=1)
  - 上海交通大学 操作系统 (陈海波、夏虞斌)    [BiliBili链接](https://www.bilibili.com/video/BV1B341117Ez?from=search&seid=711317104834272627&spm_id_from=333.337.0.0)
    - [上海交通大学 SE315](https://ipads.se.sjtu.edu.cn/courses/os/)   [视频课程（好大学在线） ](https://www.cnmooc.org/portal/course/5610/14956.mooc)   [教材 《现代操作系统——原理与实现》](https://ipads.se.sjtu.edu.cn/mospi/)   [配套 Lab](https://gitee.com/ipads-lab/chcore-lab)
  - [CMU CSAPP 对应的课程 15213](https://www.cs.cmu.edu/~213/schedule.html)
  - [CMU 15410/605](https://www.cs.cmu.edu/~410/)
  - [Gate Lectures OS](https://www.youtube.com/playlist?list=PLEbnTDJUr_If_BnzJkkN_J0Tl3iXTL8vq)
  - [清华大学 ucore OS ucore实验指导书](https://nankai.gitbook.io/ucore-os-on-risc-v64/)
- 程序语言
  - [Structure and Interpretation of Computer Programs](https://book.douban.com/subject/1451622/)
  - [CIS 194(学习haskell)](https://www.seas.upenn.edu/~cis194/spring13/lectures.html)
  - [伯克利改的Python版SICP](https://cs61a.org/)
  - [华盛顿大学 Programming Languages](<https://www.coursera.org/lecture/programming-languages/welcome-and-some-course-mechanics-3dedE>)
  - [浓缩版mit 6.001(SICP)](http://web.mit.edu/alexmv/6.037/)
- 编译原理
  - [stanford CS143](http://web.stanford.edu/class/cs143/)
  - [stanford CS243](https://suif.stanford.edu/~courses/cs243/)
  - [stanford CS343](http://web.stanford.edu/class/cs343/)
  - [Gate Lectures 编译](https://www.youtube.com/playlist?list=PLEbnTDJUr_IcPtUXFy2b1sGRPsLFMghhS)
  - [SysY 语言编译到 RISC-V-北大编译实践在线文档](https://pku-minic.github.io/online-doc/#/)

- 数据库系统
  - [CMU 15445](https://15445.courses.cs.cmu.edu/fall2019/#)
  - [CMU 15721](https://15721.courses.cs.cmu.edu/spring2019/)
  - [MIT 6.830/6.814](<http://db.lcs.mit.edu/6.830/sched.php>)
  - [pingcap talent-plan](https://zhuanlan.zhihu.com/p/61340679)
  - [instruction](https://docs.google.com/document/d/1UG0OHuL6l_hHWs3oyT9gA2n7LuYUfV23nmz0tRvXq2k/edit#heading=h.ywlair765ic9)
  - [CS 245](http://web.stanford.edu/class/cs245/#schedule)
  - [斯坦福 CS346](https://web.stanford.edu/class/cs346/2015/)
  - [伯克利 CS 186](https://cs186berkeley.net/)
  - [斯坦福 CS145](https://www.bilibili.com/video/av19616961/)
  - [华盛顿大学 CSE444](https://courses.cs.washington.edu/courses/cse444/15sp/)
- 分布式系统
  - [MIT 6.824](https://pdos.csail.mit.edu/6.824/)
  - [Stanford CS244b: Distributed systems](https://www.scs.stanford.edu/14au-cs244b/)
  - [CMU 15-440/640, Spring 2016: Distributed Systems](https://www.cs.cmu.edu/~15-440/)
  - **数据结构** by 邓公 from 清华：[MOOC](https://link.zhihu.com/?target=http%3A//www.xuetangx.com/courses/course-v1%3ATsinghuaX%2B30240184%2Bsp/about)
- 数据结构与算法
  - [UCB CS61b](https://inst.eecs.berkeley.edu/~cs61b/)
  - [普林斯顿 Algs4](http://algs4.cs.princeton.edu/)
  - [MIT 6.006](http://open.163.com/special/opencourse/algorithms.html)
  - [Gate Lectures 算法和数据结构](https://www.youtube.com/playlist?list=PLEbnTDJUr_IeHYw_sfBOJ6gk5pie0yP-0)
  - [《算法 第四版》](https://algs4.cs.princeton.edu/home/)
  - 普林斯顿在 [Coursera](https://www.coursera.org/) 上也公开了两门对应课程：[Algorithms, Part I](https://www.coursera.org/learn/algorithms-part1) 和 [Algorightmsm, Part2](https://www.coursera.org/learn/algorithms-part2)
  - [斯坦福 CS106b](http://open.163.com/special/opencourse/abstractions.html) (broken link)
- 计算机网络
  -  **计算机科学**  哈佛： [B站](https://link.zhihu.com/?target=https%3A//www.bilibili.com/video/av310513%3Ffrom%3Dsearch%26seid%3D4682685095165261117)
  - [CMU-15441](https://computer-networks.github.io/sp19/)
  - [cs144](https://cs144.github.io/)
  - [top to down approach](http://uniteng.com/wiki/doku.php?id=classlog:computer_networks)
  - [myk's top-to-down](https://github.com/moranzcw/Computer-Networking-A-Top-Down-Approach-NOTES)
  - [伯克利 EE122](https://www2.eecs.berkeley.edu/Courses/EE122/)
  - [Gate Lectures 计网](https://www.youtube.com/playlist?list=PLEbnTDJUr_IegfoqO4iPnPYQui46QqT0j)
  - [斯坦福 CS144](https://www.bilibili.com/video/av11930774/)
  - 麻省理工MIT 计算机安全与网络安全  [国内B站链接](https://www.bilibili.com/video/BV1Bm4y1o7cx?spm_id_from=333.1007.top_right_bar_window_history.content.click)
- 计算机系统设计
  - [nand2tetris](http://www.nand2tetris.org/)
  - CMU 15-213 [视频](https://www.bilibili.com/video/BV1iW411d7hd)  [讲义](https://hansimov.gitbook.io/csapp/)
  - MIT 6.828  [官网主页](https://pdos.csail.mit.edu/6.828/)   [xv6中文文档](https://th0ar.gitbooks.io/xv6-chinese/content/content/cover.html)
  - [UCB CS61c](http://www-inst.eecs.berkeley.edu/~cs61c/)
- 计算机图形学
  - [game101 GAMES101: 现代计算机图形学入门](https://sites.cs.ucsb.edu/~lingqi/teaching/games101.html)



---

</details>

# 博客、社区

- 神级程序员
  - [bellard](https://bellard.org/)
  - [Daniel Holden](https://www.theorangeduck.com/)
  - [Thomas E. Dickey](https://invisible-island.net/)
- 社区
  - [0xffff平台帖子：有什么值得一看的博客推荐](https://0xffff.one/d/12)
- rss 博客
  - [plantegg](https://plantegg.github.io/)

# 付费课程

- [udemy](www.udemy.com)
<!-- [bilibili搬运](https://www.bilibili.com/video/BV1vA4y197C7/?spm_id_from=333.337.search-card.all.click&vd_source=c8e13c17fa73d1d5aca51505a5d3170d) -->

# 词典

- [自由在线计算机词典](https://foldoc.org/)


# 待做项(备忘录草稿)

> 完成项会可能继续完善更新，以下不一定为最新。
> 详细todo会具体在文件中使用TODO标出。个人使用folke/todo-comments.nvim查询todu项。

- 长期
  - [ ] java编程思想 整理
  - [ ] 计算机网络完善
  - [ ] 操作系统完善
  - [ ] [Java 全栈知识体系](https://www.pdai.tech/)
- 学习深入+笔记完善
  - [ ] 微服务架构
  - [ ] 分布式系统相关
  - [ ] 数据库架构
- 待完善/整理
  - [x] [设计模式](./base/design_pattern.md)
  - [ ] 编译原理
  - [ ] zookeeper完善
  - [ ] [java并发笔记完善](./java/)
  - [x] [RabbitMQ基础整理](./Middleware/RabbitMQ.md)
  - [x] [鉴权方式整理](./others/authentication.md)
  - [ ] [CORS跨域完善+整理](./safety/CSRF与CORS.md)
  - [ ] [linux常用命令&工具系统整理](./base/linux_basic.md)
  - [x] [bash编程系统整理](./base/bash.md)
  - [x] 鉴权认证
  - [x] 分布式缓存算法整理
  - [x] JVM字节码与类的加载
  - [x] JVM性能监控和调优
  - [ ] [各种加密算法](./others/encryption.md)
  - [ ] [regex系统整理](./base/regex.md)
  - [ ] html,css,js 历史笔记+资料整理
  - [ ] latex 基本语法（vim-latex&texlab环境搭建时有练习，有时间再整）
  - [ ] [nginx&lvs 整理](./Middleware/)
- 待继续学习
  - [ ] Undertow
  - [ ] Mysql XA
  - [x] Mocikto框架
  - [ ] [JOOQ框架](./javaFrame/JOOQ.md)
  - [ ] [jersey框架](./javaFrame/jersey.md)
  - [ ] SpringBatch
  - [ ] SpringData
  - [ ] Cassandra
  - [ ] xdb
  - [ ] [elasticsearch](./big_data/elasticsearch.md)
  - [ ] TensorRT
  - [ ] [React](./front_end/react.md) <!-- - 个人博客框架 -->
    - 练手项目：浏览器主页私人定制
    - 练手项目：个人博客定制
  - [ ] React-Native
    - 练手项目：记账软件
  - [ ] Vue
    - **练手项目：博客评论系统前端**
  - [ ] [常用JVM](./java/JVM-常用JVM.md)
  - [ ] electron
    - 网易云 音乐可视化软件 [音乐频谱库vudio.js](https://github.com/alex2wong/vudio.js)
  - [ ] 大数据：数据湖，湖仓一体，delta lake
  - [ ] 对象存储，apache ozone，以及其他存储概念
  - [ ] 中间件Nacos
- 阅读书目
  > 个人习惯在实体书，或者使用 知之阅读 做一些读书笔记。markdown版笔记主要针对一些工具书
  - [x] 代码整洁之道
  - [x] 重构：改善既有代码设计
  - [ ] [《操作系统导论》(OS Three Easy Pieces)](https://github.com/remzi-arpacidusseau/ostep-translations/tree/master/chinese)
  - [ ] clickhouse 原理解析与应用实践
  - [ ] 数据密集型应用系统设计
  - [ ] 深入理解现代操作系统
  - [ ] Operating Systems:Three Easy Pieces
  - [ ] 冒号课堂
  - [ ] Linux C 编程一站式学习
  - [ ] Redis设计与实现
  - [ ] TCP/IP 详解
- 大数据笔记整理完善（抽时间零碎）
  - [x] hadoop+hive+hbase
  - [ ] Spark
  - [ ] Flink
  - [ ] ELK
- 短期小任务
  - [ ] Gossip 协议
  - [x] Raft 协议
  - [ ] SPEL
  - [ ] 阿里巴巴数据库内核月报目录爬虫
- lang
  - [ ] C/C++
    - [ ] 10分钟定时截图工具
    - [ ] 动态桌面，展示 todo
    - [ ] linux 触摸板驱动。参考：[OS-X-Voodoo-PS2-Controller](https://github.com/RehabMan/OS-X-Voodoo-PS2-Controller)
  - [ ] go
    - [ ] **练手项目：博客评论系统后端，针对业务逻辑编写测试**
  - [x] lua
    - [x] [实现markdown标题编号nvim插件](https://github.com/whitestarrain/md-section-number.nvim)
      - [ ] 扩展，实现侧边栏。
    - [ ] 双链生成插件(基于标题？tag功能？lsp补全？)，用来优化YourBrain思维导图
  - [ ] TypeScript
    - [x] 基础
    - [ ] 深入
    - [ ] 命令行工具开发
    - [ ] **练手项目：kakuyomu 小说终端阅读器(包含登录功能)**
  - [ ] php 工作中，有项目用，可以看看
  - [ ] Elixir
  - [ ] rust
  - [ ] kotlin
    - poweramp 联网获取歌词插件，说不定可以基于这个做二次开发[LyricsForPowerAmp](https://github.com/abhishekabhi789/LyricsForPowerAmp)
  - [ ] lisp
  - [ ] ruby
  - [ ] zig
- 机器学习&深度学习
  - [ ] opencv 系统整理
  - [ ] 深度学习笔记系统整理（暂时抽不出时间）
- 其他
  - [ ] JNI（c++之后）
  - [x] nvim配置模块化(迁移到[dotfiles](https://github.com/whitestarrain/dotfiles))
  - [ ] [想搞搞树莓派](https://shumeipai.nxez.com/)

<!--

cookie和session攻击
25匹马赛马

数据库 分区分库分表

-->

# 想做的

- 歌词动效
  - amazarashi 专辑里面的效果
  - 网易云，节奏前线 效果

# todo refs

## docker

- 理解docker基础—文件系统隔离:https://juejin.cn/post/6953648834652143629
- paas相关资料：
  - https://www.huweihuang.com/kubernetes-notes/concepts/architecture/paas-based-on-docker-and-kubernetes.html
  - https://www.redhat.com/zh/topics/cloud-computing/what-is-paas
  - https://howardlau.me/programming/operating-matrix-in-sysu.html
- [理解 docker 容器中的 uid 和 gid ](https://www.cnblogs.com/sparkdev/p/9614164.html)
- [docker 从入门到实践](https://yeasy.gitbook.io/docker_practice/) （作为整体结构，docker体系说明很全，基础笔记也很详细）
- [docker多阶段编译，c静态编译，降低镜像大小](https://segmentfault.com/a/1190000022127446)
- [docker容器里uid&gid和宿主机里uid&gid的关系](https://blog.51cto.com/u_11791718/4779730)
- [docker，supervisor初始进程问题，解决，s6](https://gist.github.com/snakevil/f14d22efcf5d7128dee6b2712bfccf00)
- [谁是Docker容器的init(1)进](https://shareinto.github.io/2019/01/30/docker-init%281%29/)
- docker 宿主机 与 容器 进程对应关系

- [百度matrix](http://gitlab.baidu.com/matrix/matrix-agent3)
  - [baseEnv实现方式](http://gitlab.baidu.com/matrix/matrix-agent3/wikis/Baseenv)

## editor

### nvim

- nvim markdown outline插件实现：https://github.com/Scuilion/markdown-drawer ui代码可以看一下
- null-ls:
  - [shellcheck添加code](https://github.com/jose-elias-alvarez/null-ls.nvim/pull/39)
  - on_attach添加 key_binding
- [nvim配置优化](https://www.xwxwgo.com/post/2022/02/10/neovim%E5%B0%8F%E7%BB%93/)
  ```
  1. 删除 cmd
  2. 插件变量重命名viewBind。vim.opt_local for set
  3. packer，转为全lua
  4. 启动速度优化配置
  5. whickkey.nvim
  6. message 提示插件
  ```
- 获取所有highlight: `so $VIMRUNTIME/syntax/hitest.vim`
- nvim whichkey支持显示keymap.set里面的desc属性，可以看看不使用wk.register。以及一些group name能不能set到 nop 实现就能统一mapping格式了
- feline 配置参考： [feline nightfox](https://github.com/EdenEast/nightfox.nvim/blob/main/misc/feline.lua)
  - 值得一抄
- [lsp signature提示支持](https://github.com/ray-x/lsp_signature.nvim)
- [pyright 需要file-based 请求cancelation机制，当前nvim似乎不支持](https://github.com/microsoft/pyright/issues/4878)
  - [lsp文档](https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#cancelRequest)
- [relative number 位置问题](https://github.com/neovim/neovim/pull/20621)
  - [自定义statuscolumn插件(nvim0.9)](https://github.com/luukvbaal/statuscol.nvim)
- [cmp优化](https://github.com/quintin-lee/NVCode/blob/master/lua/configs/lsp/init.lua)
- [pyright优化](https://www.reddit.com/r/neovim/comments/onphl1/pyright_performance_tuning_native_lsp/)
- [nvim lsp progress](https://github.com/linrongbin16/lsp-progress.nvim)
- vim 命令行中，读取register，使用 ctrl-r + register_index
- [glepnir/nvim, epoch.lua实现了自己的简单补全方案](https://github.com/glepnir/nvim)
- [nvim venn ascii draw plugin](https://github.com/jbyuki/venn.nvim)
- [nvim LSP: semantic tokens support](https://github.com/neovim/neovim/pull/21100)
  - [Semantic Highlighting in Neovim](https://gist.github.com/swarn/fb37d9eefe1bc616c2a7e476c0bc0316#disabling-highlights)
  - 覆盖插件
    - [hlargs.nvim](https://github.com/m-demare/hlargs.nvim)
    - [nvim-semantic-tokens](https://github.com/theHamsta/nvim-semantic-tokens)
- [nvim 布局预定义 folke/edgy.nvim](https://github.com/folke/edgy.nvim)
- [onedark主题](navarasu/onedark.nvim)
- neovim插件尝试：ThePrimeagen/harpoon
- vim:
  - tags管理:[ludovicchabant/vim-gutentags](https://github.com/ludovicchabant/vim-gutentags)，大型项目可能需要ctags跳转
- [telescope 支持 visual模式](https://github.com/Nauticus/dotfiles/blob/master/.config/nvim/lua/config/core/utils.lua)
- [nvim展示图片，inline graph protocol](https://github.com/edluffy/hologram.nvim)
- [vim quickfix 高级使用](https://blog.csdn.net/niuiic/article/details/115800729)
- vim text object 扩展
  - target.vim
  - mini.ai
- [awesome nvim code runner](https://github.com/rockerBOO/awesome-neovim#code-runner)
  - [overseer](https://github.com/stevearc/overseer.nvim/tree/master)
  - toggleterm
    - 要看下不kill进程的bug修了没, mac上没问题
- [jedrzejboczar/possession.nvim](https://github.com/jedrzejboczar/possession.nvim)
  - 感觉能替代startify，通过hooks和user_data
- [有一些不错的插件](https://innei.in/posts/Z-Turn/nvim-lua-config-init)
- [Astronvim里面的插件可以借鉴一下](https://github.com/AstroNvim/AstroNvim)
- [nvim-java-ide](https://sookocheff.com/post/vim/neovim-java-ide/)
- [比较完整的vim学习笔记](https://yyq123.github.io/learn-vim/)
- 几个可以尝试的插件
  - [nvim-bqf better quick fix，可以用来批量替换](https://github.com/kevinhwang91/nvim-bqf)
    - 不过普通的 quickfix 好像也行
  - [incline.nvim 显示窗口名称，但是会遮盖，所以不是太想用](https://github.com/b0o/incline.nvim)
- [pantran.nvim, translate plugin](https://github.com/potamides/pantran.nvim)
- [markdown-preview.nvim 支持remote vim + local browser](https://github.com/iamcco/markdown-preview.nvim)
- [telescope 辅助插件，支持rg参数](https://www.reddit.com/r/neovim/comments/11ukbgn/how_to_includeexclude_files_in_telescope_live_grep/)
  - [menu来切换参数](https://github.com/molecule-man/telescope-menufacture)
  - [自动带上参数](https://github.com/nvim-telescope/telescope-live-grep-args.nvim)
  - [简单实现的telescope-menufacture](https://github.com/tjdevries/config_manager/blob/7400242/xdg_config/nvim/lua/tj/telescope/custom/multi_rg.lua)
- [x] mapping配置改为 require("whichkey").register
  - 主要是为了支持 buffer mapping 的 prefix 展示
  - 处理下格式，whichkey最新版本，参数格式有变
  - git clone 的时候指定一下commit-id
- [x] [host: iterm2+tmux, ssh: nvim-0.10 导致host 的 tmux崩溃](https://github.com/tmux/tmux/issues/3983)
  - 设置`TMUX`环境变量: `TMUX="tmux" nvim`
- [ ] lsp semantic token highlighting 优先级调成比 treesitter高
  - 这样就能实现vscode上，引用不存在成员，不highlight为红色的效果了
  - 当前默认关了lsp的语义高亮
- lisp编辑辅助插件：
  - nvim([parpar](https://github.com/dundalek/parpar.nvim)) = emacs(Parinfer + Paredit)
  - nvim-parinfer
- [oil.nvim](https://github.com/stevearc/oil.nvim)

### emacs

- compilation模式，以及跳转到第一个error处
  - https://emacs.stackexchange.com/questions/13646/jumping-to-the-first-error-not-warning-when-compiling
  - 54分25秒：https://www.youtube.com/watch?v=SRgLA8X5N_4
- [远程开发模式tramp](https://zhuanlan.zhihu.com/p/488366338)
- [god mod, 类似vim的模式切换](https://github.com/emacsorphanage/god-mode)
- [embark, 另一种思路的which-key替代工具](https://github.com/oantolin/embark)
- [emacs 入门教程](https://github.com/Pavinberg/emacs-book)
- [emacs lisp简明教程](https://smacs.github.io/elisp/)
- [emac chinese document](https://github.com/lujun9972/emacs-document)
- 中英混打：https://emacs-china.org/t/os-smart-input-source/13436
- org, org-roam（obsidian的双链）
- 强大的org mode(https://www.zmonster.me/2015/07/12/org-mode-introduction.html)
- emacs甚至支持ditta，并输出图片。https://ditaa.sourceforge.net/
- [从操作系统架构的角度谈Emacs的学习](http://www.nituchao.com/os-tool/emacs-os-arch.html)
- [Emacs Lisp 简明教程](https://smacs.github.io/elisp/)
- [doom emacs](https://github.com/doomemacs/doomemacs)
- [Centaur Emacs](https://github.com/seagle0128/.emacs.d)
  - [Centaur markdown 预览支持语法高亮和各类图](https://emacs-china.org/t/centaur-markdown/12170)
- [emacs 作者 Richard Stallman 我的Lisp经历和GNU Emacs的开发](https://www.gnu.org/gnu/rms-lisp.zh-cn.html)
- [emacs2ram，把emacs放到内存中](https://github.com/lujun9972/emacs-document/blob/master/emacs-common/十倍提升Emacs性能.org)
- [21 天学会 Emacs](https://book.emacs-china.org/)
- [emacs zenburn theme， 感觉看起来不错](https://github.com/bbatsov/zenburn-emacs)
- [Emacs 自力求生指南 ── 来写自己的配置吧](https://nyk.ma/posts/emacs-write-your-own/)
- lisp edit
  - [lispy](https://github.com/abo-abo/lispy)
  - [lispyville: lispy + evil](https://github.com/noctuid/lispyville)
  - parinfer
    - [parinfer-rust-mode](https://github.com/justinbarclay/parinfer-rust-mode)
    - [parinfer-mode](https://github.com/DogLooksGood/parinfer-mode)
  - paredit
- [org-mode 编辑大文件](https://emacs-china.org/t/org-mode/7448)
- [Emacs smartparens auto-indent](https://xenodium.com/emacs-smartparens-auto-indent/)
- [simple c mode](https://github.com/rexim/simpc-mode)
- [一些杂乱的emacs中文文档，可以过一遍](http://blog.lujun9972.win/emacs-document/)
- [awesome emacs](https://github.com/emacs-tw/awesome-emacs)
- [**Emacs beginner resources**](https://sachachua.com/blog/2014/04/emacs-beginner-resources/)
- [关于 Emacs 中的变量你需要知道的事情](https://linux.cn/article-12150-1.html)
- [manateelazycat: 我平常是怎么使用 Emacs 的？](https://manateelazycat.github.io/2022/11/07/how-i-use-emacs/)
  - 有非常多花里胡哨的东西，我估计是不会用的。有太多的第三方依赖项，太脏了。
  - 尤其是那个[ESA](https://github.com/emacs-eaf/emacs-application-framework)
  - 而且有不少人性化的功能，都是vim自带的，这里面都是自定义elisp函数实现的，而且需要自己考虑绑定的key吧
  - 这也让我看到了emacs，不人性化的地方有不少
  - 作为编辑器，inbox的功能，我个人还是更喜欢vim了。
- [Emacs 插件开发手册](https://manateelazycat.github.io/2022/11/18/write-emacs-plugin/)
  - 配置emacs前必须要看一下，插件加载的流程
- [ ] [专业emacs入门](https://pavinberg.github.io/emacs-book/zh/)
- [ ] [org-modern](https://github.com/minad/org-modern)
- [ ] [paw.el外语学习，高亮笔记，单词本，分词断句(英、日)，等一体的新插件](https://emacs-china.org/t/paw-el-emacs-lingq/27331)

### vscode

- python debug config snippet

  ```json
  {
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
      {
        "name": "Python: Current File",
        "type": "python",
        "request": "launch",
        "program": "${file}",
        "console": "integratedTerminal",
        "justMyCode": true
      },
      {
        "name": "Python: Celery worker",
        "type": "python",
        "request": "launch",
        "module": "celery",
        "console": "integratedTerminal",
        "args": [
          "-A",
          "superset.tasks.celery_app",
          "worker",
          "-l",
          "info",
          "-P",
          "solo",
        ],
        "justMyCode": false
        // 生产任务: celery --app=superset.tasks.celery_app beat
        // 或者和beat的debug交替执行
      },
      {
        "name": "Python: Celery beat",
        "type": "python",
        "request": "launch",
        "module": "celery",
        "console": "integratedTerminal",
        "args": [
          "-A",
          "superset.tasks.celery_app",
          "beat",
          "-s",
          "./superset/app/"
        ],
        "justMyCode": false
      },
      {
        "name": "Python: Flask",
        "type": "python",
        "request": "launch",
        "module": "flask",
        "env": {
          "FLASK_APP": "superset/app.py",
          "FLASK_ENV": "development",
          "FLASK_DEBUG": "0"
        },
        "args": [
          "run",
          "-p",
          "8066",
          "--with-threads",
          "--debugger",
          "--host=0.0.0.0",
        ],
        "jinja": true,
        "justMyCode": false,
        "console": "integratedTerminal"
      },
    ]
  }
  ```

## linux

### basic

- [我的内存呢？Linux MemAvailable 如何计算](https://lotabout.me/2021/Linux-Available-Memory/)
- [nixcraft](https://www.cyberciti.biz/)
- [linux运维，包括iptables](https://www.zsythink.net/)
- [linux性能分析](https://mp.weixin.qq.com/s/i55jZoQ1DbkYCedwg5RPLg)
- [kcp](https://github.com/skywind3000/kcp)

### bash/shell

- [Use { ..; } instead of (..) to avoid subshell overhead](https://www.shellcheck.net/wiki/SC2235)
- [`What is the difference between the Bash operators [[ vs [ vs ( vs ((?`](https://unix.stackexchange.com/questions/306111/what-is-the-difference-between-the-bash-operators-vs-vs-vs)
- [How to execute shell command produced using echo and sed?](https://unix.stackexchange.com/questions/244372/how-to-execute-shell-command-produced-using-echo-and-sed)
- [xargs](https://www.ruanyifeng.com/blog/2019/08/xargs-tutorial.html)
- [kill 所有signal，kill -USR1](https://blog.csdn.net/xinfuxuan4/article/details/93386815)
- [what are the differences between `==` and `=` in conditional expressions?](https://unix.stackexchange.com/questions/382003/what-are-the-differences-between-and-in-conditional-expressions)
- ranger, q quit, Q quit and cd to last dir

  ```bash
    function ranger {
      local IFS=$'\t\n'
      local tempfile="$(mktemp -t tmp.XXXXXX)"
      local ranger_cmd=(
        command
        ranger
        --cmd="map Q chain shell echo %d > \"$tempfile\"; quitall"
      )

      ${ranger_cmd[@]} "$@"
      if [[ -f "$tempfile" ]] && [[ "$(cat -- "$tempfile")" != "$PWD" ]]; then
        cd -- "$(cat -- "$tempfile")" || return
      fi
      command rm -f -- "$tempfile" 2>/dev/null
    }
  ```
- ncat/nc/netcat 传输文件
- cat /dev/null
- [Bash脚本进阶指南](https://github.com/LinuxStory/Advanced-Bash-Scripting-Guide-in-Chinese)
- [here doc, here string](https://askubuntu.com/questions/678915/whats-the-difference-between-and-in-bash)
- [btop，比较酷炫的性能监视工具](https://github.com/aristocratos/btop)
- [Linux内核，后台执行: ctrl-z/fg/bg/nohup/setsid/()与&/disown/screen](https://www.cnblogs.com/aaronLinux/p/6345072.html)
- [nc 常用场景](https://www.sqlsec.com/2019/10/nc.html)
- ANSI Escape Sequences
  - [终端显示配色和样式，比如`\e[4:3m`](https://www.cnblogs.com/linuxprobe/p/14354824.html)
  - [ANSI Escape Sequences](https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797)
  - [ANSI Escape Sequence 下落的方块](https://blog.csdn.net/baiyu33/article/details/136088169)
  - [ANSI Escape Codes](https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797)
  - [Sequin: Human-readable ANSI sequences](https://github.com/charmbracelet/sequin)

### linux-c

- [gcc升级，及标准库升级](https://blog.csdn.net/wolfGuiDao/article/details/103813086)
- [理清gcc、libc、libstdc++的关系](https://blog.csdn.net/fuhanghang/article/details/113886318)
- [linux 内核理论相关](https://github.com/0voice/linux_kernel_wiki#5)
- [locale问题](https://www.jianshu.com/p/2de3c13cde89)
- [c相关技术栈知识](https://github.com/mebusy/notes)
- [An introduction to Linux's EXT4 filesystem](https://opensource.com/article/17/5/introduction-ext4-filesystem)
- [跟我一起写makefile](https://github.com/seisman/how-to-write-makefile)
- [Unix domain socket 和 TCP/IP socket 的区别](https://jaminzhang.github.io/network/the-difference-between-unix-domain-socket-and-tcp-ip-socket/)
- perror
- [Linux的.a、.so和.o文件 对比 window下的dll，lib，exe文件](https://cloud.tencent.com/developer/article/2209440)
- [linux inotify机制](https://juejin.cn/post/6988472880518414350)
- 不能对逐字节分析感到恐惧
  - elf文件分析
  - tcp/ip 流量分析
  - iptable debug
  - java 字节码文件解析
  - ....
- [glibc版本太低，打包库并修改依赖路径](https://blog.csdn.net/weixin_34829061/article/details/127322811)
- gcc、glibc、GLIBCXX、libc++、libstdc++、linux 内核。关系、版本关系以及编译依赖
  - [理清gcc、glibc、libstdc++的关系](https://www.jianshu.com/p/a3c983edabd1)
  - [关于 gcc 中遇到的问题，这里有你想要的全部答案](https://zhuanlan.zhihu.com/p/458193070)
  - [编译GLIBC时对GCC的版本有要求](https://www.calvinneo.com/2020/12/13/gcc-glibc-compile/)
  - [gcc的版本和内核版本、glibc版本没什么关联](https://gcc.gnu.org/legacy-ml/gcc/2007-06/msg00837.html)
- gcc8编译
  - [https://blog.csdn.net/weixin_42062018/article/details/124628498](https://blog.csdn.net/weixin_42062018/article/details/124628498)
  - 不过最好还是读README
- gdb
  - 前端
    - gf
    - gf2
  - 也能调试汇编
- [2 years of learning c](https://www.youtube.com/watch?v=lMvFWKHhVZ0)
  - [make your own programming language](https://craftinginterpreters.com/)
- 如何清理deleted进程占用的空间，除kill进程外
- tmpfs 重新划分
- linux虚拟分卷划分分区
- [浅谈C/C++编程中的字符编码转换](https://blog.csdn.net/benkaoya/article/details/59522148)
- glibc38 升级步骤、问题、原理
- [linux FHS](https://refspecs.linuxfoundation.org/FHS_3.0/fhs/index.html)
- [Linux thermal子系统和lm_sensors用户态工具](https://blog.csdn.net/scarecrow_byr/article/details/104402354)
- [initramfs和mkinitcpio](https://wiki.archlinuxcn.org/wiki/Mkinitcpio)
- 文件描述符占用磁盘问题
  - [Linux系统下已删除文件继续占用空间问题(磁盘爆满异常)](https://blog.csdn.net/baidu_23275675/article/details/116143974)
  - [Linux系统句柄问题分析----linux系统文件删除后，空间却不释放原因分析](https://blog.csdn.net/Keyuchen_01/article/details/108568375)
- [systemd和systemctl详解](https://www.liuvv.com/p/c9c96ac3.html)
- 内存
  - [linuxatemyram](https://www.linuxatemyram.com/)
  - [linux内存占用分析之meminfo](https://segmentfault.com/a/1190000022518282#item-5)
  - man top
  - [High memory usage with nothing running](https://forum.manjaro.org/t/high-memory-usage-with-nothing-running/70445/9)
- terminfo, TERM, curse
  - [Why does tmux set TERM=screen?](https://stackoverflow.com/questions/74786443/why-does-tmux-set-term-screen)
  - [Getting italics working correctly in tmux](https://unix.stackexchange.com/questions/745710/getting-italics-working-correctly-in-tmux)
  - [Possible colorscheme bug in Vim ](https://github.com/dracula/vim/issues/219)
    - [tmux-256color.terminfo](https://github.com/benknoble/Dotfiles/blob/master/terminfo/tmux-256color.terminfo)
    - `set -g default-terminal "tmux-256color"`
    - vim support

      ```vim
      if $TERM =~# '\v(tmux.*)|(screen.*)'
        " set any of t_so, t_se, t_ZH, t_ZR that are necessary
      endif
      ```
  - [Checking how many colors my terminal emulator supports](https://unix.stackexchange.com/questions/23763/checking-how-many-colors-my-terminal-emulator-supports/23789#23789)
  - [Clearing tmux terminal throws error: "'tmux-256color': unknown terminal type."](https://unix.stackexchange.com/questions/574669/clearing-tmux-terminal-throws-error-tmux-256color-unknown-terminal-type)
  - [Italic fonts in iTerm2, tmux, and vim](https://github.com/alexpearce/home/issues/23)
  - [How to actually get italics and true colour to work in iTerm + tmux + vim](https://medium.com/@dubistkomisch/how-to-actually-get-italics-and-true-colour-to-work-in-iterm-tmux-vim-9ebe55ebc2be)
  - [shell bash终端中输出的颜色和格式详解（超详细）](https://www.cnblogs.com/unclemac/p/12783387.html)
  - curse, ncurse库
  - [linux 终端重新认识（2）——屏幕的输出](https://www.cnblogs.com/mmxingye/p/16332581.html)
- [Does tar create new inodes to be archived?](https://unix.stackexchange.com/questions/138594/does-tar-create-new-inodes-to-be-archived)
  - [tar posix header](https://git.savannah.gnu.org/cgit/tar.git/tree/src/tar.h)

### archlinux

- [arch 安装](https://archlinuxstudio.github.io/ArchLinuxTutorial/#/)
- [mvnw](https://www.liaoxuefeng.com/wiki/1252599548343744/1305148057976866)
- [X窗口管理器的原理剖析](https://zhuanlan.zhihu.com/p/349232688)
- [archlinux系统迁移](https://docs.shanyuhai.top/os/arch/archlinux-system-migration.html)
- [少数派上，这个人的文章可以看看](https://sspai.com/post/78916)
- [check wake on lan](https://blog.csdn.net/weixin_46517129/article/details/108572641)
- [dotfiles, alatritty配置，theme拆出来](https://github.com/elijahmanor/dotfiles)
- [archlinux配置,eww的配置看起来挺酷的，也可以试试hyprland](https://zhuanlan.zhihu.com/p/646864577)
  - [主要参考的是end-4的配置](https://github.com/end-4/dots-hyprland)
  - [包括hyprland,状态栏在内的一些配置，也可以参考下](https://nth233.top/posts/2023-02-26-Hyprland%E9%85%8D%E7%BD%AE)
- [Tutorials/Using Other Window Managers with Plasma](https://userbase.kde.org/Tutorials/Using_Other_Window_Managers_with_Plasma#i3)
- archlinux 软件降级 Downgrade
  - [archlinux wiki](https://wiki.archlinuxcn.org/wiki/%E9%99%8D%E7%BA%A7%E8%BD%AF%E4%BB%B6%E5%8C%85)
  - [参考资料](https://linux.cn/article-9730-1.html)
- [生成archLinux mirror list](https://zhuanlan.zhihu.com/p/260492196)
- [archLinux+dwm+st+ranger相关问题](https://www.cnblogs.com/codefuturedalao/p/15858589.html)
- [archlinux 笔记本，动作&硬件配置](https://github.com/levinit/itnotes/blob/main/linux/laptop%E7%AC%94%E8%AE%B0%E6%9C%AC%E7%9B%B8%E5%85%B3.md)
- [合盖子动作处理](https://bbs.archlinuxcn.org/viewtopic.php?id=10306)
  - HandleLidSwitch=lock
  - systemctl restart systemd-logind.service
- [archlinux cn](https://bbs.archlinuxcn.org/)
- [arch 电量管理,thermald, tlp, laptop-mode-tools, powertop, acpi](https://www.reddit.com/r/archlinux/comments/u4fldv/tlp_vs_laptopmodetools/)
- [arch术语](https://wiki.archlinuxcn.org/wiki/Arch_%E6%9C%AF%E8%AF%AD)
- [archlinux kernel parameters](https://wiki.archlinux.org/title/kernel_parameters)
- [安装 Archlinux 时的 ToDoList](https://coda.world/archlinux-installation-todolist)
- linux 体验优化
  - [Improving performance](https://wiki.archlinux.org/title/improving_performance#CPU)
- [Linux 基本目录规范 ——XDG](https://winddoing.github.io/post/ef694e1f.html)
  - [XDG Base Directory Specification](https://specifications.freedesktop.org/basedir-spec/basedir-spec-latest.html)
- cpu模式
  - [使用 tlp 来为 linux 省电](https://fly.meow-2.com/post/linux/tlp-for-power-saving.html)
  - [浅析 linux 电源配置](https://blog.deepin.org/posts/analyzing-the-linux-power-configuration/)
  - [tlp 配置参数](https://blog.deepin.org/posts/tlp-power-management/)
  - [Restart TLP service without rebooting system](https://askubuntu.com/questions/1242991/restart-tlp-service-without-rebooting-system)
- [风扇控制](https://zhuanlan.zhihu.com/p/55859374)
- [mosh 通过 ssf 进行tcp连接](https://github.com/mobile-shell/mosh/issues/13)
  - 主要是为了解决cpolar不支持udp的问题，抽时间可以研究下
- [AUR(arch user package)管理工具 paru，与yay类似](https://linux.cn/article-13122-1.html)

### nix

- [NixOS 与 Flakes 一份非官方的新手指南](https://nixos-and-flakes.thiscute.world/zh/)
- [Nix 和 NixOS：你们安利方法错了](https://nyk.ma/posts/nix-and-nixos/)
- [nix基础](https://juejin.cn/post/7165305697561755679)
- [Nix 详解（三） nix 领域特定语言](https://www.rectcircle.cn/posts/nix-3-nix-dsl/)
  - 没有提供源码的专有软件，会通过patchelf ld-linux.so 位置到 /nix/store/xxx-glibc-xxx/lib. [wiki](https://nixos.wiki/wiki/Packaging/Binaries) [使用 nix 包管理器解决 glibc 兼容问题](https://v2ex.com/t/892346)
- [nixos wiki](https://nixos.wiki/wiki/Main_Page)
- [nix 学习经验：安装和打包](https://linux.cn/article-16332-1.html)
- [Nix Reference Manual](https://nix.dev/manual/nix/2.18/introduction)
- [nix](https://lazamar.co.uk/nix-versions/)
- [nix安装](https://medium.com/@realLanta/一个配置文件就能在各个电脑装一模一样的系统和软件-保姆级教你轻松掌握nixos-7f026b539242)
- case记录:
  - 因为没有使用FHS，所以一些工具一起使用的时候可能有问题， 比如
    - vscode的terminal中使用gdb，需要使用 vscode FHS environment: [Getting gdb to work in vscode](https://www.reddit.com/r/NixOS/comments/1aep3c5/getting_gdb_to_work_in_vscode/)
  - [No starship prompt under nix-shell?](https://github.com/starship/starship/discussions/5378)
- 如果想学习操作系统，nix wrap了那么多层，是不是更需要理解，比较一下 nixos 和 arch 的根目录
- [nix help links mangled](https://github.com/NixOS/nixpkgs/issues/355548)
  - nix --help 文档有问题, 多出了 `8;;`
- [nix working with local file](https://nix.dev/tutorials/working-with-local-files)

## lang

### java

- maven 源码和doc：

  ```
  mvn dependency:sources
  mvn dependency:resolve -Dclassifier=javadoc
  ```

### lua

- [How to bind a luafunction to keypress in vim](https://stackoverflow.com/questions/71143517/how-to-bind-a-luafunction-to-keypress-in-vim)
- [详解lua作用域和闭包](https://zhuanlan.zhihu.com/p/452689653)

### python

- [python pkg_resouce,setup.py以及entry_points](https://zhuanlan.zhihu.com/p/503252479)
- [pytest基础使用](https://juejin.cn/post/7221769090834481189)
- [python, 实现cheatengine类似的功能](https://blog.csdn.net/wuchenlhy/article/details/135976268)
- [dataclass `__hash__` method](https://stackoverflow.com/questions/52390576/how-can-i-make-a-python-dataclass-hashable)
- [Python 闭包不支持修改 upvalue，有什么替代的解决方案](https://v2ex.com/t/251731)
- uvloop, asyncio 和 gevent
  - asyncio + uvloop > gevent

### new

- haskell: 函数式
- nim: 有点儿兴趣

## base

### os

- 多线程模型:用户和内核线程对应关系。
- [计算机体系结构基础](https://foxsen.github.io/archbase/index.html)
- [cfenollosa/os-tutorial](https://github.com/cfenollosa/os-tutorial)
  - 比较旧的os实现
- **深入理解操作系统**
  - **linux-c**
    - **鸟哥linux基础**
      - **archlinux**
  - **操作系统导论**
- [多路复用](https://juejin.cn/post/6844903935648497678)
- [含桌面的完整操作系统 Essence](https://nakst.gitlab.io/essence)
- TempleOS

### algorithm

- **[欧拉计划](https://projecteuler.net/about)**
- [图论(graph theory)算法原理、实现和应用全解](https://www.srcmini.com/1635.html)
- [知乎涉及算法查漏补缺, leetcode.md](https://www.zhihu.com/question/29316754/answer/2135428383)
- [labuladong算法小抄](https://labuladong.github.io/algo/)

### compile

- [在线分析ast语法树](https://astexplorer.net/)
- [手把手教你构建 C 语言编译器](https://lotabout.me/2015/write-a-C-interpreter-0/)
- [Let's Build a Compiler, by Jack Crenshaw](https://compilers.iecc.com/crenshaw/)
- [The LEMON Parser Generator](https://www.hwaci.com/sw/lemon/)

### hardware

- [屏幕图像显示原理](http://chuquan.me/2018/08/26/graphics-rending-principle-gpu/#屏幕图像显示原理)

## tool

### git

- [Git搭配.gitattributes强制项目文本使用lf作为换行符](https://anuoua.github.io/2020/05/21/Git搭配.gitattributes强制项目文本使用lf作为换行符/)
- [使用 Git 来处理 LF 和 CRLF 的系统差异](https://www.fullstackbb.com/post/handle-line-endings-in-git/)
- [git ignore binary file](https://gist.github.com/chichunchen/970a7e97c74a253a4503)

### tmux

- [tmux 插件列表](https://github.com/rothgar/awesome-tmux#status-bar)
- [tmux status line教程](https://arcolinux.com/everything-you-need-to-know-about-tmux-status-bar/)
- vim, tmux 切换优化
  - [Seamlessly Navigate Vim and tmux Splits](https://thoughtbot.com/blog/seamlessly-navigate-vim-and-tmux-splits)
  - [vim-tmux-navigator](https://github.com/christoomey/vim-tmux-navigator)
  - [awesomewm-vim-tmux-navigator](awesomewm-vim-tmux-navigator)
- tmux nest
  - [nest local session](https://github.com/aleclearmind/nested-tmux)
  - [nest remote session](https://github.com/samoshkin/tmux-config)
- tmux
  - 命令
    - set default-command 'bash --init-file  .....'
    - select-pane -Z: change pane when zoom
  - [session保存：tmux-resurrect](https://github.com/tmux-plugins/tmux-resurrect#tmux-resurrect)
  - tmux 剪切板互通处理
- [vim, tmux剪切板互通](https://sspai.com/post/71018)


### dotfile manager

- [General-purpose dotfiles utilities](https://dotfiles.github.io/utilities/)
- [dotfile manager: stow](https://github.com/chaneyzorn/dotfiles)
  - stow 默认push，`-adopt` 会pull
  - 会将package下的文件，link到指定目录。
    - 比如 `/usr/local/stow/ruby/bin/ruby`，
    - `stow -S ruby`后
    - 默认link到 `/usr/local/bin/ruby`，也就是把`stow dir + package`替换为了`target dir`
  - `stow -t ../stow_target_dir -S package_names -n -v`: 展示之后要做什么
- [dotbot](https://github.com/anishathalye/dotbot)
- [dotfiles + stow example](https://github.com/elijahmanor/dotfiles)
- [awesome-dotfiles](https://github.com/webpro/awesome-dotfiles)
- [git bare](https://catcat.cc/post/diyo4/)
  - `cd && git init --bare $HOME/.dotfiles`
  - `alias config='/usr/bin/git --git-dir=$HOME/.dotfiles/ --work-tree=$HOME'`
- [chezmoi](https://github.com/twpayne/chezmoi) 和 [yadm](https://github.com/TheLocehiliosan/yadm.git)，都类似是git bare的封装版本。
  - 说实话我不喜欢太多的依赖
  - [yadm 使用教程](https://www.escapelife.site/posts/696ce25d.html) 中提到 /etc配置，不过看起来是两个repo

    ```bash
    # 使用.yadm作为yadm的系统文件管理目录
    alias sysyadm="sudo yadm -Y $HOME/.yadm"
    ```

## mysql

- [查询占满/tmp硬盘，show processlist](https://blog.51cto.com/nginxs/1933625)
- [程序员进阶，一些mysql问题可以看下](https://it-blog-cn.com/blogs/db/processlist.html#二、show-processlist-参数分析)
- [mysql innodb文件格式 Antelope 与 Barracuda](https://www.cnblogs.com/jiangxiaobo/p/10846694.html)
  - [MySQL 不建议使用TEXT类型](https://www.cnblogs.com/VicLiu/p/15566181.html)

## middleware

- [nginx server块中的hostname](https://blog.csdn.net/qq_35952638/article/details/100163824)
- [nginx配置中 server_name作用](https://blog.csdn.net/Cheng_Kohui/article/details/82930464)
- [celery+redis 探究](https://juejin.cn/post/7032895646822563870)
- caddy， 替代nginx

## front end

- 微前端，乾坤
- [当Ajax遇上302, 孽缘！](https://zhuanlan.zhihu.com/p/383548535)
- [Document Redirect 与 XHR Redirect区别](https://segmentfault.com/a/1190000042857203)
- [阮一峰 TypeScript教程](https://wangdoc.com/typescript/)
- [CSS 实现的各种效果，比如代码雨...](https://github.com/chokcoco/iCSS)

## big data

- [Amazon Glue集成Delta Lake构建事务型数据湖上的流式处理](https://aws.amazon.com/cn/blogs/china/amazon-glue-integrates-delta-lake-to-build-streaming-processing-on-transactional-data-lake/)
- [Delta Lake 是什么？](https://blog.csdn.net/Shockang/article/details/126804682)
- [大数据理论体系](https://blog.csdn.net/Shockang/article/details/115609804)
- [elk 中文指南](https://elkguide.elasticsearch.cn/)
- [Distributed System: DFS系列 -- NFS, AFS & HDFS（GFS）](https://blog.csdn.net/Firehotest/article/details/69220280)
- spark 性能提速
  - [spark arrow-datafusion](https://github.com/apache/arrow-datafusion)
  - [blaze](https://github.com/kwai/blaze)
  - [gluten](https://github.com/apache/incubator-gluten)

## AI

- [python实现反向传播](https://www.cnblogs.com/chenzhen0530/p/10830112.html)
- [文图生成模型架构](https://developer.aliyun.com/article/989767)
- [nlp 入门](https://github.com/PKU-TANGENT/nlp-tutorial?tab=readme-ov-file)
- [全连接，python实现](https://zhuanlan.zhihu.com/p/84153154)
- [大模型(llm)学习笔记（让天下没有难学的大模型）](https://github.com/liguodongiot/llm-action)
- [llm 实现：c/cuda实现gpt-2](https://github.com/karpathy/llm.c)
- [omniparse 将视频，图片等转换为markdown、json等](https://github.com/adithya-s-k/omniparse)
- 自然语言处理，分析日记
- [适合翻译的模型](https://linux.do/t/topic/400512)
- [pdf to markdown](https://github.com/VikParuchuri/marker)

## others

- [vim命令模式技巧，regex 速查表](https://www.cnblogs.com/outman123/p/12493186.html)
- 正则表达式，非捕获分组，\b
- [rpc, thrift协议，pd协议](https://juejin.cn/post/7077191540124647455)
  - sprintcloud 使用 http协议实现rpc
- https接入流程
- [nerd 3.0版本，图标码值位置整体调整](https://www.reddit.com/r/neovim/comments/13glwed/anyone_else_having_issues_with_nerdfonts/)
- websocket, wss协议
- [websocket socket.io状态码](https://stackoverflow.com/questions/24564877/what-do-these-numbers-mean-in-socket-io-payload)
- wasm(webassembly) wasi wapm 技术
- 免费frp服务，腾讯云不打算续了
  - 完全免费：https://freefrp.net/
  - 有付费节点：https://www.mossfrp.top/
- go，实现读取浏览器cookie: https://gist.github.com/dacort/bd6a5116224c594b14db
- [设计数据密集型应用](https://knowledgehive.github.io/ddia/#)
- p2p 打洞
  - frp p2p
    - [内网穿透笔记](https://www.xiaoyeshiyu.com/post/931.html#P2P)
    - [frp内网穿透之p2p实现远程桌面](https://blog.jlopt.com/2023/02/03/frp%E5%86%85%E7%BD%91%E7%A9%BF%E9%80%8F%E4%B9%8Bp2p%E5%AE%9E%E7%8E%B0%E8%BF%9C%E7%A8%8B%E6%A1%8C%E9%9D%A2/)
    - [【网络技术】P2P技术原理浅析](https://keenjin.github.io/2021/04/p2p/)
  - 基于libp2p的 go工具
    - [document: Hole Punching](https://docs.libp2p.io/concepts/nat/hole-punching/)
    - [github开源工具 p2ptunnel](https://github.com/chenjia404/p2ptunnel)
- [natmap ssh打洞](https://github.com/heiher/natmap/wiki/ssh)
- [Wireguard 原理](https://cshihong.github.io/2020/10/11/WireGuard%E5%9F%BA%E6%9C%AC%E5%8E%9F%E7%90%86/)
- [家庭网络无公网 IPv4 地址异地无感知远程访问家中服务的一个解决方案（NATMap+OpenWrt+Surgio+GitHub Action+Gist+Surge/Stash/Clash）](https://imciel.com/2023/04/07/connect-to-home-without-public-ipv4/)
- apache bench压测工具：ab
- ssh p2p
  - [ssh-p2p](https://github.com/nobonobo/ssh-p2p)
  - [sshx](https://github.com/suutaku/sshx)
- NAT
  - [NAT基本原理和大致流程](https://www.cnblogs.com/limejuiceOwO/p/14881608.html)
  - [NAT 类型初探](https://blog.mmf.moe/post/nat-types/)
  - [NAT穿透技术、穿透原理和方法详解](https://blog.csdn.net/lisemi/article/details/97672734)
  - [Headscale 搭建 P2P 内网穿透_](https://mritd.com/2022/10/19/use-headscale-to-build-a-p2p-network/)
  - [remote bind](https://github.com/rust-net/remote-bind)
- [百星 宿迁5元NAT](https://bxidc.xyz/)
- 内网穿透工具：
  - https://juejin.cn/post/7240288077868286010
  - https://blog.csdn.net/kjhlw/article/details/130278852
- [alist，支持多存储的文件列表程序](https://github.com/alist-org/alist)
- [tui项目](https://github.com/rothgar/awesome-tuis)
  - [一百多行，cpp实现的tui俄罗斯方块](https://github.com/taylorconor/tinytetris)
  - [六百多行，awk实现的第三人称射击游戏](https://github.com/TheMozg/awk-raycaster)
- [MIT 哲学，UNIX 哲学和 GUI 系统哲学，三种设计哲学间的交锋](https://www.cnblogs.com/lucelujiaming/p/9480869.html)
- [android 签名机制](https://github.com/jeanboydev/Android-ReadTheFuckingSourceCode/blob/master/article/android/basic/09_signature.md)
- [cdn 加速原理](https://cloud.tencent.com/developer/article/1690751)
- [neo4j图数据库]
  - [neo4j入门](https://zhuanlan.zhihu.com/p/88745411)
- 聊天室协议
  - MQTT (Message Queuing Telemetry Transport)
  - AMQP (Advanced Message Queuing Protocol)
  - DDS (Data Distribution Service)
  - XMPP (Extensible Messaging and Presence Protocol)
  - CoAP (Constrained Application Protocol)
  - WebSocket
  - **IRC**  (Internet Relay Chat)
  - RCS (Rich Communication Services)
- gpg
  - [理解和使用 GPG](https://www.rectcircle.cn/posts/understand-and-use-gpg/)
  - [GPG入门教程](https://www.ruanyifeng.com/blog/2013/07/gpg.html)
  - [archlinux wiki - GnuPG](https://wiki.archlinuxcn.org/wiki/GnuPG#)
- [yabai - a macOS Tiling WM.](https://github.com/koekeishiya/yabai)
  - [配置示例](https://github.com/spencerwooo/dotfiles-archive/blob/master/macOS/_yabairc)
- [protobuf](https://github.com/protocolbuffers/protobuf)
  - 由 Google 开发的二进制序列化格式和相关的技术，它用于高效地序列化和反序列化结构化数据，通常用于网络通信、数据存储等场景
- [Iosevka字体](https://github.com/be5invis/Iosevka)
- [所有人都能懂的正规方程](https://blog.csdn.net/weixin_41075215/article/details/104880912)
  - 线性代数，有时间可以复习下
- [网易云音乐ncm格式分析以及ncm与mp3格式转换](https://www.cnblogs.com/cyx-b/p/13443003.html)
- [CloudFlare Tunnel 免费内网穿透的简明教程](https://sspai.com/post/79278)
- anki 记忆卡，fsrs 算法
- [物联网 Arduino 入门](http://www.taichi-maker.com/homepage/arduino-tutorial-index/)
- [声卡采样率](https://zhuanlan.zhihu.com/p/624168796)
- [review doom3](https://www.youtube.com/watch?v=C_ymp74yobE)
  - 7:55: 使用一个神奇的数字加速平方根的计算
    - <https://linux.cn/article-1513-1.html>
    - <https://en.wikipedia.org/wiki/Fast_inverse_square_root>
- 生成明暗主题一分为二的图表: <https://emacs-china.org/t/topic/20216/33>
- 暗色和亮色主题讨论：<https://ux.stackexchange.com/questions/53264/dark-or-white-color-theme-is-better-for-the-eyes>
- dns 解析
  - [就是要你懂DNS--一文搞懂域名解析相关问题](https://plantegg.github.io/2019/06/09/一文搞懂域名解析相关问题/)
  - [systemd-resolv](https://www.keepnight.com/archives/1772/)

# wiki

一些乱七八糟的概念

- [SDF Public Access Unix System](https://en.wikipedia.org/wiki/SDF_Public_Access_Unix_System)
  - [SDF wiki](https://wiki.sdf.org/doku.php?id=start)
  - [anonradio](https://anonradio.net/)
- [平方根倒数速算法, 0x5f3759df](https://zh.wikipedia.org/wiki/平方根倒数速算法)
    - [doom-3](https://github.com/id-Software/DOOM-3/blob/a9c49da5afb18201d31e3f0a429a037e56ce2b9a/neo/idlib/math/Math.h#L246)
    - [quake-3](https://github.com/id-Software/Quake-III-Arena/blob/dbe4ddb10315479fc00086f08e25d968b4b43c49/code/game/q_math.c#L561)

# current dotfiles

- [x] nvim
  - bigfile
    - [x] treesitter default disable, enable through event
      - [x] treesitter should be disable by BufReaPre
    - ~[ ] abstract as feature~
    - [x] autocmd support `once` param, create BufReadPost event in BufReaPre callback
    - [x] skip nobuflisted buffer
    - [ ] big file trim space too slow, default disable trim
    - [x] java single file run
    - [ ] high cpu usage and slow when has indented code block in markdown file
  - md-section-number
    - [ ] vimscript can't hold lazy.nvim's VeryLazy event, create command through lua rather than vimscript
  - lsp
    - [x] add nix language server support
    - [x] select lsp by input
    - [ ] lsp rename file support: [nvim-lsp-file-operations](https://github.com/antosha417/nvim-lsp-file-operations)
  - plugins
    - compile mode
      - [x] autocomplete (update plugin)
      - [ ] support max line
    - [x] replace netrw: [nvim dired](https://github.com/X3eRo0/dired.nvim)
      - [x] fork and add file type support: socket, pipe, byte service, char service
      - [x] don't change cwd
      - [ ] fix: python venv cursor position
      - [ ] go_up function, history
      - [ ] nvim_set_current_dir
      - [ ] nvim-dired slower than emacs-dired when too many files (like /nix/store), why?
        - because every file need read_link or other additional operations?
    - [x] when using vim-matchup, nvim-cmp can't complete 'for' in bashscript file
    - [ ] markdown highlight strickthrough
      - [Any way to enable true markdown strikethrough](https://www.reddit.com/r/vim/comments/g631im/any_way_to_enable_true_markdown_strikethrough/)
    - [ ] try replace nvim-spectre with grug-far
    - [ ] ssr.nvim: Treesitter based structural search and replace plugin for Neovim.
    - [ ] use folke/flash.nvim replace hop.nvim? no....
    - [ ] fcitx.vim
  - treesitter
    - [treesitter-textobjects](https://github.com/nvim-treesitter/nvim-treesitter-textobjects)
  - tools
    - range diff

      ```
      text1
      =============
      text2
      ```
- bash
  - [x] history drop dup
  - [ ] parse markdown, repo collect
- tmux
  - [ ] color theme, color as variable
    - [samoshkin's tmux config](https://github.com/samoshkin/tmux-config/blob/master/tmux/tmux.conf)
  - [x] iterm2 osc, mac need set clipboard

    ```tmux
    # set -as terminal-overrides ',tmux*:Ms=\\E]52;%p1%s;%p2%s\\007'
    # set -as terminal-overrides ',screen*:Ms=\\E]52;%p1%s;%p2%s\\007'
    set -s set-clipboard on
    ```
  - terminfo support delete_line
- nixos
  - [x] why proxy environment doesn't passed to curl under root
    - common user, nixos-rebuild can get the proxy env from nix-daemon
    - `sudo -E`
  - [x] aria2
  - [ ] firewall?
  - ~[ ] urxvt doesn't support true color, show some error info when start tmux~
    - use st
  - [x] switch to firefox
    - fxxk chrome, fxxk gemini
  - dwm
    - [x] mod key: mod4 for win
    - [x] dwm mod-m, toggle zoom
    - read dwm source code and patch it
      - [x] gap between window
      - [x] more smooth config <https://www.reddit.com/r/suckless/comments/pq7ruc/dwm_lagging_when_dragging_and_resizing_floatingg>
    - ~[ ] toggle monsole shortcut~
    - ~[ ] taglayouts~
    - [x] clickable icon
    - [x] st-float, float mode run st: [Opening certain terminal applications in floating mode?](https://www.reddit.com/r/suckless/comments/mqd4ol/opening_certain_terminal_applications_in_floating/)
  - st
    - [ ] can't zoom chinese
  - fhs
    - [x] nix-ld to use mason.nvim
  - picom
    - [x] glx(opengl interface for mesa(opengl implement on linux)) or xrender
    - ~[ ] adjust animation, blur ... config~
    - [x] picom drop my refresh rate !!!! (test under ufoetest)
      - glx and vsync (At the expense of some gpu and memory)
  - nixpak, or flatpak to control firefox, qq and other gui apps's auth
    - [ ] firefox config, and chrome
    - [ ] qq and wechat
  - [x] check memory usage
  - copy manager
    - [x] copyq, with dmenu
  - check lantian's minimal-components config
    - [x] fstrim
    - [x] irqbalance
  - dwm bar
    - [x] volume contorl
    - [ ] brightness contorl
    - [x] wifi
    - [x] bluetooth
  - disk
    - [x] nix add win_d disk mount config
    - [x] use a steam game disk for win_10 and linux：
      - <https://github.com/ValveSoftware/Proton/wiki/Using-a-NTFS-disk-with-Linux-and-Windows>
      - <https://blog.l3zc.com/2024/04/mount-ntfs-drive-as-steam-lib-on-linux/>
    - [x] add gvfs support, thunar auto mount disk
      - <https://discourse.nixos.org/t/permission-error-when-trying-to-mount-local-disk-in-thunar/39474>
      - <https://nixos.wiki/wiki/Thunar>
  - secret
    - [ ] agenix
    - [ ] [sops-nix](https://github.com/Mic92/sops-nix)
      - [GPG & SSH key pairs management](https://discourse.nixos.org/t/gpg-ssh-key-pairs-management/28604)
  - later
    - [ ] lightdm-slick-gretter
    - [ ] cursor theme
    - [ ] grub2 theme
    - [x] clash-verge autostart
    - [ ] volume pop! when reboot, poweroff is ok
    - [x] clean duplicate history command when startup
    - when enable nvidia's office driver
      - test fps: <https://www.testufo.com/>
      - ~[ ] firefox in external monitor refresh rate low?~
        - caused by picom
      - ~[ ] prime offload mode, can't correct `xrandr --scale`~
        - switch to sync mode when use external monitor
    - [x] tlp make lan can't use, and make wifi default soft blocked
      - don't disable wake-on-lan ?
      - soft block is control by <https://linrunner.de/tlp/settings/radio.html>
    - [ ] ~repalce interaction-tool with keyd, switch ctrl and caps~
      - just use x11 xkb options
    - [x] R9000K speaker sound, upgrade or downgrade linux kernel, check speaker sound
        - down to 6.1 (longterm): [Y9000K issue](https://bbs.archlinux.org/viewtopic.php?id=291507)
        - up to 6.7 or down to 6.5 (middle version): [Family HD Audio Controller issue](https://bbs.archlinux.org/viewtopic.php?id=291324)
        - [x] up to 6.12 (stable)
    - [x] config windows's time config: <https://sspai.com/post/55983>
    - [x] services.logind.extraConfig, config suspend event to ignore
    - [x] use external monitor when boot
      - sync mode + xrandr
    - gpu issue (or maybe just gpu driver problem)
      > [x] finally, fix the issue by use dgpu mode, disable igpu
      - nvidia-smi itself will turn on gpu, check if the GPU is actually powered on or suspended: `cat /proc/driver/nvidia/gpus/(PCI BUS ID)/power`
        > <https://discourse.nixos.org/t/power-managment-with-nvidia-gpu/27947/18>
      - [x] **high gpu usage when idle**: fixed by config monitor in `display-setup-script`
        - <https://forums.linuxmint.com/viewtopic.php?t=422079>
        - may be just a 'feature' of nvidia monitoring, because in different power?
        - high power draw: <https://forums.developer.nvidia.com/t/bug-report-idle-power-draw-is-astronomical-with-rtx-3090/155632/78?page=3>
        - <https://wiki.archlinux.org/title/PRIME#PRIME_render_offload>

        ```
        https://forums.developer.nvidia.com/t/bug-id-4341092-40-permanent-gpu-usage-but-all-gpu-processes-are-idle-ubuntu-23-10/270044
        https://askubuntu.com/questions/1451808/use-integrated-gpu-for-desktop-display-and-leave-dedicated-gpu-for-computing-tas
        linux laptop dp use Integrated graphics
        # nvidia-settings -q CurrentMetaMode
        disable ForceFullCompositionPipeline
        ```
      - [x] higher gpu usage after xrandr
        - config xrandr in lightdm setup script
      - [x] higher gpu clock speed in linux when play video
        - may be nvidia driver bug:
          - <https://forums.developer.nvidia.com/t/nvidia-driver-has-a-habit-of-keeping-my-gpu-at-the-highest-performance-level/118113/18>
          - <https://forums.developer.nvidia.com/t/if-you-have-gpu-clock-boost-problems-please-try-gl-experimentalperfstrategy-1/71762>
        - may be need to disable Hardware Acceleration, but also may be not a problem: <https://forums.tomshardware.com/threads/why-does-my-gpu-reach-max-clock-speed-when-just-watching-youtube.1721910/#post-12164173>
        - ~change: nvidia-settings -> GPU 0 -> PowerMizer -> preferred mode~
        - [x] can config nvidia clock rate by `nvidia-smi` command
      - try:
        - ~maybe can adjust dpi and use kernel param to select monitor, without using xrandr ?~
        - ~disable force composition pipeline ? <https://forums.developer.nvidia.com/t/bug-id-4341092-40-permanent-gpu-usage-but-all-gpu-processes-are-idle-ubuntu-23-10/270044/5>~
        - ~update driver to nvidia_X11 or nvidia_X11_beta ?~
        - ~try disable picom ?~
        - ~xserver.videoDriver = ["amdgpu"]~
    - ~auto check external monitor~
      - autorandr, can switche xrandr profiles automatically when it detects that the conditions match.
      - show grub on external monitory:
        - disable mlaptop monitor at startup(boot): https://bbs.archlinux.org/viewtopic.php?id=150413
    - [x] mime type
      - st as default terminal emulator for terminal desktop item
        - <https://unix.stackexchange.com/questions/177976/set-default-xdg-open-application-to-terminal-program>
        - use `xdg-terminal-exec`
      - home-manager mime config for systemd thunar.service, options:
        - add `XDG_XXXXXXX` env for thunar.service
          - thunar service thread has more path in XDG_XXXXXXXXX.....
          - check again?
            - ` diff --side-by-side  <( cat /proc/$(pidof picom)/environ | tr '\0' '\n' | sort ) <( env | sort )`
          - add env, <https://wiki.archlinux.org/title/Systemd/User>
        - ~config mime in system-wide, instead of using home-manager~
          - no effect
        - [x] disable thunar.service

