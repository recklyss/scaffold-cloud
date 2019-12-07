#!/bin/bash -eu
# shellcheck disable=SC2046
if [ $(command -v mvn) ];then
    echo '=======================maven 已经安装======================='
else
    echo -e "Begin to install maven,Please waiting..."
    wget https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
    tar zxvf apache-maven-3.6.3-bin.tar.gz
    chmod 777 /usr/local
    chmod 777 /etc/profile
    cp -r apache-maven-3.6.3 /usr/local/maven
    echo "export MAVEN_HOME=/usr/local/maven" >>/etc/profile
    echo "export PATH=\$PATH:\$MAVEN_HOME/bin" >>/etc/profile
    source /etc/profile
    echo -e "Maven installation completed"
fi
