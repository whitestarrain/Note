> 本人也在学习过程中，欢迎参与此仓库的完善与讨论

# 目录索引

> 我个人比较喜欢**尽可能得把所有笔记整理在一个文件上面**，因此有些笔记文件可能很大，加载可能会慢一些。

> 目录索引待补充，根据文件夹名称应该可以猜个差不多

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
  - 待做项会以`待`字为开头。放在一行。比如`待整理`，`待补充`
  - 通常会罗列一些资料在下面，待整理以及待完善需要的资料后面也会指明`待整理`，`待完善等`


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
      target = "../javaFrame/image"
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
    }' Spring.md
```

# 待做项

- 长期
  - [ ] java编程思想 整理
  - [ ] 计算机网络完善
  - [ ] 操作系统完善
- 学习深入+笔记完善
  - [ ] 微服务架构
  - [ ] 分布式系统相关
  - [ ] 数据库架构
- 待完善/整理
  - [ ] 设计模式
  - [ ] zookeeper完善
  - [ ] java并发整理
  - [ ] RabbitMQ整理
  - [ ] 鉴权方式整理
  - [ ] CORS跨域完善+整理
  - [x] 鉴权认证
  - [x] 分布式缓存算法整理
  - [x] JVM字节码与类的加载
  - [ ] JVM性能监控和调优（进行中）
  - [ ] 各种加密算法
- 待继续学习
  - [ ] Undertow
  - [ ] Mysql XA
  - [ ] Mocikto框架
  - [ ] JOOQ框架
  - [ ] jersey框架
  - [ ] SpringBatch
  - [ ] Cassandra
  - [ ] xdb
  - [ ] elasticsearch
  - [ ] ELK
- 阅读书目
  > 个人习惯使用 知之阅读 做一些读书笔记。markdown版笔记仅抽空整理
  - [x] 代码整洁之道
  - [ ] **重构：改善既有代码设计** （进行中）
  - [ ] **程序设计实践**
  - [ ] Redis设计与实现
  - [ ] Operating Systems:Three Easy Pieces
- 大数据笔记整理完善
  - [ ] Spark
  - [ ] Flink
- 短期小任务
  - [ ] Gossip 协议
  - [ ] SPEL
- 其他
  - [ ] go语言入门
  - [ ] C++
  - [ ] JNI

<!--
- [ ] SpringBoot原理
- [ ] JVM字节码
- [ ] JVM调优

cookie和session攻击
25匹马赛马

数据库 分区分库分表
-->
