
# 1. nginx简介

- **Nginx (engine x)**
  - 是一款轻量级的 Web 服务器
  - 反向代理服务器
  - 及电子邮件（IMAP/POP3）代理服务器。

  ![nginx-1.png](./image/nginx-1.png)

- **代理（Proxy）与反向代理（Reverse Proxy）**
  - 代理：
    - 是指 **客户端知道要访问服务器的地址**
    - 并主动将连接请求发送给代理服务器，代理服务器代为访问，代理服务器仅作为一个跳板。
    - 代理服务器会去访问服务器上，并将得到的结果返回给客户端。
    - 本质上是
      - **客户端与其他所有服务端之间的代理**
      - 对客户端负责
  - 反向代理
    - 是指以代理服务器来接受 internet 上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给 internet 上请求连接的客户端，
    - 此时代理服务器对外就表现为一个反向代理服务器。
    - 在客户端看来，代理服务器就是一个提供服务的服务端，用户并不会感知到代理。
    - 本质上是
      - **客户端和所要代理的服务器之间的代理。**
      - 对所代理服务器负责

  ![nginx-2](./image/nginx-2.png)

# 2. 基本命令

- `nginx -s stop`: 快速关闭 Nginx，可能不保存相关信息，并迅速终止 web 服务。
- `nginx -s quit`: 平稳关闭 Nginx，保存相关信息，有安排的结束 web 服务。
- `nginx -s reload`: 因改变了 Nginx 相关配置，需要重新加载配置而重载。
- `nginx -s reopen`: 重新打开日志文件。
- `nginx -c filename`: 为 Nginx 指定一个配置文件，来代替缺省的。
  > 默认配置文件是 `conf/nginx.conf`
- `nginx -t`: 不运行，仅仅测试配置文件。nginx 将检查配置文件的语法的正确性，并尝试打开配置文件中所引用到的文件。
- `nginx -v`: 显示 nginx 的版本。
- `nginx -V`: 显示 nginx 的版本，编译器版本和配置参数。

# 3. 基本原理

# 4. 配置详解

Nginx的指令远远不止下面这些。
最主要的还是讲解整个配置文件的结构，如果想要看比较全的指令介绍、模块介绍的话，建议去Nginx的官网。

## 4.1. 基本说明

- 组成：Nginx的主配置文件是nginx.conf
  - 这个配置文件一共由三部分组成，分别为 **全局块、events块和http块**
  - 在http块中，又包含
    - http全局块
    - 多个server块
      - 每个server块中，可以包含server全局块和多个location块
  - 在同一配置块中嵌套的配置块，各个之间不存在次序关系。

- 作用域
  - 配置文件支持大量可配置的指令， **绝大多数指令不是特定属于某一个块的** 。
  - 同一个指令放在不同层级的块中，其作用域也不同
  - 一般情况下，高一级块中的指令可以作用于自身所在的块和此块包含的所有低层级块
  - 如果某个指令在两个不同层级的块中同时出现，则采用 **“就近原则”**
    - 即以较低层级块中的配置为准
    - 比如，某指令同时出现在http全局块中和server块中，并且配置不同
    - 则应该以server块中的配置为准。

## 4.2. 配置文件结构

```nginx
  #全局块
  #user  nobody;
  worker_processes  1;

  #event块
  events {
      worker_connections  1024;
  }

  #http块
  http {
      #http全局块
      include       mime.types;
      default_type  application/octet-stream;
      sendfile        on;
      keepalive_timeout  65;
      #server块
      server {
          #server全局块
          listen       8000;
          server_name  localhost;
          #location块
          location / {
              root   html;
              index  index.html index.htm;
          }
          error_page   500 502 503 504  /50x.html;
          location = /50x.html {
              root   html;
          }
      }
      #这边可以有多个server块
      server {
        ...
      }
  }
```

## 4.3. 结构说明

### 4.3.1. 全局块

- 位置：默认配置文件从开始到events块之间
- 作用：主要设置一些 **影响Nginx服务器整体运行的配置指令**
- 作用域： **Nginx服务器全局**
- 指令通常包括
  - 配置运行Nginx服务器的用户（组）
  - 允许生成的worker process数
  - Nginx进程PID存放路径
  - 日志的存放路径和类型
  - 以及配置文件引入等。

- 指令说明：

  ```nginx
  # 指定可以运行nginx服务的用户和用户组，只能在全局块配置
  # user [user] [group]
  # 将user指令注释掉，或者配置成nobody的话所有用户都可以运行
  user nobody nobody;
  # user指令在Windows上不生效，如果你制定具体用户和用户组会报小面警告
  # nginx: [warn] "user" is not supported, ignored in D:\software\nginx-1.18.0/conf/nginx.conf:2

  # 指定工作线程数，可以制定具体的进程数，也可使用自动模式，这个指令只能在全局块配置
  worker_processes number | auto；
  # 列子：指定4个工作线程，这种情况下会生成一个master进程和4个worker进程
  worker_processes 4;

  # 指定pid文件存放的路径，这个指令只能在全局块配置
  pid logs/nginx.pid;

  # 指定错误日志的路径和日志级别，此指令可以在全局块、http块、server块以及location块中配置。(在不同的块配置有啥区别？？)
  # 其中debug级别的日志需要编译时使用--with-debug开启debug开关
  error_log [path] [debug | info | notice | warn | error | crit | alert | emerg]
  error_log  logs/error.log  notice;
  error_log  logs/error.log  info;
  ```

### 4.3.2. events块

- 作用：
  - events块涉及的指令主要影响 **Nginx服务器与用户的网络连接**
  - 这一部分的指令对Nginx服务器的性能影响较大，在实际配置中应该根据实际情况灵活调整。
- 常用到的指令包括
  - 是否开启对多worker process下的网络连接进行序列化
  - 是否允许同时接收多个网络连接
  - 选取哪种事件驱动模型处理连接请求
  - 每个worker process可以同时支持的最大连接数等
- 命令

  ```nginx
  # 当某一时刻只有一个网络连接到来时，多个睡眠进程会被同时叫醒，但只有一个进程可获得连接。
  # 如果每次唤醒的进程数目太多，会影响一部分系统性能。在Nginx服务器的多进程下，就有可能出现这样的问题。
  # 开启的时候，将会对多个Nginx进程接收连接进行序列化，防止多个进程对连接的争抢
  # 默认是开启状态，只能在events块中进行配置
  accept_mutex on | off;

  # 如果multi_accept被禁止了，nginx一个工作进程只能同时接受一个新的连接。否则，一个工作进程可以同时接受所有的新连接。
  # 如果nginx使用kqueue连接方法，那么这条指令会被忽略，因为这个方法会报告在等待被接受的新连接的数量。
  # 默认是off状态，只能在event块配置
  multi_accept on | off;

  # 指定使用哪种网络IO模型，method可选择的内容有：select、poll、kqueue、epoll、rtsig、/dev/poll以及eventport，一般操作系统不是支持上面所有模型的。
  # 只能在events块中进行配置
  use method
  use epoll

  # 设置允许每一个worker process同时开启的最大连接数，当每个工作进程接受的连接数超过这个值时将不再接收连接
  # 当所有的工作进程都接收满时，连接进入logback，logback满后连接被拒绝
  # 只能在events块中进行配置
  # 注意：这个值不能超过超过系统支持打开的最大文件数，也不能超过单个进程支持打开的最大文件数，具体可以参考这篇文章：https://cloud.tencent.com/developer/article/1114773
  worker_connections  1024;
  ```

### 4.3.3. http块

- 作用：
  - http块是Nginx服务器配置中的重要部分
  - 代理、缓存和日志定义等绝大多数的功能和第三方模块的配置都可以放在这个模块中。

- 包含关系：
  - 前面已经提到，http块中可以包含自己的全局块，也可以包含server块
  - server块中又可以进一步包含location块
  - 使用“http全局块”来表示http中自己的全局块，即http块中不包含在server块中的部分。

- 指令内容：可以在http全局块中配置的指令包括:
  - 文件引入
  - MIME-Type定义
  - 日志自定义
  - 是否使用sendfile传输文件
  - 连接超时时间
  - 单连接请求数上限等。

- 指令

  ```nginx
  # 常用的浏览器中，可以显示的内容有HTML、XML、GIF及Flash等种类繁多的文本、媒体等资源，浏览器为区分这些资源，需要使用MIME Type。换言之，MIME Type是网络资源的媒体类型。Nginx服务器作为Web服务器，必须能够识别前端请求的资源类型。

  # include指令，用于包含其他的配置文件，可以放在配置文件的任何地方，但是要注意你包含进来的配置文件一定符合配置规范，
  # 比如说你include进来的配置是worker_processes指令的配置，而你将这个指令包含到了http块中，着肯定是不行的，上面已经介绍过worker_processes指令只能在全局块中。
  # 下面的指令将mime.types包含进来，mime.types和ngin.cfg同级目录，不同级的话需要指定具体路径
  include  mime.types;

  # 配置默认类型，如果不加此指令，默认值为text/plain。
  # 此指令还可以在http块、server块或者location块中进行配置。
  default_type  application/octet-stream;

  # access_log配置，此指令可以在http块、server块或者location块中进行设置
  # 在全局块中，我们介绍过errer_log指令，其用于配置Nginx进程运行时的日志存放和级别，
  # 此处所指的日志与常规的不同，它是指记录Nginx服务器提供服务过程应答前端请求的日志
  access_log path [format [buffer=size]]
  # 如果你要关闭access_log,你可以使用下面的命令
  access_log off;

  # log_format指令，用于定义日志格式，此指令只能在http块中进行配置
  log_format  main '$remote_addr - $remote_user [$time_local] "$request" '
                   '$status $body_bytes_sent "$http_referer" '
                   '"$http_user_agent" "$http_x_forwarded_for"';
  # 定义了上面的日志格式后，可以以下面的形式使用日志
  access_log  logs/access.log  main;

  # 开启关闭sendfile方式传输文件，可以在http块、server块或者location块中进行配置
  sendfile  on | off;

  # 设置sendfile最大数据量,此指令可以在http块、server块或location块中配置
  sendfile_max_chunk size;
  # 其中，size值如果大于0，Nginx进程的每个worker process每次调用sendfile()传输的数据量最大不能超过这个值(这里是128k，所以每次不能超过128k)
  # 如果设置为0，则无限制。默认值为0。
  sendfile_max_chunk 128k;

  # 配置连接超时时间,此指令可以在http块、server块或location块中配置。
  # 与用户建立会话连接后，Nginx服务器可以保持这些连接打开一段时间
  # timeout，服务器端对连接的保持时间。默认值为75s
  # header_timeout，可选项，在应答报文头部的Keep-Alive域设置超时时间：“Keep-Alive:timeout= header_timeout”。报文中的这个指令可以被Mozilla或者Konqueror识别。
  keepalive_timeout timeout [header_timeout]
  # 下面配置的含义是，在服务器端保持连接的时间设置为120 s，发给用户端的应答报文头部中Keep-Alive域的超时时间设置为100 s。
  keepalive_timeout 120s 100s

  # 配置单连接请求数上限，此指令可以在http块、server块或location块中配置。
  # Nginx服务器端和用户端建立会话连接后，用户端通过此连接发送请求。
  # 指令keepalive_requests用于限制用户通过某一连接向Nginx服务器发送请求的次数。默认是100
  keepalive_requests number;
  ```

### 4.3.4. server块

#### 4.3.4.1. 基本说明

- 虚拟主机：server块和“虚拟主机”的概念有密切联系。
  - 虚拟主机，又称虚拟服务器、主机空间或是网页空间，它是一种技术。
  - 该技术是为了节省互联网服务器硬件成本而出现的。
  - 这里的“主机”或“空间”是由实体的服务器延伸而来，硬件系统可以基于服务器群，或者单个服务器等。
  - 虚拟主机技术主要应用于HTTP、FTP及EMAIL等多项服务，将一台服务器的某项或者全部服务内容逻辑划分为多个服务单位，
  - 对外表现为多个服务器，从而充分利用服务器硬件资源。
  - **从用户角度来看，一台虚拟主机和一台独立的硬件主机是完全一样的**

- 虚拟主机作用：
  - 在使用Nginx服务器提供Web服务时，利用虚拟主机的技术就可以避免为每一个要运行的网站提供单独的Nginx服务器，也无需为每个网站对应运行一组Nginx进程。
  - 虚拟主机技术使得Nginx服务器可以在同一台服务器上只运行一组Nginx进程，就可以运行多个网站。

- 在前面提到过，每一个http块都可以包含多个server块
  - 而 **每个server块就相当于一台虚拟主机** ，
  - htpp块内部可有多台主机联合提供服务，一起对外提供在逻辑上关系密切的一组服务（或网站）。

- server块的组成：
  - 和http块相同，server块也可以包含自己的 **全局块** ，同时可以包含多个 **location块**
  - 在server全局块中，最常见的两个配置项是本虚拟主机的监听配置和本虚拟主机的名称或IP配置。

#### 4.3.4.2. listen 指令

- 说明
  - server块中最重要的指令就是listen指令
  - 这个指令有三种配置语法。
  - 这个指令默认的配置值是：`listen *:80 | *:8000;`
  - 只能在server块种配置这个指令。

- 配置方式

  ```nginx
  # 第一种
  listen address[:port] [default_server] [ssl] [http2 | spdy] [proxy_protocol] [setfib=number] [fastopen=number] [backlog=number] [rcvbuf=size] [sndbuf=size] [accept_filter=filter] [deferred] [bind] [ipv6only=on|off] [reuseport] [so_keepalive=on|off|[keepidle]:[keepintvl]:[keepcnt]];

  # 第二种
  listen port [default_server] [ssl] [http2 | spdy] [proxy_protocol] [setfib=number] [fastopen=number] [backlog=number] [rcvbuf=size] [sndbuf=size] [accept_filter=filter] [deferred] [bind] [ipv6only=on|off] [reuseport] [so_keepalive=on|off|[keepidle]:[keepintvl]:[keepcnt]];

  # 第三种
  listen unix:path [default_server] [ssl] [http2 | spdy] [proxy_protocol] [backlog=number] [rcvbuf=size] [sndbuf=size] [accept_filter=filter] [deferred] [bind] [so_keepalive=on|off|[keepidle]:[keepintvl]:[keepcnt]];
  ```
  > listen指令的使用看起来比较复杂，但其实在一般的使用过程中，相对来说比较简单，并不会进行太复杂的配置

  - `address`：监听的 IP 地址（请求来源的 IP 地址），如果是 IPv6 的地址，需要使用中括号“[]”括起来，比如[fe80::1]等。
  - `port`：端口号，如果只定义了 IP 地址没有定义端口号，就使用 80 端口。这边需要做个说明：要是你压根没配置 listen 指令，那么那么如果 nginx 以超级用户权限运行，则使用*:80，否则使用*:8000。多个虚拟主机可以同时监听同一个端口,但是 server_name 需要设置成不一样；
  - `default_server`：假如通过 Host 没匹配到对应的虚拟主机，则通过这台虚拟主机处理。具体的可以参考这篇[文章](https://segmentfault.com/a/1190000015681272)，写的不错。
  - `backlog`=number：设置监听函数 listen()最多允许多少网络连接同时处于挂起状态，在 FreeBSD 中默认为-1，其他平台默认为 511。
  - `accept_filter`=filter，设置监听端口对请求的过滤，被过滤的内容不能被接收和处理。本指令只在 FreeBSD 和 NetBSD 5.0+平台下有效。filter 可以设置为 dataready 或 httpready，感兴趣的读者可以参阅 Nginx 的官方文档。
  - `bind`：标识符，使用独立的 bind()处理此 address:port；一般情况下，对于端口相同而 IP 地址不同的多个连接，Nginx 服务器将只使用一个监听命令，并使用 bind()处理端口相同的所有连接。
  - `ssl`：标识符，设置会话连接使用 SSL 模式进行，此标识符和 Nginx 服务器提供的 HTTPS 服务有关。

- listen指令的配置非常灵活，可以单独制定ip，单独指定端口或者同时指定ip和端口，示例：

  ```nginx
  listen 127.0.0.1:8000;  #只监听来自127.0.0.1这个IP，请求8000端口的请求
  listen 127.0.0.1; #只监听来自127.0.0.1这个IP，请求80端口的请求（不指定端口，默认80）
  listen 8000; #监听来自所有IP，请求8000端口的请求
  listen *:8000; #和上面效果一样
  listen localhost:8000; #和第一种效果一致

  # TODO listen unix:path
  ```

#### 4.3.4.3. server_name指令

- 配置虚拟主机的名称方式
  - 对于name 来说，可以只有一个名称，也可以由多个名称并列，之间用空格隔开
  - 每个名字就是一个域名，由两段或者三段组成，之间由点号“.”隔开。比如

    ```nginx
    server_name myserver.com www.myserver.com
    ```
    - 在该例中，此虚拟主机的名称设置为`myserver.com`或`www. myserver.com`
    - Nginx服务器规定，第一个名称作为此虚拟主机的主要名称。
    - 在name 中可以使用通配符“*”，但通配符只能用在由三段字符串组成的名称的首段或尾段，或者由两段字符串组成的名称的尾段，如：

      ```nginx
      server_name myserver.* *.myserver.com
      ```
    - 另外name还支持正则表达式的形式。这边就不详细展开了。

- 多个匹配成功情况：
  ```
  由于server_name指令支持使用通配符和正则表达式两种配置名称的方式
  因此在包含有多个虚拟主机的配置文件中，可能会出现一个名称被多个虚拟主机的server_name匹配成功
  那么，来自这个名称的请求到底要交给哪个虚拟主机处理呢？Nginx服务器做出如下规定：
  ```
  - a. 对于匹配方式不同的，按照以下的优先级选择虚拟主机，排在前面的优先处理请求。
    - ① 准确匹配server_name
    - ② 通配符在开始时匹配server_name成功
    - ③ 通配符在结尾时匹配server_name成功
    - ④ 正则表达式匹配server_name成功
  - b. 在以上四种匹配方式中，如果server_name被处于同一优先级的匹配方式多次匹配成功，则首次匹配成功的虚拟主机处理请求。

- 有时候需要使用基于IP地址的虚拟主机配置
  > 比如访问192.168.1.31有虚拟主机1处理，访问192.168.1.32由虚拟主机2处理。
  - 这时我们要先网卡绑定别名，比如说网卡之前绑定的IP是192.168.1.30
  - 现在将192.168.1.31和192.168.1.32这两个IP都绑定到这个网卡上
  - 那么请求这个两个IP的请求才会到达这台机器。
  - 绑定别名后进行以下配置即可：

  ```nginx
  http
  {
    {
      listen:  80;
      server_name:  192.168.1.31;
      ...
    }
    {
      listen:  80;
      server_name:  192.168.1.32;
      ...
    }
  }
  ```

### 4.3.5. location块

#### 4.3.5.1. 基本说明

- 位置：
  - 每个server块中可以包含多个location块。
  - 在整个Nginx配置文档中起着重要的作用，而且Nginx服务器在许多功能上的灵活性往往在location指令的配置中体现出来。

- location块的主要作用是
  - 基于Nginx服务器接收到的请求字符串（例如， server_name/uri-string）
  - 对除虚拟主机名称（也可以是IP别名，后文有详细阐述）之外的字符串（前例中“/uri-string”部分）进行匹配
  - 对特定的请求进行处理。地址定向、数据缓存和应答控制等功能都是在这部分实现。许多第三方模块的配置也是在location块中提供功能。

#### 4.3.5.2. 路径匹配规则

```nginx
location [ = | ~ | ~* | ^~ ] uri { ... }
```

> <https://stackoverflow.com/questions/5238377/nginx-location-priority>

From the [HTTP core module docs](https://nginx.org/en/docs/http/ngx_http_core_module.html#location):

1. Directives with the "=" prefix that match the query exactly. If found, searching stops.
2. All remaining directives with conventional strings. If this match used the "^~" prefix, searching stops.
3. Regular expressions, in the order they are defined in the configuration file.
4. If #3 yielded a match, that result is used. Otherwise, the match from #2 is used.

Example from the documentation:

```
location  = / {
  # matches the query / only.
  [ configuration A ]
}
location  / {
  # matches any query, since all queries begin with /, but regular
  # expressions and any longer conventional blocks will be
  # matched first.
  [ configuration B ]
}
location /documents/ {
  # matches any query beginning with /documents/ and continues searching,
  # so regular expressions will be checked. This will be matched only if
  # regular expressions don't find a match.
  [ configuration C ]
}
location ^~ /images/ {
  # matches any query beginning with /images/ and halts searching,
  # so regular expressions will not be checked.
  [ configuration D ]
}
location ~* \.(gif|jpg|jpeg)$ {
  # matches any request ending in gif, jpg, or jpeg. However, all
  # requests to the /images/ directory will be handled by
  # Configuration D.
  [ configuration E ]
}
```

If it's still confusing, [here's a longer explanation](http://nginx.org/en/docs/http/ngx_http_core_module.html#location).

#### 4.3.5.3. root指令

- 作用：这个指令用于设置请求寻找资源的跟目录
- 配置位置：
  - 此指令可以在http块、server块或者location块中配置。
  - 由于使用Nginx服务器多数情况下要配置多个location块对不同的请求分别做出处理，因此该指令通常在location块中进行设置。

- path变量

  ```nginx
  root path
  ```

  path变量中可以包含Nginx服务器预设的大多数变量，只有documentroot和realpath_root不可以使用。

#### 4.3.5.4. alisa指令

#### 4.3.5.5. index指令

#### 4.3.5.6. error_page指令

## 4.4. 配置文件示例

  ```nginx
  ######Nginx配置文件nginx.conf中文详解#####

  #定义Nginx运行的用户和用户组
  user www www;

  #nginx进程数，建议设置为等于CPU总核心数。
  worker_processes 8;

  #全局错误日志定义类型，[ debug | info | notice | warn | error | crit ]
  error_log /usr/local/nginx/logs/error.log info;

  #进程pid文件
  pid /usr/local/nginx/logs/nginx.pid;

  #指定进程可以打开的最大描述符：数目
  #工作模式与连接数上限
  #这个指令是指当一个nginx进程打开的最多文件描述符数目，理论值应该是最多打开文件数（ulimit -n）与nginx进程数相除，但是nginx分配请求并不是那么均匀，所以最好与ulimit -n 的值保持一致。
  #现在在linux 2.6内核下开启文件打开数为65535，worker_rlimit_nofile就相应应该填写65535。
  #这是因为nginx调度时分配请求到进程并不是那么的均衡，所以假如填写10240，总并发量达到3-4万时就有进程可能超过10240了，这时会返回502错误。
  worker_rlimit_nofile 65535;


  events
  {
      #参考事件模型，use [ kqueue | rtsig | epoll | /dev/poll | select | poll ]; epoll模型
      #是Linux 2.6以上版本内核中的高性能网络I/O模型，linux建议epoll，如果跑在FreeBSD上面，就用kqueue模型。
      #补充说明：
      #与apache相类，nginx针对不同的操作系统，有不同的事件模型
      #A）标准事件模型
      #Select、poll属于标准事件模型，如果当前系统不存在更有效的方法，nginx会选择select或poll
      #B）高效事件模型
      #Kqueue：使用于FreeBSD 4.1+, OpenBSD 2.9+, NetBSD 2.0 和 MacOS X.使用双处理器的MacOS X系统使用kqueue可能会造成内核崩溃。
      #Epoll：使用于Linux内核2.6版本及以后的系统。
      #/dev/poll：使用于Solaris 7 11/99+，HP/UX 11.22+ (eventport)，IRIX 6.5.15+ 和 Tru64 UNIX 5.1A+。
      #Eventport：使用于Solaris 10。 为了防止出现内核崩溃的问题， 有必要安装安全补丁。
      use epoll;

      #单个进程最大连接数（最大连接数=连接数*进程数）
      #根据硬件调整，和前面工作进程配合起来用，尽量大，但是别把cpu跑到100%就行。每个进程允许的最多连接数，理论上每台nginx服务器的最大连接数为。
      worker_connections 65535;

      #keepalive超时时间。
      keepalive_timeout 60;

      #客户端请求头部的缓冲区大小。这个可以根据你的系统分页大小来设置，一般一个请求头的大小不会超过1k，不过由于一般系统分页都要大于1k，所以这里设置为分页大小。
      #分页大小可以用命令getconf PAGESIZE 取得。
      #[root@web001 ~]# getconf PAGESIZE
      #4096
      #但也有client_header_buffer_size超过4k的情况，但是client_header_buffer_size该值必须设置为“系统分页大小”的整倍数。
      client_header_buffer_size 4k;

      #这个将为打开文件指定缓存，默认是没有启用的，max指定缓存数量，建议和打开文件数一致，inactive是指经过多长时间文件没被请求后删除缓存。
      open_file_cache max=65535 inactive=60s;

      #这个是指多长时间检查一次缓存的有效信息。
      #语法:open_file_cache_valid time 默认值:open_file_cache_valid 60 使用字段:http, server, location 这个指令指定了何时需要检查open_file_cache中缓存项目的有效信息.
      open_file_cache_valid 80s;

      #open_file_cache指令中的inactive参数时间内文件的最少使用次数，如果超过这个数字，文件描述符一直是在缓存中打开的，如上例，如果有一个文件在inactive时间内一次没被使用，它将被移除。
      #语法:open_file_cache_min_uses number 默认值:open_file_cache_min_uses 1 使用字段:http, server, location  这个指令指定了在open_file_cache指令无效的参数中一定的时间范围内可以使用的最小文件数,如果使用更大的值,文件描述符在cache中总是打开状态.
      open_file_cache_min_uses 1;

      #语法:open_file_cache_errors on | off 默认值:open_file_cache_errors off 使用字段:http, server, location 这个指令指定是否在搜索一个文件时记录cache错误.
      open_file_cache_errors on;
  }



  #设定http服务器，利用它的反向代理功能提供负载均衡支持
  http
  {
      #文件扩展名与文件类型映射表
      include mime.types;

      #默认文件类型
      default_type application/octet-stream;

      #默认编码
      #charset utf-8;

      #服务器名字的hash表大小
      #保存服务器名字的hash表是由指令server_names_hash_max_size 和server_names_hash_bucket_size所控制的。参数hash bucket size总是等于hash表的大小，并且是一路处理器缓存大小的倍数。在减少了在内存中的存取次数后，使在处理器中加速查找hash表键值成为可能。如果hash bucket size等于一路处理器缓存的大小，那么在查找键的时候，最坏的情况下在内存中查找的次数为2。第一次是确定存储单元的地址，第二次是在存储单元中查找键 值。因此，如果Nginx给出需要增大hash max size 或 hash bucket size的提示，那么首要的是增大前一个参数的大小.
      server_names_hash_bucket_size 128;

      #客户端请求头部的缓冲区大小。这个可以根据你的系统分页大小来设置，一般一个请求的头部大小不会超过1k，不过由于一般系统分页都要大于1k，所以这里设置为分页大小。分页大小可以用命令getconf PAGESIZE取得。
      client_header_buffer_size 32k;

      #客户请求头缓冲大小。nginx默认会用client_header_buffer_size这个buffer来读取header值，如果header过大，它会使用large_client_header_buffers来读取。
      large_client_header_buffers 4 64k;

      #设定通过nginx上传文件的大小
      client_max_body_size 8m;

      #开启高效文件传输模式，sendfile指令指定nginx是否调用sendfile函数来输出文件，对于普通应用设为 on，如果用来进行下载等应用磁盘IO重负载应用，可设置为off，以平衡磁盘与网络I/O处理速度，降低系统的负载。注意：如果图片显示不正常把这个改成off。
      #sendfile指令指定 nginx 是否调用sendfile 函数（zero copy 方式）来输出文件，对于普通应用，必须设为on。如果用来进行下载等应用磁盘IO重负载应用，可设置为off，以平衡磁盘与网络IO处理速度，降低系统uptime。
      sendfile on;

      #开启目录列表访问，合适下载服务器，默认关闭。
      autoindex on;

      #此选项允许或禁止使用socke的TCP_CORK的选项，此选项仅在使用sendfile的时候使用
      tcp_nopush on;

      tcp_nodelay on;

      #长连接超时时间，单位是秒
      keepalive_timeout 120;

      #FastCGI相关参数是为了改善网站的性能：减少资源占用，提高访问速度。下面参数看字面意思都能理解。
      fastcgi_connect_timeout 300;
      fastcgi_send_timeout 300;
      fastcgi_read_timeout 300;
      fastcgi_buffer_size 64k;
      fastcgi_buffers 4 64k;
      fastcgi_busy_buffers_size 128k;
      fastcgi_temp_file_write_size 128k;

      #gzip模块设置
      gzip on; #开启gzip压缩输出
      gzip_min_length 1k;    #最小压缩文件大小
      gzip_buffers 4 16k;    #压缩缓冲区
      gzip_http_version 1.0;    #压缩版本（默认1.1，前端如果是squid2.5请使用1.0）
      gzip_comp_level 2;    #压缩等级
      gzip_types text/plain application/x-javascript text/css application/xml;    #压缩类型，默认就已经包含textml，所以下面就不用再写了，写上去也不会有问题，但是会有一个warn。
      gzip_vary on;

      #开启限制IP连接数的时候需要使用
      #limit_zone crawler $binary_remote_addr 10m;



      #负载均衡配置
      upstream jh.w3cschool.cn {

          #upstream的负载均衡，weight是权重，可以根据机器配置定义权重。weigth参数表示权值，权值越高被分配到的几率越大。
          server 192.168.80.121:80 weight=3;
          server 192.168.80.122:80 weight=2;
          server 192.168.80.123:80 weight=3;

          #nginx的upstream目前支持4种方式的分配
          #1、轮询（默认）
          #每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。
          #2、weight
          #指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。
          #例如：
          #upstream bakend {
          #    server 192.168.0.14 weight=10;
          #    server 192.168.0.15 weight=10;
          #}
          #2、ip_hash
          #每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。
          #例如：
          #upstream bakend {
          #    ip_hash;
          #    server 192.168.0.14:88;
          #    server 192.168.0.15:80;
          #}
          #3、fair（第三方）
          #按后端服务器的响应时间来分配请求，响应时间短的优先分配。
          #upstream backend {
          #    server server1;
          #    server server2;
          #    fair;
          #}
          #4、url_hash（第三方）
          #按访问url的hash结果来分配请求，使每个url定向到同一个后端服务器，后端服务器为缓存时比较有效。
          #例：在upstream中加入hash语句，server语句中不能写入weight等其他的参数，hash_method是使用的hash算法
          #upstream backend {
          #    server squid1:3128;
          #    server squid2:3128;
          #    hash $request_uri;
          #    hash_method crc32;
          #}

          #tips:
          #upstream bakend{#定义负载均衡设备的Ip及设备状态}{
          #    ip_hash;
          #    server 127.0.0.1:9090 down;
          #    server 127.0.0.1:8080 weight=2;
          #    server 127.0.0.1:6060;
          #    server 127.0.0.1:7070 backup;
          #}
          #在需要使用负载均衡的server中增加 proxy_pass http://bakend/;

          #每个设备的状态设置为:
          #1.down表示单前的server暂时不参与负载
          #2.weight为weight越大，负载的权重就越大。
          #3.max_fails：允许请求失败的次数默认为1.当超过最大次数时，返回proxy_next_upstream模块定义的错误
          #4.fail_timeout:max_fails次失败后，暂停的时间。
          #5.backup： 其它所有的非backup机器down或者忙的时候，请求backup机器。所以这台机器压力会最轻。

          #nginx支持同时设置多组的负载均衡，用来给不用的server来使用。
          #client_body_in_file_only设置为On 可以讲client post过来的数据记录到文件中用来做debug
          #client_body_temp_path设置记录文件的目录 可以设置最多3层目录
          #location对URL进行匹配.可以进行重定向或者进行新的代理 负载均衡
      }



      #虚拟主机的配置
      server
      {
          #监听端口
          listen 80;

          #域名可以有多个，用空格隔开
          server_name www.w3cschool.cn w3cschool.cn;
          index index.html index.htm index.php;
          root /data/www/w3cschool;

          #对******进行负载均衡
          location ~ .*.(php|php5)?$
          {
              fastcgi_pass 127.0.0.1:9000;
              fastcgi_index index.php;
              include fastcgi.conf;
          }

          #图片缓存时间设置
          location ~ .*.(gif|jpg|jpeg|png|bmp|swf)$
          {
              expires 10d;
          }

          #JS和CSS缓存时间设置
          location ~ .*.(js|css)?$
          {
              expires 1h;
          }

          #日志格式设定
          #$remote_addr与$http_x_forwarded_for用以记录客户端的ip地址；
          #$remote_user：用来记录客户端用户名称；
          #$time_local： 用来记录访问时间与时区；
          #$request： 用来记录请求的url与http协议；
          #$status： 用来记录请求状态；成功是200，
          #$body_bytes_sent ：记录发送给客户端文件主体内容大小；
          #$http_referer：用来记录从那个页面链接访问过来的；
          #$http_user_agent：记录客户浏览器的相关信息；
          #通常web服务器放在反向代理的后面，这样就不能获取到客户的IP地址了，通过$remote_add拿到的IP地址是反向代理服务器的iP地址。反向代理服务器在转发请求的http头信息中，可以增加x_forwarded_for信息，用以记录原有客户端的IP地址和原来客户端的请求的服务器地址。
          log_format access '$remote_addr - $remote_user [$time_local] "$request" '
          '$status $body_bytes_sent "$http_referer" '
          '"$http_user_agent" $http_x_forwarded_for';

          #定义本虚拟主机的访问日志
          access_log  /usr/local/nginx/logs/host.access.log  main;
          access_log  /usr/local/nginx/logs/host.access.404.log  log404;

          #对 "/" 启用反向代理
          location / {
              proxy_pass http://127.0.0.1:88;
              proxy_redirect off;
              proxy_set_header X-Real-IP $remote_addr;

              #后端的Web服务器可以通过X-Forwarded-For获取用户真实IP
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

              #以下是一些反向代理的配置，可选。
              proxy_set_header Host $host;

              #允许客户端请求的最大单文件字节数
              client_max_body_size 10m;

              #缓冲区代理缓冲用户端请求的最大字节数，
              #如果把它设置为比较大的数值，例如256k，那么，无论使用firefox还是IE浏览器，来提交任意小于256k的图片，都很正常。如果注释该指令，使用默认的client_body_buffer_size设置，也就是操作系统页面大小的两倍，8k或者16k，问题就出现了。
              #无论使用firefox4.0还是IE8.0，提交一个比较大，200k左右的图片，都返回500 Internal Server Error错误
              client_body_buffer_size 128k;

              #表示使nginx阻止HTTP应答代码为400或者更高的应答。
              proxy_intercept_errors on;

              #后端服务器连接的超时时间_发起握手等候响应超时时间
              #nginx跟后端服务器连接超时时间(代理连接超时)
              proxy_connect_timeout 90;

              #后端服务器数据回传时间(代理发送超时)
              #后端服务器数据回传时间_就是在规定时间之内后端服务器必须传完所有的数据
              proxy_send_timeout 90;

              #连接成功后，后端服务器响应时间(代理接收超时)
              #连接成功后_等候后端服务器响应时间_其实已经进入后端的排队之中等候处理（也可以说是后端服务器处理请求的时间）
              proxy_read_timeout 90;

              #设置代理服务器（nginx）保存用户头信息的缓冲区大小
              #设置从被代理服务器读取的第一部分应答的缓冲区大小，通常情况下这部分应答中包含一个小的应答头，默认情况下这个值的大小为指令proxy_buffers中指定的一个缓冲区的大小，不过可以将其设置为更小
              proxy_buffer_size 4k;

              #proxy_buffers缓冲区，网页平均在32k以下的设置
              #设置用于读取应答（来自被代理服务器）的缓冲区数目和大小，默认情况也为分页大小，根据操作系统的不同可能是4k或者8k
              proxy_buffers 4 32k;

              #高负荷下缓冲大小（proxy_buffers*2）
              proxy_busy_buffers_size 64k;

              #设置在写入proxy_temp_path时数据的大小，预防一个工作进程在传递文件时阻塞太长
              #设定缓存文件夹大小，大于这个值，将从upstream服务器传
              proxy_temp_file_write_size 64k;
          }


          #设定查看Nginx状态的地址
          location /NginxStatus {
              stub_status on;
              access_log on;
              auth_basic "NginxStatus";
              auth_basic_user_file confpasswd;
              #htpasswd文件的内容可以用apache提供的htpasswd工具来产生。
          }

          #本地动静分离反向代理配置
          #所有jsp的页面均交由tomcat或resin处理
          location ~ .(jsp|jspx|do)?$ {
              proxy_set_header Host $host;
              proxy_set_header X-Real-IP $remote_addr;
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
              proxy_pass http://127.0.0.1:8080;
          }

          #所有静态文件由nginx直接读取不经过tomcat或resin
          location ~ .*.(htm|html|gif|jpg|jpeg|png|bmp|swf|ioc|rar|zip|txt|flv|mid|doc|ppt|
          pdf|xls|mp3|wma)$
          {
              expires 15d;
          }

          location ~ .*.(js|css)?$
          {
              expires 1h;
          }
      }
  }
  ######Nginx配置文件nginx.conf中文详解#####
  ```

# 5. 常见使用场景

TODO: nginx使用场景整理

## 5.1. Http反向代理

```nginx
  #运行用户
  #user somebody;

  #启动进程,通常设置成和cpu的数量相等
  worker_processes  1;

  #全局错误日志
  error_log  D:/ProgramFiles/scoop/apps/nginx/current/logs/error.log;
  error_log  D:/ProgramFiles/scoop/apps/nginx/current/logs/notice.log  notice;
  error_log  D:/ProgramFiles/scoop/apps/nginx/current/logs/info.log  info;

  #PID文件，记录当前启动的nginx的进程ID
  pid        D:/ProgramFiles/scoop/apps/nginx/current/logs/nginx.pid;

  #工作模式及连接数上限
  events {
      worker_connections 1024;    #单个后台worker process进程的最大并发链接数
  }

  #设定http服务器，利用它的反向代理功能提供负载均衡支持
  http {
      #设定mime类型(邮件支持类型),类型由mime.types文件定义
      include       D:/ProgramFiles/scoop/apps/nginx/current/conf/mime.types;
      default_type  application/octet-stream;

      #设定日志
  	log_format  main  '[$remote_addr] - [$remote_user] [$time_local] "$request" '
                        '$status $body_bytes_sent "$http_referer" '
                        '"$http_user_agent" "$http_x_forwarded_for"';

      access_log    D:/ProgramFiles/scoop/apps/nginx/current/logs/access.log main;
      rewrite_log     on;

      #sendfile 指令指定 nginx 是否调用 sendfile 函数（zero copy 方式）来输出文件，对于普通应用，
      #必须设为 on,如果用来进行下载等应用磁盘IO重负载应用，可设置为 off，以平衡磁盘与网络I/O处理速度，降低系统的uptime.
      sendfile        on;
      #tcp_nopush     on;

      #连接超时时间
      keepalive_timeout  120;
      tcp_nodelay        on;

  	#gzip压缩开关
  	#gzip  on;

      #设定实际的服务器列表
      upstream zp_server1{
          server 127.0.0.1:3000;
      }

      #HTTP服务器
      server {
          #监听80端口，80端口是知名端口号，用于HTTP协议
          listen       80;

          #定义使用www.xx.com访问
          server_name  www.helloworld.com;

  		#首页
  		index index.html

  		#指向webapp的目录
  		root D:\MyRepo\my-demo-react-app;

  		#编码格式
  		charset utf-8;

  		#代理配置参数
          proxy_connect_timeout 180;
          proxy_send_timeout 180;
          proxy_read_timeout 180;
          proxy_set_header Host $host;
          proxy_set_header X-Forwarder-For $remote_addr;

          #反向代理的路径（和upstream绑定），location 后面设置映射的路径
          location / {
              proxy_pass http://zp_server1;
          }

          #静态文件，nginx自己处理
          location ~ ^/(images|javascript|js|css|flash|media|static)/ {
              root D:\MyRepo\my-demo-react-app\build;
              #过期30天，静态文件不怎么更新，过期可以设大一点，如果频繁更新，则可以设置得小一点。
              expires 30d;
          }

          #设定查看Nginx状态的地址
          location /NginxStatus {
              stub_status           on;
              access_log            on;
              auth_basic            "NginxStatus";
              auth_basic_user_file  conf/htpasswd;
          }

          #禁止访问 .htxxx 文件
          location ~ /\.ht {
              deny all;
          }

  		#错误处理页面（可选择性配置）
  		#error_page   404              /404.html;
  		#error_page   500 502 503 504  /50x.html;
          #location = /50x.html {
          #    root   html;
          #}
      }
  }
```

## 5.2. Https反向代理

## 5.3. 负载均衡

## 5.4. 有多个webapp时的配置

## 5.5. 静态站点

## 5.6. 搭建文件服务器

## 5.7. 解决跨域


# 6. 参考文档

- [ ] [nginx简易教程](https://github.com/dunwu/nginx-tutorial)
- [ ] [Nginx 的中文维基](http://tool.oschina.net/apidocs/apidoc?api=nginx-zh)
- [ ] [Nginx 开发从入门到精通](http://tengine.taobao.org/book/index.html)
- [ ] [nginx-admins-handbook](https://github.com/trimstray/nginx-admins-handbook)
- [ ] [nginxconfig.io](https://nginxconfig.io/)
- [x] [nginx配置文件详解](https://www.cnblogs.com/54chensongxia/p/12938929.html)
- [ ] [nginx 的 default_server 定义及匹配规则](https://segmentfault.com/a/1190000015681272)
- [ ] [nginx入门教程](https://github.com/jaywcjlove/nginx-tutorial)
- [x] [彻底弄懂 Nginx location 匹配](https://juejin.cn/post/6844903849166110733)
- [ ] [nginx短篇](https://www.zsythink.net/archives/category/运维相关/nginx)


