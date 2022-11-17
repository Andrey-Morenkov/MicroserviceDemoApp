package ru.anmo.api_gateway.repository.product_repository;

import java.ru.anmo.shared.api.product_service.ProductServiceResponse;

public interface IProductRepository {
    ProductServiceResponse getProductInfo(Long productId);
}
