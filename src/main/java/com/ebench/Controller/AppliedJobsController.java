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


//    --------------------- LIST OF CANDIDATE WHO APPLIED FOR THE PARTICULAR JOB BY JOB ID----------------------------------------

    @GetMapping(value = "/get_candidate_list_by_applied_job")
    public ResponseEntity getCandidateByJobId(@RequestParam ("job_id") Long jobId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,appliedJobsService.getCandidateListAppliedJobs(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
