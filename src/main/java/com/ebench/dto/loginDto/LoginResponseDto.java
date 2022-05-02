package com.ebench.dto.loginDto;

import com.ebench.entity.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseDto {

    public Long id;
    public String name;
    public String keyExperience;
    public String skills;
    public String address;
    public String skypeId;
    public String whatsapp;
    public String country;
    public String state;
    public String city;
    public String hobbies;
    private String email;
    public String interest;
    public String mobile;
    public boolean availableForWork;
    public String password;
    public String profileImageUrl;
    public UserType userType;

    public LoginResponseDto(Long id, String name, String keyExperience, String skills, String address, String skypeId,
                            String whatsapp, String country, String state, String city, String hobbies, String email, String interest,
                            String mobile, boolean availableForWork, String password, String profileImageUrl, UserType userType) {
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
}

