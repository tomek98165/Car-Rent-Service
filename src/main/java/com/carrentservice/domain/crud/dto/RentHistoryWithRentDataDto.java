package com.carrentservice.domain.crud.dto;

import java.time.Instant;

public record RentHistoryWithRentDataDto(
        Long id,
        CarDto carDto,
        CustomerDto customer,
        EmployeeDto employeeRent,
        Instant rentDate
) {
}
