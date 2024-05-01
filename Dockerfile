FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/flowers-0.0.1.jar flowers.jar
ENTRYPOINT ["java", "-jar", "flowers.jar"]