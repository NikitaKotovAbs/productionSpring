package org.example.production.controller;

import org.example.production.model.PerformanceReport;
import org.example.production.model.ProductionTask;
import org.example.production.service.PerformanceReportService;
import org.example.production.service.ProductionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasRole('User') or hasRole('Builder')")
@Controller
@RequestMapping("/reports")
public class PerformanceReportController {

    @Autowired
    private PerformanceReportService performanceReportService;

    @Autowired
    private ProductionTaskService productionTaskService;

    @GetMapping
    public String listReports(Model model) {
        model.addAttribute("reports", performanceReportService.findAll());
        return "reports/index"; // путь к представлению списка отчетов
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/add")
    public String showAddReportForm(Model model) {
        model.addAttribute("report", new PerformanceReport());
        model.addAttribute("tasks", productionTaskService.findAll()); // Получаем все задачи для выбора
        return "reports/add"; // путь к представлению добавления отчета
    }

    @PostMapping("/add")
    public String addReport(@Valid @ModelAttribute PerformanceReport report, @RequestParam("taskId") Long taskId) {
        ProductionTask task = productionTaskService.findById(taskId); // Получаем задачу по ID
        report.setTask(task); // Устанавливаем задачу в отчет
        performanceReportService.save(report); // Сохраняем отчет
        return "redirect:/reports";
    }

    @GetMapping("/edit/{id}")
    public String showEditReportForm(@PathVariable("id") Long id, Model model) {
        PerformanceReport report = performanceReportService.findById(id);
        model.addAttribute("report", report);
        model.addAttribute("tasks", productionTaskService.findAll()); // Добавляем задачи для выбора
        return "reports/edit"; // путь к представлению редактирования отчета
    }

    @PostMapping("/edit/{id}")
    public String editReport(@ModelAttribute PerformanceReport report) {
        performanceReportService.save(report); // Сохраняем отчет
        return "redirect:/reports";
    }

    @GetMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id) {
        performanceReportService.deleteById(id);
        return "redirect:/reports";
    }

    @GetMapping("/show/{id}")
    public String showReport(@PathVariable Long id, Model model) {
        PerformanceReport report = performanceReportService.findById(id);
        model.addAttribute("report", report);
        return "reports/show"; // путь к представлению показа отчета
    }
}
