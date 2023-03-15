package com.warehouse.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.service.models.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
