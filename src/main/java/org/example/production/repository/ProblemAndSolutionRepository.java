package org.example.production.repository;

import org.example.production.model.ProblemAndSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemAndSolutionRepository extends JpaRepository<ProblemAndSolution, Long> {
}
