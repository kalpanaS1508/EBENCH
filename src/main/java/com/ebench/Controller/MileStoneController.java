package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Interview;
import com.ebench.entity.MileStone;
import com.ebench.repository.MileStoneRepository;
import com.ebench.service.MileStoneService;
import com.ebench.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ebench")
public class MileStoneController {

    @Autowired
    MileStoneService mileStoneService;

    @PostMapping(value = "/add_sprint")
    public ResponseEntity addSprints(@RequestBody MileStone mileStone)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,mileStoneService.addSprint(mileStone) , ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @PutMapping(value = "/update_sprint")
    public ResponseEntity updateSprints(@RequestBody MileStone mileStone)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,mileStoneService.updateSprint(mileStone) , ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @DeleteMapping(value = "/delete_sprint")
    public ResponseEntity deleteSprint(@RequestParam Long sprintId)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,mileStoneService.deleteMileStone(sprintId) , ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

    @GetMapping(value = "/get_sprint")
    public ResponseEntity getSprint(@RequestParam("mile stone") String mileStoneName)
            throws Exception {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true,mileStoneService.getTotalSprints(mileStoneName) , ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

}
