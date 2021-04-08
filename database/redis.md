# 1. NoSQL概述

## 1.1. 数据3V与需求3高

- 大数据时代的3V：
  - 海量Volume
  - 多样Variety
  - 实时Velocity
- 互联网需求的3高：
  - 高并发
  - 高可括
    - 纵向扩展：提高一台机器的性能
    - 横向扩展：搭建集群
  - 高性能

## 1.2. 互联网架构演变

- 单机MySQL
  <details>
  <summary style="color:red;">说明</summary>

  在90年代，一个网站的访问量一般都不大，用单个数据库完全可以轻松应付。
  在那个时候，更多的都是静态网页，动态交互类型的网站不多。

  ![redis-1](./image/redis-1.png)
  > DAL dal是数据访问层的英文缩写，即为数据访问层（Data Access Layer）

  - 上述架构下，数据存储的瓶颈有三方面
    - 数据量的总大小一个机器放不下时
    - 数据的索引(B+ Tree)一个机器的内存放不下时
    - 访问量(读写混合)一个实例不能承受

  </details>

- Memcached(缓存)+MySQL+垂直拆分
  <details>
  <summary style="color:red;">说明</summary>

  后来，随着访问量的上升，几乎大部分使用MySQL架构的网站在数据库上都开始出现了性能问题，web程序不再仅仅专注在功能上，同时也在追求性能。
  程序员们开始大量的使用缓存技术来缓解数据库的压力，优化数据库的结构和索引。
  开始比较流行的是通过文件缓存来缓解数据库压力，但是当访问量继续增大的时候，多台web机器通过文件缓存不能共享，
  大量的小文件缓存也带了了比较高的IO压力。在这个时候，Memcached就自然的成为一个非常时尚的技术产品。

  ![redis-2](./image/redis-2.png)
  </details>

- Mysql主从读写分离
  <details>
  <summary style="color:red;">说明</summary>

  由于数据库的写入压力增加，Memcached 只能缓解数据库的读取压力。读写集中在一个数据库上让数据库不堪重负，
  大部分网站开始使用主从复制技术来达到读写分离，以提高读写性能和读库的可扩展性。Mysql的master-slave模 式成为这个时候的网站标配了。

  ![redis-3](./image/redis-3.png)
  </details>
- 分表分库+水平拆分+mysql集群
  <details>
  <summary style="color:red;">说明</summary>

  在Memcached的高速缓存，MySQL的主从复制， 读写分离的基础之上，这时MySQL主库的写压力开始出现瓶颈，而数据量的持续猛增，由于MyISAM使用表锁，在高并发下会出现严重的锁问题，大量的高并发MySQL应用开始使用InnoDB引擎代替MyISAM。

  同时，开始流行使用分表分库来缓解写压力和数据增长的扩展问题。这个时候，分表分库成了一个热门技术，是面试的热门问题也是业界讨论的热门技术问题。也就在这个时候，MySQL推出了还不太稳定的表分区，这也给技术实力一般的公司带来了希望。虽然MySQL推出了MySQL Cluster集群，但性能也不能很好满足互联网的要求，只是在高可靠性上提供了非常大的保证。

  ![redis-4](./image/redis-4.png)
  </details>

- 如今
  <details>
  <summary style="color:red;">说明</summary>

  - MySQL的扩展性瓶颈
    - MySQL数据库也经常存储一些大文本字段，导致数据库表非常的大，在做数据库恢复的时候就导致非常的慢，不容易快速恢复数据库。
    - 比如1000万4KB大小的文本就接近40GB的大小， 如果能把这些数据从MySQL省去，MySQL将变得非常的小。
    - 关系数据库很强大，但是它并不能很好的应付所有的应用场景。
    - MySQL的扩展性差(需要复杂的技术来实现)，大数据下IO压力大，表结构更改困难，正是当前使用MySOL的开发人员面临的问题。

  ![redis-5](./image/redis-5.png)
  </details>

## 1.3. Alibaba架构演变


## 1.4. NOSQL概念说明

### 1.4.1. 简要说明

- redis时一款高性能的NOSQL系列的非关系型数据库
- NoSQL(NoSQL = Not Only SQL)，意即“不仅仅是SQL”， **泛指非关系型的数据库**。
- 随着互联网web2.0网站的兴起，传统的关系数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。
- NoSQL 数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题，包括超大规模数据的存储。
- (例如谷歌或Facebook每天为他们的用户收集万亿比特的数据)。
- **这些类型的数据存储不需要固定的模式，无需多余操作就可以横向扩展**。

### 1.4.2. NoSQL特点

- 易扩展
  - NoSQL数据库种类繁多，但是一个共同的特点都是去掉关系数据库的关系型特性。数据之间无关系，这样就非常容易扩展。
  - 也无形之间，在架构的层面上带来了可扩展的能力。
- 大数据量高性能
  - NoSQL数据库都具有非常高的读写性能，尤其在大数据量下，同样表现优秀。
  - 这得益于它的无关系性，数据库的结构简单。
  - 一般MySQL使用Query Cache，每次表的更新Cache就失效，是一种大粒度的Cache，在针对web2.0的交互频繁的应用，Cache性能不高。
  - 而NoSQL的Cache是记录级的，是一种细粒度的Cache，所以NoSQL在这个层面上来说就要性能高很多了。
- 多样灵活的数据模型
  - NoSQL无需事先为要存储的数据建立字段，随时可以存储自定义的数据格式。
  - 而在关系数据库里，增删字段是一件非常麻烦的事情。如果是非常大数据量的表，增加字段简直就是一个噩梦。

### 1.4.3. 关系型数据库比较


- nosql
  - 优点：
    - 1）成本：nosql数据库简单易部署，基本都是开源软件，不需要像使用oracle那样花费大量成本购买使用，相比关系型数据库价格便宜。
    - 2）查询速度：nosql数据库将数据存储于缓存之中，关系型数据库将数据存储在硬盘中，自然查询速度远不及nosql数据库。
    - 3）存储数据的格式：nosql的存储格式是key,value形式、文档形式、图片形式等等，所以可以存储基础类型以及对象或者是集合等各种格式，而数据库则只支持基础类型。
    - 4）扩展性：关系型数据库有类似join这样的多表查询机制的限制导致扩展很艰难。
  - 缺点：
    - 1）维护的工具和资料有限，因为nosql是属于新的技术，不能和关系型数据库10几年的技术同日而语。
    - 2）不提供对sql的支持，如果不支持sql这样的工业标准，将产生一定用户的学习和使用成本。
    - 3）有的不提供关系型数据库对事务的处理。
    - 4）并不安全，数据存储在内存中可能未来得及持久化导致丢失
  - 优势：
    - 1）性能NOSQL是基于键值对的，可以想象成表中的主键和值的对应关系，而且不需要经过SQL层的解析，所以性能非常高。
    - 2）可扩展性同样也是因为基于键值对，数据之间没有耦合性，所以非常容易水平扩展。

- 关系型数据库
  - 优势：
    - 1）复杂查询可以用SQL语句方便的在一个表以及多个表之间做非常复杂的数据查询。
    - 2）事务支持使得对于安全性能很高的数据访问要求得以实现。对于这两类数据库，对方的优势就是自己的弱势，反之亦然。

---

- 总结
  - 关系型数据库与NoSQL数据库并非对立而是互补的关系，即通常情况下使用关系型数据库，在适合使用NoSQL的时候使用NoSQL数据库，
  - 让NoSQL数据库对关系型数据库的不足进行弥补。
  - 一般会将数据存储在关系型数据库中，在nosql数据库中备份存储关系型数据库的数据


```
关系型数据库                               nosql

高度组织化结构化数据                       代表着不仅仅是SQL
结构化查询语言(SQL)                        没有声明性查询语言
数据和关系都存储在单独的表中               没有预定义的模式
数据操纵语言，数据定义语言                 键-值对存储，列存储，文档存储，图形数据库
严格的一致性                               最终一致性，而非ACID属性
基础事务                                   非结构化和不可预知的数据:
                                           CAP定理
                                           高性能，高可用性和可伸缩性

```

### 1.4.4. 常见NoSQl数据库

- Redis:有丰富的数据结构
- Tair:有丰富的数据结构
- Mongodb:非常像关系型数据库
  - MongoDB是一个基于分布式文件存储的数据库。由C++语言编写。旨在为WEB应用提供可扩展的高性能数据存储解决方案。
  - MongoDB是一个介于关系数据库和非关系数据库之间的产品，是非关系数据库当中功能最丰富，最像关系数据库的。
- Memcache:注重于高速缓存

---

- 企业使用：
  - 新浪:BerkeleyDB+Redis
  - 美团:redis+tair
  - 阿里、百度:memcache+redis

### 1.4.5. redis三个重要特点

- KV
- Cache
- Persistence

## 1.5. 四种NoSQL数据模型

- 键值(Key-Value)存储数据库
  - 相关产品： Tokyo Cabinet/Tyrant、Redis、Voldemort、Berkeley DB
  - 典型应用： 内容缓存，主要用于处理大量数据的高访问负载。
  - 数据模型： 一系列键值对
  - 优势： 快速查询
  - 劣势： 存储的数据缺少结构化
- 列存储数据库
  - 相关产品：Cassandra, HBase, Riak
  - 典型应用：分布式的文件系统
  - 数据模型：以列簇式存储，将同一列数据存在一起
  - 优势：查找速度快，可扩展性强，更容易进行分布式扩展
  - 劣势：功能相对局限
- 文档型数据库
  - 相关产品：CouchDB、MongoDB(Bson)
  - 典型应用：Web应用（与Key-Value类似，Value是结构化的）
  - 数据模型： 一系列键值对
  - 优势：数据结构要求不严格
  - 劣势： 查询性能不高，而且缺乏统一的查询语法
- 图形(Graph)数据库
  - 相关数据库：Neo4J、InfoGrid、Infinite Graph
  - 典型应用：社交网络
  - 数据模型：图结构
  - 优势：利用图结构相关算法。
  - 劣势：需要对整个图做计算才能得出结果，不容易做分布式的集群方案。

  <details>
  <summary style="color:red;">图数据库示例</summary>

  ![redis-6](./image/redis-6.png)
  </details>

## 1.6. 分布式数据库原理

### 1.6.1. CAP

- CAP
  - 构成：
    - C:Consistency（强一致性）
    - A:Availability（可用性）
    - P:Partition tolerance（分区容错性）
  - 说明
    - CAP理论就是说在分布式存储系统中，最多只能实现上面的两点。
  - 运用
    - 而由于当前的网络硬件肯定会出现延迟丢包等问题， **所以分区容忍性是我们必须需要实现的** 。
    - 所以我们只能 **在一致性和可用性之间进行权衡** 
      >多余大多数web应用，其实并不需要强一致性。因此牺牲C换取P，这是目前分布式数据库产品的方向。
    - **没有NoSQL系统能同时保证这三点**。
  - 实际示例
    - CA:
      - 说明：单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。
      - 使用：传统Oracle数据库
    - AP:
      - 说明
        - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一些。
        - 有一些数据不一定要求异常精准，比如浏览数
        - 相对于强一致性，可用性更重要。
      - 使用：大多数网站架构的选择
    - CP:
      - 说明：
        - 满足一致性，分区容忍必的系统，通常性能不是特别高。
        - Redis起初目的是为了帮mysql减负
        - 所以要求查询数据时的强一致性。
      - 使用：Redis、Mongodb
    - 注意：分布式架构的时候必须做出取舍。
    - 图示
      > ![redis-8](./image/redis-8.png)-


<details>
<summary style="color:red;">详细说明</summary>

**CA without P**

所以如果你的分布式系統做到 CA，牺牲`Partition Tolerance`，那就是代表你的机房永远不會出現网络分区，永遠不會丢包 ，这么完美的机房给我來一打吧…除非有可能有完美的网路环境，否則 CA 根本就是传统的单机系统，而非分布式系統。

在分布式环境下，网络分区是一个自然的事实。因为分区是必然的，所以如果舍弃P，意味着要舍弃分布式系统。

**CP without A**

如果一个分布式系统不要求强的可用性，即容许系统停机或者长时间无响应的话，就可以在CAP三者中保障CP而舍弃A。

一个保证了CP而一个舍弃了A的分布式系统，一旦发生网络故障或者消息丢失等情况，就要牺牲用户的体验，等待所有数据全部一致了之后再让用户访问系统。

在单机版的Redis中，每个Master之间是没有任何通信的，所以我们一般在Jedis客户端或者Codis这样的代理中做Pre-sharding。按照CAP理论来说，单机版的Redis属于保证CP(Consistency & Partition-Tolerancy)而牺牲A(Availability)，也就说Redis能够保证所有用户看到相同的数据（一致性，因为Redis不自动冗余数据）和网络通信出问题时，暂时隔离开的子系统能继续运行（分区容忍性，因为Master之间没有直接关系，不需要通信），但是不保证某些结点故障时，所有请求都能被响应（可用性，某个Master结点挂了的话，那么它上面分片的数据就无法访问了）。

有了Cluster功能后，Redis从一个单纯的NoSQL内存数据库变成了分布式NoSQL数据库，CAP模型也从CP变成了AP。也就是说，通过自动分片和冗余数据，Redis具有了真正的分布式能力，某个结点挂了的话，因为数据在其他结点上有备份，所以其他结点顶上来就可以继续提供服务，保证了Availability。然而，也正因为这一点，Redis无法保证曾经的强一致性了。这也是CAP理论要求的，三者只能取其二。

**AP wihtout C**

要高可用并允许分区，则需放弃一致性。一旦网络问题发生，节点之间可能会失去联系。为了保证高可用，需要在用户访问时可以马上得到返回，则每个节点只能用本地数据提供服务，而这样会导致全局数据的不一致性。

这种舍弃强一致性而保证系统的分区容错性和可用性的场景和案例非常多。前面我们介绍可用性的时候说到过，很多系统在可用性方面会做很多事情来保证系统的全年可用性可以达到N个9，所以，对于很多业务系统来说，比如淘宝的购物，12306的买票。都是在可用性和一致性之间舍弃了一致性而选择可用性。

你在12306买票的时候肯定遇到过这种场景，当你购买的时候提示你是有票的（但是可能实际已经没票了），你也正常的去输入验证码，下单了。但是过了一会系统提示你下单失败，余票不足。这其实就是先在可用性方面保证系统可以正常的服务，然后在数据的一致性方面做了些牺牲，会影响一些用户体验，但是也不至于造成用户流程的严重阻塞。

但是，我们说很多网站牺牲了一致性，选择了可用性，这其实也不准确的。就比如上面的买票的例子，其实舍弃的只是强一致性。退而求其次保证了最终一致性。也就是说，虽然下单的瞬间，关于车票的库存可能存在数据不一致的情况，但是过了一段时间，还是要保证最终一致性的。

对于多数大型互联网应用的场景，主机众多、部署分散，而且现在的集群规模越来越大，所以节点故障、网络故障是常态，而且要保证服务可用性达到N个9，即保证P和A，舍弃C（退而求其次保证最终一致性）。虽然某些地方会影响客户体验，但没达到造成用户流程的严重程度。
</details>


---

- 当今数据库的需求说明：
  > 对于web2.0网站来说，关系数据库的很多主要特性却往往无用武之地
  - 数据库事务一致性需求
    - 很多web实时系统并不要求严格的数据库事务，
    - 对读一致性的要求很低，有些场合对写一致性要求并不高。允许实现最终一致性。
  - 数据库的写实时性和读实时性需求
    - 对关系数据库来说，插入一条数据之后立刻查询，是肯定可以读出来这条数据的，但是对于很多web应用来说，并不要求这么高的实时性，
    - 比方说在微博发一条消息之后，过几秒乃至十几秒之后，我的订阅者才看到这条动态是完全可以接受的。
  - 对复杂的SQL查询，特别是多表关联查询的需求
    - 任何大数据量的web系统，都非常忌讳多个大表的关联查询，以及复杂的数据分析类型的报表查询，
    - 特别是SNS类型的网站，从需求以及产品设计角度，就避免了这种情况的产生。
    - 往往更多的只是单表的主键查询，以及单表的简单条件分页查询，SQL的功能被极大的弱化了。


### 1.6.2. BASE

- BASE
  - 基本可用（Basically Available）
  - 软状态（Soft state）
  - 最终一致（Eventually consistent）

它的思想是通过 **让系统放松对某一时刻数据一致性的要求来换取系统整体伸缩性和性能上改观** 。

为什么这么说呢，缘由就在于大型系统往往由于地域分布和极高性能的要求，不可能采用分布式事务来完成这些指标，要想获得这些指标，
我们必须采用另外一种方式来完成，这里BASE就是解决这个问题的办法。


### 1.6.3. 分布式+集群


- 分布式系统（distributed system）
  - 由多台计算机和通信的软件组件通过计算机网络连接（本地网络或广域网）组成。
  - 分布式系统是建立在网络之上的软件系统。正是因为软件的特性，所以分布式系统具有高度的内聚性和透明性。
  - 因此，网络和分布式系统之间的区别更多的在于高层软件（特别是操作系统），而不是硬件。
  - 分布式系统可以应用在在不同的平台上如：PC、工作站、局域网和广域网上等。

- 概括：
  - 分布式：不同的多台服务器上面部署不同的服务模块（工程），他们之间通过Rpc/Rmi之间通信和调用，对外提供服务和组内协作。
  - 集群：不同的多台服务器上面部署相同的服务模块，通过分布式调度软件进行统一的调度，对外提供服务和访问。


# 2. Redis概述

## 2.1. 说明

**Redis:REmote DIctionary Server** (远程字典服务器)是完全开源免费的，用C语言编写的，遵守BSD协议，是一个高性能的(key/value)分布式内存数据库，基于内存运行 并支持持久化的NoSQL数据库，是当前最热门的NoSql数据库之一，也被人们称为数据结构服务器。

- Redis 与其他 key - value 缓存产品有以下三个特点：
  - Redis支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用
  - Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储
  - Redis支持数据的备份，即master-slave模式的数据备份

## 2.2. 主要应用

- 内存存储和持久化：redis支持异步将内存中的数据写到硬盘上，同时不影响继续服务
- 取最新N个数据的操作，如：可以将最新的10条评论的ID放在Redis的List集合里面
- 模拟类似于HttpSession这种需要设定过期时间的功能
- 发布、订阅消息系统
- 定时器、计数器

## 2.3. 安装

不进行详细说明

## 2.4. 杂项

- redis-benchmark.exe 测试redis在机器运行的效能
- 单进程
  - 单进程模型来处理客户端的请求。对读写等事件的响应 是通过对epoll函数的包装来做到的。Redis的实际处理速度完全依靠主进程的执行效率
  - Epoll是Linux内核为处理大批量文件描述符而作了改进的epoll，是Linux下多路复用IO接口select/poll的增强版本， 它能显著提高程序在大量并发连接中只有少量活跃的情况下的系统CPU利用率。
- 默认 **16个数据库** ，
  - 类似数组，下表从零开始，初始默认使用零号库
  - 可在配置文件配置
    ```
    databases 16
    ```
- select命令切换数据库:`select dbid`
- `dbsize`查看当前数据库的key的数量
- flushdb：清空 **当前库**，谨慎用。
- flushall；清空全部16个库，别用
- 统一密码管理， **16个库都是同样密码** ，要么都OK要么一个也连接不上
- Redis索引都是从零开始
- 默认端口是6379

# 3. 数据类型

## 3.1. 简介

- String（字符串）
  - string是redis最基本的类型，你可以理解成与Memcached一模一样的类型，一个key对应一个value。
  - string类型是 **二进制安全** 的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。
  - string类型是Redis **最基本** 的数据类型，一个redis中字符串value最多可以是 **512M**
- Hash（哈希，类似java里的Map）
  - Redis hash 是一个 **键值对集合**　。
  - Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。
  - 类似Java里面的`Map<String,Object>`
- List（列表）
  - Redis 列表是简单的 **字符串列表** ，按照插入顺序排序。
  - 可以添加一个元素导列表的头部（左边）或者尾部（右边）。
  - 它的底层实际是个 **链表** 。类似于java里面的`LinkedList`
- Set（集合）
  - Redis的Set是string类型的 **无序无重复集合** 。
  - 它是通过HashTable实现实现的
- Zset(sorted set：有序集合)
  - Redis zset 和 set 一样也是string类型元素的集合，且不允许重复的成员。
  - 不同的是每个元素都会关联一个 **double类型的分数** 。
  - redis正是 **通过分数来为集合中的成员进行从小到大的排序** 。
  - **zset的成员是唯一的，但分数(score)却可以重复** 。

## 3.2. Key

**常用命令** ：

| 命令                                      | 描述                                                         |
| ----------------------------------------- | ------------------------------------------------------------ |
| del key                                   | 该命令用于在 key 存在时删除 key。                            |
| dump key                                  | 序列化给定 key ，并返回被序列化的值。                        |
| exists key                                | 检查给定 key 是否存在。                                      |
| expire key seconds                        | 为给定 key 设置过期时间，以秒计。                            |
| expireat key timestamp                    | expireat 的作用和 expire 类似，都用于为 key 设置过期时间。 不同在于 expireat 命令接受的时间参数是 unix 时间戳(unix timestamp)。 |
| pexpire key milliseconds                  | 设置 key 的过期时间以毫秒计。                                |
| pexpireat key milliseconds-timestamp      | 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计           |
| keys pattern                              | 查找所有符合给定模式( pattern)的 key 。                      |
| move key db                               | 将当前数据库的 key 移动到给定的数据库 db 当中。              |
| persist key                               | 移除 key 的过期时间，key 将持久保持。                        |
| pttl key                                  | 以毫秒为单位返回 key 的剩余的过期时间。                      |
| ttl key                                   | 以秒为单位，返回给定 key 的剩余生存时间(ttl, time to live)。 |
| randomkey                                 | 从当前数据库中随机返回一个 key 。                            |
| rename key newkey                         | 修改 key 的名称                                              |
| renamenx key newkey                       | 仅当 newkey 不存在时，将 key 改名为 newkey 。                |
| scan cursor [match pattern] [count count] | 迭代数据库中的数据库键。                                     |
| type key                                  | 返回 key 所储存的值的类型。                                  |

## 3.3. String

**常用命令**

| 命令                           | 描述                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| set key value                  | 设置指定 key 的值                                            |
| get key                        | 获取指定 key 的值。                                          |
| getrange key start end         | 返回 key 中字符串值的子字符,-1时最后                                  |
| getset key value               | 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。   |
| getbit key offset              | 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。         |
| mget key1 [key2…]              | 获取所有(一个或多个)给定 key 的值。                          |
| setbit key offset value        | 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。   |
| setex key seconds value        | 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。 |
| setnx key value                | 只有在 key 不存在时设置 key 的值。                           |
| setrange key offset value      | 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。 |
| strlen key                     | 返回 key 所储存的字符串值的长度。                            |
| mset key value [key value …]   | 同时设置一个或多个 key-value 对。                            |
| msetnx key value [key value …] | 同时设置一个或多个 key-value 对，当且仅当 **所有** 给定 key 都不存在。 |
| psetex key milliseconds value  | 这个命令和 setex 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 setex 命令那样，以秒为单位。 |
| incr key                       | 将 key 中储存的数字值增一。                                  |
| incrby key increment           | 将 key 所储存的值加上给定的增量值（increment） 。            |
| incrbyfloat key increment      | 将 key 所储存的值加上给定的浮点增量值（increment） 。        |
| decr key                       | 将 key 中储存的数字值减一。                                  |
| decrby key decrement           | key 所储存的值减去给定的减量值（decrement） 。               |
| append key value               | 如果 key 已经存在并且是一个字符串， append 命令将指定的 value 追加到该 key 原来值（value）的末尾。 |

## 3.4. List

**常用命令**

| 命令                                  | 描述                                                         |
| ------------------------------------- | ------------------------------------------------------------ |
| blpop key1 [key2 ] timeout            | 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| brpop key1 [key2 ] timeout            | 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| brpoplpush source destination timeout | 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| lindex key index                      | 通过索引获取列表中的元素                                     |
| linsert key before/after pivot value  | 在列表的元素前或者后插入元素                                 |
| llen key                              | 获取列表长度                                                 |
| lpop key                              | 移出并获取列表的第一个元素                                   |
| lpush key value1 [value2]             | 将一个或多个值插入到列表头部                                 |
| lpushx key value                      | 将一个值插入到已存在的列表头部                               |
| lrange key start stop                 | 获取列表指定范围内的元素                                     |
| lrem key count value                  | 移除列表元素                                                 |
| lset key index value                  | 通过索引设置列表元素的值                                     |
| ltrim key start stop                  | 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 |
| rpop key                              | 移除列表的最后一个元素，返回值为移除的元素。                 |
| rpoplpush source destination          | 移除列表的最后一个元素，并将该元素添加到另一个列表并返回     |
| rpush key value1 [value2]             | 在列表中添加一个或多个值                                     |
| rpushx key value                      | 为已存在的列表添加值                                         |

**注意:是首插，左为首，也就是新插入的为首**

- 开头字母：
  - l是left
  - r时right
  - b是阻塞

- 性能总结：
  - 它是一个字符串链表，left、right都可以插入添加；
  - 如果键不存在，创建新的链表；
  - 如果键已存在，新增内容；
  - 如果值全移除，对应的键也就消失了。
  - 链表的操作 **无论是头和尾效率都极高** ，但假如是对中间元素进行操作，效率就很惨淡了。

## 3.5. Set

**常用命令**

| 命令                                           | 描述                                                |
| ---------------------------------------------- | --------------------------------------------------- |
| sadd key member1 [member2]                     | 向集合添加一个或多个成员                            |
| scard key                                      | 获取集合的成员数                                    |
| sdiff key1 [key2]                              | 返回给定所有集合的差集                              |
| sdiffstore destination key1 [key2]             | 返回给定所有集合的差集并存储在 destination 中       |
| sinter key1 [key2]                             | 返回给定所有集合的交集                              |
| sinterstore destination key1 [key2]            | 返回给定所有集合的交集并存储在 destination 中       |
| sismember key member                           | 判断 member 元素是否是集合 key 的成员               |
| smembers key                                   | 返回集合中的所有成员                                |
| smove source destination member                | 将 member 元素从 source 集合移动到 destination 集合 |
| spop key                                       | 移除并返回集合中的一个随机元素                      |
| srandmember key [count]                        | 返回集合中一个或多个随机数                          |
| srem key member1 [member2]                     | 移除集合中一个或多个成员                            |
| sunion key1 [key2]                             | 返回所有给定集合的并集                              |
| sunionstore destination key1 [key2]            | 所有给定集合的并集存储在 destination 集合中         |
| sscan key cursor [match pattern] [count count] | 迭代集合中的元素                                    |

## 3.6. zset

在set基础上，加一个score值。 之前set是k1 v1 v2 v3， 现在zset是k1 score1 v1 score2 v2

**常用命令**

| 命令                                           | 描述                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| zadd key score1 member1 [score2 member2]       | 向有序集合添加一个或多个成员，或者更新已存在成员的分数       |
| zcard key                                      | 获取有序集合的成员数                                         |
| zcount key min max                             | 计算在有序集合中指定区间分数的成员数                         |
| zincrby key increment member                   | 有序集合中对指定成员的分数加上增量 increment                 |
| zinterstore destination numkeys key [key …]    | 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中 |
| zlexcount key min max                          | 在有序集合中计算指定字典区间内成员数量                       |
| zrange key start stop [withscores]             | 通过索引区间返回有序集合指定区间内的成员                     |
| zrangebylex key min max [limit offset count]   | 通过字典区间返回有序集合的成员                               |
| zrangebyscore key min max [withscores] [limit] | 通过分数返回有序集合指定区间内的成员                         |
| zrank key member                               | 返回有序集合中指定成员的索引                                 |
| zrem key member [member …]                     | 移除有序集合中的一个或多个成员                               |
| zremrangebylex key min max                     | 移除有序集合中给定的字典区间的所有成员                       |
| zremrangebyrank key start stop                 | 移除有序集合中给定的排名区间的所有成员                       |
| zremrangebyscore key min max                   | 移除有序集合中给定的分数区间的所有成员                       |
| zrevrange key start stop [withscores]          | 返回有序集中指定区间内的成员，通过索引，分数从高到低         |
| zrevrangebyscore key max min [withscores]      | 返回有序集中指定分数区间内的成员，分数从高到低排序           |
| zrevrank key member                            | 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序 |
| zscore key member                              | 返回有序集中，成员的分数值                                   |
| zunionstore destination numkeys key [key …]    | 计算给定的一个或多个有序集的并集，并存储在新的 key 中        |
| zscan key cursor [match pattern] [count count] | 迭代有序集合中的元素（包括元素成员和元素分值）               |

## 3.7. HyperLogLog

# 配置文件说明

| 序号 | 配置项                                                       | 说明                                                         |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | `daemonize no`                                               | Redis 默认不是以守护进程的方式运行，可以通过该配置项修改，使用 yes 启用守护进程（Windows 不支持守护线程的配置为 no ） |
| 2    | `pidfile /var/run/redis.pid`                                 | 当 Redis 以守护进程方式运行时，Redis 默认会把 pid 写入 /var/run/redis.pid 文件，可以通过 pidfile 指定 |
| 3    | `port 6379`                                                  | 指定 Redis 监听端口，默认端口为 6379，作者在自己的一篇博文中解释了为什么选用 6379 作为默认端口，因为 6379 在手机按键上 MERZ 对应的号码，而 MERZ 取自意大利歌女 Alessia Merz 的名字 |
| 4    | `bind 127.0.0.1`                                             | 绑定的主机地址                                               |
| 5    | `timeout 300`                                                | 当客户端闲置多长秒后关闭连接，如果指定为 0 ，表示关闭该功能  |
| 6    | `loglevel notice`                                            | 指定日志记录级别，Redis 总共支持四个级别：debug、verbose、notice、warning，默认为 notice |
| 7    | `logfile stdout`                                             | 日志记录方式，默认为标准输出，如果配置 Redis 为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给 /dev/null |
| 8    | `databases 16`                                               | 设置数据库的数量，默认数据库为0，可以使用SELECT 命令在连接上指定数据库id |
| 9    | `save <seconds> <changes>` Redis 默认配置文件中提供了三个条件： `save 900 1` `save 300 10` `save 60 10000` | 分别表示 900 秒（15 分钟）内有 1 个更改，300 秒（5 分钟）内有 10 个更改以及 60 秒内有 10000 个更改。 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合 |
| 10   | `rdbcompression yes`                                         | 指定存储至本地数据库时是否压缩数据，默认为 yes，Redis 采用 LZF 压缩，如果为了节省 CPU 时间，可以关闭该选项，但会导致数据库文件变的巨大 |
| 11   | `dbfilename dump.rdb`                                        | 指定本地数据库文件名，默认值为 dump.rdb                      |
| 12   | `dir ./`                                                     | 指定本地数据库存放目录                                       |
| 13   | `slaveof <masterip> <masterport>`                            | 设置当本机为 slave 服务时，设置 master 服务的 IP 地址及端口，在 Redis 启动时，它会自动从 master 进行数据同步 |
| 14   | `masterauth <master-password>`                               | 当 master 服务设置了密码保护时，slav 服务连接 master 的密码  |
| 15   | `requirepass foobared`                                       | 设置 Redis 连接密码，如果配置了连接密码，客户端在连接 Redis 时需要通过 AUTH 命令提供密码，默认关闭 |
| 16   | `maxclients 128`                                             | 设置同一时间最大客户端连接数，默认无限制，Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis 会关闭新的连接并向客户端返回 max number of clients reached 错误信息 |
| 17   | `maxmemory <bytes>`                                          | 指定 Redis 最大内存限制，Redis 在启动时会把数据加载到内存中，达到最大内存后，Redis 会先尝试清除已到期或即将到期的 Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis 新的 vm 机制，会把 Key 存放内存，Value 会存放在 swap 区 |
| 18   | `appendonly no`                                              | 指定是否在每次更新操作后进行日志记录，Redis 在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis 本身同步数据文件是按上面 save 条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为 no |
| 19   | `appendfilename appendonly.aof`                              | 指定更新日志文件名，默认为 appendonly.aof                    |
| 20   | `appendfsync everysec`                                       | 指定更新日志条件，共有 3 个可选值： no：表示等操作系统进行数据缓存同步到磁盘（快） always：表示每次更新操作后手动调用 fsync() 将数据写到磁盘（慢，安全） everysec：表示每秒同步一次（折中，默认值） |
| 21   | `vm-enabled no`                                              | 指定是否启用虚拟内存机制，默认值为 no，简单的介绍一下，VM 机制将数据分页存放，由 Redis 将访问量较少的页即冷数据 swap 到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析 Redis 的 VM 机制） |
| 22   | `vm-swap-file /tmp/redis.swap`                               | 虚拟内存文件路径，默认值为 /tmp/redis.swap，不可多个 Redis 实例共享 |
| 23   | `vm-max-memory 0`                                            | 将所有大于 vm-max-memory 的数据存入虚拟内存，无论 vm-max-memory 设置多小，所有索引数据都是内存存储的(Redis 的索引数据 就是 keys)，也就是说，当 vm-max-memory 设置为 0 的时候，其实是所有 value 都存在于磁盘。默认值为 0 |
| 24   | `vm-page-size 32`                                            | Redis swap 文件分成了很多的 page，一个对象可以保存在多个 page 上面，但一个 page 上不能被多个对象共享，vm-page-size 是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page 大小最好设置为 32 或者 64bytes；如果存储很大大对象，则可以使用更大的 page，如果不确定，就使用默认值 |
| 25   | `vm-pages 134217728`                                         | 设置 swap 文件中的 page 数量，由于页表（一种表示页面空闲或使用的 bitmap）是在放在内存中的，，在磁盘上每 8 个 pages 将消耗 1byte 的内存。 |
| 26   | `vm-max-threads 4`                                           | 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4 |
| 27   | `glueoutputbuf yes`                                          | 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启 |
| 28   | `hash-max-zipmap-entries 64` `hash-max-zipmap-value 512`     | 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法 |
| 29   | `activerehashing yes`                                        | 指定是否激活重置哈希，默认为开启（后面在介绍 Redis 的哈希算法时具体介绍） |
| 30   | `include /path/to/local.conf`                                | 指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件 |

# 4. 内部基础数据结构

## 4.1. summary

## 4.2. sds 简单动态字符串

## 4.3. linkedlist 双端链表

## 4.4. ziplist 压缩列表

## 4.5. quicklist 快速链表

## 4.6. dict 字典

## 4.7. inset 整数集合

## 4.8. skiplist 跳表

# 5. 持久化

# 6. 集群

## 6.1. 主从复制实现

## 6.2. 哨兵

## 6.3. 集群

# 7. 事务的控制

# 8. 应用Application

## 8.1. 普通缓存

## 8.2. 分布式锁

## 8.3. 布隆过滤器

## 8.4. 布谷鸟过滤器

## 8.5. UV 统计

## 8.6. 排行榜

## 8.7. 关注列表和粉丝列表

## 8.8. 广告弹窗触达频率的控制

## 8.9. 延时队列


# 9. 高级算法

## 9.1. GeoHash(坐标定位算法)

## 9.2. scan(数据快速查询算法)

# 10. 常见问题

## 10.1. 容器型数据结构的通用规则

## 10.2. Redis 和 memcached 区别

## 10.3. Redis 是如何进行过期处理的

## 10.4. Redis 持久化机制(防止 Redis 挂掉后数据丢失)

## 10.5. redis 的 6 种数据淘汰策略

## 10.6. 缓存雪崩

## 10.7. 缓存穿透

## 10.8. 分布式锁

## 10.9. 延时队列的实现

## 10.10. 为什么单线程的 Redis 能处理那么多的并发客户端连接?
