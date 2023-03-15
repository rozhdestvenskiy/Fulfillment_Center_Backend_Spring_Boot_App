package com.warehouse.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import com.warehouse.service.models.enums.EnumClientStatus;
import com.warehouse.service.models.Client;
import com.warehouse.service.models.ClientStatus;
import com.warehouse.service.repository.ClientRepository;
import com.warehouse.service.repository.ClientStatusRepository;
import com.warehouse.service.payload.request.ClientRequest;

import com.warehouse.service.exceptions.ServiceException;



@Service
@RequiredArgsConstructor
public class ClientService {


  @Autowired
  private final ClientRepository clientRepository;

  @Autowired
  private final ClientStatusRepository clientStatusRepository;



  public List<?> findAll() {
     return clientRepository.findAll();
  }

  public Client findById(Long id) {
     return clientRepository.findById(id).orElseThrow(() -> new ServiceException("Error: Client is not found."));
  }

  public Client addItem(ClientRequest clientRequest)
  {
    Client item = new Client(clientRequest.getFirstname(), clientRequest.getLastname(), clientRequest.getEmail(), this.findStatusByName(clientRequest.getStatus()));
    return clientRepository.save(item);
  }

  public Client updateItem(ClientRequest clientRequest)
  {  
     Client item = this.findById(clientRequest.getId());
     item.setFirstname(clientRequest.getFirstname());
     item.setLastname(clientRequest.getLastname());
     item.setEmail(clientRequest.getEmail());
     item.setStatus(this.findStatusByName(clientRequest.getStatus()));
     return clientRepository.save(item);
  }

  public Client updateStatus(Long id, String status)
  {
     Client item = this.findById(id);
     item.setStatus(this.findStatusByName(status));
     return clientRepository.save(item);
  }

  public Boolean deleteItem(Long id)
  {
     //clientRepository.deleteById(id);

     Client item = this.updateStatus(id, "Removed");

     return true;
  }

  private EnumClientStatus castStatus(String status) {

     switch(status){
       case "New": 
          return EnumClientStatus.New;
       case "Regular": 
          return EnumClientStatus.Regular;
       case "VIP": 
          return EnumClientStatus.VIP;
       case "Inactive": 
          return EnumClientStatus.Inactive;
       case "Removed": 
          return EnumClientStatus.Removed;
     }
     return EnumClientStatus.Removed;
  }

  public ClientStatus findStatusByName( String status) {
     return clientStatusRepository.findByName(this.castStatus(status)).orElseThrow(() -> new ServiceException("Error: Client status is not found."));
  }


  public List<?> getAllStatuses() {
     return clientStatusRepository.findAll();
  }


}
