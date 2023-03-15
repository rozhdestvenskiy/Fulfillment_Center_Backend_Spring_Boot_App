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
import com.warehouse.service.payload.request.IdRequest;
import com.warehouse.service.payload.request.ReferenceRequest;
import com.warehouse.service.models.SupplyVendor;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/supply_vendor")
@RequiredArgsConstructor
public class SupplyVendorController {
  @Autowired
  private final SupplyService supplyService;

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_PACKER')")
  public List<?> all(Authentication authentication) {
    return supplyService.getAllVendors();
  }
  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public SupplyVendor addItem(@Valid @RequestBody ReferenceRequest referenceRequest) {
    return supplyService.addItemVendor(referenceRequest);
  }

  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public SupplyVendor editItem(@Valid @RequestBody ReferenceRequest referenceRequest) {
    return supplyService.updateItemVendor(referenceRequest);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Boolean deleteItem(@Valid @RequestBody IdRequest idRequest) {
    return supplyService.deleteItemVendor(idRequest.getId());
  }

}
