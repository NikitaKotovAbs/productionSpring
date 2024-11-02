package org.example.production.repository;

import org.example.production.model.ProductionTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionTaskRepository extends JpaRepository<ProductionTask, Long> {
}
