package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Config.JwtTokenUtil;
import com.ebench.Enums.UserType;
import com.ebench.dto.loginDto.LoginRequestDto;
import com.ebench.exception.BadReqException;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.VendorRepository;
import com.ebench.service.CandidateService;
import com.ebench.service.VendorService;

import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    CandidateService candidateService;
    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    VendorService vendorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @PostMapping(value = "/login" , produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto  )

            throws Exception {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(loginRequestDto.getEmail())
                .matches();
        System.out.println("Email Validate: " + emailValidation);
        if (emailValidation) {

            ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.login(loginRequestDto), ApiMessage.Api_Message);
            return apiResponse.getResponse(apiResponse);
        }
        else {
            throw new BadReqException(ApiMessage.Enter_VALID_EMAIL);
        }

    }



}