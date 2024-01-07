
[来源](https://blog.csdn.net/oneby1314/category_10278969.html)

# 1. 第 1 章 MySQL 的架构介绍

## 1.1. 、MySQL 简介

> **什么是 MySQL？**

1. MySQL是一个关系型数据库管理系统，由瑞典MySQL AB公司开发，目前属于Oracle公司。
2. MySQL是一种关联数据库管理系统，将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。
3. Mysql是开源的，所以你不需要支付额外的费用。
4. Mysl支持大型的数据库，可以处理拥有上千万条记录的大型数据库。
5. MySQL使用标准的SQL数据语言形式。
6. Mysql可以允许于多个系统上，并且支持多种语言，这些编程语言包括C、C++、Python、Java、Ped、PHP、Eifel、Ruby和TCL等。
7. Mysql对PHP有很好的支持，PHP是目前最流行的Web开发语言。
8. MySQL支持大型数据库，支持5000万条记录的数据仓库，32位系统表文件最大可支持4GB，64位系统支持最大的表文件为8TB。
9. Mysql是可以定制的，采用了GPL协议，你可以修改源码来开发自己的Mysql系统。

> **MySQL 高手是怎样练成的？**

1. mysql内核
2. sql优化工程师
3. mysql服务器的优化
4. 查询语句优化
5. 主重复制
6. 软硬件升级
7. 容灾备份
8. sql编程

## 1.2. 、Linux 安装 MySQL

### 1.2.1. 、安装 MySQL

> **CentOS 6 通过 yum 安装 mysql 5.6**

emmm… 老师课件给的安装方式失败了。。。我在网上找了些资料，终于安装成功啦~~~废了老半天力气。。。

1. https://www.cnblogs.com/micfox/articles/10989905.html
2. https://blog.csdn.net/MissDreamY/article/details/104938194

------

**检查当前系统是否安装过 mysql**

- 检查系统是否安装其他版本的 MySQL 数据库

```bash
yum list installed | grep mysql
mysql-libs.x86_64      5.1.73-5.el6_6   @anaconda-CentOS-201508042137.x86_64/6.7
```

- 有的话干掉老版本数据库

```bash
yum -y remove mysql-libs.x86_64
```

------

**安装及配置**

- 拉取 rpm 包

```bash
wget http://repo.mysql.com/mysql-community-release-el6-5.noarch.rpm
```

- 通过 rpm 安装 mysql

```bash
rpm -ivh mysql-community-release-el6-5.noarch.rpm
```

- 列举可用的 mysql 版本

```bash
yum repolist all | grep mysql
```

- 安装 mysql-community-server 版本（好像默认自带 mysql-community-client）

```bash
yum install mysql-community-server -y
```

> **mysql 服务的启动与停止**

- mysql 服务的启动

```bash
service mysqld start
```

- mysql 服务的停止

```bash
service mysqld stop
```

- 查看 mysql 服务的状态

```bash
service mysql status
```

------

**备注：正在启动 mysqld: [失败] 问题**

- 打下面这一套组合拳

```bash
rm -fr /var/lib/mysql/*
rm /var/lock/subsys/mysqld
killall mysqld
```

- 然后启动 mysql 的服务

```bash
service mysqld start
```

### 1.2.2. 、配置 MySQL

> **查看安装时创建的 mysql 用户和 mysql 组**

- 查看 mysql 用户

```bash
cat /etc/passwd|grep mysql
```

- 查看 mysql 用户组

```bash
cat /etc/group|grep mysql
```

> **设置远程 root**

- 启动 mysql 服务

```bash
service mysqld start
```

- 设置 root 用户密码：这里面会有比较多的选项，按照提示操作即可

```bash
mysql_secure_installation
```

- 登录至 mysql root 账号

```bash
mysql -uroot -p 
```

> **设置 mysql 开机启动**

- 设置 mysql 为开机自启动

```bash
chkconfig mysqld on
```

- 查看是否设置成功：2、3、4都是on代表开机自动启动

```bash
chkconfig --list | grep mysqld
```

![image-20200803172114456](https://img-blog.csdnimg.cn/img_convert/7e2cba21195f49229907672df03e6e8f.png)

- 如果想要关闭 mysql 的开机自启动，输入如下命令，找到 mysqld ，单击空格取消 mysql 的开机自启动，Tab 键选中确定，敲击 Enter 键确定即可

```bash
ntsysv
```

![image-20200809212113024](https://img-blog.csdnimg.cn/img_convert/d88d342ac4b855bb441f94636d31c0e1.png)

> **修改 mysql 配置文件位置**

- 进入 /usr/share/mysql/ 目录，找到 my-default.cnf 配置文件

```bash
cd /usr/share/mysql/
```

![image-20200803200032116](https://img-blog.csdnimg.cn/img_convert/b6ea9124a0a66e20837331d0419f5095.png)

- 将其拷贝至 /etc 目录下，重命名为 my.cnf ，覆盖原有的配置文件

```bash
cp /usr/share/mysql/my-default.cnf /etc/my.cnf
```

![image-20200803200905667](https://img-blog.csdnimg.cn/img_convert/2a8ab9103b58f11d8f66b30ce61fe0f0.png)

> **修改 mysql 字符集**

**查看 mysql 编码字符集**

- 如果在建库建表的时候， 没有明确指定字符集， 则采用默认客户端和服务器端的字符集都用了 latin1 ，其中是不包含中文字符的。
- 如何查看默认的编码字符集：

```bash
show variables like '%char%';
# 或者
show variables like '%character%';
```

![image-20200803200546415](https://img-blog.csdnimg.cn/img_convert/dc45aadc168b40a3138ce5ef55de29a3.png)

------

**修改配置文件**

- 打开 /etc/my.cnf 配置文件，修改字符编码

```bash
vim /etc/my.cnf
```

- 修改或添加如下内容

```
[client]
default-character-set=utf8

[mysqld]
character_set_server=utf8
character_set_client=utf8
collation-server=utf8_general_ci

[mysql]
default-character-set=utf8
```

- 好像比老师的配置文件少了很多内容？？？

![image-20200803202236567](https://img-blog.csdnimg.cn/img_convert/33377b66ee2e6e06b04df707953dc845.png)

------

**测试配置文件是否生效**

- 建表测试：中文无乱码

```mysql
create database db01;
create table user(id int not null, name varchar(20));
insert into user values(1,'张三1');
select * from user;
```

![image-20200803202041567](https://img-blog.csdnimg.cn/img_convert/254e97701276516d39ef101a31e2cb21.png)

- 查看 mysql 全局变量

```mysql
show variables like '%char%';
```

![image-20200803202450726](https://img-blog.csdnimg.cn/img_convert/229400c9d6c65ff66fd63b6b44251331.png)

> **mysql 的安装位置**

- 执行命令

```bash
ps -ef|grep mysql
```

![image-20200803173609488](https://img-blog.csdnimg.cn/img_convert/33d523c59ec1a08319549dc11e3498ba.png)

- 目录含义列举如下

| 路径              | 解释                       |
| ----------------- | -------------------------- |
| /var/lib/mysql/   | mysql 数据库文件的存放路径 |
| /usr/share/mysql  | 配置文件目录               |
| /usr/bin          | 相关命令目录               |
| /etc/init.d/mysql | 服务启停相关               |

## 1.3. 、MySQL 用户组管理

### 1.3.1. 、用户管理相关命令

> **创建用户**

- 创建名称为 zhang3 的用户， 密码设为 123123；

```mysql
create user zhang3 identified by '123123';
```

> **查看用和权限的相关信息**

- 查看用和权限的相关信息的 SQL 指令

```mysql
select host, user, password, select_priv, insert_priv,drop_priv from mysql.user;
```

1. host :表示连接类型
   - % 表示所有远程通过 TCP 方式的连接
   - IP 地址 如 (192.168.1.2,127.0.0.1) 通过制定 ip 地址进行的 TCP 方式的连接
   - 机器名 通过制定 i 网络中的机器名进行的 TCP 方式的连接
   - ::1 IPv6 的本地 ip 地址 等同于 IPv4 的 127.0.0.1
   - localhost 本地方式通过命令行方式的连接 ， 比如 `mysql -u xxx -p 123xxx` 方式的连接。
2. user:表示用户名
   - 同一用户通过不同方式链接的权限是不一样的。
3. password:密码
   - 所有密码串通过 password(明文字符串) 生成的密文字符串。 加密算法为 MYSQLSHA1 ， 不可逆 。
   - mysql 5.7 的密码保存到 authentication_string 字段中不再使用 password 字段。
4. select_priv , insert_priv 等
   - 为该用户所拥有的权限。

```
mysql> select host, user, password, select_priv, insert_priv,drop_priv from mysql.user;
+-----------+------+-------------------------------------------+-------------+-------------+-----------+
| host      | user | password                                  | select_priv | insert_priv | drop_priv |
+-----------+------+-------------------------------------------+-------------+-------------+-----------+
| localhost | root | *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B | Y           | Y           | Y         |
| heygo     | root | *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B | Y           | Y           | Y         |
| 127.0.0.1 | root | *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B | Y           | Y           | Y         |
| ::1       | root | *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B | Y           | Y           | Y         |
+-----------+------+-------------------------------------------+-------------+-------------+-----------+
4 rows in set (0.04 sec)
```

> **修改当前用户的密码**

```mysql
set password =password('123456');
```

> **修改其他用户的密码**

- 修改李四用户的密码

```mysql
update mysql.user set password=password('123456') where user='li4';
```

- 注意：所有通过 user 表的修改， 必须 用 `flush privileges;` 命令才能生 效

> **修改用户名**

- 将王五的用户名修改为李四

```mysql
update mysql.user set user='li4' where user='wang5';
```

- 注意：所有通过 user 表的修改， 必须 用 `flush privileges;` 命令才能生 效

> **删除用户**

- 删除李四用户

```mysql
drop user li4
```

- 注意：不要通过 `delete from user u where user='li4'` 进行删除， 系 统会有残留信息保留。

### 1.3.2. 、MySQL 的权限管理

> **授予权限**

- 该权限如果发现没有该用户， 则会直接新建一个用户。

```
grant 权限 1,权限 2,…权限 n on 数据库名称.表名称 to 用户名@用户地址 identified by '连接口令'
```

- 示例：给 li4 用户用本地命令行方式下， 授予 atguigudb 这个库下的所有 表的插删改查的权限。

```mysql
grant select,insert,delete,drop on atguigudb.* to li4@localhost ;
```

------

- 授予通过网络方式登录的的 joe 用户，对所有库所有表的全部权 限， 密码设为 123

```
grant all privileges on *.* to joe@'%' identified by '123';
```

> **收回权限**

- 查看当前用户权限：

```mysql
show grants;
```

- 收回权限命令

```
revoke [权限 1,权限 2,…权限 n] on 库名.表名 from 用户名@用户地址;
```

- 收回全库全表的所有权限

```mysql
REVOKE ALL PRIVILEGES ON mysql.* FROM joe@localhost;
```

- 收回 mysql 库下的所有表的插删改查 权限

```mysql
REVOKE select,insert,update,delete ON mysql.* FROM joe@localhost;
```

- 注意：权限收回后， 必须用户重新登录后， 才能生效。

> **查看权限**

1. 查看当前用户权限：`show grants;`
2. 查看所有用户权限：`select * from user ;`

## 1.4. 、MySQL 配置文件

> **二进制日志文件 log-bin**

二进制日志文件 log-bin ：用于主重复制

> **错误日志 log-error**

默认是关闭的，记录严重的警告和错误信息，每次启动和关闭的详细信息等

> **查询日志 log**

默认关闭，记录查询的sql语句，如果开启会减低mysql的整体性能，因为记录日志也是需要消耗系统资源的

> **数据文件**

1. 数据库文件：默认路径：/var/lib/mysql
2. frm文件：存放表结构
3. myd文件：存放表数据
4. myi文件：存放表索引

> **如何配置**

1. Windows 系统下：my.ini文件
2. Linux 系统下：etc/my.cnf

## 1.5. 、MySQL 逻辑架构介绍

> **mysql 的分层思想**

1. 和其它数据库相比，MySQL有点与众不同，它的架构可以在多种不同场景中应用并发挥良好作用。主要体现在存储引擎的架构上。
2. 插件式的存储引擎架构将查询处理和其它的系统任务以及数据的存储提取相分离。这种架构可以根据业务的需求和实际需要选择合适的存储引擎。

![image-20200803205411622](https://img-blog.csdnimg.cn/img_convert/3c67c3e003b7f881f83c88ec20340a84.png)

> **mysql 四层架构**

1. **连接层**：最上层是一些客户端和连接服务，包含本地sock通信和大多数基于客户端/服务端工具实现的类似于tcp/ip的通信。主要完成一些类似于连接处理、授权认证、及相关的安全方案。在该层上引入了线程池的概念，为通过认证安全接入的客户端提供线程。同样在该层上可以实现基于SSL的安全链接。服务器也会为安全接入的每个客户端验证它所具有的操作权限。

2. **服务层**：第二层架构主要完成大多数的核心服务功能，如SQL接口，并完成缓存的查询，SQL的分析和优化及部分内置函数的执行。所有跨存储引擎的功能也在这一层实现，如过程、函数等。在该层，服务器会解析查询并创建相应的内部解析树，并对其完成相应的优化如确定查询表的顺序，是否利用索引等，最后生成相应的执行操作。如果是select语句，服务器还会查询内部的缓存。如果缓存空间足够大，这样在解决大量读操作的环境中能够很好的提升系统的性能。

   | Management Serveices & Utilities | 系统管理和控制工具                                           |
   | -------------------------------- | ------------------------------------------------------------ |
   | SQL Interface                    | SQL 接口。 接受用户的 SQL 命令， 并且返回用户需要查询的结果。 比如 select from 就是调用 SQL Interface |
   | Parser                           | 解析器。 SQL 命令传递到解析器的时候会被解析器验证和解析      |
   | Optimizer                        | 查询优化器。 SQL 语句在查询之前会使用查询优化器对查询进行优化， 比如有 where 条件时， 优化器来决定先投影还是先过滤。 |
   | Cache 和 Buffer                  | 查询缓存。 如果查询缓存有命中的查询结果， 查询语句就可以直接去查询缓存中取 数据。 这个缓存机制是由一系列小缓存组成的。 比如表缓存， 记录缓存， key 缓存， 权限缓存等 |

3. **引擎层**：存储引擎层，存储引擎真正的负责了MySQL中数据的存储和提取，服务器通过APl与存储引擎进行通信。不同的存储引擎具有的功能不同，这样我们可以根据自己的实际需要进行选取。后面介绍MyISAM和InnoDB

4. **存储层**：数据存储层，主要是将数据存储在运行于裸设备的文件系统之上，并完成与存储引擎的交互。

> **MySQL 部件**

1. Connectors：指的是不同语言中与SQL的交互
2. Management Serveices & Utilities： 系统管理和控制工具
3. Connection Pool：连接池
   - 管理缓冲用户连接，线程处理等需要缓存的需求。负责监听对 MySQL Server 的各种请求，接收连接请求，转发所有连接请求到线程管理模块。
   - 每一个连接上 MySQL Server 的客户端请求都会被分配（或创建）一个连接线程为其单独服务。而连接线程的主要工作就是负责 MySQL Server 与客户端的通信。接受客户端的命令请求，传递 Server 端的结果信息等。线程管理模块则负责管理维护这些连接线程。包括线程的创建，线程的 cache 等。
4. SQL Interface：SQL接口。接受用户的SQL命令，并且返回用户需要查询的结果。比如select from就是调用SQL Interface
5. Parser：解析器
   - SQL命令传递到解析器的时候会被解析器验证和解析。解析器是由Lex和YACC实现的，是一个很长的脚本。
   - 在 MySQL中我们习惯将所有 Client 端发送给 Server 端的命令都称为 Query，在 MySQL Server 里面，连接线程接收到客户端的一个 Query 后，会直接将该 Query 传递给专门负责将各种 Query 进行分类然后转发给各个对应的处理模块。
   - 解析器的主要功能：
     - 将SQL语句进行语义和语法的分析，分解成数据结构，然后按照不同的操作类型进行分类，然后做出针对性的转发到后续步骤，以后SQL语句的传递和处理就是基于这个结构的。
     - 如果在分解构成中遇到错误，那么就说明这个sql语句是不合理的
6. Optimizer：查询优化器
   - SQL语句在查询之前会使用查询优化器对查询进行优化。就是优化客户端发送过来的 sql 语句 ，根据客户端请求的 query 语句，和数据库中的一些统计信息，在一系列算法的基础上进行分析，得出一个最优的策略，告诉后面的程序如何取得这个 query 语句的结果
   - 他使用的是“选取-投影-联接”策略进行查询。
     - 用一个例子就可以理解： select uid,name from user where gender = 1;
     - 这个select 查询先根据where 语句进行选取，而不是先将表全部查询出来以后再进行gender过滤
     - 这个select查询先根据uid和name进行属性投影，而不是将属性全部取出以后再进行过滤
     - 将这两个查询条件联接起来生成最终查询结果
7. Cache和Buffer：查询缓存
   - 他的主要功能是将客户端提交 给MySQL 的 Select 类 query 请求的返回结果集 cache 到内存中，与该 query 的一个 hash 值 做一个对应。该 Query 所取数据的基表发生任何数据的变化之后， MySQL 会自动使该 query 的Cache 失效。在读写比例非常高的应用系统中， Query Cache 对性能的提高是非常显著的。当然它对内存的消耗也是非常大的。
   - 如果查询缓存有命中的查询结果，查询语句就可以直接去查询缓存中取数据。这个缓存机制是由一系列小缓存组成的。比如表缓存，记录缓存，key缓存，权限缓存等
8. 存储引擎接口
   - 存储引擎接口模块可以说是 MySQL 数据库中最有特色的一点了。目前各种数据库产品中，基本上只有 MySQL 可以实现其底层数据存储引擎的插件式管理。这个模块实际上只是 一个抽象类，但正是因为它成功地将各种数据处理高度抽象化，才成就了今天 MySQL 可插拔存储引擎的特色。
   - 从上图还可以看出，MySQL区别于其他数据库的最重要的特点就是其插件式的表存储引擎。MySQL插件式的存储引擎架构提供了一系列标准的管理和服务支持，这些标准与存储引擎本身无关，可能是每个数据库系统本身都必需的，如SQL分析器和优化器等，而存储引擎是底层物理结构的实现，每个存储引擎开发者都可以按照自己的意愿来进行开发。
   - 注意：存储引擎是基于表的，而不是数据库。

> **SQL 大致的查询流程**

**mysql 的查询流程大致是：**

1. mysql 客户端通过协议与 mysql 服务器建连接， 发送查询语句， 先检查查询缓存， 如果命中， 直接返回结果，否则进行语句解析,也就是说， 在解析查询之前， 服务器会先访问查询缓存(query cache)——它存储 SELECT 语句以及相应的查询结果集。 如果某个查询结果已经位于缓存中， 服务器就不会再对查询进行解析、 优化、 以及执行。 它仅仅将缓存中的结果返回给用户即可， 这将大大提高系统的性能。
2. 语法解析器和预处理： 首先 mysql 通过关键字将 SQL 语句进行解析， 并生成一颗对应的“解析树”。 mysql 解析器将使用 mysql 语法规则验证和解析查询； 预处理器则根据一些 mysql 规则进一步检查解析数是否合法。
3. 查询优化器当解析树被认为是合法的了， 并且由优化器将其转化成执行计划。 一条查询可以有很多种执行方式，最后都返回相同的结果。 优化器的作用就是找到这其中最好的执行计划。
4. 然后， mysql 默认使用的 BTREE 索引， 并且一个大致方向是：无论怎么折腾 sql， 至少在目前来说， mysql 最多只用到表中的一个索引。

## 1.6. 、MySQL 存储引擎

> **查看 mysql 存储引擎**

- 查看 mysql 支持的存储引擎

```mysql
show engines;
```

![image-20200803211155030](https://img-blog.csdnimg.cn/img_convert/ad4a7ed2cd88fb7661a35ab3dea81ede.png)

- 查看 mysql 默认的存储引擎

```mysql
show variables like '%storage_engine%';
```

![image-20200803211257403](https://img-blog.csdnimg.cn/img_convert/97ce605423dd317fb5a25ca57e346e58.png)

> **MyISAM 引擎和 InnoDb 引擎的对比**

![image-20200803211313682](https://img-blog.csdnimg.cn/img_convert/fed0966a37ec7ebcec65bb0a9837204c.png)

> **阿里巴巴用的是啥数据库？**

1. Percona为MySQL数据库服务器进行了改进，在功能和性能上较MySQL有着很显著的提升。该版本提升了在高负载情况下的InnoDB的性能、为DBA提供一些非常有用的性能诊断工具；另外有更多的参数和命令来控制服务器行为。
2. 该公司新建了一款存储引擎叫xtradb完全可以替代innodb，并且在性能和并发上做得更好，阿里巴巴大部分mysql数据库其实使用的percona的原型加以修改。

![image-20200803211429418](https://img-blog.csdnimg.cn/img_convert/da04cfac6493cec6f502acd817b968f6.png)



# 2. 第 2 章 索引优化分析

## 2.1. 、慢 SQL

> **性能下降、 SQL 慢、执行时间长、等待时间长的原因分析**

1. 查询语句写的烂
2. 索引失效：
   - 单值索引：在user表中给name属性建个索引，`create index idx_user_name on user(name)`
   - 复合索引：在user表中给name、email属性建个索引，`create index idx_user_nameEmail on user(name,email)`
3. 关联查询太多join(设计缺陷或不得已的需求)
4. 服务器调优及各个参数设置(缓冲、线程数等)

## 2.2. 、join 查询

### 2.2.1. 、SQL 执行顺序

> **我们手写的 SQL 顺序**

![image-20200803212731183](https://img-blog.csdnimg.cn/img_convert/6f63313638288480abfa89c49ecf6ae0.png)

> **MySQL 实际执行 SQL 顺序**

1. mysql 执行的顺序：随着 Mysql 版本的更新换代， 其优化器也在不断的升级， 优化器会分析不同执行顺序产生的性能消耗不同而动态调整执行顺序。
2. 下面是经常出现的查询顺序：

![image-20200803212826429](https://img-blog.csdnimg.cn/img_convert/bf19cf6a81b9d6d46f0bb78cccd35238.png)

- 总结：mysql 从 FROM 开始执行~

![image-20200803212936111](https://img-blog.csdnimg.cn/img_convert/292e157cd8d0efd2570d8c2c93284da4.png)

### 2.2.2. 、JOIN 连接查询

> **常见的 JOIN 查询图**

![image-20200803213106434](https://img-blog.csdnimg.cn/img_convert/2060feedc52769bca0af01c91711d217.png)

> **建表 SQL**

```mysql
CREATE TABLE tbl_dept(
	id INT(11) NOT NULL AUTO_INCREMENT,
	deptName VARCHAR(30) DEFAULT NULL,
	locAdd VARCHAR(40) DEFAULT NULL,
	PRIMARY KEY(id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE tbl_emp (
	id INT(11) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(20) DEFAULT NULL,
	deptId INT(11) DEFAULT NULL,
	PRIMARY KEY (id),
	KEY fk_dept_Id (deptId)
	#CONSTRAINT 'fk_dept_Id' foreign key ('deptId') references 'tbl_dept'('Id')
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO tbl_dept(deptName,locAdd) VALUES('RD',11);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('HR',12);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('MK',13);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('MIS',14);
INSERT INTO tbl_dept(deptName,locAdd) VALUES('FD',15);

INSERT INTO tbl_emp(NAME,deptId) VALUES('z3',1);
INSERT INTO tbl_emp(NAME,deptId) VALUES('z4',1);
INSERT INTO tbl_emp(NAME,deptId) VALUES('z5',1);
INSERT INTO tbl_emp(NAME,deptId) VALUES('w5',2);
INSERT INTO tbl_emp(NAME,deptId) VALUES('w6',2);
INSERT INTO tbl_emp(NAME,deptId) VALUES('s7',3);
INSERT INTO tbl_emp(NAME,deptId) VALUES('s8',4);
INSERT INTO tbl_emp(NAME,deptId) VALUES('s9',51);
```

- tbl_dept 表结构

```
mysql> select * from tbl_dept;
+----+----------+--------+
| id | deptName | locAdd |
+----+----------+--------+
|  1 | RD       | 11     |
|  2 | HR       | 12     |
|  3 | MK       | 13     |
|  4 | MIS      | 14     |
|  5 | FD       | 15     |
+----+----------+--------+
5 rows in set (0.00 sec)
```

- tbl_emp 表结构

```
mysql> select * from tbl_emp;
+----+------+--------+
| id | NAME | deptId |
+----+------+--------+
|  1 | z3   |      1 |
|  2 | z4   |      1 |
|  3 | z5   |      1 |
|  4 | w5   |      2 |
|  5 | w6   |      2 |
|  6 | s7   |      3 |
|  7 | s8   |      4 |
|  8 | s9   |     51 |
+----+------+--------+
8 rows in set (0.00 sec)
```

> **7 种 JOIN 示例**

**笛卡尔积**

1. tbl_emp 表和 tbl_dept 表的笛卡尔乘积：`select * from tbl_emp, tbl_dept;`
2. 其结果集的个数为：5 * 8 = 40

```
mysql> select * from tbl_emp, tbl_dept;
+----+------+--------+----+----------+--------+
| id | NAME | deptId | id | deptName | locAdd |
+----+------+--------+----+----------+--------+
|  1 | z3   |      1 |  1 | RD       | 11     |
|  1 | z3   |      1 |  2 | HR       | 12     |
|  1 | z3   |      1 |  3 | MK       | 13     |
|  1 | z3   |      1 |  4 | MIS      | 14     |
|  1 | z3   |      1 |  5 | FD       | 15     |
|  2 | z4   |      1 |  1 | RD       | 11     |
|  2 | z4   |      1 |  2 | HR       | 12     |
|  2 | z4   |      1 |  3 | MK       | 13     |
|  2 | z4   |      1 |  4 | MIS      | 14     |
|  2 | z4   |      1 |  5 | FD       | 15     |
|  3 | z5   |      1 |  1 | RD       | 11     |
|  3 | z5   |      1 |  2 | HR       | 12     |
|  3 | z5   |      1 |  3 | MK       | 13     |
|  3 | z5   |      1 |  4 | MIS      | 14     |
|  3 | z5   |      1 |  5 | FD       | 15     |
|  4 | w5   |      2 |  1 | RD       | 11     |
|  4 | w5   |      2 |  2 | HR       | 12     |
|  4 | w5   |      2 |  3 | MK       | 13     |
|  4 | w5   |      2 |  4 | MIS      | 14     |
|  4 | w5   |      2 |  5 | FD       | 15     |
|  5 | w6   |      2 |  1 | RD       | 11     |
|  5 | w6   |      2 |  2 | HR       | 12     |
|  5 | w6   |      2 |  3 | MK       | 13     |
|  5 | w6   |      2 |  4 | MIS      | 14     |
|  5 | w6   |      2 |  5 | FD       | 15     |
|  6 | s7   |      3 |  1 | RD       | 11     |
|  6 | s7   |      3 |  2 | HR       | 12     |
|  6 | s7   |      3 |  3 | MK       | 13     |
|  6 | s7   |      3 |  4 | MIS      | 14     |
|  6 | s7   |      3 |  5 | FD       | 15     |
|  7 | s8   |      4 |  1 | RD       | 11     |
|  7 | s8   |      4 |  2 | HR       | 12     |
|  7 | s8   |      4 |  3 | MK       | 13     |
|  7 | s8   |      4 |  4 | MIS      | 14     |
|  7 | s8   |      4 |  5 | FD       | 15     |
|  8 | s9   |     51 |  1 | RD       | 11     |
|  8 | s9   |     51 |  2 | HR       | 12     |
|  8 | s9   |     51 |  3 | MK       | 13     |
|  8 | s9   |     51 |  4 | MIS      | 14     |
|  8 | s9   |     51 |  5 | FD       | 15     |
+----+------+--------+----+----------+--------+
40 rows in set (0.00 sec)
```

------

**inner join**

1. tbl_emp 表和 tbl_dept 的交集部分（公共部分）
2. `select * from tbl_emp e inner join tbl_dept d on e.deptId = d.id;`

```
mysql> select * from tbl_emp e inner join tbl_dept d on e.deptId = d.id;
+----+------+--------+----+----------+--------+
| id | NAME | deptId | id | deptName | locAdd |
+----+------+--------+----+----------+--------+
|  1 | z3   |      1 |  1 | RD       | 11     |
|  2 | z4   |      1 |  1 | RD       | 11     |
|  3 | z5   |      1 |  1 | RD       | 11     |
|  4 | w5   |      2 |  2 | HR       | 12     |
|  5 | w6   |      2 |  2 | HR       | 12     |
|  6 | s7   |      3 |  3 | MK       | 13     |
|  7 | s8   |      4 |  4 | MIS      | 14     |
+----+------+--------+----+----------+--------+
7 rows in set (0.00 sec)
```

------

**left join**

1. tbl_emp 与 tbl_dept 的公共部分 + tbl_emp 表的独有部分
2. left join：取左表独有部分 + 两表公共部分
3. `select * from tbl_emp e left join tbl_dept d on e.deptId = d.id;`

```
mysql> select * from tbl_emp e left join tbl_dept d on e.deptId = d.id;
+----+------+--------+------+----------+--------+
| id | NAME | deptId | id   | deptName | locAdd |
+----+------+--------+------+----------+--------+
|  1 | z3   |      1 |    1 | RD       | 11     |
|  2 | z4   |      1 |    1 | RD       | 11     |
|  3 | z5   |      1 |    1 | RD       | 11     |
|  4 | w5   |      2 |    2 | HR       | 12     |
|  5 | w6   |      2 |    2 | HR       | 12     |
|  6 | s7   |      3 |    3 | MK       | 13     |
|  7 | s8   |      4 |    4 | MIS      | 14     |
|  8 | s9   |     51 | NULL | NULL     | NULL   |
+----+------+--------+------+----------+--------+
8 rows in set (0.00 sec)
```

------

**right join**

1. tbl_emp 与 tbl_dept 的公共部分 + tbl_dept表的独有部分
2. right join：取右表独有部分 + 两表公共部分
3. `select * from tbl_emp e right join tbl_dept d on e.deptId = d.id;`

```
mysql> select * from tbl_emp e right join tbl_dept d on e.deptId = d.id;
+------+------+--------+----+----------+--------+
| id   | NAME | deptId | id | deptName | locAdd |
+------+------+--------+----+----------+--------+
|    1 | z3   |      1 |  1 | RD       | 11     |
|    2 | z4   |      1 |  1 | RD       | 11     |
|    3 | z5   |      1 |  1 | RD       | 11     |
|    4 | w5   |      2 |  2 | HR       | 12     |
|    5 | w6   |      2 |  2 | HR       | 12     |
|    6 | s7   |      3 |  3 | MK       | 13     |
|    7 | s8   |      4 |  4 | MIS      | 14     |
| NULL | NULL |   NULL |  5 | FD       | 15     |
+------+------+--------+----+----------+--------+
8 rows in set (0.00 sec)
```

------

**left join without common part**

1. tbl_emp 表的独有部分：将 left join 结果集中的两表公共部分去掉即可：`where d.id is null`
2. `select * from tbl_emp e left join tbl_dept d on e.deptId = d.id where d.id is null;`

```
mysql> select * from tbl_emp e left join tbl_dept d on e.deptId = d.id where d.id is null;
+----+------+--------+------+----------+--------+
| id | NAME | deptId | id   | deptName | locAdd |
+----+------+--------+------+----------+--------+
|  8 | s9   |     51 | NULL | NULL     | NULL   |
+----+------+--------+------+----------+--------+
1 row in set (0.00 sec)
```

------

**right join without common part**

1. tbl_dept表的独有部分：将 right join 结果集中的两表公共部分去掉即可：`where e.id is null`
2. `select * from tbl_emp e right join tbl_dept d on e.deptId = d.id where e.id is null;`

```
mysql> select * from tbl_emp e right join tbl_dept d on e.deptId = d.id where e.id is null;
+------+------+--------+----+----------+--------+
| id   | NAME | deptId | id | deptName | locAdd |
+------+------+--------+----+----------+--------+
| NULL | NULL |   NULL |  5 | FD       | 15     |
+------+------+--------+----+----------+--------+
1 row in set (0.00 sec)
```

------

**full join**

1. 兄弟，mysql 不支持 full join ，但是我们可以通过骚操作实现 full join ，union 关键字用于连接结果集，并且自动去重
2. tbl_emp 与 tbl_dept 的公共部分 + tbl_emp 表的独有部分 + tbl_dept表的独有部分：将 left join 的结果集和 right join 的结果集使用 union 合并即可
3. `select * from tbl_emp e left join tbl_dept d on e.deptId = d.id union select * from tbl_emp e right join tbl_dept d on e.deptId = d.id;`

```
mysql> select * from tbl_emp e left join tbl_dept d on e.deptId = d.id
    -> union
    -> select * from tbl_emp e right join tbl_dept d on e.deptId = d.id;
+------+------+--------+------+----------+--------+
| id   | NAME | deptId | id   | deptName | locAdd |
+------+------+--------+------+----------+--------+
|    1 | z3   |      1 |    1 | RD       | 11     |
|    2 | z4   |      1 |    1 | RD       | 11     |
|    3 | z5   |      1 |    1 | RD       | 11     |
|    4 | w5   |      2 |    2 | HR       | 12     |
|    5 | w6   |      2 |    2 | HR       | 12     |
|    6 | s7   |      3 |    3 | MK       | 13     |
|    7 | s8   |      4 |    4 | MIS      | 14     |
|    8 | s9   |     51 | NULL | NULL     | NULL   |
| NULL | NULL |   NULL |    5 | FD       | 15     |
+------+------+--------+------+----------+--------+
9 rows in set (0.00 sec)
```

------

**full join without common part**

1. tbl_emp 表的独有部分 + tbl_dept表的独有部分
2. `select * from tbl_emp e left join tbl_dept d on e.deptId = d.id where d.id is null union select * from tbl_emp e right join tbl_dept d on e.deptId = d.id where e.id is null;`

```
mysql> select * from tbl_emp e left join tbl_dept d on e.deptId = d.id where d.id is null
    -> union
    -> select * from tbl_emp e right join tbl_dept d on e.deptId = d.id where e.id is null;
+------+------+--------+------+----------+--------+
| id   | NAME | deptId | id   | deptName | locAdd |
+------+------+--------+------+----------+--------+
|    8 | s9   |     51 | NULL | NULL     | NULL   |
| NULL | NULL |   NULL |    5 | FD       | 15     |
+------+------+--------+------+----------+--------+
2 rows in set (0.00 sec)
```

## 2.3. 、索引简介

### 2.3.1. 、索引是什么

> **索引是个什么东东？**

1. MySQL官方对索引的定义为：索引(Index)是帮助MySQL高效获取数据的数据结构。可以得到索引的本质：**索引是数据结构**
2. 你可以简单理解为"**排好序的快速查找数据结构**"，即**索引 = 排序 + 查找**
3. 一般来说索引本身占用内存空间也很大，不可能全部存储在内存中，因此**索引往往以文件形式存储在硬盘上**
4. 我们平时所说的索引，如果没有特别指明，都是指B树(多路搜索树，并不一定是二叉树)结构组织的索引。
5. 聚集索引，次要索引，覆盖索引，复合索引，前缀索引，唯一索引默认都是使用B+树索引，统称索引。当然，除了B+树这种类型的索引之外，还有哈希索引(hash index)等。

### 2.3.2. 、索引原理

> **将索引理解为"排好序的快速查找数据结构"**

1. 在数据之外，数据库系统还维护着满足特定查找算法的数据结构，这些数据结构**以某种方式引用（指向）数据**，这样就可以在这些数据结构上实现高级查找算法。这种数据结构，就是索引。
2. 下图就是一种可能的索引方式示例：
   - 左边是数据表，一共有两列七条记录，最左边的十六进制数字是数据记录的物理地址
   - 为了加快col2的查找，可以维护一个右边所示的二叉查找树，**每个节点分别包含索引键值和一个指向对应数据记录物理地址的指针**，这样就可以运用二叉查找在一定的复杂度内获取到相应数据，从而快速的检索出符合条件的记录。

![image-20200803222848380](https://img-blog.csdnimg.cn/img_convert/b258fbc51e43460fc2bfaaa1d31fe426.png)

### 2.3.3. 、索引优劣势

> **索引的优势**

1. 类似大学图书馆的书目索引，提高数据检索效率，**降低数据库的IO成本**
2. 通过索引列对数据进行排序，**降低数据排序成本**，降低了CPU的消耗

> **索引的劣势**

1. 实际上索引也是一张表，该表保存了主键和索引字段，并指向实体表的记录，所以索引列也是要占用空间的
2. 虽然索引大大提高了查询速度，**同时却会降低更新表的速度**，如果对表INSERT，UPDATE和DELETE。因为更新表时，MySQL不仅要不存数据，还要保存一下索引文件每次更新添加了索引列的字段，都会调整因为更新所带来的键值变化后的索引信息
3. 索引只是提高效率的一个因素，如果你的MySQL有大数据量的表，就需要花时间研究建立优秀的索引，或优化查询语句

### 2.3.4. 、MySQL 索引分类

> **mysql 索引分类**

参考资料：https://www.cnblogs.com/luyucheng/p/6289714.html

1. 普通索引：是最基本的索引，它没有任何限制，即一个索引只包含单个列，一个表可以有多个单列索引；建议一张表索引不要超过5个，优先考虑复合索引
2. 唯一索引：与前面的普通索引类似，不同的就是：索引列的值必须唯一，但允许有空值。如果是组合索引，则列值的组合必须唯一
3. 主键索引：是一种特殊的唯一索引，一个表只能有一个主键，不允许有空值。一般是在建表的时候同时创建主键索引：
4. 复合索引：指多个字段上创建的索引，只有在查询条件中使用了创建索引时的第一个字段，索引才会被使用。使用组合索引时遵循最左前缀集合
5. 全文索引：主要用来查找文本中的关键字，而不是直接与索引中的值相比较。fulltext索引跟其它索引大不相同，它更像是一个搜索引擎，而不是简单的where语句的参数匹配

### 2.3.5. 、MySQL 索引语法

> **建立索引的 SQL 语句**

1. 创建索引：

   - 如果是CHAR和VARCHAR类型，length可以小于字段实际长度；
   - 如果是BLOB和TEXT类型，必须指定length。

   ```mysql
   CREATE [UNIQUE] INDEX  indexName ON mytable(columnname(length));
   ' or '
   ALTER mytable ADD [UNIQUE]  INDEX [indexName] ON(columnname(length));
   ```

2. 删除索引

   ```mysql
   DROP INDEX [indexName] ON mytable;
   ```

3. 查看索引（`\G`表示将查询到的横向表格纵向输出，方便阅读）

   ```mysql
   SHOW INDEX FROM table_name\G
   ```

------

**使用 ALTER 命令，有四种方式来添加数据表的索引：**

1. `ALTER TABLE tbl_name ADD PRIMARY KEY(column_list)`：该语句添加一个主键，这意味着索引值必须是唯一的，且不能为NULL。
2. `ALTER TABLE tbl_name ADD UNIQUE index_name(column_list)`：这条语句创建索引的值必须是唯一的（除了NULL外，NULL可能会出现多次）。
3. `ALTER TABLE tbl_name ADD INDEX index_name(column_list)`：.添加普通索引，索引值可出现多次。
4. `ALTER TABLE tbl_name ADD FULLTEXT index_name(column_list)`：该语句指定了索引为FULLTEXT，用于全文索引。

------

**带你看看 mysql 索引：Index_type 为 BTREE**

```
mysql> show index from tbl_emp;
+---------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table   | Non_unique | Key_name   | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+---------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| tbl_emp |          0 | PRIMARY    |            1 | id          | A         |           8 |     NULL | NULL   |      | BTREE      |         |               |
| tbl_emp |          1 | fk_dept_Id |            1 | deptId      | A         |           8 |     NULL | NULL   | YES  | BTREE      |         |               |
+---------+------------+------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
2 rows in set (0.00 sec)
```

### 2.3.6. 、MySQL 索引结构

#### 2.3.6.1. 、Btree 索引

> **Btree 索引搜索过程**

【初始化介绍】

1. 一颗 b 树， **浅蓝色的块我们称之为一个磁盘块**， 可以看到每个磁盘块包含几个**数据项（深蓝色所示） 和指针（黄色所示）**
2. 如磁盘块 1 包含数据项 17 和 35， 包含指针 P1、 P2、 P3
3. P1 表示小于 17 的磁盘块， P2 表示在 17 和 35 之间的磁盘块， P3 表示大于 35 的磁盘块
4. **真实的数据存在于叶子节点和非叶子节点中**

【查找过程】

1. 如果要查找数据项 29， 那么首先会把磁盘块 1 由磁盘加载到内存， 此时发生一次 IO， 在内存中用二分查找确定 29在 17 和 35 之间， 锁定磁盘块 1 的 P2 指针， 内存时间因为非常短（相比磁盘的 IO） 可以忽略不计
2. 通过磁盘块 1的 P2 指针的磁盘地址把磁盘块 3 由磁盘加载到内存， 发生第二次 IO， 29 在 26 和 30 之间， 锁定磁盘块 3 的 P2 指针
3. 通过指针加载磁盘块 8 到内存， 发生第三次 IO， 同时内存中做二分查找找到 29， 结束查询， 总计三次 IO。

![image-20200804093236612](https://img-blog.csdnimg.cn/img_convert/e4942aa1472d9d720bd14b12c1561965.png)

#### 2.3.6.2. 、B+tree 索引

> **B+tree 索引搜索过程**

【B+Tree 与 BTree 的区别】

B-树的关键字（数据项）和记录是放在一起的； B+树的非叶子节点中只有关键字和指向下一个节点的索引， 记录只放在叶子节点中。

【B+Tree 与 BTree 的查找过程】

1. 在 B 树中， 越靠近根节点的记录查找时间越快， 只要找到关键字即可确定记录的存在； 而 B+ 树中每个记录的查找时间基本是一样的， 都需要从根节点走到叶子节点， 而且在叶子节点中还要再比较关键字。
2. 从这个角度看 B 树的性能好像要比 B+ 树好， 而在实际应用中却是 B+ 树的性能要好些。 因为 B+ 树的非叶子节点不存放实际的数据，这样每个节点可容纳的元素个数比 B 树多， 树高比 B 树小， 这样带来的好处是减少磁盘访问次数。
3. 尽管 B+ 树找到一个记录所需的比较次数要比 B 树多， 但是一次磁盘访问的时间相当于成百上千次内存比较的时间， 因此实际中B+ 树的性能可能还会好些， 而且 B+树的叶子节点使用指针连接在一起， 方便顺序遍历（范围搜索）， 这也是很多数据库和文件系统使用 B+树的缘故。

【性能提升】

真实的情况是， 3 层的 B+ 树可以表示上百万的数据， 如果上百万的数据查找只需要三次 IO， 性能提高将是巨大的，如果没有索引， 每个数据项都要发生一次 IO， 那么总共需要百万次的 IO， 显然成本非常非常高。

【思考： 为什么说 B+树比 B-树更适合实际应用中操作系统的文件索引和数据库索引？】

1. B+树的磁盘读写代价更低：B+树的内部结点并没有指向关键字具体信息的指针。 因此其内部结点相对 B 树更小。 如果把所有同一内部结点的关键字存放在同一盘块中， 那么盘块所能容纳的关键字数量也越多。 一次性读入内存中的需要查找的关键字也就越多。 相对来说 IO 读写次数也就降低了。
2. B+树的查询效率更加稳定：由于非终结点并不是最终指向文件内容的结点， 而只是叶子结点中关键字的索引。 所以任何关键字的查找必须走一条从根结点到叶子结点的路。 所有关键字查询的路径长度相同， 导致每一个数据的查询效率相当。

![image-20200811145838930](https://img-blog.csdnimg.cn/img_convert/7f5ea19eb42404dd4437667bfc29dd84.png)

### 2.3.7. 、何时需要建索引

> **哪些情况下适合建立索引**

1. **主键自动建立唯一索引**
2. **频繁作为查询的条件的字段**应该创建索引
3. **查询中与其他表关联的字段**，外键关系建立索引
4. 频繁更新的字段不适合创建索引
5. Where 条件里用不到的字段不创建索引
6. 单间/组合索引的选择问题，Who？（在高并发下倾向创建组合索引）
7. **查询中排序的字段**，排序字段若通过索引去访问将大大提高排序的速度
8. **查询中统计或者分组字段**

> **哪些情况不要创建索引**

1. 表记录太少
2. **经常增删改的表**
3. **数据重复且分布平均的表字段**，因此应该只为经常查询和经常排序的数据列建立索引。注意，如果某个数据列包含许多重复的内容，为它建立索引就没有太大的实际效果。

------

**案例分析：**

1. 假如一个表有10万行记录，有一个字段A只有T和F两种值，且每个值的分布概率大约为50%，那么对这种表A字段建索引一般不会提高数据库的查询速度。
2. 索引的选择性是指索引列中不同值的数目与表中记录数的比。如果一个表中有2000条记录，表索引列有1980个不同的值，那么这个索引的选择性就是1980/2000=0.99。
3. 一个索引的选择性越接近于1，这个索引的效率就越高。

## 2.4. 、性能分析

### 2.4.1. 、性能优化概述

> **MySQL Query Optimizer 的作用**

1. MySQL 中有专门负责优化SELECT语句的优化器模块，主要功能：通过计算分析系统中收集到的统计信息，为客户端请求的Query提供他认为最优的执行计划（MySQL认为最优的数据检索方式，但不见得是DBA认为是最优的，这部分最耗费时间）
2. 当客户端向MySQL 请求一条Query，命令解析器模块完成请求分类，区别出是SELECT并转发给MySQL Query Optimizer时，MySQL Query Optimizer 首先会对整条Query进行优化，处理掉一些常量表达式的预算，直接换算成常量值。并对Query中的查询条件进行简化和转换，如去掉一些无用或显而易见的条件、结构调整等。然后分析 Query中的Hint信息（如果有），看显示Hint信息是否可以完全确定该Query的执行计划。如果没有Hint 或Hint 信息还不足以完全确定执行计划，则会读取所涉及对象的统计信息，根据Query进行写相应的计算分析，然后再得出最后的执行计划。

> **MySQL 常见瓶颈**

1. CPU 瓶颈：CPU在饱和的时候一般发生在数据装入在内存或从磁盘上读取数据时候
2. IO 瓶颈：磁盘I/O瓶颈发生在装入数据远大于内存容量时
3. 服务器硬件的性能瓶颈：top、free、iostat和vmstat来查看系统的性能状态

### 2.4.2. 、Explain 概述

> **Explain**

**是什么？Explain 是查看执行计划**

1. 使用EXPLAIN关键字可以模拟优化器执行SQL语句，从而知道MySQL是如何处理你的SQL语句的。分析你的查询语句或是结构的性能瓶颈
2. 官网地址：https://dev.mysql.com/doc/refman/8.0/en/explain-output.html

------

**能干嘛？**

1. 表的读取顺序（id 字段）
2. 数据读取操作的操作类型（select_type 字段）
3. 哪些索引可以使用（possible_keys 字段）
4. 哪些索引被实际使用（keys 字段）
5. 表之间的引用（ref 字段）
6. 每张表有多少行被优化器查询（rows 字段）

------

**怎么玩？**

- Explain + SQL语句

```
mysql> explain select * from tbl_emp;
+----+-------------+---------+------+---------------+------+---------+------+------+-------+
| id | select_type | table   | type | possible_keys | key  | key_len | ref  | rows | Extra |
+----+-------------+---------+------+---------------+------+---------+------+------+-------+
|  1 | SIMPLE      | tbl_emp | ALL  | NULL          | NULL | NULL    | NULL |    8 | NULL  |
+----+-------------+---------+------+---------------+------+---------+------+------+-------+
1 row in set (0.00 sec)
```

### 2.4.3. 、Explain 详解

> **id：select查询的序列号，包含一组数字，表示查询中执行select子句或操作表的顺序**

id 取值的三种情况：

1. **id相同**，执行顺序由上至下

   ![image-20200804101016101](https://img-blog.csdnimg.cn/img_convert/da4abcf3fa84454edf244eb89e1be90a.png)

2. **id不同**，如果是子查询，id的序号会递增，**id值越大优先级越高，越先被执行**

   ![image-20200804101005684](https://img-blog.csdnimg.cn/img_convert/25404ed2cc2d5443ca8e30bf09579d7f.png)

3. **id相同不同**，同时存在：id如果相同，可以认为是一组，从上往下顺序执行；在所有组中，id值越大，优先级越高，越先执行；衍生=DERIVED

   ![image-20200804101502048](https://img-blog.csdnimg.cn/img_convert/e4e29eff2a64a71f6e748deb9baf99cc.png)

> **select_type：查询的类型，主要用于区别普通查询、联合查询、子查询等复杂查询**

1. SIMPLE：简单的select查询，查询中不包含子查询或者UNION
2. PRIMARY：查询中若包含任何复杂的子部分，最外层查询则被标记为PRIMARY
3. SUBQUERY：在SELECT或者WHERE列表中包含了子查询
4. DERIVED：在FROM列表中包含的子查询被标记为DERIVED（衍生）MySQL会递归执行这些子查询，把结果放在临时表里
5. UNION：若第二个SELECT出现在UNION之后，则被标记为UNION；若UNION包含在FROM子句的子查询中，外层SELECT将被标记为：DERIVED
6. UNION RESULT：从UNION表获取结果的SELECT

------

**UNION 和 UNION RESULT举例**

```
xplain
    -> select * from tbl_emp e left join tbl_dept d on e.deptId = d.id
    -> union
    -> select * from tbl_emp e right join tbl_dept d on e.deptId = d.id;
+----+--------------+------------+------+---------------+------------+---------+-----------+------+----------------------------------------------------+
| id | select_type  | table      | type | possible_keys | key        | key_len | ref       | rows | Extra                                              |
+----+--------------+------------+------+---------------+------------+---------+-----------+------+----------------------------------------------------+
|  1 | PRIMARY      | e          | ALL  | NULL          | NULL       | NULL    | NULL      |    8 | NULL                                               |
|  1 | PRIMARY      | d          | ALL  | PRIMARY       | NULL       | NULL    | NULL      |    5 | Using where; Using join buffer (Block Nested Loop) |
|  2 | UNION        | d          | ALL  | NULL          | NULL       | NULL    | NULL      |    5 | NULL                                               |
|  2 | UNION        | e          | ref  | fk_dept_Id    | fk_dept_Id | 5       | db01.d.id |    1 | NULL                                               |
| NULL | UNION RESULT | <union1,2> | ALL  | NULL          | NULL       | NULL    | NULL      | NULL | Using temporary                                    |
+----+--------------+------------+------+---------------+------------+---------+-----------+------+----------------------------------------------------+
5 rows in set (0.00 sec)
```

> **table：显示这一行的数据是关于哪张表的**

> **type：访问类型排列，显示查询使用了何种类型**

1. type显示的是访问类型，是较为重要的一个指标，结果值从最好到最坏依次是：`system>const>eq_ref>ref>fultext>ref_or_null>index_merge>unique_subquery>index_subquery>range>index>ALL`
2. 挑重要的来说：`system>const>eq_ref>ref>range>index>ALL`，一般来说，得保证查询至少达到range级别，最好能达到ref。

------

**从最好到最差依次是：system>const>eq_ref>ref>range>index>ALL**

1. system：表只有一行记录（等于系统表），这是const类型的特例，平时不会出现，这个也可以忽略不计

2. const：表示通过索引一次就找到了，const用于比较primary key或者unique索引。因为只匹配一行数据，所以很快。如将主键置于where列表中，MySQL就能将该查询转换为一个常量

   ![image-20200804103028065](https://img-blog.csdnimg.cn/img_convert/4ab5ad168975b919a0fef79a50ef722a.png)

3. eq_ref：唯一性索引，对于每个索引键，表中只有一条记录与之匹配，常见于主键或唯一索引扫描

   ![image-20200804103543783](https://img-blog.csdnimg.cn/img_convert/f7bd1ea1cb7dde026d9187fef1822e94.png)

4. ref：非唯一索引扫描，返回匹配某个单独值的所有行。本质上也是一种索引访问，它返回所有匹配某个单独值的行，然而，它可能会找到多个符合条件的行，所以他应该属于查找和扫描的混合体

   ![image-20200804103959011](https://img-blog.csdnimg.cn/img_convert/9f2f98d605f4504a2032a71c9c629d76.png)

5. **range**：只检索给定范围的行，使用一个索引来选择行。key列显示使用了哪个索引一般就是在你的where语句中出现了`between`、`<`、`>`、`in`等的查询这种范围扫描索引扫描比全表扫描要好，因为他**只需要开始索引的某一点，而结束于另一点，不用扫描全部索引**

   ![image-20200804104130086](https://img-blog.csdnimg.cn/img_convert/77d5536b196806ce02c676f0a16c79b9.png)

6. **index**：Full Index Scan，index与ALL区别为index类型**只遍历索引树**。这通常比ALL快，因为索引文件通常比数据文件小。（也就是说**虽然all和index都是读全表，但index是从索引中读取的，而all是从硬盘数据库文件中读的**）

   ![image-20200804104254208](https://img-blog.csdnimg.cn/img_convert/7d4b75a446fdeff53d3ccb96ce7ae9f4.png)

7. **all**：FullTable Scan，**将遍历全表以找到匹配的行（全表扫描）**

   ![image-20200804104358142](https://img-blog.csdnimg.cn/img_convert/a9cc0f39661fb313c5d2c91f35d627bd.png)

8. 备注：一般来说，得保证查询只是达到range级别，最好达到ref

> **possible_keys**

1. 显示**可能**应用在这张表中的索引，一个或多个
2. 若查询涉及的字段上存在索引，则该索引将被列出，但不一定被查询实际使用

> **key**

1. **实际**使用的索引，如果为null，则没有使用索引

2. **若查询中使用了覆盖索引，则该索引仅出现在key列表中**

   ![image-20200804105225100](https://img-blog.csdnimg.cn/img_convert/740cb69c1c775b87c413768f5dcf1679.png)

> **key_len**

1. **表示索引中使用的字节数**，可通过该列计算查询中使用的索引的长度。在不损失精确性的情况下，长度越短越好
2. key_len显示的值为**索引最大可能长度**，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的

![image-20200804105833936](https://img-blog.csdnimg.cn/img_convert/2ad15fda5cf87d06ad929a9ea9d0a4f1.png)

> **ref**

1. **显示索引哪一列被使用了**，如果可能的话，最好是一个常数。哪些列或常量被用于查找索引列上的值
2. 由key_len可知t1表的索引idx_col1_col2被充分使用，t1表的col1匹配t2表的col1，t1表的col2匹配了一个常量，即’ac’

![image-20200804110346982](https://img-blog.csdnimg.cn/img_convert/28aaffa8fcce6c311fc1fdc379d04f3e.png)

> **rows**

根据表统计信息及索引选用情况，大致估算出找到所需的记录所需要读取的行数

![image-20200804111249713](https://img-blog.csdnimg.cn/img_convert/6f3280416e87d914b8693a5a9f0be0dd.png)

> **Extra：包含不适合在其他列中显示但十分重要的额外信息**

1. **Using filesort（文件排序）：**

   - MySQL中无法利用索引完成排序操作成为“文件排序”
   - 说明mysql会对数据使用一个外部的索引排序，而不是按照表内的索引顺序进行读取
   - **出现 Using filesort 不好（九死一生），需要尽快优化 SQL**
   - 示例中第一个查询只使用了 col1 和 col3，原有索引派不上用场，所以进行了外部文件排序
   - 示例中第二个查询使用了 col1、col2 和 col3，原有索引派上用场，无需进行文件排序

   ![image-20200804111738931](https://img-blog.csdnimg.cn/img_convert/5a8058b72155c2139f787c7ca850a5e7.png)

2. **Using temporary（创建临时表）：**

   - 使用了临时表保存中间结果，MySQL在对查询结果排序时使用临时表。常见于排序 order by 和分组查询 group by
   - **出现 Using temporary 超级不好（十死无生），需要立即优化 SQL**
   - 示例中第一个查询只使用了 col1，原有索引派不上用场，所以创建了临时表进行分组
   - 示例中第二个查询使用了 col1、col2，原有索引派上用场，无需创建临时表

   ![image-20200804112558915](https://img-blog.csdnimg.cn/img_convert/d882d26bfee9d22de19de32f258054bf.png)

3. **Using index（覆盖索引）：**

   - 表示相应的select操作中使用了覆盖索引（Coveing Index），避免访问了表的数据行，效率不错！

   - 如果同时出现using where，表明索引被用来执行索引键值的查找

   - 如果没有同时出现using where，表明索引用来读取数据而非执行查找动作

     ![image-20200804113450147](https://img-blog.csdnimg.cn/img_convert/47c3005a7d32a29e79a9136d4c6e0470.png)

   - **覆盖索引（Covering Index），也说为索引覆盖**

     - 理解方式一：就是**select的数据列只用从索引中就能够取得，不必读取数据行**，MySQL可以**利用索引返回select列表中的字段，而不必根据索引再次读取数据文件**，换句话说查询列要被所建的索引覆盖。
     - 理解方式二：索引是高效找到行的一个方法，但是一般数据库也能使用索引找到一个列的数据，因此它不必读取整个行。毕竟索引叶子节点存储了它们索引的数据；当能通过读取索引就可以得到想要的数据，那就不需要读取行了。一个索引包含了（或覆盖了）满足查询结果的数据就叫做覆盖索引。
     - 注意：**如果要使用覆盖索引，一定要注意select列表中只取出需要的列，不可select \*** ，因为如果将所有字段一起做索引会导致索引文件过大，查询性能下降。

4. **Using where**：表明使用了where过滤

5. **Using join buffer**：表明使用了连接缓存

6. impossible where：where子句的值总是false，不能用来获取任何元组

   ![image-20200804113946144](https://img-blog.csdnimg.cn/img_convert/87a860501fa146add8f7735bdde108dd.png)

7. select tables optimized away：在没有GROUPBY子句的情况下，基于索引优化MIN/MAX操作或者对于MyISAM存储引擎优化`COUNT(*)`操作，不必等到执行阶段再进行计算，查询执行计划生成的阶段即完成优化。

8. distinct：优化distinct，在找到第一匹配的元组后即停止找同样值的工作

> **Explain 热身 Case**

1. 第一行（执行顺序4）：id列为1，表示是union里的第一个select，select_type列的primary表示该查询为外层查询，table列被标记为`<derived3>`，表示查询结果来自一个衍生表，其中derived3中3代表该查询衍生自第三个select查询，即id为3的select。【`select d1.name ...`】
2. 第二行（执行顺序2）：id为3，是整个查询中第三个select的一部分。因查询包含在from中，所以为derived。【`select id, name from t1 where other_column= ' '`】
3. 第三行（执行顺序3）：select列表中的子查询select_type为subquery，为整个查询中的第二个select。【`select id from t3`】
4. 第四行（执行顺序1）：select_type为union，说明第四个select是union里的第二个select，最先执行【`select name, id from t2`】
5. 第五行（执行顺序5）：代表从union的临时表中读取行的阶段，table列的<union1, 4>表示用第一个和第四个select的结果进行union操作。【两个结果进行uinion操作】

![image-20200804114203012](https://img-blog.csdnimg.cn/img_convert/fe64ec372bf9043f92fe91a28176660a.png)

## 2.5. 、索引优化

### 2.5.1. 、单表索引优化

> **单表索引优化分析**

**创建表**

- 建表 SQL

```mysql
CREATE TABLE IF NOT EXISTS article(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	author_id INT(10) UNSIGNED NOT NULL,
	category_id INT(10) UNSIGNED NOT NULL,
	views INT(10) UNSIGNED NOT NULL,
	comments INT(10) UNSIGNED NOT NULL,
	title VARCHAR(255) NOT NULL,
	content TEXT NOT NULL
);

INSERT INTO article(author_id,category_id,views,comments,title,content)
VALUES
(1,1,1,1,'1','1'),
(2,2,2,2,'2','2'),
(1,1,3,3,'3','3');
```

- 表中的测试数据

```
mysql> SELECT * FROM article;
+----+-----------+-------------+-------+----------+-------+---------+
| id | author_id | category_id | views | comments | title | content |
+----+-----------+-------------+-------+----------+-------+---------+
|  1 |         1 |           1 |     1 |        1 | 1     | 1       |
|  2 |         2 |           2 |     2 |        2 | 2     | 2       |
|  3 |         1 |           1 |     3 |        3 | 3     | 3       |
+----+-----------+-------------+-------+----------+-------+---------+
3 rows in set (0.00 sec)
```

------

**查询案例**

- 查询category_id为1且comments 大于1的情况下，views最多的article_id。

```
mysql> SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
+----+-----------+
| id | author_id |
+----+-----------+
|  3 |         1 |
+----+-----------+
1 row in set (0.00 sec)
```

- 此时 article 表中只有一个主键索引

```
mysql> SHOW INDEX FROM article;
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table   | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| article |          0 | PRIMARY  |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
1 row in set (0.00 sec)
```

- 使用 explain 分析 SQL 语句的执行效率：`EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;`

```
mysql> EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
+----+-------------+---------+------+---------------+------+---------+------+------+-----------------------------+
| id | select_type | table   | type | possible_keys | key  | key_len | ref  | rows | Extra                       |
+----+-------------+---------+------+---------------+------+---------+------+------+-----------------------------+
|  1 | SIMPLE      | article | ALL  | NULL          | NULL | NULL    | NULL |    3 | Using where; Using filesort |
+----+-------------+---------+------+---------------+------+---------+------+------+-----------------------------+
1 row in set (0.00 sec)
```

- 结论：
  - 很显然，type是ALL，即最坏的情况。
  - Extra 里还出现了Using filesort，也是最坏的情况。
  - 优化是必须的。

------

**开始优化：新建索引**

- 创建索引的 SQL 命令

```mysql
# ALTER TABLE article ADD INDEX idx_article_ccv('category_id', 'comments', 'views'); 
create index idx_article_ccv on article(category_id, comments, views);
```

- 在 category_id 列、comments 列和 views 列上建立联合索引

```
mysql> create index idx_article_ccv on article(category_id, comments, views);
Query OK, 0 rows affected (0.01 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM article;
+---------+------------+-----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table   | Non_unique | Key_name        | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+---------+------------+-----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| article |          0 | PRIMARY         |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| article |          1 | idx_article_ccv |            1 | category_id | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| article |          1 | idx_article_ccv |            2 | comments    | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| article |          1 | idx_article_ccv |            3 | views       | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+---------+------------+-----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
4 rows in set (0.00 sec)
```

- 再次执行查询：type变成了range，这是可以忍受的。但是extra里使用Using filesort仍是无法接受的。

```
mysql> EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
+----+-------------+---------+-------+-----------------+-----------------+---------+------+------+---------------------------------------+
| id | select_type | table   | type  | possible_keys   | key             | key_len | ref  | rows | Extra                                 |
+----+-------------+---------+-------+-----------------+-----------------+---------+------+------+---------------------------------------+
|  1 | SIMPLE      | article | range | idx_article_ccv | idx_article_ccv | 8       | NULL |    1 | Using index condition; Using filesort |
+----+-------------+---------+-------+-----------------+-----------------+---------+------+------+---------------------------------------+
1 row in set (0.00 sec)
```

- 分析：
  - 但是我们已经建立了索引，为啥没用呢？
  - 这是因为按照B+Tree索引的工作原理，先排序 category_id，如果遇到相同的 category_id 则再排序comments，如果遇到相同的 comments 则再排序 views。
  - 当comments字段在联合索引里处于中间位置时，**因为comments>1条件是一个范围值**（所谓 range），MySQL 无法利用索引再对后面的views部分进行检索，即 **range 类型查询字段后面的索引无效**。
- 将查询条件中的 `comments > 1` 改为 `comments = 1` ，发现 Use filesort 神奇地消失了，从这点可以验证：范围后的索引会导致索引失效

```
mysql> EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments = 1 ORDER BY views DESC LIMIT 1;
+----+-------------+---------+------+-----------------+-----------------+---------+-------------+------+-------------+
| id | select_type | table   | type | possible_keys   | key             | key_len | ref         | rows | Extra       |
+----+-------------+---------+------+-----------------+-----------------+---------+-------------+------+-------------+
|  1 | SIMPLE      | article | ref  | idx_article_ccv | idx_article_ccv | 8       | const,const |    1 | Using where |
+----+-------------+---------+------+-----------------+-----------------+---------+-------------+------+-------------+
1 row in set (0.00 sec)
```

------

**删除索引**

- 删除索引的 SQL 指令

```mysql
DROP INDEX idx_article_ccv ON article;
```

- 删除刚才创建的 idx_article_ccv 索引

```
mysql> DROP INDEX idx_article_ccv ON article;
Query OK, 0 rows affected (0.00 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM article;
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table   | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| article |          0 | PRIMARY  |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
1 row in set (0.00 sec)
```

------

**再次创建索引**

- 创建索引的 SQL 指令

```mysql
# ALTER TABLE article ADD INDEX idx_article_ccv('category_id',  'views'); 
create index idx_article_ccv on article(category_id, views);
```

- 由于 range 后（`comments > 1`）的索引会失效，这次我们建立索引时，直接抛弃 comments 列，先利用 category_id 和 views 的联合索引查询所需要的数据，再从其中取出 `comments > 1` 的数据（我觉着应该是这样的）

```
mysql> create index idx_article_ccv on article(category_id, views);
Query OK, 0 rows affected (0.30 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM article;
+---------+------------+-----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table   | Non_unique | Key_name        | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+---------+------------+-----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| article |          0 | PRIMARY         |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| article |          1 | idx_article_ccv |            1 | category_id | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| article |          1 | idx_article_ccv |            2 | views       | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+---------+------------+-----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
3 rows in set (0.00 sec)
```

- 再次执行查询：可以看到，type变为了ref，Extra中的Using filesort也消失了，结果非常理想

```
ysql> EXPLAIN SELECT id, author_id FROM article WHERE category_id = 1 AND comments > 1 ORDER BY views DESC LIMIT 1;
+----+-------------+---------+------+-----------------+-----------------+---------+-------+------+-------------+
| id | select_type | table   | type | possible_keys   | key             | key_len | ref   | rows | Extra       |
+----+-------------+---------+------+-----------------+-----------------+---------+-------+------+-------------+
|  1 | SIMPLE      | article | ref  | idx_article_ccv | idx_article_ccv | 4       | const |    2 | Using where |
+----+-------------+---------+------+-----------------+-----------------+---------+-------+------+-------------+
1 row in set (0.00 sec)
```

- 为了不影响之后的测试，删除该表的 idx_article_ccv 索引

```
mysql> DROP INDEX idx_article_ccv ON article;
Query OK, 0 rows affected (0.05 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM article;
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table   | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| article |          0 | PRIMARY  |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+---------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
1 row in set (0.01 sec)
```

### 2.5.2. 、两表索引优化

> **两表索引优化分析：主外键**

**创建表**

- 建表 SQL

```mysql
CREATE TABLE IF NOT EXISTS class(
	id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	card INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS book(
	bookid INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	card INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY(bookid)
);

INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO class(card) VALUES(FLOOR(1+(RAND()*20)));

INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO book(card) VALUES(FLOOR(1+(RAND()*20)));
```

- class 表中的测试数据

```
mysql> select * from class;
+----+------+
| id | card |
+----+------+
|  1 |   12 |
|  2 |   13 |
|  3 |   12 |
|  4 |   17 |
|  5 |   11 |
|  6 |    3 |
|  7 |    1 |
|  8 |   16 |
|  9 |   17 |
| 10 |   16 |
| 11 |    9 |
| 12 |   17 |
| 13 |   18 |
| 14 |   16 |
| 15 |    7 |
| 16 |    8 |
| 17 |   19 |
| 18 |    9 |
| 19 |    6 |
| 20 |    5 |
| 21 |    6 |
+----+------+
21 rows in set (0.00 sec)
```

- book 表中的测试数据

```
mysql> select * from book;
+--------+------+
| bookid | card |
+--------+------+
|      1 |   16 |
|      2 |    1 |
|      3 |   17 |
|      4 |    3 |
|      5 |   20 |
|      6 |   12 |
|      7 |   18 |
|      8 |   13 |
|      9 |   13 |
|     10 |    4 |
|     11 |    1 |
|     12 |   13 |
|     13 |   20 |
|     14 |   20 |
|     15 |    1 |
|     16 |    2 |
|     17 |    9 |
|     18 |   16 |
|     19 |   14 |
|     20 |    2 |
+--------+------+
20 rows in set (0.00 sec)
```

------

**查询案例**

- 实现两表的连接，连接条件是 class.card = book.card

```
mysql> SELECT * FROM class LEFT JOIN book ON class.card = book.card;
+----+------+--------+------+
| id | card | bookid | card |
+----+------+--------+------+
|  1 |   12 |      6 |   12 |
|  2 |   13 |      8 |   13 |
|  2 |   13 |      9 |   13 |
|  2 |   13 |     12 |   13 |
|  3 |   12 |      6 |   12 |
|  4 |   17 |      3 |   17 |
|  5 |   11 |   NULL | NULL |
|  6 |    3 |      4 |    3 |
|  7 |    1 |      2 |    1 |
|  7 |    1 |     11 |    1 |
|  7 |    1 |     15 |    1 |
|  8 |   16 |      1 |   16 |
|  8 |   16 |     18 |   16 |
|  9 |   17 |      3 |   17 |
| 10 |   16 |      1 |   16 |
| 10 |   16 |     18 |   16 |
| 11 |    9 |     17 |    9 |
| 12 |   17 |      3 |   17 |
| 13 |   18 |      7 |   18 |
| 14 |   16 |      1 |   16 |
| 14 |   16 |     18 |   16 |
| 15 |    7 |   NULL | NULL |
| 16 |    8 |   NULL | NULL |
| 17 |   19 |   NULL | NULL |
| 18 |    9 |     17 |    9 |
| 19 |    6 |   NULL | NULL |
| 20 |    5 |   NULL | NULL |
| 21 |    6 |   NULL | NULL |
+----+------+--------+------+
28 rows in set (0.00 sec)
```

- 使用 explain 分析 SQL 语句的性能，可以看到：驱动表是左表 class 表

```
mysql> EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;
+----+-------------+-------+------+---------------+------+---------+------+------+----------------------------------------------------+
| id | select_type | table | type | possible_keys | key  | key_len | ref  | rows | Extra                                              |
+----+-------------+-------+------+---------------+------+---------+------+------+----------------------------------------------------+
|  1 | SIMPLE      | class | ALL  | NULL          | NULL | NULL    | NULL |   21 | NULL                                               |
|  1 | SIMPLE      | book  | ALL  | NULL          | NULL | NULL    | NULL |   20 | Using where; Using join buffer (Block Nested Loop) |
+----+-------------+-------+------+---------------+------+---------+------+------+----------------------------------------------------+
2 rows in set (0.00 sec)
```

- 结论：
  - **type 有 All ，rows 为表中数据总行数，说明 class 和 book 进行了全表检索**
  - 即每次 class 表对 book 表进行左外连接时，都需要在 book 表中进行一次全表检索

------

**添加索引：在右表添加索引**

- 添加索引的 SQL 指令

```mysql
ALTER TABLE 'book' ADD INDEX Y ('card');
```

- 在 book 的 card 字段上添加索引

```
mysql> ALTER TABLE book ADD INDEX Y (card);
Query OK, 0 rows affected (0.30 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM book;
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| book  |          0 | PRIMARY  |            1 | bookid      | A         |          20 |     NULL | NULL   |      | BTREE      |         |               |
| book  |          1 | Y        |            1 | card        | A         |          20 |     NULL | NULL   |      | BTREE      |         |               |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
2 rows in set (0.00 sec)
```

- 测试结果：可以看到第二行的type变为了ref，rows也变成了优化比较明显。

```
mysql> EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;
+----+-------------+-------+------+---------------+------+---------+-----------------+------+-------------+
| id | select_type | table | type | possible_keys | key  | key_len | ref             | rows | Extra       |
+----+-------------+-------+------+---------------+------+---------+-----------------+------+-------------+
|  1 | SIMPLE      | class | ALL  | NULL          | NULL | NULL    | NULL            |   21 | NULL        |
|  1 | SIMPLE      | book  | ref  | Y             | Y    | 4       | db01.class.card |    1 | Using index |
+----+-------------+-------+------+---------------+------+---------+-----------------+------+-------------+
2 rows in set (0.00 sec)
```

- 分析：
  - 这是由左连接特性决定的。LEFT JOIN条件用于确定如何从右表搜索行，左边一定都有，所以右边是我们的关键点，一定需要建立索引。
  - **左表连接右表，则需要拿着左表的数据去右表里面查，索引需要在右表中建立索引**

------

**添加索引：在右表添加索引**

- 删除之前 book 表中的索引

```mysql
DROP INDEX Y ON book;
```

- 在 class 表的 card 字段上建立索引

```mysql
ALTER TABLE class ADD INDEX X(card);
```

- 再次执行左连接，凉凉~~~

```
mysql> EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card;
+----+-------------+-------+-------+---------------+------+---------+------+------+----------------------------------------------------+
| id | select_type | table | type  | possible_keys | key  | key_len | ref  | rows | Extra                                              |
+----+-------------+-------+-------+---------------+------+---------+------+------+----------------------------------------------------+
|  1 | SIMPLE      | class | index | NULL          | X    | 4       | NULL |   21 | Using index                                        |
|  1 | SIMPLE      | book  | ALL   | NULL          | NULL | NULL    | NULL |   20 | Using where; Using join buffer (Block Nested Loop) |
+----+-------------+-------+-------+---------------+------+---------+------+------+----------------------------------------------------+
2 rows in set (0.00 sec)
```

- 别怕，我们来执行右连接：可以看到第二行的type变为了ref，rows也变成了优化比较明显。

```
mysql> EXPLAIN SELECT * FROM class RIGHT JOIN book ON class.card = book.card;
+----+-------------+-------+------+---------------+------+---------+----------------+------+-------------+
| id | select_type | table | type | possible_keys | key  | key_len | ref            | rows | Extra       |
+----+-------------+-------+------+---------------+------+---------+----------------+------+-------------+
|  1 | SIMPLE      | book  | ALL  | NULL          | NULL | NULL    | NULL           |   20 | NULL        |
|  1 | SIMPLE      | class | ref  | X             | X    | 4       | db01.book.card |    1 | Using index |
+----+-------------+-------+------+---------------+------+---------+----------------+------+-------------+
2 rows in set (0.00 sec)
```

- 分析：
  - 这是因为RIGHT JOIN条件用于确定如何从左表搜索行，右边一定都有，所以左边是我们的关键点，一定需要建立索引。
  - class RIGHT JOIN book ：book 里面的数据一定存在于结果集中，我们需要拿着 book 表中的数据，去 class 表中搜索，所以索引需要建立在 class 表中
- 为了不影响之后的测试，删除该表的 idx_article_ccv 索引

```
mysql> DROP INDEX X ON class;
Query OK, 0 rows affected (0.04 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM class;
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| class |          0 | PRIMARY  |            1 | id          | A         |          21 |     NULL | NULL   |      | BTREE      |         |               |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
1 row in set (0.00 sec)
```

### 2.5.3. 、三表索引优化

> **三表索引优化分析**

**创建表**

- 建表 SQL

```mysql
CREATE TABLE IF NOT EXISTS phone(
	phoneid INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	card INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY(phoneid)
)ENGINE=INNODB;

INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
INSERT INTO phone(card) VALUES(FLOOR(1+(RAND()*20)));
```

- phone 表中的测试数据

```
mysql> select * from phone;
+---------+------+
| phoneid | card |
+---------+------+
|       1 |    7 |
|       2 |    7 |
|       3 |   13 |
|       4 |    6 |
|       5 |    8 |
|       6 |    4 |
|       7 |   16 |
|       8 |    4 |
|       9 |   15 |
|      10 |    1 |
|      11 |   20 |
|      12 |   18 |
|      13 |    9 |
|      14 |    9 |
|      15 |   20 |
|      16 |   11 |
|      17 |   15 |
|      18 |    3 |
|      19 |    8 |
|      20 |   10 |
+---------+------+
20 rows in set (0.00 sec)
```

------

**查询案例**

- 实现三表的连接查询：

```
mysql> SELECT * FROM class LEFT JOIN book ON class.card = book.card LEFT JOIN phone ON book.card = phone.card;
+----+------+--------+------+---------+------+
| id | card | bookid | card | phoneid | card |
+----+------+--------+------+---------+------+
|  2 |   13 |      8 |   13 |       3 |   13 |
|  2 |   13 |      9 |   13 |       3 |   13 |
|  2 |   13 |     12 |   13 |       3 |   13 |
|  8 |   16 |      1 |   16 |       7 |   16 |
| 10 |   16 |      1 |   16 |       7 |   16 |
| 14 |   16 |      1 |   16 |       7 |   16 |
|  8 |   16 |     18 |   16 |       7 |   16 |
| 10 |   16 |     18 |   16 |       7 |   16 |
| 14 |   16 |     18 |   16 |       7 |   16 |
|  7 |    1 |      2 |    1 |      10 |    1 |
|  7 |    1 |     11 |    1 |      10 |    1 |
|  7 |    1 |     15 |    1 |      10 |    1 |
| 13 |   18 |      7 |   18 |      12 |   18 |
| 11 |    9 |     17 |    9 |      13 |    9 |
| 18 |    9 |     17 |    9 |      13 |    9 |
| 11 |    9 |     17 |    9 |      14 |    9 |
| 18 |    9 |     17 |    9 |      14 |    9 |
|  6 |    3 |      4 |    3 |      18 |    3 |
|  4 |   17 |      3 |   17 |    NULL | NULL |
|  9 |   17 |      3 |   17 |    NULL | NULL |
| 12 |   17 |      3 |   17 |    NULL | NULL |
|  1 |   12 |      6 |   12 |    NULL | NULL |
|  3 |   12 |      6 |   12 |    NULL | NULL |
|  5 |   11 |   NULL | NULL |    NULL | NULL |
| 15 |    7 |   NULL | NULL |    NULL | NULL |
| 16 |    8 |   NULL | NULL |    NULL | NULL |
| 17 |   19 |   NULL | NULL |    NULL | NULL |
| 19 |    6 |   NULL | NULL |    NULL | NULL |
| 20 |    5 |   NULL | NULL |    NULL | NULL |
| 21 |    6 |   NULL | NULL |    NULL | NULL |
+----+------+--------+------+---------+------+
30 rows in set (0.00 sec)
```

- 使用 explain 分析 SQL 指令：

```
mysql> EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card = book.card LEFT JOIN phone ON book.card = phone.card;
+----+-------------+-------+------+---------------+------+---------+------+------+----------------------------------------------------+
| id | select_type | table | type | possible_keys | key  | key_len | ref  | rows | Extra                                              |
+----+-------------+-------+------+---------------+------+---------+------+------+----------------------------------------------------+
|  1 | SIMPLE      | class | ALL  | NULL          | NULL | NULL    | NULL |   21 | NULL                                               |
|  1 | SIMPLE      | book  | ALL  | NULL          | NULL | NULL    | NULL |   20 | Using where; Using join buffer (Block Nested Loop) |
|  1 | SIMPLE      | phone | ALL  | NULL          | NULL | NULL    | NULL |   20 | Using where; Using join buffer (Block Nested Loop) |
+----+-------------+-------+------+---------------+------+---------+------+------+----------------------------------------------------+
3 rows in set (0.00 sec)
```

- 结论：
  - **type 有All ，rows 为表数据总行数，说明 class、 book 和 phone 表都进行了全表检索**
  - Extra 中 Using join buffer ，表明连接过程中使用了 join 缓冲区

------

**创建索引**

- 创建索引的 SQL 语句

```
ALTER TABLE book ADD INDEX Y (card);
ALTER TABLE phone ADD INDEX Z (card);
```

- **进行 LEFT JOIN ，永远都在右表的字段上建立索引**

```
mysql> ALTER TABLE book ADD INDEX Y (card);
Query OK, 0 rows affected (0.06 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM book;
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| book  |          0 | PRIMARY  |            1 | bookid      | A         |          20 |     NULL | NULL   |      | BTREE      |         |               |
| book  |          1 | Y        |            1 | card        | A         |          20 |     NULL | NULL   |      | BTREE      |         |               |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
2 rows in set (0.00 sec)

mysql> ALTER TABLE phone ADD INDEX Z (card);
Query OK, 0 rows affected (0.05 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM phone;
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table | Non_unique | Key_name | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| phone |          0 | PRIMARY  |            1 | phoneid     | A         |          20 |     NULL | NULL   |      | BTREE      |         |               |
| phone |          1 | Z        |            1 | card        | A         |          20 |     NULL | NULL   |      | BTREE      |         |               |
+-------+------------+----------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
2 rows in set (0.00 sec)
```

- 执行查询：后2行的type都是ref，且总rows优化很好，效果不错。因此索引最好设置在需要经常查询的字段中。

```
mysql> EXPLAIN SELECT * FROM class LEFT JOIN book ON class.card=book.card LEFT JOIN phone ON book.card = phone.card;
+----+-------------+-------+------+---------------+------+---------+-----------------+------+-------------+
| id | select_type | table | type | possible_keys | key  | key_len | ref             | rows | Extra       |
+----+-------------+-------+------+---------------+------+---------+-----------------+------+-------------+
|  1 | SIMPLE      | class | ALL  | NULL          | NULL | NULL    | NULL            |   21 | NULL        |
|  1 | SIMPLE      | book  | ref  | Y             | Y    | 4       | db01.class.card |    1 | Using index |
|  1 | SIMPLE      | phone | ref  | Z             | Z    | 4       | db01.book.card  |    1 | Using index |
+----+-------------+-------+------+---------------+------+---------+-----------------+------+-------------+
3 rows in set (0.00 sec)
```

> **Join 语句优化的结论**

**将 left join 看作是两层嵌套 for 循环**

1. 尽可能减少Join语句中的NestedLoop的循环总次数；
2. **永远用小结果集驱动大的结果集（在大结果集中建立索引，在小结果集中遍历全表）；**
3. 优先优化NestedLoop的内层循环；
4. 保证Join语句中被驱动表上Join条件字段已经被索引；
5. 当无法保证被驱动表的Join条件字段被索引且内存资源充足的前提下，不要太吝惜JoinBuffer的设置；

------

**我的理解**

1. 使用小表驱动大表，这就相当于外层 for 循环的次数少，内层 for 循环的次数多
2. 然后我们在大表中建立了索引，这样内层 for 循环的效率明显提高
3. 综上，使用小表驱动大表，在大表中建立了索引

## 2.6. 、索引失效

> **索引失效（应该避免）**

**创建表**

- 建表 SQL

```mysql
CREATE TABLE staffs(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(24)NOT NULL DEFAULT'' COMMENT'姓名',
	`age` INT NOT NULL DEFAULT 0 COMMENT'年龄',
	`pos` VARCHAR(20) NOT NULL DEFAULT'' COMMENT'职位',
	`add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'入职时间'
)CHARSET utf8 COMMENT'员工记录表';

INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('z3',22,'manager',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('July',23,'dev',NOW());
INSERT INTO staffs(`name`,`age`,`pos`,`add_time`) VALUES('2000',23,'dev',NOW());

ALTER TABLE staffs ADD INDEX index_staffs_nameAgePos(`name`,`age`,`pos`);
```

- staffs 表中的测试数据

```
mysql> select * from staffs;
+----+------+-----+---------+---------------------+
| id | name | age | pos     | add_time            |
+----+------+-----+---------+---------------------+
|  1 | z3   |  22 | manager | 2020-08-04 14:42:33 |
|  2 | July |  23 | dev     | 2020-08-04 14:42:33 |
|  3 | 2000 |  23 | dev     | 2020-08-04 14:42:33 |
+----+------+-----+---------+---------------------+
3 rows in set (0.00 sec)
```

- staffs 表中的复合索引：name、age、pos

```
mysql> SHOW INDEX FROM staffs;
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table  | Non_unique | Key_name                | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| staffs |          0 | PRIMARY                 |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            1 | name        | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            2 | age         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            3 | pos         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
4 rows in set (0.00 sec)
```

### 2.6.1. 、索引失效准则

> **索引失效判断准则**

1. 全值匹配我最爱
2. 最佳左前缀法则：如果索引了多例，要遵守最左前缀法则。指的是查询从索引的最左前列开始并且不跳过索引中的列。
3. 不在索引列上做任何操作（计算、函数、（自动or手动）类型转换），会导致索引失效而转向全表扫描
4. 存储引擎不能使用索引中范围条件右边的列
5. 尽量使用覆盖索引（只访问索引的查询（索引列和查询列一致）），减少`select *`
6. mysql在使用不等于（!=或者<>）的时候无法使用索引会导致全表扫描
7. `is null`，`is not null` 也无法使用索引（早期版本不能走索引，后续版本应该优化过，可以走索引）
8. like以通配符开头（’%abc…’）mysql索引失效会变成全表扫描操作
9. 字符串不加单引号索引失效
10. 少用or，用它连接时会索引失效

> **最佳左匹配法则：带头大哥不能死，中间兄弟不能断**

- 只有带头大哥 name 时
  - key = index_staffs_nameAgePos 表明索引生效
  - ref = const ：这个常量就是查询时的 ‘July’ 字符串常量

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref   | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 74      | const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
1 row in set (0.00 sec)
```

- 带头大哥 name 带上小弟 age
  - key = index_staffs_nameAgePos 表明索引生效
  - ref = const,const：两个常量分别为 ‘July’ 和 23

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July'AND age = 23;
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref         | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 78      | const,const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------+------+-----------------------+
1 row in set (0.00 sec)
```

- 带头大哥 name 带上小弟 age ，小弟 age 带上小小弟 pos
  - key = index_staffs_nameAgePos 表明索引生效
  - ref = const,const,const ：三个常量分别为 ‘July’、23 和 ‘dev’

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July'AND age = 23 AND pos = 'dev';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref               | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 140     | const,const,const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+-----------------------+
1 row in set (0.00 sec)
```

- 带头大哥 name 挂了
  - key = NULL 说明索引失效
  - ref = null 表示 ref 也失效

```
mysql> EXPLAIN SELECT * FROM staffs WHERE age = 23 AND pos = 'dev';
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | NULL          | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

- 带头大哥 name 没挂，小弟 age 跑了
  - key = index_staffs_nameAgePos 说明索引没有失效
  - ref = const 表明只使用了一个常量，即第二个常量（pos = ‘dev’）没有生效

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July'AND pos = 'dev';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref   | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 74      | const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
1 row in set (0.00 sec)
```

> **在索引列上进行计算，会导致索引失效，进而转向全表扫描**

- 不对带头大哥 name 进行任何操作：key = index_staffs_nameAgePos 表明索引生效

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref   | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 74      | const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
1 row in set (0.00 sec)
```

- 对带头大哥 name 进行操作：使用 LEFT 函数截取子串
  - key = NULL 表明索引生效
  - type = ALL 表明进行了全表扫描

```
mysql> EXPLAIN SELECT * FROM staffs WHERE LEFT(name,4) = 'July';
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | NULL          | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

> **范围之后全失效**

- 精确匹配
  - type = ref 表示非唯一索引扫描，SQL 语句将返回匹配某个单独值的所有行。
  - key_len = 140 表明表示索引中使用的字节数

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July'AND age = 23 AND pos = 'dev';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref               | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 140     | const,const,const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+-----------------------+
1 row in set (0.00 sec)
```

- 将 age 改为范围匹配
  - type = range 表示范围扫描
  - key = index_staffs_nameAgePos 表示索引并没有失效
  - key_len = 78 ，ref = NULL 均表明范围搜索使其后面的索引均失效

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July'AND age > 23 AND pos = 'dev';
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys           | key                     | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | staffs | range | index_staffs_nameAgePos | index_staffs_nameAgePos | 78      | NULL |    1 | Using index condition |
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

> **尽量使用覆盖索引（只访问索引的查询（索引列和查询列一致）），减少 select \***

- `SELECT *` 的写法

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name = 'July'AND age > 23 AND pos = 'dev';
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys           | key                     | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | staffs | range | index_staffs_nameAgePos | index_staffs_nameAgePos | 78      | NULL |    1 | Using index condition |
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

- 覆盖索引的写法：Extra = Using where; Using index ，Using index 表示使用索引列进行查询，将大大提高查询的效率

```
mysql> EXPLAIN SELECT name, age, pos FROM staffs WHERE name = 'July'AND age = 23 AND pos = 'dev';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+--------------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref               | rows | Extra                    |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+--------------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 140     | const,const,const |    1 | Using where; Using index |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------------------+------+--------------------------+
1 row in set (0.00 sec)
```

- 覆盖索引中包含 range 条件：type = ref 并且 Extra = Using where; Using index ，虽然在查询条件中使用了 范围搜索，但是由于我们只需要查找索引列，所以无需进行全表扫描

```
mysql> EXPLAIN SELECT name, age, pos FROM staffs WHERE name = 'July'AND age > 23 AND pos = 'dev';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+--------------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref   | rows | Extra                    |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+--------------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 74      | const |    1 | Using where; Using index |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+--------------------------+
1 row in set (0.00 sec)
```

> **mysql在使用不等于（!=或者<>）的时候无法使用索引会导致全表扫描**

- 在使用 != 会 <> 时会导致索引失效：
  - key = null 表示索引失效
  - rows = 3 表示进行了全表扫描

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name != 'July';
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys           | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | index_staffs_nameAgePos | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT * FROM staffs WHERE name <> 'July';
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys           | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | index_staffs_nameAgePos | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

> **is null，is not null 也无法使用索引**

- is null，is not null 会导致索引失效：key = null 表示索引失效

```
ysql> EXPLAIN SELECT * FROM staffs WHERE name is null;
+----+-------------+-------+------+---------------+------+---------+------+------+------------------+
| id | select_type | table | type | possible_keys | key  | key_len | ref  | rows | Extra            |
+----+-------------+-------+------+---------------+------+---------+------+------+------------------+
|  1 | SIMPLE      | NULL  | NULL | NULL          | NULL | NULL    | NULL | NULL | Impossible WHERE |
+----+-------------+-------+------+---------------+------+---------+------+------+------------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT * FROM staffs WHERE name is not null;
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys           | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | index_staffs_nameAgePos | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

> **like % 写最右**

- staffs 表的索引关系

```
mysql> SHOW INDEX from staffs;
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table  | Non_unique | Key_name                | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| staffs |          0 | PRIMARY                 |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            1 | name        | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            2 | age         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            3 | pos         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
4 rows in set (0.00 sec)
```

- like % 写在左边的情况
  - type = All ，rows = 3 表示进行了全表扫描
  - key = null 表示索引失效

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name like '%July';
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | NULL          | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT * FROM staffs WHERE name like '%July%';
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | NULL          | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+---------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

- like % 写在右边的情况：key = index_staffs_nameAgePos 表示索引未失效

```
mysql> EXPLAIN SELECT * FROM staffs WHERE name like 'July%';
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys           | key                     | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | staffs | range | index_staffs_nameAgePos | index_staffs_nameAgePos | 74      | NULL |    1 | Using index condition |
+----+-------------+--------+-------+-------------------------+-------------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

> **解决【like ‘%str%’ 】索引失效的问题：覆盖索引**

**创建表**

- 建表 SQL

```mysql
CREATE TABLE `tbl_user`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) DEFAULT NULL,
	`age`INT(11) DEFAULT NULL,
	`email` VARCHAR(20) DEFAULT NULL,
	PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO tbl_user(`name`,`age`,`email`)VALUES('1aa1',21,'a@163.com');
INSERT INTO tbl_user(`name`,`age`,`email`)VALUES('2bb2',23,'b@163.com');
INSERT INTO tbl_user(`name`,`age`,`email`)VALUES('3cc3',24,'c@163.com');
INSERT INTO tbl_user(`name`,`age`,`email`)VALUES('4dd4',26,'d@163.com');
```

- tbl_user 表中的测试数据

```
mysql> select * from tbl_user;
+----+------+------+-----------+
| id | name | age  | email     |
+----+------+------+-----------+
|  1 | 1aa1 |   21 | a@163.com |
|  2 | 2bb2 |   23 | b@163.com |
|  3 | 3cc3 |   24 | c@163.com |
|  4 | 4dd4 |   26 | d@163.com |
+----+------+------+-----------+
4 rows in set (0.00 sec)
```

------

创建索引

- 创建索引的 SQL 指令

```mysql
CREATE INDEX idx_user_nameAge ON tbl_user(name, age);
```

- 在 tbl_user 表的 name 字段和 age 字段创建联合索引

```
mysql> CREATE INDEX idx_user_nameAge ON tbl_user(name, age);
Query OK, 0 rows affected (0.05 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SHOW INDEX FROM tbl_user;
+----------+------------+------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table    | Non_unique | Key_name         | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+----------+------------+------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| tbl_user |          0 | PRIMARY          |            1 | id          | A         |           4 |     NULL | NULL   |      | BTREE      |         |               |
| tbl_user |          1 | idx_user_nameAge |            1 | name        | A         |           4 |     NULL | NULL   | YES  | BTREE      |         |               |
| tbl_user |          1 | idx_user_nameAge |            2 | age         | A         |           4 |     NULL | NULL   | YES  | BTREE      |         |               |
+----------+------------+------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
3 rows in set (0.00 sec)
```

------

**测试覆盖索引**

- 如下 SQL 的索引均不会失效：
  - 只要查询的字段能和覆盖索引扯得上关系，并且没有多余字段，覆盖索引就不会失效
  - 但我就想不通了，id 扯得上啥关系。。。

```mysql
EXPLAIN SELECT name, age FROM tbl_user WHERE NAME LIKE '%aa%';

EXPLAIN SELECT name FROM tbl_user WHERE NAME LIKE '%aa%';
EXPLAIN SELECT age FROM tbl_user WHERE NAME LIKE '%aa%';

EXPLAIN SELECT id FROM tbl_user WHERE NAME LIKE '%aa%';
EXPLAIN SELECT id, name FROM tbl_user WHERE NAME LIKE '%aa%';
EXPLAIN SELECT id, age FROM tbl_user WHERE NAME LIKE '%aa%';
EXPLAIN SELECT id, name, age FROM tbl_user WHERE NAME LIKE '%aa%';
mysql> EXPLAIN SELECT id FROM tbl_user WHERE NAME LIKE '%aa%';
+----+-------------+----------+-------+---------------+------------------+---------+------+------+--------------------------+
| id | select_type | table    | type  | possible_keys | key              | key_len | ref  | rows | Extra                    |
+----+-------------+----------+-------+---------------+------------------+---------+------+------+--------------------------+
|  1 | SIMPLE      | tbl_user | index | NULL          | idx_user_nameAge | 68      | NULL |    4 | Using where; Using index |
+----+-------------+----------+-------+---------------+------------------+---------+------+------+--------------------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT name, age FROM tbl_user WHERE NAME LIKE '%aa%';
+----+-------------+----------+-------+---------------+------------------+---------+------+------+--------------------------+
| id | select_type | table    | type  | possible_keys | key              | key_len | ref  | rows | Extra                    |
+----+-------------+----------+-------+---------------+------------------+---------+------+------+--------------------------+
|  1 | SIMPLE      | tbl_user | index | NULL          | idx_user_nameAge | 68      | NULL |    4 | Using where; Using index |
+----+-------------+----------+-------+---------------+------------------+---------+------+------+--------------------------+
1 row in set (0.00 sec)
```

- 如下 SQL 的索引均会失效：但凡有多余字段，覆盖索引就会失效

```mysql
EXPLAIN SELECT * FROM tbl_user WHERE NAME LIKE '%aa%';
EXPLAIN SELECT id, name, age, email FROM tbl_user WHERE NAME LIKE '%aa%';
mysql> EXPLAIN SELECT * FROM tbl_user WHERE NAME LIKE '%aa%';
+----+-------------+----------+------+---------------+------+---------+------+------+-------------+
| id | select_type | table    | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+----+-------------+----------+------+---------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | tbl_user | ALL  | NULL          | NULL | NULL    | NULL |    4 | Using where |
+----+-------------+----------+------+---------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT id, name, age, email FROM tbl_user WHERE NAME LIKE '%aa%';
+----+-------------+----------+------+---------------+------+---------+------+------+-------------+
| id | select_type | table    | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+----+-------------+----------+------+---------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | tbl_user | ALL  | NULL          | NULL | NULL    | NULL |    4 | Using where |
+----+-------------+----------+------+---------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

> **字符串不加单引号索引失效**

- 正常操作，索引没有失效

```
mysql> SHOW INDEX FROM staffs;
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table  | Non_unique | Key_name                | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| staffs |          0 | PRIMARY                 |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            1 | name        | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            2 | age         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            3 | pos         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
4 rows in set (0.00 sec)

mysql> explain select * from staffs where name='2000';
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
| id | select_type | table  | type | possible_keys           | key                     | key_len | ref   | rows | Extra                 |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
|  1 | SIMPLE      | staffs | ref  | index_staffs_nameAgePos | index_staffs_nameAgePos | 74      | const |    1 | Using index condition |
+----+-------------+--------+------+-------------------------+-------------------------+---------+-------+------+-----------------------+
1 row in set (0.00 sec)
```

- 如果字符串忘记写 ‘’ ，那么 mysql 会为我们进行隐式的类型转换，但凡进行了类型转换，索引都会失效

```
mysql> explain select * from staffs where name=2000;
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys           | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | index_staffs_nameAgePos | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

> **少用or，用它连接时会索引失效**

- 使用 or 连接，会导致索引失效

```
mysql> SHOW INDEX FROM staffs;
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table  | Non_unique | Key_name                | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| staffs |          0 | PRIMARY                 |            1 | id          | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            1 | name        | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            2 | age         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
| staffs |          1 | index_staffs_nameAgePos |            3 | pos         | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+--------+------------+-------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
4 rows in set (0.00 sec)

mysql> explain select * from staffs where name='z3' or name = 'July';
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
| id | select_type | table  | type | possible_keys           | key  | key_len | ref  | rows | Extra       |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
|  1 | SIMPLE      | staffs | ALL  | index_staffs_nameAgePos | NULL | NULL    | NULL |    3 | Using where |
+----+-------------+--------+------+-------------------------+------+---------+------+------+-------------+
1 row in set (0.00 sec)
```

### 2.6.2. 、索引优化面试题

> **索引优化面试题**

**创建表**

- 建表 SQL

```mysql
create table test03(
    id int primary key not null auto_increment,
    c1 char(10),
    c2 char(10),
    c3 char(10),
    c4 char(10),
    c5 char(10)
);

insert into test03(c1,c2,c3,c4,c5) values ('a1','a2','a3','a4','a5');
insert into test03(c1,c2,c3,c4,c5) values ('b1','b2','b3','b4','b5');
insert into test03(c1,c2,c3,c4,c5) values ('c1','c2','c3','c4','c5');
insert into test03(c1,c2,c3,c4,c5) values ('d1','d2','d3','d4','d5');
insert into test03(c1,c2,c3,c4,c5) values ('e1','e2','e3','e4','e5');

create index idx_test03_c1234 on test03(c1,c2,c3,c4);
```

- test03 表中的测试数据

```
mysql> select * from test03;
+----+------+------+------+------+------+
| id | c1   | c2   | c3   | c4   | c5   |
+----+------+------+------+------+------+
|  1 | a1   | a2   | a3   | a4   | a5   |
|  2 | b1   | b2   | b3   | b4   | b5   |
|  3 | c1   | c2   | c3   | c4   | c5   |
|  4 | d1   | d2   | d3   | d4   | d5   |
|  5 | e1   | e2   | e3   | e4   | e5   |
+----+------+------+------+------+------+
5 rows in set (0.00 sec)
```

- test03 表中的索引

```
mysql> SHOW INDEX FROM test03;
+--------+------------+------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table  | Non_unique | Key_name         | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+--------+------------+------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| test03 |          0 | PRIMARY          |            1 | id          | A         |           5 |     NULL | NULL   |      | BTREE      |         |               |
| test03 |          1 | idx_test03_c1234 |            1 | c1          | A         |           5 |     NULL | NULL   | YES  | BTREE      |         |               |
| test03 |          1 | idx_test03_c1234 |            2 | c2          | A         |           5 |     NULL | NULL   | YES  | BTREE      |         |               |
| test03 |          1 | idx_test03_c1234 |            3 | c3          | A         |           5 |     NULL | NULL   | YES  | BTREE      |         |               |
| test03 |          1 | idx_test03_c1234 |            4 | c4          | A         |           5 |     NULL | NULL   | YES  | BTREE      |         |               |
+--------+------------+------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
5 rows in set (0.00 sec)
```

> **问题：我们创建了复合索引idx_test03_c1234，根据以下SQL分析下索引使用情况？**

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c3='a3' AND c4='a4';`
- 即全值匹配

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c3='a3' AND c4='a4';
+----+-------------+--------+------+------------------+------------------+---------+-------------------------+------+-----------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref                     | rows | Extra                 |
+----+-------------+--------+------+------------------+------------------+---------+-------------------------+------+-----------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 124     | const,const,const,const |    1 | Using index condition |
+----+-------------+--------+------+------------------+------------------+---------+-------------------------+------+-----------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c4='a4' AND c3='a3' AND c2='a2' AND c1='a1';`
- mysql 优化器进行了优化，所以我们的索引都生效了

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c4='a4' AND c3='a3' AND c2='a2' AND c1='a1';
+----+-------------+--------+------+------------------+------------------+---------+-------------------------+------+-----------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref                     | rows | Extra                 |
+----+-------------+--------+------+------------------+------------------+---------+-------------------------+------+-----------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 124     | const,const,const,const |    1 | Using index condition |
+----+-------------+--------+------+------------------+------------------+---------+-------------------------+------+-----------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c3>'a3' AND c4='a4';`
- c3 列使用了索引进行排序，并没有进行查找，导致 c4 无法用索引进行查找

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c3>'a3' AND c4='a4'; 
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys    | key              | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | test03 | range | idx_test03_c1234 | idx_test03_c1234 | 93      | NULL |    1 | Using index condition |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c4>'a4' AND c3='a3';`
- mysql 优化器进行了优化，所以我们的索引都生效了，在 c4 时进行了范围搜索

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c4>'a4' AND c3='a3'; 
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys    | key              | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | test03 | range | idx_test03_c1234 | idx_test03_c1234 | 124     | NULL |    1 | Using index condition |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c4='a4' ORDER BY c3;`
- c3 列将索引用于排序，而不是查找，c4 列没有用到索引

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c4='a4' ORDER BY c3; 
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref         | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 62      | const,const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' ORDER BY c3;`
- 那不就和上面一样的嘛~~~，c4 列都没有用到索引

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' ORDER BY c3; 
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref         | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 62      | const,const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' ORDER BY c4;`
- 妈耶，因为索引建立的顺序和使用的顺序不一致，导致 mysql 动用了文件排序
- 看到 Using filesort 就要知道：此句 SQL 必须优化

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' ORDER BY c4; 
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+----------------------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref         | rows | Extra                                              |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+----------------------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 62      | const,const |    1 | Using index condition; Using where; Using filesort |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+----------------------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c5='a5' ORDER BY c2, c3;`
- 只用 c1 一个字段索引，但是c2、c3用于排序，无filesort
- 难道因为排序的时候，c2 紧跟在 c1 之后，所以就不用 filesort 吗？

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c5='a5' ORDER BY c2, c3; 
+----+-------------+--------+------+------------------+------------------+---------+-------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 31      | const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c5='a5' ORDER BY c3, c2;`
- 出现了filesort，我们建的索引是1234，它没有按照顺序来，32颠倒了

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c5='a5' ORDER BY c3, c2; 
+----+-------------+--------+------+------------------+------------------+---------+-------+------+----------------------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra                                              |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+----------------------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 31      | const |    1 | Using index condition; Using where; Using filesort |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+----------------------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' ORDER BY c2, c3;`
- 用c1、c2两个字段索引，但是c2、c3用于排序，无filesort

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' ORDER BY c2, c3; 
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref         | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 62      | const,const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c5='a5' ORDER BY c2, c3;`
- 和 c5 这个坑爹货没啥关系

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c5='a5' ORDER BY c2, c3; 
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref         | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 62      | const,const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c5='a5' ORDER BY c3, c2;`
- 注意查询条件 c2=‘a2’ ，我都把 c2 查出来了（c2 为常量），我还给它排序作甚，所以没有产生 filesort

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2='a2' AND c5='a5' ORDER BY c3, c2; 
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref         | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 62      | const,const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c4='a4' GROUP BY c2, c3;`
- 顺序为 1 2 3 ，没有产生文件排序

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c4='a4' GROUP BY c2, c3; 
+----+-------------+--------+------+------------------+------------------+---------+-------+------+------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra                              |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 31      | const |    1 | Using index condition; Using where |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+------------------------------------+
1 row in set (0.00 sec)
```

- `EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c4='a4' GROUP BY c3, c2;`
- group by 表面上叫分组，分组之前必排序，group by 和 order by 在索引上的问题基本是一样的
- Using temporary; Using filesort 两个都有，我只能说是灭绝师太

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c4='a4' GROUP BY c3, c2; 
+----+-------------+--------+------+------------------+------------------+---------+-------+------+---------------------------------------------------------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra                                                               |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+---------------------------------------------------------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 31      | const |    1 | Using index condition; Using where; Using temporary; Using filesort |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+---------------------------------------------------------------------+
1 row in set (0.01 sec)
```

- 结论：
  - group by 基本上都需要进行排序，但凡使用不当，会有临时表产生
  - 定值为常量、范围之后失效，最终看排序的顺序

### 2.6.3. 、索引失效总结

> **一般性建议**

1. 对于单键索引，尽量选择针对当前query过滤性更好的索引
2. 在选择组合索引的时候，当前query中过滤性最好的字段在索引字段顺序中，位置越靠左越好。
3. 在选择组合索引的时候，尽量选择可以能包含当前query中的where子句中更多字段的索引
4. 尽可能通过分析统计信息和调整query的写法来达到选择合适索引的目的

> **索引优化的总结**

- like 后面以常量开头，比如 like ‘kk%’ 和 like ‘k%kk%’ ，可以理解为就是常量

![image-20200804162716329](https://img-blog.csdnimg.cn/img_convert/b90f429227d6c5d1c504ad147d8f424b.png)

------

**like SQL 实测**

- = ‘kk’ ：key_len = 93 ，请记住此参数的值，后面有用

```
----+-------------+--------+------+------------------+------------------+---------+-------------------+------+-----------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref               | rows | Extra                 |
+----+-------------+--------+------+------------------+------------------+---------+-------------------+------+-----------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 93      | const,const,const |    1 | Using index condition |
+----+-------------+--------+------+------------------+------------------+---------+-------------------+------+-----------------------+
1 row in set (0.00 sec)
```

- like ‘kk%’：
  - key_len = 93 ，和上面一样，说明 c1 c2 c3 都用到了索引
  - type = range 表明这是一个范围搜索

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2 like 'kk%' AND c3='a3';
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys    | key              | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | test03 | range | idx_test03_c1234 | idx_test03_c1234 | 93      | NULL |    1 | Using index condition |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

- like ‘%kk’ 和 like ‘%kk%’ ：key_len = 31 ，表示只有 c1 用到了索引

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2 like '%kk' AND c3='a3';
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-----------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra                 |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-----------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 31      | const |    1 | Using index condition |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-----------------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2 like '%kk%' AND c3='a3';
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-----------------------+
| id | select_type | table  | type | possible_keys    | key              | key_len | ref   | rows | Extra                 |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-----------------------+
|  1 | SIMPLE      | test03 | ref  | idx_test03_c1234 | idx_test03_c1234 | 31      | const |    1 | Using index condition |
+----+-------------+--------+------+------------------+------------------+---------+-------+------+-----------------------+
1 row in set (0.00 sec)
```

- like ‘k%kk%’ ：key_len = 93 ，表示 c1 c2 c3 都用到了索引

```
mysql> EXPLAIN SELECT * FROM test03 WHERE c1='a1' AND c2 like 'k%kk%' AND c3='a3';
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
| id | select_type | table  | type  | possible_keys    | key              | key_len | ref  | rows | Extra                 |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
|  1 | SIMPLE      | test03 | range | idx_test03_c1234 | idx_test03_c1234 | 93      | NULL |    1 | Using index condition |
+----+-------------+--------+-------+------------------+------------------+---------+------+------+-----------------------+
1 row in set (0.00 sec)
```

> **索引优化的总结**

全值匹配我最爱， 最左前缀要遵守；

带头大哥不能死， 中间兄弟不能断；

索引列上少计算， 范围之后全失效；

LIKE 百分写最右， 覆盖索引不写 *；

不等空值还有 OR， 索引影响要注意；

VAR 引号不可丢， SQL 优化有诀窍。



# 3. 第 3 章 查询截取分析

## 3.1. 、查询优化

### 3.1.1. 、MySQL 优化原则

> **mysql 的调优大纲**

1. 慢查询的开启并捕获
2. explain+慢SQL分析
3. show profile查询SQL在Mysql服务器里面的执行细节和生命周期情况
4. SQL数据库服务器的参数调优

> **永远小表驱动大表，类似嵌套循环 Nested Loop**

1. EXISTS 语法：
   - `SELECT ... FROM table WHERE EXISTS(subquery)`
   - 该语法可以理解为：将查询的数据，放到子查询中做条件验证，根据验证结果（TRUE或FALSE）来决定主查询的数据结果是否得以保留。
2. EXISTS(subquery) 只返回TRUE或FALSE，因此子查询中的`SELECT *`也可以是`SELECT 1`或其他，官方说法是实际执行时会忽略SELECT清单，因此没有区别
3. EXISTS子查询的实际执行过程可能经过了优化而不是我们理解上的逐条对比，如果担忧效率问题，可进行实际检验以确定是否有效率问题。
4. EXISTS子查询往往也可以用条件表达式、其他子查询或者JOIN来替代，何种最优需要具体问题具体分析

![image-20200805101726632](https://img-blog.csdnimg.cn/img_convert/ae1d7b7768c672c484694ff4f284edfe.png)

------

**结论：**

1. 永远记住小表驱动大表
2. 当 B 表数据集小于 A 表数据集时，使用 in
3. 当 A 表数据集小于 B 表数据集时，使用 exist

------

**in 和 exists 的用法**

- tbl_emp 表和 tbl_dept 表

```
mysql> select * from tbl_emp;
+----+------+--------+
| id | NAME | deptId |
+----+------+--------+
|  1 | z3   |      1 |
|  2 | z4   |      1 |
|  3 | z5   |      1 |
|  4 | w5   |      2 |
|  5 | w6   |      2 |
|  6 | s7   |      3 |
|  7 | s8   |      4 |
|  8 | s9   |     51 |
+----+------+--------+
8 rows in set (0.00 sec)

mysql> select * from tbl_dept;
+----+----------+--------+
| id | deptName | locAdd |
+----+----------+--------+
|  1 | RD       | 11     |
|  2 | HR       | 12     |
|  3 | MK       | 13     |
|  4 | MIS      | 14     |
|  5 | FD       | 15     |
+----+----------+--------+
5 rows in set (0.00 sec)
```

- in 的写法

```
mysql> select * from tbl_emp e where e.deptId in (select id from tbl_dept);
+----+------+--------+
| id | NAME | deptId |
+----+------+--------+
|  1 | z3   |      1 |
|  2 | z4   |      1 |
|  3 | z5   |      1 |
|  4 | w5   |      2 |
|  5 | w6   |      2 |
|  6 | s7   |      3 |
|  7 | s8   |      4 |
+----+------+--------+
7 rows in set (0.00 sec)
```

- exists 的写法

```
mysql> select * from tbl_emp e where exists (select 1 from tbl_dept d where e.deptId = d.id);
+----+------+--------+
| id | NAME | deptId |
+----+------+--------+
|  1 | z3   |      1 |
|  2 | z4   |      1 |
|  3 | z5   |      1 |
|  4 | w5   |      2 |
|  5 | w6   |      2 |
|  6 | s7   |      3 |
|  7 | s8   |      4 |
+----+------+--------+
7 rows in set (0.00 sec)
```

### 3.1.2. 、ORDER BY 优化

> **ORDER BY子句，尽量使用Index方式排序，避免使用FileSort方式排序**

**创建表**

- 建表 SQL

```mysql
create table tblA(
    #id int primary key not null auto_increment,
    age int,
    birth timestamp not null
);

insert into tblA(age, birth) values(22, now());
insert into tblA(age, birth) values(23, now());
insert into tblA(age, birth) values(24, now());

create index idx_A_ageBirth on tblA(age, birth);
```

- tblA 表中的测试数据

```
mysql> select * from tblA;
+------+---------------------+
| age  | birth               |
+------+---------------------+
|   22 | 2020-08-05 10:36:32 |
|   23 | 2020-08-05 10:36:32 |
|   24 | 2020-08-05 10:36:32 |
+------+---------------------+
3 rows in set (0.00 sec)
```

- tbl 中的索引

```
mysql> SHOW INDEX FROM tblA;
+-------+------------+----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table | Non_unique | Key_name       | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+-------+------------+----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| tblA  |          1 | idx_A_ageBirth |            1 | age         | A         |           3 |     NULL | NULL   | YES  | BTREE      |         |               |
| tblA  |          1 | idx_A_ageBirth |            2 | birth       | A         |           3 |     NULL | NULL   |      | BTREE      |         |               |
+-------+------------+----------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
2 rows in set (0.00 sec)
```

------

**CASE1：能使用索引进行排序的情况**

- 只有带头大哥 age

```
mysql> EXPLAIN SELECT * FROM tblA where age>20 order by age;
+----+-------------+-------+-------+----------------+----------------+---------+------+------+--------------------------+
| id | select_type | table | type  | possible_keys  | key            | key_len | ref  | rows | Extra                    |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+--------------------------+
|  1 | SIMPLE      | tblA  | index | idx_A_ageBirth | idx_A_ageBirth | 9       | NULL |    3 | Using where; Using index |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+--------------------------+
1 row in set (0.01 sec)

mysql> EXPLAIN SELECT * FROM tblA where birth>'2016-01-28 00:00:00' order by age;
+----+-------------+-------+-------+---------------+----------------+---------+------+------+--------------------------+
| id | select_type | table | type  | possible_keys | key            | key_len | ref  | rows | Extra                    |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+--------------------------+
|  1 | SIMPLE      | tblA  | index | NULL          | idx_A_ageBirth | 9       | NULL |    3 | Using where; Using index |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+--------------------------+
1 row in set (0.00 sec)
```

- 带头大哥 age + 小弟 birth

```
mysql> EXPLAIN SELECT * FROM tblA where age>20 order by age,birth;
+----+-------------+-------+-------+----------------+----------------+---------+------+------+--------------------------+
| id | select_type | table | type  | possible_keys  | key            | key_len | ref  | rows | Extra                    |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+--------------------------+
|  1 | SIMPLE      | tblA  | index | idx_A_ageBirth | idx_A_ageBirth | 9       | NULL |    3 | Using where; Using index |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+--------------------------+
1 row in set (0.00 sec)
```

- mysql 默认升序排列，全升序或者全降序，都扛得住

```
mysql> EXPLAIN SELECT * FROM tblA ORDER BY age ASC, birth ASC;
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-------------+
| id | select_type | table | type  | possible_keys | key            | key_len | ref  | rows | Extra       |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-------------+
|  1 | SIMPLE      | tblA  | index | NULL          | idx_A_ageBirth | 9       | NULL |    3 | Using index |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-------------+
1 row in set (0.00 sec)

mysql> EXPLAIN SELECT * FROM tblA ORDER BY age DESC, birth DESC;
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-------------+
| id | select_type | table | type  | possible_keys | key            | key_len | ref  | rows | Extra       |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-------------+
|  1 | SIMPLE      | tblA  | index | NULL          | idx_A_ageBirth | 9       | NULL |    3 | Using index |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-------------+
1 row in set (0.01 sec)
```

------

**CASE2：不能使用索引进行排序的情况**

- 带头大哥 age 挂了

```
mysql> EXPLAIN SELECT * FROM tblA where age>20 order by birth;
+----+-------------+-------+-------+----------------+----------------+---------+------+------+------------------------------------------+
| id | select_type | table | type  | possible_keys  | key            | key_len | ref  | rows | Extra                                    |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+------------------------------------------+
|  1 | SIMPLE      | tblA  | index | idx_A_ageBirth | idx_A_ageBirth | 9       | NULL |    3 | Using where; Using index; Using filesort |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+------------------------------------------+
1 row in set (0.01 sec)
```

- 小弟 birth 居然敢在带头大哥 age 前面

```
mysql> EXPLAIN SELECT * FROM tblA where age>20 order by birth,age;
+----+-------------+-------+-------+----------------+----------------+---------+------+------+------------------------------------------+
| id | select_type | table | type  | possible_keys  | key            | key_len | ref  | rows | Extra                                    |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+------------------------------------------+
|  1 | SIMPLE      | tblA  | index | idx_A_ageBirth | idx_A_ageBirth | 9       | NULL |    3 | Using where; Using index; Using filesort |
+----+-------------+-------+-------+----------------+----------------+---------+------+------+------------------------------------------+
1 row in set (0.00 sec)
```

- mysql 默认升序排列，如果全升序或者全降序，都 ok ，但是一升一降 mysql 就扛不住了

```
mysql> EXPLAIN SELECT * FROM tblA ORDER BY age ASC, birth DESC;
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-----------------------------+
| id | select_type | table | type  | possible_keys | key            | key_len | ref  | rows | Extra                       |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-----------------------------+
|  1 | SIMPLE      | tblA  | index | NULL          | idx_A_ageBirth | 9       | NULL |    3 | Using index; Using filesort |
+----+-------------+-------+-------+---------------+----------------+---------+------+------+-----------------------------+
1 row in set (0.00 sec)
```

------

**结论**

1. MySQL支持二种方式的排序，FileSort和Index，**Index效率高，它指MySQL扫描索引本身完成排序**，FileSort方式效率较低。
2. ORDER BY满足两情况（最佳左前缀原则），会使用Index方式排序
   - ORDER BY语句使用索引最左前列
   - 使用where子句与OrderBy子句条件列组合满足索引最左前列
3. 尽可能在索引列上完成排序操作，遵照索引建的**最佳左前缀**

> **如果未在索引列上完成排序，mysql 会启动 filesort 的两种算法：双路排序和单路排序**

1. 双路排序
   - MySQL4.1之前是使用双路排序，字面意思是两次扫描磁盘，最终得到数据。读取行指针和将要进行orderby操作的列，对他们进行排序，然后扫描已经排序好的列表，按照列表中的值重新从列表中读取对应的数据传输
   - 从磁盘取排序字段，在buffer进行排序，再从磁盘取其他字段。
2. 单路排序
   - 取一批数据，要对磁盘进行两次扫描，众所周知，I/O是很耗时的，所以在mysql4.1之后，出现了改进的算法，就是单路排序。
   - 从磁盘读取查询需要的所有列，按照将要进行orderby的列，在sort buffer对它们进行排序，然后扫描排序后的列表进行输出，它的效率更快一些，避免了第二次读取数据，并且把随机IO变成顺序IO，但是它会使用更多的空间，因为它把每一行都保存在内存中了。
3. 结论及引申出的问题：
   - 由于单路是改进的算法，总体而言好过双路
   - 在sort_buffer中，方法B比方法A要多占用很多空间，因为方法B是把所有字段都取出，所以有可能取出的数据的总大小超出了sort_buffer的容量，导致每次只能取sort_buffer容量大小的数据，进行排序（创建tmp文件，多路合并），排完再取取sort_buffer容量大小，再排…… 从而会导致多次I/O。
   - 结论：本来想省一次I/O操作，反而导致了大量的/O操作，反而得不偿失。
4. 更深层次的优化策略：
   - 增大sort_buffer_size参数的设置
   - 增大max_length_for_sort_data参数的设置

------

**遵循如下规则，可提高Order By的速度**

1. Order by时select *是一个大忌，只Query需要的字段，这点非常重要。在这里的影响是：
   - 当Query的字段大小总和小于max_length_for_sort_data，而且排序字段不是TEXT|BLOB类型时，会用改进后的算法——单路排序，否则用老算法——多路排序。
   - 两种算法的数据都有可能超出sort_buffer的容量，超出之后，会创建tmp文件进行合并排序，导致多次I/O，但是用单路排序算法的风险会更大一些，所以要提高sort_buffer_size。
2. 尝试提高 sort_buffer_size不管用哪种算法，提高这个参数都会提高效率，当然，要根据系统的能力去提高，因为这个参数是针对每个进程的
3. 尝试提高max_length_for_sort_data提高这个参数，会增加用改进算法的概率。但是如果设的太高，数据总容量超出sort_buffer_size的概率就增大，明显症状是高的磁盘I/O活动和低的处理器使用率。

> **Order By 排序索引优化的总结**

![image-20200805111725731](https://img-blog.csdnimg.cn/img_convert/0c88f6d981ceaa6d99490c37a92da354.png)

### 3.1.3. 、GROUP BY 优化

> **group by关键字优化**

1. group by实质是**先排序后进行分组，遵照索引的最佳左前缀**
2. 当无法使用索引列，增大max_length_for_sort_data参数的设置+增大sort_buffer_size参数的设置
3. where高于having，能写在where限定的条件就不要去having限定了
4. 其余的规则均和 order by 一致

## 3.2. 、慢查询日志

### 3.2.1. 、慢查询日志介绍

> **慢查询日志是什么？**

1. MySQL的慢查询日志是MySQL提供的一种日志记录，它用来**记录在MySQL中响应时间超过阀值的语句**，具体指运行时间超过**long_query_time**值的SQL，则会被记录到慢查询日志中。
2. long_query_time的默认值为10，意思是运行10秒以上的SQL语句会被记录下来
3. 由他来查看哪些SQL超出了我们的最大忍耐时间值，比如一条sql执行超过5秒钟，我们就算慢SQL，希望能收集超过5秒的sql，结合之前explain进行全面分析。

### 3.2.2. 、慢查询日志开启

> **怎么玩？**

**说明：**

1. 默认情况下，MySQL数据库没有开启慢查询日志，需要我们手动来设置这个参数。
2. 当然，如果不是调优需要的话，一般不建议启动该参数，因为开启慢查询日志会或多或少带来一定的性能影响。慢查询日志支持将日志记录写入文件

------

**查看是否开启及如何开启**

- 查看慢查询日志是否开启：
  - 默认情况下slow_query_log的值为OFF，表示慢查询日志是禁用的
  - 可以通过设置**slow_query_log**的值来开启
  - 通过`SHOW VARIABLES LIKE '%slow_query_log%';`查看 mysql 的慢查询日志是否开启

```
mysql> SHOW VARIABLES LIKE '%slow_query_log%';
+---------------------+-------------------------------+
| Variable_name       | Value                         |
+---------------------+-------------------------------+
| slow_query_log      | OFF                           |
| slow_query_log_file | /var/lib/mysql/Heygo-slow.log |
+---------------------+-------------------------------+
2 rows in set (0.00 sec)
```

- 如何开启开启慢查询日志：
  - `set global slow_query_log = 1;`开启慢查询日志
  - 使用`set global slow_query_log=1`开启了慢查询日志只对当前数据库生效，如果MySQL重启后则会失效。

```
mysql> set global slow_query_log = 1;
Query OK, 0 rows affected (0.07 sec)

mysql> SHOW VARIABLES LIKE '%slow_query_log%';
+---------------------+-------------------------------+
| Variable_name       | Value                         |
+---------------------+-------------------------------+
| slow_query_log      | ON                            |
| slow_query_log_file | /var/lib/mysql/Heygo-slow.log |
+---------------------+-------------------------------+
2 rows in set (0.00 sec)
```

- 如果要永久生效，就必须修改配置文件my.cnf（其它系统变量也是如此）

  - 修改my.cnf文件，[mysqld]下增加或修改参数：slow_query_log和slow_query_log_file后，然后重启MySQL服务器。
  - 也即将如下两行配置进my.cnf文件

  ```
  [mysqld]
  slow_query_log =1
  slow_query_log_file=/var/lib/mysql/Heygo-slow.log
  ```

  - 关于慢查询的参数slow_query_log_file，它指定慢查询日志文件的存放路径，系统默认会给一个缺省的文件host_name-slow.log（如果没有指定参数slow_query_log_file的话）

------

**那么开启慢查询日志后，什么样的SQL参会记录到慢查询里面？**

- 这个是由参数long_query_time控制，默认情况下long_query_time的值为10秒，命令：`SHOW VARIABLES LIKE 'long_query_time%';`查看慢 SQL 的阈值

```
mysql> SHOW VARIABLES LIKE 'long_query_time%';
+-----------------+-----------+
| Variable_name   | Value     |
+-----------------+-----------+
| long_query_time | 10.000000 |
+-----------------+-----------+
1 row in set (0.01 sec)
```

- 可以使用命令修改，也可以在my.cnf参数里面修改。
- 假如运行时间正好等于long_query_time的情况，并不会被记录下来。也就是说，在mysql源码里是判断大于long_query_time，而非大于等于。

### 3.2.3. 、慢查询日志示例

> **案例讲解**

- 查看慢 SQL 的阈值时间，默认阈值时间为 10s

```
mysql> SHOW VARIABLES LIKE 'long_query_time%';
+-----------------+-----------+
| Variable_name   | Value     |
+-----------------+-----------+
| long_query_time | 10.000000 |
+-----------------+-----------+
1 row in set (0.00 sec)
```

- 设置慢 SQL 的阈值时间，我们将其设置为 3s

```
mysql> set global long_query_time=3;
Query OK, 0 rows affected (0.00 sec)
```

- 为什么设置后阈值时间没变？
  - 需要重新连接或者新开一个回话才能看到修改值。
  - 查看全局的 long_query_time 值：`show global variables like 'long_query_time';`发现已经生效

```
mysql> set global long_query_time=3;
Query OK, 0 rows affected (0.00 sec)

mysql> SHOW VARIABLES LIKE 'long_query_time%';
+-----------------+-----------+
| Variable_name   | Value     |
+-----------------+-----------+
| long_query_time | 10.000000 |
+-----------------+-----------+
1 row in set (0.00 sec)

mysql> show global variables like 'long_query_time';
+-----------------+----------+
| Variable_name   | Value    |
+-----------------+----------+
| long_query_time | 3.000000 |
+-----------------+----------+
1 row in set (0.00 sec)
```

- 记录慢 SQL 以供后续分析

  - 怼个 select sleep(4); 超过 3s ，肯定会被记录到日志中

  ```
  mysql> select sleep(4); 
  +----------+
  | sleep(4) |
  +----------+
  |        0 |
  +----------+
  1 row in set (4.00 sec)
  ```

  - 慢查询日志文件在 /var/lib/mysql/ 下，后缀为 -slow.log

  ```
  [root@Heygo mysql]# cd /var/lib/mysql/
  [root@Heygo mysql]# ls -l
  总用量 176156
  -rw-rw----. 1 mysql mysql       56 8月   3 19:08 auto.cnf
  drwx------. 2 mysql mysql     4096 8月   5 10:36 db01
  -rw-rw----. 1 mysql mysql     7289 8月   3 22:38 Heygo.err
  -rw-rw----. 1 mysql mysql      371 8月   5 12:58 Heygo-slow.log
  -rw-rw----. 1 mysql mysql 79691776 8月   5 10:36 ibdata1
  -rw-rw----. 1 mysql mysql 50331648 8月   5 10:36 ib_logfile0
  -rw-rw----. 1 mysql mysql 50331648 8月   3 19:08 ib_logfile1
  drwx------. 2 mysql mysql     4096 8月   3 19:08 mysql
  srwxrwxrwx. 1 mysql mysql        0 8月   3 22:38 mysql.sock
  drwx------. 2 mysql mysql     4096 8月   3 19:08 performance_schema
  ```

  - 查看慢查询日志中的内容

  ```
  [root@Heygo mysql]# cat Heygo-slow.log 
  /usr/sbin/mysqld, Version: 5.6.49 (MySQL Community Server (GPL)). started with:
  Tcp port: 3306  Unix socket: /var/lib/mysql/mysql.sock
  Time                 Id Command    Argument
  # Time: 200805 12:58:01
  # User@Host: root[root] @ localhost []  Id:    11
  # Query_time: 4.000424  Lock_time: 0.000000 Rows_sent: 1  Rows_examined: 0
  SET timestamp=1596603481;
  select sleep(4);
  ```

- 查询当前系统中有多少条慢查询记录：`show global status like '%Slow_queries%';`

```
mysql> show global status like '%Slow_queries%';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| Slow_queries  | 1     |
+---------------+-------+
1 row in set (0.00 sec)
```

------

**配置版的慢查询日志**

在 /etc/my.cnf 文件的 [mysqld] 节点下配置

```
slow_query_log=1；
slow_query_log_file=/var/lib/mysql/Heygo-slow.log 
long_query_time=3；
log_output=FILE
```

> **日志分析命令 mysqldumpslow**

**mysqldumpslow是什么？**

在生产环境中，如果要手工分析日志，查找、分析SQL，显然是个体力活，MySQL提供了日志分析工具mysqldumpslow。

------

**查看 mysqldumpslow的帮助信息**

```
[root@Heygo mysql]# mysqldumpslow --help
Usage: mysqldumpslow [ OPTS... ] [ LOGS... ]

Parse and summarize the MySQL slow query log. Options are

  --verbose    verbose
  --debug      debug
  --help       write this text to standard output

  -v           verbose
  -d           debug
  -s ORDER     what to sort by (al, at, ar, c, l, r, t), 'at' is default
                al: average lock time
                ar: average rows sent
                at: average query time
                 c: count
                 l: lock time
                 r: rows sent
                 t: query time  
  -r           reverse the sort order (largest last instead of first)
  -t NUM       just show the top n queries
  -a           don't abstract all numbers to N and strings to 'S'
  -n NUM       abstract numbers with at least n digits within names
  -g PATTERN   grep: only consider stmts that include this string
  -h HOSTNAME  hostname of db server for *-slow.log filename (can be wildcard),
               default is '*', i.e. match all
  -i NAME      name of server instance (if using mysql.server startup script)
  -l           don't subtract lock time from total time
```

------

**mysqldumpshow 参数解释**

1. s：是表示按何种方式排序
2. c：访问次数
3. l：锁定时间
4. r：返回记录
5. t：查询时间
6. al：平均锁定时间
7. ar：平均返回记录数
8. at：平均查询时间
9. t：即为返回前面多少条的数据
10. g：后边搭配一个正则匹配模式，大小写不敏感的

------

**常用参数手册**

1. 得到返回记录集最多的10个SQL

   ```
   mysqldumpslow -s r -t 10 /var/lib/mysql/Heygo-slow.log
   ```

2. 得到访问次数最多的10个SQL

   ```
   mysqldumpslow -s c- t 10/var/lib/mysql/Heygo-slow.log
   ```

3. 得到按照时间排序的前10条里面含有左连接的查询语句

   ```
   mysqldumpslow -s t -t 10 -g "left join" /var/lib/mysql/Heygo-slow.log
   ```

4. 另外建议在使用这些命令时结合 | 和more使用，否则有可能出现爆屏情况

   ```
   mysqldumpslow -s r -t 10 /var/lib/mysql/Heygo-slow.log | more
   ```

## 3.3. 、批量数据脚本

> **创建表**

- 建表 SQL

```mysql
CREATE TABLE dept
(
    deptno int unsigned primary key auto_increment,
    dname varchar(20) not null default "",
    loc varchar(8) not null default ""
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE emp
(
    id int unsigned primary key auto_increment,
    empno mediumint unsigned not null default 0,
    ename varchar(20) not null default "",
    job varchar(9) not null default "",
    mgr mediumint unsigned not null default 0,
    hiredate date not null,
    sal decimal(7,2) not null,
    comm decimal(7,2) not null,
    deptno mediumint unsigned not null default 0
)ENGINE=INNODB DEFAULT CHARSET=utf8;
```

> **设置参数**

- 创建函数，假如报错：This function has none of DETERMINISTIC………

- 由于开启过慢查询日志，因为我们开启了bin-log，我们就必须为我们的function指定一个参数。

  - `log_bin_trust_function_creators = OFF` ，默认必须为 function 传递一个参数

  ```
  mysql> show variables like 'log_bin_trust_function_creators'; 
  +---------------------------------+-------+
  | Variable_name                   | Value |
  +---------------------------------+-------+
  | log_bin_trust_function_creators | OFF   |
  +---------------------------------+-------+
  1 row in set (0.00 sec)
  ```

  - 通过 `set global log_bin_trust_function_creators=1;`我们可以不用为 function 传参

  ```
  mysql> set global log_bin_trust_function_creators=1; 
  Query OK, 0 rows affected (0.00 sec)
  
  mysql> show variables like 'log_bin_trust_function_creators';
  +---------------------------------+-------+
  | Variable_name                   | Value |
  +---------------------------------+-------+
  | log_bin_trust_function_creators | ON    |
  +---------------------------------+-------+
  1 row in set (0.00 sec)
  ```

- 这样添加了参数以后，如果mysqld重启，上述参数又会消失，永久方法在配置文件中修改‘

  - windows下：my.ini --> [mysqld] 节点下加上 `log_bin_trust_function_creators=1`
  - linux下：/etc/my.cnf --> [mysqld] 节点下加上 `log_bin_trust_function_creators=1`

> **创建函数，保证每条数据都不同**

- 随机产生字符串的函数

```mysql
delimiter $$ # 两个 $$ 表示结束
create function rand_string(n int) returns varchar(255)
begin
    declare chars_str varchar(100) default 'abcdefghijklmnopqrstuvwxyz';
    declare return_str varchar(255) default '';
    declare i int default 0;
    while i < n do
        set return_str = concat(return_str,substring(chars_str,floor(1+rand()*52),1));
        set i=i+1;
    end while;
    return return_str;
end $$
```

- 随机产生部门编号的函数

```mysql
delimiter $$
create function rand_num() returns int(5)
begin
    declare i int default 0;
    set i=floor(100+rand()*10);
    return i;
end $$
```

> **创建存储过程**

- 创建往emp表中插入数据的存储过程

```mysql
delimiter $$
create procedure insert_emp(in start int(10),in max_num int(10))
begin
    declare i int default 0;
    set autocommit = 0;
    repeat
        set i = i+1;
        insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values((start+i),rand_string(6),'salesman',0001,curdate(),2000,400,rand_num());
        until i=max_num
        end repeat;
    commit;
end $$
```

- 创建往dept表中插入数据的存储过程

```mysql
delimiter $$
create procedure insert_dept(in start int(10),in max_num int(10))
begin
    declare i int default 0;
    set autocommit = 0;
    repeat
        set i = i+1;
        insert into dept(deptno,dname,loc) values((start+i),rand_string(10),rand_string(8));
        until i=max_num
        end repeat;
    commit;
end $$
```

> **调用存储过程**

- 向 dept 表中插入 10 条记录

```mysql
DELIMITER ;
CALL insert_dept(100, 10);
mysql> select * from dept;
+--------+---------+--------+
| deptno | dname   | loc    |
+--------+---------+--------+
|    101 | aowswej | syrlhb |
|    102 | uvneag  | pup    |
|    103 | lps     | iudgy  |
|    104 | jipvsk  | ihytx  |
|    105 | hrpzhiv | vjb    |
|    106 | phngy   | yf     |
|    107 | uhgd    | lgst   |
|    108 | ynyl    | iio    |
|    109 | daqbgsh | mp     |
|    110 | yfbrju  | vuhsf  |
+--------+---------+--------+
10 rows in set (0.00 sec)
```

- 向 emp 表中插入 50w 条记录

```mysql
DELIMITER ;
CALL insert_emp(100001, 500000);
mysql> select * from emp limit 20;
+----+--------+-------+----------+-----+------------+---------+--------+--------+
| id | empno  | ename | job      | mgr | hiredate   | sal     | comm   | deptno |
+----+--------+-------+----------+-----+------------+---------+--------+--------+
|  1 | 100002 | ipbmd | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    101 |
|  2 | 100003 | bfvt  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    107 |
|  3 | 100004 |       | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    109 |
|  4 | 100005 | cptas | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    101 |
|  5 | 100006 | ftn   | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    108 |
|  6 | 100007 | gzh   | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    102 |
|  7 | 100008 | rji   | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    100 |
|  8 | 100009 |       | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    106 |
|  9 | 100010 | tms   | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    100 |
| 10 | 100011 | utxe  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    101 |
| 11 | 100012 | vbis  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    104 |
| 12 | 100013 | qgfv  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    104 |
| 13 | 100014 | wrvb  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    105 |
| 14 | 100015 | dyks  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    109 |
| 15 | 100016 | hpcs  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    101 |
| 16 | 100017 | fxb   | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    108 |
| 17 | 100018 | vqxq  | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    102 |
| 18 | 100019 | rq    | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    102 |
| 19 | 100020 | l     | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    106 |
| 20 | 100021 | lk    | salesman |   1 | 2020-08-05 | 2000.00 | 400.00 |    100 |
+----+--------+-------+----------+-----+------------+---------+--------+--------+
20 rows in set (0.00 sec)
```

## 3.4. 、Show Profile

> **Show Profile 是什么？**

1. 是mysql提供可以用来分析当前会话中语句执行的资源消耗情况。可以用于SQL的调优测量
2. 官网：http://dev.mysql.com/doc/refman/5.5/en/show-profile.html
3. 默认情况下，参数处于关闭状态，并保存最近15次的运行结果

> **分析步骤**

**查看是当前的SQL版本是否支持Show Profile**

- show variables like ‘profiling%’; 查看 Show Profile 是否开启

```
mysql> show variables like 'profiling%';
+------------------------+-------+
| Variable_name          | Value |
+------------------------+-------+
| profiling              | OFF   |
| profiling_history_size | 15    |
+------------------------+-------+
2 rows in set (0.01 sec)
```

------

**开启功能 Show Profile ，默认是关闭，使用前需要开启**

- `set profiling=on;` 开启 Show Profile

```
mysql> set profiling=on; 
Query OK, 0 rows affected, 1 warning (0.00 sec)

mysql> show variables like 'profiling%';
+------------------------+-------+
| Variable_name          | Value |
+------------------------+-------+
| profiling              | ON    |
| profiling_history_size | 15    |
+------------------------+-------+
2 rows in set (0.00 sec)
```

------

**运行SQL**

- 正常 SQL

```mysql
select * from tbl_emp;
select * from tbl_emp e inner join tbl_dept d on e.deptId = d.id;
select * from tbl_emp e left join tbl_dept d on e.deptId = d.id;
```

- 慢 SQL

```mysql
select * from emp group by id%10 limit 150000;
select * from emp group by id%10 limit 150000;
select * from emp group by id%20 order by 5;
```

------

**查看结果**

- 通过 show profiles; 指令查看结果

```
mysql> show profiles;
+----------+------------+----------------------------------------------------------------------+
| Query_ID | Duration   | Query                                                                |
+----------+------------+----------------------------------------------------------------------+
|        1 | 0.00052700 | show variables like 'profiling%'                                     |
|        2 | 0.00030300 | select * from tbl_emp                                                |
|        3 | 0.00010650 | select * from tbl_emp e inner join tbl_dept d on e.'deptId' = d.'id' |
|        4 | 0.00031625 | select * from tbl_emp e inner join tbl_dept d on e.deptId = d.id     |
|        5 | 0.00042100 | select * from tbl_emp e left join tbl_dept d on e.deptId = d.id      |
|        6 | 0.38621875 | select * from emp group by id%20 limit 150000                        |
|        7 | 0.00014900 | select * from emp group by id%20 order by 150000                     |
|        8 | 0.38649000 | select * from emp group by id%20 order by 5                          |
|        9 | 0.06782700 | select COUNT(*) from emp                                             |
|       10 | 0.35434400 | select * from emp group by id%10 limit 150000                        |
+----------+------------+----------------------------------------------------------------------+
10 rows in set, 1 warning (0.00 sec)
```

------

**诊断SQL**

- `show profile cpu, block io for query SQL编号;` 查看 SQL 语句执行的具体流程以及每个步骤花费的时间

```
mysql> show profile cpu, block io for query 2;
+----------------------+----------+----------+------------+--------------+---------------+
| Status               | Duration | CPU_user | CPU_system | Block_ops_in | Block_ops_out |
+----------------------+----------+----------+------------+--------------+---------------+
| starting             | 0.000055 | 0.000000 |   0.000000 |            0 |             0 |
| checking permissions | 0.000007 | 0.000000 |   0.000000 |            0 |             0 |
| Opening tables       | 0.000011 | 0.000000 |   0.000000 |            0 |             0 |
| init                 | 0.000024 | 0.000000 |   0.000000 |            0 |             0 |
| System lock          | 0.000046 | 0.000000 |   0.000000 |            0 |             0 |
| optimizing           | 0.000018 | 0.000000 |   0.000000 |            0 |             0 |
| statistics           | 0.000008 | 0.000000 |   0.000000 |            0 |             0 |
| preparing            | 0.000019 | 0.000000 |   0.000000 |            0 |             0 |
| executing            | 0.000003 | 0.000000 |   0.000000 |            0 |             0 |
| Sending data         | 0.000089 | 0.000000 |   0.000000 |            0 |             0 |
| end                  | 0.000004 | 0.000000 |   0.000000 |            0 |             0 |
| query end            | 0.000003 | 0.000000 |   0.000000 |            0 |             0 |
| closing tables       | 0.000005 | 0.000000 |   0.000000 |            0 |             0 |
| freeing items        | 0.000006 | 0.000000 |   0.000000 |            0 |             0 |
| cleaning up          | 0.000006 | 0.000000 |   0.000000 |            0 |             0 |
+----------------------+----------+----------+------------+--------------+---------------+
15 rows in set, 1 warning (0.00 sec)
```

- 参数备注：

1. ALL：显示所有的开销信息
2. BLOCK IO：显示块IO相关开销
3. CONTEXT SWITCHES：上下文切换相关开销
4. CPU：显示CPU相关开销信息
5. IPC：显示发送和接收相关开销信息
6. MEMORY：显示内存相关开销信息
7. PAGE FAULTS：显示页面错误相关开销信息
8. SOURCE：显示和Source_function，Source_file，Source_line相关的开销信息
9. SWAPS：显示交换次数相关开销的信息

------

**日常开发需要注意的结论**

1. converting HEAP to MyISAM：查询结果太大，内存都不够用了往磁盘上搬了。
2. Creating tmp table：创建临时表，mysql 先将拷贝数据到临时表，然后用完再将临时表删除
3. Copying to tmp table on disk：把内存中临时表复制到磁盘，危险！！！
4. locked：锁表

------

**举例**

奇了怪了。。。老师的慢 SQL 我怎么复现不了，下面是老师的例子

![image-20200805143307302](https://img-blog.csdnimg.cn/img_convert/d3ab0ee45d1accb580c8f9cc8a9499de.png)

## 3.5. 、全局查询日志

**永远不要在生产环境开启这个功能。**

> 配置启用全局查询日志

- 在mysql的my.cnf中，设置如下：

```
# 开启
general_log=1

# 记录日志文件的路径
general_log_file=/path/logfile

# 输出格式
log_output=FILE
```

> **编码启用全局查询日志**

- 执行如下指令开启全局查询日志

```mysql
set global general_log=1;
set global log_output='TABLE';
```

- 此后，你所执行的sql语句，将会记录到mysql库里的general_log表，可以用下面的命令查看

```mysql
select * from mysql.general_log;
mysql> select * from mysql.general_log;
+---------------------+---------------------------+-----------+-----------+--------------+-----------------------------------------------+
| event_time          | user_host                 | thread_id | server_id | command_type | argument                                      |
+---------------------+---------------------------+-----------+-----------+--------------+-----------------------------------------------+
| 2020-08-05 14:41:07 | root[root] @ localhost [] |        14 |         0 | Query        | select * from emp group by id%10 limit 150000 |
| 2020-08-05 14:41:12 | root[root] @ localhost [] |        14 |         0 | Query        | select COUNT(*) from emp                      |
| 2020-08-05 14:41:30 | root[root] @ localhost [] |        14 |         0 | Query        | select * from mysql.general_log               |
+---------------------+---------------------------+-----------+-----------+--------------+-----------------------------------------------+
3 rows in set (0.00 sec)举报![img](https://g.csdnimg.cn/side-toolbar/3.0/images/fanhuidingbucopy.png
```





# 4. 第 4 章 MySQL 锁机制

## 4.1. 、概述

### 4.1.1. 、锁的定义

> **锁的定义**

1. 锁是计算机协调**多个进程或线程并发访问某一资源的机制**。
2. 在数据库中，除传统的计算资源（如CPU、RAM、I/O等）的争用以外，数据也是一种供许多用户共享的资源。
3. 如何保证数据并发访问的一致性、有效性是所有数据库必须解决的一个问题，锁冲突也是影响数据库并发访问性能的一个重要因素。
4. 从这个角度来说，锁对数据库而言显得尤其重要，也更加复杂。

### 4.1.2. 、锁的分类

> **锁的分类**

1. 从数据操作的类型（读、写）分
   - **读锁**（共享锁）：针对同一份数据，多个读操作可以同时进行而不会互相影响
   - **写锁**（排它锁）：当前写操作没有完成前，它会阻断其他写锁和读锁。
2. 从对数据操作的颗粒度
   - 表锁
   - 行锁

## 4.2. 、表锁

> **表锁的特点**

**偏向MyISAM存储引擎**，开销小，加锁快，无死锁，锁定粒度大，**发生锁冲突的概率最高，并发最低**

### 4.2.1. 、表锁案例

> **表锁案例分析**

**创建表**

- 建表 SQL：**引擎选择 myisam**

```mysql
create table mylock (
    id int not null primary key auto_increment,
    name varchar(20) default ''
) engine myisam;

insert into mylock(name) values('a');
insert into mylock(name) values('b');
insert into mylock(name) values('c');
insert into mylock(name) values('d');
insert into mylock(name) values('e');

select * from mylock;
```

- mylock 表中的测试数据

```
mysql> select * from mylock;
+----+------+
| id | name |
+----+------+
|  1 | a    |
|  2 | b    |
|  3 | c    |
|  4 | d    |
|  5 | e    |
+----+------+
5 rows in set (0.00 sec)
```

> **手动加锁和释放锁**

- 查看当前数据库中表的上锁情况：`show open tables;`，0 表示未上锁

```
mysql> show open tables;
+--------------------+----------------------------------------------------+--------+-------------+
| Database           | Table                                              | In_use | Name_locked |
+--------------------+----------------------------------------------------+--------+-------------+
| performance_schema | events_waits_history                               |      0 |           0 |
| performance_schema | events_waits_summary_global_by_event_name          |      0 |           0 |
| performance_schema | setup_timers                                       |      0 |           0 |
| performance_schema | events_waits_history_long                          |      0 |           0 |
| performance_schema | events_statements_summary_by_digest                |      0 |           0 |
| performance_schema | mutex_instances                                    |      0 |           0 |
| performance_schema | events_waits_summary_by_instance                   |      0 |           0 |
| performance_schema | events_stages_history                              |      0 |           0 |
| mysql              | db                                                 |      0 |           0 |
| performance_schema | events_waits_summary_by_host_by_event_name         |      0 |           0 |
| mysql              | user                                               |      0 |           0 |
| mysql              | columns_priv                                       |      0 |           0 |
| performance_schema | events_statements_history_long                     |      0 |           0 |
| performance_schema | performance_timers                                 |      0 |           0 |
| performance_schema | file_instances                                     |      0 |           0 |
| performance_schema | events_stages_summary_by_user_by_event_name        |      0 |           0 |
| performance_schema | events_stages_history_long                         |      0 |           0 |
| performance_schema | setup_actors                                       |      0 |           0 |
| performance_schema | cond_instances                                     |      0 |           0 |
| mysql              | proxies_priv                                       |      0 |           0 |
| performance_schema | socket_summary_by_instance                         |      0 |           0 |
| performance_schema | events_statements_current                          |      0 |           0 |
| mysql              | event                                              |      0 |           0 |
| performance_schema | session_connect_attrs                              |      0 |           0 |
| mysql              | plugin                                             |      0 |           0 |
| performance_schema | threads                                            |      0 |           0 |
| mysql              | time_zone_transition_type                          |      0 |           0 |
| mysql              | time_zone_name                                     |      0 |           0 |
| performance_schema | file_summary_by_event_name                         |      0 |           0 |
| performance_schema | events_waits_summary_by_user_by_event_name         |      0 |           0 |
| performance_schema | socket_summary_by_event_name                       |      0 |           0 |
| performance_schema | users                                              |      0 |           0 |
| mysql              | servers                                            |      0 |           0 |
| performance_schema | events_waits_summary_by_account_by_event_name      |      0 |           0 |
| db01               | tbl_emp                                            |      0 |           0 |
| performance_schema | events_statements_summary_by_host_by_event_name    |      0 |           0 |
| db01               | tblA                                               |      0 |           0 |
| performance_schema | table_io_waits_summary_by_index_usage              |      0 |           0 |
| performance_schema | events_waits_current                               |      0 |           0 |
| db01               | user                                               |      0 |           0 |
| mysql              | procs_priv                                         |      0 |           0 |
| performance_schema | events_statements_summary_by_thread_by_event_name  |      0 |           0 |
| db01               | emp                                                |      0 |           0 |
| db01               | tbl_user                                           |      0 |           0 |
| db01               | test03                                             |      0 |           0 |
| mysql              | slow_log                                           |      0 |           0 |
| performance_schema | file_summary_by_instance                           |      0 |           0 |
| db01               | article                                            |      0 |           0 |
| performance_schema | objects_summary_global_by_type                     |      0 |           0 |
| db01               | phone                                              |      0 |           0 |
| performance_schema | events_waits_summary_by_thread_by_event_name       |      0 |           0 |
| performance_schema | setup_consumers                                    |      0 |           0 |
| performance_schema | socket_instances                                   |      0 |           0 |
| performance_schema | rwlock_instances                                   |      0 |           0 |
| db01               | tbl_dept                                           |      0 |           0 |
| performance_schema | events_statements_summary_by_user_by_event_name    |      0 |           0 |
| db01               | staffs                                             |      0 |           0 |
| db01               | class                                              |      0 |           0 |
| mysql              | general_log                                        |      0 |           0 |
| performance_schema | events_stages_summary_global_by_event_name         |      0 |           0 |
| performance_schema | events_stages_summary_by_account_by_event_name     |      0 |           0 |
| performance_schema | events_statements_summary_by_account_by_event_name |      0 |           0 |
| performance_schema | table_lock_waits_summary_by_table                  |      0 |           0 |
| performance_schema | hosts                                              |      0 |           0 |
| performance_schema | setup_objects                                      |      0 |           0 |
| performance_schema | events_stages_current                              |      0 |           0 |
| mysql              | time_zone                                          |      0 |           0 |
| mysql              | tables_priv                                        |      0 |           0 |
| performance_schema | table_io_waits_summary_by_table                    |      0 |           0 |
| mysql              | time_zone_leap_second                              |      0 |           0 |
| db01               | book                                               |      0 |           0 |
| performance_schema | session_account_connect_attrs                      |      0 |           0 |
| db01               | mylock                                             |      0 |           0 |
| mysql              | func                                               |      0 |           0 |
| performance_schema | events_statements_summary_global_by_event_name     |      0 |           0 |
| performance_schema | events_statements_history                          |      0 |           0 |
| performance_schema | accounts                                           |      0 |           0 |
| mysql              | time_zone_transition                               |      0 |           0 |
| db01               | dept                                               |      0 |           0 |
| performance_schema | events_stages_summary_by_host_by_event_name        |      0 |           0 |
| performance_schema | events_stages_summary_by_thread_by_event_name      |      0 |           0 |
| mysql              | proc                                               |      0 |           0 |
| performance_schema | setup_instruments                                  |      0 |           0 |
| performance_schema | host_cache                                         |      0 |           0 |
+--------------------+----------------------------------------------------+--------+-------------+
84 rows in set (0.00 sec)
```

- 添加锁

```mysql
lock table 表名1 read(write), 表名2 read(write), ...;
```

- 释放表锁

```mysql
unlock tables;
```

#### 4.2.1.1. 、读锁示例

> **读锁示例**

- 在 session 1 会话中，给 mylock 表加个读锁

```
mysql> lock table mylock read;
Query OK, 0 rows affected (0.00 sec)
```

- 在 session1 会话中能不能读取 mylock 表：可以读

```
################# session1 中的操作 #################

mysql> select * from mylock;
+----+------+
| id | name |
+----+------+
|  1 | a    |
|  2 | b    |
|  3 | c    |
|  4 | d    |
|  5 | e    |
+----+------+
5 rows in set (0.00 sec)
```

- 在 session1 会话中能不能读取 book 表：并不行。。。

```
################# session1 中的操作 #################

mysql> select * from book;
ERROR 1100 (HY000): Table 'book' was not locked with LOCK TABLES
```

- 在 session2 会话中能不能读取 mylock 表：可以读

```
################# session2 中的操作 #################

mysql> select * from mylock;
+----+------+
| id | name |
+----+------+
|  1 | a    |
|  2 | b    |
|  3 | c    |
|  4 | d    |
|  5 | e    |
+----+------+
5 rows in set (0.00 sec)
```

- 在 session1 会话中能不能修改 mylock 表：并不行。。。

```
################# session1 中的操作 #################

mysql> update mylock set name='a2' where id=1;
ERROR 1099 (HY000): Table 'mylock' was locked with a READ lock and can't be updated
```

- 在 session2 会话中能不能修改 mylock 表：阻塞，一旦 mylock 表锁释放，则会执行修改操作

```
################# session2 中的操作 #################

mysql> update mylock set name='a2' where id=1;
# 在这里阻塞着呢~~~
```

**结论**

1. 当前 session 和其他 session 均可以读取加了读锁的表
2. 当前 session 不能读取其他表，并且不能修改加了读锁的表
3. 其他 session 想要修改加了读锁的表，必须等待其读锁释放

#### 4.2.1.2. 、写锁示例

> **写锁示例**

- 在 session 1 会话中，给 mylock 表加个写锁

```
mysql> lock table mylock write;
Query OK, 0 rows affected (0.00 sec)
```

- 在 session1 会话中能不能读取 mylock 表：阔以

```
################# session1 中的操作 #################

mysql> select * from mylock;
+----+------+
| id | name |
+----+------+
|  1 | a2   |
|  2 | b    |
|  3 | c    |
|  4 | d    |
|  5 | e    |
+----+------+
5 rows in set (0.00 sec)
```

- 在 session1 会话中能不能读取 book 表：不阔以

```
################# session1 中的操作 #################

mysql> select * from book;
ERROR 1100 (HY000): Table 'book' was not locked with LOCK TABLES
```

- 在 session1 会话中能不能修改 mylock 表：当然可以啦，加写锁就是为了修改呀

```
################# session1 中的操作 #################

mysql> update mylock set name='a2' where id=1;
Query OK, 0 rows affected (0.00 sec)
Rows matched: 1  Changed: 0  Warnings: 0
```

- 在 session2 会话中能不能读取 mylock 表：

```
################# session2 中的操作 #################

mysql> select * from mylock;
# 在这里阻塞着呢~~~
```

**结论**

1. 当前 session 可以读取和修改加了写锁的表
2. 当前 session 不能读取其他表
3. 其他 session 想要读取加了写锁的表，必须等待其读锁释放

> **案例结论**

1. MyISAM在执行查询语句（SELECT）前，会自动给涉及的所有表加**读锁**，在执行增删改操作前，会自动给涉及的表加**写锁**。
2. MySQL的表级锁有两种模式：
   - 表共享读锁（Table Read Lock）
   - 表独占写锁（Table Write Lock）

![image-20200805154049814](https://img-blog.csdnimg.cn/img_convert/5655870ace82d810473b8804a715db92.png)

------

结论：结合上表，所以对MyISAM表进行操作，会有以下情况：

1. 对MyISAM表的读操作（加读锁），不会阻塞其他进程对同一表的读请求，但会阻塞对同一表的写请求。只有当读锁释放后，才会执行其它进程的写操作。
2. 对MyISAM表的写操作（加写锁），会阻塞其他进程对同一表的读和写操作，只有当写锁释放后，才会执行其它进程的读写操作
3. 简而言之，就是读锁会阻塞写，但是不会堵塞读。而写锁则会把读和写都堵塞。

### 4.2.2. 、表锁分析

> **表锁分析**

- 查看哪些表被锁了，0 表示未锁，1 表示被锁

```mysql
show open tables;
```

------

【如何分析表锁定】可以通过检查table_locks_waited和table_locks_immediate状态变量来分析系统上的表锁定，通过 `show status like 'table%';` 命令查看

1. Table_locks_immediate：产生表级锁定的次数，表示可以立即获取锁的查询次数，每立即获取锁值加1；
2. Table_locks_waited：出现表级锁定争用而发生等待的次数（不能立即获取锁的次数，每等待一次锁值加1），此值高则说明存在着较严重的表级锁争用情况；

```
mysql> show status like 'table%';
+----------------------------+--------+
| Variable_name              | Value  |
+----------------------------+--------+
| Table_locks_immediate      | 500440 |
| Table_locks_waited         | 1      |
| Table_open_cache_hits      | 500070 |
| Table_open_cache_misses    | 5      |
| Table_open_cache_overflows | 0      |
+----------------------------+--------+
5 rows in set (0.00 sec)
```

- 此外，Myisam的读写锁调度是写优先，这也是myisam不适合做写为主表的引擎。因为写锁后，其他线程不能做任何操作，**大量的更新会使查询很难得到锁**，从而造成永远阻塞

## 4.3. 、行锁

> **行锁的特点**

1. **偏向InnoDB存储引擎**，开销大，加锁慢；会出现死锁；**锁定粒度最小，发生锁冲突的概率最低，并发度也最高**。
2. InnoDB与MyISAM的最大不同有两点：**一是支持事务（TRANSACTION）；二是采用了行级锁**

### 4.3.1. 、事务复习

> **行锁支持事务，复习下老知识**

**事务（Transation）及其ACID属性**

事务是由一组SQL语句组成的逻辑处理单元，事务具有以下4个属性，通常简称为事务的ACID属性。

1. **原子性**（Atomicity）：事务是一个原子操作单元，其对数据的修改，要么全都执行，要么全都不执行。
2. **一致性**（Consistent）：在事务开始和完成时，数据都必须保持一致状态。这意味着所有相关的数据规则都必须应用于事务的修改，以保持数据的完整性；事务结束时，所有的内部数据结构（如B树索引或双向链表）也都必须是正确的。
3. **隔离性**（Isolation）：数据库系统提供一定的隔离机制，保证事务在不受外部并发操作影响的“独立”环境执行。这意味着事务处理过程中的中间状态对外部是不可见的，反之亦然。
4. **持久性**（Durability）：事务院成之后，它对于数据的修改是永久性的，即使出现系统故障也能够保持。

------

**并发事务处理带来的问题**

1. 更新丢失

   （Lost Update）：

   - 当两个或多个事务选择同一行，然后基于最初选定的值更新该行时，由于每个事务都不知道其他事务的存在，就会发生丢失更新问题一一最后的更新覆盖了由其他事务所做的更新。
   - 例如，两个程序员修改同一java文件。每程序员独立地更改其副本，然后保存更改后的副本，这样就覆盖了原始文档。最后保存其更改副本的编辑人员覆盖前一个程序员所做的更改。
   - 如果在一个程序员完成并提交事务之前，另一个程序员不能访问同一文件，则可避免此问题。

2. 脏读

   （Dirty Reads）：

   - 一个事务正在对一条记录做修改，在这个事务完成并提交前，这条记录的数据就处于不一致状态；这时，另一个事务也来读取同一条记录，如果不加控制，第二个事务读取了这些“脏”数据，并据此做进一步的处理，就会产生未提交的数据依赖关系。这种现象被形象地叫做”脏读”。
   - 一句话：事务A读取到了事务B已修改但尚未提交的的数据，还在这个数据基础上做了操作。此时，如果B事务回滚，A读取的数据无效，不符合一致性要求。

3. 不可重复读

   （Non-Repeatable Reads）：

   - 一个事务在读取某些数据后的某个时间，再次读取以前读过的数据，却发现其读出的数据已经发生了改变、或某些记录已经被删除了！这种现象就叫做“不可重复读”。
   - 一句话：事务A读取到了事务B已经提交的修改数据，不符合隔离性

4. 幻读

   （Phantom Reads）

   - 一个事务按相同的查询条件重新读取以前检索过的数据，却发现其他事务插入了满足其查询条件的新数据，这种现象就称为“幻读一句话：事务A读取到了事务B体提交的新增数据，不符合隔离性。
   - 多说一句：幻读和脏读有点类似，脏读是事务B里面修改了数据，幻读是事务B里面新增了数据。

------

**事物的隔离级别**

1. 脏读”、“不可重复读”和“幻读”，其实都是数据库读一致性问题，必须由数据库提供一定的事务隔离机制来解决。
2. 数据库的事务隔离越严格，并发副作用越小，但付出的代价也就越大，因为事务隔离实质上就是使事务在一定程度上“串行化”进行，这显然与“并发”是矛盾的。
3. 同时，不同的应用对读一致性和事务隔离程度的要求也是不同的，比如许多应用对“不可重复读”和“幻读”并不敏感，可能更关心数据并发访问的能力。
4. 查看当前数据库的事务隔离级别：`show variables like 'tx_isolation';` mysql 默认是可重复读

![image-20200805155415247](https://img-blog.csdnimg.cn/img_convert/762c292ec819d23175d4974181676a2a.png)

### 4.3.2. 、行锁案例

> **行锁案例分析**

**创建表**

- 建表 SQL

```mysql
CREATE TABLE test_innodb_lock (a INT(11),b VARCHAR(16))ENGINE=INNODB;

INSERT INTO test_innodb_lock VALUES(1,'b2');
INSERT INTO test_innodb_lock VALUES(3,'3');
INSERT INTO test_innodb_lock VALUES(4, '4000');
INSERT INTO test_innodb_lock VALUES(5,'5000');
INSERT INTO test_innodb_lock VALUES(6, '6000');
INSERT INTO test_innodb_lock VALUES(7,'7000');
INSERT INTO test_innodb_lock VALUES(8, '8000');
INSERT INTO test_innodb_lock VALUES(9,'9000');
INSERT INTO test_innodb_lock VALUES(1,'b1');

CREATE INDEX test_innodb_a_ind ON test_innodb_lock(a);
CREATE INDEX test_innodb_lock_b_ind ON test_innodb_lock(b);
```

- test_innodb_lock 表中的测试数据

```
mysql> select * from test_innodb_lock;
+------+------+
| a    | b    |
+------+------+
|    1 | b2   |
|    3 | 3    |
|    4 | 4000 |
|    5 | 5000 |
|    6 | 6000 |
|    7 | 7000 |
|    8 | 8000 |
|    9 | 9000 |
|    1 | b1   |
+------+------+
9 rows in set (0.00 sec)
```

- test_innodb_lock 表中的索引

```
mysql> SHOW INDEX FROM test_innodb_lock;
+------------------+------------+------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| Table            | Non_unique | Key_name               | Seq_in_index | Column_name | Collation | Cardinality | Sub_part | Packed | Null | Index_type | Comment | Index_comment |
+------------------+------------+------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
| test_innodb_lock |          1 | test_innodb_a_ind      |            1 | a           | A         |           9 |     NULL | NULL   | YES  | BTREE      |         |               |
| test_innodb_lock |          1 | test_innodb_lock_b_ind |            1 | b           | A         |           9 |     NULL | NULL   | YES  | BTREE      |         |               |
+------------------+------------+------------------------+--------------+-------------+-----------+-------------+----------+--------+------+------------+---------+---------------+
2 rows in set (0.00 sec)
```

> **操作同一行数据**

- session1 开启事务，修改 test_innodb_lock 中的数据

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='4001' where a=4;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

- session2 开启事务，修改 test_innodb_lock 中同一行数据，将导致 session2 发生阻塞，一旦 session1 提交事务，session2 将执行更新操作

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='4002' where a=4;
# 在这儿阻塞着呢~~~

# 时间太长，会报超时错误哦
mysql> update test_innodb_lock set b='4001' where a=4;
ERROR 1205 (HY000): Lock wait timeout exceeded; try restarting transaction
```

> **操作不同行数据**

- session1 开启事务，修改 test_innodb_lock 中的数据

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='4001' where a=4;
Query OK, 0 rows affected (0.00 sec)
Rows matched: 1  Changed: 0  Warnings: 0
```

- session2 开启事务，修改 test_innodb_lock 中不同行的数据
- 由于采用行锁，session2 和 session1 互不干涉，所以 session2 中的修改操作没有阻塞

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='9001' where a=9;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

> **无索引导致行锁升级为表锁**

- session1 开启事务，修改 test_innodb_lock 中的数据，varchar 不用 ’ ’ ，导致系统自动转换类型，导致索引失效

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set a=44 where b=4000;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

- session2 开启事务，修改 test_innodb_lock 中不同行的数据
- 由于发生了自动类型转换，索引失效，导致行锁变为表锁

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='9001' where a=9;
# 在这儿阻塞着呢~~~
```

### 4.3.3. 、间隙锁

> **什么是间隙锁**

1. 当我们用范围条件而不是相等条件检索数据，并请求共享或排他锁时，InnoDB会给符合条件的已有数据记录的索引项加锁；对于键值在条件范围内但并不存在的记录，叫做“间隙（GAP）”
2. InnoDB也会对这个“间隙”加锁，这种锁机制是所谓的间隙锁（Next-Key锁）

> **间隙锁的危害**

1. 因为Query执行过程中通过过范围查找的话，他会锁定整个范围内所有的索引键值，即使这个键值并不存在。
2. 间隙锁有一个比较致命的弱点，就是当锁定一个范围键值之后，即使某些不存在的键值也会被无辜的锁定，而造成在锁定的时候无法插入锁定键值范围内的任何数据。在某些场景下这可能会对性能造成很大的危害

------

**间隙锁示例**

- test_innodb_lock 表中的数据

```
mysql> select * from test_innodb_lock;
+------+------+
| a    | b    |
+------+------+
|    1 | b2   |
|    3 | 3    |
|    4 | 4000 |
|    5 | 5000 |
|    6 | 6000 |
|    7 | 7000 |
|    8 | 8000 |
|    9 | 9000 |
|    1 | b1   |
+------+------+
9 rows in set (0.00 sec)
```

- session1 开启事务，执行修改 a > 1 and a < 6 的数据，这会导致 mysql 将 a = 2 的数据行锁住（虽然表中并没有这行数据）

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='Heygo' where a>1 and a<6;
Query OK, 3 rows affected (0.00 sec)
Rows matched: 3  Changed: 3  Warnings: 0
```

- session2 开启事务，修改 test_innodb_lock 中不同行的数据，也会导致阻塞，直至 session1 提交事务

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='9001' where a=9;
# 在这儿阻塞着呢~~~
```

### 4.3.4. 、手动行锁

> **如何锁定一行**

- `select xxx ... for update` 锁定某一行后，其它的操作会被阻塞，直到锁定行的会话提交
- session1 开启事务，手动执行 for update 锁定指定行，待执行完指定操作时再将数据提交

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> select * from test_innodb_lock  where a=8 for update;
+------+------+
| a    | b    |
+------+------+
|    8 | 8000 |
+------+------+
1 row in set (0.00 sec)
```

- session2 开启事务，修改 session1 中被锁定的行，会导致阻塞，直至 session1 提交事务

```
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> update test_innodb_lock set b='XXX' where a=8;
# 在这儿阻塞着呢~~~
```

### 4.3.5. 、行锁分析

> **案例结论**

1. Innodb存储引擎由于实现了行级锁定，虽然在锁定机制的实现方面所带来的性能损耗可能比表级锁定会要更高一些，但是在整体并发处理能力方面要远远优于MyISAM的表级锁定的。
2. **当系统并发量较高的时候，Innodb的整体性能和MyISAM相比就会有比较明显的优势了**。
3. 但是，Innodb的行级锁定同样也有其脆弱的一面，当我们使用不当的时候（索引失效，导致行锁变表锁），可能会让Innodb的整体性能表现不仅不能比MyISAM高，甚至可能会更差。

> **行锁分析**

**如何分析行锁定**

- 通过检查InnoDB_row_lock状态变量来分析系统上的行锁的争夺情况

```mysql
show status like 'innodb_row_lock%';
mysql> show status like 'innodb_row_lock%';
+-------------------------------+--------+
| Variable_name                 | Value  |
+-------------------------------+--------+
| Innodb_row_lock_current_waits | 0      |
| Innodb_row_lock_time          | 212969 |
| Innodb_row_lock_time_avg      | 42593  |
| Innodb_row_lock_time_max      | 51034  |
| Innodb_row_lock_waits         | 5      |
+-------------------------------+--------+
5 rows in set (0.00 sec)
```

**对各个状态量的说明如下：**

1. Innodb_row_lock_current_waits：当前正在等待锁定的数量；
2. Innodb_row_lock_time：从系统启动到现在锁定总时间长度；
3. Innodb_row_lock_time_avg：每次等待所花平均时间；
4. Innodb_row_lock_time_max：从系统启动到现在等待最常的一次所花的时间；
5. Innodb_row_lock_waits：系统启动后到现在总共等待的次数；

------

**对于这5个状态变量，比较重要的主要是**

1. Innodb_row_lock_time_avg（等待平均时长）
2. Innodb_row_lock_waits（等待总次数）
3. Innodb_row_lock_time（等待总时长）

------

尤其是当等待次数很高，而且每次等待时长也不小的时候，我们就需要分析系统中为什么会有如此多的等待，然后根据分析结果着手指定优化计划。

### 4.3.6. 、行锁优化

> **优化建议**

1. 尽可能让所有数据检索都通过索引来完成，避免无索引行锁升级为表锁
2. 合理设计索引，尽量缩小锁的范围
3. 尽可能较少检索条件，避免间隙锁
4. 尽量控制事务大小，减少锁定资源量和时间长度
5. 尽可能低级别事务隔离

## 4.4. 、页锁

1. 开销和加锁时间界于表锁和行锁之间：会出现死锁；
2. 锁定粒度界于表锁和行锁之间，并发度一般。
3. 了解即可





# 5. 第 5 章 主从复制

## 5.1. 、复制的基本原理

> **复制的基本原理**

**slave会从master读取binlog来进行数据同步，主从复制的三步骤**

1. master将改变记录到二进制日志（binary log）。这些记录过程叫做**二进制日志事件**（binary log events）
2. slave将master的binary log events拷贝到它的**中继日志（relay log）**
3. **slave重做中继日志中的事件**，将改变应用到自己的数据库中。MySQL复制是异步的且串行化的

![image-20200805190201268](https://img-blog.csdnimg.cn/img_convert/c21246f5720506676e51b872ab77b7ce.png)

## 5.2. 、复制的基本原则

1. 每个slave只有一个master
2. 每个slave只能有一个唯一的服务器ID
3. 每个master可以有多个salve

## 5.3. 、复制最大问题

因为发生多次 IO， 存在延时问题

## 5.4. 、一主一从常见配置

> **前提：mysql 版本一致，主从机在同一网段下**

**ping 测试**

- Linux 中 ping Windows

```
[root@Heygo 桌面]# ping 10.206.207.131
PING 10.206.207.131 (10.206.207.131) 56(84) bytes of data.
64 bytes from 10.206.207.131: icmp_seq=1 ttl=128 time=1.27 ms
64 bytes from 10.206.207.131: icmp_seq=2 ttl=128 time=0.421 ms
64 bytes from 10.206.207.131: icmp_seq=3 ttl=128 time=1.12 ms
64 bytes from 10.206.207.131: icmp_seq=4 ttl=128 time=0.515 ms
^C
--- 10.206.207.131 ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3719ms
rtt min/avg/max/mdev = 0.421/0.835/1.279/0.373 ms
[root@Heygo 桌面]# 
```

- Windows 中 ping Linux

```
C:\Users\Heygo>ping 192.168.152.129

正在 Ping 192.168.152.129 具有 32 字节的数据:
来自 192.168.152.129 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.152.129 的回复: 字节=32 时间<1ms TTL=64
来自 192.168.152.129 的回复: 字节=32 时间=1ms TTL=64
来自 192.168.152.129 的回复: 字节=32 时间<1ms TTL=64

192.168.152.129 的 Ping 统计信息:
    数据包: 已发送 = 4，已接收 = 4，丢失 = 0 (0% 丢失)，
往返行程的估计时间(以毫秒为单位):
    最短 = 0ms，最长 = 1ms，平均 = 0ms
```

> **主机修改 my.ini 配置文件（Windows）**

主从都配置都在 [mysqld] 节点下，都是小写，以下是老师的配置文件

![image-20200812191912521](https://img-blog.csdnimg.cn/img_convert/6724f51a0ff16d8b1df9ab8d71190060.png)

------

**以下两条为必须配置**

- 配置主机 id

```ini
server-id=1
```

- 启用二进制日志

```ini
log-bin=C:/Program Files (x86)/MySQL/MySQL Server 5.5/log-bin/mysqlbin
```

------

**以下为非必须配置**

- 启动错误日志

```ini
log-err=C:/Program Files (x86)/MySQL/MySQL Server 5.5/log-bin/mysqlerr
```

- 根目录

```ini
basedir="C:/Program Files (x86)/MySQL/MySQL Server 5.5/"
```

- 临时目录

```ini
tmpdir="C:/Program Files (x86)/MySQL/MySQL Server 5.5/"
```

- 数据目录

```ini
datadir="C:/Program Files (x86)/MySQL/MySQL Server 5.5/Data/"
```

- 主机，读写都可以

```init
read-only=0
```

- 设置不要复制的数据库

```ini
binlog-ignore-db=mysql
```

- 设置需要复制的数据

```ini
binlog-do-db=需要复制的主数据库名字
```

> **从机修改 my.cnf 配置文件（Linux）**

- 【必须】从服务器唯一ID

```ini
server-id=2
```

- 【可选】启用二进制文件

> **修改配置文件后的准备工作**

**因修改过配置文件，主机+从机都重启 mysql 服务**

- Windows

```bash
net stop mysql
net start mysql
```

- Linux

```bash
service mysqld restart
```

------

**主机从机都关闭防火墙**

- Windows 手动关闭防火墙
- 关闭虚拟机 linux 防火墙

```bash
service iptables stop
```

> **在 Windows 主机上简历账户并授权 slave**

- 创建用户， 并赋予从机 `REPLICATION` 权限（从主机的数据库表中复制表）

```bash
GRANT REPLICATION SLAVE ON *.* TO '备份账号'@'从机器数据库 IP' IDENTIFIED BY '账号密码';
GRANT REPLICATION SLAVE ON *.* TO 'Heygo'@'192.168.152.129' IDENTIFIED BY '123456';
```

- 刷新权限信息

```bash
flush privileges;
mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)
```

- 通过 `select * from mysql.user where user='Heygo'\G;` 命令可查看：从机只有 `Repl_slave_priv` 权限为 `Y`，其余权限均为 `N`

```
mysql> select * from mysql.user where user='Heygo'\G;
*************************** 1. row ***************************
                  Host: %
                  User: Heygo
              Password: *6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9
           Select_priv: N
           Insert_priv: N
           Update_priv: N
           Delete_priv: N
           Create_priv: N
             Drop_priv: N
           Reload_priv: N
         Shutdown_priv: N
          Process_priv: N
             File_priv: N
            Grant_priv: N
       References_priv: N
            Index_priv: N
            Alter_priv: N
          Show_db_priv: N
            Super_priv: N
 Create_tmp_table_priv: N
      Lock_tables_priv: N
          Execute_priv: N
       Repl_slave_priv: Y
      Repl_client_priv: N
      Create_view_priv: N
        Show_view_priv: N
   Create_routine_priv: N
    Alter_routine_priv: N
      Create_user_priv: N
            Event_priv: N
          Trigger_priv: N
Create_tablespace_priv: N
              ssl_type:
            ssl_cipher:
           x509_issuer:
          x509_subject:
         max_questions: 0
           max_updates: 0
       max_connections: 0
  max_user_connections: 0
                plugin:
 authentication_string: NULL
1 row in set (0.00 sec)
```

- 查询 master 的状态，将 File 和 Position 记录下来，在启动 Slave 时需要用到这两个参数

```bash
show master status;
mysql> show master status;
+------------------+----------+--------------+------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB |
+------------------+----------+--------------+------------------+
| mysql-bin.000001 |      107 | mysql        |                  |
+------------------+----------+--------------+------------------+
1 row in set (0.00 sec)
```

> **在 Linux 从上验证是否能登陆主机的 MySQL**

- 在从机上执行 `mysql -h 10.206.207.131 -uHeygo -p` 命令，发现无法连接主机的 MySQL 数据库

```
[root@Heygo 桌面]# mysql -h 10.206.207.131 -uHeygo -p
Enter password: 
ERROR 1130 (HY000): Host 'windows10.microdone.cn' is not allowed to connect to this MySQL server
```

- 查阅资料发现：当你远程登录 MySQL 时，使用的账号要有特殊要求，如果要使用某个账号来远程登录，必须将账号的 host 属性值更改成 `%`。我敲，阳哥怎么就成功了呢？可以看到：我们在执行了 `GRANT REPLICATION SLAVE ON *.* TO 'Heygo'@'192.168.152.129' IDENTIFIED BY '123456';` 命令之后，Heygo 账户的 host 属性为 `192.168.152.129`

```
mysql> select user,host,plugin from user;
+-------+-----------------+--------+
| user  | host            | plugin |
+-------+-----------------+--------+
| root  | localhost       |        |
| root  | 192.168.152.129 |        |
| Heygo | 192.168.152.129 |        |
+-------+-----------------+--------+
3 rows in set (0.00 sec)
```

- 于是我先使用 `update user set host = '%' where user = 'Heygo';` 命令将 Heygo 账户的 `host` 字段设置为 `%`；然后使用 `flush privileges;` 命令刷新权限信息

```
mysql> update user set host = '%' where user = 'Heygo';
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)

mysql> select user,host,plugin from user;
+-------+-----------------+--------+
| user  | host            | plugin |
+-------+-----------------+--------+
| root  | localhost       |        |
| root  | 192.168.152.129 |        |
| Heygo | %               |        |
+-------+-----------------+--------+
3 rows in set (0.00 sec)
```

- 在 Linux 从机上使用 `mysql -h 10.206.207.131 -uHeygo -p` 命令能够成功连接上主机上的 MySQL 数据库。我敲，谜一样

```
[root@Heygo 桌面]# mysql -h 10.206.207.131 -uHeygo -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 33
Server version: 5.5.15-log MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
```

> **在 Linux 从机上配置需要复制的主机**

- 从机进行认证

```bash
CHANGE MASTER TO 
MASTER_HOST='主机 IP',
MASTER_USER='创建用户名',
MASTER_PASSWORD='创建的密码',
MASTER_LOG_FILE='File 名字',
MASTER_LOG_POS=Position数字;
CHANGE MASTER TO 
MASTER_HOST='10.206.207.131',
MASTER_USER='Heygo',
MASTER_PASSWORD='123456',
MASTER_LOG_FILE='mysql-bin.000001',
MASTER_LOG_POS=107;
```

- 启动从服务器复制功能

```bash
start slave;
```

- 查看从机复制功能是否启动成功：使用 `show slave status\G;` 命令查看 `Slave_SQL_Running:Yes` 和 `Slave_IO_Running:Yes` 说明从机连接主机成功（第一次测试没有成功，这是隔了半年之后的测试，因此某些数据会有出入）

```
mysql> show slave status\G;
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 10.206.207.131
                  Master_User: Heygo
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000052
          Read_Master_Log_Pos: 4274
               Relay_Log_File: mysqld-relay-bin.000063
                Relay_Log_Pos: 2998
        Relay_Master_Log_File: mysql-bin.000052
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB: 
          Replicate_Ignore_DB: 
           Replicate_Do_Table: 
       Replicate_Ignore_Table: 
      Replicate_Wild_Do_Table: 
  Replicate_Wild_Ignore_Table: 
                   Last_Errno: 0
                   Last_Error: 
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 4274
              Relay_Log_Space: 4749
              Until_Condition: None
               Until_Log_File: 
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File: 
           Master_SSL_CA_Path: 
              Master_SSL_Cert: 
            Master_SSL_Cipher: 
               Master_SSL_Key: 
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error: 
               Last_SQL_Errno: 0
               Last_SQL_Error: 
  Replicate_Ignore_Server_Ids: 
             Master_Server_Id: 1
                  Master_UUID: 
             Master_Info_File: /var/lib/mysql/master.info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for the slave I/O thread to update it
           Master_Retry_Count: 86400
                  Master_Bind: 
      Last_IO_Error_Timestamp: 
     Last_SQL_Error_Timestamp: 
               Master_SSL_Crl: 
           Master_SSL_Crlpath: 
           Retrieved_Gtid_Set: 
            Executed_Gtid_Set: 
                Auto_Position: 0
1 row in set (0.00 sec)
```

- 如何停止从服务复制功能

```bash
stop slave;
```
