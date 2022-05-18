package com.ebench.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Long taskId;
    public Long candidateId;
    public Long vendorId;
    public Long clientId;
    public String projectName;


}
