package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class CustomerRetriever {
    private final CustomerRepository customerRepository;

    CustomerRetriever(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    CustomerDto findCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("id: " + id));
        return CustomerMapper.customerToCustomerDto(customer);
    }
    Set<CustomerDto> findAllCustomers(){
        return customerRepository.findAll().stream().map(CustomerMapper::customerToCustomerDto)
                .collect(Collectors.toSet());
    }
    CustomerDto findCustomerByPesel(String pesel){
        Customer customer = customerRepository.findCustomerByPesel(pesel)
                .orElseThrow(() -> new CustomerNotFoundException("pesel: " + pesel));
        return CustomerMapper.customerToCustomerDto(customer);
    }


}
