package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Config.JwtTokenUtil;
import com.ebench.Utils.ApiResponse;
import com.ebench.entity.Candidate;
import com.ebench.entity.UserType;
import com.ebench.exception.BadReqException;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.VendorRepository;
import com.ebench.service.CandidateService;
import com.ebench.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


    @GetMapping(value = "/loginCandidate")
    public ResponseEntity login(@RequestParam String email, String password, UserType userType)
            throws Exception {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
        System.out.println("Email Validate: " + emailValidation);
        if (emailValidation) {

            ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.login(email, password, userType), ApiMessage.Api_Message);
            return apiResponse.getResponse(apiResponse);
        } else {
            throw new BadReqException(ApiMessage.Enter_VALID_EMAIL);
        }

    }



}
