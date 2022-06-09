package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Utils.ApiResponse;
import com.ebench.dto.CandidateReqDto;
import com.ebench.exception.ResourceNotFoundException;
import com.ebench.repository.CandidateRepository;
import com.ebench.service.CandidateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateService candidateService;

//_________________________________________Register Candidate________________________________________________________________
    @PostMapping(value = "/register_Candidate")
    public ResponseEntity register(@RequestBody CandidateReqDto candidateReqDto )
            throws Exception {
//        CandidateReqDto candidateReqDto1 = new ObjectMapper().readValue(candidateReqDto, CandidateReqDto.class);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.register(candidateReqDto), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PostMapping(value = "/register_Candidate1")
    public ResponseEntity registerCandidate(@RequestPart CandidateReqDto candidateReqDto,@RequestPart MultipartFile file,HttpServletRequest request )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.registerCandidate(candidateReqDto,file,getSiteURL(request)), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


//___________________________________Get Api for candidate_______________________________________________________________________________

    @RequestMapping(value = "/get_Candidate", method = RequestMethod.GET)
    public ResponseEntity getCandidate(@RequestParam("id") Long id) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.getCandidate(id), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

// __________________________________________Update api for Candidate __________________________________________________

    @PutMapping(value = "/update_Candidate")
    public ResponseEntity updatecandidate(@RequestBody CandidateReqDto candidateReqDto)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.updateCandidate(candidateReqDto), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


    // __________________________Delete api for candidate______________________________________________________________



    @RequestMapping(value = "/delete_Candidate" , method=RequestMethod.DELETE)
    public ResponseEntity deletecandidate(@RequestParam("id") Long id) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.deletecandidate(id),
                ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


    // __________________________GET api for candidate__________________________________________________________________

    @RequestMapping(value = "/get_CandidateByVendor", method = RequestMethod.GET)
    public ResponseEntity getCandidate(@RequestParam("experience") String keyExperience , @RequestParam("skills") String skills , @RequestParam("city") String city ,@RequestParam("mobile") String mobile) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.getCandidate(keyExperience, skills, city ,mobile), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    //_________________________________Update api for candidate_____________________________________________________________

    @PutMapping(value = "/update_Candidate1")
    public ResponseEntity updatecandidate1(@RequestPart CandidateReqDto candidateReqDto,@RequestPart MultipartFile file,HttpServletRequest request  )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.updateCandidate1(candidateReqDto,file,getSiteURL(request)), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
//______________________________Email Verification___________________________________________________________________________
    @RequestMapping(value = "/email/verify/", method = RequestMethod.GET)
    public ResponseEntity emailVerify(@RequestParam("uid") Long uid, @RequestParam("code") String code) throws JsonProcessingException, ResourceNotFoundException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateService.emailVerify(uid,code), ApiMessage.Email_Verified);
        return apiResponse.getResponse(apiResponse);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
