package com.carrentservice.domain.crud.dto;

public record CarRequestDto(
        String make,
        String model,
        String vin,
        String description,
        int year,
        boolean availability
) {
}
