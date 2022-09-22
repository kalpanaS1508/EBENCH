package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Entity
@Table(name = "company" , uniqueConstraints = {@UniqueConstraint(columnNames = {"company_email"})})
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long companyId;
    public Long candidateId;
    public Long vendorId;
    public String companyName;
    public String companyAddress;

    @Column(name = "company_email", unique = true)
    @Valid
    public String companyEmail;

    public String companyPhoneNumber;
    public String aboutCompany;
    public String companyLocation;

}
