package com.education.lending.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.lending.entity.Equipment;
import com.education.lending.repository.EquipmentRepository;
import com.education.lending.service.EquipmentService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**Equipment service implemented methods
 * 
 * @author Suresh Injeti
 *
 */
@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService{

	@Autowired
	EquipmentRepository equipmentRepository;

	@Override
	public List<Equipment> getAllEquipments() {
		List<Equipment> list = equipmentRepository.findAll();
		return list;
	}

	@Override
	public List<String> getCategories() {
		List<String> categoryList =equipmentRepository.findByCategory();
		
		return categoryList;
	}

	@Transactional
	@Override
	public Equipment createEquipment(Equipment equipment) {
		
		equipmentRepository.save(equipment);
		return equipment;
	}

	@Transactional
	@Override
	public Equipment updateEquipment(Equipment equipment) {
		equipmentRepository.save(equipment);
		return equipment;
	}

	@Override
	public void deleteEquipment(Integer equipmentId) {
		equipmentRepository.deleteById(equipmentId);
		
	}


}
