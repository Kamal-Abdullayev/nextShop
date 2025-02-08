package com.nextShop.notification_service.dto.kafka;

import com.nextShop.notification_service.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentConfirmation {
    private String orderReferenceId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String userFirstName;
    private String userLastName;
    private String userEmail;

}
