package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Utils.VerificationCode;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class VendorService {

    private static final Logger logger = LoggerFactory.getLogger(VendorService.class);

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;


    @Autowired
    public VendorRepository vendorRepository;

    @Autowired
    public JavaMailSender javaMailSender;


    private String UPLOAD_DIR = "D://EBench V1//EBENCH//target//classes//Static//file";

    public Vendor Register(Vendor vendor, MultipartFile file) {

        logger.info("vendor Email should be in format ");

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(vendor.getEmail())
                .matches();

        logger.info("vendor password should be in format ");

        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(vendor.getPassword())
                .matches();

        if (emailAlreadyExist(vendor.getEmail())) {
            logger.info("vendor will get the email and check it is present or not " + vendor.getEmail());
            throw new BadReqException(ApiMessage.EMAIL_IS_PRESENT);
        }

        Path fileNameAndPath = null;
        if (!file.isEmpty()) {

            try {
                StringBuilder fileName = new StringBuilder();

                String filename = file.getOriginalFilename();
                String[] str = filename.split("[.]", 2);
                for (String i : str) {
                    System.out.println(i);
                }

                String fileNameWithTime = str[0] + "_" + System.currentTimeMillis() + "." + str[1];

                fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + fileNameWithTime);

                fileName.append(file.getOriginalFilename());

                Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

                String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));

                logger.info("file uploaded successfully  " + fileNameAndPath);

            } catch (Exception e) {
                System.out.println("Exception occured");
            }

        }

        Vendor vendor1 = new Vendor();
        try {
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
            vendor1.setVendorProfileImageUrl((fileNameAndPath == null) ? "" : fileNameAndPath.toString());
            vendor1.setAvailability(vendor.getAvailability());
            vendor1.setExperience(vendor.getExperience());
            vendor1.setVerificationCode(VerificationCode.getRandomNumberString());

            logger.info("vendor details ", vendor1);
            // vendorRepository.save(vendor1);

            vendorRepository.save(vendor1);
            sendVerificationEmail(vendor1);

        } catch (BadReqException e) {
            throw new BadReqException(e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        logger.info("vendor register yourself successfully" + vendor1);
        return vendor1;
    }


    //-------------------------Email verification code-----------------------------------------------------
    private void sendVerificationEmail(Vendor vendor)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress = vendor.getEmail();
        System.out.println("email address " + toAddress);
        String subject = "Please verify your registration";
        String content = "Dear " + vendor.getName() + "<br>"
                + "Please click the verification code below to verify your registration:<br>"
                + "<h3>" + vendor.getVerificationCode() + "</h3>"
                + "Thank you,<br>"
                + "EBECNCH TEAM.com";


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        message.setContent(content, "text/html");
        javaMailSender.send(message);

    }


    public Boolean emailAlreadyExist(String email) {

//        logger.info("In Email Exist Checking Method " , emailAlreadyExist(email));

        Optional<Vendor> vendor = vendorRepository.findVendorByEmail(email);
        if (vendor.isPresent()) {

            return true;
        } else {

            return false;
        }
    }

//    --------------------------------UPDATE-------------------------------------------


    public Vendor updateVendor(Vendor vendor, MultipartFile file) throws Exception {

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


        Vendor vendor1 = null;
        try {
            Optional<Vendor> id = vendorRepository.findById(vendor.getVendorId());

            vendor1 = null;

            if (!id.isPresent()) {
                logger.info("id is not present firstly you have to register yourself");
                throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
            } else {
                if (emailAlreadyExist(vendor.getEmail())) {
                    logger.info("vendor will get the email and check it is present or not " + vendor.getEmail());
                    throw new BadReqException(ApiMessage.EMAIL_ALREADY_USED);
                }

                id.isPresent();
                vendor1 = id.get();

                Path fileNameAndPath = null;
                if (!file.isEmpty()) {

                    StringBuilder fileName = new StringBuilder();

                    String filename = file.getOriginalFilename();
                    String[] str = filename.split("[.]", 2);
                    for (String i : str) {
                        System.out.println(i);
                    }

                    String fileNameWithTime = str[0] + "_" + System.currentTimeMillis() + "." + str[1];

                    fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + fileNameWithTime);

                    fileName.append(file.getOriginalFilename());

                    Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

                    String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));

                    System.out.println("file uploaded successfully  " + fileNameAndPath);
                }
//---------------------------------------------------------------------------------------------------------------------

                vendor1.setName(vendor.getName());

                if (!emailValidation) {
                    throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
                } else {
                    vendor1.setEmail(vendor.getEmail());
                }
                vendor1.setVendorId(vendor.getVendorId());
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
                vendor1.setVendorProfileImageUrl((fileNameAndPath == null) ? "" : fileNameAndPath.toString());
                vendor1.setExperience(vendor.getExperience());
                vendor1.setAvailability(vendor.getAvailability());
                vendor1.setVerificationCode(VerificationCode.getRandomNumberString());

                logger.info("vendor details are updated by this Id !! " + vendor.getVendorId());

                Vendor save = vendorRepository.save(vendor1);
                sendVerificationEmail(save);

            }
            logger.info("vendor details are updated " + vendor1);
            return vendor1;

        } catch (BadReqException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
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
            logger.info("Not get vendor details by this id " + vendorId);
            throw new BadReqException(ApiMessage.VENDOR_NOT_PRESENT);
        }
    }


// ---------------------------------- DELETE VENDOR---------------------------------------------------------------

    public Vendor deleteVendor(Long  vendorId){
        Optional<Vendor> vendorId1 = vendorRepository.findById(vendorId);
        if(!vendorId1.isPresent()){
            logger.info("vendor is not present by this id "  + vendorId);
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

//    ------------------------- LOGIN VENDOR-----------------------------------------------------------------

        public Vendor login(String email, String password) {

            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            boolean emailValidation = Pattern.compile(regexPattern)
                    .matcher(email)
                    .matches();

            Vendor vendor = vendorRepository.findByEmailAndPassword(email, password);
            try {
                if (email.isEmpty() || !emailValidation) {
                    logger.info("Email is empty");
                    throw new BadReqException(ApiMessage.ENTER_EMAIL);
                }
                if (password.isEmpty() || password.length() < 4) {
                    logger.info("password is empty and password length should be greater than 4");
                    throw new BadReqException(ApiMessage.ENTER_PASSWORD);
                } else if (vendor == null) {
                    throw new BadReqException(ApiMessage.INVALID_CREDENTIAL);
                }
            } catch (Exception e) {
                throw new BadReqException(e.getMessage());
            }
            logger.info("vendor details by login " + vendor);
            return vendor;
        }
}
