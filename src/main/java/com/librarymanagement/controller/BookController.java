package com.librarymanagement.controller;

import com.librarymanagement.dto.BookDTO;
import com.librarymanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Book management endpoints
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Books", description = "Book Management APIs")
public class BookController {

    private final BookService bookService;

    /**
     * Create a new book
     */
    @PostMapping
    @Operation(summary = "Create a new book", description = "Creates a new book in the library")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        log.info("Creating new book");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

    /**
     * Get all books
     */
    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves all books from the library")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        log.info("Fetching all books");
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Get book by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieves a specific book by its ID")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        log.info("Fetching book with ID: {}", id);
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    /**
     * Get book by ISBN
     */
    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Get book by ISBN", description = "Retrieves a book by its ISBN")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        log.info("Fetching book with ISBN: {}", isbn);
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    /**
     * Get all active books
     */
    @GetMapping("/active/all")
    @Operation(summary = "Get all active books", description = "Retrieves all active books")
    public ResponseEntity<List<BookDTO>> getAllActiveBooks() {
        log.info("Fetching all active books");
        return ResponseEntity.ok(bookService.getAllActiveBooks());
    }

    /**
     * Get available books
     */
    @GetMapping("/available/all")
    @Operation(summary = "Get available books", description = "Retrieves all books with available copies")
    public ResponseEntity<List<BookDTO>> getAvailableBooks() {
        log.info("Fetching available books");
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }

    /**
     * Get books by category
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "Get books by category", description = "Retrieves all books in a specific category")
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@PathVariable String category) {
        log.info("Fetching books by category: {}", category);
        return ResponseEntity.ok(bookService.getBooksByCategory(category));
    }

    /**
     * Get books by author
     */
    @GetMapping("/author/{author}")
    @Operation(summary = "Get books by author", description = "Retrieves all books by a specific author")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author) {
        log.info("Fetching books by author: {}", author);
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    /**
     * Search books
     */
    @GetMapping("/search")
    @Operation(summary = "Search books", description = "Searches books by title, author, or ISBN")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String searchTerm) {
        log.info("Searching books with term: {}", searchTerm);
        return ResponseEntity.ok(bookService.searchBooks(searchTerm));
    }

    /**
     * Update book
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update book", description = "Updates an existing book")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        log.info("Updating book with ID: {}", id);
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    /**
     * Deactivate book
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate book", description = "Deactivates a book")
    public ResponseEntity<BookDTO> deactivateBook(@PathVariable Long id) {
        log.info("Deactivating book with ID: {}", id);
        return ResponseEntity.ok(bookService.deactivateBook(id));
    }

    /**
     * Delete book
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Deletes a book from the library")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("Deleting book with ID: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
