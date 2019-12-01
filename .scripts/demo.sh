#!/bin/bash -eu
if [ `command -v mvn` ];then
    echo '=======================maven 已经安装======================='
else
    echo '=======================maven 没有安装======================='
fi
