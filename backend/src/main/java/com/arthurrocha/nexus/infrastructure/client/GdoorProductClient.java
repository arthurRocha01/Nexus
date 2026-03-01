package com.arthurrocha.nexus.infrastructure.client;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorFetchAllResponse;
import com.arthurrocha.nexus.infrastructure.client.dto.GdoorFetchOneResponse;
import com.arthurrocha.nexus.infrastructure.client.mapper.GdoorProductMapper;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

@Component
public class GdoorProductClient {
  
  private final RestClient restClient;
  private final GdoorProductMapper productMapper;

  public GdoorProductClient(RestClient restClient, GdoorProductMapper productMapper) {
    this.restClient = restClient;
    this.productMapper = productMapper;
  }

  public List<Product> fetchAll(int page, int limit) {
    GdoorFetchAllResponse response = this.restClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/products")
        .queryParam("page", page)
        .queryParam("limit", limit)
        .build()
      )
      .retrieve()
      .body(GdoorFetchAllResponse.class);

      if (response == null || response.data() == null) {
        return Collections.emptyList();
      }

      return response.data().stream()
        .map(productMapper::toDomain)
        .toList();
  }

  public Product fetchById(String id) {
    GdoorFetchOneResponse response = this.restClient.get()
      .uri("/products/{id}", id)
      .retrieve()
      .body(GdoorFetchOneResponse.class);

      if (response == null) {
        return null;
      }

      return this.productMapper.toDomain(response.data());
  }

  public void update(Product product) {
    String id = product.getId();

    JsonNode rootNode = this.restClient.get()
      .uri("/products/{id}", id)
      .retrieve()
      .body(JsonNode.class);

      ObjectNode originalDataNode = (ObjectNode) rootNode.get("data");

      ObjectNode updatedDataNode = productMapper.fromDomain(originalDataNode, product);
      System.out.println(updatedDataNode);

      this.restClient.put()
        .uri("/products/{id}", id)
        .body(updatedDataNode)
        .retrieve()
        .toBodilessEntity();
  }
}