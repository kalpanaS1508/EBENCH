package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    public Long projectId;

    @Column(name = "taskId")
    public Long taskId;
    @Column(name = "candidateId")
    public Long candidateId;
    @Column(name = "vendorId")
    public Long vendorId;
    @Column(name = "clientId")
    public Long clientId;
    @Column(name = "projectName")
    public String projectName;
    @NotNull
    @Column(name = "isDeleted")
    public boolean isDeleted;

}
