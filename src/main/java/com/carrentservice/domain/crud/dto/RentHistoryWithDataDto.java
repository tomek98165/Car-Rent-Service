package com.carrentservice.domain.crud.dto;

import java.time.Instant;

public record RentHistoryWithDataDto(
        Long id,
        CarDto carDto,
        CustomerDto customer,
        EmployeeDto employeeRent,
        EmployeeDto employeeReturn,
        Instant rentDate,
        Instant returnDate

) {
}
