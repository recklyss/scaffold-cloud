FROM adoptopenjdk/maven-openjdk8 AS builder
COPY . /scaffold_cloud
WORKDIR /scaffold_cloud
RUN mvn clean install -Dmaven.test.skip=true

FROM adoptopenjdk/maven-openjdk8
ARG app_version=DEV
ENV APP_VERSION=$app_version
COPY --from=builder /scaffold_cloud/scripts/start.sh /jars/scripts/
COPY --from=builder /scaffold_cloud/scaffold-eureka/target/scaffold-eureka-0.0.1-SNAPSHOT.jar /jars/scaffold-eureka/
COPY --from=builder /scaffold_cloud/scaffold-tx-manager/target/scaffold-txmanager-1.0-SNAPSHOT.jar /jars/scaffold-tx-namager/
COPY --from=builder /scaffold_cloud/scaffold-business/scaffold-business-sys-service/target/scaffold-business-sys-service-1.0-SNAPSHOT.jar /jars/scaffold-business/
COPY --from=builder /scaffold_cloud/scaffold-business/scaffold-business-job-service/target/scaffold-business-job-service-1.0-SNAPSHOT.jar /jars/scaffold-business/
COPY --from=builder /scaffold_cloud/scaffold-business/scaffold-business-thirdparty-service/target/scaffold-business-thirdparty-service-1.0-SNAPSHOT.jar /jars/scaffold-business/
COPY --from=builder /scaffold_cloud/scaffold-route/scaffold-route-app/target/scaffold-route-app-1.0-SNAPSHOT.jar /jars/scaffold-route/
COPY --from=builder /scaffold_cloud/scaffold-route/scaffold-route-openapi/target/scaffold-route-openapi-1.0-SNAPSHOT.jar /jars/scaffold-route/
COPY --from=builder /scaffold_cloud/scaffold-route/scaffold-route-operate/target/scaffold-route-operate-1.0-SNAPSHOT.jar /jars/scaffold-route/
WORKDIR /jars
ENTRYPOINT ["chmod", "+x", "scripts/start.sh"]
CMD pwd
CMD ["./scripts/start.sh"]