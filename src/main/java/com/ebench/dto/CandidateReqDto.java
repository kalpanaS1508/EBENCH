package com.ebench.dto;

import com.ebench.entity.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
@Valid
@SQLDelete(sql = "UPDATE Candidate SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(uniqueConstraints =@UniqueConstraint(columnNames = {"email"}))
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class CandidateReqDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String keyExperience;
    public String skills;
    public String address;
    public String skypeId;
    public String whatsapp;
    public String country;
    public String state;
    public String city;
    public String hobbies;
    @Column(unique = true)
    @Valid
    @NotNull(message = "Email Already Present")
    private String email;
    public String interest;
    public String mobile;
    public boolean deleted;
    public boolean availableForWork;
    public String password;
    public String profileImageUrl;
    public String twitterId;
    public String linkedIn;
    public String pincode;
    public boolean activeStatus;
    @JsonFormat(pattern = "HH:mm:ss")
    public Time lastSeen;
    public String currentDesignation;
    public  String jobProfile;
    @Lob
    public String overview;
    public String CurrentlyWorkingCompanyName;
    public String roleInHiring;
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date joiningDateInCompany;
    @Enumerated(EnumType.STRING)
    public UserType userType;
    public String specialization;
    public String yearOfPassing;
    public BigDecimal percentage;
    public String collegeName;
    public String universityName;
    public String schoolName;

    public CandidateReqDto(Long id, String firstName, String lastName, String keyExperience, String skills, String address, String skypeId, String whatsapp, String country, String state, String city,
                           String hobbies, String email, String interest, String mobile,
                           boolean deleted, boolean availableForWork, String password, String profileImageUrl, String twitterId, String linkedIn, String pincode, boolean activeStatus, Time lastSeen,
                           String currentDesignation,
                           String jobProfile, String overview, String currentlyWorkingCompanyName, String roleInHiring, Date joiningDateInCompany, UserType userType, String specialization,
                           String yearOfPassing, BigDecimal percentage, String collegeName, String universityName, String schoolName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.keyExperience = keyExperience;
        this.skills = skills;
        this.address = address;
        this.skypeId = skypeId;
        this.whatsapp = whatsapp;
        this.country = country;
        this.state = state;
        this.city = city;
        this.hobbies = hobbies;
        this.email = email;
        this.interest = interest;
        this.mobile = mobile;
        this.deleted = deleted;
        this.availableForWork = availableForWork;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.twitterId = twitterId;
        this.linkedIn = linkedIn;
        this.pincode = pincode;
        this.activeStatus = activeStatus;
        this.lastSeen = lastSeen;
        this.currentDesignation = currentDesignation;
        this.jobProfile = jobProfile;
        this.overview = overview;
        CurrentlyWorkingCompanyName = currentlyWorkingCompanyName;
        this.roleInHiring = roleInHiring;
        this.joiningDateInCompany = joiningDateInCompany;
        this.userType = userType;
        this.specialization = specialization;
        this.yearOfPassing = yearOfPassing;
        this.percentage = percentage;
        this.collegeName = collegeName;
        this.universityName = universityName;
        this.schoolName = schoolName;
    }

    public CandidateReqDto() {
    }
}

