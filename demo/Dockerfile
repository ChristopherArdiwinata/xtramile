# Use an OpenJDK 17 base image (adjust if using a different Java version)
FROM openjdk:17-jdk-slim

# Create a volume for temporary files (optional)
VOLUME /tmp

# Copy the built jar from the target folder to the image
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
