# 1. 云原生体系

## 1.1. 简介

- 软件通常会作为一种服务来交付，即 **软件即服务(SaaS)** 
- `12-factor`:
  - 衡量一个后端服务是否适合搬到云上准则
  - 描述了云端应用服务应当遵循的一些最佳实践
- `12-Factor`原则为构建SaaS应用提供了以下的`方法论`：
  - 使用标准化流程自动配置，减少开发者的学习成本。
  - 和操作系统解耦，使其可以在各个系统间提供最大的移植性。
  - 适合部署在现代的云计算平台上，从而在服务器和系统管理方面节省资源。
  - 将开发环境与生产环境的差异降至最低，并使用持续交付实施敏捷开发。
  - 可以在工具、架构和开发流程不发生明显变化的前提下实现拓展
- 该理论适应于任何语言和后端服务(数据库、消息队列、缓存等)开发的应用程序。

## 1.2. 基础要素

### 1.2.1. 基准代码-Codebase

> 一份基准代码，多份部署

应用代码使用`版本控制系统`来管理，常用的有`Git`、`SVN`等。一份用来跟踪代码所有修订版本的数据库称为`代码库`。

#### 1.2.1.1. 一份基准代码

基准代码和应用之间总是保持一一对应的关系：

- 一旦有多个基准代码，则不能称之为一个应用，而是一个分布式系统。
  - 分布式系统中的每个组件都是一个应用，
  - 每个应用都可以使用`12-Factor`原则进行开发。
- 多个应用共享一份基准代码有悖于`12-Factor`原则。
  - 解决方法是将共享的代码拆成独立的类库，通过依赖管理去使用它们。

#### 1.2.1.2. 多份部署

每个应用只对应一份基准代码，但可以同时存在多份的部署，每份部署相当于运行了一个应用的实例。

- 多份部署的区别在于：
  - 可以存在不同的配置文件对应不同的环境。
    - 例如开发环境、预发布环境、生产环境等。
  - 可以使用不同的版本。
    - 例如开发环境的版本可能高于预发布环境版本，还没同步到预发布环境版本，
    - 同理，预发布环境版本可能高于生产环境版本。

### 1.2.2. 显式和隔离的依赖 - Dependencies

> 显式声明依赖关系

- 大多数的编程语言都会提供一个`包管理系统`或工具，其中包含所有的`依赖库`，
  - 例如Golang的vendor目录存放了该应用的所有依赖包。
- 12-Factor原则下的应用会通过`依赖清单`来显式确切地声明所有的依赖项。
  - 在运行工程中通过`依赖隔离工具`来保证应用不会去调用系统中存在但依赖清单中未声明的依赖项。
- 显式声明依赖项的优点在于可以简化环境配置流程，开发者关注应用的基准代码，而依赖库则由依赖库管理工具来管理和配置。
  - 例如，Golang中的包管理工具dep等。

### 1.2.3. 配置分离存储到环境中 - Configuration

> 在环境中存储配置

- 通常， **应用的配置在不同的发布环境中** (例如：开发、预发布、生产环境)会有很大的差异，其中包括：
  - 数据库、Redis等后端服务的配置
  - 每份部署特有的配置，例如域名
  - 第三方服务的证书等
- 12-Factor原则 **要求代码和配置严格分离** ，而不应该通过代码常量的形式写在代理里面。
  - 配置在不同的部署环境中存在大幅差异，但是代码却是完全一致的。
- 判断一个应用是否正确地将配置排除在代码外，可以看应用的基准代码是否可以立即开源而不担心暴露敏感信息。
- 12-Factor原则建议将应用的配置存储在环境变量中
  - 环境变量可以方便在不同的部署环境中修改，而不侵入原有的代码
  - (例如，k8s的大部分代码配置是通过环境变量的方式来传入的)。
- 12-Factor应用中，环境变量的粒度要足够小且相对独立。
  - 当应用需要拓展时，可以平滑过渡。

### 1.2.4. 分离构建、发布、运行 - Build, Release, Run

> 严格分离构建和运行

- 基准代码转化成一份部署需要经过三个阶段：
  - 构建阶段：指代码转化为可执行包的过程。
    - 构建过程会使用指定版本的代码，获取依赖项，编译生成二进制文件和资源文件。
  - 发布阶段：将构建的结果与当前部署所需的配置结合，并可以在运行环境中使用。
  - 运行阶段（运行时）：指针对指定的发布版本在执行环境中启动一系列应用程序的进程。

- 12-Factor应用严格区分构建、发布、运行三个步骤
  - 每一个发布版本对应一个唯一的发布ID，可以使用时间戳或递增的版本序列号。
- 如果需要修改则需要产生一个新的发布版本，如果需要回退，则回退到之前指定的发布版本。
- 新代码部署之前，由开发人员触发构建操作，构建阶段可以相对复杂一些，方便错误信息可以展示出来得到妥善处理。
- 运行阶段可以人为触发或自动运行，运行阶段应该保持尽可能少的模块。

### 1.2.5. 环境对等 - Dev/Prod Parity

> 尽可能的保持开发，预发布，线上环境相同

- 不同的发布环境可能存在以下差异：
  - 时间差异：开发到部署的周期较长。
  - 人员差异：开发人员只负责开发，运维人员只负责部署。分工过于隔离。
  - 工具差异：不同环境的配置和运行环境，使用的后端类型可能存在不同。
- 应尽量缩小本地与线上的差异，缩短上线周期，开发运维一体化，保证开发环境与线上运行的环境一致（例如，可以通过Docker容器的方式）。

## 1.3. 针对云原生应用

### 1.3.1. 分离基础的后端组件 - Backing services

> 把后端服务当作附加资源

- 后端服务指程序运行时所需要通过网络调用的各种服务。例如：
  - 数据库:[MySQL](http://dev.mysql.com/)，[CouchDB](http://couchdb.apache.org/)
  - 消息/队列系统:[RabbitMQ](http://www.rabbitmq.com/)，[Beanstalkd](https://beanstalkd.github.io/)
  - SMTP 邮件发送服务:[Postfix](http://www.postfix.org/)
  - 缓存系统:[Memcached](http://memcached.org/)
- 其中可以根据管理对象分为`本地服务`(例如本地数据库)和`第三方服务`(例如Amason S3)。
  - 对于12-Factor应用来说都是附加资源，没有区别对待，
  - 当其中一份后端服务失效后，可以通过切换到原先备份的后端服务中，而不需要修改代码(但可能需要修改配置)。
  - 12-Factor应用与后端服务保持松耦合的关系。

### 1.3.2. 无状态的服务进程 - Processes

> 以一个或多个无状态进程运行应用

- 12-Factor应有的进程必须是 **无状态且无共享** 的，任何需要持久化的数据存储在后端服务中，例如数据库。
- 内存区域和磁盘空间可以作为进程的缓存，12-Factor应用不需要关注这些缓存的持久化，而是允许其丢失，例如重启的时候。
- 进程的二进制文件应该在构建阶段执行编译而不是运行阶段。
- 当应用使用到`粘性Session`，即将用户的session数据缓存到进程的内存中，将同一用户的后续请求路由到同一个进程。
  - **12-Factor应用反对这种处理方式** ，而是建议将session的数据保存在redis/memcached带有过期时间的缓存中。

### 1.3.3. 自带端口绑定 - Port Binding

> 通过端口绑定提供服务

- 应用通过端口绑定来提供服务，并监听发送至该端口的请求。
- 端口绑定的方式意味着一个应用也可以成为另一个应用的后端服务，例如提供某些API请求。

### 1.3.4. 通过进程的水平扩展增大并发能力 - Concurrency

> 通过进程模型进行扩展

- 12-Factor应用中，开发人员可以将不同的工作分配给不同类型进程，
  - 例如HTTP请求由web进程来处理，
  - 常驻的后台工作由worker进程来处理
  - （k8s的设计中就经常用不同类型的manager来处理不同的任务）。
- 12-Factor应用的进程具备无共享、水平分区的特性，使得水平扩展较为容易。
- 12-Factor应用的进程 **不需要守护进程或是写入PID文件** ，
  - 而是 **通过进程管理器（例如 systemd）** 来管理输出流，响应崩溃的进程，以及处理用户触发的重启或关闭超级进程的操作。

### 1.3.5. 易处置：快速启动和优雅退出 - Disposability

> 快速启动和优雅终止可最大化健壮性

- 12-Factor应用的进程是`易处理`的，即它们可以快速的开启或停止，这样有利于快速部署迭代和弹性伸缩实例。
- 进程应该追求最小的启动时间，这样可以敏捷发布，增加健壮性，当出现问题可以快速在别的机器部署一个实例。
- 进程一旦接收到`终止信号(SIGTERM)`就会`优雅终止`。
  - 优雅终止指停止监听服务的端口，拒绝所有新的请求，并继续执行当前已接收的请求，然后退出。
- 进程还需在面对突然挂掉的情况下保持健壮性
  - 例如通过`任务队列`的方式来解决进程突然挂掉而没有完成处理的事情，
  - 所以应该设计为任务执行是`幂等`的，可以被重复执行，重复执行的结果是一致的。

### 1.3.6. 日志作为事件流 - Logs

> 把日志当作事件流

- 日志应该是`事件流`的汇总。
  - 12-Factor应用本身不考虑存储自己的日志输出流，不去写或管理日志文件，而是通过标准输出（stdout）的方式。
- 日志的标准输出流可以通过其他组件截获，整合其他的日志输出流，一并发给统一的日志中心处理，用于查看或存档
  - 例如：日志收集开源工具Fluentd。
- 截获的日志流可以输出至文件，或者在终端实时查看。
  - 最重要的是可以发送到`Splunk`这样的日志索引及分析系统，提供后续的分析统计及监控告警等功能。例如：
    - 找出过去一段时间的特殊事件。
    - 图形化一个大规模的趋势，如每分钟的请求量。
    - 根据用户定义的条件触发告警，如每分钟报错数超过某个警戒线。

### 1.3.7. 分离管理类任务 - Admin Processes

> 后台管理任务当作一次性进程运行

- 开发人员经常需要执行一些管理或维护应用的一次性任务，如：
  - 执行数据库DDL
  - 周期执行的运维任务
  - 一次性的数据迁移和修复等等
- 一次性管理进程应该和常驻进程使用相同的运行环境，
- 开发人员可以通过ssh方式来执行一次性脚本或任务。

---

- 反例：
  - 在应用服务运行环境中安装一个数据库客户端，运维人员手动跑一堆修改数据库的SQL
  - 或者安装一些运维脚本，放到机器的cron table定期执行一些脚本。

- 正例：
  > 如果要实现每天跑一次的数据分析脚本，除了到机器上加crontab这个最坏的办法，还有什么其他办法呢？
  - 《The Twelve-Factor App》
    - **可以用一次性的容器** 
    - 每天创建一个容器执行脚本，确认执行成功后随即销毁，不成功可以自动重试
    - 比如Kuernetes提供的CronJob机制。
  - 《Beyond the Twelve-Factor App》
    - 可以 **在应用内或应用外单独做一个服务** ，提供一个HTTP/RPC接口做这件事
    - 调度平台每天触发的仅仅是HTTP调用，根据调用返回结果决定重试。
    - 彻底去除Admin Processes，所有的东西都是可伸缩的Backing Service。

## 1.4. 第三类要素

如果说“12要素”是云原生应用的必要条件，那它们也不能构成充分条件。

- Kevin Hoffman在2016年写的《Beyond the Twelve-Factor App》一书中
  - 又重新修订了“12要素”，修改了一些解释
  - 另外添加了`API First`、`Telemetry`、`Authentication & Authorization`三个要素。
  - 前两个对微服务系统非常重要，第三个则是安全性的核心保障机制。
- 说明：
  - API First：以API为中心的协作模式，永远先定义好API再做其他事情。
    - 微服务系统的开发模式，大多是多个小团队齐头并进，设计好之后，先定义API就非常重要了。
    - 以API作为团队、应用服务之间沟通的桥梁。
  - Telemetry：翻译成“遥测”有些别扭，属于可观测性（Observability）的一部分，上面说过的日志也属于可观测性的一部分。
    - 对于云原生系统，要杜绝传统的“SSH进去运行Debug工具”的事情发生，“遥测”是实现这一点的唯一手段。
    - 监控、告警、链路追踪，在微服务系统中缺一不可。
  - Authentication & Authorization：认证和授权，属于安全性方面的“要素”，对传统的应用服务也适合。
    - 但云原生应用实现认证和授权的方式有所不同：对终端用户的认证授权往往在网关层就通过OAuth 2.0/OpenID Connect等协议统一处理了；
    - 对服务之间调用的认证授权通过Service Mesh可以做到零信任安全模式。

## 1.5. k8s知识体系

# 2. 参考资料

- [ ] [kubernetes学习笔记](https://www.huweihuang.com/kubernetes-notes)
  <!--
  取消vip限制
  d = document.getElementById("vip-container")
  d.style.height = "10000px"
  -->
- [ ] [12 factor](https://12factor.net/)
- [ ] [浅析云原生12要素](https://zhuanlan.zhihu.com/p/243404169)


