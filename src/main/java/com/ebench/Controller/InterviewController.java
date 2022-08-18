package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.Interview;
import com.ebench.service.InterviewService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/interview")
public class InterviewController {

    @Autowired
    InterviewService interviewService;

    @PostMapping(value = "/add_interview")
    public ResponseEntity createInterview(@RequestBody Interview interview)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, interviewService.createInterview(interview), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_interview")
    public ResponseEntity updateInterview(@RequestBody Interview interview)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, interviewService.updateInterview(interview), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}
