package com.arthurrocha.nexus.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.GdoorProductClient;

@Service
public class ProductService {
  private final GdoorProductClient gdoorClient; 

  public ProductService(GdoorProductClient gdoorClient) {
    this.gdoorClient = gdoorClient;
  }

  public List<Product> listProducts(int page, int limit) {
    return this.gdoorClient.fetchAll(page, limit);
  }

  public Product getProductById(String id) {
    return this.gdoorClient.fetchById(id);
  }
}
