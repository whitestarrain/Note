TODO: Temporal

# 简介

Temporal是一个新兴的分布式的工作流引擎。如果你在工作中遇到过以下这些场景，都可以来了解一下Temporal这个底层引擎。

- 跨服务、跨时间周期的复杂业务流程
- 业务工作流建模（BPM）
- DevOps工作流
- Saga分布式事务
- BigData数据处理和分析Pipeline
- Serverless函数编排

Temporal解决的关键痛点，就是分布式系统中的编排问题。

## 编排问题

要理解编排，可以借助和Orchestration对应的另一个概念：Choreography。找不到合适的中文翻译，还是看图理解吧：

举个例子，我们开发微服务时，经常借助消息队列（MQ）做事件驱动的业务逻辑，实现最终一致的、跨多个服务的数据流，这属于Choreography。而一旦引入了MQ，可能会遇到下面一系列问题：

- 消息时序问题
- 重试幂等问题
- 事件和消息链路追踪问题
- 业务逻辑过于分散的问题
- 数据已经不一致的校正对账问题
- …

[tempral-20240706001434.png](./image/tempral-20240706001434.png)

在复杂微服务系统中，MQ是一个很有用的组件，但MQ不是银弹，这些问题经历过的人会懂。如果过度依赖类似MQ的方案事件驱动，但又没有足够强大的消息治理方案，整个分布式系统将嘈杂不堪，难以维护。

如果转换思路，找一个“调度主体”，让所有消息的流转，都由这个”指挥家”来控制怎么样呢？对，这就是Orchestration的含义。

- Choreography 是无界上下文，去中心化，每个组件只关注和发布自己的事件，完全异步，注重的是解耦；
- Orchestration 是有界上下文，存在全局编排者，从全局建模成状态机，注重的是内聚。

**Temporal的所有应用场景，都是有全局上下文、高内聚的「编排」场景** 。比如BPM有明确的流程图，DevOps和BigData Pipeline有明确的DAG，长活事务有明确的执行和补偿流程。

Temporal让我们像写正常的代码一样，可以写一段工作流代码，但并不一定是在本机执行，哪一行在什么时间yield，由服务端信令统一控制，很多分布式系统韧性问题也被封装掉了，比如，分布式锁、宕机导致的重试失败、过期重试导致的数据错误，并发消息的处理时间差问题等等。

## Temporal关键概念

- Workflow，Workflow是在编排层的关键概念，每种类型是注册到服务端的一个WorkflowType，每个WorkflowType可以创建任意多的运行实例，即WorkflowExecution，每个Execution有唯一的WorkflowID，如果是Cron/Continue-as-New, 每次执行还会有唯一的RunID。Workflow可以有环，可以嵌套子工作流（ChildWorkflow）；
- Activity，Workflow所编排的对象主要就是Activity，编排Activity就行正常写代码一样，可以用if / for 甚至 while(true) 等各种逻辑结构来调用Activity方法，只要具备确定性即可；
- Signal，对于正在运行的WorkflowExecution，可以发送携带参数的信号，Workflow中可以等待或根据条件处理信号，动态控制工作流的执行逻辑。

# 参考

- [工作流引擎Temporal学习笔记](https://code2life.top/2023/01/23/0070-temporal-notes/)
