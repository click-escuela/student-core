#!/usr/bin/env bash
cd /home/ec2-user/server/student-core

sudo rm -rf /home/ec2-user/server/student-core/student-service.pid

echo "eliminando archivo"

sudo java -jar -Dlogging.file.name=/home/ec2-user/server/student-core/debug.log student-core-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null & echo $! > student-service.pid
