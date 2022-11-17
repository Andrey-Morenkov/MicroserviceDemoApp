package ru.anmo.api_gateway.service.mappers;

import lombok.Builder;
import lombok.Getter;
import java.ru.anmo.shared.api.customer_service.CustomerServiceResponse;
import java.ru.anmo.shared.api.order_service.OrderResponse;
import java.ru.anmo.shared.api.product_service.ProductServiceResponse;

import java.util.List;

@Builder
@Getter
public class RepositoriesResponseToApiGatewayResponseMapper {

    private final CustomerServiceResponse customerServiceResponse;
    private final List<OrderResponse> orderServiceResponse;
    private final ProductServiceResponse productServiceResponse;
}
