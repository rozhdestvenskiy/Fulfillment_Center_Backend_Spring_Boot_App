package com.warehouse.service.payload.request;

import javax.validation.constraints.*;

import lombok.Data;
import java.util.Set;


import java.math.BigDecimal;
import java.sql.Date;


@Data
public class OrderRequest {
  private Long id;

  @NotBlank
  @Size(min = 1, max = 20)
  private String number;

  private Date orderDate;

  private BigDecimal cost;

  private String status;


  private Long supply;

  private Long client;

  private Long product;
}
