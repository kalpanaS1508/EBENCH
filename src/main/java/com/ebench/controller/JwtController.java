package com.ebench.controller;

import com.ebench.MiddleWare.Entity.JwtRequest;
import com.ebench.MiddleWare.Entity.JwtResponse;
import com.ebench.MiddleWare.JwtUtil;
import com.ebench.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));

        } catch (BadCredentialsException e) {

            System.out.println("exception " + e);

            throw new Exception("Invalid Email and Password");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());

        String Token = this.jwtUtil.generateToken(userDetails);

        System.out.println("jwt" + Token);
        return ResponseEntity.ok(new JwtResponse(Token));
    }


}
