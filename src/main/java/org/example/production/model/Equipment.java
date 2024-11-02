package org.example.production.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String equipmentName;

    @NotBlank
    @Size(max = 50)
    private String equipmentType;

    @NotBlank
    @Size(max = 50)
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }
    public String getEquipmentType() { return equipmentType; }
    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
