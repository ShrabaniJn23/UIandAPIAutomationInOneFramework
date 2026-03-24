# Use Maven with OpenJDK 11
FROM maven:3.8.6-openjdk-11-slim

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Run tests
CMD ["mvn", "test"]