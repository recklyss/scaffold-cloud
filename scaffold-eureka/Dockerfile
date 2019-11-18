FROM java:8
VOLUME /tmp
ADD scaffold-eureka/target/scaffold-eureka-0.0.1-SNAPSHOT.jar /scaffold_cloud/
WORKDIR /scaffold_cloud
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","scaffold-eureka-0.0.1-SNAPSHOT.jar"]