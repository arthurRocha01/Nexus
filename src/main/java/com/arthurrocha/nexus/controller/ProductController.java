package com.arthurrocha.nexus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arthurrocha.nexus.application.ProductService;
import com.arthurrocha.nexus.domain.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Product>> list(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "10") int limit
  ) {
    List<Product> products = this.service.listProducts(page, limit);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable String id) {
    Product product = this.service.getProductById(id);
    return ResponseEntity.ok(product);
  }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Product product) {
      this.service.updateProduct(id, product);
      return ResponseEntity.noContent().build(); 
    }
}
