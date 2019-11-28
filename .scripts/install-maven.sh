#!/bin/bash -eu
if [ `command -v mvn` ];then
    echo 'maven 已经安装'
else
    echo 'maven 没有安装 准备安装maven'

    #得到时间
    TIME_FLAG=`date +%Y%m%d_%H%M%S`
    #备份配置文件
    cp /etc/profile /etc/profile.bak_$TIME_FLAG
    echo -e  "Begin to install maven,Please waiting..."
    #解压maven
    wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
    tar zxvf apache-maven-3.6.3-bin.tar.gz
    mv apache-maven-3.6.3 /usr/local/maven
    #修改maven的环境变量，直接写入配置文件
    echo "#MAVEN_HOME" >>/etc/profile
    echo "export MAVEN_HOME=/usr/local/maven" >>/etc/profile
    echo "export PATH=\$PATH:\$MAVEN_HOME/bin" >>/etc/profile
    #运行后直接生效
    source /etc/profile
    echo -e "Maven installation completed"
fi
