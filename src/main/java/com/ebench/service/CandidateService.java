package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.Config.JwtTokenUtil;
import com.ebench.dto.CandidateReqDto;
import com.ebench.dto.loginDto.LoginResponseDto;
import com.ebench.entity.Candidate;
import com.ebench.Enums.UserType;
import com.ebench.entity.Vendor;
import com.ebench.exception.BadReqException;
import com.ebench.exception.ResourceNotFoundException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.VendorRepository;
import com.ebench.utils.Common;
import com.ebench.utils.GlobalResources;
import com.ebench.utils.VerificationCode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.Pattern;

@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CandidateService {

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired

    AuthenticationManager authenticationManager;


    private Logger logger = GlobalResources.getlogger(CandidateService.class);

    // __________________________________ Register Api for Candidate__________________________________________//


    private String UPLOAD_DIR = "D://EBench V1//EBENCH//src//main//resources//Static//file";

    public CandidateReqDto register(CandidateReqDto candidateReqDto) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(candidateReqDto.getEmail())
                .matches();
        System.out.println((emailValidation));
        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(candidateReqDto.getPassword())
                .matches();
        System.out.println((pattern));

        if (emailAlreadyExist(candidateReqDto.getEmail())) {
            logger.error("This Email already used please insert new email");
            throw new BadReqException(ApiMessage.EMAIL_ALREADY_USED);
        }
        try {

            Candidate candidate = new Candidate();
            candidate.setFirstName(candidateReqDto.getFirstName());
            candidate.setLastName(candidateReqDto.getLastName());
            candidate.setKeyExperience(candidateReqDto.getKeyExperience());
            candidate.setSkills(candidateReqDto.getSkills());
            candidate.setAddress(candidateReqDto.getAddress());
            candidate.setSkypeId(candidateReqDto.getSkypeId());
            candidate.setWhatsapp(candidateReqDto.getWhatsapp());
            candidate.setCountry(candidateReqDto.getCountry());
            candidate.setState(candidateReqDto.getState());
            candidate.setCity(candidateReqDto.getCity());
            candidate.setHobbies(candidateReqDto.getHobbies());
            if (!emailValidation) {
                logger.error("emailvalidation: Email is in not proper format");
                throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
            } else {
                candidate.setEmail(candidateReqDto.getEmail());
            }
            candidate.setInterest(candidateReqDto.getInterest());

                if (candidateReqDto.getMobile().isEmpty() || candidateReqDto.getMobile().length() != 10) {
                    logger.error("mobile number validating");
                    throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
                } else {
                    candidate.setMobile(candidateReqDto.getMobile());
                }
//            }


            candidate.setAvailableForWork(candidateReqDto.isAvailableForWork());

            if (pattern != true) {
                throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
            }
            else {
                candidate.setPassword(candidateReqDto.getPassword());
            }

            candidate.setUserType(candidateReqDto.getUserType());
            candidate.setDeleted(candidateReqDto.isDeleted());
            candidate.setTwitterId(candidateReqDto.getTwitterId());
            candidate.setLinkedIn(candidateReqDto.getLinkedIn());
            candidate.setPincode(candidateReqDto.getPincode());
            candidate.setActiveStatus(candidateReqDto.isActiveStatus());
            candidate.setLastSeen(candidateReqDto.getLastSeen());
            candidate.setCurrentDesignation(candidateReqDto.getCurrentDesignation());
            candidate.setJobProfile(candidateReqDto.getJobProfile());
            candidate.setOverview(candidateReqDto.getOverview());
            candidate.setCurrentlyWorkingCompanyName(candidateReqDto.getCurrentlyWorkingCompanyName());
            candidate.setRoleInHiring(candidateReqDto.getRoleInHiring());
            candidate.setJoiningDateInCompany(candidateReqDto.getJoiningDateInCompany());
            candidate.setSpecialization(candidateReqDto.getSpecialization());
            candidate.setYearOfPassing(candidateReqDto.getYearOfPassing());
            candidate.setPercentage(candidateReqDto.getPercentage());
            candidate.setCollegeName(candidateReqDto.getCollegeName());
            candidate.setUniversityName(candidateReqDto.getUniversityName());
            candidate.setSchoolName(candidateReqDto.getSchoolName());
            candidate.setRating(candidateReqDto.getRating());
            candidate.setIsCandidate(candidateReqDto.isCandidate());
            candidate.setInterviewId(candidateReqDto.getInterviewId());

            candidateRepository.save(candidate);
        } catch (BadReqException e) {
            logger.error("candidate not saved____________________>-_____________");
            throw new BadReqException(e.getMessage());
        }
        logger.info("calling method Registration:return whole data of candidate");
        return candidateReqDto;
    }

    public Boolean emailAlreadyExist(String email) {
        System.out.println("In Email Exist Checking Method");
        logger.info(" fetching candidate details from candidateRepository where candidate email is " + email);
        Optional<Candidate> candidate = candidateRepository.findUserByEmail(email);
        if (candidate.isPresent()) {
            System.out.println("True");
            logger.error("Candidate with this email " + email + " with id " + candidate.get().candidateId + " is already present ");

            return true;
        } else {
            System.out.println("False");
            return false;
        }
    }

    // ______________________________Get Api for Candidate _____________________________________________________________________//

    public Candidate getCandidate(Long id) {
        logger.info(" get candidate details from candidate repository " + " by this id " + id);
        Optional<Candidate> user = candidateRepository.findById(id);
        Candidate candidate1 = null;
        try {
            if (user.isPresent()) {

                candidate1 = user.get();
            } else {
                logger.error("user not present");
                throw new UserNotFoundException(ApiMessage.User_Not_Present);
            }
        } catch (UserNotFoundException e) {
            logger.error("user not found");
            throw new UserNotFoundException(e.getMessage());
        }
        return candidate1;
    }

    // ____________________________________Update Api for candidate Registration _______________________________________//

    public CandidateReqDto updateCandidate(CandidateReqDto candidateReqDto) {

        logger.info("In update candidate method : fetching details from candidate by id" + candidateReqDto.getCandidateId());

        Optional<Candidate> candidate = candidateRepository.findById(candidateReqDto.getCandidateId());

        Candidate candidate1=null;

        try {
            if(candidateReqDto.getEmail() !=null) {
                String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
                boolean emailValidation = Pattern.compile(regexPattern)
                        .matcher(candidateReqDto.getEmail())
                        .matches();
                System.out.println((emailValidation));
            }

            if(candidateReqDto.getPassword() !=null) {
                String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
                boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                        .matcher(candidateReqDto.getPassword())
                        .matches();
                System.out.println((pattern));
            }

            if(candidate.isPresent()) {

                candidate1 = candidate.get();

                if(candidateReqDto.getFirstName() !=null){
                candidate1.setFirstName(candidateReqDto.getFirstName());
                }

                if(candidateReqDto.getLastName() !=null){
                candidate1.setLastName(candidateReqDto.getLastName());
                }

                if(candidateReqDto.getKeyExperience() !=null) {
                    candidate1.setKeyExperience(candidateReqDto.getKeyExperience());
                }

                if(candidateReqDto.getSkills()!=null) {
                    candidate1.setSkills(candidateReqDto.getSkills());
                }
                if(candidateReqDto.getAddress() !=null) {
                    candidate1.setAddress(candidateReqDto.getAddress());
                }

                if(candidateReqDto.getSkypeId() !=null) {
                    candidate1.setSkypeId(candidateReqDto.getSkypeId());
                }

                if(candidateReqDto.getWhatsapp() !=null) {
                    candidate1.setWhatsapp(candidateReqDto.getWhatsapp());
                }

                if(candidateReqDto.getCountry() !=null) {
                    candidate1.setCountry(candidateReqDto.getCountry());
                }

                if(candidateReqDto.getState() !=null) {
                    candidate1.setState(candidateReqDto.getState());
                }

                if(candidateReqDto.getCity() !=null) {
                    candidate1.setCity(candidateReqDto.getCity());
                }

                if(candidateReqDto.getHobbies() !=null) {
                    candidate1.setHobbies(candidateReqDto.getHobbies());
                }
//                if (!emailValidation) {
//                    throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
//
//                } else
                    if(candidateReqDto.getEmail() !=null) {
                    candidate1.setEmail(candidateReqDto.getEmail());
                }
                 if(candidateReqDto.getInterest() !=null) {
                     candidate1.setInterest(candidateReqDto.getInterest());
                 }
               if(candidateReqDto.getMobile()!=null){

                if (candidateReqDto.getMobile().isEmpty() || candidateReqDto.getMobile().length() != 10) {
                    throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
                }

                } else if(candidateReqDto.getMobile() !=null) {

                    candidate1.setMobile(candidateReqDto.getMobile());
                }
                    candidate1.setAvailableForWork(candidateReqDto.isAvailableForWork());

//
//                if (pattern != false) {
//                    throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
//                }
//                else

                    if(candidateReqDto.getPassword() !=null){
                    candidate1.setPassword(candidateReqDto.getPassword());
                }

                if(candidateReqDto.getUserType() !=null) {
                    candidate1.setUserType(candidateReqDto.getUserType());
                }

                if(candidateReqDto.getTwitterId() !=null) {
                    candidate1.setTwitterId(candidateReqDto.getTwitterId());
                }

                if(candidateReqDto.getLinkedIn() !=null) {
                    candidate1.setLinkedIn(candidateReqDto.getLinkedIn());
                }

                if(candidateReqDto.getPincode() !=null) {
                    candidate1.setPincode(candidateReqDto.getPincode());
                }

                    candidate1.setActiveStatus(candidateReqDto.isActiveStatus());

                if(candidateReqDto.getLastSeen() !=null) {
                    candidate1.setLastSeen(candidateReqDto.getLastSeen());
                }

                if(candidateReqDto.getCurrentDesignation() !=null) {
                    candidate1.setCurrentDesignation(candidateReqDto.getCurrentDesignation());
                }

                if(candidateReqDto.getJobProfile() !=null) {
                    candidate1.setJobProfile(candidateReqDto.getJobProfile());
                }

                if(candidateReqDto.getOverview() !=null) {
                    candidate1.setOverview(candidateReqDto.getOverview());
                }

                if(candidateReqDto.getCurrentlyWorkingCompanyName() !=null) {
                    candidate1.setCurrentlyWorkingCompanyName(candidateReqDto.getCurrentlyWorkingCompanyName());
                }

                if(candidateReqDto.getRoleInHiring() !=null) {
                    candidate1.setRoleInHiring(candidateReqDto.getRoleInHiring());
                }

                if(candidateReqDto.getJoiningDateInCompany() !=null) {
                    candidate1.setJoiningDateInCompany(candidateReqDto.getJoiningDateInCompany());
                }

                if(candidateReqDto.getSpecialization() !=null) {
                    candidate1.setSpecialization(candidateReqDto.getSpecialization());

                }

                if(candidateReqDto.getYearOfPassing() !=null) {
                    candidate1.setYearOfPassing(candidateReqDto.getYearOfPassing());
                }

                if(candidateReqDto.getPercentage() !=null) {
                    candidate1.setPercentage(candidateReqDto.getPercentage());
                }

                if(candidateReqDto.getCollegeName() !=null) {
                    candidate1.setCollegeName(candidateReqDto.getCollegeName());

                }

                if(candidateReqDto.getUniversityName() !=null) {
                    candidate1.setUniversityName(candidateReqDto.getUniversityName());
                }

                if(candidateReqDto.getSchoolName() !=null) {
                    candidate1.setSchoolName(candidateReqDto.getSchoolName());
                }

                if(candidateReqDto.getRating() !=null) {
                    candidate1.setRating(candidateReqDto.getRating());
                }

                candidate1.setIsCandidate(candidateReqDto.isCandidate());

                candidate1.setDeleted(candidateReqDto.isDeleted());

                if(candidateReqDto.getInterviewId() !=null) {
                    candidate1.setInterviewId(candidateReqDto.getInterviewId());
                }

                 candidateRepository.save(candidate1);

                System.out.println("candidate update details " + candidate1);

            }
            return candidateReqDto;
        }
        catch (BadReqException e) {
            e.printStackTrace();
        }
        return candidateReqDto;
    }


    //_______________________________Delete api for candidate______________________________________

    public Candidate deleteCandidate(Long candidateId) {
        logger.info("getting candidate id for soft delete" + "" + candidateId);
        Optional<Candidate> candidate1 = candidateRepository.findById(candidateId);
        Candidate candidate = null;
        if (candidate1.isPresent()) {
            candidate = candidate1.get();
        } else {
            logger.error("deleting candidate by soft delete but candidate not found ");
            throw new UserNotFoundException("Candidate Not Found");
        }
        candidate.setDeleted(true);
        candidateRepository.save(candidate);
        logger.info("candidate deleted sucessfully");
        return candidate;
    }

    //___________________________________Login for user_________________________________________________________________
    public LoginResponseDto login(String email, String password, UserType userType) throws Exception {

        System.out.println("The user is " + userType);

        logger.info("The user is candidate");

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(email)
                .matches();

        if (email.isEmpty() || !emailValidation) {
            logger.info("Please Enter valid email");
            throw new BadReqException(ApiMessage.ENTER_EMAIL);
        }
        if (password.isEmpty() || password.length() < 4) {
            logger.info("please Enter valid password");
            throw new BadReqException(ApiMessage.ENTER_PASSWORD);
        }
        String token = "";

        Candidate candidate1 = null;
        Vendor vendor = null;
        LoginResponseDto loginResponseDto = null;

        if (userType == UserType.CANDIDATE) {
            System.out.println("Yes Candidate");
            candidate1 = candidateRepository.findByEmailAndPassword(email, password);
            if (candidate1 == null) {
                logger.info("Invalid credentials in login candidate");
                throw new BadReqException(ApiMessage.INVALID_credential);
            }
            authenticate(email, password);

                /*final UserDetails userDetails = jwtInMemoryUserDetailsService
                        .loadUserByUsername(email);*/

            final UserDetails userDetails = new User(candidate1.getEmail(), new BCryptPasswordEncoder().encode(candidate1.getPassword()),
                    new ArrayList<>());

            token = jwtTokenUtil.generateToken(userDetails);

            loginResponseDto = new LoginResponseDto(token, candidate1.getCandidateId() , candidate1.userType);

            System.out.println("Token generated from candidate " + token);

            logger.info("candidate login sucessfully");
        }

        if(userType == UserType.VENDOR){
             vendor = vendorRepository.findByEmailAndPassword(email, password);
            if (vendor == null) {
                throw new BadReqException(ApiMessage.INVALID_CREDENTIAL);
            }
            logger.info("vendor details by login " + vendor);

            authenticate(email, password);
            /*final UserDetails userDetails = jwtInMemoryUserDetailsService
                    .loadUserByUsername(email);*/


            final UserDetails userDetails = new User(vendor.getEmail(), new BCryptPasswordEncoder().encode(vendor.getPassword()),
                    new ArrayList<>());

            token = jwtTokenUtil.generateToken(userDetails);

            logger.info("vendor login sucessfully");

            loginResponseDto = new LoginResponseDto(token, vendor.getVendorId() , vendor.userType);

            System.out.println("token generated by vendor");

        }

        return loginResponseDto;
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

// ------------------------------- GET CANDIDATE------------------------------------------------------------------------

    public List<Candidate> getCandidate(String keyExperience, String skills, String city, String mobile) {

        if (keyExperience.isEmpty()) {
            keyExperience = null;
        }
        if (skills.isEmpty()) {
            skills = null;
        }
        if (city.isEmpty()) {
            city = null;
        }
        if (mobile.isEmpty()) {
            mobile = null;
        }

        List<Candidate> bySkillAndExperience = candidateRepository.findBySkillAndExperience(keyExperience, skills, city, mobile);
        logger.info("getting candidate details by skill and experience");
        System.out.println(bySkillAndExperience);
        return bySkillAndExperience;
    }

    // __________________________________Register api for candidate_____________________________________________________

    public Candidate registerCandidate(CandidateReqDto candidateReqDto, MultipartFile file) {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(candidateReqDto.getEmail())
                .matches();
        logger.info("email validate in version 2 register api");
        System.out.println((emailValidation));

        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(candidateReqDto.getPassword())
                .matches();
        System.out.println((pattern));

        if (emailAlreadyExist(candidateReqDto.getEmail())) {
            System.out.println("User Already Exist");
            logger.info(("email already used in version 2 api of resister_______________________>____________________"));
            throw new BadReqException(ApiMessage.EMAIL_ALREADY_USED);
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
                Candidate candidate = new Candidate();
        try{
                candidate.setFirstName(candidateReqDto.getFirstName());
                candidate.setLastName(candidateReqDto.getLastName());
                candidate.setKeyExperience(candidateReqDto.getKeyExperience());
                candidate.setSkills(candidateReqDto.getSkills());
                candidate.setAddress(candidateReqDto.getAddress());
                candidate.setSkypeId(candidateReqDto.getSkypeId());
                candidate.setWhatsapp(candidateReqDto.getWhatsapp());
                candidate.setCountry(candidateReqDto.getCountry());
                candidate.setState(candidateReqDto.getState());
                candidate.setCity(candidateReqDto.getCity());
                candidate.setRole(candidateReqDto.getRole());
                candidate.setHobbies(candidateReqDto.getHobbies());
                if (!emailValidation) {
                    throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
                } else {
                    candidate.setEmail(candidateReqDto.getEmail());
                }
                candidate.setInterest(candidateReqDto.getInterest());

                if (candidateReqDto.getMobile().isEmpty() || candidateReqDto.getMobile().length() != 10) {
                    throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
                } else {
                    candidate.setMobile(candidateReqDto.getMobile());
                }
                candidate.setAvailableForWork(candidateReqDto.isAvailableForWork());
                if (pattern != true) {
                    throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
                } else {
                    candidate.setPassword(candidateReqDto.getPassword());
                }
                candidate.setProfileImageUrl(fileNameAndPath.toString());
                candidate.setUserType(candidateReqDto.getUserType());
                candidate.setDeleted(candidateReqDto.isDeleted());
                candidate.setTwitterId(candidateReqDto.getTwitterId());
                candidate.setLinkedIn(candidateReqDto.getLinkedIn());
                candidate.setPincode(candidateReqDto.getPincode());
                candidate.setActiveStatus(candidateReqDto.isActiveStatus());
                candidate.setLastSeen(candidateReqDto.getLastSeen());
                candidate.setCurrentDesignation(candidateReqDto.getCurrentDesignation());
                candidate.setJobProfile(candidateReqDto.getJobProfile());
                candidate.setOverview(candidateReqDto.getOverview());
                candidate.setCurrentlyWorkingCompanyName(candidateReqDto.getCurrentlyWorkingCompanyName());
                candidate.setRoleInHiring(candidateReqDto.getRoleInHiring());
                candidate.setJoiningDateInCompany(candidateReqDto.getJoiningDateInCompany());
                candidate.setSpecialization(candidateReqDto.getSpecialization());
                candidate.setYearOfPassing(candidateReqDto.getYearOfPassing());
                candidate.setPercentage(candidateReqDto.getPercentage());
                candidate.setCollegeName(candidateReqDto.getCollegeName());
                candidate.setUniversityName(candidateReqDto.getUniversityName());
                candidate.setSchoolName(candidateReqDto.getSchoolName());
                candidate.setEmailVerifyCode(Common.getRandomNumberString());
                candidate.setIsCandidate(candidateReqDto.isCandidate());
                candidate.setEmailVerified(false);
                candidate.setIsCandidate(candidateReqDto.isCandidate());
                candidate.setRating(candidateReqDto.getRating());
                candidate.setInterviewId(candidateReqDto.getInterviewId());
                candidate.setProfileImageUrl((fileNameAndPath == null) ? "" : fileNameAndPath.toString());

                candidateRepository.save(candidate);
                sendVerificationEmail(candidate);
                return candidate;

          }
        catch (BadReqException e) {
        throw new BadReqException(e.getMessage());

    } catch (IOException e) {
        e.printStackTrace();
    } catch (MessagingException e) {
        e.printStackTrace();
    }
        return candidate;

    }

    //_________________________cHECK EMAIL VERIFIED OR NOT __________________________________________________________________
    public Boolean emailVerify(Long uid, String code) throws ResourceNotFoundException {
        Boolean verifyStatus = false;
        try {
            Optional<Candidate> candidate = candidateRepository.findById(uid);
            if (candidate.isPresent()) {
                Candidate candidate1 = candidate.get();
                System.out.println(candidate1.getEmailVerifyCode());
                if (candidate1.getEmailVerifyCode().equals(code)) {
                    candidate1.setEmailVerified(true);
                    candidateRepository.save(candidate1);
                    verifyStatus = true;
                }
            } else {
                throw new ResourceNotFoundException(ApiMessage.CANDIDATE_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException(ApiMessage.CANDIDATE_NOT_FOUND);
        }
        return verifyStatus;
    }

    //___________________________________eMAIL VERIFICATION CODE____________________________________________________________

    private void sendVerificationEmail(Candidate candidate)
            throws MessagingException, UnsupportedEncodingException {
        try {
            String toAddress = candidate.getEmail();
            String subject = "Please verify your registration";
            String content = "Dear " + candidate.getFirstName() + " " + candidate.getLastName() + ",<br>"
                    + "Please Enter this verification code to verify your registration sucessfully:<br>"
                    + "<h3>" + candidate.getEmailVerifyCode() + "</h3><br>"
                    + "Thank you,<br>"
                    + "Have a nice day,<br>"
                    + "The Ebench Team";
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);
            message.setContent(content, "text/html");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    ----------------------------------UPDATE CODE WITH FILE AND DATA--------------------------------------------------

    public Candidate updateCandidate1(CandidateReqDto candidateReqDto, MultipartFile file) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateReqDto.getCandidateId());

        Candidate candidate1 = null;


        if(candidateReqDto.getEmail() !=null) {
            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            boolean emailValidation = Pattern.compile(regexPattern)
                    .matcher(candidateReqDto.getEmail())
                    .matches();

            if (!emailValidation) {
            throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
            }

            System.out.println((emailValidation));
        }

        if(candidateReqDto.getPassword() !=null) {
            String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
            boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                    .matcher(candidateReqDto.getPassword())
                    .matches();

        if (!pattern ) {
         throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
        }

            System.out.println((pattern));
        }

            try {
                if(candidate.isPresent()) {
                     candidate1 = candidate.get();

                    Path fileNameAndPath = null;

                    if (!file.isEmpty()) {
                        StringBuilder fileName = new StringBuilder();
                        String filename = file.getOriginalFilename();
                        String[] str = filename.split("[.]", 2);
                        for (String i : str) {
                            System.out.println(i);
                        }

                        String fileNameWithTime = str[0] + "_" + System.currentTimeMillis() + "." + str[1];
                        System.out.println("FIle NAme With TIme : " + fileNameWithTime);
                        fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + fileNameWithTime);
                        fileName.append(file.getOriginalFilename());
                        Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

                        String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));

                        System.out.println("file uploaded successfully  " + fileNameAndPath);
                        System.out.println(candidateReqDto);

                    }

                    if (candidateReqDto.getFirstName() != null) {
                        candidate1.setFirstName(candidateReqDto.getFirstName());
                    }

                    if (candidateReqDto.getLastName() != null) {
                        candidate1.setLastName(candidateReqDto.getLastName());
                    }

                    if (candidateReqDto.getKeyExperience() != null) {
                        candidate1.setKeyExperience(candidateReqDto.getKeyExperience());
                    }

                    if (candidateReqDto.getSkills() != null) {
                        candidate1.setSkills(candidateReqDto.getSkills());
                    }
                    if (candidateReqDto.getAddress() != null) {
                        candidate1.setAddress(candidateReqDto.getAddress());
                    }

                    if (candidateReqDto.getSkypeId() != null) {
                        candidate1.setSkypeId(candidateReqDto.getSkypeId());
                    }

                    if (candidateReqDto.getWhatsapp() != null) {
                        candidate1.setWhatsapp(candidateReqDto.getWhatsapp());
                    }

                    if (candidateReqDto.getCountry() != null) {
                        candidate1.setCountry(candidateReqDto.getCountry());
                    }

                    if (candidateReqDto.getState() != null) {
                        candidate1.setState(candidateReqDto.getState());
                    }

                    if (candidateReqDto.getCity() != null) {
                        candidate1.setCity(candidateReqDto.getCity());
                    }

                    if (candidateReqDto.getHobbies() != null) {
                        candidate1.setHobbies(candidateReqDto.getHobbies());
                    }

                    if (candidateReqDto.getEmail() != null) {
                        candidate1.setEmail(candidateReqDto.getEmail());
                    }
                    if (candidateReqDto.getInterest() != null) {
                        candidate1.setInterest(candidateReqDto.getInterest());
                    }
                    if (candidateReqDto.getMobile() != null) {
                        if (candidateReqDto.getMobile().isEmpty() || candidateReqDto.getMobile().length() != 10) {
                            throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
                        }

                    } else if (candidateReqDto.getMobile() != null) {
                        candidate1.setMobile(candidateReqDto.getMobile());
                    }
                    candidate1.setAvailableForWork(candidateReqDto.isAvailableForWork());

                    if (candidateReqDto.getPassword() != null) {
                        candidate1.setPassword(candidateReqDto.getPassword());
                    }

                    if(fileNameAndPath != null)
                        candidate1.setProfileImageUrl(fileNameAndPath.toString());

                    if (candidateReqDto.getUserType() != null) {
                        candidate1.setUserType(candidateReqDto.getUserType());
                    }

                    if (candidateReqDto.getTwitterId() != null) {
                        candidate1.setTwitterId(candidateReqDto.getTwitterId());
                    }

                    if (candidateReqDto.getLinkedIn() != null) {
                        candidate1.setLinkedIn(candidateReqDto.getLinkedIn());
                    }

                    if (candidateReqDto.getPincode() != null) {
                        candidate1.setPincode(candidateReqDto.getPincode());
                    }

                    candidate1.setActiveStatus(candidateReqDto.isActiveStatus());

                    if (candidateReqDto.getLastSeen() != null) {
                        candidate1.setLastSeen(candidateReqDto.getLastSeen());
                    }

                    if (candidateReqDto.getCurrentDesignation() != null) {
                        candidate1.setCurrentDesignation(candidateReqDto.getCurrentDesignation());
                    }

                    if (candidateReqDto.getJobProfile() != null) {
                        candidate1.setJobProfile(candidateReqDto.getJobProfile());
                    }

                    if (candidateReqDto.getOverview() != null) {
                        candidate1.setOverview(candidateReqDto.getOverview());
                    }

                    if (candidateReqDto.getCurrentlyWorkingCompanyName() != null) {
                        candidate1.setCurrentlyWorkingCompanyName(candidateReqDto.getCurrentlyWorkingCompanyName());
                    }

                    if (candidateReqDto.getRoleInHiring() != null) {
                        candidate1.setRoleInHiring(candidateReqDto.getRoleInHiring());
                    }

                    if (candidateReqDto.getJoiningDateInCompany() != null) {
                        candidate1.setJoiningDateInCompany(candidateReqDto.getJoiningDateInCompany());
                    }

                    if (candidateReqDto.getSpecialization() != null) {
                        candidate1.setSpecialization(candidateReqDto.getSpecialization());

                    }

                    if (candidateReqDto.getYearOfPassing() != null) {
                        candidate1.setYearOfPassing(candidateReqDto.getYearOfPassing());
                    }

                    if (candidateReqDto.getPercentage() != null) {
                        candidate1.setPercentage(candidateReqDto.getPercentage());
                    }

                    if (candidateReqDto.getCollegeName() != null) {
                        candidate1.setCollegeName(candidateReqDto.getCollegeName());

                    }

                    if (candidateReqDto.getUniversityName() != null) {
                        candidate1.setUniversityName(candidateReqDto.getUniversityName());
                    }

                    if (candidateReqDto.getSchoolName() != null) {
                        candidate1.setSchoolName(candidateReqDto.getSchoolName());
                    }

                    if (candidateReqDto.getRating() != null) {
                        candidate1.setRating(candidateReqDto.getRating());
                    }

                    candidate1.setEmailVerifyCode(Common.getRandomNumberString());

                    candidate1.setIsCandidate(candidateReqDto.isCandidate());

                    candidate1.setDeleted(candidateReqDto.isDeleted());


                    if (candidateReqDto.getInterviewId() != null) {
                        candidate1.setInterviewId(candidateReqDto.getInterviewId());
                    }

                    candidate1.setEmailVerified(false);

                    candidate1.setProfileImageUrl((fileNameAndPath == null) ? "" : fileNameAndPath.toString());

                    candidate1.setVerificationCode(VerificationCode.getRandomNumberString());

                    Candidate candidate2 = candidateRepository.save(candidate1);

                    sendVerificationEmail(candidate2);
                    return candidate2;
                }
                else{
                    throw new BadReqException(ApiMessage.CANDIDATE_NOT_PRESENT);
                }

            }
            catch (BadReqException e) {
                logger.error("version 2 of register api not saving successfully_____________________>------");
                throw new BadReqException(e.getMessage());
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
            return candidate1;
        }

    }


