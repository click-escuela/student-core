#!/usr/bin/env bash
cd /home/ec2-user/server/student-core

sudo rm -rf /home/ec2-user/server/student-core/student-service.pid

echo "eliminando archivo"

sudo java -jar -Dspring.profiles.active=prod -Dspring.datasource.url=jdbc:mysql://clickescuela.ccmmeszml0xl.us-east-2.rds.amazonaws.com:3306/clickescuela -Dspring.datasource.username=root -Dspring.datasource.password=secret123 \
   student-core-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null & echo $! > student-service.pid