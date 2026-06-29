package com.librarymanagement.service;

import com.librarymanagement.dto.MemberDTO;
import com.librarymanagement.entity.Member;
import com.librarymanagement.exception.InvalidOperationException;
import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for Member operations
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /**
     * Create a new member
     */
    public MemberDTO createMember(MemberDTO memberDTO) {
        log.info("Creating new member with email: {}", memberDTO.getEmail());
        
        if (memberRepository.findByEmail(memberDTO.getEmail()).isPresent()) {
            throw new InvalidOperationException("Member with email " + memberDTO.getEmail() + " already exists");
        }
        
        if (memberRepository.findByPhoneNumber(memberDTO.getPhoneNumber()).isPresent()) {
            throw new InvalidOperationException("Member with phone number " + memberDTO.getPhoneNumber() + " already exists");
        }
        
        Member member = modelMapper.map(memberDTO, Member.class);
        member.setMemberId("LIB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        Member savedMember = memberRepository.save(member);
        
        log.info("Member created successfully with ID: {}", savedMember.getId());
        return modelMapper.map(savedMember, MemberDTO.class);
    }

    /**
     * Update an existing member
     */
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        log.info("Updating member with ID: {}", id);
        
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + id));
        
        if (!member.getEmail().equals(memberDTO.getEmail()) && 
            memberRepository.findByEmail(memberDTO.getEmail()).isPresent()) {
            throw new InvalidOperationException("Member with email " + memberDTO.getEmail() + " already exists");
        }
        
        if (!member.getPhoneNumber().equals(memberDTO.getPhoneNumber()) && 
            memberRepository.findByPhoneNumber(memberDTO.getPhoneNumber()).isPresent()) {
            throw new InvalidOperationException("Member with phone number " + memberDTO.getPhoneNumber() + " already exists");
        }
        
        modelMapper.map(memberDTO, member);
        Member updatedMember = memberRepository.save(member);
        
        log.info("Member updated successfully with ID: {}", id);
        return modelMapper.map(updatedMember, MemberDTO.class);
    }

    /**
     * Get member by ID
     */
    public MemberDTO getMemberById(Long id) {
        log.info("Fetching member with ID: {}", id);
        
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + id));
        
        return modelMapper.map(member, MemberDTO.class);
    }

    /**
     * Get member by member ID
     */
    public MemberDTO getMemberByMemberId(String memberId) {
        log.info("Fetching member with Member ID: {}", memberId);
        
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with Member ID: " + memberId));
        
        return modelMapper.map(member, MemberDTO.class);
    }

    /**
     * Get all members
     */
    public List<MemberDTO> getAllMembers() {
        log.info("Fetching all members");
        
        return memberRepository.findAll().stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all active members
     */
    public List<MemberDTO> getAllActiveMembers() {
        log.info("Fetching all active members");
        
        return memberRepository.findByIsActiveTrue().stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get members by membership type
     */
    public List<MemberDTO> getMembersByMembershipType(Member.MembershipType membershipType) {
        log.info("Fetching members by membership type: {}", membershipType);
        
        return memberRepository.findByMembershipType(membershipType).stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Get members by city
     */
    public List<MemberDTO> getMembersByCity(String city) {
        log.info("Fetching members by city: {}", city);
        
        return memberRepository.findByCity(city).stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Search members
     */
    public List<MemberDTO> searchMembers(String searchTerm) {
        log.info("Searching members with term: {}", searchTerm);
        
        return memberRepository.searchMembers(searchTerm).stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Delete member
     */
    public void deleteMember(Long id) {
        log.info("Deleting member with ID: {}", id);
        
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + id));
        
        memberRepository.delete(member);
        log.info("Member deleted successfully with ID: {}", id);
    }

    /**
     * Deactivate member
     */
    public MemberDTO deactivateMember(Long id) {
        log.info("Deactivating member with ID: {}", id);
        
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + id));
        
        member.setIsActive(false);
        Member updatedMember = memberRepository.save(member);
        
        log.info("Member deactivated successfully with ID: {}", id);
        return modelMapper.map(updatedMember, MemberDTO.class);
    }

    /**
     * Check if member can borrow more books
     */
    public boolean canBorrowBook(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));
        
        return member.getCurrentBooksIssued() < member.getMaxBooksAllowed();
    }

    /**
     * Increase books issued count
     */
    public void increaseBookIssueCount(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));
        
        if (!canBorrowBook(memberId)) {
            throw new InvalidOperationException("Member has reached maximum book limit");
        }
        
        member.setCurrentBooksIssued(member.getCurrentBooksIssued() + 1);
        memberRepository.save(member);
    }

    /**
     * Decrease books issued count
     */
    public void decreaseBookIssueCount(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));
        
        if (member.getCurrentBooksIssued() > 0) {
            member.setCurrentBooksIssued(member.getCurrentBooksIssued() - 1);
            memberRepository.save(member);
        }
    }
}
