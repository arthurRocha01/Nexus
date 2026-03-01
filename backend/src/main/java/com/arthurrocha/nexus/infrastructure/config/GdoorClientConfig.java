package com.arthurrocha.nexus.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GdoorClientConfig {
  
  @Value("${gdoor.api.url}")
  private String baseUrl;

  @Value("${gdoor.api.token}")
  private String token;


  @Bean()
  public RestClient gdoorClient() {
    return RestClient.builder()
      .baseUrl(this.baseUrl)
      .defaultHeader("Accept", "application/json")
      .defaultHeader("Authorization", "Bearer " + this.token)
      .build();
  }
}