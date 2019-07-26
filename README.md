## scaffold-cloud项目
> 为Java学习而诞生的spring-cloud脚手架项目

## 项目结构
    scaffold-cloud 项目：
- scaffold-business [业务服务提供者](#)
    - scaffold-business-service [系统业务服务-业务模块](#)
    - scaffold-business-controller [对外提供的restful接口服务模块](#)
- scaffold-business-api [业务API包 用于接口与实现分离](#)
    - scaffold-business-sys-api [系统资源、菜单、权限等API封装](#)
- scaffold-core [工具类以及各种公共代码](#)
    - scaffold-core-common [工具类模块，公共代码](#)
- scaffold-eureka [注册中心Eureka](#)
- scaffold-route [主业务消费者](#)
    - scaffold-route-operate [后台管理接口及页面](#)

## 查看非SpringCloud的[scaffold](https://github.com/Fatezhang/scaffold) 
> SpringBoot脚手架，用于快速开发后台管理及其他简单的系统
