package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String name;

  @NotBlank
  @Size(max = 50)
  private String location;

  private Integer qty;

  private BigDecimal pricePerItem;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "products_and_distributors",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "distributor_id"))
  private Set<ProductDistributor> distributor = new HashSet<>();

  public Product(String name, String location, Integer qty, BigDecimal pricePerItem) {
      this.name = name;
      this.location = location;
      this.qty = qty;
      this.pricePerItem = pricePerItem;
  }

}
