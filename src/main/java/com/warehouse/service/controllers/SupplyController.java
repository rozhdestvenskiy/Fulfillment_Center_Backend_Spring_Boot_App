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
import com.warehouse.service.services.SupplyService;
import com.warehouse.service.models.Supply;
import com.warehouse.service.payload.request.SupplyRequest;
import com.warehouse.service.payload.request.IdRequest;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SupplyController {
  @Autowired
  private final SupplyService supplyService;

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_PACKER')")
  public List<?> all(Authentication authentication) {
    return supplyService.findAll();
  }
  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR')")
  public Supply addItem(@Valid @RequestBody SupplyRequest supplyRequest) {
    return supplyService.addItem(supplyRequest);
  }

  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR')")
  public Supply editItem(@Valid @RequestBody SupplyRequest supplyRequest) {
    return supplyService.updateItem(supplyRequest);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Boolean deleteItem(@Valid @RequestBody IdRequest idRequest) {
    return supplyService.deleteItem(idRequest.getId());
  }

}
