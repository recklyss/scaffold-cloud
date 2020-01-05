# scaffold-cloud

> A scaffold multi-module project created by Spring Cloud

[中文](./docs/README.md)

<p align="center">
  <a href="http://zhangjiaheng.cn"><img alt="author" src="https://img.shields.io/badge/作者-ZhangJiaheng_Blog-blue.svg"/></a>
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8+-orange.svg"/></a>
  <a href="https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-2.1.4.RELEASE-brightgreen.svg"/></a>
  <a href="https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/"><img alt="Spring Cloud" src="https://img.shields.io/badge/Spring Cloud-Greenwich.SR1-black.svg"/></a>
  <a href="https://github.com/Fatezhang/scaffold-cloud/blob/master/LICENSE"><img alt="LICENSE" src="https://img.shields.io/github/license/Fatezhang/scaffold-cloud.svg"/></a>  
  <a href="https://gitter.im/scaffold-cloud/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge"><img alt="Gitter" src="https://badges.gitter.im/scaffold-cloud/community.svg"/></a>  
</p>


<p align="center">
  <a href="https://github.com/Fatezhang/scaffold-cloud/stargazers"><img alt="star" src="https://img.shields.io/github/stars/Fatezhang/scaffold-cloud.svg?label=Stars&style=social"/></a>
  <a href="https://github.com/Fatezhang/scaffold-cloud/network/members"><img alt="star" src="https://img.shields.io/github/forks/Fatezhang/scaffold-cloud.svg?label=Fork&style=social"/></a>
  <a href="https://github.com/Fatezhang/scaffold-cloud/watchers"><img alt="star" src="https://img.shields.io/github/watchers/Fatezhang/scaffold-cloud.svg?label=Watch&style=social"/></a>
  <a href='https://gitee.com/chennianfenglou/scaffold-cloud/stargazers'><img alt='fork' src='https://gitee.com/chennianfenglou/scaffold-cloud/badge/star.svg?theme=white'/></a>
</p>

---

## Introduction:notebook_with_decorative_cover:

With the development of the micro services, there are a lot of frameworks we should study. But when we create a project it always the same to integrate the refs of all the dependencies. Mostly, what we want is to use them but to do the duplicate things. So I create a scaffold of Spring Cloud to use and study Spring Cloud frameworks for coders. And you can use it develop your projects fast.

## For Who:information_desk_person:

It’s a scaffold project for everyone who want to create a Spring Cloud stuff quickly.

## Quick Start:new:

### Foreplay:previous_track_button:

Because this is a Java project, if you have not developed java projects or some other relevant experience  you may need start from [RUNOOB](https://www.runoob.com/java/java-tutorial.html).

And then you should learn about **creating a java web project**.

Relevantly, mysql, Redis, docker, maven, etc. Learn more if you can. 

### Now Start It:beginner:

#### Start locally:house:

1. Install MySQL locally and remember your username and password, also port(default 3306)

2. Install Redis, same note as last step

3. Install maven, if you are in China, use Alibaba’s mirror will be faster: 

   ```xml
   <mirror>
     <id>alimaven</id>
     <name>aliyun maven</name>
     <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
     <mirrorOf>central</mirrorOf>        
   </mirror>
   ```

   

4. Clone from git `git@github.com:Fatezhang/scaffold-cloud.git`

5. Make sure the configuration in your `application-local.properties` are correct.

6. Start scaffold-eureka, for automically registration/discovery

7. Start scaffold-tx-manager, for distributed transaction

8. Start scaffold-business-sys-service, this is a basic module, others depends on it

9. Start scaffold-operate 后台管理服务

10. 访问 http://localhost:8750 即可访问后台管理

#### Start in docker:nut_and_bolt:

1. 安装 docker，参考[安装教程](https://docs.docker.com/install/)
2. 进入项目所在目录
3. 执行 `./.scripts/recreate-docker-image.sh` 创建 docker 镜像
4. 执行 `./.scripts/start-docker-service.sh` 即使用 docker-compose 启动
5. 访问 http://localhost:8750 即可访问后台管理




















