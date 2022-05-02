package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.User;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    public UserRepository userRepository;

//-------------------------find candidate list on the basis of skills-------------------------------

    public List<User> getCandidateBySkills(String skills , String keyExperience , String state) {
        try {
            if (!skills.isEmpty() && !keyExperience.isEmpty() && !state.isEmpty()) {
                List<User> listOfCandidateBySkills = userRepository.findBySkillsandExp();
                return listOfCandidateBySkills;

            }
            else {
                throw new UserNotFoundException(ApiMessage.Not_Found);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }


}
