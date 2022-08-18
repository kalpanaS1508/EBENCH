package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.utils.ApiResponse;
//import com.ebench.Enums.ChangeTaskStatus;
import com.ebench.entity.Task;
import com.ebench.exception.ResourceNotFoundException;
import com.ebench.service.Taskservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/candidate")
public class TaskController {
    @Autowired
    Taskservice taskservice;

    //-------------------------Get Project List-----------------------

    @RequestMapping(value = "/get_Project_List", method = RequestMethod.GET)
    public ResponseEntity getProjectList(@RequestParam("id") Long candidateId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.getProjectList(candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);

    }
    @RequestMapping(value = "/get_Task", method = RequestMethod.GET)
    public ResponseEntity getTask(@RequestParam("id") Long candidateId ,@RequestParam String taskStartDate) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.getTask(candidateId,taskStartDate ), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PostMapping(value = "/create_Task")
    public ResponseEntity createTask(@RequestBody Task taskmanagement) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.createTask(taskmanagement), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
    @PutMapping(value = "/update_Task")
      public ResponseEntity updateTask( @RequestBody Task taskmanagement) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.updateTask(taskmanagement), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @RequestMapping(value = "/delete_task" , method=RequestMethod.DELETE)
    public ResponseEntity deleteTask(@RequestParam("id") Long taskManagementId) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.deleteTask(taskManagementId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
    @RequestMapping(value = "/get_task_History", method = RequestMethod.GET)
    public ResponseEntity taskHistory(@RequestParam("candidateId") Long candidateId ) throws IOException, ResourceNotFoundException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,taskservice.getTaskHistory(candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @RequestMapping(value = "/get_PendingTask", method = RequestMethod.GET)
    public ResponseEntity getPendingTask(@RequestParam("id") Long candidateId) throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,taskservice.getPendingTask(candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

//    ------------------------doubt on this-----------------------------
    @PostMapping(value = "/pendingFormtask")
    public ResponseEntity pendingFormTask(@RequestParam  Long candidateId) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, taskservice.pendingTaskForm(candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @RequestMapping(value = "/getPendingTaskByProjectName", method = RequestMethod.GET)
    public ResponseEntity getPendingTaskByProjectName(@RequestParam String projectName) throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,taskservice.getPendingTaskByProjectName(projectName), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}