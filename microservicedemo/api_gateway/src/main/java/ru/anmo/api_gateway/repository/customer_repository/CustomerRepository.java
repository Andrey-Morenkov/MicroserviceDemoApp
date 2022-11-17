package ru.anmo.api_gateway.repository.customer_repository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.ru.anmo.shared.api.customer_service.CustomerServiceResponse;
import java.ru.anmo.shared.customer.CustomerType;
import java.ru.anmo.shared.exceptions.model.CustomerNotFoundException;
import java.ru.anmo.shared.exceptions.model.IllegalIdException;

import java.io.IOException;
import java.ru.anmo.shared.exceptions.model.InternalException;

@Repository
public class CustomerRepository implements ICustomerRepository{

    private static final String CUSTOMER_SERVICE_URL = "http://localhost:8081/api/v1/users/";

    @Override
    public CustomerServiceResponse getCustomerInfo(Long customerId, String userAgent) {

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
                    default -> throw new InternalException("Good enough for demo, something went wrong: " + response);
                }
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", userAgent);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(CUSTOMER_SERVICE_URL + customerId, HttpMethod.GET, requestEntity, CustomerServiceResponse.class).getBody();
    }
}
