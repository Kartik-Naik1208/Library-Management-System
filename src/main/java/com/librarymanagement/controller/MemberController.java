package com.librarymanagement.controller;

import com.librarymanagement.dto.MemberDTO;
import com.librarymanagement.entity.Member;
import com.librarymanagement.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Member management endpoints
 */
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Members", description = "Member Management APIs")
public class MemberController {

    private final MemberService memberService;

    /**
     * Create a new member
     */
    @PostMapping
    @Operation(summary = "Create a new member", description = "Registers a new member in the library")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        log.info("Creating new member");
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberDTO));
    }

    /**
     * Get all members
     */
    @GetMapping
    @Operation(summary = "Get all members", description = "Retrieves all members from the library")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        log.info("Fetching all members");
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    /**
     * Get member by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get member by ID", description = "Retrieves a specific member by ID")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        log.info("Fetching member with ID: {}", id);
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    /**
     * Get member by Member ID
     */
    @GetMapping("/memberId/{memberId}")
    @Operation(summary = "Get member by Member ID", description = "Retrieves a member by their member ID")
    public ResponseEntity<MemberDTO> getMemberByMemberId(@PathVariable String memberId) {
        log.info("Fetching member with Member ID: {}", memberId);
        return ResponseEntity.ok(memberService.getMemberByMemberId(memberId));
    }

    /**
     * Get all active members
     */
    @GetMapping("/active/all")
    @Operation(summary = "Get all active members", description = "Retrieves all active members")
    public ResponseEntity<List<MemberDTO>> getAllActiveMembers() {
        log.info("Fetching all active members");
        return ResponseEntity.ok(memberService.getAllActiveMembers());
    }

    /**
     * Get members by membership type
     */
    @GetMapping("/membership-type/{type}")
    @Operation(summary = "Get members by membership type", description = "Retrieves members by membership type")
    public ResponseEntity<List<MemberDTO>> getMembersByMembershipType(@PathVariable Member.MembershipType type) {
        log.info("Fetching members by membership type: {}", type);
        return ResponseEntity.ok(memberService.getMembersByMembershipType(type));
    }

    /**
     * Get members by city
     */
    @GetMapping("/city/{city}")
    @Operation(summary = "Get members by city", description = "Retrieves members from a specific city")
    public ResponseEntity<List<MemberDTO>> getMembersByCity(@PathVariable String city) {
        log.info("Fetching members by city: {}", city);
        return ResponseEntity.ok(memberService.getMembersByCity(city));
    }

    /**
     * Search members
     */
    @GetMapping("/search")
    @Operation(summary = "Search members", description = "Searches members by name")
    public ResponseEntity<List<MemberDTO>> searchMembers(@RequestParam String searchTerm) {
        log.info("Searching members with term: {}", searchTerm);
        return ResponseEntity.ok(memberService.searchMembers(searchTerm));
    }

    /**
     * Update member
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update member", description = "Updates an existing member's information")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        log.info("Updating member with ID: {}", id);
        return ResponseEntity.ok(memberService.updateMember(id, memberDTO));
    }

    /**
     * Deactivate member
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate member", description = "Deactivates a member")
    public ResponseEntity<MemberDTO> deactivateMember(@PathVariable Long id) {
        log.info("Deactivating member with ID: {}", id);
        return ResponseEntity.ok(memberService.deactivateMember(id));
    }

    /**
     * Delete member
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete member", description = "Deletes a member from the library")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        log.info("Deleting member with ID: {}", id);
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
