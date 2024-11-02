package org.example.production.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String materialName;

    @Min(0)
    private int quantityInStock;

    @NotBlank
    @Size(max = 20)
    private String unitOfMeasurement;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    public String getUnitOfMeasurement() { return unitOfMeasurement; }
    public void setUnitOfMeasurement(String unitOfMeasurement) { this.unitOfMeasurement = unitOfMeasurement; }
}
