#!/usr/bin/env sh

echo "=== Building the application with Maven ==="
mvn clean package -DskipTests

echo "=== Starting the Spring Boot application on port 8080 ==="
java -jar target/*.jar
