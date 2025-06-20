package com.devtiro.blog.controllers;

import com.devtiro.blog.dto.AuthResponse;
import com.devtiro.blog.dto.LoginRequest;
import com.devtiro.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword());
        String token = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse
                .builder()
                .token(token)
                .expiresIn(86400)
                .build();
        return ResponseEntity.ok(authResponse);
    }



}
