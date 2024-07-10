# Logging Service

A Spring Boot-based logging service designed to efficiently log and manage application events.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Configuration](#configuration)
- [ToDo](#todo)

## Features

- Centralized logging for applications.
- Support for multiple log levels (INFO, DEBUG, ERROR, etc.).
- REST API for submitting and retrieving logs.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK 17 or higher.
- Maven 3.6.0 or higher.
- Docker (optional, for containerized deployment).

## Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/your-username/logging-service.git
    cd logging-service
    ```

2. **Build the project:**

    ```sh
    mvn clean install
    ```

## Usage

**Run the application:**

    ```sh
    mvn spring-boot:run
    ```

   Or, if you prefer using Docker:

    ```sh
    docker build -t logging-service .
    docker run -p 8080:8080 logging-service
    ```
   The logging service API will be available at `http://localhost:8080`.

## Endpoints

   ### Submit a structured log ###

   ```sh
    curl -X POST http://localhost:8080/logs/structured -H "Content-Type: application/json" -d '{ "timestamp": 1665081600000, "applicationCode": "logging-service", "level": "ERROR", "revision": "abc-123", "thread": "nio-8080-exec-3", "file": "c.e.l.domain.logging.LoggingController", "line": 20, "message": "There was a problem with functionality" }'
   ```
      
   ### Submit an unstructured log (Not yet implemented) ###

   ```sh
      curl -X POST http://localhost:8080/logs -H "Content-Type: application/json" -d '2024-07-10T08:28:45.311Z  INFO 5640 --- [logging] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms'
   ```

   ### Retrieve logs (Not yet implemented) ###

   ```sh
      curl http://localhost:8080/logs
   ```

## Configuration

Configuration options can be set in the `application.yml` file or via environment variables.


## ToDo
   - implement missing endpoints
   - fix github packages permissions to deploy the built image
   - add deployment pipeline to deploy built artifact
   - separate unit tests from integration using profiles to prevent long build times 
   - consider nginx image for container to control tls
   - configure actuator endpoints to improve application observability (currently only health endpoint is available)
   - add swagger to document endpoints
