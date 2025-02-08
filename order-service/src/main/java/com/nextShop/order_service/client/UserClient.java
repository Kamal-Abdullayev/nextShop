package com.nextShop.order_service.client;

import com.nextShop.order_service.dto.request.product.ProductPurchaseRequestDto;
import com.nextShop.order_service.dto.request.user.UserRequestDto;
import com.nextShop.order_service.dto.response.ProductPurchaseResponseDto;
import com.nextShop.order_service.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class UserClient {
    @Value("${nextShop.config.user-service-url}")
    private String userServiceUrl;

    private final WebClient webClient;

    public Optional<UserResponseDto> getUserById(UserRequestDto userRequestDto) {
        return Optional.ofNullable(
                webClient.get()
                        .uri(userServiceUrl + "/" + userRequestDto.getUserId())
                        .retrieve()
                        .bodyToMono(UserResponseDto.class)
                        .block()
        );
    }

}
