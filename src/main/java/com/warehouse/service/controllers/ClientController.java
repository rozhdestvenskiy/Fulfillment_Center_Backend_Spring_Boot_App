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
import com.warehouse.service.services.ClientService;
import com.warehouse.service.payload.request.ClientRequest;
import com.warehouse.service.payload.request.IdRequest;
import com.warehouse.service.models.Client;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
  @Autowired
  private final ClientService clientService;


  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_PACKER')")
  public List<?> all() {
    return clientService.findAll();
  }

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Client addItem(@Valid @RequestBody ClientRequest clientRequest) {
    return clientService.addItem(clientRequest);
  }

  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Client editItem(@Valid @RequestBody ClientRequest clientRequest) {
    return clientService.updateItem(clientRequest);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Boolean deleteItem(@Valid @RequestBody IdRequest idRequest) {
    return clientService.deleteItem(idRequest.getId());
  }

}
