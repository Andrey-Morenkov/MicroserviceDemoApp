package ru.anmo.customer_service.service

import org.springframework.stereotype.Service
import ru.anmo.customer_service.repository.CustomerRepository
import java.ru.anmo.shared.api.customer_service.CustomerServiceResponse

@Service
class CustomerService(val customerRepository: CustomerRepository): ICustomerService {
    override fun getCustomerInformation(id: Long): CustomerServiceResponse {
        return CustomerServiceResponse(666, "DarthVeider")
    }
}