package ru.anmo.api_gateway.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.anmo.api_gateway.service.ApiGatewayService;
import java.ru.anmo.shared.api.api_gateway.ApiGatewayResponse;
import java.ru.anmo.shared.exceptions.model.EasterEggException;
import java.ru.anmo.shared.exceptions.model.IllegalIdException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class ApiGatewayController {

    private final ApiGatewayService apiGatewayService;

    @ApiOperation(value = "Main method to get all data according current task", response = ApiGatewayResponse.class, code = 200)
    @GetMapping
    public ApiGatewayResponse getTotalClientInfo(@RequestHeader(value = "User-Agent") String userAgent,
                                                 @RequestParam(value = "client_id", required = true) Long clientId) {
        if (clientId <= 0L) {
            throw new IllegalIdException("Customer ID should be > 0, received id = $id");
        }
        if (clientId == 42L) {
            throw new EasterEggException();
        }

        ApiGatewayResponse response = apiGatewayService.processCustomerQuery(clientId, userAgent);
        if (response == null) {
            throw new RuntimeException("Something went wrong");
        }
        return response;
    }
}
