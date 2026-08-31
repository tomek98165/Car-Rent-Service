package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import org.springframework.stereotype.Service;

@Service
class CarUpdater {
    private final CarRepository carRepository;
    private final CarRetriever carRetriever;

    CarUpdater(CarRepository carRepository, CarRetriever carRetriever) {
        this.carRepository = carRepository;
        this.carRetriever = carRetriever;
    }

    public void changeAvailabilityCar(Long id){
        CarDto car = carRetriever.findCarById(id);
        car = new CarDto(
                car.id(),
                car.make(),
                car.model(),
                car.licensePlate(),
                car.description(),
                car.year(),
                !car.availability()
        );
        Car updateCar = CarMapper.carDtoToCar(car);
        carRepository.save(updateCar);
    }


}
