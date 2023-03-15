package com.warehouse.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import com.warehouse.service.models.User;
import com.warehouse.service.models.Role;
import com.warehouse.service.repository.UserRepository;
import com.warehouse.service.repository.RoleRepository;

import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.payload.request.UserRequest;

import com.warehouse.service.models.enums.EnumRoles;




@Service
@RequiredArgsConstructor
public class UserService {


  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final RoleRepository roleRepository;



  public List<User> findAll() {
     return userRepository.findAll();
  }

  public User findById(Long id) {
     return userRepository.findById(id).orElseThrow(() -> new ServiceException("Error: User is not found."));
  }

  private List<?> findByRole(EnumRoles role)
  {


      return this.findAll().stream() 
                           .filter(p -> p.getRoles().stream().filter(r -> r.getName() == role).count() > 0)
                           .collect(Collectors.toList());

//    return this.findAll();
  }

  public List<?> findPackers() {
    return this.findByRole(EnumRoles.ROLE_PACKER);
  }
  public List<?> findSupervisors() {
     return this.findByRole(EnumRoles.ROLE_SUPERVISOR);
  }





  public User updateItem(UserRequest userRequest)
  {  
    User item = this.findById(userRequest.getId());
    item.setUsername(userRequest.getUsername());
    item.setEmail(userRequest.getEmail());

    Set<Role> roles = new HashSet<>();
    Set<Long>  l_roles = userRequest.getRoles();

    for (Long role : l_roles)
       roles.add(roleRepository.findById(role).orElseThrow(() -> new ServiceException("Error: Role is not found.")));
    item.setRoles(roles);

    return userRepository.save(item);

  }
  public Boolean deleteItem(Long id)
  {
     userRepository.deleteById(id);
     return true;
  }                                                                                                             

  public List<?> getAllRoles() {
     return roleRepository.findAll();
  }
}
