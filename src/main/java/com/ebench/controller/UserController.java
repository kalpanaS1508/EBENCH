package com.ebench.controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.UserReqDto;
import com.ebench.repository.UserRepository;
import com.ebench.service.UserService;
import com.ebench.utils.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;




    //-------------------------------register user----------------------------------------
    @RequestMapping(value = "/register",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity registerUser(@RequestBody UserReqDto userReqDto )
            throws IOException {
            ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, userService.register(userReqDto ), ApiMessage.Api_Message);

            logger.info("[registerUser] user have register!!");
            return apiResponse.getResponse(apiResponse);
        }

//--------------------------------get user-----------------------------------------

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam("id") Long id) throws JsonProcessingException{
        logger.info("Entered in the API.");
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,userService.getUser(id),ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }

//---------------------------------available for work ------------------------------------

    @GetMapping(value = "/availableForWork")
    public ResponseEntity onlineUser(@RequestParam boolean availableForWork, @RequestParam int sort) throws JsonProcessingException{
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,userService.onlineUser(availableForWork,sort),ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }


//-----------------------------------update user----------------------------------------

    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = "application/json")
    public ResponseEntity updateUser(@RequestBody UserReqDto userReqDto) throws JsonProcessingException{
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,userService.updateUser(userReqDto),ApiMessage.Api_Message);

        logger.error("[updateUser] user details have not updated");
        return apiResponse.getResponse(apiResponse);
    }

    @RequestMapping(value = "/upload-image", method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity uploadImage(@RequestParam("profile-image") MultipartFile multipartFile ,@RequestParam("user") String userReqDto ) throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK,true,userService.uploadImage(multipartFile,userReqDto),ApiMessage.Api_Message);

        logger.error("[uploadImage] image have not uploaded");
        return apiResponse.getResponse(apiResponse);
    }
}
