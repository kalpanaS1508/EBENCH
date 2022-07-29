package com.ebench.dto;

import com.ebench.entity.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
@Valid
@AllArgsConstructor
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
    public String email;
    public String interest;
    public String mobile;
    public boolean deleted;
    public boolean availableForWork;
    @Size(min = 2, max = 30)
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
    public boolean isCandidate;
    public String specialization;
    public String yearOfPassing;
    public BigDecimal percentage;
    public String collegeName;
    public String universityName;
    public String schoolName;
    public String emailVerifyCode;
    public boolean emailVerified;
    public String role;



    public CandidateReqDto() {
    }
}
