package org.example.production.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Quality_Control")
public class QualityControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = true,
            foreignKey = @ForeignKey(name = "fkg8blym25486pwjnx13oeb55gb",
                    foreignKeyDefinition = "FOREIGN KEY (task_id) REFERENCES public.production_tasks (id) ON DELETE SET NULL"))
    private ProductionTask task;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inspectionDate;

    @NotBlank
    @Size(max = 50)
    private String inspectionResult;

    private String comments;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProductionTask getTask() { return task; }
    public void setTask(ProductionTask task) { this.task = task; }
    public Date getInspectionDate() { return inspectionDate; }
    public void setInspectionDate(Date inspectionDate) { this.inspectionDate = inspectionDate; }
    public String getInspectionResult() { return inspectionResult; }
    public void setInspectionResult(String inspectionResult) { this.inspectionResult = inspectionResult; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
