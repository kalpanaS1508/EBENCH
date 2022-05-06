package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.CandidateReqDto;
import com.ebench.entity.Candidate;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.CandidateRepository;
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
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;


    // __________________________________ Register Api for Candidate__________________________________________//

//    private String UPLOAD_DIR = "D://EBENCH MAY//EBENCH//target//classes//Static//image";

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
            System.out.println("User Already Exist");
            throw new BadReqException(ApiMessage.EMAIL_ALREADY_USED);
        }


        try {
//            StringBuilder fileName = new StringBuilder();
//            Path fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + file.getOriginalFilename());
//            fileName.append(file.getOriginalFilename());
//            Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);
//
//            String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));
//
//            System.out.println("file uploaded successfully  " + fileNameAndPath);
//            System.out.println(candidateReqDto);

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
            if (pattern != false) {
                throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
            } else {
                candidate.setPassword(candidateReqDto.getPassword());
            }
           // candidate.setProfileImageUrl(fileNameAndPath.toString());
            candidate.setUserType(candidateReqDto.getUserType());
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
            candidateRepository.save(candidate);
        } catch (BadReqException e) {
            throw new BadReqException(e.getMessage());
        }
        return candidateReqDto;
    }

    public Boolean emailAlreadyExist(String email) {

        System.out.println("In Email Exist Checking Method");

        Optional<Candidate> user = candidateRepository.findUserByEmail(email);
        if (user.isPresent()) {
            System.out.println("True");
            return true;
        } else {
            System.out.println("False");
            return false;
        }
    }
    // _______________________________Get Api for Candidate ______________________________________________________________________//

    public Candidate getCandidate(Long id) {
        Optional<Candidate> user = candidateRepository.findById(id);
        Candidate candidate1 = null;
        try {
            if (user.isPresent()) {

                candidate1 = user.get();

            } else {
                throw new UserNotFoundException(ApiMessage.User_Not_Present);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
            return candidate1;
    }




    // _____________________________________Update Api for candidate Registration ________________________________________//

    public CandidateReqDto updateCandidate(CandidateReqDto candidateReqDto,MultipartFile file) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateReqDto.getId());
        Candidate candidate1 = new Candidate();
        candidate1 = candidate.get();
        try {
            if (candidate.isPresent()) {
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
                    System.out.println("User Already Exist");
                    throw new BadReqException(ApiMessage.EMAIL_ALREADY_USED);
                }



                try {
//                    StringBuilder fileName = new StringBuilder();
//                    Path fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + file.getOriginalFilename());
//                    fileName.append(file.getOriginalFilename());
//
//                    Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);
//
//                    String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));

                   // System.out.println("file uploaded successfully  " + fileNameAndPath);
                    System.out.println(candidateReqDto);
                    candidate1.setFirstName(candidateReqDto.getFirstName());
                    candidate1.setLastName(candidateReqDto.getLastName());
                    candidate1.setKeyExperience(candidateReqDto.getKeyExperience());
                    candidate1.setSkills(candidateReqDto.getSkills());
                    candidate1.setAddress(candidateReqDto.getAddress());
                    candidate1.setSkypeId(candidateReqDto.getSkypeId());
                    candidate1.setWhatsapp(candidateReqDto.getWhatsapp());
                    candidate1.setCountry(candidateReqDto.getCountry());
                    candidate1.setState(candidateReqDto.getState());
                    candidate1.setCity(candidateReqDto.getCity());
                    candidate1.setHobbies(candidateReqDto.getHobbies());
                    if (!emailValidation) {
                        throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
                    } else {
                        candidate1.setEmail(candidateReqDto.getEmail());
                    }
                    candidate1.setInterest(candidateReqDto.getInterest());
                    if (candidateReqDto.getMobile().isEmpty() || candidateReqDto.getMobile().length() != 10) {
                        throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);
                    } else {
                        candidate1.setMobile(candidateReqDto.getMobile());
                    }
                    candidate1.setAvailableForWork(candidateReqDto.isAvailableForWork());
                    if (pattern != false) {
                        throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
                    } else {
                        candidate1.setPassword(candidateReqDto.getPassword());
                    }
                   // candidate1.setProfileImageUrl(fileNameAndPath.toString());
                    candidate1.setUserType(candidateReqDto.getUserType());
                    candidate1.setTwitterId(candidateReqDto.getTwitterId());
                    candidate1.setLinkedIn(candidateReqDto.getLinkedIn());
                    candidate1.setPincode(candidateReqDto.getPincode());
                    candidate1.setActiveStatus(candidateReqDto.isActiveStatus());
                    candidate1.setLastSeen(candidateReqDto.getLastSeen());
                    candidate1.setCurrentDesignation(candidateReqDto.getCurrentDesignation());
                    candidate1.setJobProfile(candidateReqDto.getJobProfile());
                    candidate1.setOverview(candidateReqDto.getOverview());
                    candidate1.setCurrentlyWorkingCompanyName(candidateReqDto.getCurrentlyWorkingCompanyName());
                    candidate1.setRoleInHiring(candidateReqDto.getRoleInHiring());
                    candidate1.setJoiningDateInCompany(candidateReqDto.getJoiningDateInCompany());
                    candidate1.setSpecialization(candidateReqDto.getSpecialization());
                    candidate1.setYearOfPassing(candidateReqDto.getYearOfPassing());
                    candidate1.setPercentage(candidateReqDto.getPercentage());
                    candidate1.setCollegeName(candidateReqDto.getCollegeName());
                    candidate1.setUniversityName(candidateReqDto.getUniversityName());
                    candidate1.setSchoolName(candidateReqDto.getSchoolName());
                    candidateRepository.save(candidate1);
                }
                catch (BadReqException e) {
                    throw new BadReqException(e.getMessage());
                }

                return candidateReqDto;
            }
        } catch (BadReqException e) {
            e.printStackTrace();
        }
        return candidateReqDto;
    }
 //_______________________________Delete api for candidate______________________________________
 public CandidateReqDto deletecandidate(long id) {
        Candidate candidate = new Candidate();
     try {
         Optional<Candidate> candidate1 = candidateRepository.findById(id);
         if (candidate1 != null) {
             candidateRepository.deleteById(id);
         } else {
             throw new RuntimeException(ApiMessage.Api_Message);
         }
     } catch (Exception e) {
         throw new RuntimeException(ApiMessage.Not_Found);
     }
        return null;
 }
}