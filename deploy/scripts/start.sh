#!/bin/bash

# 项目名称
SERVER_NAME="${project.artifactId}"
# jar名称
JAR_NAME="${project.build.finalName}.jar"

cd $(dirname "$0") || exit
cd ..
DEPLOY_DIR=$(pwd)

CONF_DIR=$DEPLOY_DIR/config
LOGS_DIR=$DEPLOY_DIR/logs
STDOUT_FILE=$LOGS_DIR/stdout.log
JAR_LIB_DIR=lib
CONFIG_FILES=" -Dspring.config.additional-location=$CONF_DIR/ -Djava.ext.dirs=$JAR_LIB_DIR"

PID=$(ps -f | grep java | grep "$JAR_NAME" |awk '{print $2}')
if [ "$1" = "status" ]; then
  if [ -n "$PID" ]; then
    echo "The $SERVER_NAME is running...!"
    echo "PID: $PID"
    exit 0
  else
    echo "The $SERVER_NAME is stopped"
    exit 0
  fi
fi
if [ -n "$PID" ]; then
  echo "ERROR: The $SERVER_NAME already started!"
  echo "PID: $PID"
  exit 1
fi

if [ ! -d "$LOGS_DIR" ]; then
  mkdir "$LOGS_DIR"
fi

JAVA_MEM_OPTS=" -server -Xms128m -Xmx128m
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+PrintTenuringDistribution
-XX:+PrintHeapAtGC
-XX:+PrintReferenceGC
-Xloggc:./logs/gc-%t.log
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=14
-XX:GCLogFileSize=100M
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./logs/heapdump.hprof
"

echo -e "Starting the $SERVER_NAME ..."
nohup java $JAVA_MEM_OPTS $CONFIG_FILES -jar $DEPLOY_DIR/lib/$JAR_NAME > $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do
  echo -e ".\c"
  sleep 1
  COUNT=$(ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l)
  if [ "$COUNT" -gt 0 ]; then
    break
  fi
done
echo "OK!"

PID=$(ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}')
echo "PID: $PID"
echo "STDOUT: $STDOUT_FILE"
