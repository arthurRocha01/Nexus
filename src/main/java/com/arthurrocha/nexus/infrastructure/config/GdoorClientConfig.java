package com.arthurrocha.nexus.infrastructure.config;

// import feign.RequestInterceptor;
// import feign.RequestTemplate;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class GdoorClientConfig {

//     @Value("${gdoor.api.token}")
//     private String apiToken;

//     @Bean
//     public RequestInterceptor gdoorRequestInterceptor() {
//         return new RequestInterceptor() {
//             @Override
//             public void apply(RequestTemplate template) {
//                 // Adiciona os headers padrões para todas as chamadas do Gdoor
//                 template.header("Authorization", "Bearer " + apiToken);
//                 template.header("Content-Type", "application/json");
//                 template.header("Accept", "application/json");
//             }
//         };
//     }
// }