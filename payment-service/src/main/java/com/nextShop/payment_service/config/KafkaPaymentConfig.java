package com.nextShop.payment_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaPaymentConfig {
    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("payment-topic", 1, (short) 1);
    }

}
