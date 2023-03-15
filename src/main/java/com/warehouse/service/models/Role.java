package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.warehouse.service.models.enums.EnumRoles;


@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private EnumRoles name;

  public Role(EnumRoles name) {
    this.name = name;
  }
/*
  public Role() {

  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EnumRoles getName() {
    return name;
  }

  public void setName(EnumRoles name) {
    this.name = name;
  }
*/
}