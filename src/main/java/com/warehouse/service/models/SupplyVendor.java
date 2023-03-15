package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import com.warehouse.service.models.enums.EnumSupplyVendors;



@Data // Experimenting with Lombok Library
@Entity
@Table(name = "supply_vendors")
@NoArgsConstructor // Experimenting with Lombok Library
public class SupplyVendor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String name;

  public SupplyVendor(String name) {
      this.name = name;
  }


}