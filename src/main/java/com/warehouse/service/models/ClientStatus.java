package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.warehouse.service.models.enums.EnumClientStatus;

@Data
@Entity
@Table(name = "client_status")
@NoArgsConstructor
public class ClientStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private EnumClientStatus name;


}