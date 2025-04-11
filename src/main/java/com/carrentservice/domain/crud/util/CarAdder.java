package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import com.carrentservice.domain.crud.dto.CarRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
class CarAdder {
    private final CarRepository carRepository;

    CarAdder(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    CarDto addCar(final CarRequestDto carRequestDto){
        Car newCar = CarMapper.CarRequestDtoToCar(carRequestDto);
        Car savedCar = carRepository.save(newCar);
        return CarMapper.CarToCartDto(savedCar);
    }
}
