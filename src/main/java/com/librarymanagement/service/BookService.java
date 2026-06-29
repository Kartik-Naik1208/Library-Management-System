package com.librarymanagement.service;

import com.librarymanagement.dto.BookDTO;
import com.librarymanagement.entity.Book;
import com.librarymanagement.exception.InvalidOperationException;
import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Book operations
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    /**
     * Create a new book
     */
    public BookDTO createBook(BookDTO bookDTO) {
        log.info("Creating new book with ISBN: {}", bookDTO.getIsbn());
        
        if (bookRepository.findByIsbn(bookDTO.getIsbn()).isPresent()) {
            throw new InvalidOperationException("Book with ISBN " + bookDTO.getIsbn() + " already exists");
        }
        
        Book book = modelMapper.map(bookDTO, Book.class);
        book.setAvailableCopies(book.getTotalCopies());
        Book savedBook = bookRepository.save(book);
        
        log.info("Book created successfully with ID: {}", savedBook.getId());
        return modelMapper.map(savedBook, BookDTO.class);
    }

    /**
     * Update an existing book
     */
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        log.info("Updating book with ID: {}", id);
        
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        
        if (!book.getIsbn().equals(bookDTO.getIsbn()) && 
            bookRepository.findByIsbn(bookDTO.getIsbn()).isPresent()) {
            throw new InvalidOperationException("Book with ISBN " + bookDTO.getIsbn() + " already exists");
        }
        
        modelMapper.map(bookDTO, book);
        Book updatedBook = bookRepository.save(book);
        
        log.info("Book updated successfully with ID: {}", id);
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    /**
     * Get book by ID
     */
    public BookDTO getBookById(Long id) {
        log.info("Fetching book with ID: {}", id);
        
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        
        return modelMapper.map(book, BookDTO.class);
    }

    /**
     * Get book by ISBN
     */
    public BookDTO getBookByIsbn(String isbn) {
        log.info("Fetching book with ISBN: {}", isbn);
        
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
        
        return modelMapper.map(book, BookDTO.class);
    }

    /**
     * Get all books
     */
    public List<BookDTO> getAllBooks() {
        log.info("Fetching all books");
        
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all active books
     */
    public List<BookDTO> getAllActiveBooks() {
        log.info("Fetching all active books");
        
        return bookRepository.findByIsActiveTrue().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all available books
     */
    public List<BookDTO> getAvailableBooks() {
        log.info("Fetching all available books");
        
        return bookRepository.findAllAvailableBooks().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get books by category
     */
    public List<BookDTO> getBooksByCategory(String category) {
        log.info("Fetching books by category: {}", category);
        
        return bookRepository.findByCategory(category).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get books by author
     */
    public List<BookDTO> getBooksByAuthor(String author) {
        log.info("Fetching books by author: {}", author);
        
        return bookRepository.findByAuthor(author).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Search books
     */
    public List<BookDTO> searchBooks(String searchTerm) {
        log.info("Searching books with term: {}", searchTerm);
        
        return bookRepository.searchBooks(searchTerm).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Delete book
     */
    public void deleteBook(Long id) {
        log.info("Deleting book with ID: {}", id);
        
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        
        bookRepository.delete(book);
        log.info("Book deleted successfully with ID: {}", id);
    }

    /**
     * Deactivate book
     */
    public BookDTO deactivateBook(Long id) {
        log.info("Deactivating book with ID: {}", id);
        
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        
        book.setIsActive(false);
        Book updatedBook = bookRepository.save(book);
        
        log.info("Book deactivated successfully with ID: {}", id);
        return modelMapper.map(updatedBook, BookDTO.class);
    }

    /**
     * Reduce available copies when book is borrowed
     */
    public void reduceAvailableCopies(Long bookId) {
        log.info("Reducing available copies for book ID: {}", bookId);
        
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        
        if (book.getAvailableCopies() <= 0) {
            throw new InvalidOperationException("No available copies for book: " + book.getTitle());
        }
        
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }

    /**
     * Increase available copies when book is returned
     */
    public void increaseAvailableCopies(Long bookId) {
        log.info("Increasing available copies for book ID: {}", bookId);
        
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        
        if (book.getAvailableCopies() >= book.getTotalCopies()) {
            throw new InvalidOperationException("Cannot exceed total copies for book: " + book.getTitle());
        }
        
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }
}
