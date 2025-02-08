package com.nextShop.order_service.service;

import com.nextShop.order_service.client.PaymentClient;
import com.nextShop.order_service.client.ProductClient;
import com.nextShop.order_service.client.UserClient;
import com.nextShop.order_service.dto.message_broker.OrderConfirmationDto;
import com.nextShop.order_service.dto.request.order.OrderCreateRequestDto;
import com.nextShop.order_service.dto.request.order_detail.OrderDetailCreatRequestDto;
import com.nextShop.order_service.dto.request.payment.PaymentRequestDto;
import com.nextShop.order_service.dto.request.user.UserRequestDto;
import com.nextShop.order_service.dto.response.ProductOrderResponseDto;
import com.nextShop.order_service.dto.response.ProductPurchaseResponseDto;
import com.nextShop.order_service.dto.response.UserResponseDto;
import com.nextShop.order_service.exception.ResourceNotFoundException;
import com.nextShop.order_service.model.Order;
import com.nextShop.order_service.repository.OrderRepository;
import com.nextShop.order_service.service.producer.OrderProducerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final UserClient userClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final OrderProducerService orderProducerService;
    private final PaymentClient paymentClient;

    @Transactional
    public String createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        log.info("Create Order request: {}", orderCreateRequestDto);

        UserResponseDto dbUser = userClient.getUserById(UserRequestDto.builder()
                        .userId(orderCreateRequestDto.getUserId())
                        .build())
                .orElseThrow(() -> {
                    log.error("User not found with id {}", orderCreateRequestDto.getUserId());
                    return new ResourceNotFoundException("User not found");
                });
        log.info("User found with email: {}, username: {}", dbUser.getEmail(), dbUser.getUsername());

        log.info("Purchase the following products: {}", orderCreateRequestDto.getProducts());
        List<ProductPurchaseResponseDto> purchaseProducts = productClient.purchaseProducts(orderCreateRequestDto.getProducts());
        log.info("List of purchased products: {}", purchaseProducts);

        Order newOrder = Order.builder()
                .referenceId(orderCreateRequestDto.getReferenceId())
                .totalPrice(orderCreateRequestDto.getTotalPrice())
                .userId(orderCreateRequestDto.getUserId())
                .paymentMethod(orderCreateRequestDto.getPaymentMethod())
                .updatedAt(LocalDateTime.now())
                .build();

        Order order = orderRepository.save(newOrder);

        purchaseProducts.forEach(product -> {
            orderDetailService.saveOrderDetail(OrderDetailCreatRequestDto.builder()
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .discountPrice(product.getDiscountPrice())
                    .productName(product.getName())
                    .orderId(order.getId())
                    .build()
            );

        });


        paymentClient.requestOrderPayment(PaymentRequestDto.builder()
                        .orderId(order.getId())
                        .amount(orderCreateRequestDto.getTotalPrice())
                        .paymentMethod(orderCreateRequestDto.getPaymentMethod())
                        .orderReference(order.getReferenceId())
                        .user(dbUser)
                .build());

        UserResponseDto user = UserResponseDto.builder()
                .firstName(dbUser.getFirstName())
                .lastName(dbUser.getLastName())
                .email(dbUser.getEmail())
                .username(dbUser.getUsername())
                .phoneNumber(dbUser.getPhoneNumber())
                .build();
        orderProducerService.sendOrderConfirmation(
                OrderConfirmationDto.builder()
                        .referenceId(orderCreateRequestDto.getReferenceId())
                        .userId(orderCreateRequestDto.getUserId())
                        .paymentMethod(orderCreateRequestDto.getPaymentMethod())
                        .orderAmount(orderCreateRequestDto.getTotalPrice())
                        .user(user)
                        .products(purchaseProducts)
                        .build()
        );

        return order.getId();
    }

    public List<ProductOrderResponseDto> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).stream()
                .map(ProductOrderResponseDto::convert)
                .toList();
    }

    public ProductOrderResponseDto findOrderById(String orderId) {
        return ProductOrderResponseDto.convert(findOrderObjectById(orderId));
    }

    public Order findOrderObjectById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }


}
