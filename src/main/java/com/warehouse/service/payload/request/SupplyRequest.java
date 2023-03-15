package com.warehouse.service.payload.request;

import javax.validation.constraints.*;

import lombok.Data;
import java.util.Set;


@Data
public class SupplyRequest {
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String size;

  private Integer weightLimit;

  private Integer qty;

  @NotBlank
  @Size(max = 120)
  private String type;

  private Set<Long> vendorSupplies;

}
