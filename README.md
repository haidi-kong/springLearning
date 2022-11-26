**「ilearning-java学习分享」**

## 🐯 平台简介

**ilearning**，深入学习java相关知识和源码，尽量运用到实际场景，避免八股文。

> 有任何问题，或者想要的功能，可以在 _Issues_ 中提问。
>
> 😜 给项目点点 Star 吧！

学习分享内容
* ilearningGenerateCode 基于velocity，读取表结构快速生成springboot开发全套代码，支持自定义模板，代码生成路径，快速开发
* spring事务，引入事务的方式，1.xml切面 2.配置类 3.注解 4.手动事务 理论知识可参考 [博客](https://blog.csdn.net/qq_17236715/article/details/125591467?spm=1001.2014.3001.5501)
* shardingSphere 分库分表实践 1.分库分表配置 2.分库分表不同路由算法(shardingjdbc 分支)[博客](https://blog.csdn.net/qq_17236715/article/details/127680981?spm=1001.2014.3001.5502) 3.分库分表多租户实践(shardingjdbc 分支)[博客](https://blog.csdn.net/qq_17236715/article/details/127717084?spm=1001.2014.3001.5502)
* canal 实践 1.canal docker部署 2.canal消费instance[博客](https://blog.csdn.net/qq_17236715/article/details/127680981?spm=1001.2014.3001.5502) 3.分库分表多租户实践(shardingjdbc 分支)[博客](https://blog.csdn.net/qq_17236715/article/details/127717084?spm=1001.2014.3001.5502)
* canal+rabbimq 实现统一缓存管理和缓存一致性处理 1.方案设计 2.缓存统一管理 3.测试 [博客](https://blog.csdn.net/qq_17236715/article/details/128028366?spm=1001.2014.3001.5502)



| 项目名                | 说明                     | 用途                                                                                                                                 |
|--------------------|------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `ilearningCommon`    | Spring Boot 公用模块        | spring常用组件的配置和引入和业务层隔离     |
| `ilearningDependencies`  | Spring Boot 依赖模块       | 统一管理maven依赖包  |
| `ilearningGenerateCode` | 基于数据库表快速生成能一键启动的模块业务代码，springboot常见的分层结构 |
| `ilearningServer` | 启动模块 | 搭建的业务模块，很多快速业务搭建验证会在这里进行 | 


### 后端

| 框架                                                                                          | 说明               | 版本       | 学习指南                                                           |
|---------------------------------------------------------------------------------------------|------------------|----------|----------------------------------------------------------------|
| [Spring Boot](https://spring.io/projects/spring-boot)                                       | 应用开发框架           | 2.5.12   | [文档](https://github.com/YunaiV/SpringBoot-Labs)                |
| [MySQL](https://www.mysql.com/cn/)                                                          | 数据库服务器           | 5.7      |                                                                |
| [Druid](https://github.com/alibaba/druid)                                                   | JDBC 连接池、监控组件    | 1.2.8    | [文档](http://www.iocoder.cn/Spring-Boot/datasource-pool/?yudao) |
| [MyBatis Plus](https://mp.baomidou.com/)                                                    | MyBatis 增强工具包    | 3.5.1    | [文档](http://www.iocoder.cn/Spring-Boot/MyBatis/?yudao)         |
| [Dynamic Datasource](https://dynamic-datasource.com/)                                       | 动态数据源            | 3.5.0    | [文档](http://www.iocoder.cn/Spring-Boot/datasource-pool/?yudao) |
| [Spring MVC](https://github.com/spring-projects/spring-framework/tree/master/spring-webmvc) | MVC 框架           | 5.3.16   | [文档](http://www.iocoder.cn/SpringMVC/MVC/?yudao)               |
| [Spring Security](https://github.com/spring-projects/spring-security)                       | Spring 安全框架      | 5.5.5    | [文档](http://www.iocoder.cn/Spring-Boot/Spring-Security/?yudao) |
| [Knife4j](https://gitee.com/xiaoym/knife4j)                                                 | Swagger 增强 UI 实现 | 3.0.2    | [文档](http://www.iocoder.cn/Spring-Boot/Swagger/?yudao)         |
| [SkyWalking](https://skywalking.apache.org/)                                                | 分布式应用追踪系统        | 8.5.0    | [文档](http://www.iocoder.cn/Spring-Boot/SkyWalking/?yudao)      |
| [Jackson](https://github.com/FasterXML/jackson)                                             | JSON 工具库         | 2.12.6   |                                                                |
| [MapStruct](https://mapstruct.org/)                                                         | Java Bean 转换     | 1.4.1    | [文档](http://www.iocoder.cn/Spring-Boot/MapStruct/?yudao)       |
| [Lombok](https://projectlombok.org/)                                                        | 消除冗长的 Java 代码    | 1.16.14  | [文档](http://www.iocoder.cn/Spring-Boot/Lombok/?yudao)          |
| [org.apache.shardingsphere](https://github.com/apache/shardingsphere)                       | 分库分表    | 4.0.0-RC1  | [文档](https://github.com/apache/shardingsphere)          |
| [canal](https://github.com/alibaba/canal)                                                   | canal    | 1.5  | [文档](https://github.com/alibaba/canal)          |
| [rabbitmq](https://github.com/rabbitmq/rabbitmq-server)                                     | rabbitmq    | xx  | [文档](https://github.com/rabbitmq/rabbitmq-server )          |
