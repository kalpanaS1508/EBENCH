package com.ebench.Controller;

import com.ebench.entity.AppliedJobs;
import com.ebench.entity.Jobs;
import com.ebench.service.AppliedJobsService;
import com.ebench.service.JobService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.ebench.Apimessage.ApiMessage.Api_Message;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/ebench")
public class AppliedJobsController {

    @Autowired
    public AppliedJobsService appliedJobsService;

    @PostMapping(value = "/applied_jobs")
    public ResponseEntity appliedjobs(@RequestBody AppliedJobs appliedJobs)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,appliedJobsService.create(appliedJobs), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @GetMapping(value = "/applied_resume")
    public ResponseEntity getResumeCount(@RequestParam ("job_id") Long jobId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,appliedJobsService.getCountResume(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}
