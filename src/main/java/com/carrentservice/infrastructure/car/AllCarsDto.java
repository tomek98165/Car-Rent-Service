package com.carrentservice.infrastructure.car;

import com.carrentservice.domain.crud.dto.CarDto;

import java.util.Set;

public record AllCarsDto(Set<CarDto> Cars) {
}
