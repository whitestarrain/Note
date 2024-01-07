# 1. 基本概念

## 1.1. 认证、授权、凭证

![authen-8](./image/authen-8.png)

- 认证是 authentication，
  - 指的是当前用户的身份，
  - 当用户登陆过后系统便能追踪到他的身份做出符合相应业务逻辑的操作。
  - 即使用户没有登录，大多数系统也会追踪他的身份，只是当做来宾或者匿名用户来处理。
  - 认证技术解决的是 "我是谁？"的问题。

- 授权是 authorization
  - 指的是什么样的身份被允许访问某些资源，在获取到用户身份后继续检查用户的权限。
  - 单一的系统授权往往是伴随认证来完成的，但是在开放 API 的多系统结构下，授权可以由不同的系统来完成，例如 OAuth。
  - 授权技术是解决"我能做什么？"的问题。

- 凭证是credentials:
  - 实现认证和授权的基础是需要一种媒介来标记访问者的身份或权利
  - 在现实生活中每个人都需要一张身份证才能访问自己的银行账户、结婚和办理养老保险等，这就是认证的凭证；
  - 在古代军事活动中，皇帝会给出战的将军颁发兵符，下级将领不关心持有兵符的人，只需要执行兵符对应的命令即可。
  - 在互联网世界中，服务器为每一个访问者颁发 session ID 存放到 cookie，这就是一种凭证技术。
  - 数字凭证还表现在方方面面，SSH 登录的密匙、JWT 令牌、一次性密码等。

## 1.2. 权限种类

- 页面权限:
  - 即用户登录系统可以看到的页面
  - 由菜单来控制,菜单包括一级菜单和二级菜单
  - 只要用户有一级和二级菜单的权限,那么用户就可以访问页面
- 操作权限:
  - 即页面的功能按钮,包括查看,新增,修改,删除,审核等
  - 用户点击删除按钮时,后台会校验用户角色下的所有权限是否包含该删除权限,如果是,就可以进行下一步操作,反之提示无权限。
  - 有的系统要求"可见即可操作",意思是如果页面上能够看到操作按钮,那么用户就可以操作,要实现此需求
  - 这里就需要前端来配合,前端开发把用户的权限信息缓存,在页面判断用户是否包含此权限,如果有,就显示该按钮,如果没有,就隐藏该按钮。
  - 某种程度上提升了用户体验,但是在实际场景可自行选择是否需要这样做
- 数据权限:
  - 数据权限就是用户在同一页面看到的数据是不同的,
  - 比如财务部只能看到其部门下的用户数据,采购部只看采购部的数据,
  - 解决方案一般是把数据和具体的组织架构关联起来,
  - 举个例子,再给用户授权的时候,用户选择某个角色同时绑定组织如财务部或者合肥分公司,那么该用户就有了该角色下财务部或合肥分公司下的的数据权限。

## 1.3. 其他注意点

- 用户账户的存储:
  - 也不一定是存放在数据库中的一张表
  - 在一些企业 IT 系统中，对账户管理和权限有了更多的要求。
  - 所以账户技术 （accounting）可以帮助我们使用不同的方式管理用户账户，同时具有不同系统之间共享账户的能力
  - 例如微软的活动目录（AD），以及简单目录访问协议（LDAP），甚至区块链技术。

# 2. 访问控制策略

## 2.1. 基于用户角色的访问控制（RBAC）

### 2.1.1. 说明

![rbac-1](./image/rbac-1.png)

- 说明：RBAC是一套成熟的权限模型。

- 对比：
  - 在传统权限模型中，我们直接把权限赋予用户。
  - 而在RBAC中，增加了“角色”的概念，我们首先把权限赋予角色，再把角色赋予用户。这样，由于增加了角色，授权会更加灵活方便。

- 种类：
  - 在RBAC中，根据权限的复杂程度，又可分为RBAC0、RBAC1、RBAC2、RBAC3。
  - 其中，RBAC0是基础，RBAC1、RBAC2、RBAC3都是以RBAC0为基础的升级。
  - 我们可以根据自家产品权限的复杂程度，选取适合的权限模型。


### 2.1.2. RBAC0

![rbac-2](./image/rbac-2.png)

![rbac-7](./image/rbac-7.png)

- RBAC0是基础，很多产品只需基于RBAC0就可以搭建权限模型了。
- 在这个模型中，我们**把权限赋予角色，再把角色赋予用户**。
- 用户和角色，角色和权限都是**多对多**的关系。
- 用户拥有的权限等于他所有的角色持有**权限之和**。

### 2.1.3. RBAC1

![rbac-3](./image/rbac-3.png)

![rbac-8](./image/rbac-8.png)

 - RBAC1建立在RBAC0基础之上，
 - 在角色中引入了**继承**的概念。
 - 简单理解就是，**给角色可以分成几个等级**，每个等级权限不同，从而实现更细粒度的权限管理。

### 2.1.4. RBAC2

- RBAC2同样建立在RBAC0基础之上，
- 仅是对用户、角色和权限三者之间增加了一些限制。
- 这些限制可以分成两类
  - 静态职责分离SSD(Static Separation of Duty)
    - 角色互斥：
      - 同一用户不能分配到一组互斥角色集合中的多个角色，互斥角色是指权限互相制约的两个角色。
      - 案例：请款系统中一个用户不能同时被指派给申请角色和审批员角色。
    - 基数约束：
      - 一个角色被分配的用户数量受限，它指的是有多少用户能拥有这个角色。
      - 案例：一个角色专门为公司CEO创建的，那这个角色的数量是有限的。
    - 先决条件角色：
      - 指要想获得较高的权限，要首先拥有低一级的权限。
      - 案例：先有副总经理权限，才能有总经理权限。
  - 动态职责分离DSD(Dynamic Separation of Duty)
    - 运行时互斥：
      - 允许一个用户具有两个角色的成员资格，但在运行中不可同时激活这两个角色，
      - 案例：同一个用户拥有多个角色，角色的权限有重叠，以较大权限为准。

  ![rbac-4](./image/rbac-4.png)

### 2.1.5. RBAC3

![rbac-5](./image/rbac-5.png)

- RBAC3是RBAC1和RBAC2的合集，所以RBAC3既有角色分层，也包括可以增加各种限制。

### 2.1.6. 延伸:用户组

![rbac-6](./image/rbac-6.png)


- 原因：
  - 当平台用户基数增大，角色类型增多时，如果直接给用户配角色，管理员的工作量就会很大。
  - 这时候我们可以引入一个概念“用户组”，就是将相同属性的用户归类到一起

- 示例：
  - 加入用户组的概念后，可以将部门看做一个用户组，再给这个部门直接赋予角色（1万员工部门可能就几十个），
  - 使部门拥有部门权限，这样这个部门的所有用户都有了部门权限，而不需要为每一个用户再单独指定角色，
  - 同时，也可以为特定的用户指定角色，这样用户除了拥有所属用户组的所有权限外，还拥有自身特定的权限。

- 优点：
  - 用户组的优点
  - 除了减少工作量
  - 还有更便于理解、增加多级管理关系

  > 如：我们在进行组织机构配置的时候，
  > 除了加入部门，还可以加入职级、岗位等层级，来为用户组内部成员的权限进行等级上的区分。

- 基于RBAC模型，还可以适当延展，使其更适合我们的产品。
- 譬如增加用户组概念，直接给用户组分配角色，再把用户加入用户组。
- 这样用户除了拥有自身的权限外，还拥有了所属用户组的所有权限。

### 2.1.7. 优缺点

- 优点
  - 稳定：
    - 从管理角度出发，角色是为了解决特定问题而被创造的，也就是解决分工问题
    - 而用户的变动几率比系统里的角色大多了，角色这一定义相对会更加稳定一些。
  - 简单：
    - 用户与权限的逻辑分离，使得权限只和角色相关。
    - 而根据上文，角色是被相关干系人或行业直接创造出来的，以角色为核心的权限管理体系的理解成本和学习成本相对于新造概念会少上很多，非常利于在内部推广。

- 缺点
  > 从管理角度出发，角色是为了解决特定问题而被创造的，也就是解决分工问题， <br />
  > 而自然人的变动几率比系统里的角色大多了，角色这一定义相对会更加稳定一些，<br />
  > 这一优点实际上也带来了缺点<br />
  - 角色管理困难：
    - 现实生活中人是不稳定的，在 RBAC 中通常表现为一个人关联了多个角色，这样就给角色管理带来了很大的难题，
    - 尽管有权限继承、角色互斥等等办法提高角色管理的效率，但是在面对以下难题时依然束手无策：
      - 产品经理Ａ拥有产品相关权限，但是由于最近在负责业务Ｂ，需要获取Ｂ的临时权限；这个时候需要新增一个角色；
      - 产品经理 C 需要业务 D 中一个单独后台页面的查看和修改权限；这个时候也需要新增一个角色
    - 新增资源时，需要维护所有与之相关的角色，维护起来费时费力，而且角色是一个相对静态的权限定义，如果需要把一个流程权限化：
      - 运营 A 创建了兑换码后，产品 B 才能去看兑换订单的详情数据
      - 像这样一类权限获取，是 RBAC 所不支持的。
  - 与最小权限原则冲突
    - 用户应该仅能访问其工作所需的资源和服务，拥有既不超过也不低于完成工作所需的管理权限。过度预配访问权限可能会增加内部人员威胁、资源配置错误以及难以进行审核跟踪的风险。如果权限预配不足，则用户可能无法访问完成任务所需的资源——如果严格按照最小权限原则，将会让角色爆炸这个问题更加严重


## 2.2. 基于属性的访问控制（ABAC）

### 2.2.1. RBAC的不适用

> RBAC 在很多时候是管用的 比如我们的系统是面向销售公司或者学校这种组织架构很严整的地方，但是在复杂场景下，RBAC 渐渐就不够用了
>
> 它会产生很多虚无的 role 而且在管理和控制上更难

- 比如在某个医疗机构中，我们想要控制一个科室内，护士只能访问自己所负责的病人资料时
  - 我们就无法直接使用 nurse 这个 role，我们需要更细粒度的 role 去划分病人老张还是老王
  - 这就会产生和现实不对应的 role，例如：老张的护士，老王的护士。
  - 在医院这种病人流动性很大的场景下，频繁的创建和销毁 role 是很容易出问题
  - 我们的确要求很频繁，但是与现实不 match 的虚无的 role 很难管理。

  > ![authen-10](./image/authen-10.png)

- 另一种情况是，如果管理者考虑医疗数据的安全性与隐私性，不希望护士在离开医院后能够访问到病人资料，我们会更加难办
  - 常见的策略要么是在底层网络层进行处理，直接禁止在院外的一切访问
  - 但是很多企业的需求往往是，使用 VPN 我依旧可以访问内部的资源，但是我还是希望基于所在地进行精确的控制
  - 比如看邮件是可以的，但是看财务数据是不行的。
  - 在 RBAC 下，我们也可以通过虚拟的 role 来控制，比如下班后给与其 Out of Office 的 role，然后给与这个 role 最小的权限
  - 这自然又需要虚拟的 role 与大量的动态控制。
    > 一般来说，在 RBAC 中滥用 role 所带来的问题被称为 “role explosion”

  > ![authen-11](./image/authen-11.png)


### 2.2.2. 介绍

- ABAC基于属性的访问控制 (Attribute Based Access Control)解决的问题:
  - 上述情况为例
  - 直觉上来说我们需要更多的"东西"来进行更精细的访问控制，用来匹配我们复杂的业务场景
  - 同时，我们也希望这个新的模型易于理解和实现，也利于控制与运维
  - 简单来说，对于 ABAC 我们判断一个用户是否能访问某项资源，是对其很多不同属性的计算而得到的。

**传统的 RBAC 与 ACL 等访问控制机制中，可以认为是 ABAC 的子集，对于 RBAC，只是我们的访问机制的实现只是基于属性 role 而已，ACL 则是基于属性是 identity 的 AC。**

### 2.2.3. 术语

- **Attribute**：
  - 属性,用于表示 subject、object 或者 environment conditions 的特点
  - attribute 使用 key-value 的形式来存储这些信息
  - 比如我在公司的 role 是 developer，role 是 key，developer 是 value，而我的小组昵称袋熊，key 是 team，value 是 wombat。
- **Subject**：
  - 常常指代使用系统的人或者其他使用者（non-person entity，NPE）
  - 比如说客户端程序，访问 API 的 client 或者移动设备等等
  - 当然一个 subject 可以有多个的 attributes，就像用户属性这些我们曾经用过的名词一样。
- **Object**：指代我们这个 ACM 需要管理的资源
  - 比如文件，比如某项记录，比如某台机器或者某个网站，任何你需要进行访问控制的资源都可以称为 object，
  - **同样 object 也可以有多项属性，比如袋熊组的桌子，或者洛克组的线上实例**
  - 我们也常常使用 resource 来描述这些资源，但是在 ABAC 的环境下，我们称为 object。
- **Operation**：
  - 有了 object 有了 subject，自然就有了 subject 需要做的事情
  - 比如查看某条记录，登录某台服务器，使用某个 SaaS 服务进行报销或者查看候选人的作业。
  - 往往包括我们常说的读、写、修改、拷贝等等， **一般 operation 是会表达在 request 中的，比如 HTTP method。**
- **Policy**：通过 subject、object 的 attribute 与 environment conditions 一起来判断 subject 的请求是否能够允许的关系表示
  - **比如说：policy 可以用人类语言这样表达：只有袋熊组的人才能访问这几台服务器，或者只有在办公室才能访问这些资源，但对于机器来说，无非就是一个判断语句罢了**。
  - 当然了，policy 可以是 **一堆这样 boolean 逻辑判断的组合** ，比如只有公司的正式员工、并且在公司的六楼区域的网络中，才能访问某个服务。
  - 可以使用 [Specification Pattern](https://en.wikipedia.org/wiki/Specification_pattern) 来实现 policy，其实没那么复杂。
- **Environment Conditions**：
  - 表示目前进行的访问请求发生时，操作或情境的上下文。
  - Environment conditions 常常用来描述环境特征，是独立于 subject 与 object 的，常用来描述系统的情况
  - 比如时间，当前的安全等级，生产环境还是测试环境等等。

### 2.2.4. 基本场景与概念

- 基本场景：
  - 当 subject 需要去读取某一条记录时，我们的访问控制机制在请求发起后便开始运作
  - 该机制需要计算，来自 policy 中记录的规则，subject 的 attribute，object 的 attribute 以及 environment conditions
  - 而最后会产生一个是否允许读取的结果。

  > ![authen-12](./image/authen-12.png)

- 进一步抽象
  > 在 NIST 的描述中，我们对 ACM 内部进行进一步的抽象，可以得出这两个核心模块
  - Policy Decision Point (PDP) 
  - Policy Enforcement Point (PEP)

  > ![authen-13](./image/authen-13.png)

- 核心机制
  - 在请求发起后
  - subject attributes、object attributes 与 environment conditions 作为输入
  - PEP 获取规则，PDP 进行计算
  - 最后确定是否有权进行请求

  > ![authen-14](./image/authen-14.png)

- opterion与request的关系
  ```
  RESTful 是我们常用的设计风格，RESTful 的流行是与 HTTP 协议密不可分的，
  其中我们很看重 HTTP 协议中的 methods，并且赋予了这些 methods 易于理解的语义，
  我们会很自然的认为 GET 是获取资源，而 POST 是创建新的资源，PUT 则是修改，
  使用这些 methods 是与控制资源联系在一起。
  但是，在 HTTP 协议中，并没有规定 GET 是否可以进行资源上的修改和更新，因为这只是个传输协议罢了，
  是我们在这个协议之上发明了新的东西，所以也没有“官方的、标准的” RESTful。
  之所以在这里提到 RESTful 与 HTTP 的关系，是因为在进行 ABAC 系统设计时，operation 是可以有很多种表达的，
  而重要的是定义这些 operation 与 request 的关系，你可以使用 SOAP 或者其他协议，或者在 RESTful 之上自己发明一些字段
  或者加入进 HTTP header，但是一定需要有清楚的 operation 描述。
  ```

### 2.2.5. ABAC如何应对复杂场景

- Attribute 灵活
  - 我们可以很容易的为不同的用户设计 attribute，
  - 往往在很多企业的实现中存在一个 consumer profile 或者 user details 的服务
  - 这些服务中很多字段比如职位、职级、办公室、项目等就是天然的 attribute
  - 对于需要管理的 object，如果是一台虚拟机，那么 IP 地址、归属组织、cost code 等都可以是 attribute
  - 而且因为 attribute 是 K-V 式的，往往一张一对多的表就可以控制好 subject、object 与 attribute 的对应。

- 细粒度授权支持
  - ABAC 能做到细粒度的授权管理
  - 在 policy 中，我们的准许访问的判断是可以写的很灵活的
    - 可以判断请求中的某个属性是否满足于一个正则表达式，或者字符串相等（这个很常见，特别是在使用 AWS IAM 做最小权限原则时）
    - 也可以使用逻辑与、逻辑或的关系自由组合很多不同的访问规则。
    - **也可以使用之前提到的 Specification Pattern 很轻松的实现灵活的 policy，解析 JSON 或者 XML 去动态的创建规则，而这些含有规则的 JSON 或 XML，则是可以被编程实现的（可编程的 policy 是动态的授权验权的前提）**
    -  policy 甚至可以做到，只有姓张的工程师在某个项目时才能访问某个资源，在 RBAC 的时代，这是很难的。

- 访问控制管理成本很低
  - ABAC 对系统管理员是友好的
  - 在 RBAC 的时代，如果我需要实现细粒度的资源管理或者经常 subject 与 object 的对应关系经常变动，那么管理员难以操作的，也很容易出现问题
  - 其中常常被采用的解决方案就是创建那些本不应该存在的 role
  - 但是在 ABAC 时代，管理员的管理对象会缩减到 policy，也就是只处理访问控制
    - 我们再回到医疗机构的那个例子中，如果某个护士负责照顾老张，系统管理员只需要新建一个 policy 并写上允许访问即可
    - 当老张出院后，**只需要删除或者失效这个 policy 就可以了**
    - 在 RBAC 的环境中，你可能需要为某个虚拟的 role 动态的添加 permission
    - 而 permission 如果到了针对单个病人的情况下，是绝对多如牛毛的，特别是有两个叫做老张的病人时。
  - 往往，我们会使用 JSON 或者 XML 定义这个 policy，那么，这一切都可以完全自动化，而不需要使用管理员点击
  - 再现实一些的话，我们可以完全实现一个审批的流程，如果你使用过 Google Drive，你会对这个请求访问的过程绝不陌生。
    > ![authen-15](./image/authen-15.png) 

- 动态的总体控制
  - Environment conditions 也能够提供统一的系统级别的控制
  - 比如威胁等级或者按照区域划分安全级别，不同的区域使用 ABAC 时，可能环境上会有变化
  - 例如我们常用红区来表示最高安全级别，那么我们默认就需要 deny 所有请求，并且会触发警报等等，但是在绿区这种办公区域，可能默认所有的请求都是被允许的等等。
  - **Environment conditions 可以提供“拉闸”这样的功能**，而且它也是可以动态调整的。

### 2.2.6. 优缺点

- 优点
  - 灵活：
    - attibute灵活：见上方
    - policy灵活
  - 颗粒度小：见上方
  - 统计系统级别控制:见上方

- 缺点
  - 策略定制比较麻烦:
    - 不能直观看出用户和资源的访问关系，
    - 策略的维护也比较麻烦
  - 性能
    - ABAC 的一系列优点都是建立在占用更多性能的前提下
    - 需要实时计算，较多规则会有性能问题



### 2.2.7. ABAC 应用

#### 2.2.7.1. AWS IAM

- 当企业正在使用公有云时，对公有云的资源进行控制是非常难以管理的
- 当然可以为每个小组安排好虚拟机或者 RDS 之类的，但是这太静态了，而且也不足以细粒度
- 比如，当我们想实现最小权限原则时就很难办到（例如你的数据库只想被你确定的几台实例所访问到）
  - 往往这种需求会实现为，在某个网段内，大家都可以访问到某个数据库或者中间件
  - 那自然是不够理想的。
- 如果公有云的资源是租用的，你可以按照自己的需求动态的扩容或者降低你的资源，
- 那么这种场景下，资源是动态的，而且变化很大（可能会根据流量动态的启动实例或者关闭）
- 那在这种情况下如何做到访问控制与最小权限原则，那你就不能再基于 users 与 roles 进行操作，这时候你就需要 ABAC
- AWS 作为云计算的领导者，很早就实现了类似的功能，而使用 IAM 则是 operations 的必修课。


待整理

[ IAM 与 ABAC 介绍](https://www.youtube.com/watch?v=Iq_hDc385t4&t=2378s)。

#### 2.2.7.2. k8s

<details>
<summary style="color:red;">示例</summary>

```json
({
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "group": "system:authenticated",
    "nonResourcePath": "*",
    "readonly": true
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "group": "system:unauthenticated",
    "nonResourcePath": "*",
    "readonly": true
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "admin",
    "namespace": "*",
    "resource": "*",
    "apiGroup": "*"
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "scheduler",
    "namespace": "*",
    "resource": "pods",
    "readonly": true
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": { "user": "scheduler", "namespace": "*", "resource": "bindings" }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "kubelet",
    "namespace": "*",
    "resource": "pods",
    "readonly": true
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "kubelet",
    "namespace": "*",
    "resource": "services",
    "readonly": true
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "kubelet",
    "namespace": "*",
    "resource": "endpoints",
    "readonly": true
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": { "user": "kubelet", "namespace": "*", "resource": "events" }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "alice",
    "namespace": "projectCaribou",
    "resource": "*",
    "apiGroup": "*"
  }
},
{
  "apiVersion": "abac.authorization.kubernetes.io/v1beta1",
  "kind": "Policy",
  "spec": {
    "user": "bob",
    "namespace": "projectCaribou",
    "resource": "*",
    "apiGroup": "*",
    "readonly": true
  }
})
```

</details>

### 2.2.8. 总结

- 复杂性
  - ABAC 在概念上的设计的确是有先进性的，对于有 RBAC 知识的人，ABAC 不难理解，也就是 基于用户属性进行访问权限判断
  - **对于 ABAC 的概念，这并不是复杂度的来源，而是授权这件事本身的复杂性**
  - **对于系统的设计者与管理者来说，一旦需要关注细粒度的授权管理，那么复杂是无法避免的。**

- 微服务: **关于微服务的流行与 ABAC 的配合，特别是分布式身份验证之后，有很多方面需要考虑与完善：**
  - **怎么做到分布式的授权与验权**
  - **怎么实现 PEP、PDP 等 ABAC 提倡的模块设计**
  - **这些东西可否做成应用程序透明的方式，可否与 security sidecar 集成等等**

## 2.3. 自主型访问控制（DAC）

<p style="color:red;">
待做
</p>

[Linux安全操作系统构建方法与技术(第三讲)——自主访问控制与强制访问控制]()https://www.doc88.com/p-5196497909047.html)

Discretionary Access Control -

用户/对象来决定访问控制权限。信息的所有者来决定谁有权利来访问信息以及操作类型（读，写，执行...）

- 定义：由资源的所有者、某些组的成员确定访问权限。
- 优点：可以基于数据/资源自主控制权限。
- 缺点：控制较为分散，不易管理。
- 例子：文章的发布者指定哪些其它用户可以对这篇文章进行哪些操作。例如UNIX权限管理

## 2.4. 强制性访问控制（MAC）

[Linux安全操作系统构建方法与技术(第三讲)——自主访问控制与强制访问控制]()https://www.doc88.com/p-5196497909047.html)

Mandatory Access Controll

系统来决定访问权限。安全属性是强制型的规定，它由安全管理员或操作系统根据限定的规则确定的，是一种规则的访问控制。

- 定义：给信息添加敏感程度标签，与用户的敏感程度标签进行对比确定能否访问。而标签是由管理员设定，
- 优点：适用于安全要求较高如军事相关的系统。
- 缺点：不够灵活。
- 例子：A 资源拥有敏感标签B，C用户拥有敏感标签D，如果D不小于B，则B可以访问A。

## 2.5. 基于访问控制列表（ACL）

Access Control List

- 网络：[ACL（访问控制列表）基础篇](https://zhuanlan.zhihu.com/p/39191464)
- centos8:[ACCESS CONTROL LISTS](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/7/html/system_administrators_guide/ch-access_control_lists)


## 2.6. 规则驱动的基于角色的访问控制

提供了一种基于约束的访问控制。

## 2.7. 基于属性证书的访问控制

访问权限信息存放在用户属性证书的权限属性中，每个权限属性描述了一个或多个用户的访问权限。当用户对某一个资源提出访问请求时，系统根据用户的属性证书中的权限来判断是否允许。

# 3. 鉴权方法

## 3.1. HTTP Basic Authentication

### 3.1.1. 说明

这种授权方式是浏览器遵守http协议实现的基本授权方式，HTTP协议进行通信的过程中，HTTP协议定义了基本认证允许HTTP服务器对客户端进行用户身份证的方法。

### 3.1.2. 流程

![authen-1](./image/authen-1.png)

- 客户端向服务器请求数据，
  - 请求的内容可能是一个网页或者是一个ajax异步请求，
  - 此时，假设客户端尚未被验证，则客户端提供如下请求至服务器:

  ```http
    Get /index.html HTTP/1.0 
    Host:www.google.com
  ```

- 服务器向客户端发送验证请求代码401
  - （WWW-Authenticate: Basic realm="google.com"这句话是关键，
  - 如果没有客户端不会弹出用户名和密码输入界面）服务器返回的数据大抵如下：

  ```http
    HTTP/1.0 401 Unauthorised 
    Server: SokEvo/1.0 
    WWW-Authenticate: Basic realm=”google.com” 
    Content-Type: text/html 
    Content-Length: xxx
  ```

- 当符合http1.0或1.1规范的客户端（如IE，FIREFOX）收到401返回值时，
  - **将自动弹出一个登录窗口**
  - 要求用户输入用户名和密码。

- 用户输入用户名和密码后，将用户名及密码以BASE64加密方式加密，并将密文放入前一条请求信息中，则客户端发送的第一条请求信息则变成如下内容：

  ```http
    Get /index.html HTTP/1.0 
    Host:www.google.com 
    Authorization: Basic d2FuZzp3YW5n
  ```

  > 注：`d2FuZzp3YW5n`表示加密后的用户名及密码 <br />
  > （用户名：密码 然后通过base64加密，加密过程是浏览器默认的行为，不需要我们人为加密，我们只需要输入用户名密码即可）

- 服务器收到上述请求信息后，
  - 将 `Authorization` 字段后的用户信息取出、解密，
  - 将解密后的用户名及密码与用户数据库进行比较验证，
  - 如用户名及密码正确，服务器则根据请求，将所请求资源发送给客户端

### 3.1.3. 效果

- 客户端未未认证的时候，会弹出用户名密码输入框，这个时候请求时属于 `pending` 状态
  > ![authen-2](./image/authen-2.png)
- 当用户输入用户名密码的时候客户端会再次发送带 `Authentication` 头的请求。
  > ![authen-3](./image/authen-3.png)

### 3.1.4. 实现

- server.js
  <details>
  <summary style="color:red;">代码</summary>

    ```javascript
    let express = require("express");
    let app = express();

    app.use(express.static(__dirname+'/public'));

    app.get("/Authentication_base",function(req,res){
      console.log('req.headers.authorization:',req.headers)
      if(!req.headers.authorization){
        res.set({
          'WWW-Authenticate':'Basic realm="wang"'
        });
        res.status(401).end();
      }else{
        let base64 = req.headers.authorization.split(" ")[1];
        let userPass = new Buffer(base64, 'base64').toString().split(":");
        let user = userPass[0];
        let pass = userPass[1];
        if(user=="wang"&&pass="wang"){
          res.end("OK");
        }else{
          res.status(401).end();
        }
      }
    })

    app.listen(9090)
    ```
  </details>

- index.html
  <details>
  <summary style="color:red;">代码</summary>

    ```html
    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="UTF-8">
        <title>HTTP Basic Authentication</title>
      </head>
      <body>
        <div></div>
        <script src="js/jquery-3.2.1.js"></script>
        <script>
          $(function(){
          send('./Authentication_base');
          })
          var send = function(url){
                $.ajax({ 
                url : url, 
                method : 'GET', 
              });
          }
        </script>
      </body>
    </html>
    ```
  </details>

### 3.1.5. 优缺点

- 优点：
  - 基本认证的一个优点是基本上所有流行的网页浏览器都支持基本认证。
  - 基本认证很少在可公开访问的互联网网站上使用，有时候会在小的私有系统中使用（如路由器网页管理接口）。
  - 后来的机制HTTP摘要认证是为替代基本认证而开发的，允许密钥以相对安全的方式在不安全的通道上传输。
  - 程序员和系统管理员有时会在可信网络环境中使用基本认证，使用Telnet或其他明文网络协议工具手动地测试Web服务器。
  - 这是一个麻烦的过程，但是网络上传输的内容是人可读的，以便进行诊断。

- 缺点：
  - 虽然基本认证非常容易实现，但该方案创建在以下的假设的基础上，即：
    - 客户端和服务器主机之间的连接是安全可信的。
      - 特别是，如果没有使用`SSL/TLS`这样的传输层安全的协议，那么以明文传输的密钥和口令很容易被拦截。
      - 该方案也同样没有对服务器返回的信息提供保护。
  - 现存的浏览器保存认证信息直到标签页或浏览器被关闭，或者用户清除历史记录。
    - HTTP没有为服务器提供一种方法指示客户端丢弃这些被缓存的密钥。
    - 这意味着服务器端在用户不关闭浏览器的情况下，并没有一种有效的方法来让用户注销。


## 3.2. Cookie=Session登录验证

### 3.2.1. cookie说明

- 原因：
  - Http协议是一个无状态的协议，服务器不会知道到底是哪一台浏览器访问了它
  - 因此需要一个标识用来让服务器区分不同的浏览器。`cookie` 就是这个管理服务器与客户端之间状态的标识。

- 原理
  - 浏览器第一次向服务器发送请求时，服务器在 `response` 头部设置 `Set-Cookie` 字段
  - 浏览器收到响应就会设置 `cookie` 并存储
  - 在下一次该浏览器向服务器发送请求时，就会在 `request` 头部自动带上 `Cookie` 字段
  - 服务器端收到该 `cookie` 用以区分不同的浏览器。
  - 当然，这个 `cookie` 与某个用户的对应关系应该在第一次访问时就存在服务器端，这时就需要 `session` 了。

```javascript
const http = require('http')
http.createServer((req, res) => {
  if (req.url === '/favicon.ico') {
    return
  } else {
    res.setHeader('Set-Cookie', 'name=zhunny')
    res.end('Hello Cookie')
  }
}).listen(3000) 
```

### 3.2.2. session说明

- `session` 是会话的意思
  - 浏览器第一次访问服务端，服务端就会创建一次会话，在会话中保存标识该浏览器的信息。
  - 他们都由服务端生成，为了弥补 `Http` 协议无状态的缺陷。
  - 它与 `cookie` 的区别就是 `session` 是缓存在服务端的，`cookie` 则是缓存在客户端

### 3.2.3. session-cookie认证

- 步骤
  - 服务器在接受客户端首次访问时在服务器端创建seesion
    - 我们可以将seesion保存在内存中，也可以保存在redis中，推荐使用后者，
    - 然后给这个session生成一个唯一的标识字符串,然后在 响应头中种下这个唯一标识字符串。
  - 签名(非必需步骤)
    - 这一步通过秘钥对sid进行签名处理，避免客户端修改sid。
  - 浏览器中收到请求响应的时候会解析响应头，然后将sid保存在本地cookie中，浏览器在下次http请求的请求头中会带上该域名下的cookie信息。
  - 服务器在接受客户端请求时会去解析请求头cookie中的sid，然后根据这个sid去找服务器端保存的该客户端的session，然后判断该请求是否合法。

![authen-4](./image/authen-4.png)

```javascript
const http = require('http')
//此时session存在内存中
const session = {}
http.createServer((req, res) => {
  const sessionKey = 'sid'
  if (req.url === '/favicon.ico') {
    return
  } else {
    const cookie = req.headers.cookie
    //再次访问，对sid请求进行认证
    if (cookie && cookie.indexOf(sessionKey) > -1) {
      res.end('Come Back')
    }
    //首次访问，生成sid，保存在服务器端
    else {
      const sid = (Math.random() * 9999999).toFixed()
      res.setHeader('Set-Cookie', `${sessionKey}=${sid}`)
      session[sid] = { name: 'zhunny' }
      res.end('Hello Cookie')
    }
  }
}).listen(3000)
```
### 3.2.4. 用户登录认证

- 使用session-cookie做登录认证时，登录时存储session，退出登录时删除session，
- 而其他的需要登录后才能操作的接口需要提前验证是否存在session，存在才能跳转页面，不存在则回到登录页面。
- 用户相关信息也可以存入Session供鉴权使用

### 3.2.5. 缺点

- **①** **占资源：** 
  - 每个用户经过我们的应用认证之后，我们的应用都要在服务端做一次记录，以方便用户下次请求的鉴别，
  - 通常而言session都是保存在内存中，而随着认证用户的增多，服务端的开销会明显增大。
    - 为了满足全局一致性，我们最好把 `session` 存储在 `redis` 中做持久化，
    - 而在分布式环境下，我们可能需要在每个服务器上都备份，占用了大量的存储空间。
- **② 扩展性弱：**
  - 用户认证之后，服务端做认证记录，如果认证的记录被保存在内存中的话，这意味着用户下次请求还必须要请求在这台服务器上,这样才能拿到授权的资源，
  - 这样在分布式的应用上，相应的限制了负载均衡器的能力。这也意味着限制了应用的扩展能力。
  - 同样Session和Cookie只能应用在浏览器上，app端无法使用
- **③ CSRF攻击：**
  - 因为是基于cookie来进行用户识别的, cookie如果被截获，用户就会很容易受到CSRF跨站请求伪造的攻击。

## 3.3. token

### 3.3.1. 说明

- `token` 是一个令牌
- 浏览器第一次访问服务端时会签发一张令牌，之后浏览器每次携带这张令牌访问服务端就会认证该令牌是否有效
- 只要服务端可以解密该令牌，就说明请求是合法的
- 令牌中包含的用户信息还可以区分不同身份的用户。一般 `token` 由用户信息、时间戳和由 `hash` 算法加密的签名构成。

### 3.3.2. Token认证流程

1. 客户端使用用户名跟密码请求登录
2. 服务端收到请求，去验证用户名与密码
3. 验证成功后，服务端会签发一个 `Token`，再把这个 `Token` 发送给客户端
4. 客户端收到 `Token` 以后可以把它存储起来，比如放在 `Cookie` 里或者`Local Storage` 里
5. 客户端每次向服务端请求资源的时候需要带着服务端签发的 `Token`
6. 服务端收到请求，然后去验证客户端请求里面带着的 `Token`（request头部添加Authorization），如果验证成功，就向客户端返回请求的数据 ，如果不成功返回401错误码，鉴权失败。

### 3.3.3. token的缺点

- 性能： 加密解密消耗使得 `token` 认证比 `session-cookie` 更消耗性能。
- `token` 比 `sessionId` 大，更占带宽。(基本可以忽略)

### 3.3.4. Token和session的区别

- 区别
  - 使用局限：`token` 认证不局限于 `cookie` ，这样就使得这种认证方式可以支持多种客户端，而不仅是浏览器。且不受同源策略的影响。
  - 安全：不使用 `cookie` 就可以规避CSRF攻击。
  - 扩展性：`token` 不需要存储，`token` 中已包含了用户信息，服务器端变成无状态，服务器端只需要根据定义的规则校验这个 `token` 是否合法就行。这也使得 `token` 的可扩展性更强。

## 3.4. JWT（JSON Web Token）

### 3.4.1. 说明

- 定义：
  - **Json web token (JWT)**
  - 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（[(RFC 7519](https://link.jianshu.com/?t=https://tools.ietf.org/html/rfc7519)).
  - 该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。
  - JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，
  - 也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。

- 原理： **基于token的鉴权机制** ：
  - 基于token的鉴权机制类似于http协议也是无状态的，它不需要在服务端去保留用户的认证信息或者会话信息。
  - 这就意味着基于token认证机制的应用不需要去考虑用户在哪一台服务器登录了，这就为应用的扩展提供了便利。

- 基本流程：
  1. 客户端使用用户名跟密码请求登录
  2. 服务端收到请求，去验证用户名与密码
  3. 验证成功后，服务端会签发一个`Token`，再把这个`Token`发送给客户端
  4. 客户端收到`Token`以后可以把它存储起来，比如放在`Cookie`里或者`Local Storage`里
  5. 客户端每次向服务端请求资源的时候需要带着服务端签发的`Token`
  6. 服务端收到请求，然后去验证客户端请求里面带着的`Token`，如果验证成功，就向客户端返回请求的数据

  > 这个**token**必须要在每次请求时传递给服务端，它应该保存在请求头里， 另外，服务端要支持[CORS(跨域资源共享)](https://www.jianshu.com/p/330f03bb8b7f)策略，一般我们在服务端这么做就可以了`Access-Control-Allow-Origin: *`。

  ![authen-7](./image/authen-7.png)

### 3.4.2. JWT的组成部分

- Header（头部），可反解
  - Header部分是一个JSON对象，描述JWT的元数据。
  - 一般描述信息为该Token的加密算法以及Token的类型。
  - {"alg"": "HS256","typ": "JWT"}的意思就是
    - 该token使用HS256加密，
    - token类型是JWT。
  - 这个部分基本相当于明文，它将这个JSON对象**只做了一个Base64转码**，变成一个字符串。Base64编码解码是有算法的，解码过程是可逆的。
  - 头部信息默认携带着两个字段。
- Payload（负载），可反解
  - Payload 部分也是一个 JSON 对象，用来存放实际需要传递的数据。
    - 公共的声明：7个官方字段
      <details>
      <summary style="color:red;">官方字段</summary>
      
        ```
        iss: jwt签发者
        sub: jwt所面向的用户
        aud: 接收jwt的一方
        exp: jwt的过期时间，这个过期时间必须要大于签发时间
        nbf: 定义在什么时间之前，该jwt都是不可用的.
        iat: jwt的签发时间
        jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        ```
      </details>
    - 私有的声明：在这个部分定义私有字段。
  - 一般存放用户名、用户身份以及一些JWT的描述字段。
  - 它也**只是做了一个Base64编码**，因此肯定不能在其中存放秘密信息，比如说登录密码之类的。
- Signature（签名），不可逆
  - Signature是对前面两个部分的签名，防止数据篡改，
  - 如果前面两段信息被人修改了发送给服务器端，此时服务器端是可利用签名来验证信息的正确性的。
  - 签名需要使用密钥计算得到，密钥是服务器端保存的，用户不知道。
  - 算出签名以后，把 Header、Payload、Signature 三个部分拼成一个字符串，每个部分之间用”点”（.）分隔，就可以返回给用户。

### 3.4.3. 示例

```bash
  eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
  ## 头部（header）. 载荷（payload）. 签证（signature）
```

在该网站[JWT](https://jwt.io/)，可以解码或编码一个JWT。一个JWT形如：

![authen-5](./image/authen-5.png)

### 3.4.4. 优点

- 通用：因为json的通用性，所以JWT是可以进行跨语言支持的，像JAVA, PHP, NodeJS, JavaScript, 等很多语言都可以使用。
- 信息交换：因为有了payload部分，所以JWT可以在自身存储一些其他业务逻辑所必要的非敏感信息。有效使用 JWT，可以降低服务器查询数据库的次数。
- 便于传输：jwt的构成非常简单，字节占用很小，所以它是非常便于传输的。
- 扩展：它不需要在服务端保存会话信息, 所以它易于应用的扩展

### 3.4.5. 安全相关

- JWT 默认是不加密，但也是可以加密的。生成原始 Token 以后，可以用密钥再加密一次。
- 不应该在jwt的payload部分存放敏感信息，因为该部分是客户端可解密的部分。
- 保护好secret私钥，该私钥非常重要。
- 如果可以，请使用https协议
- JWT 的最大缺点是，由于服务器不保存 session 状态，因此无法在使用过程中废止某个 token，或者更改 token 的权限。也就是说，一旦 JWT 签发了，在到期之前就会始终有效，除非服务器部署额外的逻辑。
- JWT 本身包含了认证信息，一旦泄露，任何人都可以获得该令牌的所有权限。为了减少盗用，JWT 的有效期应该设置得比较短。对于一些比较重要的权限，使用时应该再次对用户进行认证。

### 3.4.6. JWT验证用户登录

- 前段代码

  <details>
  <summary style="color:red;">前端代码</summary>

  ```javascript
  //前端代码
  //axios的请求拦截器，在每个request请求头上加JWT认证信息
  axios.interceptors.request.use(
      config => {
          const token = window.localStorage.getItem("token");
          if (token) {
          // 判断是否存在token，如果存在的话，则每个http header都加上token
          // Bearer是JWT的认证头部信息
              config.headers.common["Authorization"] = "Bearer " + token;
          }
          return config;
      },
      err => {
          return Promise.reject(err);
      }
  );
  //登录方法：在将后端返回的JWT存入localStorage
  async login() {
      const res = await axios.post("/login-token", {
          username: this.username,
          password: this.password
      });
      localStorage.setItem("token", res.data.token);
  },
  //登出方法：删除JWT
  async logout() {
      localStorage.removeItem("token");
  },
  async getUser() {
      await axios.get("/getUser-token");
  }
  ```
  </details>

- 后端代码

  <details>
  <summary style="color:red;">后端代码</summary>

  ```javascript
  //后端代码
  const jwt = require("jsonwebtoken");
  const jwtAuth = require("koa-jwt");
  //用来签名的密钥
  const secret = "it's a secret";

  router.post("/login-token", async ctx => {
    const { body } = ctx.request;
    //登录逻辑，略，即查找数据库，若该用户和密码合法，即将其信息生成一个JWT令牌传给用户
    const userinfo = body.username;
    ctx.body = {
      message: "登录成功",
      user: userinfo,
      // 生成 token 返回给客户端
      token: jwt.sign(
        {
          data: userinfo,
          // 设置 token 过期时间，一小时后，秒为单位
          exp: Math.floor(Date.now() / 1000) + 60 * 60
        },
        secret
      )
    };
  });

  //jwtAuth这个中间件会拿着密钥解析JWT是否合法。
  //并且把JWT中的payload的信息解析后放到state中，ctx.state用于中间件的传值。
  router.get(
    "/getUser-token",
    jwtAuth({
      secret
    }),
    async ctx => {
      // 验证通过，state.user
      console.log(ctx.state.user);
      ctx.body = {
        message: "获取数据成功",
        userinfo: ctx.state.user.data 
      };
    }
  )
  //这种密码学的方式使得token不需要存储，只要服务端能拿着密钥解析出用户信息，就说明该用户是合法的。
  //若要更进一步的权限验证，需要判断解析出的用户身份是管理员还是普通用户。
  ```
  </details>

## 3.5. HMAC（AK/SK）认证

### 3.5.1. 基本原理

- 名称来源：
  - 这种基于 AK/SK 的认证方式主要是利用散列的消息认证码 (Hash-based MessageAuthentication Code) 来实现的因此有很多地方叫 HMAC 认证
  - 实际上不是非常准确。
  - HMAC 只是利用带有 key 值的哈希算法生成消息摘要，在设计 API 时有具体不同的实现。
  - HMAC算法 在作为网络通信的认证设计中作为凭证生成算法使用，避免了口令等敏感信息在网络中传输。

  > ![authen-9](./image/authen-9.png)

- 说明
  - access key:用来标识时哪个用户或者app
    - 当有多个用户时，不同用户有不同的 access key
    - access key给后端是为了知道当前用户的secret key
  - secure key:用来加密解密
  - message:实际传输的信息

- 用途：
  - 一般用于后台程序执行API调用时的服务端认证；AK标识用户，SK作为对称加密通信的秘钥。
  - ak 是 key，sk 其实是 value 。调用者用 sk 加密数据，并把 ak 一起传过去。服务端用 ak 查询对应的 sk 解密数据。
  - 服务端和调用方都应该持有SK

- 基本流程：
  - 客户端：
    - 客户端需要在认证服务器中预先设置 **access key（AK 或叫 app ID）**  和 **secure key（SK）**
    - 在调用 API 时，客户端需要对**要传递的数据和 access key** 进行自然排序后并**使用 secure key 进行加密**生成一个额外的参数 digest(摘要)
      > **sk只作为加密算法的参数**，**不会进行传输** ，传输的是使用sk加密得到的摘要
  - 服务端
    - 服务器根据预先设置的 secure key 进行与上述同样的摘要计算
    - 要求结果完全一致，否则说明认证失败
  - 注意:
    - **secure key 不能在网络中传输，以及在不受信任的位置存放（浏览器等）**

### 3.5.2. 干扰信息

### 3.5.3. 说明

#### 3.5.3.1. 质疑/应答算法

#### 3.5.3.2. 基于时间的一次性密码认证

## 3.6. OAuth

![authen-6](./image/authen-6.png)

- **[OAuth2.0笔记跳转](./OAuth2.0.md)**

## 3.7. 基于RBAC与数据库

> 在架构设计的章节中我们提到了除用户、角色和权限外，他们相互联系的关系共五个层面，映射到数据库的设计对应的就是五张表。

- user（用户）：每个用户都有唯一的 UID 识别，并被授予不同的角色
- role（角色）：不同角色具有不同权限
- permission（权限）：访问权限
- 用户-角色映射：用户和角色之间的映射关系
- 角色-权限映射：角色和权限之间的映射

![rbac-9](./image/rbac-9.png)

![rbac-10](./image/rbac-10.png)

# 4. 参考资料

- [前后端常见的几种鉴权方式](https://juejin.cn/post/6844903927100473357)
- [聊聊鉴权那些事](https://segmentfault.com/a/1190000020146855)
- [4种常见的鉴权方式及说明](https://blog.csdn.net/sinat_33255495/article/details/103920131)
- [基于token的鉴权机制 JWT介绍](https://www.jianshu.com/p/dec4fe44255b)
- [深入理解令牌认证机制（token）](https://segmentfault.com/a/1190000018632472) 待整理
- [细说API - 认证、授权和凭证](https://juejin.cn/post/6844903807839649806)
- [用户权限管理数据库设计（RBAC）](https://www.cnblogs.com/myseries/p/10871633.html) 待整理
- [细说API - 认证、授权和凭证](https://juejin.cn/post/6844903807839649806)
- [ABAC - 基于属性的访问控制 - 复杂场景下访问控制解决之道](https://blog.csdn.net/XiaoBeiTu/article/details/100773968)
- [权限体系解析 功能&数据](https://zhuanlan.zhihu.com/p/67486776) 待整理
- [NIST (美国国家标准技术研究所) 的 ABAC 的定义与实现考虑，最重要的资料](https://nvlpubs.nist.gov/nistpubs/specialpublications/NIST.SP.800-162.pdf) 待阅读
- [一文读懂 AWS IAM - 知乎](https://zhuanlan.zhihu.com/p/111676632) 待整理
- [权限系统设计](https://zhuanlan.zhihu.com/p/362733752)

