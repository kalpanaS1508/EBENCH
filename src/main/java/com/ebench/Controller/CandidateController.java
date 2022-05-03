package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.repository.CandidateRepository;
import com.ebench.service.CandidateService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateService candidateService;
//_____________________________________Register api for candidate__________________________________________________________________________


    @PostMapping(value = "/registercandidate")
    public ResponseEntity register(@RequestParam("file") MultipartFile file, String candidateReqDto)
            throws IOException {
      CandidateReqDto candidateReqDto1=new ObjectMapper().readValue(candidateReqDto,CandidateReqDto.class);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.register(candidateReqDto1,file),ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}
