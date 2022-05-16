package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.ChangeTaskStatus;
import com.ebench.entity.Taskmanagement;
import com.ebench.entity.UserType;
import com.ebench.service.Taskservice;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
    @Autowired
    Taskservice taskservice;

    //-------------------------Get Project List-----------------------

    @RequestMapping(value = "/getProjects", method = RequestMethod.GET)
    public ResponseEntity getProjects(@RequestParam("id") Long candidateId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.getProjectList(candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);

    }
    @RequestMapping(value = "/getTask", method = RequestMethod.GET)
    public ResponseEntity getTask(@RequestParam("id") Long id, @RequestParam String projectName, @RequestParam ChangeTaskStatus changeTaskStatus) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.getTask(id,projectName,changeTaskStatus), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PostMapping(value = "/createTask")
    public ResponseEntity createTask(@RequestBody Taskmanagement taskmanagement) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.createTask(taskmanagement), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
    @PutMapping(value = "/updateTask")
      public ResponseEntity updateTask( @RequestBody Taskmanagement taskmanagement,@RequestParam("id") Long taskManagementId) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.updateTask(taskmanagement,taskManagementId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @RequestMapping(value = "/deleteTask" , method=RequestMethod.DELETE)
        public ResponseEntity deleteTask(@RequestParam("id") Long taskManagementId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,taskservice.deleteTask(taskManagementId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}