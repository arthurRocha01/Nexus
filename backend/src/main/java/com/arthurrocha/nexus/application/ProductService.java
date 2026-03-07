package com.arthurrocha.nexus.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.automation.AutomationClient;
import com.arthurrocha.nexus.infrastructure.client.gdoor.GdoorProductClient;

@Service
public class ProductService {
  private final GdoorProductClient gdoorClient;
  private final AutomationClient automationClient;


  public ProductService(GdoorProductClient gdoorClient, AutomationClient automationClient) {
    this.gdoorClient = gdoorClient;
    this.automationClient = automationClient;
  
  }

  public List<Product> findAll(int page, int limit) {
    return this.gdoorClient.fetchAll(page, limit);
  }

  public Product findById(String id) {
    return this.gdoorClient.fetchById(id);
  }

  public void update(String id, Product productData) {
    this.gdoorClient.update(productData);
  }

  public Product checkPriceMatch(String id) {
    Product product = this.gdoorClient.fetchById(id);
    return this.automationClient.checkPriceMatch(product);
  }
}
