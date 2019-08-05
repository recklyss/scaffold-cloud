## scaffold-cloud项目说明
> 为Java学习而诞生的spring-cloud脚手架项目

**登录页：**
![登录页面](demo.png)

## scaffold-cloud 项目结构：
---
### scaffold-business [业务服务提供者](#) 端口从8850 - 8860
#### scaffold-business-sys-service [系统业务微服务-业务模块](#) 端口 8850
#### scaffold-business-job-service [定时任务微服务-业务模块](#) 端口 8851
#### scaffold-business-thirdparty-service [第三方业务微服务-业务模块](#) 端口 8852
---
### scaffold-business-api [业务API包 用于接口与实现分离](#)
#### scaffold-business-sys-api [系统资源、菜单、权限等API封装](#)
#### scaffold-business-job-api [定时任务API封装](#)
#### scaffold-business-thirdparty-api [第三方服务API封装](#)
---
### scaffold-core [工具类以及各种公共代码](#)
#### scaffold-core-code [每个模块都会用到的公共代码，Bean，config等](#)
#### scaffold-core-common [工具类模块，公共代码](#)
#### scaffold-core-plugin [自动代码生成插件模块](#)
---
### scaffold-eureka [注册中心Eureka](#) 端口 8761
---
### scaffold-feign [Feign模块](#)
#### scaffold-feign-sys [feign-sys模块](#)
#### scaffold-feign-job [feign-job模块](#)
#### scaffold-feign-thirdparty [feign-thirdparty模块](#)
---
### scaffold-route [主业务消费者](#) 端口从8750 - 8760
#### scaffold-route-operate [后台管理接口及页面](#) 端口 8750
#### scaffold-route-app [APP客户端接口](#) 端口 8751
---
## 查看非SpringCloud的[scaffold](https://github.com/Fatezhang/scaffold) 
> SpringBoot脚手架，用于快速开发后台管理及其他简单的系统
