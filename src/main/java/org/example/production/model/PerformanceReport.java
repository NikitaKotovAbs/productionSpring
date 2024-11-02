package org.example.production.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Performance_Reports")
public class PerformanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "task_id", nullable = true,
            foreignKey = @ForeignKey(name = "fkq2v0lom7wghdwm25x6mgm0fol",
                    foreignKeyDefinition = "FOREIGN KEY (task_id) REFERENCES public.production_tasks (id) ON DELETE SET NULL"))
    private ProductionTask task;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reportDate;

    @Min(0)
    private int producedQuantity;

    @Min(0)
    private int defectiveQuantity;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProductionTask getTask() { return task; }
    public void setTask(ProductionTask task) { this.task = task; }
    public Date getReportDate() { return reportDate; }
    public void setReportDate(Date reportDate) { this.reportDate = reportDate; }
    public int getProducedQuantity() { return producedQuantity; }
    public void setProducedQuantity(int producedQuantity) { this.producedQuantity = producedQuantity; }
    public int getDefectiveQuantity() { return defectiveQuantity; }
    public void setDefectiveQuantity(int defectiveQuantity) { this.defectiveQuantity = defectiveQuantity; }
}
