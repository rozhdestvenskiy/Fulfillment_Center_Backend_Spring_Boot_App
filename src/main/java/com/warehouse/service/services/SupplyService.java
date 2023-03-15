package com.warehouse.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import com.warehouse.service.models.Supply;
import com.warehouse.service.models.SupplyVendor;
import com.warehouse.service.repository.SupplyRepository;
import com.warehouse.service.repository.SupplyVendorRepository;

import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.payload.request.SupplyRequest;
import com.warehouse.service.payload.request.ReferenceRequest;




@Service
@RequiredArgsConstructor
public class SupplyService {


  @Autowired
  private final SupplyRepository supplyRepository;

  @Autowired
  private final SupplyVendorRepository supplyVendorRepository;



  public List<?> findAll() {
     return supplyRepository.findAll();
  }

  public Supply findById(Long id) {
     return supplyRepository.findById(id).orElseThrow(() -> new ServiceException("Error: Supply is not found."));
  }


  public Supply addItem(SupplyRequest supplyRequest)
  {
     Supply item = new Supply(supplyRequest.getSize()
                             ,supplyRequest.getWeightLimit()
                             ,supplyRequest.getQty()
                             ,supplyRequest.getType()
                             );
    Set<SupplyVendor> vendorSupplies = new HashSet<>();
    Set<Long> supplyVendors = supplyRequest.getVendorSupplies();

    for (Long vendor_id : supplyVendors)
       vendorSupplies.add(supplyVendorRepository.findById(vendor_id).orElseThrow(() -> new ServiceException("Error: Vendor is not found.")));
    item.setVendorSupplies(vendorSupplies);

    return supplyRepository.save(item);
  }

  public Supply updateItem(SupplyRequest supplyRequest)
  {  
    Supply item = this.findById(supplyRequest.getId());

    item.setSize(supplyRequest.getSize());
    item.setWeightLimit(supplyRequest.getWeightLimit());
    item.setQty(supplyRequest.getQty());
    item.setType(supplyRequest.getType());
    Set<SupplyVendor> vendorSupplies = new HashSet<>();
    Set<Long> supplyVendors = supplyRequest.getVendorSupplies();

    for (Long vendor_id : supplyVendors)
       vendorSupplies.add(supplyVendorRepository.findById(vendor_id).orElseThrow(() -> new ServiceException("Error: Vendor is not found.")));
    item.setVendorSupplies(vendorSupplies);

    return supplyRepository.save(item);

  }

  public Boolean deleteItem(Long id)
  {
     supplyRepository.deleteById(id);
     return true;
  }                                                                                                             

  public List<?> getAllVendors() {
     return supplyVendorRepository.findAll();
  }
  public SupplyVendor addItemVendor(ReferenceRequest referenceRequest)
  {
     SupplyVendor item = new SupplyVendor(referenceRequest.getName());

    return supplyVendorRepository.save(item);
  }

  public SupplyVendor updateItemVendor(ReferenceRequest referenceRequest)
  {  
    SupplyVendor item = supplyVendorRepository.findById(referenceRequest.getId()).orElseThrow(() -> new ServiceException("Error: Supply vendor is not found."));

    item.setName(referenceRequest.getName());

    return supplyVendorRepository.save(item);

  }
  public Boolean deleteItemVendor(Long id)
  {
     supplyVendorRepository.deleteById(id);
     return true;
  }                                                                                                             

}
