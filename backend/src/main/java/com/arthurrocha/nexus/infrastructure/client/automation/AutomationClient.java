package com.arthurrocha.nexus.infrastructure.client.automation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.arthurrocha.nexus.domain.Product;
import com.arthurrocha.nexus.domain.ProductMatchResult;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationAuthResponseDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceRequestDto;
import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationCheckPriceResponseDto;
import com.arthurrocha.nexus.infrastructure.client.automation.mapper.AutomationProductMapper;

@Component
public class AutomationClient {
  
  private final RestClient restClient;
  private final AutomationProductMapper productMapper;

  public AutomationClient(@Qualifier("automationRestClient") RestClient restClient, AutomationProductMapper productMapper) {
    this.restClient = restClient;
    this.productMapper = productMapper;
  }

  public String fetchGdoorToken() {
    AutomationAuthResponseDto response = this.restClient.get()
      .uri("/auth/gdoor-token")
      .retrieve()
      .body(AutomationAuthResponseDto.class);

      if (response == null || response.getJwt_token() == null) {
        return null;
      }

      return response.getJwt_token();
  }

  public ProductMatchResult checkPriceMatch(Product product) {
    AutomationCheckPriceRequestDto requestBody = this.productMapper.toRequestDto(product);

    AutomationCheckPriceResponseDto response = this.restClient.post()
      .uri("/inventory/check-price")
      .body(requestBody)
      .retrieve()
      .body(AutomationCheckPriceResponseDto.class);

      if (response == null) {
        throw new RuntimeException("Falha ao buscar produto em Automation.");
      }

      return this.productMapper.toMatchResult(response, product);
  }
}
