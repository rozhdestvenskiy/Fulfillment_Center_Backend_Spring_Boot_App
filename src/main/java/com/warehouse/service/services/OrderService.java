package com.warehouse.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import com.warehouse.service.models.enums.EnumOrderStatus;

import com.warehouse.service.models.Order;
import com.warehouse.service.models.OrderStatus;
import com.warehouse.service.models.Supply;
import com.warehouse.service.models.Product;
import com.warehouse.service.models.User;
import com.warehouse.service.models.Client;
import com.warehouse.service.models.ClientStatus;
import com.warehouse.service.repository.OrderRepository;
import com.warehouse.service.repository.OrderStatusRepository;
import com.warehouse.service.repository.SupplyRepository;
import com.warehouse.service.repository.ProductRepository;
import com.warehouse.service.repository.UserRepository;
import com.warehouse.service.repository.ClientRepository;
import com.warehouse.service.repository.ClientStatusRepository;
import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.payload.request.OrderRequest;

import com.warehouse.service.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;



@Service
@RequiredArgsConstructor
public class OrderService {

  @Autowired
  private final OrderRepository orderRepository;

  @Autowired
  private final OrderStatusRepository orderStatusRepository;

  @Autowired
  private final ClientRepository clientRepository;

  @Autowired
  private final SupplyRepository supplyRepository;

  @Autowired
  private final ProductRepository productRepository;

  @Autowired
  private final UserRepository userRepository;



  public List<?> findAll() {
     return orderRepository.findAll();
  }

  public Order findById(Long id) {
     return orderRepository.findById(id).orElseThrow(() -> new ServiceException("Error: Order is not found."));
  }

  public Order addItem(Authentication authentication, OrderRequest orderRequest)
  {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    Order item = new Order(orderRequest.getNumber(), 
                           orderRequest.getOrderDate(), 
                           orderRequest.getCost(), 
                           this.findStatusByName(orderRequest.getStatus()), 
                           supplyRepository.findById(orderRequest.getSupply()).get(), 
                           clientRepository.findById(orderRequest.getClient()).orElseThrow(() -> new ServiceException("Error: Client is not found.")), 
                           productRepository.findById(orderRequest.getProduct()).orElseThrow(() -> new ServiceException("Error: Product is not found.")), 
                           userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new ServiceException("Error: User is not found."))
                          );
    return orderRepository.save(item);
  }

  public Order updateItem(Authentication authentication, OrderRequest orderRequest)
  {  
     UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

     Order item = this.findById(orderRequest.getId());
     item.setNumber(orderRequest.getNumber());
     item.setOrderDate(orderRequest.getOrderDate());
     item.setCost(orderRequest.getCost());
     item.setStatus(this.findStatusByName(orderRequest.getStatus()));
     item.setSupply(supplyRepository.findById(orderRequest.getSupply()).get());
     item.setClient(clientRepository.findById(orderRequest.getClient()).orElseThrow(() -> new ServiceException("Error: Client is not found.")));
     item.setProduct(productRepository.findById(orderRequest.getProduct()).orElseThrow(() -> new ServiceException("Error: Product is not found.")));
     item.setUser(userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new ServiceException("Error: User is not found.")));
     return orderRepository.save(item);
  }

  public Order updateStatus(Long id, String status)
  {
     Order item = this.findById(id);
     item.setStatus(this.findStatusByName(status));
     return orderRepository.save(item);
  }

  public Boolean deleteItem(Long id)
  {
     Order item = this.updateStatus(id, "Cancelled");

     return true;
  }

  

  private EnumOrderStatus castStatus(String status) {

     switch(status){
       case "Not_processed": 
          return EnumOrderStatus.Not_processed;
       case "Processed": 
          return EnumOrderStatus.Processed;
       case "Cancelled": 
          return EnumOrderStatus.Cancelled;
     }
     return EnumOrderStatus.Cancelled;
  }
  public OrderStatus findStatusByName(String status) {
     return orderStatusRepository.findByName(this.castStatus(status)).orElseThrow(() -> new ServiceException("Error: Order status is not found."));
  }


  public List<?> getAllStatuses() {
     return orderStatusRepository.findAll();
  }


}
