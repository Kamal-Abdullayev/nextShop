package com.nextShop.order_service.client;

import com.nextShop.order_service.dto.request.payment.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@RequiredArgsConstructor
@Configuration
public class PaymentClient {
    @Value("${nextShop.config.payment-service-url}")
    private String paymentServiceUrl;

    private final WebClient webClient;

    public String requestOrderPayment(PaymentRequestDto paymentRequestDto) {
        return webClient.post()
                .uri(paymentServiceUrl)
                .bodyValue(paymentRequestDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
