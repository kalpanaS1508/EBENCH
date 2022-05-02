package com.ebench.service;

import com.ebench.Apimessage.ApiMessage;
import com.ebench.entity.User;
import com.ebench.exception.UserNotFoundException;
import com.ebench.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginService {

 @Autowired
 private UserRepository userRepository;

 public Optional<User> loginUser(String email, String password) {

  Optional<User> user = Optional.ofNullable(userRepository.findByEmailandPassword(email,password));

  if (!user.isPresent()) {
   throw new UserNotFoundException(ApiMessage.INVALID_EMAIL_AND_PASSWORD);
  }
  else {
   return user;

  }
 }
}

