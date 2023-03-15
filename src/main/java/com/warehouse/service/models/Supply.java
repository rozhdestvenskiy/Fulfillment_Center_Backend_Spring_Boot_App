package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data // Experimenting with Lombok Library
@Entity
@Table(name = "supplies")
@NoArgsConstructor  // Experimenting with Lombok Library
public class Supply {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String size;

  private Integer weightLimit;

  private Integer qty;

  @NotBlank
  @Size(max = 120)
  private String type;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "vendors_and_supplies",
        joinColumns = @JoinColumn(name = "vendor_id"),
        inverseJoinColumns = @JoinColumn(name = "supply_id"))
  private Set<SupplyVendor> vendorSupplies = new HashSet<>();

  public Supply(String size, Integer weightLimit, Integer qty, String type) {
      this.size = size;
      this.weightLimit = weightLimit;
      this.qty = qty;
      this.type = type;
  }


}
