package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.repository.CandidateRepository;
import com.ebench.service.CandidateService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateService candidateService;
//_____________________________________Register api for candidate__________________________________________________________________________


    @PostMapping(value = "/registercandidate")
    public ResponseEntity register(@RequestBody CandidateReqDto candidateReqDto )
            throws IOException {
//        CandidateReqDto candidateReqDto1 = new ObjectMapper().readValue(candidateReqDto, CandidateReqDto.class);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.register(candidateReqDto), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
//___________________________________Get Api for candidate_______________________________________________________________________________

    @RequestMapping(value = "/getCandidate", method = RequestMethod.GET)
    public ResponseEntity getCandidate(@RequestParam("id") Long id) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.getCandidate(id), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

// __________________________________________Update api for Candidate __________________________________________________________________

    @PutMapping(value = "/updatecandidate")
    public ResponseEntity updatecandidate(@RequestParam("file") MultipartFile file, String candidateReqDto)
            throws IOException {
        CandidateReqDto candidateReqDto1 = new ObjectMapper().readValue(candidateReqDto, CandidateReqDto.class);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.updateCandidate(candidateReqDto1, file), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);

    }
    // __________________________Delete api for candidate______________________________________________________________
    @RequestMapping(value = "/deletecandidate" , method=RequestMethod.DELETE)
    public ResponseEntity deletecandidate(@RequestParam("id") Long id) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.deletecandidate(id), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
