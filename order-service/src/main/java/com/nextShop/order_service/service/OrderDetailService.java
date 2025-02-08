package com.nextShop.order_service.service;

import com.nextShop.order_service.dto.request.order_detail.OrderDetailCreatRequestDto;
import com.nextShop.order_service.dto.response.OrderDetailResponseDto;
import com.nextShop.order_service.model.OrderDetail;
import com.nextShop.order_service.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public String saveOrderDetail(OrderDetailCreatRequestDto orderDetail) {

        OrderDetail newOrderDetail = OrderDetail.builder()
                .productName(orderDetail.getProductName())
                .price(orderDetail.getPrice())
                .discountPrice(orderDetail.getDiscountPrice())
                .quantity(orderDetail.getQuantity())
                .build();

        return orderDetailRepository.save(newOrderDetail).getId();
    }

    public List<OrderDetailResponseDto> findOrderDetailsByOrderId(String orderId) {
        return orderDetailRepository.findAllByOrderId(orderId).stream()
                .map(OrderDetailResponseDto::convert)
                .toList();
    }


}
