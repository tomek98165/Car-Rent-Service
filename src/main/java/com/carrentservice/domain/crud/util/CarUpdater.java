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
        Car updateCar = CarMapper.carDtoToCar(car);
        updateCar.setAvailability(!updateCar.isAvailability());
        Car updatedCar = carRepository.save(updateCar);
    }


}
