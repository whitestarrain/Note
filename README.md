> **我对自己的大脑很有信心，它早晚会把我学的东西忘得一干二净，所以就有了这个仓库**
>
> 记笔记是为了以后查起来方便。看过的东西，就算没有整理，基本上也都会搞一下目录和参考资料
>
> 里面的笔记不全是我自己写的，时间有限，既然有现成的笔记，拿过来改改整理一下也是一种不错的方式。(当然会注明出处)
>
> 本人也在学习过程中，欢迎参与此仓库的完善与讨论

# 目录索引(未整理完)

> 我个人比较喜欢**尽可能得把所有笔记整理在一个文件上面**，因此有些笔记文件可能很大，加载可能会慢一些。

- **基础**
  - [计算机网络基础问题](./base/computer_network_question.md)
  - [计算机网络知识体系与知识点](./base/computer_network.md)
    > 没开始整理，只整理了大致结构。具体知识结构整理到了思维导图里面，有时间再整理具体内容
  - [操作系统基础知识点与问题](./base/os_question.md)
  - [操作系统基础知识体系与知识点](./base/os.md)
    > 没开始整理。看上面那个也行。之前打算抽时间重新梳理一下的。不过看上面那个也行
  - [设计模式](./base/design_pattern.md)
    > 这东西感觉得经常看啊
  - [linux常用命令，工具与shell](./base/linux.md)
    > 抽时间完善中
  - [编程思想](./base/programming_logic.md)
    > 鬼知道我啥时候整的这些东西。记得只是想抽时间把大学里学的一些概念性的东西整理一下来着。一个字，杂
  - [正则表达式](./base/regex.md)
    > 网上太多教程了，这里就打算记录一下比较生的用法
- **大数据(笔记没时间更完，看一下[资料](./big_data/资料)吧)** 
  - [hadoop(hdfs,MR,HBase),hive](./big_data/hadoop.md)
    > 好像是以前跟着视频边学边记的。更到HBase后就没时间更了。
  - [storm](./big_data/storm.md)
    > 记了一些基础
  - [scala基本语法](./big_data/scala.md)
    > 学spark必学
  - [spark](./big_data/spark.md)
    > spark笔记，也是更到一半就没时间更了
  - [flink](./big_data/flink.md)
    > 基本上没记多少笔记
  - [elasticsearch](./big_data/elasticsearch.md)
    > 边学边从网上抄的笔记
- **中间件** 
  - [lvs,keepalive,](./Middleware/lvs+keepalive.md)
  - [nginx](./Middleware/nginx.md)
  - [RabbitMQ](./Middleware/RabbitMQ.md)
  - [kafka](./Middleware/kafka.md)
- **容器**
  - [docker](./container/docker.md)
    > 占位
  - [k8s](./container/k8s.md)
    > 占位
- **前端** 
- **python**
  - [pyqt5](./python/pyqt5.md)
    > 做一些图像处理的界面还不错
  - [python爬虫和MongoDB](./python/python爬虫.md)
- **Java** 
- **分布式** 
- **读书笔记** 
- **人工智能** (深度学习部分没怎么整过)
  - [numpy,pandas速查](./AI/np,pd_doc.md)
  - [机器学习基础笔记](./AI/machine_learning.md)
  - [深度学习框架](./AI/deeplearning_frame.md)
    > pytorch,tensorflow,caffe等。基本不会自己整理
  - [深度学习笔记目录](./AI/deep_learning.md)
    > 没时间整理，推荐找找吴恩达的视频笔记看
  - [opencv](./AI/opencv.md)
    > 官方教程整理的文档，英文。整理中
  - [深度学习工具](./AI/tools.md)
    > 记录一些常用工具，如wandb。还没开始整理
  - [模型部署](./AI/module_deploy.md)
    > 模型轻量化，部署相关

- **C/C++**
- **[速查表](./cheatsheet.md)** 
- **其他**

# 思维导图

查看思维导图：[思维导图](https://whitestarrain.github.io/Note/YourBrain.html)

> **注意**

- ✨使用[markmap](https://github.com/gera2ld/markmap)渲染
- 🎉有些节点点开后会特别大
  - 点来点去太过麻烦，所以基本只自动折叠了一级
  - 但对于一些过于具体的节点，没有忍住把答案写上去的节点也设置了自动折叠
- ✒**仍旧在更新中**
  - 有些预计整理的笔记，思维导图上有，但是没有笔记文件
  - 有些已经整理的笔记，有笔记文件，但是思维导图上没有
- 🎈思维导图上的链接
  - 都是一些不错的博客文章。大多针对一些知识点。
  - **之后会替换成本项目中的文件链接**，将这些博客写到参考资料中去
- 💣**请每次查看时使用ctrl+F5更新，避免浏览器缓存导致思维导图过旧**

# 笔记风格

- 叙述风格：
  - 尽量避免写大段说明
    - 基本上都会把知识点以列表或者表格的形式展示
    - 并且标注每一个知识点的划分，比如`原因`,`作用`,`原理`等等。
  - 列表下面会有引用作为补充说明

- 段落风格：
  - 尽量控制在五级标题以内。
  - 标题内内容的划分主要有两种
    - `---`:以前经常用
    - `> **内容说明**`:现在比较常用，有时间会把`---`都改成这种风格
      > 注意，引用不加粗是补充说明。引用加粗是段内划分

- 待做项说明：
  - <del>待做项会以`待`字为开头。放在一行。比如`待整理`，`待补充`</del>
    > 以后还是改成TODO吧，`folke/todo-comments.nvim`挺好用的
  - 通常会以todo列表的形式罗列一些资料在下面，注明待完善以及已经整理完成的资料

# 历史仓库

因为维护三个笔记仓库太麻烦了，于是将三个仓库整合为一个仓库，以后将集中于本仓库的更新。

三个历史仓库分别为。

- [java学习笔记](https://github.com/whitestarrain/java_study_note)
- [大数据与深度学习笔记](https://github.com/whitestarrain/big_data)
- [python学习笔记](https://github.com/whitestarrain/python_learn)

# 目录调整

之前目录调整用来`git mv`图片的代码。因为`git mv`不支持正则表达式匹配，所以只能使用awk了。

```awk
awk 'BEGIN{
      target = "../javaFrame/image"  # 图片移动位置
      print target
    }
    {
      a = match($0,/!\[.*\](.*)/);
      if(a!=0){
        str = substr($0,a);
        i = index(str,"(");
        j = index(str,")");
        image_path = substr(str,i+1,j-i-1);
        command = sprintf("git mv %s %s",image_path,target);
        print command
        system(command)
      }
    }' Spring.md  # 移动图片所关联的md文件
```

# 待做项

> 完成项不保证不再更新

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
  - [ ] 设计模式
  - [ ] 编译原理
  - [ ] zookeeper完善
  - [ ] java并发整理
  - [ ] RabbitMQ整理
  - [ ] 鉴权方式整理
  - [ ] CORS跨域完善+整理
  - [ ] linux常用命令&工具系统整理
  - [ ] shell编程系统整理
  - [x] 鉴权认证
  - [x] 分布式缓存算法整理
  - [x] JVM字节码与类的加载
  - [ ] JVM性能监控和调优
  - [ ] 各种加密算法
  - [ ] regex系统整理
  - [ ] html,css,js 历史笔记+资料整理
  - [ ] latex 基本语法（vim-latex&texlab环境搭建时有练习，有时间再整）
  - [ ] nginx&lvs 整理(./Middleware/load_balance.md 中笔记梳理)
- 待继续学习
  - [ ] Undertow
  - [ ] Mysql XA
  - [x] Mocikto框架
  - [ ] JOOQ框架
  - [ ] jersey框架
  - [ ] SpringBatch
  - [ ] SpringData
  - [ ] Cassandra
  - [ ] xdb
  - [ ] elasticsearch
  - [ ] ELK
  - [ ] TensorRT
  - [ ] React <!-- - 个人博客框架 -->
    - 练手项目：浏览器主页私人订制
  - [ ] Vue
    - **练手项目：博客评论系统前端**
  - 常用JVM
- 阅读书目
  > 个人习惯使用 知之阅读 做一些读书笔记。markdown版笔记仅抽空整理
  - [x] 代码整洁之道
  - [ ] **重构：改善既有代码设计**
  - [ ] **程序设计实践**
  - [ ] Redis设计与实现
  - [ ] Operating Systems:Three Easy Pieces
  - [ ] Linux C 编程一站式学习
  - [ ] 冒号课堂
- 大数据笔记整理完善（抽时间零碎）
  - [x] hadoop+hive+hbase
  - [ ] Spark
  - [ ] Flink
- 短期小任务
  - [ ] Gossip 协议
  - [x] Raft 协议
  - [ ] SPEL
  - [ ] 阿里巴巴数据库内核月报目录爬虫
- lang
  - [ ] C/C++
  - [ ] go
    - [ ] **练手项目：博客评论系统后端，针对业务逻辑编写测试**
  - [x] lua
    - [ ] 实现markdown标题编号nvim插件
  - [ ] rust
  - [ ] TypeScript
    - [x] 基础
    - [ ] 深入
    - [ ] 命令行工具开发
    - [ ] **练手项目：kakuyomu 小说终端阅读器(包含登录功能)**
- 机器学习&深度学习
  - [ ] opencv 系统整理
  - [ ] 深度学习笔记系统整理（暂时抽不出时间）
- 其他
  - [ ] JNI（c++之后）
  - [x] nvim配置模块化(迁移到[dotfiles](https://github.com/whitestarrain/dotfiles))

<!--
- [ ] SpringBoot原理
- [ ] JVM字节码
- [ ] JVM调优

cookie和session攻击
25匹马赛马

数据库 分区分库分表
-->
