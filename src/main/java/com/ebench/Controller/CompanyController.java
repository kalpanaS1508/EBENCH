package com.ebench.Controller;

import com.ebench.entity.AppliedJobs;
import com.ebench.entity.Company;
import com.ebench.service.CompanyService;
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
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping(value = "/add_company")
    public ResponseEntity addCompany(@RequestBody Company company)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,companyService.addCompany(company), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_company")
    public ResponseEntity updateCompany(@RequestBody Company company)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,companyService.updateCompany(company), Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
