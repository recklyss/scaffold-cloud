FROM java:8
VOLUME /tmp
ADD scaffold-tx-manager/target/scaffold-txmanager-1.0-SNAPSHOT.jar /scaffold_cloud/
WORKDIR /scaffold_cloud
RUN ["java","-Djava.security.egd=file:/dev/./urandom","-jar","scaffold-txmanager-1.0-SNAPSHOT.jar"]