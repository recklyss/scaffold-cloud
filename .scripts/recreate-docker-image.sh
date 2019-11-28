#!/usr/bin/bash -eu

echo "============start to package with maven and recreate docker image=============="
cd ..

SERVICE_FOLDERS=( scaffold-eureka scaffold-zuul )
path=
for (( i = 0; i < ${#SERVICE_FOLDERS[@]}; i++ )); do
    path=${SERVICE_FOLDERS[${i}]}
    echo "进入目录 >>>> cd ${path}"
    cd "${path}"
    pwd
    mvn clean package docker:build -Ptest
    cd -
done
echo "============                      create end                     =============="