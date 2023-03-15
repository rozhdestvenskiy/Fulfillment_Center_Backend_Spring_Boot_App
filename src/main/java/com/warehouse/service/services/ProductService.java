package com.warehouse.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import com.warehouse.service.models.Product;
import com.warehouse.service.models.ProductDistributor;
import com.warehouse.service.repository.ProductRepository;
import com.warehouse.service.repository.ProductDistributorRepository;

import com.warehouse.service.exceptions.ServiceException;
import com.warehouse.service.payload.request.ProductRequest;
import com.warehouse.service.payload.request.ReferenceRequest;




@Service
@RequiredArgsConstructor
public class ProductService {


  @Autowired
  private final ProductRepository productRepository;

  @Autowired
  private final ProductDistributorRepository productDistributorRepository;



  public List<?> findAll() {
     return productRepository.findAll();
  }

  public Product findById(Long id) {
     return productRepository.findById(id).orElseThrow(() -> new ServiceException("Error: Product is not found."));
  }

  public Product addItem(ProductRequest productRequest)
  {
     Product item = new Product(productRequest.getName(), 
                                productRequest.getLocation(), 
                                productRequest.getQty(), 
                                productRequest.getPricePerItem()
                               );
    Set<ProductDistributor> distributors = new HashSet<>();
    Set<Long> l_distributors = productRequest.getDistributor();

    for (Long distributor_id : l_distributors)
       distributors.add(productDistributorRepository.findById(distributor_id).orElseThrow(() -> new ServiceException("Error: Distributer is not found.")));
    item.setDistributor(distributors);

    return productRepository.save(item);
  }

  public Product updateItem(ProductRequest productRequest)
  {  
     Product item = this.findById(productRequest.getId());
     item.setName(productRequest.getName());
     item.setLocation(productRequest.getLocation());
     item.setQty(productRequest.getQty());
     item.setPricePerItem(productRequest.getPricePerItem());
     Set<ProductDistributor> distributors = new HashSet<>();
     Set<Long> l_distributors = productRequest.getDistributor();

     for (Long distributor_id : l_distributors)
       distributors.add(productDistributorRepository.findById(distributor_id).orElseThrow(() -> new ServiceException("Error: Distributer is not found.")));
     item.setDistributor(distributors);

     return productRepository.save(item);
  }


  public Boolean deleteItem(Long id)
  {
     productRepository.deleteById(id);
     return true;
  }                                                                                                             

  public List<?> getAllDistributors() {
     return productDistributorRepository.findAll();
  }
  public ProductDistributor addItemDistributor(ReferenceRequest referenceRequest)
  {
     ProductDistributor item = new ProductDistributor(referenceRequest.getName());

    return productDistributorRepository.save(item);
  }

  public ProductDistributor updateItemDistributor(ReferenceRequest referenceRequest)
  {  
    ProductDistributor item = productDistributorRepository.findById(referenceRequest.getId()).orElseThrow(() -> new ServiceException("Error: Product distributer is not found."));

    item.setName(referenceRequest.getName());

    return productDistributorRepository.save(item);

  }
  public Boolean deleteItemDistributor(Long id)
  {
     productDistributorRepository.deleteById(id);
     return true;
  }                                                                                                             


}
