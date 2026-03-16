package com.arthurrocha.nexus.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Product.Builder.class)
public class Product {
    private String id;
    private String description;
    private int quantity;
    private BigDecimal price;
    private BigDecimal costPrice;
    private String ncm;
    private String barcode;
    
    private Product(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.costPrice = builder.costPrice;
        this.ncm = builder.ncm;
        this.barcode = builder.barcode;
    }
    
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String id;
        private String description;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal costPrice;
        private String ncm;
        private String barcode;
        
        @JsonCreator
        public Builder(
            @JsonProperty("id") String id, 
            @JsonProperty("description") String description) 
            {
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

            public Builder costPrice(BigDecimal costPrice) {
                this.costPrice = costPrice;
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
        
        public Integer getQuantity() {
            return quantity;
        }
        
        public BigDecimal getPrice() {
            return price;
        }

        public BigDecimal getCostPrice() {
            return costPrice;
        }
        
        public String getNcm() {
            return ncm;
        }
        
        public String getBarcode() {
            return barcode;
        }
    }
    