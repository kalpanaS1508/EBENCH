package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.VendorRepository;
import com.ebench.service.CandidateService;
import com.ebench.service.VendorService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @GetMapping (value = "/loginCandidate")
    public ResponseEntity login(@RequestParam String email,@RequestParam String password,boolean isCandidate  )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.login(email,password,isCandidate), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
    @GetMapping (value = "/loginVendor")
    public ResponseEntity loginVendor(@RequestParam String email,@RequestParam String password )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.login(email,password), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
