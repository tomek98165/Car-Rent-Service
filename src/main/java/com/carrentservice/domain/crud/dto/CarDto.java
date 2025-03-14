package com.carrentservice.domain.crud.dto;

public record CarDto(
        Long id,
        String make,
        String model,
        char[] vin,
        String description,
        int year,
        boolean availability

) {
}
