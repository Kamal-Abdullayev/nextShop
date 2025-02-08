package com.nextShop.payment_service.dto.message_broker;

import com.nextShop.payment_service.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentNotificationRequestDto {
    private String orderReferenceId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
}
