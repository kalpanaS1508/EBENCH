package com.ebench.Controller;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Utils.ApiResponse;
import com.ebench.service.VendorLoginService;
import com.ebench.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendorLoginController {

    @Autowired
    public VendorLoginService vendorLoginService;

    @GetMapping(value = "/login_vendor")
    public ResponseEntity loginVendor(@RequestParam String email, @RequestParam String password )
            throws IOException {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, true, vendorLoginService.login(email,password), ApiMessage.Api_Message);
        return apiResponse.getResponse(apiResponse);
    }
}


