package com.ebench.service;

import com.ebench.dto.loginDto.LoginResponseDto;
import com.ebench.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

    @Service
    public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

        @Autowired
        UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            LoginResponseDto loginResponseDto = userRepository.findByEmail(email);
            System.out.println(loginResponseDto.getEmail() + "    " + loginResponseDto.getPassword());

            if (email.equals(loginResponseDto.getEmail())) {
                return new User(loginResponseDto.getEmail(), loginResponseDto.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User Not Found");
            }
        }

    }
