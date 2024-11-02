package org.example.production.repository;

import org.example.production.model.PerformanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReportRepository extends JpaRepository<PerformanceReport, Long> {
}
