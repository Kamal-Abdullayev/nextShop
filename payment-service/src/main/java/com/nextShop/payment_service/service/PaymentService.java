package com.nextShop.payment_service.service;


import com.nextShop.payment_service.dto.message_broker.PaymentNotificationRequestDto;
import com.nextShop.payment_service.dto.PaymentRequest;
import com.nextShop.payment_service.model.Payment;
import com.nextShop.payment_service.repository.PaymentRepository;
import com.nextShop.payment_service.service.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    public String createPayment(PaymentRequest paymentRequest) {
        Payment payment = Payment.builder()
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .orderId(paymentRequest.getOrderId())
                .build();

        notificationProducer.sendNotification(PaymentNotificationRequestDto.builder()
                .orderReferenceId(paymentRequest.getOrderReference())
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .userFirstName(paymentRequest.getUser().getFirstName())
                .userLastName(paymentRequest.getUser().getLastName())
                .userEmail(paymentRequest.getUser().getEmail())
                .build());
        return paymentRepository.save(payment).getOrderId();
    }
}
