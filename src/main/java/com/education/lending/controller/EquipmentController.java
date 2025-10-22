package com.education.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.lending.entity.Equipment;
import com.education.lending.service.EquipmentService;

/**Equipment API controller
 * 
 * @author Suresh Injeti
 *
 */
@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/categories")
    public List<String> getCategories() {
        return equipmentService.getCategories();
    }
    
    @GetMapping
    public List<Equipment> getAll() {
        return equipmentService.getAllEquipments();
    }

    @PostMapping
    public Equipment create(@RequestBody Equipment eq) {
        return equipmentService.createEquipment(eq);
    }

    @PutMapping
    public Equipment update( @RequestBody Equipment updated) {
       
        return equipmentService.updateEquipment(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
    	equipmentService.deleteEquipment(id);
    }
}

