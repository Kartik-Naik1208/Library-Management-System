# CONTRIBUTING.md

## How to Contribute

We welcome contributions from the community! Here's how you can help:

## 🐛 Reporting Bugs

1. Check if the bug is already reported
2. Create a new issue with:
   - Clear title
   - Detailed description
   - Steps to reproduce
   - Expected vs actual behavior
   - Screenshots if applicable

## 🌟 Suggesting Features

1. Check if the feature is already requested
2. Create a new issue with:
   - Use case description
   - Expected behavior
   - Benefits
   - Code examples if applicable

## 🔧 Making Changes

### Setup Development Environment

```bash
# 1. Fork the repository
# 2. Clone your fork
git clone https://github.com/YOUR-USERNAME/Library-Management-System.git

# 3. Add upstream remote
git remote add upstream https://github.com/Kartik-Naik1208/Library-Management-System.git

# 4. Create feature branch
git checkout -b feature/your-feature-name
```

### Code Guidelines

1. **Follow Java conventions**
   - Use meaningful variable names
   - Add JavaDoc comments
   - Keep methods focused and small

2. **Maintain code quality**
   - No code duplication
   - Proper error handling
   - Comprehensive logging

3. **Write tests**
   - Unit tests for services
   - Integration tests for endpoints
   - Test edge cases

4. **Document changes**
   - Update README if needed
   - Add comments for complex logic
   - Update API documentation

### Commit Messages

Use clear, descriptive commit messages:

```
fix: Correct calculation for overdue fines
add: New endpoint for book reservations
refactor: Extract common validation logic
docs: Update API documentation
```

### Submitting Pull Request

1. Sync with upstream:
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```

2. Push to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```

3. Create Pull Request with:
   - Clear title and description
   - Link to related issues
   - Explanation of changes
   - Screenshots if applicable

## ✅ Code Review Process

1. Author submits PR
2. Maintainers review code
3. Changes requested (if any)
4. Author updates PR
5. PR approved and merged

## 📋 Checklist Before Submitting

- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] Tests written and passing
- [ ] No breaking changes
- [ ] Commit messages are descriptive

## 🎓 Development Standards

### Java Code Style
- Use 4 spaces for indentation
- Max line length: 120 characters
- Use camelCase for variables/methods
- Use UPPER_CASE for constants

### Naming Conventions
- Classes: PascalCase (e.g., BookService)
- Methods: camelCase (e.g., getBookById)
- Constants: UPPER_SNAKE_CASE (e.g., MAX_BOOKS_ALLOWED)
- Packages: lowercase (e.g., com.librarymanagement)

### Documentation
- JavaDoc for public methods
- Inline comments for complex logic
- README updates for new features
- API endpoint documentation

## 🧪 Testing Requirements

### Unit Tests
```java
@Test
void testGetBookById() {
    // Arrange
    Book book = new Book();
    
    // Act
    BookDTO result = bookService.getBookById(1L);
    
    // Assert
    assertNotNull(result);
}
```

### Test Coverage
- Minimum 70% code coverage
- Critical paths tested
- Edge cases covered
- Error scenarios tested

## 📚 Documentation Requirements

1. Update relevant README sections
2. Add JavaDoc comments
3. Update API documentation
4. Add examples if needed
5. Update change log

## 🚀 Release Process

1. Version bump (semantic versioning)
2. Update CHANGELOG.md
3. Create release notes
4. Tag release in Git
5. Build and publish

## 💬 Community Guidelines

- Be respectful and inclusive
- Provide constructive feedback
- Ask questions if unclear
- Help other contributors
- Report issues professionally

## 📞 Getting Help

- Review existing documentation
- Check closed issues
- Ask in pull request comments
- Contact maintainers

## ✨ Recognition

Contributors will be:
- Credited in CONTRIBUTORS.md
- Mentioned in release notes
- Added to project team

Thank you for contributing! 🙌
