package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class CarRetriever {
    private final CarRepository carRepository;

    CarRetriever(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    Set<CarDto> findAllCars(Pageable pageable){
        return carRepository.findAll(pageable)
                .stream()
                    .map(car -> new CarDto(
                            car.getId(),
                            car.getMake(),
                            car.getModel(),
                            car.getVin(),
                            car.getDescription(),
                            car.getYear(),
                            car.isAvailability()))
                    .collect(Collectors.toSet());
    }

    CarDto findCarById(final Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("id: " + id));
        return new CarDto(car.getId(),
                car.getMake(),
                car.getModel(),
                car.getVin(),
                car.getDescription(),
                car.getYear(),
                car.isAvailability());
    }

    CarDto findCarByVin(final char[] vin) {
        Car car = carRepository.findByVin(vin)
                .orElseThrow(() -> new CarNotFoundException("id: " + vin));
        return new CarDto(car.getId(),
                car.getMake(),
                car.getModel(),
                car.getVin(),
                car.getDescription(),
                car.getYear(),
                car.isAvailability());
    }

    Set<CarDto> findAllAvailableCars(Pageable pageable){
        return carRepository.findAllByAvailabilityIsTrue(pageable)
                .stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getVin(),
                        car.getDescription(),
                        car.getYear(),
                        car.isAvailability()))
                .collect(Collectors.toSet());
    }

    Set<CarDto> findAllCarsByMakeAndModel(String make, String model){
        return carRepository.findAllByMakeAndModel(make,model)
                .stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getMake(),
                        car.getModel(),
                        car.getVin(),
                        car.getDescription(),
                        car.getYear(),
                        car.isAvailability()))
                .collect(Collectors.toSet());
    }
}
