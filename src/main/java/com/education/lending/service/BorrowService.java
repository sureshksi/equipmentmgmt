package com.education.lending.service;

import java.util.List;

import com.education.lending.entity.BorrowRequest;
import com.education.lending.entity.enums.RequestStatus;

public interface BorrowService {

	public BorrowRequest createRequest(BorrowRequest borrowRequest);
	public BorrowRequest updateRequest(BorrowRequest borrowRequest);
	public BorrowRequest getRequestById(Integer requestId);
	public List<BorrowRequest> getAllRequests();
	public void deleteRequestById(Integer requestId);
	public void updateStatusById(RequestStatus status, Integer requestId);
}
