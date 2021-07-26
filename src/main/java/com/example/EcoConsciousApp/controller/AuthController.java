package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.security.AuthRequest;
import com.example.EcoConsciousApp.entity.User;
import com.example.EcoConsciousApp.security.SignupRequest;
import com.example.EcoConsciousApp.service.impl.UserDetailService;
import com.example.EcoConsciousApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            //validate username and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch(Exception ex){
            throw new Exception(ResponseMessage.INVALID_USERNAME_OR_PASSWORD);
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        if(userDetailService.userNameExist(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(ResponseMessage.USERNAME_HAS_BEEN_TAKEN);
        }

        User user = new User(signupRequest.getUsername(),
                            passwordEncoder.encode(signupRequest.getPassword()),
                            signupRequest.getEmail());

        userDetailService.saveUser(user);
        return ResponseEntity.ok(ResponseMessage.USER_SUCCESSFULLY_REGISTERED);
    }
}
