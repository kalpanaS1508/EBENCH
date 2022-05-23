package com.ebench.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long id;
    @Column(name="taskId")
    public Long taskId;
    @Column(name="candidateId")
    public Long candidateId;
    @Column(name="vendorId")
    public Long vendorId;
    @Column(name="clientId")
    public Long clientId;
    @Column(name="projectName")
    public String projectName;
    @NotNull
    @Column(name="isDeleted")
    public boolean isDeleted;


    public Project(Long id, Long taskId, Long candidateId, Long vendorId, Long clientId, String projectName, boolean isDeleted) {
        this.id = id;
        this.taskId = taskId;
        this.candidateId = candidateId;
        this.vendorId = vendorId;
        this.clientId = clientId;
        this.projectName = projectName;
        this.isDeleted = isDeleted;
    }

    public Project() {
    }
}
