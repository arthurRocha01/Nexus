package com.arthurrocha.nexus.domain;

import java.math.BigDecimal;

public class Product {
  private String id;
  private String description;
  private int quantity;
  private BigDecimal price;
  private String ncm;
  private String barcode;

  private Product(Builder builder) {
    this.id = builder.id;
    this.description = builder.description;
    this.quantity = builder.quantity;
    this.price = builder.price;
    this.ncm = builder.ncm;
    this.barcode = builder.barcode;
  }

  public static class Builder {
        private String id;
        private String description;
        private int quantity;
        private BigDecimal price;
        private String ncm;
        private String barcode;

        public Builder(String id, String description) {
            this.id = id;
            this.description = description;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder ncm(String ncm) {
            this.ncm = ncm;
            return this;
        }

        public Builder barcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
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

  public String getBarcode() {
    return barcode;
  }
}
