package ru.anmo.api_gateway.repository.customer_repository;

import java.ru.anmo.shared.api.customer_service.CustomerServiceResponse;
import java.ru.anmo.shared.customer.CustomerType;

public interface ICustomerRepository {
    CustomerServiceResponse getCustomerInfo(Long customerId, String userAgent);
}
