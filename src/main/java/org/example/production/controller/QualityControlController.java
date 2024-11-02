package org.example.production.controller;

import org.example.production.model.QualityControl;
import org.example.production.model.ProductionTask;
import org.example.production.service.QualityControlService;
import org.example.production.service.ProductionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@PreAuthorize("hasRole('User') or hasRole('Builder')")
@Controller
@RequestMapping("/quality-control")
public class QualityControlController {

    @Autowired
    private QualityControlService qualityControlService;

    @Autowired
    private ProductionTaskService productionTaskService;

    @GetMapping
    public String listQualityControls(Model model) {
        model.addAttribute("qualityControls", qualityControlService.findAll());
        return "quality-control/index";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("qualityControl", new QualityControl());
        model.addAttribute("tasks", productionTaskService.findAll());
        return "quality-control/add";
    }
    @PreAuthorize("hasRole('Builder')")
    @PostMapping("/add")
    public String addQualityControl(@ModelAttribute QualityControl qualityControl) {
        qualityControlService.save(qualityControl);
        return "redirect:/quality-control";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        QualityControl qualityControl = qualityControlService.findById(id);
        model.addAttribute("qualityControl", qualityControl);
        model.addAttribute("tasks", productionTaskService.findAll());
        return "quality-control/edit";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/show/{id}")
    public String showQualityControl(@PathVariable Long id, Model model) {
        QualityControl qualityControl = qualityControlService.findById(id);
        model.addAttribute("qualityControl", qualityControl);
        return "quality-control/show";
    }

    @PreAuthorize("hasRole('Builder')")
    @PostMapping("/edit/{id}")
    public String editQualityControl(@PathVariable Long id, @Valid @ModelAttribute QualityControl qualityControl, @RequestParam("taskId") Long taskId) {
        ProductionTask task = productionTaskService.findById(taskId);
        qualityControl.setTask(task);
        qualityControlService.save(qualityControl);
        return "redirect:/quality-control";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/delete/{id}")
    public String deleteQualityControl(@PathVariable Long id) {
        qualityControlService.deleteById(id);
        return "redirect:/quality-control";
    }
}
