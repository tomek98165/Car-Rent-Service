package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CustomerDto;
import com.carrentservice.domain.crud.dto.CustomerRequestDto;

class CustomerMapper {

    static Customer customerRequestDtoToCustomer(CustomerRequestDto customerDto){
        return new Customer(
                customerDto.name(),
                customerDto.lastName(),
                customerDto.address(),
                customerDto.zipCode(),
                customerDto.town(),
                customerDto.pesel(),
                customerDto.birthDate(),
                customerDto.Gender(),
                customerDto.phoneNumber(),
                customerDto.email());
    }
    static CustomerDto customerToCustomerDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getZipCode(),
                customer.getTown(),
                customer.getPesel(),
                customer.getBirthDate(),
                customer.getGender(),
                customer.getPhoneNumber(),
                customer.getEmail());
    }

     static Customer customerDtoToCustomer(CustomerDto customerDto){
        return new Customer(
                customerDto.id(),
                customerDto.name(),
                customerDto.lastName(),
                customerDto.address(),
                customerDto.zipCode(),
                customerDto.town(),
                customerDto.pesel(),
                customerDto.birthDate(),
                customerDto.Gender(),
                customerDto.phoneNumber(),
                customerDto.email());
    }
}
