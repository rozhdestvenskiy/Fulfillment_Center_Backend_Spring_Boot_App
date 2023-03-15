package com.warehouse.service.controllers;

import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.services.OrderService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order_status")
@RequiredArgsConstructor
public class OrderStatusController {

  @Autowired
  private final OrderService orderService;

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_PACKER')")
  public List<?> all(Authentication authentication) {
    return orderService.getAllStatuses();
  }
}
