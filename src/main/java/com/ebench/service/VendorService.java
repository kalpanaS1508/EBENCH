package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class VendorService {

    @Autowired
    public VendorRepository vendorRepository;

    private String UPLOAD_DIR = "D://EBench V1//EBENCH//target//classes//Static//image";

    public Vendor Register(Vendor vendor, MultipartFile file) {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(vendor.getEmail())
                .matches();
        System.out.println((emailValidation));
        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(vendor.getPassword())
                .matches();

        System.out.println((pattern));

        Vendor vendor1 = new Vendor();

        try {
            //----------image url save in database code--------------------


                StringBuilder fileName = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + file.getOriginalFilename());
                fileName.append(file.getOriginalFilename());

                Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

                String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));

                System.out.println("file uploaded successfully  " + fileNameAndPath);

                System.out.println(vendor);

            //------------------------------------------------------------------------------

            vendor1.setName(vendor.getName());

            if (!emailValidation) {
                throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
            } else {
                vendor1.setEmail(vendor.getEmail());
            }

            if (pattern != true) {
                throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
            } else {
                vendor1.setPassword(vendor.getPassword());
            }
            vendor1.setAddress(vendor.getAddress());
            vendor1.setDesignation(vendor.getDesignation());
            vendor1.setCity(vendor.getCity());
            vendor1.setCountry(vendor.getCountry());
            vendor1.setStatus(vendor.getStatus());
            vendor1.setLastSeen(vendor.getLastSeen());

            if (vendor.getContactNo().isEmpty() || vendor.getContactNo().length() != 10) {
                throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);

            } else {
                vendor1.setContactNo(vendor.getContactNo());
            }
            vendor1.setRecentActivities(vendor.getRecentActivities());
            vendor1.setRecentDateActivities(vendor.getRecentDateActivities());
            vendor1.setDailyActivities(vendor.getDailyActivities());
            vendor1.setSkypeId(vendor.getSkypeId());
            vendor1.setTwitterId(vendor.getTwitterId());

            vendor1.setVendorProfileImageUrl(fileNameAndPath.toString());

            vendor1.setAvailability(vendor.getAvailability());
            vendor1.setExperience(vendor.getExperience());

            System.out.println("vendor details " + vendor1);
            vendorRepository.save(vendor1);

        } catch (BadReqException e) {
            throw new BadReqException(ApiMessage.PROPER_DETAILS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendor1;
    }

    public Vendor updateVendor(Long vendorId){
        Optional<Vendor> id = vendorRepository.findById(vendorId);
        if(id.isPresent()){
            Vendor vendor = id.get();

        }

    }


}
