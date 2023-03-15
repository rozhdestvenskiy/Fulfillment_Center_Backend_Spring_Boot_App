package com.warehouse.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.service.models.ProductDistributor;
@Repository
public interface ProductDistributorRepository extends JpaRepository<ProductDistributor, Long> {

}
