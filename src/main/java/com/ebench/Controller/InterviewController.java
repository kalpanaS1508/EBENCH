package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Interview;
import com.ebench.service.InterviewService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ebench")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping(value = "/get_interview")
    public ResponseEntity getInterviewHistory(@RequestParam ("status") String hiringStatus)
            throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, interviewService.getInterviewHistory(hiringStatus), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @GetMapping(value = "/update_Status")
    public ResponseEntity updateStatus(@RequestParam Long interviewId,Long candidateId)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, interviewService.updateStatus(interviewId,candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
    @GetMapping(value = "/update_Teammember_status")
    public ResponseEntity update_Teamamember_Apply_status(@RequestParam Long interviewId,Long teamMemberId )
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, interviewService.update_Teamamember_Apply_status(interviewId,teamMemberId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}
