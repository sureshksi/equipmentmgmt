package com.education.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.lending.entity.BorrowRequest;
import com.education.lending.entity.enums.RequestStatus;
import com.education.lending.service.BorrowService;

/**Borrow request API controller
 * 
 * @author Suresh Injeti
 *
 */
@RestController
@RequestMapping("/api/v1/borrowrequest")
public class BorrowRequestController {

	@Autowired
	BorrowService borrowService;
	
	@GetMapping
    public ResponseEntity<?> getAll() {
        List<BorrowRequest> request = borrowService.getAllRequests();
         return ResponseEntity.ok(request);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BorrowRequest request) {
    	if(request != null) {
    		borrowService.createRequest(request);
    		return ResponseEntity.ok(request);
    	}else {
    		return new ResponseEntity<Object>("Create request failed", HttpStatus.BAD_REQUEST);
    	}
    }

    @PutMapping
	public ResponseEntity<?> update(@RequestBody BorrowRequest updated) {
		if (updated != null) {
			borrowService.updateRequest(updated);
			return ResponseEntity.ok(updated);
		} else {
			return new ResponseEntity<Object>("updated request failed", HttpStatus.BAD_REQUEST);
		}
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
    	if(id!=0) {
	    	borrowService.deleteRequestById(id);
	    	return ResponseEntity.ok("Delete deleted successfully");
    	} else {
    		return new ResponseEntity<Object>("Delete request failed", HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PatchMapping("/updatestatus")
    public ResponseEntity<?> updateStatus(@RequestParam String status, @RequestParam Integer requestId) {
    	if(requestId!=0) {
	    	borrowService.updateStatusById(RequestStatus.fromString(status), requestId);
	    	return ResponseEntity.ok("Update status successfully");
    	} else {
    		return new ResponseEntity<Object>("Update status request failed", HttpStatus.BAD_REQUEST);
    	}
    }
    
	@GetMapping("/user/{userId}")
    public ResponseEntity<?> getRequestsByUserId(@PathVariable Integer userId) {
        List<BorrowRequest> request = borrowService.getRequestByUser(userId);
         return ResponseEntity.ok(request);
    }
}
