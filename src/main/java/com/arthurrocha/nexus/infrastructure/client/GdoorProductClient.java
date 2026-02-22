package com.arthurrocha.nexus.infrastructure.client;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorFetchAllResponse;
import com.arthurrocha.nexus.infrastructure.client.mapper.GdoorProductMapper;

@Component
public class GdoorProductClient {
  
  private final RestClient restClient;
  private final GdoorProductMapper productMapper;

  public GdoorProductClient(RestClient restClient, GdoorProductMapper productMapper) {
    this.restClient = restClient;
    this.productMapper = productMapper;
  }

  public List<Product> fetchAll() {
    GdoorFetchAllResponse response = this.restClient.get()
      .uri("/products")
      .retrieve()
      .body(GdoorFetchAllResponse.class);

      if (response == null || response.data() == null) {
        return Collections.emptyList();
      }

      return response.data().stream()
        .map(productMapper::toDomain)
        .toList();
  }
}