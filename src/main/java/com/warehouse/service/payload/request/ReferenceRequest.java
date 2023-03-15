package com.warehouse.service.payload.request;

import javax.validation.constraints.*;

import lombok.Data;



@Data
public class ReferenceRequest {
  private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;

}
