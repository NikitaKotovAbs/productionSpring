package org.example.production.service;

import org.example.production.model.Material;
import org.example.production.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material findById(Long id) {
        return materialRepository.findById(id).orElse(null);
    }

    public void save(Material material) {
        materialRepository.save(material);
    }

    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }
}
