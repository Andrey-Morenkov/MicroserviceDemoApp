package ru.anmo.customer_service.controller

import org.springframework.web.bind.annotation.*
import java.ru.anmo.shared.exceptions.model.EasterEggException
import java.ru.anmo.shared.exceptions.model.IllegalIdException
import ru.anmo.customer_service.service.ICustomerService
import java.ru.anmo.shared.api.customer_service.CustomerServiceResponse

@RestController
@RequestMapping("/api/v1/users")
class CustomerController(private val customerService: ICustomerService) {

    @GetMapping("/{id}")
    fun getCustomerInformation(@PathVariable id: Long): CustomerServiceResponse {
        if (id <= 0L) {
            throw IllegalIdException("Customer ID should be > 0, received id = $id")
        }
        if (id == 42L) {
            throw EasterEggException()
        }

        return customerService.getCustomerInformation(id)
    }
}


