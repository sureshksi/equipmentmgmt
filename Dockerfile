# Use OpenJDK base image
#FROM openjdk:17-jdk-slim  ##deprecated
FROM eclipse-temurin:17-jdk

# Install curl (and clean up afterwards)
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy the built JAR from your local target folder into the container
COPY target/equipmentmgmt-latest.jar lendingservice.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "lendingservice.jar"]
