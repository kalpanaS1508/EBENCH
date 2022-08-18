package com.ebench.dto.loginDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseDto {

   public Long candidateId;
   public String firstName;
    public String lastName;
    private String email;
    public String mobile;
    public String token;

    public LoginResponseDto(String token , Long candidateId) {

        this.token = token;
        this.candidateId = candidateId;

    }

    public LoginResponseDto(Long candidateId, String firstName, String lastName, String email, String mobile, String token) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.token = token;
    }
}

