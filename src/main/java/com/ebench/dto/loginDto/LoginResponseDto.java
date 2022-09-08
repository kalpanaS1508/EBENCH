package com.ebench.dto.loginDto;

import com.ebench.Enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseDto {

    public Long candidateId;
    public Long vendorId;
    public String firstName;
    public String lastName;
    public String email;
    public String mobile;
    public String token;
    public UserType userType;

    public LoginResponseDto(String token , Long candidateId , UserType userType) {

        this.token = token;
        this.candidateId = candidateId;
        this.userType = userType;

    }

    public LoginResponseDto(Long vendorId, String token, UserType userType) {
        this.vendorId = vendorId;
        this.token = token;
        this.userType = userType;
    }

    public LoginResponseDto(Long candidateId, Long vendorId, String firstName, String lastName, String email, String mobile, String token , UserType userType) {
        this.candidateId = candidateId;
        this.vendorId = vendorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.token = token;
        this.userType = userType;
    }
}

