package com.nextShop.notification_service.service;

import com.nextShop.notification_service.dto.kafka.PaymentConfirmation;
import com.nextShop.notification_service.dto.kafka.OrderConfirmation;
import com.nextShop.notification_service.model.Notification;
import com.nextShop.notification_service.model.enums.NotificationType;
import com.nextShop.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Received Payment Confirmation: {}", paymentConfirmation);

        Notification notification = Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .date(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();

        notificationRepository.save(notification);


        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getUserEmail(),
                paymentConfirmation.getUserFirstName() + " " + paymentConfirmation.getUserLastName(),
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReferenceId(),
                paymentConfirmation.getPaymentMethod().name()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        log.info("Received Payment Confirmation: {}", orderConfirmation);

        Notification notification = Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .date(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();

        notificationRepository.save(notification);
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getUser().getEmail(),
                orderConfirmation.getUser().getFirstName() + " " + orderConfirmation.getUser().getLastName(),
                orderConfirmation.getOrderAmount(),
                orderConfirmation.getReferenceId(),
                orderConfirmation.getProducts()
        );
    }
}
