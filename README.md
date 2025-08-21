# Day17proj1 - Bug Management API

A simple Spring Boot REST API for managing bugs with pagination support.

## 📂 Project Structure

```
Directory structure:
└── adrin-bershik-c-j-day17proj1/
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── example/
    │   │   │           └── day14proj1/
    │   │   │               ├── Day14proj1Application.java
    │   │   │               ├── controller/
    │   │   │               │   └── BugController.java
    │   │   │               ├── dto/
    │   │   │               │   └── BugResponseDTO.java
    │   │   │               ├── entity/
    │   │   │               │   └── Bug.java
    │   │   │               ├── mapper/
    │   │   │               │   └── BugMapper.java
    │   │   │               ├── repository/
    │   │   │               │   └── BugRepository.java
    │   │   │               └── service/
    │   │   │                   └── BugService.java
    │   │   └── resources/
    │   │       └── application.properties
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── example/
    │                   └── day14proj1/
    │                       └── Day14proj1ApplicationTests.java
    └── .mvn/
        └── wrapper/
            └── maven-wrapper.properties

```

## 🚀 Features

- Create, Read, Update, and Delete bugs
- Pagination with page numbers starting from 1
- Data Transfer Object (DTO) mapping
- RESTful API design
- JPA & Hibernate for persistence

## 🛠️ Technologies Used

- Java 17+
- Spring Boot 3+
- Spring Data JPA
- Lombok
- MapStruct
- Maven

## ⚙️ Configuration

Edit `src/main/resources/application.properties` to match your database setup:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bugdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Running the project

# Clone the repository
```
git clone https://github.com/Adrin-Bershik-C-J/day17proj1.git

cd day17proj1
```

# Run with Maven
./mvnw spring-boot:run
