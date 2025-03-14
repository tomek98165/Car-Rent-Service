package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import com.carrentservice.domain.crud.dto.CarRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
class CarAdder {
    private final CarRepository carRepository;

    CarAdder(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    CarDto addCar(final CarRequestDto carRequestDto){
        Car newCar = new Car(
                carRequestDto.make(),
                carRequestDto.model(),
                carRequestDto.vin(),
                carRequestDto.description(),
                carRequestDto.year(),
                carRequestDto.availability());
        Car savedCar = carRepository.save(newCar);
        return new CarDto(savedCar.getId(),
                savedCar.getMake(),
                savedCar.getModel(),
                savedCar.getVin(),
                savedCar.getDescription(),
                savedCar.getYear(),
                savedCar.isAvailability());
    }
}
