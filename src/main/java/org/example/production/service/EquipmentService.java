package org.example.production.service;

import org.example.production.model.Equipment;
import org.example.production.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public void save(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }
}
