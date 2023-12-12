#!/bin/bash

SERVER_NAME="${project.artifactId}"
# jar名称
JAR_NAME="${project.build.finalName}.jar"

cd $(dirname "$0") || exit
cd ..

PID=$(ps -ef | grep java | grep "$JAR_NAME" |awk '{print $2}')
if [ -z "$PID" ]; then
  echo "ERROR: The $SERVER_NAME does not started!"
  exit 1
fi
echo -e "Stopping the $SERVER_NAME ...\c"
for PID in $PID ; do
  kill "$PID" > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do
  echo -e ".\c"
  sleep 1
  COUNT=1
  for PID1 in $PID ; do
    PID_EXIST=$(ps -f -p "$PID1" | grep java)
    if [ -n "$PID_EXIST" ]; then
      COUNT=0
      break
    fi
  done
done
echo "OK!"
echo "PID: $PID"
