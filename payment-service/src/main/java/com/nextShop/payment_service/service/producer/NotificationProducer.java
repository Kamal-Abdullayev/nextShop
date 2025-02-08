package com.nextShop.payment_service.service.producer;

import com.nextShop.payment_service.dto.message_broker.PaymentNotificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequestDto> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequestDto notification) {
        log.info("Send notification: {}", notification);
        Message<PaymentNotificationRequestDto> messageBuilder = MessageBuilder.withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();

        kafkaTemplate.send(messageBuilder);
    }
}
