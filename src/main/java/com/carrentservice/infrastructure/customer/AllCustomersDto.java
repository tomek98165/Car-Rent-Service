package com.carrentservice.infrastructure.customer;

import com.carrentservice.domain.crud.dto.CustomerDto;
import java.util.Set;

public record AllCustomersDto(Set<CustomerDto> customers) {
}
