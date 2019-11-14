FROM java:8
VOLUME /tmp
ADD target/scaffold-business-sys-service-1.0-SNAPSHOT.jar /scaffold_cloud/scaffold-business-sys-service-1.0-SNAPSHOT.jar
WORKDIR /scaffold_cloud
RUN bash -c 'touch ./scaffold-business-sys-service-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","scaffold-business-sys-service-1.0-SNAPSHOT.jar"]