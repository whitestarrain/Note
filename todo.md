
> todo 和一些想之后看的资料

# 优先级较高项

- [x] [手把手教你构建 C 语言编译器](https://lotabout.me/2015/write-a-C-interpreter-0/)
  - [x] 编译原理复习

- [ ] emacs, elisp
  - common ansi lisp
  - [Emacs Lisp 简明教程](https://smacs.github.io/elisp/)

- [ ] effective cpp
  - 主要是为了刷题，同时复习一下cpp

- [ ] [常用 bash 命令](https://github.com/jlevy/the-art-of-command-line/blob/master/README-zh.md)
  - star 不少，可以过一遍，看看有没有自己眼生的，总结到 linux.md 或者 bash.md 中

- [ ] 迁移 nixos
  - 一段时间不 `pacman -Syu`，就会有些包因为版本问题没办法使用
  - 看看迁移成本，以及看看，以及 nix 管理方式下，在想尝试新东西的时候，会不会很繁琐
    - ok, nix shell, 完美
  - 流程：
    - [x] nix 基础
    - [x] 系统盘
    - [ ] 初始化nix配置
    - [ ] 数据备份
    - [ ] 系统安装

- [ ] NAS 搭建 && 数据备份
  - 参考：[ntzyz, nas 和数据备份](https://ntzyz.space/zh-cn/post/nas-and-backup/)
  - 主要是因为网易云音乐相关，想搞个公共存储
  - [ ] 网易云音乐解密+备份+同步 脚本

- [ ] 使用 make 安装 dotfiles
  - 参考： https://github.com/jessfraz/dotfiles
  - 主要是为了解决 home 下软链目录还是软链文件的问题,stow 会自动 merge ，这会导致目录结构不同时，行为不一致

- [ ] csapp
  - [csapp labs](http://csapp.cs.cmu.edu/3e/labs.html)
  - [lab 资料](https://hansimov.gitbook.io/csapp/labs/labs-overview)

- ucore

- [ ] 博客
  - [ ] 完工遗留的 blog 草稿
  - [ ] 博客里面，写上今年规划，以及多写一些博客吧

- 深度学习进阶，自然语言处理
  - 主要是用于整理日记，进行总结

- [ ] [emacs GTD 日程管理](https://www.cnblogs.com/yangruiGB2312/p/9101838.html)

- [ ] [DN42 实验网络介绍及注册教程](https://lantian.pub/article/modify-website/dn42-experimental-network-2020.lantian/)

# 小任务

- [ ] traceroute 实现
  - [优雅地在 Traceroute 里膜 拜大佬](https://lantian.pub/article/creations/traceroute-chain.lantian/)
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
- [ ] c 实现文件上传下载
- [ ] c 实现https
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
> - [编程指北](https://csguide.cn/)

- [MIT - 6.824 分布式课程](https://pdos.csail.mit.edu/6.824/)
- [MIT 6.828 JOS与NJU ICS NEMU源码精读](https://knowledgehive.github.io/6.828/#)
- [game101 GAMES101: 现代计算机图形学入门](https://sites.cs.ucsb.edu/~lingqi/teaching/games101.html)
- lc-3
  - [主页 Introduction to Computing Systems](https://highered.mheducation.com/sites/0072467509/student_view0/)
  - [模拟器 LC 3 Simulator Windows Version 3.01 (385.0K)](https://highered.mheducation.com/sites/dl/free/0072467509/104652/LC301.exe)
  - [指令手册 AppendixA.pdf](https://highered.mheducation.com/sites/dl/free/0072467509/104691/pat67509_appa.pdf)
- [编译原理](https://csguide.cn/roadmap/basic/compiler.html)
- [计算机导论：CS 50](https://cs50.harvard.edu/college/2021/spring)
  - [b站视频](https://www.bilibili.com/video/BV1Rb411378V)
- 清华大学 ucore OS
  - [ucore实验指导书](https://nankai.gitbook.io/ucore-os-on-risc-v64/)
- MIT 6.828: Operating System Engineering
- [SysY 语言编译到 RISC-V-北大编译实践在线文档](https://pku-minic.github.io/online-doc/#/)

# 神级程序员 博客

- [bellard](https://bellard.org/)
- [Daniel Holden](https://www.theorangeduck.com/)
- [Thomas E. Dickey](https://invisible-island.net/)

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
- lisp编辑辅助插件：nvim([parpar](https://github.com/dundalek/parpar.nvim)) = emacs(Parinfer + Paredit)
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
- [专业emacs入门](https://zhuanlan.zhihu.com/p/385214753)
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

### bash/shell

- bash的case与shift:
  ```bash
  while [ $# -gt 0 ]
  do
    case $1 in
      -h|--help) print_usage; exit;;
      # For options with required arguments, an additional shift is needed.
      -i|--ip) IP="$2" ; shift;;
      -p|--port) PORT="$2" ; shift;;
      -f|--file) FILE="$2" ; shift;;
      -t|--type) TYPE="$2" ; shift;;
      (--) shift; break;;
      (-*) echo "$0: invalid option $1" 1>&2; exit 1;;
      (*) break;;
    esac
    shift
  done
  ```

- tar『-C』说明
  ```bash
  # 解压后，会得到output/${APP}/....，这种方式会保留文件夹层级
  tar -czf output/${APP}.tar.gz output/${APP}
  # -C 相当于cd到指定目录再压缩，后面跟着一个"."，表示压缩 "output/${APP}" 目录
  tar -czf output/${APP}.tar.gz -C output/${APP} .
  ```

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
- [linux运维，包括iptables](https://www.zsythink.net/)
- [linux性能分析](https://mp.weixin.qq.com/s/i55jZoQ1DbkYCedwg5RPLg)
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

### linux/c

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
  - [我的内存呢？Linux MemAvailable 如何计算](https://lotabout.me/2021/Linux-Available-Memory/)
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
- [archlinux配置文件，包括picom混色器。该作者的一些tmux等配置文件也可以参考下，比如tmux插件。](https://github.com/shendypratamaa/.arch)
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
- x window / x11 / xorg
  - https://www.zhihu.com/question/321725817
- dwm
  - [dwm arch cn wiki](https://wiki.archlinuxcn.org/wiki/Dwm)
  - [dwm配置](https://zocoxx.com/archlinux-dwm-incomplete-guide.html)
  - [ArchLinux+DWM不完全指北](https://zocoxx.com/archlinux-dwm-incomplete-guide.html)
- [picom](https://wiki.archlinux.org/title/picom)
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
  - 没有提供源码的专有软件，会通过patchelf ld-linux.so 位置到 /nix/store/xxx-glibc-xxx/lib. [wiki](https://nixos.wiki/wiki/Packaging/Binaries)
- [使用 nix 包管理器解决 glibc 兼容问题](https://v2ex.com/t/892346)
- [nixos wiki](https://nixos.wiki/wiki/Main_Page)
- [nix 学习经验：安装和打包](https://linux.cn/article-16332-1.html)
- [Nix Reference Manual](https://nix.dev/manual/nix/2.18/introduction)
- [nix](https://lazamar.co.uk/nix-versions/)
- [nix安装](https://medium.com/@realLanta/一个配置文件就能在各个电脑装一模一样的系统和软件-保姆级教你轻松掌握nixos-7f026b539242)
- case记录:
  - 因为没有使用FHS，所以一些工具一起使用的时候可能有问题， 比如
    - vscode的terminal中使用gdb，需要使用 vscode FHS environment: [Getting gdb to work in vscode](https://www.reddit.com/r/NixOS/comments/1aep3c5/getting_gdb_to_work_in_vscode/)

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
- [关于加密、证书的那些事](https://www.cnblogs.com/sewain/p/14250884.html#%E6%95%B0%E6%8D%AE%E5%8A%A0%E5%AF%86)
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


