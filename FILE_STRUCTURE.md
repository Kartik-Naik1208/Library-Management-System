# File Structure and Organization

## Directory Layout

```
Library-Management-System/
│
├── src/
│   ├── main/
│   │   ├── java/com/librarymanagement/
│   │   │   ├── controller/
│   │   │   │   ├── BookController.java (REST endpoints for books)
│   │   │   │   ├── MemberController.java (REST endpoints for members)
│   │   │   │   └── BorrowRecordController.java (REST endpoints for borrowing)
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── BookService.java (Business logic for books)
│   │   │   │   ├── MemberService.java (Business logic for members)
│   │   │   │   └── BorrowRecordService.java (Business logic for borrowing)
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── BookRepository.java (Data access for books)
│   │   │   │   ├── MemberRepository.java (Data access for members)
│   │   │   │   └── BorrowRecordRepository.java (Data access for borrowing)
│   │   │   │
│   │   │   ├── entity/
│   │   │   │   ├── Book.java (Book JPA entity)
│   │   │   │   ├── Member.java (Member JPA entity)
│   │   │   │   └── BorrowRecord.java (BorrowRecord JPA entity)
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── BookDTO.java (Book data transfer object)
│   │   │   │   ├── MemberDTO.java (Member data transfer object)
│   │   │   │   └── BorrowRecordDTO.java (BorrowRecord data transfer object)
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java (Custom exception)
│   │   │   │   ├── InvalidOperationException.java (Custom exception)
│   │   │   │   ├── GlobalExceptionHandler.java (Global exception handler)
│   │   │   │   ├── ErrorResponse.java (Error response DTO)
│   │   │   │   └── ValidationErrorResponse.java (Validation error response DTO)
│   │   │   │
│   │   │   ├── config/
│   │   │   │   └── SwaggerConfig.java (Swagger/OpenAPI configuration)
│   │   │   │
│   │   │   └── LibraryManagementApplication.java (Spring Boot main class)
│   │   │
│   │   └── resources/
│   │       └── application.properties (Application configuration)
│   │
│   └── test/
│       └── java/com/librarymanagement/ (Test files)
│
├── Documentation Files
│   ├── README.md (Main documentation)
│   ├── QUICKSTART.md (Quick setup guide)
│   ├── DEVELOPMENT_GUIDE.md (Development practices)
│   ├── API_TESTING_GUIDE.md (API testing guide)
│   ├── DATABASE_SETUP.md (Database configuration)
│   ├── DATABASE_SAMPLE_DATA.sql (Sample data SQL)
│   ├── PROJECT_SUMMARY.md (Project summary)
│   ├── CONTRIBUTING.md (Contribution guidelines)
│   └── FILE_STRUCTURE.md (This file)
│
├── Configuration Files
│   ├── pom.xml (Maven configuration)
│   └── .gitignore (Git ignore rules)
│
└── Root Directory
    └── .git/ (Git repository)
```

## File Descriptions

### Controllers (REST Endpoints)

**BookController.java**
- Purpose: REST endpoints for book management
- Endpoints: 13 endpoints for CRUD and search operations
- Methods: createBook, getAll, getById, search, update, delete, etc.

**MemberController.java**
- Purpose: REST endpoints for member management
- Endpoints: 11 endpoints for member operations
- Methods: createMember, getAll, getById, search, update, delete, etc.

**BorrowRecordController.java**
- Purpose: REST endpoints for borrowing management
- Endpoints: 10 endpoints for book borrowing/returning
- Methods: issueBook, returnBook, getRecords, getHistory, etc.

### Services (Business Logic)

**BookService.java**
- CRUD operations for books
- Search and filter functionality
- Availability tracking
- ~160 lines of code

**MemberService.java**
- Member registration and management
- Membership validation
- Borrowing limit enforcement
- ~140 lines of code

**BorrowRecordService.java**
- Issue and return operations
- Fine calculation logic
- History tracking
- ~180 lines of code

### Repositories (Data Access)

**BookRepository.java**
- Spring Data JPA repository
- 12+ custom query methods
- Search by title, author, ISBN, category
- Pagination and filtering

**MemberRepository.java**
- Spring Data JPA repository
- 11+ custom query methods
- Search by name, city, membership type
- Expiration tracking

**BorrowRecordRepository.java**
- Spring Data JPA repository
- 10+ custom query methods
- Overdue tracking
- History queries

### Entities (JPA Models)

**Book.java**
- Database table: books
- Fields: isbn, title, author, publisher, category, etc.
- Relationships: One-to-many with BorrowRecord

**Member.java**
- Database table: members
- Fields: firstName, lastName, email, membership details, etc.
- Relationships: One-to-many with BorrowRecord

**BorrowRecord.java**
- Database table: borrow_records
- Fields: borrowDate, dueDate, returnDate, fineAmount, status
- Relationships: Many-to-one with Book and Member

### DTOs (Data Transfer Objects)

**BookDTO.java**
- Used for REST request/response
- Excludes sensitive database fields
- Simple POJO with getters/setters

**MemberDTO.java**
- Used for REST request/response
- Excludes sensitive fields
- Maps to Member entity

**BorrowRecordDTO.java**
- Used for REST request/response
- Includes computed fields (memberName, bookTitle)
- Maps to BorrowRecord entity

### Exception Handling

**ResourceNotFoundException.java**
- Extends RuntimeException
- Thrown when resource not found (404)

**InvalidOperationException.java**
- Extends RuntimeException
- Thrown for business logic violations (400)

**GlobalExceptionHandler.java**
- @RestControllerAdvice annotated
- Handles all application exceptions
- Returns formatted error responses

**ErrorResponse.java**
- Standard error response DTO
- Contains status, message, timestamp, path

**ValidationErrorResponse.java**
- Validation error response DTO
- Contains field-level error messages

### Configuration

**SwaggerConfig.java**
- OpenAPI/Swagger configuration
- API documentation
- Contact and license information

**application.properties**
- Server configuration (port: 8080)
- Database connection settings
- JPA/Hibernate settings
- Logging configuration
- Swagger configuration

## Organization Principles

### 1. Layered Architecture
- Controller → Service → Repository → Database
- Clear separation of concerns
- Easy to test each layer independently

### 2. Single Responsibility Principle
- Each class has one reason to change
- Controllers handle HTTP
- Services handle business logic
- Repositories handle data access

### 3. DRY (Don't Repeat Yourself)
- Shared logic extracted to services
- Common exception handling centralized
- Reusable DTOs

### 4. Package Organization
- Organized by feature (vertical slicing)
- Alternative: Organized by layer (horizontal slicing)
- Current: Organized by layer for clarity

## Code Metrics

| Metric | Count |
|--------|-------|
| Java Classes | 22 |
| REST Endpoints | 34+ |
| Service Methods | 50+ |
| Repository Methods | 30+ |
| Database Entities | 3 |
| DTOs | 3 |
| Exception Classes | 5 |
| Configuration Classes | 1 |
| Total Lines of Code | 3500+ |

## Dependencies

### Core Spring Dependencies
- spring-boot-starter-web
- spring-boot-starter-data-jpa

### Database
- mysql-connector-java
- h2database (for testing)

### Development Tools
- lombok
- modelmapper

### Documentation
- springdoc-openapi-starter-webmvc-ui

### Testing
- spring-boot-starter-test
- junit-jupiter

## How to Navigate the Code

### To Understand REST APIs
1. Start with Controllers
2. Then check Services for business logic
3. Review Repositories for queries
4. Check DTOs for request/response structure

### To Add a New Feature
1. Create Entity in entity/
2. Create Repository in repository/
3. Create Service in service/
4. Create Controller in controller/
5. Create DTO in dto/
6. Add exception handling if needed
7. Update documentation

### To Fix a Bug
1. Identify which layer (Controller/Service/Repository)
2. Review relevant exception handling
3. Check test cases
4. Fix and test
5. Update documentation

## Best Practices Implemented

✅ Separation of concerns  
✅ Single responsibility principle  
✅ DRY principle  
✅ Error handling and logging  
✅ Transaction management  
✅ DTOs for API boundaries  
✅ Custom exceptions  
✅ Consistent naming conventions  
✅ Comprehensive documentation  
✅ Layered architecture  

---

For more information, see the README.md and other documentation files.
