package ru.anmo.customer_service.service

import ru.anmo.shared.api.customer_service.CustomerServiceResponse

interface ICustomerService {
    fun getCustomerInformation(id: Long): CustomerServiceResponse
}