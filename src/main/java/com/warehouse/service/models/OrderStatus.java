package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.warehouse.service.models.enums.EnumOrderStatus;


@Data
@Entity
@Table(name = "order_status")
@NoArgsConstructor
public class OrderStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private EnumOrderStatus name;

}