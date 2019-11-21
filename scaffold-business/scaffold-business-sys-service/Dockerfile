FROM java:8
VOLUME /tmp
ADD scaffold-business/scaffold-business-sys-service/target/scaffold-business-sys-service-1.0-SNAPSHOT.jar /scaffold_cloud/scaffold-business-sys-service-1.0-SNAPSHOT.jar
ADD scripts/entrypoint.sh /entrypoint.sh
WORKDIR /scaffold_cloud
RUN ls -l /