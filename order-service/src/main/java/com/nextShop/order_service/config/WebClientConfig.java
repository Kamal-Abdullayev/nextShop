package com.nextShop.order_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//@Configuration
//public class WebClientConfig {
//
//    @Value("${nextShop.config.product-service-url}")
//    private String url;
//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder().baseUrl(url).build();
//    }
//
//}

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}