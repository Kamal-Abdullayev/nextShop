package com.nextShop.order_service.controller;

import com.nextShop.order_service.dto.request.order.OrderCreateRequestDto;
import com.nextShop.order_service.dto.response.ProductOrderResponseDto;
import com.nextShop.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(
            @Valid @RequestBody OrderCreateRequestDto order
    ) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductOrderResponseDto>> getAllOrders(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2" ) int size
    ) {
        return new ResponseEntity<>(orderService.findAllOrders(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOrderResponseDto> getOrderById(@PathVariable("id") String orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }
}
