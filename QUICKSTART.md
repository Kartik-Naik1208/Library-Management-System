# Spring Boot Library Management System

A comprehensive Spring Boot enterprise application demonstrating key concepts including:
- Spring Boot fundamentals
- RESTful web services
- Spring Data JPA with Hibernate ORM
- CRUD operations
- Enterprise application development patterns

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Kartik-Naik1208/Library-Management-System.git
   cd Library-Management-System
   ```

2. **Create MySQL Database**
   ```sql
   CREATE DATABASE library_management_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Configure Database Connection**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_management_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the Application**
   - API Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - API Docs: `http://localhost:8080/v3/api-docs`

## Project Structure

```
Library-Management-System/
├── src/main/java/com/librarymanagement/
│   ├── controller/               # REST Controllers
│   │   ├── BookController
│   │   ├── MemberController
│   │   └── BorrowRecordController
│   ├── service/                 # Business Logic
│   │   ├── BookService
│   │   ├── MemberService
│   │   └── BorrowRecordService
│   ├── repository/              # Data Access Layer
│   │   ├── BookRepository
│   │   ├── MemberRepository
│   │   └── BorrowRecordRepository
│   ├── entity/                  # JPA Entities
│   │   ├── Book
│   │   ├── Member
│   │   └── BorrowRecord
│   ├── dto/                     # DTOs
│   │   ├── BookDTO
│   │   ├── MemberDTO
│   │   └── BorrowRecordDTO
│   ├── exception/               # Exception Handling
│   │   ├── ResourceNotFoundException
│   │   ├── InvalidOperationException
│   │   ├── GlobalExceptionHandler
│   │   ├── ErrorResponse
│   │   └── ValidationErrorResponse
│   ├── config/                  # Configuration
│   │   └── SwaggerConfig
│   └── LibraryManagementApplication
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── README.md
```

## Key Components

### 1. Entities (JPA Models)
- **Book**: Represents library books with ISBN, title, author, availability tracking
- **Member**: Represents library members with membership information
- **BorrowRecord**: Tracks book borrowing and returning with fine calculation

### 2. Repositories (Data Access)
Implemented using Spring Data JPA with custom query methods:
- Find by specific fields
- Search functionality
- Custom @Query annotations
- Named parameters

### 3. Services (Business Logic)
- **BookService**: CRUD operations, search, availability management
- **MemberService**: Member management, borrowing limits
- **BorrowRecordService**: Issue/return books, fine calculation, history tracking

### 4. Controllers (REST Endpoints)
RESTful APIs following best practices:
- Proper HTTP methods (GET, POST, PUT, DELETE)
- Appropriate status codes
- Comprehensive error handling
- OpenAPI/Swagger documentation

## API Documentation

### Books API
```
POST   /api/v1/books                    # Create book
GET    /api/v1/books                    # Get all books
GET    /api/v1/books/{id}               # Get book by ID
GET    /api/v1/books/isbn/{isbn}        # Get by ISBN
GET    /api/v1/books/active/all         # Get active books
GET    /api/v1/books/available/all      # Get available books
GET    /api/v1/books/category/{category}# Get by category
GET    /api/v1/books/author/{author}    # Get by author
GET    /api/v1/books/search             # Search books
PUT    /api/v1/books/{id}               # Update book
PUT    /api/v1/books/{id}/deactivate    # Deactivate book
DELETE /api/v1/books/{id}               # Delete book
```

### Members API
```
POST   /api/v1/members                           # Create member
GET    /api/v1/members                           # Get all members
GET    /api/v1/members/{id}                      # Get member by ID
GET    /api/v1/members/memberId/{memberId}      # Get by member ID
GET    /api/v1/members/active/all                # Get active members
GET    /api/v1/members/membership-type/{type}   # Get by membership type
GET    /api/v1/members/city/{city}               # Get by city
GET    /api/v1/members/search                    # Search members
PUT    /api/v1/members/{id}                      # Update member
PUT    /api/v1/members/{id}/deactivate          # Deactivate member
DELETE /api/v1/members/{id}                      # Delete member
```

### Borrow Records API
```
POST   /api/v1/borrow-records/issue                         # Issue book
POST   /api/v1/borrow-records/{id}/return                   # Return book
GET    /api/v1/borrow-records                               # Get all records
GET    /api/v1/borrow-records/{id}                          # Get by ID
GET    /api/v1/borrow-records/active/all                    # Get active
GET    /api/v1/borrow-records/overdue/all                   # Get overdue
GET    /api/v1/borrow-records/member/{memberId}             # Get by member
GET    /api/v1/borrow-records/member/{memberId}/active      # Get member active
GET    /api/v1/borrow-records/member/{memberId}/history     # Get history
GET    /api/v1/borrow-records/book/{bookId}                 # Get by book
```

## Example Requests

### 1. Create a Book
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
    "availableCopies": 5,
    "price": 65.99,
    "isActive": true
  }'
```

### 2. Register a Member
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

### 3. Issue a Book
```bash
curl -X POST "http://localhost:8080/api/v1/borrow-records/issue?memberId=1&bookId=1"
```

### 4. Return a Book
```bash
curl -X POST http://localhost:8080/api/v1/borrow-records/1/return
```

### 5. Search Books
```bash
curl "http://localhost:8080/api/v1/books/search?searchTerm=Programming"
```

## Technology Highlights

### Spring Boot Features
- Auto-configuration
- Embedded Tomcat server
- Dependency injection with @Autowired
- Component scanning
- Actuator ready

### Spring Data JPA
- Custom repository queries
- @Query with JPQL
- Named parameters
- Query methods (findBy...)
- Pagination and sorting

### Hibernate ORM
- Entity lifecycle management
- @Entity and @Table mapping
- @ManyToOne relationships
- Cascade operations
- Lazy and eager loading

### REST Principles
- Resource-based URLs
- HTTP method semantics (GET, POST, PUT, DELETE)
- Proper HTTP status codes
- Request/response JSON
- Error responses

### Enterprise Patterns
- Service layer pattern
- Repository pattern
- DTO pattern
- Exception handling
- Logging with SLF4J
- Transaction management

## Database Schema

The application uses Hibernate to auto-create tables:

- **books**: Book inventory
- **members**: Library members
- **borrow_records**: Borrowing transactions

## Features

✅ Complete CRUD operations
✅ Advanced search and filtering
✅ Fine calculation for overdue books
✅ Member borrowing limits
✅ Book availability tracking
✅ Comprehensive error handling
✅ Transaction management
✅ Logging throughout
✅ OpenAPI/Swagger documentation
✅ RESTful API design

## Future Enhancements

- Authentication and authorization (Spring Security)
- Role-based access control (Admin, Librarian, Member)
- Email notifications
- Payment integration
- Book reservations
- Rating and review system
- Advanced reporting
- Batch operations
- Caching (Redis)
- API rate limiting

## Configuration Properties

```properties
# Server
server.port=8080
spring.application.name=Library-Management-System

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/library_management_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.root=INFO
logging.level.com.librarymanagement=DEBUG

# OpenAPI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

## Error Handling

The application implements comprehensive error handling:

- **ResourceNotFoundException** (404): When requested resource is not found
- **InvalidOperationException** (400): When operation violates business rules
- **ValidationException** (400): When input validation fails
- **Global Exception Handler**: Catches all uncaught exceptions

## Testing

Use Swagger UI or curl to test all endpoints:
`http://localhost:8080/swagger-ui.html`

## License

Apache License 2.0

## Author

Kartik Naik
- GitHub: [@Kartik-Naik1208](https://github.com/Kartik-Naik1208)
- Email: kartikprakashnaik@gmail.com

## Contributing

Contributions are welcome! Please feel free to submit pull requests.

## Support

If you have any questions or issues, please open an issue on GitHub.
