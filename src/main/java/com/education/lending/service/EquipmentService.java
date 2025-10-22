package com.education.lending.service;

import java.util.List;

import com.education.lending.entity.Equipment;

/**Equipment service defined methods
 * 
 * @author Suresh Injeti
 *
 */
public interface EquipmentService {

	public List<Equipment> getAllEquipments();
	public List<String> getCategories();
	public Equipment createEquipment(Equipment equipment);
	public Equipment updateEquipment(Equipment equipment);
	public void deleteEquipment(Integer equipmentId);
	
}
