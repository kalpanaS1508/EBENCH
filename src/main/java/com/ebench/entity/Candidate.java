package com.ebench.entity;

import com.ebench.Enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Valid
@Table(name = "Candidate", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long candidateId;

    @Column(name = "interview_id")
    public Long interviewId;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "key_experience")
    public String keyExperience;

    @Column(name = "skills")
    public String skills;

    @Column(name = "address")
    public String address;

    @Column(name = "deleted")
    public boolean deleted;

    @Column(name = "skype_id")
    public String skypeId;

    @Column(name = "whatsapp")
    public String whatsapp;

    @Column(name = "country")
    public String country;

    @Column(name = "state")
    public String state;

    @Column(name = "city")
    public String city;

    @Column(name = "hobbies")
    public String hobbies;

    @Column(name = "email", unique = true)
    @Valid
    private String email;

    @Column(name = "interest")
    public String interest;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "available_for_work")
    public boolean availableForWork;


    @Column(name = "password")
    public String password;

    @Column(name = " profile_image_url")
    public String profileImageUrl;

    @Column(name = "twitterId")
    public String twitterId;

    @Column(name = "linkedIn")
    public String linkedIn;

    @Column(name = "pincode")
    public String pincode;

    @Column(name = "activeStatus")
    public boolean activeStatus;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "lastSeen")
    public Time lastSeen;

    @Column(name="current_Designation")
    public String currentDesignation;

    @Column(name="job_Profile")
    public String jobProfile;

    @Column(name="role_In_Hiring")
    public String roleInHiring;

    @Column(name="rating")
    public Integer rating;

    @Lob
    @Column(name = "overview")
    public String overview;

    @Column(name = "currently_Working_Company_Name")
    public String currentlyWorkingCompanyName;

    @Column(name = "joiningDateInCompany")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date joiningDateInCompany;

    @Column(name = "is_candidate")
    public Boolean isCandidate;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType userType;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    public Date createdAt = new Date();

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date updatedAt;
    @Column(name = "specialization")
    public String specialization;
    @Column(name = "yearOfPassing")
    public String yearOfPassing;
    @Column(name = "percentage")
    public BigDecimal percentage;
    @Column(name = "collegeName")
    public String collegeName;
    @Column(name = "universityName")
    public String universityName;
    @Column(name = "schoolName")
    public String schoolName;
    @Column(name = "email_verification_code")
    public String emailVerifyCode;
    @Column(name = "email_verified")
    public boolean emailVerified;
    @Column(name = "role")
    public String role;

    @Column(name = "verification_code" , updatable = true)
    public String verificationCode;


}
