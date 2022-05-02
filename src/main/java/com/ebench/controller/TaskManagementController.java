package com.ebench.controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.TaskManagementReqDto;
import com.ebench.entity.User;
import com.ebench.service.TaskManagementService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/taskmanagement")
public class TaskManagementController {

    @Autowired
    public TaskManagementService taskManagementService;

    @PostMapping(value = "/add")
    public ResponseEntity addTask(@RequestBody TaskManagementReqDto taskManagementReqDto,
                                  @RequestParam Long loginUserId) throws JsonProcessingException{
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,taskManagementService.addTaskManagement(taskManagementReqDto,loginUserId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity updateTask(@RequestBody TaskManagementReqDto taskManagementReqDto) throws JsonProcessingException{
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,taskManagementService.updateTaskmanagement(taskManagementReqDto),ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


}
