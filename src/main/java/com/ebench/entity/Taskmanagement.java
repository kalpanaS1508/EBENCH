package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
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
    public Long id;
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
    @Column(name="taskStartDate")
    public Date taskStartDate;
    @Column(name="taskDueDate")
    public Date taskDueDate;
    @Column(name="taskStatus")
    public boolean taskStatus;
    @Column(name="changeTaskStatus")
    public changeTaskStatus changeTaskStatus;
    @Column(name="noOfDelayedDate")
    public Long noOfDelayedDate;
    @Size(max=300)
    @Column(name="taskDescription")
    public String taskDescription;
    @Column(name="addCommentsFromClient")
    public String addCommentsFromClient;
    @Column(name="addCommentsFromCandidate")
    public String addCommentsFromCandidate;

    public Taskmanagement(Long taskManagementId, Long id, Long vendorId, Long clientId, String candidateName,
                          String projectName, Integer noOfProjects, String taskName, Date taskStartDate, Date taskDueDate,
                          boolean taskStatus, com.ebench.entity.changeTaskStatus changeTaskStatus,
                          Long noOfDelayedDate, String taskDescription, String addCommentsFromClient, String addCommentsFromCandidate) {
        this.taskManagementId = taskManagementId;
        this.id = id;
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
    }
}
