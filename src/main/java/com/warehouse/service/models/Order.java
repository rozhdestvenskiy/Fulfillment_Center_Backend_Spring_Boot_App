package com.warehouse.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Date;


@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String number;

    @Column(name = "order_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date orderDate;

    private BigDecimal cost;

  

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status")
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supply")
    private Supply supply;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;


    public Order(String number, Date orderDate, BigDecimal cost, OrderStatus status, Supply supply, Client client, Product product, User user) {
      this.number = number;
      this.orderDate = orderDate;
      this.cost = cost;
      this.status = status;
      this.supply = supply;
      this.client = client;
      this.product = product;
      this.user = user;
    }
}
