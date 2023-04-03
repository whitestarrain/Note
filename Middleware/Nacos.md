TODO: Nacos 动态服务发现中间件

> 动态服务发现、服务配置、服务元数据及流量管理。

```
Eureka（原生，2.0遇到瓶颈，停止维护）
Zookeeper（支持，专业的独立产品。例如：dubbo）
Consul（原生，GO语言开发）
Nacos
  相对于 Spring Cloud Eureka 来说，Nacos 更强大。
  Nacos = Spring Cloud Eureka + Spring Cloud Config
  Nacos 可以与 Spring, Spring Boot, Spring Cloud 集成，并能代替 Spring Cloud Eureka, Spring Cloud Config。
  通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-config 实现配置的动态变更。
  通过 Nacos Server 和 spring-cloud-starter-alibaba-nacos-discovery 实现服务的注册与发现。
```

# 参考资料

- [Nacos简介](https://blog.csdn.net/qq_51726114/article/details/123115068)
