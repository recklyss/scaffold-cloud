#!/bin/bash -eu
if [ `command -v mvn` ];then
    echo '=======================maven 已经安装======================='
else
    echo -e "Begin to install maven,Please waiting..."
    yum -y install expect
    set timeout 2
    spawn su
    expect "Password:"
    exec sleep 1
    send "emhhbmc=1ZJH\r"
    expect "#"
    interact
    wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
    tar zxvf apache-maven-3.6.3-bin.tar.gz
    cp -r apache-maven-3.6.3 /usr/local/maven
    echo "export MAVEN_HOME=/usr/local/maven" >>/etc/profile
    echo "export PATH=\$PATH:\$MAVEN_HOME/bin" >>/etc/profile
    source /etc/profile
    echo -e "Maven installation completed"
fi
