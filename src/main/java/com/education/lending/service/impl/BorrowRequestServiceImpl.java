package com.education.lending.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.lending.entity.BorrowRequest;
import com.education.lending.entity.Equipment;
import com.education.lending.entity.enums.RequestStatus;
import com.education.lending.repository.BorrowRequestRepository;
import com.education.lending.repository.EquipmentRepository;
import com.education.lending.service.BorrowService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BorrowRequestServiceImpl implements BorrowService {

	@Autowired
	BorrowRequestRepository borrowRepository;
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@Transactional
	@Override
	public BorrowRequest createRequest(BorrowRequest borrowRequest) {
		borrowRepository.save(borrowRequest);
		log.info("Equipment request is created");
		return borrowRequest;
	}
	
	@Transactional
	@Override
	public BorrowRequest updateRequest(BorrowRequest borrowRequest) {
		borrowRepository.save(borrowRequest);
		log.info("Equipment requested is updated");
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
		log.info("Equipment request is deleted");
	}

	@Transactional
	@Override
	public void updateStatusById(RequestStatus status, Integer requestId) {
		borrowRepository.updateStatusById(status, requestId);
		BorrowRequest borrowStatus=this.getRequestById(requestId);
		if(borrowStatus!= null) {
			Equipment equip =  borrowStatus.getEquipment();
			
			if("RETURNED".equalsIgnoreCase(status.name())) {
				if(equip.getQuantity()==0) {
					equip.setQuantity(1);
					equip.setBorrowed(equip.getBorrowed()-1);
					equip.setAvailable(true);
				}else{
					equip.setQuantity(equip.getQuantity()+1);
					equip.setAvailable(true);
					equip.setBorrowed(equip.getBorrowed()-1);
				}
				equipmentRepository.save(equip);
				//equipmentRepository.returnRequest(borrowStatus.getEquipment().);
				log.info("Equipment is returned");
			}else if("APPROVED".equalsIgnoreCase(status.name())) {
				if(equip.getQuantity()==0) {
					equip.setQuantity(0);
					equip.setBorrowed(equip.getBorrowed());
					equip.setAvailable(false);
				}else{
					equip.setQuantity(equip.getQuantity()-1);
					equip.setAvailable(true);
					equip.setBorrowed(equip.getBorrowed()+1);
				}
				equipmentRepository.save(equip);
				//equipmentRepository.borrowRequest(requestId);
				log.info("Equipment is borrowed");
			}
		}
		
	}

}
