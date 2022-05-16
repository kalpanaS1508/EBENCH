package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.VendorJobs;
import com.ebench.service.VendorJobService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.ebench.Apimessage.ApiMessage.Api_Message;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendorJobsController {

    @Autowired
    public VendorJobService vendorJobService;

    @PostMapping(value = "/add_jobs")
    public ResponseEntity addJobs(@RequestBody VendorJobs vendorJobs)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,vendorJobService.addJobs(vendorJobs), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_jobs")
    public ResponseEntity updateJobs(@RequestBody VendorJobs vendorJobs)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,vendorJobService.updateJobs(vendorJobs), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @DeleteMapping(value = "/delete_jobs")
    public ResponseEntity deleteJobs(@RequestParam("id") Long jobId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,vendorJobService.deleteVendorJob(jobId), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}
