package com.education.lending.entity;

import com.education.lending.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**User entity
 * 
 * @author Suresh Injeti
 *
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id 
    @GeneratedValue 
    private Integer id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String mobile;
    @Column(name="loginid")
    private String loginId;
    @Enumerated(EnumType.STRING)
    private Role role;
    
}

