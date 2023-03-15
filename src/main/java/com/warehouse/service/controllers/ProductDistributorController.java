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
import com.warehouse.service.services.ProductService;
import com.warehouse.service.payload.request.IdRequest;
import com.warehouse.service.payload.request.ReferenceRequest;
import com.warehouse.service.models.ProductDistributor;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product_distributor")
@RequiredArgsConstructor
public class ProductDistributorController {
  @Autowired
  private final ProductService productService;

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_PACKER')")
  public List<?> all(Authentication authentication) {
    return productService.getAllDistributors();
  }

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public ProductDistributor addItem(@Valid @RequestBody ReferenceRequest referenceRequest) {
    return productService.addItemDistributor(referenceRequest);
  }

  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public ProductDistributor editItem(@Valid @RequestBody ReferenceRequest referenceRequest) {
    return productService.updateItemDistributor(referenceRequest);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Boolean deleteItem(@Valid @RequestBody IdRequest idRequest) {
    return productService.deleteItemDistributor(idRequest.getId());
  }


}
