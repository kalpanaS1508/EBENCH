package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Vendor;
import com.ebench.service.VendorService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/vendor")
@CrossOrigin("*")
public class VendorController {

    @Autowired
    public VendorService vendorService;


//   --------------------------- vendor registration-----------------------------------------

    @PostMapping(value = "/registervendor")
    public ResponseEntity Register(@RequestParam("file") MultipartFile file , @RequestParam("vendor") String vendor)
            throws IOException {
        Vendor vendor1= new ObjectMapper().readValue(vendor,Vendor.class);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.Register(vendor1,file), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}

