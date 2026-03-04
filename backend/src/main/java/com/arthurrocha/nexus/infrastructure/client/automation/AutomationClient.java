package com.arthurrocha.nexus.infrastructure.client.automation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.arthurrocha.nexus.infrastructure.client.automation.dto.AutomationAuthResponseDto;

@Component
public class AutomationClient {
  
  private final RestClient restClient;

  public AutomationClient(@Qualifier("automationRestClient") RestClient restClient) {
    this.restClient = restClient;
  }

  public String fetchNewGdoorToken() {
    AutomationAuthResponseDto response = this.restClient.get()
      .uri("/auth/gdoor-token")
      .retrieve()
      .body(AutomationAuthResponseDto.class);

      if (response == null || response.getJwt_token() == null) {
        return null;
      }

      return response.getJwt_token();
  }
}
