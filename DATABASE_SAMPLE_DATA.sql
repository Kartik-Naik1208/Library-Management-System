-- Sample data for Library Management System

-- Insert Books
INSERT INTO books (isbn, title, author, publisher, publication_year, category, description, total_copies, available_copies, price, is_active) VALUES
('978-0-13-110362-7', 'The C Programming Language', 'Brian W. Kernighan', 'Prentice Hall', 1988, 'Programming', 'Classic programming book', 5, 5, 65.99, true),
('978-0-20161-622-4', 'The Pragmatic Programmer', 'David Thomas', 'Addison-Wesley', 1999, 'Programming', 'Best practices for software development', 3, 3, 49.95, true),
('978-0-134-49418-4', 'Clean Code', 'Robert C. Martin', 'Prentice Hall', 2008, 'Programming', 'A handbook of agile software craftsmanship', 4, 4, 55.00, true),
('978-0-132-35088-2', 'Enterprise Integration Patterns', 'Gregor Hohpe', 'Addison-Wesley', 2003, 'Architecture', 'Designing, building, and deploying messaging solutions', 2, 2, 59.99, true),
('978-0-596-00712-6', 'Learning Perl', 'Randal L. Schwartz', 'O Reilly Media', 2001, 'Programming', 'Introduction to Perl programming', 3, 3, 45.00, true),
('978-1-449-37970-9', 'Python for Data Analysis', 'Wes McKinney', 'O Reilly Media', 2012, 'Data Science', 'Data manipulation with Pandas', 4, 4, 40.00, true),
('978-0-201-61622-4', 'Design Patterns', 'Gang of Four', 'Addison-Wesley', 1994, 'Design', 'Elements of reusable OOP software', 2, 2, 54.99, true),
('978-1-491-92418-8', 'Building Microservices', 'Sam Newman', 'O Reilly Media', 2015, 'Architecture', 'Designing fine-grained systems', 3, 3, 50.00, true);

-- Insert Members
INSERT INTO members (member_id, first_name, last_name, email, phone_number, address, city, state, postal_code, date_of_birth, membership_date, membership_expiry_date, membership_type, max_books_allowed, current_books_issued, is_active) VALUES
('LIB-0001', 'John', 'Doe', 'john.doe@example.com', '9876543210', '123 Main Street', 'New York', 'NY', '10001', '1990-01-15', '2024-01-01', '2025-01-01', 'STANDARD', 5, 0, true),
('LIB-0002', 'Jane', 'Smith', 'jane.smith@example.com', '9876543211', '456 Oak Avenue', 'Los Angeles', 'CA', '90001', '1992-05-20', '2024-02-15', '2026-02-15', 'PREMIUM', 10, 0, true),
('LIB-0003', 'Bob', 'Johnson', 'bob.johnson@example.com', '9876543212', '789 Pine Road', 'Chicago', 'IL', '60601', '1988-11-30', '2024-03-10', '2034-03-10', 'LIFETIME', 15, 0, true),
('LIB-0004', 'Alice', 'Williams', 'alice.williams@example.com', '9876543213', '321 Elm Street', 'Houston', 'TX', '77001', '1995-07-25', '2024-04-01', '2025-04-01', 'STANDARD', 5, 0, true),
('LIB-0005', 'Charlie', 'Brown', 'charlie.brown@example.com', '9876543214', '654 Maple Drive', 'Phoenix', 'AZ', '85001', '1991-09-10', '2024-01-20', '2025-01-20', 'STANDARD', 5, 0, true);
