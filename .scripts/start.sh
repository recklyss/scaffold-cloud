#!/bin/bash

echo "Hello world!!! =======================================================================  Hello nmb~"
#1.启动所有jar程序：sh service.sh start all
#2.停止所有jar程序：sh service.sh stop all
#3.重启所有jar程序：sh service.sh restart all
#4.单独启动、停止、重启某个jar程序：把最后面的all替换为某个jar程序的代码即可
#####################################################Environment Setting#######################################################
##加载环境变量，再部署时jenkins会报 “nohup: failed to run command java: No such file or directory ” 的错误，但是手动启动脚本没问题，加上后问题解决。
#source /etc/profile
##程序代码数组
#APPS=(route-app route-operate)
#
##程序名称数组
#
#NAMES=(app模块 operate模块)
#
##jar包数组
#
#JARS=(loanmarket-route-app-1.0-SNAPSHOT.jar loanmarket-route-operate-1.0-SNAPSHOT.jar)
#
##jar包路径数组
#
#PATHS=(/jianbing/jar/v1 /jianbing/jar/v1)
#
##jar包log
#
#LOGPATHS=(/jianbing/log/loanmarket-route-app /jianbing/log/loanmarket-route-operate)
#
#start() {
#  local APPNAME=
#  local NAME=
#  local CLASSNAME=
#  local PROJECTDIR=
#  local LOGPATH=
#  local command="sh service.sh start"
#  local cmd2="$1"
#  local cmd2ok=0
#  local cnt=0
#  local okcnt=0
#  local today=$(date +%Y-%m-%d)
#  local hour=$(date +%H)
#  #local C_PID="0"
#  #for i in `seq 0 22`
#  echo "---------------------------开始启动服务..."
#  for ((i = 0; i < ${#APPS[@]}; i++)); do
#    APPNAME=${APPS[$i]}
#    NAME=${NAMES[$i]}
#    CLASSNAME=${JARS[$i]}
#    PROJECTDIR=${PATHS[$i]}
#    LOGPATH=${LOGPATHS[$i]}
#    if [ "$cmd2" == "all" ] || [ "$cmd2" == "$APPNAME" ]; then
#      cmd2ok=1
#      C_PID="0"
#      cnt=0
#      #ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#      PID=$(ps -ef | grep $(echo $CLASSNAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}')
#      #do
#      #C_PID=$(ps --no-heading $pid | wc -l)
#      #if [ "$C_PID" == "1" ]; then
#      if [ -n "$PID" ]; then
#        echo "$APPNAME---$NAME:己经运行,PID=$PID"
#        #okcnt=$(($okcnt+1))
#      else
#        cd $PROJECTDIR
#        if [ ! -d $LOGPATH ]; then
#          mkdir $LOGPATH
#        fi
#        rm -f $LOGPATH/nohup.out
#        command="nohup java -Xms1024m -Xmx1536m -XX:MaxPermSize=512m -XX:MaxNewSize=512m -jar -Djava.security.egd=file:/dev/./urandom $CLASSNAME"
#        exec $command >>$LOGPATH/nohup.out &
#        #ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#        #do
#        # C_PID=$(ps --no-heading $pid | wc -l)
#        #done
#        PID=$(ps -ef | grep $(echo $CLASSNAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}')
#        cnt=0
#        #while (("$C_PID" == "0"))
#        while [ -z "$PID" ]; do
#          if (($cnt == 30)); then
#            echo "$APPNAME---$NAME:$cnt秒内未启动，请检查！"
#            break
#          fi
#          cnt=$(($cnt + 1))
#          sleep 1s
#          #ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read pid
#          #do
#          # C_PID=$(ps --no-heading $pid | wc -l)
#          #done
#          PID=$(ps -ef | grep $(echo $CLASSNAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}')
#        done
#        okcnt=$(($okcnt + 1))
#        echo "$APPNAME---$NAME:己经成功启动,PID=$PID"
#
#        if [ -f $LOGPATH/nohup.out ]; then
#          frist=$(sed -n '$=' $LOGPATH/nohup.out)
#        fi
#        if [ -z $frist ]; then
#          frist=1
#        fi
#        #监控项目启动状态
#        while [ -f $LOGPATH/nohup.out ]; do
#          result=$(grep "$today $hour" $LOGPATH/nohup.out | grep "started")
#          if [[ "$result" != "" ]]; then
#            break
#          else
#            secend=$(sed -n '$=' $LOGPATH/nohup.out)
#            sed -n "$frist, ${secend}p" $LOGPATH/nohup.out
#            ((frist = $secend + 1))
#            sleep 3s
#          fi
#        done
#        echo "$APPNAME---$NAME:启动成功"
#      fi
#      #done
#    fi
#  done
#  if (($cmd2ok == 0)); then
#    echo "command2: all|route-app|route-operate"
#  else
#    echo "---------------------------本次启动:$okcnt个服务"
#  fi
#}
#
#stop() {
#  local APPNAME=
#  local CLASSNAME=
#  local PROJECTDIR=
#  local command="sh service.sh stop"
#  local cmd2="$1"
#  local cmd2ok=0
#  #local C_PID="0"
#  local okcnt=0
#  echo "---------------------------开始停止服务..."
#  for ((i = 0; i < ${#APPS[@]}; i++)); do
#    APPNAME=${APPS[$i]}
#    NAME=${NAMES[$i]}
#    CLASSNAME=${JARS[$i]}
#    PROJECTDIR=${PATHS[$i]}
#    if [ "$cmd2" == "all" ] || [ "$cmd2" == "$APPNAME" ]; then
#      cmd2ok=1
#      #ps -ef | grep "$CLASSNAME" | awk '{print $2}' | while read PID
#      PID=$(ps -ef | grep $(echo $CLASSNAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}')
#      #do
#      #C_PID=$(ps --no-heading $PID | wc -l)
#      #if [ "$C_PID" == "1" ]; then
#      if [ -n "$PID" ]; then
#        echo "$NAME:PID=$PID准备结束"
#        kill $PID
#        #C_PID=$(ps --no-heading $PID | wc -l)
#        #while (("$C_PID" == "1"))
#        PID=$(ps -ef | grep $(echo $CLASSNAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}')
#        while [ -n "$PID" ]; do
#          sleep 1s
#          #C_PID=$(ps --no-heading $PID | wc -l)
#          PID=$(ps -ef | grep $(echo $CLASSNAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}')
#        done
#        echo "$NAME:成功结束"
#        okcnt=$(($okcnt + 1))
#      else
#        echo "$NAME:未运行"
#      fi
#      #done
#    fi
#  done
#  if (($cmd2ok == 0)); then
#    echo "command2: all|route-app|route-operate"
#  else
#    echo "---------------------------本次共停止:$okcnt个服务"
#  fi
#}
#
#case "$1" in
#start)
#  start "$2"
#  exit 1
#  ;;
#stop)
#  stop "$2"
#  ;;
#restart)
#  stop "$2"
#  start "$2"
#  ;;
#*)
#  echo "command1: start|stop|restart"
#  exit 1
#  ;;
#esac