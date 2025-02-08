package com.nextShop.payment_service.controller;


import com.nextShop.payment_service.dto.PaymentRequest;
import com.nextShop.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.createPayment(paymentRequest), HttpStatus.CREATED);
    }


}
