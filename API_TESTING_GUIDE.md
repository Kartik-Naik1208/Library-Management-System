# API Testing Guide

## Using Swagger UI

1. Start the application
2. Open browser: `http://localhost:8080/swagger-ui.html`
3. Expand endpoint sections
4. Click "Try it out"
5. Enter parameters and click "Execute"

## Using cURL

### Books Endpoints

**Create Book:**
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
    "price": 65.99,
    "isActive": true
  }'
```

**Get All Books:**
```bash
curl -X GET http://localhost:8080/api/v1/books
```

**Get Book by ID:**
```bash
curl -X GET http://localhost:8080/api/v1/books/1
```

**Get Available Books:**
```bash
curl -X GET http://localhost:8080/api/v1/books/available/all
```

**Search Books:**
```bash
curl -X GET "http://localhost:8080/api/v1/books/search?searchTerm=Programming"
```

**Update Book:**
```bash
curl -X PUT http://localhost:8080/api/v1/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "isbn": "978-0-13-110362-7",
    "title": "The C Programming Language (Revised)",
    "author": "Brian W. Kernighan",
    "publisher": "Prentice Hall",
    "publicationYear": 1988,
    "category": "Programming",
    "totalCopies": 10,
    "price": 69.99,
    "isActive": true
  }'
```

**Delete Book:**
```bash
curl -X DELETE http://localhost:8080/api/v1/books/1
```

### Members Endpoints

**Create Member:**
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

**Get All Members:**
```bash
curl -X GET http://localhost:8080/api/v1/members
```

**Get Member by ID:**
```bash
curl -X GET http://localhost:8080/api/v1/members/1
```

**Search Members:**
```bash
curl -X GET "http://localhost:8080/api/v1/members/search?searchTerm=John"
```

**Get Members by City:**
```bash
curl -X GET http://localhost:8080/api/v1/members/city/New%20York
```

**Update Member:**
```bash
curl -X PUT http://localhost:8080/api/v1/members/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "phoneNumber": "9876543210",
    "address": "456 Oak Avenue",
    "city": "Los Angeles",
    "state": "CA",
    "postalCode": "90001",
    "dateOfBirth": "1990-01-15",
    "membershipDate": "2024-01-01",
    "membershipExpiryDate": "2025-01-01",
    "membershipType": "PREMIUM"
  }'
```

### Borrow Records Endpoints

**Issue Book:**
```bash
curl -X POST "http://localhost:8080/api/v1/borrow-records/issue?memberId=1&bookId=1"
```

**Return Book:**
```bash
curl -X POST http://localhost:8080/api/v1/borrow-records/1/return
```

**Get All Borrow Records:**
```bash
curl -X GET http://localhost:8080/api/v1/borrow-records
```

**Get Active Borrow Records:**
```bash
curl -X GET http://localhost:8080/api/v1/borrow-records/active/all
```

**Get Overdue Records:**
```bash
curl -X GET http://localhost:8080/api/v1/borrow-records/overdue/all
```

**Get Member's Borrow Records:**
```bash
curl -X GET http://localhost:8080/api/v1/borrow-records/member/1
```

**Get Member's Borrowing History:**
```bash
curl -X GET http://localhost:8080/api/v1/borrow-records/member/1/history
```

## Using Postman

1. Download and install Postman
2. Create new request
3. Select method (GET, POST, PUT, DELETE)
4. Enter URL: `http://localhost:8080/api/v1/...`
5. Add headers: `Content-Type: application/json`
6. Add request body (JSON format)
7. Click Send
8. Review response

## Response Examples

### Success Response (200 OK)
```json
{
  "id": 1,
  "isbn": "978-0-13-110362-7",
  "title": "The C Programming Language",
  "author": "Brian W. Kernighan",
  "publisher": "Prentice Hall",
  "publicationYear": 1988,
  "category": "Programming",
  "totalCopies": 5,
  "availableCopies": 5,
  "price": 65.99,
  "isActive": true
}
```

### Created Response (201 Created)
```json
{
  "id": 1,
  "memberId": "LIB-0001",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "membershipType": "STANDARD",
  "isActive": true
}
```

### Error Response (400 Bad Request)
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email is required",
    "phoneNumber": "Phone number is required"
  },
  "timestamp": "2024-06-29T10:30:00",
  "path": "/api/v1/members"
}
```

### Not Found Response (404 Not Found)
```json
{
  "status": 404,
  "message": "Member not found with ID: 999",
  "timestamp": "2024-06-29T10:30:00",
  "path": "/api/v1/members/999"
}
```

## Test Scenarios

### Scenario 1: Add Book and Search
1. Create a book with ISBN: 978-0-13-110362-7
2. Search for books by author
3. Verify book appears in search results

### Scenario 2: Register Member and Issue Book
1. Register new member
2. Get all available books
3. Issue book to member
4. Verify member's current books count increases

### Scenario 3: Return Book with Fine
1. Issue book to member
2. Wait past due date
3. Return book
4. Verify fine is calculated

### Scenario 4: Search and Filter
1. Get all books
2. Search by title
3. Get books by category
4. Get books by author

## Performance Testing

### Using Apache JMeter
1. Download JMeter
2. Create test plan
3. Add thread group
4. Add HTTP request sampler
5. Configure URL and parameters
6. Add listeners (View Results Tree, Summary Report)
7. Run test

### Load Testing Script
```bash
# Test 100 requests in parallel
for i in {1..100}; do
  curl -X GET http://localhost:8080/api/v1/books &
done
wait
```

## Troubleshooting

### 400 Bad Request
- Check request body format (JSON)
- Verify required fields are present
- Check field data types

### 401 Unauthorized
- Check authentication credentials
- Verify API key if required

### 404 Not Found
- Verify resource ID exists
- Check URL path
- Verify resource hasn't been deleted

### 500 Internal Server Error
- Check application logs
- Verify database connection
- Check for null pointer exceptions
