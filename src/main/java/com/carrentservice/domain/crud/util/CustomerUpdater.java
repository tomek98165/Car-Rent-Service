package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
class CustomerUpdater {

    private final CustomerRepository customerRepository;
    private final CustomerRetriever customerRetriever;


    CustomerUpdater(CustomerRepository customerRepository, CustomerRetriever customerRetriever) {
        this.customerRepository = customerRepository;
        this.customerRetriever = customerRetriever;
    }

    CustomerDto updateCustomer(CustomerDto customerDto){
        customerRetriever.findCustomerById(customerDto.id());
        Customer updateCustomer = CustomerMapper.customerDtoToCustomer(customerDto);
        Customer updatedCustomer = customerRepository.save(updateCustomer);

        return CustomerMapper.customerToCustomerDto(updatedCustomer);


    }
}
