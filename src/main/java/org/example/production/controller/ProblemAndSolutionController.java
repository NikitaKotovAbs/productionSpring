package org.example.production.controller;

import org.example.production.model.ProblemAndSolution;
import org.example.production.model.ProductionTask;
import org.example.production.model.User;
import org.example.production.service.ProblemAndSolutionService;
import org.example.production.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasRole('User') or hasRole('Builder')")

@Controller
@RequestMapping("/problems")
public class ProblemAndSolutionController {

    @Autowired
    private ProblemAndSolutionService problemAndSolutionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listProblems(Model model) {
        model.addAttribute("problems", problemAndSolutionService.findAll());
        return "problems/index";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/add")
    public String showAddProblemForm(Model model) {
        model.addAttribute("problem", new ProblemAndSolution());
        model.addAttribute("users", userService.getAllUsers());
        return "problems/add";
    }

    @PostMapping("/add")
    public String addProblem(@ModelAttribute ProblemAndSolution problem, @RequestParam("userId") Long userId) {
        User user = userService.findById(userId); // Получаем пользователя по ID
        problem.setUser(user); // Устанавливаем пользователя в проблему
        problemAndSolutionService.save(problem); // Сохраняем проблему
        return "redirect:/problems";
    }


    @GetMapping("/edit/{id}")
    public String showEditProblemForm(@PathVariable Long id, Model model) {
        ProblemAndSolution problem = problemAndSolutionService.findById(id);
        model.addAttribute("problem", problem);
        model.addAttribute("users", userService.getAllUsers());
        return "problems/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProblem(@ModelAttribute ProblemAndSolution problem) {
//        problem.setId(id);
//        User user = userService.findById(userId);
//        problem.setUser(user);
        problemAndSolutionService.save(problem);
        return "redirect:/problems";
    }

    @GetMapping("/delete/{id}")
    public String deleteProblem(@PathVariable Long id) {
        problemAndSolutionService.deleteById(id);
        return "redirect:/problems";
    }

    @GetMapping("/show/{id}")
    public String showProblem(@PathVariable Long id, Model model) {
        ProblemAndSolution problem = problemAndSolutionService.findById(id);
        model.addAttribute("problem", problem);
        return "problems/show";
    }
}
