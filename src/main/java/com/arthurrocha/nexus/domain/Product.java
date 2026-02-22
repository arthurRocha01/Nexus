package com.arthurrocha.nexus.domain;

import java.math.BigDecimal;

public class Product {
  private String id;
  private String description;
  private int quantity;
  private BigDecimal price;
  private String ncm;

  private Product(String id, String description, int quantity, BigDecimal price, String ncm) {
    this.id = id;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
    this.ncm = ncm;
  }

  public static Product create(String id, String description, int quantity, BigDecimal price, String ncm) {
    return new Product(id, description, quantity, price, ncm);
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
    }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getNcm() {
    return ncm;
  }
}
