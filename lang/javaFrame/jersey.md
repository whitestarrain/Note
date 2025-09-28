# 简介

- 开发RESTful WebService意味着支持在多种媒体类型以及抽象底层的客户端-服务器通信细节，如果没有一个好的工具包可用，这将是一个困难的任务
- 为了简化使用JAVA开发RESTful WebService及其客户端，一个轻量级的标准被提出：JAX-RS API(Java API for RESTful Web Services.)
- Jersey RESTful WebService框架是一个开源的、产品级别的JAVA框架，支持JAX-RS API并且是一个JAX-RS(JSR 311和 JSR 339)的参考实现
- Jersey提供自己的API，其API继承自JAX-RS，提供更多的特性和功能以进一步简化RESTful service和客户端的开发

# 简单配置

- 转到创建 maven 项目的文件夹，在 pom.xml 中添加所需的依赖项
  ```xml
  <dependencies>
      <!-- Jersey 2.22.2 -->
      <dependency>
          <groupId>org.glassfish.jersey.containers</groupId>
          <artifactId>jersey-container-servlet</artifactId>
          <version>${jersey.version}</version>
      </dependency>
      <!-- JSON/POJO support -->
      <dependency>
          <groupId>org.glassfish.jersey.media</groupId>
          <artifactId>jersey-media-json-jackson</artifactId>
          <version>${jersey.version}</version>
      </dependency>
  </dependencies>

  <properties>
      <jersey.version>2.22.2</jersey.version>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
  ```

- 在 Web.xml 中，添加以下代码
  ```java
  public class ApplicationConfig extends ResourceConfig {
      public ApplicationConfig() {
          register(OtherStuffIfNeeded.class);
      }
  }
  ```
  ```xml
  <servlet>
      <servlet-name>jersey-serlvet</servlet-name>
      <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
      <init-param>
          <param-name>jersey.config.server.provider.packages</param-name>
          <!-- Service or resources to be placed in the following package --> 
          <param-value>com.stackoverflow.service</param-value>
      </init-param>
    
      <!-- Application configuration, used for registering resources like filters  -->
      <init-param>
          <param-name>javax.ws.rs.Application</param-name>
          <param-value>com.stackoverflow.config.ApplicationConfig</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
  </servlet>

  <!-- Url mapping, usage-http://domainname:port/appname/api/ -->
  <servlet-mapping>
      <servlet-name>jersey-serlvet</servlet-name>
      <url-pattern>/api/*</url-pattern>
  </servlet-mapping>
  ```
  > 还应该注意的是，如果你想 **不使用** web.xml，你可以简单地删除它， <br />
  > 并在 `ApplicationConfig` 类之上添加 `@ApplicationPath("/api")`。
  ```java
    @ApplicationPath("/api")
    public class ApplicationConfig extends ResourceConfig {
        public ApplicationConfig() {
            // this call has the same effect as
            // jersey.config.server.provider.packages
            // in the web.xml: it scans that packages for resources and providers. 
            packages("com.stackoverflow.service");
        }
    }
    ```

- 编写服务
  ```java
  package com.stackoverflow.service;
  
  import javax.ws.rs.GET;
  import javax.ws.rs.Path;
  import javax.ws.rs.PathParam;
  
  @Path("/first")
  public class FirstRESTfull {
      private static final Logger log = LoggerFactory.getLogger(FirstRESTfull.class);
  
      @GET
      @Path("/{userId}")
      public String getParam(@PathParam(value = "userId") String userId) {
      System.out.println(userId);
          return userId;
      }
  }
  ```
- 部署到tomcat，访问`http://localhost:8080/你的项目名/api/first/sss `

# 注解说明

## @Path

- @Path
  - 使用：
    - @Path注解的值是一个**相对的URI路径**
    - @Path的有没有/开头是一样的，同理，结尾有没有包含/也是一样的。
  - 作用：
    - 标注class
      - 表明该类是个资源类。
      - 凡是资源类，必须使用@Path注解，不然jersey无法扫描到该资源类。
    - 标注method
      - 表示具体的请求资源的路径
      - 这点和Spring的@RequestMapping不同，@RequestMapping只是简单的注解请求路径而已，@Controller才是表明该类是个action
      - 而@Path一人扮演两个角色

  - 示例
    ```java
    package com.stackoverflow.service;
    
    import javax.ws.rs.GET;
    import javax.ws.rs.Path;
    import javax.ws.rs.PathParam;
    
    @Path("/first")
    public class FirstRESTfull {
        private static final Logger log = LoggerFactory.getLogger(FirstRESTfull.class);
    
        @GET
        @Path("/{userId}")
        public String getParam(@PathParam(value = "userId") String userId) {
        System.out.println(userId);
            return userId;
        }
    }
    ```

## 声明http请求的请求方式

- @GET: GET请求（读取/列出/检索单个或资源集合。）
- @POST: POST请求（新建资源。）
- @PUT: PUT请求（更新现有资源或资源集合。）
- @DELETE: DELETE请求（删除资源或资源集合。）

## @Produces，@Consumes

- @Consumes
  - 用来指定可以接受client发送过来的MIME类型，
  - 同样可以用于class或者method，
  - 也可以指定多个MIME类型，一般用于@PUT，@POST
  - 如果方法返回void，则给客户端返回204
    ```java
    @POST
    @Consumes("text/plain")
    public void postClichedMessage(String message) {
        // Store the message
    }
    ```

- @Produces
  - 用来指定将要返回给client端的数据标识类型（MIME）
  - @Produces可以作为class注释，也可以作为方法注释，
  - 方法的@Produces注释将会覆盖class的注释
  ```java
  @Path("/myResource")
  @Produces("text/plain")
  public class SomeResource {
      @GET
      public String doGetAsPlainText() {
          ...
      }
  
      @GET
      @Produces("text/html")
      public String doGetAsHtml() {
          ...
      }
  }
  ```
  - @Produce注解可以指定多个值，同时可以指定quality factor：
    ```java
    @GET
    @Produces({"application/xml; qs=0.9", "application/json"})
    public String doGetAsXmlOrJson() {
        ...
    }
    ```

## 请求参数注释

- @PathParam: 
  - 用于获取url中直接在斜杠后面添加参数值
    ```java
    @DELETE
    @Path("/mqHosts/{mqId}/queues/{queueId}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @ApiOperation(value = "删除MQ主机下的一个队列")
    public Object deleteMqQueue(@Context HttpServletRequest request,
                                    @PathParam("mqId") String mqId,
                                    @PathParam("queueId") String queueId) {}
    // url /mqHosts/1/queues/2    
    // mqId=1,queueId=2
    ```
  - 我们还可以对模板参数的格式做约束，例如我们只允许大小写字符以及数字，则可以使用下面的正则表达式来限制模板参数，如果请求路径不符合要求，将会返回404.
    ```java
    @Path("users/{username: [a-zA-z_0-9]*}")
    ```

- @QueryParam: 用于获取url中在后面添加【键值对】形式的参数
  ```java
  @GET
      @Path("/interfaceLimits")
      @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
      @ApiOperation(value = "查询租户所有计费接口的计费规则")
      public Object getAllInterfaceLimitsByTenantId(@Context HttpServletRequest request,
                                                    @QueryParam("tenantId") String tenantId) {
  // url /interfaceLimits?tenantId=1
  // tenantId=1
  ```

- @Context:
  - 该注解用来解析上下文参数。(@Context HttpRequest request)

- @FormParam:
  - 客户端以form(MIME为application/x-www-form-urlencoded)的方式提交表单，服务端使用@FormParam解析form表单中的参数

- @FormDataParam:
  - 通常在上传文件的时候，需要@FormDataParam。客户端提交form(MIME为multipart/form-data)的方式提交表单，服务端使用

- @FormDataParam: 
  - 用来解析form表单中的参数

- @HeaderParam: 
  - 获取http请求头中的参数值

- @CookieParam: 
  - 获取http请求头中cookie中的参数值

- @MatrixParam: 
  - 获取请求URL中的参数中的键值对，
  - 必须使用";"作为键值对的分隔符,比如/person/1;id=1;name=bella;age=22。
  - 除此之外呢，@MatrixParam可以接受List参数，尤其是在键值对key相同的时候，就会被解析为List

- @DefaultValue
  - @DefaultValue配合@PathParam、@QueryParam、@FormParam、@FormDataParam、@MatrixParam、@HeaderParam、@CookieParam等使用，
  - 如果请求指定的参数中没有值，就使用@DefaultValue中的值为默认值。
  - 注意：@DefaultValue指定的值在解析过程中出错（比如@DefaultValue("test") @QueryParam("age") int age），将**返回404错误**。

- @BeanParam
  - 如果传递的参数较多，可以自己写个bean，
  - bean中的字段使用@PathParam、@QueryParam、@FormParam、@FormDataParam、@MatrixParam、@HeaderParam、@CookieParam来注解。
  - 而在resouces中具体方法参数中就可以使用@BeanParam来注解这个自定义的bean

- @Encoded:禁止解码，客户端发送的参数是什么样，服务器接收到的参数就是什么样

