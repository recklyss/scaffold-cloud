FROM adoptopenjdk/maven-openjdk8 AS builder
COPY . /scaffold_cloud
WORKDIR /scaffold_cloud
RUN mvn package -Dmaven.test.skip=true

FROM adoptopenjdk/maven-openjdk8
ARG app_version=DEV
ENV APP_VERSION=$app_version
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["/usr/local/bin/shush", "exec", "--"]
CMD ["ps -ef"]