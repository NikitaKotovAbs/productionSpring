package org.example.production.service;

import org.example.production.model.ProblemAndSolution;
import org.example.production.repository.ProblemAndSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemAndSolutionService {

    @Autowired
    private ProblemAndSolutionRepository repository;

    public List<ProblemAndSolution> findAll() {
        return repository.findAll();
    }

    public ProblemAndSolution findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(ProblemAndSolution problemAndSolution) {
        repository.save(problemAndSolution);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
