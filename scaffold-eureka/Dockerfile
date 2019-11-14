FROM java:8
VOLUME /tmp
ADD target/scaffold-eureka-0.0.1-SNAPSHOT.jar /scaffold_cloud/scaffold-eureka-0.0.1-SNAPSHOT.jar
WORKDIR /scaffold_cloud
RUN bash -c 'touch ./scaffold-eureka-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","scaffold-eureka-0.0.1-SNAPSHOT.jar"]