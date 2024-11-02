package org.example.production.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Production_Tasks")
public class ProductionTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String taskName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Устанавливаем формат даты
    private Date assignmentDate;

    @NotBlank
    @Size(max = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true,
            foreignKey = @ForeignKey(name = "fkb8dvcncqc485aqoxont3hwwfo",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES public.users (id) ON DELETE SET NULL")) // Здесь указываем ON DELETE SET NULL
    private User user;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public Date getAssignmentDate() { return assignmentDate; }
    public void setAssignmentDate(Date assignmentDate) { this.assignmentDate = assignmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
