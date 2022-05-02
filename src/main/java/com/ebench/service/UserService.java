package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.dto.UserReqDto;
import com.ebench.entity.User;
import com.ebench.exception.BadReqException;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.UserRepository;
import com.ebench.utils.Constant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserRepository userRepository;


    private String UPLOAD_DIR = "C:\\Users\\Admin\\OneDrive\\Desktop\\ebsol" +
          "\\E-benchs\\src\\main\\resources\\Static\\image";


    public UserReqDto register(UserReqDto userReqDto) {


        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(userReqDto.getEmail())
                .matches();
        System.out.println((emailValidation));
        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(userReqDto.getPassword())
                .matches();
        System.out.println((pattern));

//        if(emailAlreadyExist(userReqDto.email)){
//            throw new ResourceAlreadyExists(ApiMessage.Email_Already_Present);
//        }
        try {
            User user = new User();
            user.setName(userReqDto.getName());
            user.setAddress(userReqDto.getAddress());
            user.setCity(userReqDto.getCity());
            user.setCountry(userReqDto.getCountry());
            if (!emailValidation) {
                throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
            } else {
                user.setEmail(userReqDto.getEmail());
            }
            user.setHobbies(userReqDto.getHobbies());
            user.setInterest(userReqDto.getInterest());
            user.setKeyExperience(userReqDto.getKeyExperience());
            if (userReqDto.getMobile().isEmpty() || userReqDto.getMobile().length() != 10) {
                throw new BadReqException(ApiMessage.Enter_Valid_Phone_Number);

            } else {
                user.setMobile(userReqDto.getMobile());
            }

            user.setSkills(userReqDto.getSkills());
            user.setAvailableForWork(userReqDto.isAvailableForWork());
            user.setSkypeId(userReqDto.getSkypeId());
            user.setWhatsapp(userReqDto.getWhatsapp());
            user.setState(userReqDto.getState());
           user.setProfileImageUrl(userReqDto.getProfileImageUrl());


            if (pattern != true) {
                throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
            } else {
                user.setPassword(userReqDto.getPassword());
            }
            user.setUserType(userReqDto.getUserType());
            userRepository.save(user);
        } catch (BadReqException e) {
            throw new BadReqException(e.getMessage());
        }
        return userReqDto;
    }


    //------------------------------------------------get user--------------------------------------------------
    public Optional<User> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        User user1 = null;
        try {
            if (user.isPresent()) {
                user1 = user.get();

                logger.error("fetched data");
            }
            else {
                throw new UserNotFoundException(ApiMessage.User_Not_Present);
            }

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
        return user;
    }

    //---------------------------------------------available for work--------------------------------------------------
    public List<User> onlineUser(boolean availableForWork, int sort) {
        if (sort == Constant.ZERO) {
            List<User> list = userRepository.findByNameandKey(availableForWork, Sort.by(Sort.Direction.ASC, "name"));
            return list;
        } else if (sort == Constant.ONE) {
            List<User> list = userRepository.findByNameandKey(availableForWork, Sort.by(Sort.Direction.DESC, "name"));
            return list;
        } else if (sort == Constant.TWO) {
            List<User> list = userRepository.findByNameandKey(availableForWork, Sort.by(Sort.Direction.ASC, "keyExperience"));
            return list;
        } else if (sort == Constant.THREE) {
            List<User> list = userRepository.findByNameandKey(availableForWork, Sort.by(Sort.Direction.DESC, "keyExperience"));
            return list;
        } else {
            throw new UserNotFoundException(ApiMessage.Not_Found);
        }

    }


    //---------------------------------------------update user--------------------------------------------------------
    public UserReqDto updateUser(UserReqDto userReqDto) {
        Optional<User> user = userRepository.findById(userReqDto.getId());

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean emailValidation = Pattern.compile(regexPattern)
                .matcher(userReqDto.getEmail())
                .matches();
//        System.out.println((emailValidation));
//        final String PASSWORD_PATTERN =
//                "^(?=.*[0-9].{1})(?=.*[A-Za-z].{2})(?=.*[@#$%^&+=].{1})(?=\\S+$).{8,}$";
        String PASSWORD_PATTERN = "^(?=(?:[a-zA-Z0-9]*[a-zA-Z]){2})(?=(?:[a-zA-Z0-9]*\\d){2})[a-zA-Z0-9]{8,}$";
        boolean pattern = Pattern.compile(PASSWORD_PATTERN)
                .matcher(userReqDto.getPassword())
                .matches();
        System.out.println((pattern));
        User user1 = new User();
        user1 = user.get();
        try {


            if (user.isPresent()) {

                user1.setName(userReqDto.getName());
//                    if (userReqDto.getPassword().length() < 8) {
//                        throw new BadReqException(ApiMessage.Password_Length_Invalid);
//                    }
                if (pattern != true) {
                    throw new BadReqException(ApiMessage.Password_Not_Proper_Format);
                } else {
                    user1.setPassword(userReqDto.getPassword());
                }
                user1.setCountry(userReqDto.getCountry());
                user1.setCity(userReqDto.getCity());
                user1.setKeyExperience(userReqDto.getKeyExperience());
                user1.setState(userReqDto.getState());
                user1.setWhatsapp(userReqDto.getWhatsapp());
                user1.setSkypeId(userReqDto.getSkypeId());
                user1.setAvailableForWork(userReqDto.isAvailableForWork());
                user1.setMobile(userReqDto.getMobile());
                user1.setInterest(userReqDto.getInterest());
                user1.setHobbies(userReqDto.getHobbies());
                user1.setAddress(userReqDto.getAddress());
                user1.setSkills(userReqDto.getSkills());
                if (emailValidation != true) {
                    throw new BadReqException(ApiMessage.Email_Not_In_Proper_Format);
                }
////                    else if(emailAlreadyExist(user1.email)){
////                        throw new BadReqException(ApiMessage.Email_Already_Present);
////                    }
                else {
                    user1.setEmail(userReqDto.getEmail());
                }
                userRepository.save(user1);

            } else {
                throw new UserNotFoundException(ApiMessage.User_Not_Present);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }

        return userReqDto;
    }

    public ResponseEntity<String> uploadImage(MultipartFile file , String userReqDto) {

      try{

          if (file.isEmpty()) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
          }


            if (file != null) {
                StringBuilder fileName = new StringBuilder();
                final String UPLOAD_DIR = new ClassPathResource("Static/image").getFile().getAbsolutePath();


                Path fileNameAndPath = Paths.get(UPLOAD_DIR, File.separator + file.getOriginalFilename());
                fileName.append(file.getOriginalFilename());

                Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                    User value = objectMapper.readValue(userReqDto, User.class);

                    String fileName2 = StringUtils.cleanPath(String.valueOf(fileNameAndPath.getFileName()));

                    User u1 = value;
                    u1.setProfileImageUrl(fileName2);

                    User savedUser = userRepository.save(u1);

                    System.out.println(savedUser.getProfileImageUrl());


                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                System.out.println("file uploaded successfully  " + fileNameAndPath);
                System.out.println(userReqDto);

            }

          if (!file.getContentType().equals("image/jpeg")) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG files are allowed");
          }

      } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Working properly");
    }
}


























//    public boolean emailAlreadyExist(String email) {
//
//        System.out.println("In Email Exist Chacking Method");
//
//        Optional<User> userReqDto = userRepository.findByEmail(email);
//        /*System.out.println("Optional User : "+user);
//            String getUser = user.toString();*/
//        if (userReqDto.isPresent()) {
//            System.out.println("True");
//            return true;
//        } else {
//            System.out.println("False");
//            return false;
//        }
//    }



