package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Data
@Valid
@Table(name = "Jobs")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="jobId")
    public Long jobId;

    @Column(name="candidateId")
    public Long candidateId;

    @Column(name="jobProfile")
    public String jobProfile;

    @Column(name="location")
    public String location;

    @Column(name ="companyName")
    public String companyName;

    @Column(name = "employment")
    @Enumerated(EnumType.STRING)
    public Employment employment;

    @Column(name="team")
    public String team;

    @Lob
    @Column(name="overviewOfJob")
    public String overviewOfJob;

    @Column(name="views")
    public Long views;

    @Column(name="minimumQualification")
    public String minimumQualification;

    @Column(name="preferredQualification")
    public String preferredQualification;

    @Lob
    @Column(name="aboutUser")
    public  String aboutUser;
    @Lob
    @Column(name="aboutJob")
    public String aboutJob;






}
