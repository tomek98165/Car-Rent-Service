package com.carrentservice.domain.crud.dto;

public record RentHistoryRentRequestDto(
        Long carId,
        Long customerId,
        Long employeeRentId

) {
}
