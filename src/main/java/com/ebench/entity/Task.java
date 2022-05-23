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
    @Column(name="taskmanagementId")
    public Long taskManagementId;
    @Column(name="candidateId")
    public Long candidateId;
    @Column(name = "vendorId")
    public Long vendorId;
    @Column(name="clientId")
    public Long clientId;
    @Column(name="projectId")
    public String projectId;
    @Column(name="candidateName")
    public String candidateName;
    @Column(name="projectName")
    public String projectName;
    @Column(name = "clientName")
    public String clientName;
    @Column(name ="noofProjects")
    public Integer noOfProjects;
    @Column(name="taskName")
    public String taskName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="taskStartDate")
    public Date taskStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="taskDueDate")
    public Date taskDueDate;
    @Column(name="taskStatus")
    public boolean taskStatus;
    @Column(name="changeTaskStatus")
    @Enumerated(EnumType.STRING)
    public ChangeTaskStatus changeTaskStatus;
    @Column(name="noOfDelayedDate")
    public Long noOfDelayedDate;
    @Size(max=300)
    @Column(name="taskDescription")
    public String taskDescription;
    @Column(name="addCommentsFromClient")
    public String addCommentsFromClient;
    @Column(name="addCommentsFromCandidate")
    public String addCommentsFromCandidate;
    @Column(name="waitingResponseFrom")
    public String waitingResponseFrom;
    @Column(name="anyDependency")
    public String anyDependency;




    public Task() {
    }

    public Task(String projectName, String taskName, Date taskDueDate, ChangeTaskStatus changeTaskStatus) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.taskDueDate = taskDueDate;
        this.changeTaskStatus = changeTaskStatus;
    }
}
