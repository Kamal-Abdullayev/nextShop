package com.nextShop.order_service.dto.request.order;

import com.nextShop.order_service.dto.request.product.ProductPurchaseRequestDto;
import com.nextShop.order_service.model.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequestDto {
    private String referenceId;
    private BigDecimal totalPrice;
    private String userId;
    private PaymentMethod paymentMethod;

    List<ProductPurchaseRequestDto> products;

}
