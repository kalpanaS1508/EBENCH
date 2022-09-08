package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.CandidateSkills;
import com.ebench.service.CandidateSkillsService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebench")
public class CandidateSkillsController {

    @Autowired
    CandidateSkillsService candidateSkillsService;

    @PostMapping(value = "/add_skills")
    public ResponseEntity addSkills(@RequestBody CandidateSkills candidateSkills)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateSkillsService.addSkills(candidateSkills), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_skills")
    public ResponseEntity updateSkills(@RequestBody CandidateSkills candidateSkills)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateSkillsService.updateSkills(candidateSkills), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @GetMapping(value = "/get_candidate_skills_list")
    public ResponseEntity getSkills(@RequestParam( "id") Long candidateId)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, candidateSkillsService.getCandidateSkillList(candidateId), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
