package com.nextShop.order_service.controller;

import com.nextShop.order_service.dto.response.OrderDetailResponseDto;
import com.nextShop.order_service.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDetailResponseDto>> findOrderDetailsByOrderId(
            @PathVariable("id") String orderId) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailsByOrderId(orderId), HttpStatus.OK);
    }

}
