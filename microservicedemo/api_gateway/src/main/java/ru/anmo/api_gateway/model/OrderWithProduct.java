package ru.anmo.api_gateway.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderWithProduct {
    private Long orderId;
    private Long productId;
    private String productTitle;
}
