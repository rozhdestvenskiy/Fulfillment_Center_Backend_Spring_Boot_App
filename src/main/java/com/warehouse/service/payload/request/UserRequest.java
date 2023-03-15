package com.warehouse.service.payload.request;

import javax.validation.constraints.*;

import lombok.Data;
import java.util.Set;



@Data
public class UserRequest {
  private Long id;

  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<Long> roles;

}
