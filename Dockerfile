FROM amazoncorretto:17-alpine-jdk as build

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN sh gradlew bootJar

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /build/ /build/

ENTRYPOINT ["java","-jar","/build/libs/alpha-0.0.1-SNAPSHOT.jar"]
