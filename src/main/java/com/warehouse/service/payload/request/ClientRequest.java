package com.warehouse.service.payload.request;

import javax.validation.constraints.*;

import lombok.Data;


@Data
public class ClientRequest {
  private Long id;

    @NotBlank
    @Size(max = 20)
    private String firstname;

    @NotBlank
    @Size(max = 20)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String status;

}
