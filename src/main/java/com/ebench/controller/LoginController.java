package com.ebench.controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.service.LoginService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class LoginController {

    @Autowired
     private LoginService loginService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)

    public ResponseEntity userLogin(@RequestParam("email") String email, @RequestParam ("password") String password) throws JsonProcessingException {
        logger.info("Entered in the API.");
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true, loginService.loginUser(email, password), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}
