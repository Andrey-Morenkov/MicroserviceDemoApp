package ru.anmo.api_gateway.repository.order_repository;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.ru.anmo.shared.api.order_service.OrderResponse;
import java.ru.anmo.shared.exceptions.model.CustomerNotFoundException;
import java.ru.anmo.shared.exceptions.model.IllegalIdException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository{

    private static final String ORDER_SERVICE_URL = "http://localhost:8082/api/v1/orders/";

    @Override
    public List<OrderResponse> getCustomerOrders(Long customerId) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode().isError();
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                switch (response.getStatusCode()) {
                    case BAD_REQUEST -> throw new IllegalIdException(response.toString());
                    case NOT_FOUND -> throw new CustomerNotFoundException(response.toString());
                    default -> throw new RuntimeException("Good enough for demo, something went wrong: " + response);
                }
            }
        });

        return Arrays.stream(restTemplate.getForEntity(ORDER_SERVICE_URL + customerId, OrderResponse[].class).getBody()).toList();
    }
}
