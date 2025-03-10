# temp build
FROM docker.io/gradle:8.0.2-jdk AS TEMP_BUILD
ARG SKIP_TESTS=false
# Copy project files
# COPY  --chown=gradle:gradle build.gradle.kts settings.gradle.kts /home/gradle/src/
COPY build.gradle.kts settings.gradle.kts /home/gradle/src/
COPY src /home/gradle/src/src
COPY gradle /home/gradle/src/gradle
COPY configs /home/gradle/src/configs
WORKDIR /home/gradle/src
RUN if [ "$SKIP_TESTS" = "true" ]; then \
    gradle build --no-daemon -x test; \
  else \
    gradle build --no-daemon; \
  fi

# build image
FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8088
COPY --from=TEMP_BUILD /home/gradle/src/configs /app/configs
COPY --from=TEMP_BUILD /home/gradle/src/build/libs/*.jar /app/
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app/in2-wallet-api-0.0.1-SNAPSHOT.jar"]
