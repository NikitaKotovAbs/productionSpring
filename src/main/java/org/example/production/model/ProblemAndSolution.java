package org.example.production.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Problems_And_Solutions")
public class ProblemAndSolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String problemDescription;

    @NotBlank
    private String rootCause;

    @NotBlank
    private String proposedSolution;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date implementationDate;

    @NotBlank
    @Size(max = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true,
            foreignKey = @ForeignKey(name = "fk1l94lfb62m0iyxq590k2ft5n5",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES public.users (id) ON DELETE SET NULL"))
    private User user;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProblemDescription() { return problemDescription; }
    public void setProblemDescription(String problemDescription) { this.problemDescription = problemDescription; }
    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }
    public String getProposedSolution() { return proposedSolution; }
    public void setProposedSolution(String proposedSolution) { this.proposedSolution = proposedSolution; }
    public Date getImplementationDate() { return implementationDate; }
    public void setImplementationDate(Date implementationDate) { this.implementationDate = implementationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
