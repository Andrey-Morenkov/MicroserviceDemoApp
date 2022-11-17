package ru.anmo.api_gateway.repository.product_repository;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.ru.anmo.shared.api.product_service.ProductServiceResponse;
import java.ru.anmo.shared.exceptions.model.IllegalIdException;
import java.ru.anmo.shared.exceptions.model.ProductNotFoundException;

import java.io.IOException;

@Repository
public class ProductRepository implements IProductRepository{

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8083/api/v1/products/";

    @Override
    public ProductServiceResponse getProductInfo(Long productId) {

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
                    case NOT_FOUND -> throw new ProductNotFoundException(response.toString());
                    default -> throw new RuntimeException("Good enough for demo, something went wrong: " + response);
                }
            }
        });

        return restTemplate.getForObject(PRODUCT_SERVICE_URL + productId, ProductServiceResponse.class);
    }
}
