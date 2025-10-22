package com.education.lending.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.education.lending.entity.BorrowRequest;
import com.education.lending.entity.Equipment;
import com.education.lending.entity.enums.RequestStatus;

/**Repository for Request
 * 
 * @author Suresh Injeti
 *
 */
@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Integer> {
	
	List<BorrowRequest> findByEquipmentAndStatusInAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
			Equipment equipment, List<RequestStatus> statuses, LocalDate endDate, LocalDate startDate);


	List<BorrowRequest> findByStatus(String status);
}
