package org.example.production.service;

import org.example.production.model.QualityControl;
import org.example.production.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualityControlService {

    @Autowired
    private QualityControlRepository qualityControlRepository;

    public List<QualityControl> findAll() {
        return qualityControlRepository.findAll();
    }

    public QualityControl findById(Long id) {
        return qualityControlRepository.findById(id).orElse(null);
    }

    public QualityControl save(QualityControl qualityControl) {
        return qualityControlRepository.save(qualityControl);
    }

    public void deleteById(Long id) {
        qualityControlRepository.deleteById(id);
    }
}
