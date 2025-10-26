package com.education.lending.entity;

import java.time.LocalDate;

import com.education.lending.entity.enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="request")
public class BorrowRequest {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    private String requestedby;

    @Enumerated(EnumType.STRING)
    private RequestStatus status; // PENDING, APPROVED, REJECTED, RETURNED
    
   // @ManyToOne private User user;
    //@ManyToOne private Equipment equipment;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "equipmentid")
    private Equipment equipment;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid")
    private User user;

    }

