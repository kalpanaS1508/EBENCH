package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class VendorLoginService {

    @Autowired
    public  VendorRepository vendorRepository;

    public Vendor login(String email, String password) {

    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    boolean emailValidation = Pattern.compile(regexPattern)
            .matcher(email)
            .matches();

     Vendor vendor = vendorRepository.findByEmailAndPassword(email, password);
    try {
        if (email.isEmpty() || !emailValidation) {
            throw new BadReqException(ApiMessage.ENTER_EMAIL);
        }
        if (password.isEmpty() || password.length() < 4) {
            throw new BadReqException(ApiMessage.ENTER_PASSWORD);
        } else if (vendor == null) {
            throw new BadReqException(ApiMessage.INVALID_CREDENTIAL);
        }
    } catch (Exception e) {
        throw new BadReqException(e.getMessage());
    }
    return vendor;
}


}
