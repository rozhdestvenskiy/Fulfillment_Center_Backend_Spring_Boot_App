package com.warehouse.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.warehouse.service.models.OrderStatus;
import com.warehouse.service.models.enums.EnumOrderStatus;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
  Optional<OrderStatus> findByName(EnumOrderStatus name);
}
