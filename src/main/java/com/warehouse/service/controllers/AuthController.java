package com.warehouse.service.controllers;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.service.payload.request.LoginRequest;
import com.warehouse.service.payload.request.SignupRequest;
import com.warehouse.service.payload.response.JwtResponse;
import com.warehouse.service.payload.response.MessageResponse;
import com.warehouse.service.services.AuthService;
import com.warehouse.service.exceptions.ServiceException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  @Autowired
  private final AuthService authService;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.signin(loginRequest.getUsername(), loginRequest.getPassword()));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    try{
         if(authService.signup(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword()))
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
         else
           return ResponseEntity
             .badRequest()
             .body(new MessageResponse("Error: User is not registered!!!"));
    }
    catch(ServiceException e)
    {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse(e.getMessage()));

    }
  }
}
