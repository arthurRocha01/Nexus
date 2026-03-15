package com.arthurrocha.nexus.infrastructure.client.gdoor;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.infrastructure.client.gdoor.dto.GdoorFetchAllResponse;
import com.arthurrocha.nexus.infrastructure.client.gdoor.dto.GdoorFetchOneResponse;
import com.arthurrocha.nexus.infrastructure.client.gdoor.dto.GdoorProductDto;
import com.arthurrocha.nexus.infrastructure.client.gdoor.mapper.GdoorProductMapper;

@Component
public class GdoorProductClient {
    
    private final RestClient restClient;
    private final GdoorProductMapper productMapper;
    
    public GdoorProductClient(@Qualifier("gdoorRestClient") RestClient restClient, GdoorProductMapper productMapper) {
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
        
        System.out.println(response.data());
        
        return response.data().stream()
        .map(productMapper::toDomainFromSummary)
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
        
        GdoorFetchOneResponse response = this.restClient.get()
        .uri("/products/{id}", id)
        .retrieve()
        .body(GdoorFetchOneResponse.class);
        
        if (response == null || response.data() == null) {
            throw new RuntimeException("Falha ao buscar produto na GDoor para atualização. ID: " + id);
        }
        
        GdoorProductDto externalProduct = response.data();
        
        this.productMapper.updateExternalFromDomain(product, externalProduct);
        
        this.restClient.put()
        .uri("/products/{id}", id)
        .body(externalProduct)
        .retrieve()
        .toBodilessEntity();
    }
}