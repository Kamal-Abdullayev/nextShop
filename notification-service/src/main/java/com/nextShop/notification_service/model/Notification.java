package com.nextShop.notification_service.model;

import com.nextShop.notification_service.dto.kafka.PaymentConfirmation;
import com.nextShop.notification_service.dto.kafka.OrderConfirmation;
import com.nextShop.notification_service.model.enums.NotificationType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Notification {
    @MongoId
    private String id;
    private NotificationType type;
    private LocalDateTime date;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
