package com.arthurrocha.nexus.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GdoorClientConfig {
    
    @Value("${gdoor.api.url}")
    private String baseUrl;
    
    private final GdoorTokenManager tokenManager;
    
    public GdoorClientConfig(GdoorTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
    
    @Bean(name = "gdoorRestClient")
    public RestClient gdoorClient() {
        return RestClient.builder()
        .baseUrl(this.baseUrl)
        .defaultHeader("Content-Type", "application/json")
        .requestInterceptor((request, body, execution) -> {
            String token = this.tokenManager.getCurrentToken();
            
            if (token != null && !token.isEmpty()) {
                request.getHeaders().setBearerAuth(token);
            }
            
            return execution.execute(request, body);
        })
        .build();
    }
}