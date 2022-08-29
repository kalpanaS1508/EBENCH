package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.JobWorkOrder;
import com.ebench.entity.Vendor;
import com.ebench.service.JobWorkOrderService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/ebench")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobWorkOrderController {

    @Autowired
    JobWorkOrderService jobWorkOrderService;

    @PostMapping(value = "/add_work_order" )
    public ResponseEntity createWorkOrder(@RequestBody JobWorkOrder jobWorkOrder)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, jobWorkOrderService.createWorkOrder(jobWorkOrder), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_work_order")
    public ResponseEntity updateWorkOrder(@RequestBody JobWorkOrder jobWorkOrder)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, jobWorkOrderService.updateWorkOrder(jobWorkOrder), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @DeleteMapping(value = "/delete_work_order")
    public ResponseEntity deleteWorkOrder(@RequestParam("id") Long jobWorkOrderId)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, jobWorkOrderService.deleteWorkOrder(jobWorkOrderId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


}
