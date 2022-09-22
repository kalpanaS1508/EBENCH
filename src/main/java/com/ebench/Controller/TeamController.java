package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.TeamMember;
import com.ebench.service.TeamMemberService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/ebench")
public class TeamController {

    @Autowired
    TeamMemberService teamMemberService;


    @PostMapping(value = "/add_Team_Member")
    public ResponseEntity addTeam_Member(@RequestBody TeamMember teamMember)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, teamMemberService.createTeam(teamMember), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


    @GetMapping(value = "/get_Team_Member")
    public ResponseEntity getTeam_Member()
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, teamMemberService.getTeam(), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_Team_Member")
    public ResponseEntity update_Team_Member(@RequestBody TeamMember teamMember)
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, teamMemberService.updateTeam(teamMember), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


}

