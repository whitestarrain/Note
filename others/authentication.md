# HTTP Basic Authentication

## 说明

这种授权方式是浏览器遵守http协议实现的基本授权方式，HTTP协议进行通信的过程中，HTTP协议定义了基本认证允许HTTP服务器对客户端进行用户身份证的方法。

## 流程

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

## 效果

- 客户端未未认证的时候，会弹出用户名密码输入框，这个时候请求时属于 `pending` 状态
  > ![authen-2](./image/authen-2.png)
- 当用户输入用户名密码的时候客户端会再次发送带 `Authentication` 头的请求。
  > ![authen-3](./image/authen-3.png)

## 实现

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
  <summary style="color:red;"></summary>

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

## 优缺点

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


# Cookie=Session

# token

# JWT

# OAuth

[OAuth2.0](./OAuth2.0.md)

# 数据库





# 参考资料

- [前后端常见的几种鉴权方式](https://juejin.cn/post/6844903927100473357)
- [聊聊鉴权那些事](https://segmentfault.com/a/1190000020146855)
- [4种常见的鉴权方式及说明](https://blog.csdn.net/sinat_33255495/article/details/103920131)
- [基于token的鉴权机制 JWT介绍](https://www.jianshu.com/p/dec4fe44255b)


