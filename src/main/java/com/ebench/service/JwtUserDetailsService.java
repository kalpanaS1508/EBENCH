package com.ebench.service;

import java.util.ArrayList;

import com.ebench.entity.Candidate;
import com.ebench.entity.UserType;
import com.ebench.entity.Vendor;
import com.ebench.repository.CandidateRepository;
import com.ebench.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired

    CandidateRepository candidateRepository;

    @Autowired

    VendorRepository vendorRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Email from load by user " + username);


        Candidate candidate = candidateRepository.findByEmail(username);

        Vendor vendor = vendorRepository.findByEmail(username);

        System.out.println("AFter candidate");
        if(candidate!=null) {
            if (candidate.getEmail() != null) {
                return new User(username, new BCryptPasswordEncoder().encode(candidate.getPassword()),
                        new ArrayList<>());
            }
        }
        System.out.println("for Vendor");
        if(vendor!=null) {
            if (vendor.getEmail() != null) {

                return new User(username, new BCryptPasswordEncoder().encode(vendor.getPassword()),
                        new ArrayList<>());
            }
        }

        return new User(username, new BCryptPasswordEncoder().encode(candidate.getPassword()),
                new ArrayList<>());

    }




        @Bean
        public PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder();
        }




    }
