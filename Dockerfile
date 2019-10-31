FROM java:8
#将本地文件夹挂载到当前容器
VOLUME /tmp
MAINTAINER Fatezhang
#拷贝文件到容器，注意这里的jar包是事先准备好的一个演示Cloud的jar项目，需要放在Dockerfile同样的目录下
ADD /scaffold-eureka/target/scaffold-eureka-0.0.1-SNAPSHOT.jar eureka.jar
RUN ["/bin/bash","-c","touch /eureka.jar"]
#指定JAVA 环境变量
ENV JAVA_HOME /jdk/jre
ENV PATH $PATH:$JAVA_HOME/bin
ENV CLASSPATH .:$JAVA_HOME/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#开放8761端口
EXPOSE 8761
#配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","/eureka.jar"]