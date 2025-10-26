package com.education.lending.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.lending.entity.BorrowRequest;
import com.education.lending.entity.enums.RequestStatus;
import com.education.lending.repository.BorrowRequestRepository;
import com.education.lending.service.BorrowService;

import jakarta.transaction.Transactional;

@Service
public class BorrowRequestServiceImpl implements BorrowService {

	@Autowired
	BorrowRequestRepository borrowRepository;
	
	@Transactional
	@Override
	public BorrowRequest createRequest(BorrowRequest borrowRequest) {
		borrowRepository.save(borrowRequest);
		return borrowRequest;
	}
	
	@Transactional
	@Override
	public BorrowRequest updateRequest(BorrowRequest borrowRequest) {
		borrowRepository.save(borrowRequest);
		return borrowRequest;
	}

	@Override
	public BorrowRequest getRequestById(Integer requestId) {
		Optional<BorrowRequest> borrowOpts = borrowRepository.findById(requestId);
		if(borrowOpts.isEmpty())
			return null;
		else
			return borrowOpts.get();
	}

	@Override
	public List<BorrowRequest> getAllRequests() {
		return borrowRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteRequestById(Integer requestId) {
		borrowRepository.deleteById(requestId);
	}

	@Transactional
	@Override
	public void updateStatusById(RequestStatus status, Integer requestId) {
		borrowRepository.updateStatusById(status, requestId);
	}

}
