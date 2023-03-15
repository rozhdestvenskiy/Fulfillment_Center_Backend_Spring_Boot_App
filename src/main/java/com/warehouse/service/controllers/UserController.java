package com.warehouse.service.controllers;

import lombok.RequiredArgsConstructor;
import java.util.List;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.services.UserService;
import com.warehouse.service.payload.request.IdRequest;
import com.warehouse.service.payload.request.UserRequest;
import com.warehouse.service.models.User;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController{
  @Autowired
  private final UserService userService;

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public List<?> all(Authentication authentication) {
    return userService.findAll();
  }
  @GetMapping("/packers")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR')")
  public List<?> getPackers(Authentication authentication) {
    return userService.findPackers();
  }
  @GetMapping("/supervisors")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public List<?> getSupervisors(Authentication authentication) {
    return userService.findSupervisors();
  }


  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public User editItem(@Valid @RequestBody UserRequest userRequest) {
    return userService.updateItem(userRequest);
  }

}
