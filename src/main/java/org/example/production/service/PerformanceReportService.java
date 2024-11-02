package org.example.production.service;

import org.example.production.model.PerformanceReport;
import org.example.production.repository.PerformanceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceReportService {
    @Autowired
    private PerformanceReportRepository performanceReportRepository;

    public List<PerformanceReport> findAll() {
        return performanceReportRepository.findAll();
    }

    public PerformanceReport findById(Long id) {
        return performanceReportRepository.findById(id).orElse(null);
    }

    public void save(PerformanceReport performanceReport) {
        performanceReportRepository.save(performanceReport);
    }

    public void deleteById(Long id) {
        performanceReportRepository.deleteById(id);
    }
}
