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


import com.warehouse.service.services.OrderService;
import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.payload.request.IdRequest;
import com.warehouse.service.payload.request.OrderRequest;
import com.warehouse.service.models.Order;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
  @Autowired
  private final OrderService orderService;

  @GetMapping("/all")
  public List<?> all() {
    return orderService.findAll();
  }

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Order addItem(Authentication authentication, @Valid @RequestBody OrderRequest orderRequest) {
    return orderService.addItem(authentication, orderRequest);
  }

  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_PACKER')")
  public Order editItem(Authentication authentication, @Valid @RequestBody OrderRequest orderRequest) {
    return orderService.updateItem(authentication, orderRequest);
  }

  @PostMapping("/delete")
  @PreAuthorize("hasRole('ROLE_MANAGER')")
  public Boolean deleteItem(@Valid @RequestBody IdRequest idRequest) {
    return orderService.deleteItem(idRequest.getId());
  }

}
