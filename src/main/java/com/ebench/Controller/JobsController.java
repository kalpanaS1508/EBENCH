package com.ebench.Controller;

import com.ebench.entity.Jobs;
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
@RequestMapping(value = "/candidate")
public class JobsController {

    @Autowired
    public JobService jobService;

    @PostMapping(value = "/add_jobs")
    public ResponseEntity addJobs(@RequestBody Jobs jobs)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.addJobs(jobs), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_jobs")
    public ResponseEntity updateJobs(@RequestBody Jobs jobs)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.updateJobs(jobs), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @DeleteMapping(value = "/delete_jobs")
    public ResponseEntity deleteJobs(@RequestParam("id") Long jobId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.deleteJob(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @GetMapping(value = "/get_status")
    public ResponseEntity getJobs(@RequestParam("id") Long vendorId)
             throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getJobStatus(vendorId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/get_active_deactive_status")
    public ResponseEntity getJobs(@RequestParam("id") Long jobId , @RequestParam("status") boolean jobStatus)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.activeDeactiveStatus(jobId, jobStatus), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}

