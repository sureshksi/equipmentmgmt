package com.education.lending.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.education.lending.entity.BorrowRequest;
import com.education.lending.entity.Equipment;
import com.education.lending.entity.User;
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
	
	//@Query(value = "UPDATE request SET status = :status WHERE id = :requestId", nativeQuery = true)
	@Modifying
	@Query("UPDATE BorrowRequest i SET i.status = :status WHERE i.id = :requestId")
	void updateStatusById(@Param("status") RequestStatus status, @Param("requestId") Integer requestId);
	
//	@Query("SELECT b FROM BorrowRequest b WHERE b.user = :userId AND b.status in(PENDING, APPROVED)")
//	List<BorrowRequest> findRequestsByUserId(@Param("userId") User userId);
	
	@Query("SELECT b FROM BorrowRequest b WHERE b.user = :user AND b.status IN (:statuses)")
	List<BorrowRequest> findRequestsByUserAndStatuses(
	        @Param("user") User user,  @Param("statuses") List<RequestStatus> statuses);


}
