## scaffold-cloud项目说明
> 为Java学习而诞生的spring-cloud脚手架项目

## scaffold-cloud 项目结构：

### scaffold-business [业务服务提供者](#) 
端口从8850 - 8860
#### scaffold-business-service [系统业务微服务-业务模块](#) 端口 8850

### scaffold-business-api [业务API包 用于接口与实现分离](#)
#### scaffold-business-sys-api [系统资源、菜单、权限等API封装](#)

### scaffold-core [工具类以及各种公共代码](#)
#### scaffold-core-common [工具类模块，公共代码](#)

### scaffold-eureka [注册中心Eureka](#) **端口 8761**
### scaffold-route [主业务消费者](#)
端口从8750 - 8760
#### scaffold-route-operate [后台管理接口及页面](#) 端口 8750
operate中的Feign目录本来想单独拆出来作为一个模块，然后让operate引用这个模块的。后来还是考虑不要这么多冗余的模块了。
在route的子模块中，哪个feign用到API的接口，自己创建Feign接口然后继承API接口即可。
#### scaffold-route-app [APP客户端接口](#) 端口 8751

## 查看非SpringCloud的[scaffold](https://github.com/Fatezhang/scaffold) 
> SpringBoot脚手架，用于快速开发后台管理及其他简单的系统
