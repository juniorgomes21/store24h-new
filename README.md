# Store24h

Backend application for the Store24h system, developed with **Java and Spring Boot**.

The project provides the backend infrastructure for a system that manages business data and communicates with client applications through a web-based architecture.

## 🚀 Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- JWT
- OAuth2
- Bean Validation
- Thymeleaf
- Spring Boot Actuator
- Maven
- Docker

## 🏗️ Main Features

The backend includes infrastructure for:

- RESTful web services
- Database persistence with JPA/Hibernate
- MySQL database integration
- Authentication and authorization
- JWT-based security
- OAuth2 integration
- Request/data validation
- Application monitoring with Spring Boot Actuator
- Caching support
- Server-side rendering with Thymeleaf

## 📁 Project Structure

The application follows a layered Spring Boot architecture, separating the main responsibilities of the application into dedicated components.

```text
src/
├── main/
│   ├── java/
│   └── resources/
└── test/
```

## ⚙️ Requirements

Before running the application, make sure you have:

- Java 17+
- Maven
- MySQL
- Docker (optional, for running MySQL in a container)

## 🐳 Running MySQL with Docker

You can start a MySQL 8 container with:

```bash
docker run --name mysql \
  -d \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=change-me \
  --restart unless-stopped \
  mysql:8
```

After starting the container, configure the application's database connection according to your local environment.

## ▶️ Running the Application

Clone the repository:

```bash
git clone https://github.com/juniorgomes21/store24h-new.git
cd store24h-new
```

Then run the application using Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Alternatively, the project can be opened and executed directly from an IDE such as IntelliJ IDEA.

## 🧪 Tests

The project includes Spring Boot testing support and a dedicated test source directory.

Run the tests with:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## 📌 Project Status

This repository represents the backend implementation of the Store24h system and is part of a larger application ecosystem, including client-side applications.

## 👨‍💻 Author

Developed by **Junior Gomes**.

[GitHub Profile](https://github.com/juniorgomes21)
