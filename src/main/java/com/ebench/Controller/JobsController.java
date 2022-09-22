package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Enums.JobFilter;
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
@RequestMapping(value = "/ebench")
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


//    ---------------------------GET JOB DETAILS BY JOB ID SHOWS ON CANDIDATE SIDE--------------------------------------

    @GetMapping(value = "/get_job_details")
    public ResponseEntity getJobsDetails(@RequestParam("id") Long jobId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getJobsView(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

//    ---------------------------------------- GET JOB LIST BY VENDOR --------------------------------------------------

    @GetMapping(value = "/get_job_list")
    public ResponseEntity getJobsListByVendor(@RequestParam Long vendorId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getJobListByVendor(vendorId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }



    //    ---------------------------------GET NEW JOBS LIST WHICH SHOWS UP ON CANDIDATE SIDE -------------------------------------------------------


    @GetMapping(value = "/get_new_jobs_List")
    public ResponseEntity getNewJobsList(@RequestParam("job_status") boolean jobStatus )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getNewJobsList(jobStatus), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }




//    ---------------------------------GET POSTED JOB RESPONSES -------------------------------------------------------

    @GetMapping(value = "/get_posted_job")
    public ResponseEntity getJobs(@RequestParam("job_status") boolean jobStatus , @RequestParam("vendorId") Long vendorId)
             throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getNumberOfCandidate(jobStatus , vendorId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


    @PutMapping(value = "/update_active_deactive_status")
    public ResponseEntity getJobs(@RequestParam("id") Long jobId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.activeDeactiveStatus(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }



    //_________________Get api for candidate on the basis of job location and job title_______________________________


    @GetMapping(value = "/get_latest_jobs")
    public ResponseEntity getLatestJobs(@RequestParam  String jobDescription, @RequestParam String jobLocation,@RequestParam JobFilter jobFilter)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getJobs_on_location_and_designation(jobDescription,jobLocation,jobFilter), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


    //__________________Get api for candidate to get jobDescription provide by vendor_______________________________________

    @GetMapping(value = "/get_job_description")
    public ResponseEntity getjobDescription(@RequestParam Long jobId )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getJobDescription(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


//    @GetMapping(value = "/get_List_Of_latest_jobs")
//    public ResponseEntity getListOfLatestJobs(@RequestParam  String clientName,@RequestParam String jobLocation,@RequestParam String jobDescription, @RequestParam String skills,@RequestParam String shiftTime )
//            throws IOException {
//        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,jobService.getLatestJob(clientName,jobLocation,jobDescription,skills,shiftTime), Api_Message);
//        return apiResponse.getResponse(apiResponse);
//    }



}

