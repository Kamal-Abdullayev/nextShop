package com.nextShop.order_service.service.producer;

import com.nextShop.order_service.dto.message_broker.OrderConfirmationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducerService {
    private final KafkaTemplate<String, OrderConfirmationDto> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmationDto orderConfirmationDto) {
        log.info("Send order confirmation: {}", orderConfirmationDto);
        Message<OrderConfirmationDto> messageBuilder = MessageBuilder.withPayload(orderConfirmationDto)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(messageBuilder);

    }
}
