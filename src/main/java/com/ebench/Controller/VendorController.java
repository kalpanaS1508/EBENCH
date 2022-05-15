package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.Vendor;
import com.ebench.service.VendorService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/vendor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendorController {

    @Autowired
    public VendorService vendorService;


//   ---------------------------VENDOR REGISTRATION-----------------------------------------

    @PostMapping(value = "/registervendor")
    public ResponseEntity Register(@RequestBody Vendor vendor)
            throws IOException {

//        Vendor vendor1= new ObjectMapper().readValue(vendor,Vendor.class);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.Register(vendor), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PostMapping(value = "/sample")
    public String TestApi(@RequestParam("name") String name){
        return String.format("%s", name);
    }

//    --------------------------------VENDOR GET API-----------------------------------------------

    @RequestMapping(value = "/get_vendor", method = RequestMethod.GET)
    public ResponseEntity getVendor(@RequestParam("id") Long vendorId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.getVendor(vendorId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


//    --------------------------------VENDOR UPDATE API-----------------------------------------------

    @PutMapping(value = "/update_vendor")
    public ResponseEntity updateVendor(@RequestBody Vendor vendor)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.updateVendor(vendor), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);

    }

//    --------------------------VENDOR DELETE API----------------------------------------------------------

    @RequestMapping(value = "/delete_vendor" , method=RequestMethod.DELETE)
    public ResponseEntity deleteContact(@RequestParam("id") Long vendorId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.deleteVendor(vendorId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

//    --------------------------VENDOR SOFT DELETE API----------------------------------------------------------

    @RequestMapping(value = "/soft_delete_vendor" , method=RequestMethod.DELETE)
    public ResponseEntity softDeleteContact(@RequestParam("id") Long vendorId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorService.softDeleteVendor(vendorId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}

