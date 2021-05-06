# 入门

- 注意：spring会扫描引导类统计级别以及子级别下的所有类。不同包的话无法扫描到

# 整合servlet

- 无其他注解，返回的是字符串时，会调用视图解析器，寻找名称为指定字符串的html（而springmvc中默认为jsp视图解析器，）

- @RequestMapping:
  - 位置：
    - 类上：一级目录
    - 方法上：二级目录
  - 属性：具体pdf
    - value:和path相同
    - method:指定只接收那中请求方式，默认所有种类 
      > RequestMapping(value="/saveAccount",method=RequestMethod.POST)
    - param:用于指定必须要传的参数
      - RequestMapping(params={"username"})：必须有username属性
      - RequestMapping(params={"username=admin"})：表示必须要与username属性且值为admin
      - RequestMapping(params = {"moeny!100"})，表示请求参数中money不能是100
    - header：表示必须请求头必须含有指定键对应的键值对才行
      - header={"Accept"}：必须有Accept请求头

- 请求参数的绑定
  - 形参和请求参数key相同时会自动绑定
  - 类型：
    - 基本
    - pojo
      - pojo有pojo
        ```
        要封装为Account
        Account:
          String number
          int money
          User user
            int id
            String name
        那么form中:
        number
        money
        user.id
        user.name
        ```
    - list,map

- 自定义类型转换器

- 获取servlet原生api

- @requestparam：把请求中指定名称的参数给控制器中的形参赋值
- @RequestBody：用于获取请求体内容。直接使用得到是key=value&key=value...结构的数据。get方式不能用
- @PathVariable：用于绑定url中的占位符。例如：请求url中/delete/{id}，这个{id}就是url占位符。
- @RequestHeader：用于获取请求消息头
- @CookieValue：用于把指定cookie名称的值传入控制器方法参数。
- @ModelAttribute：在处理器执行之前执行
- @SessionAttribute：用于多次执行控制器方法间的参数共享。
  - model存入时会存入到request
  - 如果有该注解，里边指定键值对也会存入道session
    - 通过key指定
    - 通过value的type指定
  - modelMap.get()会从request和session中取值

- controller内方法响应返回值：
  - String：`return "success";`,默认是进行转发
    > controller 方法返回字符串可以指定逻辑视图名，通过视图解析器解析为物理视图地址
  - void ：
    - 默认：如果请求路径为`/test`，那么就会去找`test.jsp`或`test.html`文件
    - 使用原生servletapi，进行转发，重定向，响应文本
  - ModalAndView：往域中封装数据以及设置转发路径
  - 关键字：
    > 不会调用视图解析器，要自己制定好路径
    - forward 转发：`return "forward:/folder/success.jsp";`
    - Redirect 重定向：`return:"/index.html"``return "redirect:testReturnModelAndView";`
      > 重定向时也不需要加项目名称，框架会自动加好
    
- 拦截器
  - 在application.properties 或 application.yml指定静态资源，就不会拦截静态资源（或者在）
  - 实现`HandlerInterceptor`接口，写一个拦截器
  - 注册拦截器
    ```java
    @Configuration
    public class WebConfigurer implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // 添加拦截器
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/loginSys").excludePathPatterns("/static/**");
            // 指定拦截路径
        }
        public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 设置静态资源路径，让拦截器不要拦截静态资源，否则，CSS,html等会无法加载
        // 该路径也可以设置多个，组成静态资源链
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    }
    ```