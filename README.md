**「ilearning-java学习分享」**

## 🐯 平台简介

**ilearning**，深入学习java相关知识和源码，尽量运用到实际场景，避免八股文。

> 有任何问题，或者想要的功能，可以在 _Issues_ 中提问。
>
> 😜 给项目点点 Star 吧！

学习分享内容
* 1.spring/springboot.



事务传播和Mybatis事务实现。


| 项目名                | 说明                     | 用途                                                                                                                                 |
|--------------------|------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `ilearningCommon`    | Spring Boot 公用模块        | spring常用组件的配置和引入和业务层隔离     |
| `ilearningDependencies`  | Spring Boot 依赖模块       | 统一管理maven依赖包  |
| `ilearningGenerateCode` | 基于数据库表快速生成能一键启动的模块业务代码，springboot常见的分层结构 |
| `ilearningServer` | 启动模块 | 搭建的业务模块 |


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
