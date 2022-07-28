package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.utils.ApiResponse;
import com.ebench.entity.Project;
import com.ebench.exception.ProjectNotFoundException;
import com.ebench.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/create_Project")
    public ResponseEntity createProject (@RequestBody Project project) throws ProjectNotFoundException, JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,projectService.createProject(project), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_Project")
    public ResponseEntity updateProject( @RequestBody Project project,@RequestParam("id") Long id) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,projectService.updateProject(project,id), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @RequestMapping(value = "/get_Project", method = RequestMethod.GET)
    public ResponseEntity getProject(@RequestParam("id") Long id) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, projectService.getProject(id), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


    @RequestMapping(value = "/delete_Project" , method=RequestMethod.DELETE)
    public ResponseEntity deleteProject(@RequestParam("id") Long id) throws JsonProcessingException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,projectService.deleteProject(id), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
