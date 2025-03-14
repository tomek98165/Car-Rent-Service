package com.carrentservice.domain.crud.dto;

public record CarRequestDto(
        String make,
        String model,
        char[] vin,
        String description,
        int year,
        boolean availability
) {
}
