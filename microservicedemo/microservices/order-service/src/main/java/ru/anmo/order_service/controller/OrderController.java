package ru.anmo.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anmo.shared.exceptions.model.EasterEggException;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @GetMapping("/{id}")
    public Long getCustomerInformation(@PathVariable Long id) {
        throw new EasterEggException();
    }


}
