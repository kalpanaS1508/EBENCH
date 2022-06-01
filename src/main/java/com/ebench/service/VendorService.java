package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.Candidate;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class VendorService {

    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);

    @Autowired
    public VendorRepository vendorRepository;

//    private String UPLOAD_DIR = "D://EBench V1//EBENCH//target//classes//Static//image";

    public Vendor Register(Vendor vendor) throws Exception {

        logger.info("vendor Email should be in format ");

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(vendor.getEmail())
                .matches();
        System.out.println((emailValidation));

        logger.info("vendor password should be in format ");

        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(vendor.getPassword())
                .matches();

        logger.info("pattern " + pattern);

        if (emailAlreadyExist(vendor.getEmail())) {
            throw new BadReqException(ApiMessage.EMAIL_IS_PRESENT);
        }

        Vendor vendor1 = new Vendor();

        try {
            //----------image url save in database code--------------------


//            StringBuilder fileName = new StringBuilder();
//            Path fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + file.getOriginalFilename());
//            fileName.append(file.getOriginalFilename());
//
//            Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);
//
//            String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));
//
//            System.out.println("file uploaded successfully  " + fileNameAndPath);
//
//            System.out.println(vendor);

            //------------------------------------------------------------------------------

            vendor1.setName(vendor.getName());

            if (!emailValidation) {
                throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
            }else {
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
            vendor1.setStatus(vendor.isStatus());
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

            vendor1.setVendorProfileImageUrl(vendor.getVendorProfileImageUrl());

            vendor1.setAvailability(vendor.getAvailability());
            vendor1.setExperience(vendor.getExperience());

            System.out.println("vendor details " + vendor1);
            vendorRepository.save(vendor1);

        } catch (BadReqException e) {
            throw new BadReqException(e.getMessage());

        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        return vendor1;
    }

    public Boolean emailAlreadyExist(String email) {

        System.out.println("In Email Exist Checking Method");

        Optional<Vendor> vendor = vendorRepository.findVendorByEmail(email);
        if (vendor.isPresent()) {
            System.out.println("True");
            return true;
        } else {
            System.out.println("False");
            return false;
        }
    }

//    --------------------------------UPDATE-------------------------------------------

    public Vendor updateVendor(Vendor vendor) throws Exception {
        System.out.println(vendor.getVendorId());

        Optional<Vendor> id = vendorRepository.findById(vendor.getVendorId());

        Vendor vendor1 = null;

            if(!id.isPresent()){
                throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
            }
            else{
                id.isPresent();
                vendor1 = id.get();

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

                if (emailAlreadyExist(vendor.getEmail())) {
                    throw new BadReqException(ApiMessage.EMAIL_IS_PRESENT);
                }

                try {
                    vendor1.setName(vendor.getName());

                    if (!emailValidation) {
                        throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
                    } else {
                        vendor1.setEmail(vendor.getEmail());
                    }
                    vendor1.setAddress(vendor.getAddress());
                    vendor1.setPassword(vendor.getPassword());
                    vendor1.setDesignation(vendor.getDesignation());
                    vendor1.setCity(vendor.getCity());
                    vendor1.setCountry(vendor.getCountry());
                    vendor1.setStatus(vendor.isStatus());
                    vendor1.setTwitterId(vendor.getTwitterId());
                    vendor1.setSkypeId(vendor.getSkypeId());
                    vendor1.setLastSeen(vendor.getLastSeen());

                    if (vendor.getContactNo().isEmpty() || vendor.getContactNo().length() != 10) {
                        throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
                    } else {
                        vendor1.setContactNo(vendor.getContactNo());
                    }

                    vendor1.setRecentActivities(vendor.getRecentActivities());
                    vendor1.setRecentDateActivities(vendor.getRecentDateActivities());
                    vendor1.setDailyActivities(vendor.getDailyActivities());
                    vendor1.setVendorProfileImageUrl(vendor.getVendorProfileImageUrl());
                    vendor1.setExperience(vendor.getExperience());
                    vendor1.setAvailability(vendor.getAvailability());

                    vendorRepository.save(vendor1);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        return vendor1;
    }

// ---------------------------------- GET VENDOR ---------------------------------------------------------------

    public Vendor getVendor(Long vendorId){
        Optional<Vendor> id = vendorRepository.findById(vendorId);
        if(id.isPresent()){
            Vendor vendor = id.get();
            return vendor;
        }
        else{
            throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
        }
    }


// ---------------------------------- DELETE VENDOR---------------------------------------------------------------

    public Vendor deleteVendor(Long  vendorId){
        Optional<Vendor> vendorId1 = vendorRepository.findById(vendorId);
        if(!vendorId1.isPresent()){
            throw new RuntimeException(ApiMessage.VENDOR_NOT_PRESENT);
        } else{
            vendorId1.isPresent();
            Vendor vendor = vendorId1.get();
              vendorRepository.deleteById(vendor.getVendorId());
        }
        return null;
    }

//----------------------------SOFT DELETE-------------------------------------------------------

    public Vendor softDeleteVendor(Long vendorId) {
        Optional<Vendor> candidate1 = vendorRepository.findById(vendorId);
        Vendor vendor= null;
        if(candidate1.isPresent())
        {
            vendor = candidate1.get();
        }
        else
        {
            throw new UserNotFoundException(ApiMessage.VENDOR_NOT_PRESENT);
        }
        vendor.setStatus(false);

        vendorRepository.save(vendor);

        return vendor ;
    }

}
