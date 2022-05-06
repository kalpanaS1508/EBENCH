package com.ebench.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name="education")
public class Education {
    @Column(name="education_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long education_id;
    @Column(name="candidateId")
   public Long candidateId;
    @Column(name="specialization")
    public String specialization;
    @Column(name="yearOfPassing")
    public String yearOfPassing;
    @Column(name="percentage")
    public BigDecimal percentage;
    @Column(name="collegeName")
    public String collegeName;
    @Column(name="universityName")
    public String universityName;
    @Column(name="schoolName")
    public String schoolName;

    @ManyToMany
    Set<Candidate> candidates;

    public Education() {

    }






}
