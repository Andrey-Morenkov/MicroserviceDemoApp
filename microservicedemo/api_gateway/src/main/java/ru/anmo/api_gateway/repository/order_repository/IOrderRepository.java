package ru.anmo.api_gateway.repository.order_repository;

import java.ru.anmo.shared.api.order_service.OrderResponse;

import java.util.List;

public interface IOrderRepository {
    List<OrderResponse> getCustomerOrders(Long customerId);
}
