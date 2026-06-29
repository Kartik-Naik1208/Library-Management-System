package com.librarymanagement.controller;

import com.librarymanagement.dto.BorrowRecordDTO;
import com.librarymanagement.service.BorrowRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for BorrowRecord management endpoints
 */
@RestController
@RequestMapping("/api/v1/borrow-records")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Borrow Records", description = "Book Borrowing Management APIs")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    /**
     * Issue book to member
     */
    @PostMapping("/issue")
    @Operation(summary = "Issue book to member", description = "Issues a book to a library member")
    public ResponseEntity<BorrowRecordDTO> issueBook(
            @RequestParam Long memberId,
            @RequestParam Long bookId) {
        log.info("Issuing book ID: {} to member ID: {}", bookId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(borrowRecordService.issueBook(memberId, bookId));
    }

    /**
     * Return book from member
     */
    @PostMapping("/{borrowRecordId}/return")
    @Operation(summary = "Return book", description = "Records the return of a book by a member")
    public ResponseEntity<BorrowRecordDTO> returnBook(@PathVariable Long borrowRecordId) {
        log.info("Returning book for borrow record ID: {}", borrowRecordId);
        return ResponseEntity.ok(borrowRecordService.returnBook(borrowRecordId));
    }

    /**
     * Get borrow record by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get borrow record by ID", description = "Retrieves a specific borrow record")
    public ResponseEntity<BorrowRecordDTO> getBorrowRecordById(@PathVariable Long id) {
        log.info("Fetching borrow record with ID: {}", id);
        return ResponseEntity.ok(borrowRecordService.getBorrowRecordById(id));
    }

    /**
     * Get all borrow records
     */
    @GetMapping
    @Operation(summary = "Get all borrow records", description = "Retrieves all borrow records")
    public ResponseEntity<List<BorrowRecordDTO>> getAllBorrowRecords() {
        log.info("Fetching all borrow records");
        return ResponseEntity.ok(borrowRecordService.getAllBorrowRecords());
    }

    /**
     * Get all active borrow records
     */
    @GetMapping("/active/all")
    @Operation(summary = "Get all active borrow records", description = "Retrieves all currently active borrow records")
    public ResponseEntity<List<BorrowRecordDTO>> getAllActiveBorrows() {
        log.info("Fetching all active borrow records");
        return ResponseEntity.ok(borrowRecordService.getAllActiveBorrows());
    }

    /**
     * Get overdue records
     */
    @GetMapping("/overdue/all")
    @Operation(summary = "Get overdue records", description = "Retrieves all overdue borrow records")
    public ResponseEntity<List<BorrowRecordDTO>> getOverdueRecords() {
        log.info("Fetching overdue borrow records");
        return ResponseEntity.ok(borrowRecordService.getOverdueRecords());
    }

    /**
     * Get borrow records for member
     */
    @GetMapping("/member/{memberId}")
    @Operation(summary = "Get member's borrow records", description = "Retrieves all borrow records for a specific member")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsForMember(@PathVariable Long memberId) {
        log.info("Fetching borrow records for member ID: {}", memberId);
        return ResponseEntity.ok(borrowRecordService.getBorrowRecordsForMember(memberId));
    }

    /**
     * Get active borrow records for member
     */
    @GetMapping("/member/{memberId}/active")
    @Operation(summary = "Get member's active borrow records", description = "Retrieves active borrow records for a member")
    public ResponseEntity<List<BorrowRecordDTO>> getActiveBorrowsForMember(@PathVariable Long memberId) {
        log.info("Fetching active borrow records for member ID: {}", memberId);
        return ResponseEntity.ok(borrowRecordService.getActiveBorrowsForMember(memberId));
    }

    /**
     * Get borrowing history for member
     */
    @GetMapping("/member/{memberId}/history")
    @Operation(summary = "Get member's borrowing history", description = "Retrieves complete borrowing history for a member")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowingHistory(@PathVariable Long memberId) {
        log.info("Fetching borrowing history for member ID: {}", memberId);
        return ResponseEntity.ok(borrowRecordService.getBorrowingHistory(memberId));
    }

    /**
     * Get borrow records for book
     */
    @GetMapping("/book/{bookId}")
    @Operation(summary = "Get book's borrow records", description = "Retrieves all borrow records for a specific book")
    public ResponseEntity<List<BorrowRecordDTO>> getBorrowRecordsForBook(@PathVariable Long bookId) {
        log.info("Fetching borrow records for book ID: {}", bookId);
        return ResponseEntity.ok(borrowRecordService.getBorrowRecordsForBook(bookId));
    }
}
