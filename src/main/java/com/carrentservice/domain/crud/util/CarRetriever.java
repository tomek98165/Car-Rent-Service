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
                    .map(CarMapper::CarToCartDto)
                    .collect(Collectors.toSet());
    }

    CarDto findCarById(final Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("id: " + id));
        return CarMapper.CarToCartDto(car);
    }

    CarDto findCarByLicensePlate(final String licensePlate) {
        Car car = carRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new CarNotFoundException("licencePlate: " + licensePlate));
        return CarMapper.CarToCartDto(car);
    }

    Set<CarDto> findAllAvailableCars(Pageable pageable){
        return carRepository.findAllByAvailabilityIsTrue(pageable)
                .stream()
                .map(CarMapper::CarToCartDto)
                .collect(Collectors.toSet());
    }

    Set<CarDto> findAllCarsByMakeAndModel(String make, String model){
        return carRepository.findAllByMakeAndModel(make,model)
                .stream()
                .map(CarMapper::CarToCartDto)
                .collect(Collectors.toSet());
    }
}
