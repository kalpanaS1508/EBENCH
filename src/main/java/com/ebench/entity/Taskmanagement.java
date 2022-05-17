package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "taskmanagement")
public class Taskmanagement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="taskManagementId")
    public Long taskManagementId;
    @Column(name="candidateId")
    public Long candidateId;
    @Column(name = "vendorId")
    public Long vendorId;
    @Column(name="clientId")
    public Long clientId;
    @Column(name="candidateName")
    public String candidateName;
    @Column(name="projectName")
    public String projectName;
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
    @Column(name="isDeleted")
    public boolean isDeleted;

    public Taskmanagement(Long taskManagementId, Long candidateId, Long vendorId, Long clientId, String candidateName,
                          String projectName, Integer noOfProjects, String taskName, Date taskStartDate, Date taskDueDate,
                          boolean taskStatus, ChangeTaskStatus changeTaskStatus, Long noOfDelayedDate,
                          String taskDescription, String addCommentsFromClient, String addCommentsFromCandidate, boolean isDeleted) {
        this.taskManagementId = taskManagementId;
        this.candidateId = candidateId;
        this.vendorId = vendorId;
        this.clientId = clientId;
        this.candidateName = candidateName;
        this.projectName = projectName;
        this.noOfProjects = noOfProjects;
        this.taskName = taskName;
        this.taskStartDate = taskStartDate;
        this.taskDueDate = taskDueDate;
        this.taskStatus = taskStatus;
        this.changeTaskStatus = changeTaskStatus;
        this.noOfDelayedDate = noOfDelayedDate;
        this.taskDescription = taskDescription;
        this.addCommentsFromClient = addCommentsFromClient;
        this.addCommentsFromCandidate = addCommentsFromCandidate;
        this.isDeleted = isDeleted;
    }

    public Taskmanagement() {
    }
}
