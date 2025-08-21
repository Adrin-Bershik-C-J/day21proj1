# Bug Tracker API

A Spring Boot REST API for managing bug reports with role-based access control and JWT authentication.

## Features

- **JWT Authentication**: Secure token-based authentication
- **Role-based Access Control**: Three user roles (Admin, Developer, User)
- **Bug Management**: Create, read, update, and delete bug reports
- **Search & Filter**: Filter bugs by status, assignee, and project
- **Pagination**: Paginated bug listing with customizable page size
- **API Documentation**: Interactive Swagger UI documentation
- **In-memory Database**: H2 database for development and testing

## Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database operations
- **H2 Database**: In-memory database
- **JWT (JSON Web Tokens)**: Token-based authentication
- **MapStruct**: Entity-DTO mapping
- **Swagger/OpenAPI**: API documentation
- **Lombok**: Boilerplate code reduction
- **Maven**: Dependency management

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Adrin-Bershik-C-J/day21proj1.git
cd day21proj1
```

2. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Access the interactive API documentation at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`

## Authentication

### Default Users

The application comes with three pre-configured users:

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ADMIN |
| user | user123 | USER |
| developer | developer123 | DEVELOPER |

### Login

**POST** `/api/auth`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "jwt": "eyJhbGciOiJIUzI1NiJ9...",
  "userName": "admin"
}
```

### Using JWT Token

Include the JWT token in the Authorization header for all protected endpoints:

```
Authorization: Bearer <your-jwt-token>
```

## API Endpoints

### Authentication
- `POST /api/auth` - User login

### Bug Management

#### Public Access (All Authenticated Users)
- `GET /api/bugs/all` - Get all bugs (paginated)
- `GET /api/bugs/search` - Filter bugs by status, assignee, or project

#### Admin Only
- `POST /api/bugs/admin/create` - Create a new bug
- `DELETE /api/bugs/admin/delete?id={id}` - Delete a bug
- `PUT /api/bugs/admin/update/{id}` - Update a bug (Admin or Developer)

## Sample Data

The application loads sample bug data on startup:

- Login Issue (Project Alpha) - Open
- Crash on Submit (Project Beta) - In Progress
- UI Misalignment (Project Alpha) - Closed
- Payment Gateway Timeout (Project Gamma) - Open
- Profile Picture Not Uploading (Project Beta) - In Progress
- Search Function Slow (Project Delta) - Open
- Notification Not Sending (Project Alpha) - Closed
- Data Export Fails (Project Gamma) - Open
- Dark Mode Styling Issue (Project Delta) - In Progress
- Email Verification Link Broken (Project Beta) - Closed

## Usage Examples

### 1. Login and Get Token
```bash
curl -X POST http://localhost:8080/api/auth \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### 2. Get All Bugs (Paginated)
```bash
curl -X GET "http://localhost:8080/api/bugs/all?page=0&size=5" \
  -H "Authorization: Bearer <your-jwt-token>"
```

### 3. Filter Bugs
```bash
curl -X GET "http://localhost:8080/api/bugs/search?status=Open&project=Project Alpha" \
  -H "Authorization: Bearer <your-jwt-token>"
```

### 4. Create Bug (Admin Only)
```bash
curl -X POST http://localhost:8080/api/bugs/admin/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-jwt-token>" \
  -d '{
    "title": "New Bug Report",
    "status": "Open",
    "assignee": "John Doe",
    "project": "Project Alpha"
  }'
```

### 5. Update Bug (Admin/Developer Only)
```bash
curl -X PUT http://localhost:8080/api/bugs/admin/update/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-jwt-token>" \
  -d '{
    "title": "Updated Bug Title",
    "status": "In Progress",
    "assignee": "Jane Smith",
    "project": "Project Beta"
  }'
```

## Role-based Access Control

| Endpoint | Admin | Developer | User |
|----------|-------|-----------|------|
| Login | ✅ | ✅ | ✅ |
| View bugs | ✅ | ✅ | ✅ |
| Filter/search bugs | ✅ | ✅ | ✅ |
| Create bugs | ✅ | ❌ | ❌ |
| Update bugs | ✅ | ✅ | ❌ |
| Delete bugs | ✅ | ❌ | ❌ |

## Project Structure

```
src/main/java/com/example/day14proj1/
├── config/             # Configuration classes
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
├── controller/         # REST controllers
│   ├── AuthController.java
│   └── BugController.java
├── dto/               # Data Transfer Objects
│   ├── BugResponseDTO.java
│   ├── UserRequestDTO.java
│   └── UserResponseDTO.java
├── entity/            # JPA entities
│   ├── Bug.java
│   └── User.java
├── mapper/            # MapStruct mappers
│   ├── BugMapper.java
│   └── UserMapper.java
├── repository/        # Data repositories
│   ├── BugRepository.java
│   └── UserRepository.java
├── service/           # Business logic
│   ├── BugService.java
│   ├── JwtAuthFilter.java
│   ├── JwtService.java
│   └── UserService.java
└── Day14proj1Application.java
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
