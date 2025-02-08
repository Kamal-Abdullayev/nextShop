package com.nextShop.notification_service.dto.kafka;

import com.nextShop.notification_service.dto.ProductResponse;
import com.nextShop.notification_service.dto.UserResponse;
import com.nextShop.notification_service.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderConfirmation {
    private String referenceId;
    private BigDecimal orderAmount;
    private String userId;
    private PaymentMethod paymentMethod;
    private UserResponse user;
    private List<ProductResponse> products;
}
