# URL-Shortener-MVP

## 1. Introduction

This is a **URL Shortener MVP** designed to provide a basic URL shortening service using **Java Spring Boot** and **PostgreSQL**. It enables users to shorten URLs and retrieve the original ones via a simple REST API. 

The project is built following **Clean Architecture**, ensuring separation of concerns, scalability, and ease of maintenance.

The main purpose of this MVP is to demonstrate a production-ready API that can be expanded and improved over time. This can be used as the foundation for **Sprint 1**, and further iterations can focus on optimizing performance, adding more features, and refining scalability.

---
## 2. Project Planning and Management

The project was planned and managed using **GitHub Projects** (check the [Project Board](https://github.com/users/GabrielMelhem/projects/1) for details). I used the board to create and manage **tickets** for each feature or task, tracking progress and documenting successful results with screenshots and comments. This helped ensure clear communication and structured development.

Each ticket includes:
- Descriptions of the task or feature
- Comments on the implementation details
- Screenshots of successful outputs
- Any challenges encountered and resolved
---
## 3. Features

The key features of this URL Shortener MVP include:

- **URL Shortening**: Converts URLs into shorter, more manageable ones.
- **Redirect**: Allows users to enter a short URL and be redirected to the original URL.
- **Rate Limiting**: Implements rate limiting to prevent abuse.
- **Caching**: to optimize frequent URL lookups.
- **Error Handling**: Implements graceful error management to ensure a robust user experience.
- **Stability**: Utilizes **HikariCP** connection pooling for optimized database connections and enhanced stability.
- **Customizable for Environments**: Supports both **development** and **production** environments with tailored configurations.
- **Monitoring and Logging**: Includes **metrics checks** and **health checks**, with logging configured using **SLF4J**  for detailed application insights.
- **High Traffic Handling**: Employs **load balancing** with **NGINX** to distribute traffic and manage scalability under heavy loads.
- **Database Optimization**: Improves performance with proper **indexing** strategies to optimize database queries.

This feature set ensures the URL Shortener MVP is scalable, reliable, and production-ready, covering key aspects of performance, stability, and user experience.

---
## 4. Project Structure

The project follows **Clean Architecture**, promoting a modular and decoupled design. The main layers of the architecture include:

- **App Layer**: Application configuration and entry point.
- **Boundary Layer**: Contains the controllers and DTOs for handling HTTP requests and responses.
- **Domain Layer**: Encapsulates business logic and use cases, adhering to clean architecture principles.
- **Store Layer**: Handles database interactions using repositories and entities.

This structure ensures high testability, scalability, and maintainability by keeping the code organized and separating concerns.

---
## 5. Testing: Writing Unit Tests

Unit tests were implemented to validate each layer of the application. The following testing strategies were used:
- **Unit Tests**: For domain logic and service layers, using **JUnit 5**.

Tests are located in the `src/test/java` directory, organized by module and functionality.

### Running Tests

This project uses the **Maven Wrapper** to ensure consistency across different environments. To run the tests, use the following command:

```bash
./mvnw clean test
```
---
## 6. Production-Ready API

### 6.1 Stability: HikariCP
The application uses **HikariCP** for database connection pooling, ensuring high performance and stability in production environments.

### 6.2 Customizable for Environments
Configuration is customized for both **development** and **production** environments using Spring profiles (`application-dev.properties` and `application-prod.properties`).

### 6.3 Monitoring and Logging
Implemented robust **monitoring and logging** strategies:
- **Metrics** and **Health Checks** using Spring Actuator to monitor the system status.
- **SLF4j**  for logging across all layers, with different logging levels for each environment.

### 6.4 Error Handling
Implemented global error handling with custom exceptions, ensuring meaningful error messages and graceful failure scenarios.

---

## 7. Global Scalability

### 7.1 Rate Limiting: Resilience4j
**Resilience4j** was used for rate limiting, preventing excessive traffic from overwhelming the system, ensuring stable performance during traffic spikes.

### 7.2 Database Optimization: Indexing
Database indexing strategies were implemented to optimize query performance and minimize latency.

### 7.3 Caching: Caffeine In-Memory
Integrated **Caffeine** for in-memory caching to speed up frequently accessed URLs and reduce database load.

### 7.4 High Traffic: Load Balancing with NGINX
Configured **NGINX** as a load balancer to distribute traffic efficiently across multiple instances, improving the app’s ability to handle high volumes of traffic.

---
## 8. Dockerization

The project has been fully **Dockerized** to simplify deployment and ensure consistency across environments. **Docker Compose** is used to orchestrate the services, including the **Spring Boot** application and **PostgreSQL** database.

## 9.  Set Up and Run
Prerequisites:
 - Java 21
 - Maven Wrapper
 - Docker and Docker Compose

## 10. Future Improvements
This MVP can be used as a starting point for further improvements in future sprints. Some of the possible enhancements include:

 - Adding a user authentication layer for personalized short URLs.
 - Implementing analytics for tracking the usage of short URLs.
 - Expanding caching mechanisms for better performance.
 - Optimizing the rate-limiting strategy for different user tiers.

Feel free to contribute or raise issues to discuss improvements

