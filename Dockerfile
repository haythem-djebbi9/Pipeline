# Dockerfile
FROM eclipse-temurin:17-jre-jammy-slim

# Metadata (optionnel)
LABEL maintainer="ton-nom <ton.email@example.com>"

# Copy the jar produced by Maven into the image
# Si ton jar est dans target/*.jar :
COPY target/*.jar /app/app.jar

# Expose port (optionnel)
EXPOSE 8089

# Run the jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
