package com.carrentservice.domain.crud.dto;

public record RentHistoryRequestDto(
        Long carId,
        Long customerId,
        Long employeeRentId

) {
}
