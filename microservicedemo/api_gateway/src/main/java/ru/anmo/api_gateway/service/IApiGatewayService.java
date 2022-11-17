package ru.anmo.api_gateway.service;

import java.ru.anmo.shared.api.api_gateway.ApiGatewayResponse;
import java.ru.anmo.shared.customer.CustomerType;

public interface IApiGatewayService {
    ApiGatewayResponse processCustomerQuery(Long customerId, String userAgent);
}
