#!/bin/bash -eu
if [ `command -v mvn` ];then
    echo '=======================maven 已经安装======================='
else
    echo 'maven 没有安装 准备安装maven'
    echo -e  "Begin to install maven,Please waiting..."
    wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
    tar zxvf apache-maven-3.6.3-bin.tar.gz
    mv apache-maven-3.6.3 /usr/local/maven
    echo "#MAVEN_HOME" >>/etc/profile
    echo "export MAVEN_HOME=/usr/local/maven" >>/etc/profile
    echo "export PATH=\$PATH:\$MAVEN_HOME/bin" >>/etc/profile
    source /etc/profile
    echo -e "Maven installation completed"
fi
