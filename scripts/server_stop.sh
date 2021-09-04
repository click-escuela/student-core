#!/bin/bash
chmod +x /home/ec2-user/server/student-core/logs
chmod +x /home/ec2-user/server/student-core/logs/error.log
chmod +x /home/ec2-user/server/student-core/logs/debug.log
var="$(cat /home/ec2-user/server/student-core/student-service.pid)"
sudo kill $var
sudo rm -rf /home/ec2-user/server/student-core/student-service.pid