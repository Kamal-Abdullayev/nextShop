package com.nextShop.order_service.repository;

import com.nextShop.order_service.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findAllByOrderId(String orderId);
}
