package com.ebench.controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.service.VendorService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vendor")
@CrossOrigin("*")
public class VendorController {

    @Autowired
    public VendorService vendorService;

    @RequestMapping(value = "/getcandidate",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity getCandidateByskills(@RequestParam("skills") String skills ,@RequestParam("keyExperience") String keyExperience, @RequestParam("state") String state) throws JsonProcessingException{
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,vendorService.getCandidateBySkills(skills,keyExperience,state), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
