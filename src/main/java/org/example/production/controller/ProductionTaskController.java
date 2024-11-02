package org.example.production.controller;

import org.example.production.model.ProductionTask;
import org.example.production.model.User;
import org.example.production.service.ProductionTaskService;
import org.example.production.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@PreAuthorize("hasRole('User') or hasRole('Builder')")
@Controller
@RequestMapping("/tasks")
public class ProductionTaskController {

    @Autowired
    private ProductionTaskService productionTaskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", productionTaskService.findAll());
        return "tasks/index"; // Путь к вашему шаблону списка задач
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new ProductionTask());
        model.addAttribute("users", userService.getAllUsers()); // Получаем всех пользователей
        return "tasks/add"; // Путь к вашему шаблону добавления задачи
    }


    @PostMapping("/add")
    public String addTask(@ModelAttribute ProductionTask task, @RequestParam("userId") Long userId) {
        User user = userService.findById(userId); // Получаем пользователя по ID
        task.setUser(user); // Устанавливаем пользователя в задачу
        productionTaskService.save(task); // Сохраняем задачу
        return "redirect:/tasks";
    }



    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") Long taskId, Model model) {
        ProductionTask task = productionTaskService.findById(taskId);
        List<User> users = userService.getAllUsers(); // Получаем всех пользователей

        model.addAttribute("task", task);
        model.addAttribute("users", users); // Передаем список пользователей в модель
        return "tasks/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@ModelAttribute ProductionTask task) {
        if (task.getUser() == null) {
            // Здесь можно установить значение по умолчанию, если это необходимо
        }
        productionTaskService.save(task);
        return "redirect:/tasks"; // Перенаправление после сохранения
    }


    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        productionTaskService.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/show/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        ProductionTask task = productionTaskService.findById(id);
        model.addAttribute("task", task);
        return "tasks/show"; // Путь к вашему шаблону показа задачи
    }
}
