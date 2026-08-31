package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import com.carrentservice.domain.crud.dto.CarRequestDto;

class CarMapper {

    static Car carDtoToCar(CarDto carDto){
        return new Car(
                carDto.id(),
                carDto.make(),
                carDto.model(),
                carDto.licensePlate(),
                carDto.description(),
                carDto.year(),
                carDto.availability());
    }

    static Car CarRequestDtoToCar(CarRequestDto carDto){
        return new Car(
                carDto.make(),
                carDto.model(),
                carDto.licensePlate(),
                carDto.description(),
                carDto.year());
    }

    static CarDto CarToCartDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getLicensePlate(),
                car.getDescription(),
                car.getYear(),
                car.isAvailability());
    }


    }
