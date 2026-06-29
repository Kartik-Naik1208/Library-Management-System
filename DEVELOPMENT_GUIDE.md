# Development Guide

## Project Setup for Development

### 1. Prerequisites
- Java 17 or higher (JDK)
- Maven 3.6 or higher
- MySQL 8.0 or higher
- Git
- IDE (IntelliJ IDEA or Eclipse recommended)

### 2. Clone and Setup

```bash
# Clone repository
git clone https://github.com/Kartik-Naik1208/Library-Management-System.git
cd Library-Management-System

# Build project
mvn clean install

# Run tests
mvn test

# Run application
mvn spring-boot:run
```

### 3. IDE Setup (IntelliJ IDEA)

1. Open project: `File > Open` and select project directory
2. Configure SDK: `Project Structure > Project > SDK > 17`
3. Enable Annotation Processing: `Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing`
4. Run configuration: Click on Run menu and select Run Configurations

### 4. IDE Setup (Eclipse)

1. Import project: `File > Import > Existing Maven Projects`
2. Select project directory
3. Configure JDK: `Project > Properties > Java Build Path > Libraries`
4. Update Maven: Right-click project > Maven > Update Project

## Code Structure

### Entity Layer
- Located in `com.librarymanagement.entity`
- Uses JPA annotations (@Entity, @Table, @Column)
- Includes relationships (@ManyToOne)
- Implements Lombok for boilerplate code

### Repository Layer
- Located in `com.librarymanagement.repository`
- Extends JpaRepository
- Implements custom query methods
- Uses @Query for complex queries

### Service Layer
- Located in `com.librarymanagement.service`
- Contains business logic
- Handles transactions with @Transactional
- Uses dependency injection

### Controller Layer
- Located in `com.librarymanagement.controller`
- REST endpoints with @RestController
- Proper HTTP methods and status codes
- OpenAPI documentation with @Operation

## Best Practices

### 1. Code Style
- Follow Java naming conventions
- Use meaningful variable names
- Keep methods small and focused
- Add JavaDoc comments for public methods

### 2. Database
- Use transactions appropriately
- Implement proper cascade operations
- Use lazy loading for large relationships
- Index frequently queried columns

### 3. API Design
- Use proper HTTP methods
- Return appropriate status codes
- Implement error handling
- Version your APIs

### 4. Testing
- Write unit tests for services
- Use test databases (H2)
- Mock external dependencies
- Test edge cases

## Common Tasks

### Add a New Entity

1. Create entity class in `entity` package:
```java
@Entity
@Table(name = "table_name")
@Data
@NoArgsConstructor
public class NewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // fields
}
```

2. Create repository in `repository` package:
```java
@Repository
public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {
    // custom methods
}
```

3. Create service in `service` package:
```java
@Service
@Transactional
@RequiredArgsConstructor
public class NewEntityService {
    private final NewEntityRepository repository;
    // business logic
}
```

4. Create controller in `controller` package:
```java
@RestController
@RequestMapping("/api/v1/new-entities")
@RequiredArgsConstructor
public class NewEntityController {
    private final NewEntityService service;
    // endpoints
}
```

### Add a Custom Query

In repository:
```java
@Query("SELECT e FROM Entity e WHERE e.field = :value")
List<Entity> findByCustomField(@Param("value") String value);
```

### Add Exception Handling

Create custom exception:
```java
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
```

Handle in GlobalExceptionHandler:
```java
@ExceptionHandler(CustomException.class)
public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(ex.getMessage()));
}
```

## Debugging

### Enable Debug Logging

In application.properties:
```properties
logging.level.com.librarymanagement=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

### Debug in IDE

1. Set breakpoints
2. Run in Debug mode (Shift+F9 in IntelliJ)
3. Use Debug Console to inspect variables
4. Step through code (F10 = step over, F11 = step into)

### View Generated SQL

In application.properties:
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Performance Tips

1. **Use Pagination**
```java
Repository.findAll(PageRequest.of(0, 10));
```

2. **Lazy Load Relationships**
```java
@ManyToOne(fetch = FetchType.LAZY)
private Entity entity;
```

3. **Use Query Projections**
```java
@Query("SELECT new dto(e.id, e.name) FROM Entity e")
List<EntityDTO> findProjected();
```

4. **Index Frequently Searched Columns**
```java
@Column(name = "email")
@Index(name = "idx_email")
private String email;
```

## Deployment

### Build JAR
```bash
mvn clean package
```

### Run JAR
```bash
java -jar target/library-management-system-1.0.0.jar
```

### Docker

Create Dockerfile:
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/library-management-system-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build and run:
```bash
docker build -t library-mgmt .
docker run -p 8080:8080 library-mgmt
```

## Troubleshooting

### Build Fails
- Clean cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`
- Check Java version: `java -version`

### Application Won't Start
- Check database connection
- Verify MySQL is running
- Check port 8080 is available
- Review logs in console

### Slow Queries
- Enable query logging
- Check for N+1 queries
- Add database indexes
- Use pagination

## Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/)
- [Springdoc OpenAPI](https://springdoc.org/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
