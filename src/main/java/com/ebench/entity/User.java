package com.ebench.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@Valid
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name="key_experience")
    public String keyExperience;

    @Column(name="skills")
    public String skills;

    @Column(name = "address")
    public String address;

    @Column(name ="skype_id")
    public String skypeId;

    @Column(name ="whatsapp")
    public String whatsapp;

    @Column(name = "country")
    public String country;

    @Column(name = "state")
    public String state;

    @Column(name ="city")
    public String city;

    @Column(name ="hobbies")
    public String hobbies;

    @Column(name = "email",unique = true)
    @Valid
    private String email;

    @Column(name = "interest")
    public String interest;

    @Column(name="mobile")
    public String mobile;

    @Column(name = "available_for_work")
    public boolean availableForWork;

    @Column(name = "password")
    public String password;


    @Column(name = " profile_image_url" , columnDefinition = "LONGBLOB")
    public String profileImageUrl;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType userType;


    @CreatedDate
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;


    @PrePersist
    protected void prePerist() {
        if (this.createdAt == null) createdAt = LocalDateTime.now();
        if (this.updatedAt == null) updatedAt = LocalDateTime.now();
    }


    public User(Long id, String name, String keyExperience, String skills) {
        this.id = id;
        this.name = name;
        this.keyExperience = keyExperience;
        this.skills = skills;
    }

    public User(Long id, String name, String keyExperience, String skills, String address, String skypeId, String whatsapp,
                String country, String state, String city, String hobbies, String email, String interest, String mobile, boolean availableForWork,
                String password, String profileImageUrl, UserType userType) {
        this.id = id;
        this.name = name;
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
        this.availableForWork = availableForWork;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.userType = userType;

    }

    public User() {
    }

}
