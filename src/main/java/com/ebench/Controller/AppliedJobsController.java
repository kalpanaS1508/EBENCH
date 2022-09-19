package com.ebench.Controller;

import com.ebench.dto.EmailrequestDto;
import com.ebench.entity.AppliedJobs;
import com.ebench.entity.Jobs;
import com.ebench.entity.Notification;
import com.ebench.service.AppliedJobsService;
import com.ebench.service.JobService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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


 //______________________Update_Job_Status_______________________________________________________________________//
    @GetMapping(value = "/update_job_status")
    public ResponseEntity updateJobStatus(@RequestParam ("id") Long appliedJobsId,@RequestParam Long candidateId )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,appliedJobsService.updateStatus(appliedJobsId,candidateId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    //______________________________________createNotification______________________________________________________//
    @PostMapping(value = "/createNotification")
    public ResponseEntity createNotification (@RequestBody Notification notification)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,appliedJobsService.createNotification(notification), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

















}
