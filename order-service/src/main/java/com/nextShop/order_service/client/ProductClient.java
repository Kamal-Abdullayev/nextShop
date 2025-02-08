package com.nextShop.order_service.client;

import com.nextShop.order_service.dto.request.product.ProductPurchaseRequestDto;
import com.nextShop.order_service.dto.response.ProductPurchaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ProductClient {
    @Value("${nextShop.config.product-service-url}")
    private String productUrl;

    private final WebClient webClient;

    public List<ProductPurchaseResponseDto> purchaseProducts(List<ProductPurchaseRequestDto> productPurchaseResponseDtoList) {
        return webClient.post()
                .uri(productUrl + "/purchase")
                .bodyValue(productPurchaseResponseDtoList)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductPurchaseResponseDto>>() {})
                .block();
    }
}
