package org.example.production.service;

import org.example.production.model.ProductionTask;
import org.example.production.repository.ProductionTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionTaskService {

    @Autowired
    private ProductionTaskRepository productionTaskRepository;

    public List<ProductionTask> findAll() {
        return productionTaskRepository.findAll();
    }

    public ProductionTask findById(Long id) {
        return productionTaskRepository.findById(id).orElse(null);
    }

    public void save(ProductionTask task) {
        productionTaskRepository.save(task);
    }

    public void deleteById(Long id) {
        productionTaskRepository.deleteById(id);
    }
}
