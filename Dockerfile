# Stage 1: Build the frontend
FROM node:18 AS frontend

# Set the working directory for the frontend
WORKDIR /beautyApp/frontend

# Copy frontend source code and dependencies
COPY /beautyApp/frontend/package.json ./
COPY /beautyApp/frontend/package-lock.json ./

# Copy the rest of the frontend source code
COPY /beautyApp/frontend .

# Install frontend dependencies using npm
RUN npm install

# Build the frontend
RUN npm run build



# Stage 2: Build the backend
FROM maven:3.8.4-openjdk-17-slim AS backend

# Set the working directory for the backend
WORKDIR /beautyApp/backend

# Copy backend source code
COPY /beautyApp/backend .

# Build the backend
RUN mvn package -DskipTests


# Stage 3: Final image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /beautyApp

# # Copy the built frontend from the frontend stage
# COPY --from=frontend /beautyApp/ackendend/target ./frontend/target

# Copy the built backend from the backend stage
COPY --from=backend /beautyApp/backend/target/beautyApp-0.0.1-SNAPSHOT.jar ./beautyApp.jar

# Expose ports
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "beautyApp.jar"]
