package com.education.lending.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.education.lending.entity.Equipment;

/**Repository for Equipment
 * 
 * @author Suresh Injeti
 *
 */
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

	List<Equipment> findByCategoryContainingIgnoreCase(String category);

	@Query("select e.category from Equipment as e")
	List<String> findByCategory();
}
