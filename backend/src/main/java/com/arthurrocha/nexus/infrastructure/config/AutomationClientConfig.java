package com.arthurrocha.nexus.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AutomationClientConfig {
  
  @Value("${automation.api.url}")
  private String baseUrl;

  @Bean(name = "automationRestClient")
  public RestClient automationClient() {
    return RestClient.builder()
      .baseUrl(this.baseUrl)
      .defaultHeader("Content-Type", "application/json")
      .build();
  }
}
