package org.example.production.controller;

import org.example.production.model.Equipment;
import org.example.production.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasRole('User') or hasRole('Builder')")
@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public String listEquipment(Model model) {
        model.addAttribute("equipmentList", equipmentService.findAll());
        return "equipment/index";
    }
    @PreAuthorize("hasRole('Builder')")
    @GetMapping("/add")
    public String showAddEquipmentForm(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "equipment/add";
    }

    @PostMapping("/add")
    public String addEquipment(@Valid @ModelAttribute Equipment equipment) {
        equipmentService.save(equipment);
        return "redirect:/equipment";
    }

    @GetMapping("/edit/{id}")
    public String showEditEquipmentForm(@PathVariable Long id, Model model) {
        Equipment equipment = equipmentService.findById(id);
        model.addAttribute("equipment", equipment);
        return "equipment/edit";
    }

    @PostMapping("/edit/{id}")
    public String editEquipment(@PathVariable Long id, @Valid @ModelAttribute Equipment equipment) {
        equipment.setId(id);
        equipmentService.save(equipment);
        return "redirect:/equipment";
    }

    @GetMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteById(id);
        return "redirect:/equipment";
    }

    @GetMapping("/show/{id}")
    public String showEquipment(@PathVariable Long id, Model model) {
        Equipment equipment = equipmentService.findById(id);
        model.addAttribute("equipment", equipment);
        return "equipment/show";
    }
}
