# # Use an official Maven image as the build stage
# FROM maven:3.8.4-openjdk-11 AS build

# # Set the working directory
# WORKDIR /app

# # Copy the pom.xml and other build files
# COPY pom.xml .

# # Download dependencies
# RUN mvn dependency:go-offline

# # Copy the source code
# COPY src src

# # Build the application
# RUN mvn clean package -DskipTests

# Use an official OpenJDK image as the base image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the pre-built JAR file from the workflow
# Assuming the workflow exports the JAR file to the build artifacts or a specified directory
COPY /target/beautyApp-0.0.1-SNAPSHOT.jar /app/beautyApp.jar

# Expose the port the application will run on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "beautyApp.jar"]

