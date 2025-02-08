package com.nextShop.order_service.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String productName;
    private double quantity;
    private double price;
    private double discountPrice;
    // TODO: add other fields

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
