#!/bin/bash
var="$(cat /home/ec2-user/server/student-core/student-service.pid)"
sudo kill $var
sudo rm -rf /home/ec2-user/server/student-core/student-service.pid