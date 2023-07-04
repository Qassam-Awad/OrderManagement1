# Use a base image with JDK 17 pre-installed
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled application JAR file to the container
COPY target/OrderManagement1-0.0.1-SNAPSHOT.jar /app/OrderManagement1.jar

ENTRYPOINT ["java", "-jar", "OrderManagement1.jar"]
