package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CustomerDto;
import com.carrentservice.domain.crud.dto.CustomerRequestDto;
import org.springframework.stereotype.Service;

@Service
class CustomerAdder {

    private final CustomerRepository customerRepository;

    CustomerAdder(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    CustomerDto addNewCustomer(CustomerRequestDto customerRequestDto){
        Customer newCustomer = CustomerMapper.customerRequestDtoToCustomer(customerRequestDto);
        Customer savedCustomer = customerRepository.save(newCustomer);

        return CustomerMapper.customerToCustomerDto(savedCustomer);
    }
}
