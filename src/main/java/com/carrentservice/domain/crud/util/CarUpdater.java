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

    public CarDto changeAvailabilityCar(Long id){
        CarDto car = carRetriever.findCarById(id);
        Car updateCar = new Car(
                car.id(),
                car.make(),
                car.model(),
                car.vin(),
                car.description(),
                car.year(),
                !car.availability());
        Car updatedCar = carRepository.save(updateCar);
        return new CarDto(updatedCar.getId(),
                updateCar.getMake(),
                updatedCar.getModel(),
                updatedCar.getVin(),
                updatedCar.getDescription(),
                updatedCar.getYear(),
                updatedCar.isAvailability());
    }


}
