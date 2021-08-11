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

## 1.2. 其他注意点

- 用户账户的存储:
  - 也不一定是存放在数据库中的一张表
  - 在一些企业 IT 系统中，对账户管理和权限有了更多的要求。
  - 所以账户技术 （accounting）可以帮助我们使用不同的方式管理用户账户，同时具有不同系统之间共享账户的能力
  - 例如微软的活动目录（AD），以及简单目录访问协议（LDAP），甚至区块链技术。

# 2. 访问控制策略

## 2.1. 基于访问控制列表（ACL）

## 2.2. 基于用户角色的访问控制（RBAC）


### 2.2.1. 说明

![rbac-1](./image/rbac-1.png)

- 说明：RBAC是一套成熟的权限模型。

- 对比：
  - 在传统权限模型中，我们直接把权限赋予用户。
  - 而在RBAC中，增加了“角色”的概念，我们首先把权限赋予角色，再把角色赋予用户。这样，由于增加了角色，授权会更加灵活方便。

- 种类：
  - 在RBAC中，根据权限的复杂程度，又可分为RBAC0、RBAC1、RBAC2、RBAC3。
  - 其中，RBAC0是基础，RBAC1、RBAC2、RBAC3都是以RBAC0为基础的升级。
  - 我们可以根据自家产品权限的复杂程度，选取适合的权限模型。


### 2.2.2. RBAC0

![rbac-2](./image/rbac-2.png)

![rbac-7](./image/rbac-7.png)

- RBAC0是基础，很多产品只需基于RBAC0就可以搭建权限模型了。
- 在这个模型中，我们**把权限赋予角色，再把角色赋予用户**。
- 用户和角色，角色和权限都是**多对多**的关系。
- 用户拥有的权限等于他所有的角色持有**权限之和**。

### 2.2.3. RBAC1

![rbac-3](./image/rbac-3.png)

![rbac-8](./image/rbac-8.png)

 - RBAC1建立在RBAC0基础之上，
 - 在角色中引入了**继承**的概念。
 - 简单理解就是，**给角色可以分成几个等级**，每个等级权限不同，从而实现更细粒度的权限管理。

### 2.2.4. RBAC2

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

### 2.2.5. RBAC3

![rbac-5](./image/rbac-5.png)

- RBAC3是RBAC1和RBAC2的合集，所以RBAC3既有角色分层，也包括可以增加各种限制。

### 2.2.6. 延伸:用户组

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

- 用途：
  - 一般用于后台程序执行API调用时的服务端认证；AK标识用户，SK作为对称加密通信的秘钥。
  - ak 是 key，sk 其实是 value 。调用者用 sk 加密数据，并把 ak 一起传过去。服务端用 ak 查询对应的 sk 解密数据。
  - 服务端和调用方都应该持有SK

- 基本流程：
  - 客户端：
    - 客户端需要在认证服务器中预先设置 **access key（AK 或叫 app ID）**  和 **secure key（SK）**
    - 在调用 API 时，客户端需要对参数和 access key 进行自然排序后并使用 secure key 进行签名生成一个额外的参数 digest(摘要)
    - **sk只作为加密算法的参数**，不会进行传输
  - 服务端
    - 服务器根据预先设置的 secure key 进行同样的摘要计算
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


