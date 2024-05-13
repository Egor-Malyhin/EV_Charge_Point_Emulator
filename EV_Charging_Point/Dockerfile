FROM openjdk:17-jdk-alpine3.14

WORKDIR /app

COPY target/EV_Charging_Point_Emulator-1.0.jar /app/EV_Charging_Point_Emulator-1.0.jar

COPY src/main/resources/application /app/application

CMD ["java", "-jar", "EV_Charging_Point_Emulator-1.0.jar"]