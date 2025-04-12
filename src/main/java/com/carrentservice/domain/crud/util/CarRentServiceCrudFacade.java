package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.CarDto;
import com.carrentservice.domain.crud.dto.CarRequestDto;
import com.carrentservice.domain.crud.dto.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
public class CarRentServiceCrudFacade {

    private final CarAdder carAdder;
    private final CarRetriever carRetriever;
//    private final CarUpdater carUpdater;

    private final EmployeeRetriever employeeRetriever;
    private final EmployeeAdder employeeAdder;
    private final EmployeeUpdater employeeUpdater;

    private final CustomerRetriever customerRetriever;
    private final CustomerAdder customerAdder;
    private final CustomerUpdater customerUpdater;

    private final RentHistoryRetriever rentHistoryRetriever;
    private final RentHistoryUpdate rentHistoryUpdate;
    private final RentHistoryAdder rentHistoryAdder;

    public CarRentServiceCrudFacade(CarAdder carAdder,
                                    CarRetriever carRetriever,
//                                    CarUpdater carUpdater,
                                    EmployeeRetriever employeeRetriever,
                                    EmployeeAdder employeeAdder,
                                    EmployeeUpdater employeeUpdater,
                                    CustomerRetriever customerRetriever,
                                    CustomerAdder customerAdder,
                                    CustomerUpdater customerUpdater,
                                    RentHistoryRetriever rentHistoryRetriever,
                                    RentHistoryUpdate rentHistoryUpdate,
                                    RentHistoryAdder rentHistoryAdder) {
        this.carAdder = carAdder;
        this.carRetriever = carRetriever;
//        this.carUpdater = carUpdater;
        this.employeeRetriever = employeeRetriever;
        this.employeeAdder = employeeAdder;
        this.employeeUpdater = employeeUpdater;
        this.customerRetriever = customerRetriever;
        this.customerAdder = customerAdder;
        this.customerUpdater = customerUpdater;
        this.rentHistoryRetriever = rentHistoryRetriever;
        this.rentHistoryUpdate = rentHistoryUpdate;
        this.rentHistoryAdder = rentHistoryAdder;
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
    public CarDto findCarByLicensePlate(String licensePlate){
        return carRetriever.findCarByLicensePlate(licensePlate);
    }

    public Set<CarDto> findAllCarsByMakeAndModel(String make, String model){
        return carRetriever.findAllCarsByMakeAndModel(make, model);
    }


    public CustomerDto findCustomerById(Long id){
        return customerRetriever.findCustomerById(id);
    }

    public Set<CustomerDto> findAllCustomers(){
        return customerRetriever.findAllCustomers();
    }

//    public



}
