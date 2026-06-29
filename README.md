# README - Library Management System

## Overview
Library Management System is a comprehensive Spring Boot application designed to manage library operations including book inventory, member management, and book borrowing/returning records.

## Technology Stack
- **Spring Boot 3.1.5** - Framework
- **Spring Data JPA** - ORM and Database Access
- **Hibernate** - Object-Relational Mapping
- **MySQL 8.0** - Relational Database
- **Maven** - Build Tool
- **Lombok** - Code Generation
- **ModelMapper** - DTO Mapping
- **Springdoc OpenAPI** - API Documentation
- **Java 17** - Programming Language

## Project Structure
```
src/main/java/com/librarymanagement/
├── controller/          # REST API endpoints
│   ├── BookController.java
│   ├── MemberController.java
│   └── BorrowRecordController.java
├── service/             # Business logic
│   ├── BookService.java
│   ├── MemberService.java
│   └── BorrowRecordService.java
├── repository/          # Data access layer
│   ├── BookRepository.java
│   ├── MemberRepository.java
│   └── BorrowRecordRepository.java
├── entity/              # JPA entities
│   ├── Book.java
│   ├── Member.java
│   └── BorrowRecord.java
├── dto/                 # Data Transfer Objects
│   ├── BookDTO.java
│   ├── MemberDTO.java
│   └── BorrowRecordDTO.java
├── exception/           # Custom exceptions
│   ├── ResourceNotFoundException.java
│   ├── InvalidOperationException.java
│   ├── GlobalExceptionHandler.java
│   ├── ErrorResponse.java
│   └── ValidationErrorResponse.java
├── config/              # Configuration classes
│   └── SwaggerConfig.java
└── LibraryManagementApplication.java

src/main/resources/
└── application.properties   # Application configuration
```

## Features

### 1. Book Management
- Add new books to the library
- Update book information
- Search books by title, author, ISBN, or category
- View all books and available books
- Deactivate/Delete books
- Track available copies

### 2. Member Management
- Register new library members
- Update member information
- Search members by name or city
- View member details
- Manage membership types (Standard, Premium, Lifetime)
- Deactivate/Delete members
- Track member borrowing limits

### 3. Book Borrowing & Returning
- Issue books to members
- Return books from members
- Track borrowing records
- Automatic fine calculation for overdue books
- View active and completed borrowing records
- Get member's borrowing history
- Track overdue records

## API Endpoints

### Books API (`/api/v1/books`)
- `POST /` - Create a new book
- `GET /` - Get all books
- `GET /{id}` - Get book by ID
- `GET /isbn/{isbn}` - Get book by ISBN
- `GET /active/all` - Get all active books
- `GET /available/all` - Get available books
- `GET /category/{category}` - Get books by category
- `GET /author/{author}` - Get books by author
- `GET /search?searchTerm=` - Search books
- `PUT /{id}` - Update book
- `PUT /{id}/deactivate` - Deactivate book
- `DELETE /{id}` - Delete book

### Members API (`/api/v1/members`)
- `POST /` - Create a new member
- `GET /` - Get all members
- `GET /{id}` - Get member by ID
- `GET /memberId/{memberId}` - Get member by Member ID
- `GET /active/all` - Get all active members
- `GET /membership-type/{type}` - Get members by membership type
- `GET /city/{city}` - Get members by city
- `GET /search?searchTerm=` - Search members
- `PUT /{id}` - Update member
- `PUT /{id}/deactivate` - Deactivate member
- `DELETE /{id}` - Delete member

### Borrow Records API (`/api/v1/borrow-records`)
- `POST /issue?memberId=&bookId=` - Issue book to member
- `POST /{borrowRecordId}/return` - Return book
- `GET /` - Get all borrow records
- `GET /{id}` - Get borrow record by ID
- `GET /active/all` - Get all active borrow records
- `GET /overdue/all` - Get overdue records
- `GET /member/{memberId}` - Get member's borrow records
- `GET /member/{memberId}/active` - Get member's active borrow records
- `GET /member/{memberId}/history` - Get member's borrowing history
- `GET /book/{bookId}` - Get book's borrow records

## Database Setup

### Prerequisites
- MySQL 8.0 or higher installed
- MySQL running on localhost:3306

### Configuration
Update `application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Database Initialization
The application automatically creates tables using Hibernate:
```properties
spring.jpa.hibernate.ddl-auto=update
```

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

### Build and Run
```bash
# Clone the repository
git clone https://github.com/Kartik-Naik1208/Library-Management-System.git

# Navigate to project directory
cd Library-Management-System

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Testing with cURL

### Create a Book
```bash
curl -X POST http://localhost:8080/api/v1/books \
  -H "Content-Type: application/json" \
  -d '{
    "isbn": "978-0-13-110362-7",
    "title": "The C Programming Language",
    "author": "Brian W. Kernighan",
    "publisher": "Prentice Hall",
    "publicationYear": 1988,
    "category": "Programming",
    "description": "Classic programming book",
    "totalCopies": 5,
    "price": 65.99
  }'
```

### Create a Member
```bash
curl -X POST http://localhost:8080/api/v1/members \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "9876543210",
    "address": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001",
    "dateOfBirth": "1990-01-15",
    "membershipDate": "2024-01-01",
    "membershipExpiryDate": "2025-01-01",
    "membershipType": "STANDARD"
  }'
```

### Issue a Book
```bash
curl -X POST "http://localhost:8080/api/v1/borrow-records/issue?memberId=1&bookId=1" \
  -H "Content-Type: application/json"
```

### Return a Book
```bash
curl -X POST http://localhost:8080/api/v1/borrow-records/1/return \
  -H "Content-Type: application/json"
```

## Key Features Implementation

### 1. Spring Boot Fundamentals
- Auto-configuration
- Embedded server (Tomcat)
- Dependency injection
- Bean management

### 2. RESTful Web Services
- REST Controller annotations
- HTTP method mappings (GET, POST, PUT, DELETE)
- Request/Response handling
- Status codes

### 3. Spring Data JPA
- Custom query methods
- @Query annotations
- Named parameters
- Pagination and sorting support

### 4. Hibernate ORM
- Entity mapping
- Relationship mapping (@ManyToOne)
- Cascade operations
- Lazy/Eager loading

### 5. CRUD Operations
- Create - POST endpoints
- Read - GET endpoints
- Update - PUT endpoints
- Delete - DELETE endpoints

### 6. Enterprise Features
- Transaction management (@Transactional)
- Exception handling (Global exception handler)
- Logging (SLF4J)
- DTO pattern
- Service layer pattern
- Repository pattern

## Exception Handling

The application includes comprehensive exception handling:
- `ResourceNotFoundException` - When resource is not found
- `InvalidOperationException` - When invalid operation is attempted
- `MethodArgumentNotValidException` - For validation errors
- Global exception handler for all exceptions

## Future Enhancements
- User authentication and authorization
- Payment integration for fine collection
- Email notifications for overdue books
- Advanced search and filtering
- Book reservations
- Rating and review system
- Bulk operations
- Report generation

## Author
Kartik Naik

## License
Apache License 2.0

## Support
For issues and questions, please open an issue on GitHub.
