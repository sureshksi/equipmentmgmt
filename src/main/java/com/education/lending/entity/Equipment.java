package com.education.lending.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**Equipment entity
 * 
 * @author Suresh Injeti
 *
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name="equipment")
public class Equipment {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Integer id;
    private String name;
    private String category;
    private String conditiontype;
    private Integer quantity;
    private Boolean available;
}

