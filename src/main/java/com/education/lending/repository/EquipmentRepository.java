package com.education.lending.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("select DISTINCT e.category from Equipment as e")
	List<String> findByCategory();
	
	@Modifying
	@Query("UPDATE Equipment e SET e.borrowed = e.borrowed+1, e.quantity = e.quantity-1 WHERE e.id = :equipmentId")
	void borrowRequest(@Param("equipmentId") Integer equipmentId);


	@Modifying
	@Query("UPDATE Equipment e SET e.borrowed = e.borrowed-1, e.quantity = e.quantity+1 WHERE e.id = :equipmentId")
	void returnRequest( @Param("equipmentId") Integer equipmentId);


}
