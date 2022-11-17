package ru.anmo.api_gateway.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anmo.api_gateway.repository.customer_repository.ICustomerRepository;
import ru.anmo.api_gateway.repository.order_repository.IOrderRepository;
import ru.anmo.api_gateway.repository.product_repository.IProductRepository;
import java.ru.anmo.shared.api.api_gateway.ApiGatewayResponse;
import java.ru.anmo.shared.api.customer_service.CustomerServiceResponse;

import java.ru.anmo.shared.api.order_service.OrderResponse;
import java.ru.anmo.shared.api.product_service.ProductServiceResponse;
import java.ru.anmo.shared.exceptions.model.InternalException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Service
public class ApiGatewayService implements IApiGatewayService {

    private ICustomerRepository customerRepository;
    private IOrderRepository orderRepository;
    private IProductRepository productRepository;

    @Override
    public ApiGatewayResponse processCustomerQuery(Long customerId, String userAgent) {

        CompletableFuture<CustomerServiceResponse> customerInfo = asyncGetCustomerInfo(customerId, userAgent);
        CompletableFuture<Map<Long, ProductServiceResponse>> ordersWithProductsInfo = getOrdersOfCustomer(customerId)
                                                                                        .thenCompose(this::getProductsForOrders);

        try {
            return customerInfo
                    .thenCombine(ordersWithProductsInfo, (customerData, ordersData) -> {
                        List<ApiGatewayResponse.OrderInfo> orderInfos = new ArrayList<>();
                        ordersData.forEach((key, value) -> {
                            orderInfos.add(new ApiGatewayResponse.OrderInfo(key, value.getId(), value.getTitle()));
                        });
                        return new ApiGatewayResponse(customerData.getName(), orderInfos);
                    })
                    .get();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new InternalException(e.getMessage());
        }
    }


    private CompletableFuture<CustomerServiceResponse> asyncGetCustomerInfo(final Long customerId, String userAgent) {
        return CompletableFuture.supplyAsync(() -> customerRepository.getCustomerInfo(customerId, userAgent));
    }

    private CompletableFuture<List<OrderResponse>> getOrdersOfCustomer(final Long customerId) {
        return CompletableFuture.supplyAsync(() -> orderRepository.getCustomerOrders(customerId));
    }

    private CompletableFuture<Map<Long, ProductServiceResponse>> getProductsForOrders(final List<OrderResponse> orderResponses) {
        return CompletableFuture.supplyAsync(() -> {
                    Map<Long, ProductServiceResponse> result = new HashMap<>();

                    for (OrderResponse order: orderResponses) {
                        ProductServiceResponse response = productRepository.getProductInfo(order.getProductId());
                        result.put(order.getId(), response);
                    }

                    return result;
                });
    }
}
