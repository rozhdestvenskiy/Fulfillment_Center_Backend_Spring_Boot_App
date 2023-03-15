package com.warehouse.service.payload.request;

import javax.validation.constraints.*;

import lombok.Data;
import java.util.Set;

import java.math.BigDecimal;
import java.sql.Date;


@Data
public class ProductRequest {
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String name;

  @NotBlank
  @Size(max = 50)
  private String location;

  private Integer qty;

  private BigDecimal pricePerItem;

  private Set<Long> distributor;

}
