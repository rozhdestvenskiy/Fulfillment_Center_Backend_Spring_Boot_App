package com.warehouse.service.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.warehouse.service.models.enums.EnumRoles;
import com.warehouse.service.models.Role;
import com.warehouse.service.models.User;
import com.warehouse.service.payload.response.JwtResponse;
import com.warehouse.service.repository.RoleRepository;
import com.warehouse.service.repository.UserRepository;
import com.warehouse.service.security.jwt.JwtUtils;
import com.warehouse.service.security.services.UserDetailsImpl;

import com.warehouse.service.exceptions.ServiceException;


@Service
@RequiredArgsConstructor
public class AuthService {


  @Autowired
  private final AuthenticationManager authenticationManager;

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final RoleRepository roleRepository;

  @Autowired
  private final PasswordEncoder encoder;

  @Autowired
  private final JwtUtils jwtUtils;


  public JwtResponse signin(String username, String password) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return new JwtResponse(jwt, 
                           userDetails.getId(), 
                           userDetails.getUsername(), 
                           userDetails.getEmail(), 
                           roles);
  }



  public boolean signup(String name, String email, String password) {
    if (userRepository.existsByUsername(name)) {
       throw new ServiceException("Error: Username is already taken!");
    }

    if (userRepository.existsByEmail(email)) {
       throw new ServiceException("Error: Email is already in use!");
    }

    // Create new user's account
    User user = new User(name, 
               email,
               encoder.encode(password));

    EnumRoles defaultRole = EnumRoles.ROLE_PACKER;

    if(userRepository.count() == 0)
       defaultRole = EnumRoles.ROLE_MANAGER;
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(defaultRole)
                                  .orElseThrow(() -> new ServiceException("Error: Role is not found."));
    roles.add(userRole);

    user.setRoles(roles);
    userRepository.save(user);

    return true;


/*
    System.out.println("____________________________");

    System.out.println(EnumRoles.ROLE_PACKER);

    List<Role> l_roles = roleRepository.findAll();
    for (Role r : l_roles)
        System.out.println(r.getName() + " ");
    System.out.println("____________________________");
*/
  }

  public List<?> getAllRoles() {
     return roleRepository.findAll();
  }


}
