package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Valid
@Table(name = "Candidate", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
//@SQLDelete(sql = "UPDATE Candidate SET deleted = true WHERE id=?")
//@Where(clause = "deleted=false")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

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

    @Column(name = "currentDesignation")
    public String currentDesignation;

    @Column(name = "jobProfile")
    public String jobProfile;

    @Lob
    @Column(name = "overview")
    public String overview;

    @Column(name = "currentlyWorkingCompanyName")
    public String CurrentlyWorkingCompanyName;

    @Column(name = "roleInHiring")
    public String roleInHiring;

    @Column(name = "joiningDateInCompany")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date joiningDateInCompany;

    @Column(name = "isCandidate", nullable = false)
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

    public Candidate(Long id, String firstName, String lastName, String keyExperience,
                     String skills, String address, String whatsapp, String city, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.keyExperience = keyExperience;
        this.skills = skills;
        this.address = address;
        this.whatsapp = whatsapp;
        this.city = city;
        this.email = email;
    }

    public Candidate() {
        }


    public Candidate(Long id, String firstName, String lastName, String keyExperience, String skills, String address, boolean deleted,
                     String skypeId, String whatsapp, String country, String state, String city, String hobbies, String email, String interest,
                     String mobile, boolean availableForWork, String password, String profileImageUrl, String twitterId, String linkedIn, String pincode, boolean activeStatus, Time lastSeen, String currentDesignation, String jobProfile, String overview, String currentlyWorkingCompanyName, String roleInHiring, Date joiningDateInCompany, Boolean isCandidate, UserType userType, Date createdAt, Date updatedAt, String specialization,
                     String yearOfPassing, BigDecimal percentage, String collegeName, String universityName, String schoolName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.keyExperience = keyExperience;
        this.skills = skills;
        this.address = address;
        this.deleted = deleted;
        this.skypeId = skypeId;
        this.whatsapp = whatsapp;
        this.country = country;
        this.state = state;
        this.city = city;
        this.hobbies = hobbies;
        this.email = email;
        this.interest = interest;
        this.mobile = mobile;
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
        this.isCandidate = isCandidate;
        this.userType = userType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.specialization = specialization;
        this.yearOfPassing = yearOfPassing;
        this.percentage = percentage;
        this.collegeName = collegeName;
        this.universityName = universityName;
        this.schoolName = schoolName;
    }
}
