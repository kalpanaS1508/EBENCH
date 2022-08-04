package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "taskmanagement")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Long taskManagementId;

    public Long candidateId;

    public Long vendorId;

    public Long clientId;

    public Long projectId;

    public String candidateName;
    public String projectName;

    public String clientName;

    public Integer noOfProjects;
    public String taskName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public String taskStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public String taskDueDate;

    public boolean taskStatus;

    @Enumerated(EnumType.STRING)
    public ChangeTaskStatus changeTaskStatus;

    public Long noOfDelayedDate;
    @Size(max=300)

    public String taskDescription;

  public String addCommentsFromClient;

    public String addCommentsFromCandidate;

    public String waitingResponseFrom;

    public String anyDependency;




    public Task() {
    }

    public Task(String projectName, String taskName, String taskDueDate, ChangeTaskStatus changeTaskStatus) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.taskDueDate = taskDueDate;
        this.changeTaskStatus = changeTaskStatus;
    }
}
