package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import com.warehouse.service.models.enums.EnumProductDistributors;


@Data
@Entity
@Table(name = "product_distributors")
@NoArgsConstructor
public class ProductDistributor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String name;

  public ProductDistributor(String name) {
      this.name = name;
  }
}