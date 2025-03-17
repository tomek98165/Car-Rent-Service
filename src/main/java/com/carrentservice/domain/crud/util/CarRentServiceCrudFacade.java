package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import com.carrentservice.domain.crud.dto.CarRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class CarRentServiceCrudFacade {

    private final CarAdder carAdder;
    private final CarRetriever carRetriever;
    private final CarUpdater carUpdater;

    public CarRentServiceCrudFacade(CarAdder carAdder, CarRetriever carRetriever, CarUpdater carUpdater) {
        this.carAdder = carAdder;
        this.carRetriever = carRetriever;
        this.carUpdater = carUpdater;
    }

    public CarDto addCar(CarRequestDto carRequestDto){
        return carAdder.addCar(carRequestDto);
    }

    public Set<CarDto> findAllCars(Pageable pageable){
        return carRetriever.findAllCars(pageable);
    }
    public Set<CarDto> findAllAvailableCars(Pageable pageable){
        return carRetriever.findAllAvailableCars(pageable);
    }
    public CarDto findCarById(Long id){
        return carRetriever.findCarById(id);
    }
    public CarDto findCarByVin(String vin){
        return carRetriever.findCarByVin(vin);
    }

    public Set<CarDto> findAllCarsByMakeAndModel(String make, String model){
        return carRetriever.findAllCarsByMakeAndModel(make, model);
    }

    public CarDto updateAvailabilityCar (Long id){
        return carUpdater.changeAvailabilityCar(id);
    }
}
