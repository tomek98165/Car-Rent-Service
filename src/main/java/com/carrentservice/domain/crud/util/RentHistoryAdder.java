package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.*;
import org.springframework.stereotype.Service;

@Service
class RentHistoryAdder {

    private final RentHistoryRepository rentHistoryRepository;
    private final CarRetriever carRetriever;
    private final CarUpdater carUpdater;
    private final EmployeeRetriever employeeRetriever;
    private final CustomerRetriever customerRetriever;
    private final TimeProvider timeProvider;


    RentHistoryAdder(RentHistoryRepository rentHistoryRepository, CarRetriever carRetriever, CarUpdater carUpdater, EmployeeRetriever employeeRetriever, CustomerRetriever customerRetriever, TimeProvider timeProvider) {
        this.rentHistoryRepository = rentHistoryRepository;
        this.carRetriever = carRetriever;
        this.carUpdater = carUpdater;
        this.employeeRetriever = employeeRetriever;
        this.customerRetriever = customerRetriever;
        this.timeProvider = timeProvider;
    }

    RentHistoryWithRentDataDto rentCar(RentHistoryRequestDto rentHistoryRequestDto) throws RentHistoryCarAvailableException {
        CarDto car = carRetriever.findCarById(rentHistoryRequestDto.carId());
        if(car.availability()) {
            EmployeeDto employee = employeeRetriever.findEmployeeById(rentHistoryRequestDto.employeeRentId());
            CustomerDto customer = customerRetriever.findCustomerById(rentHistoryRequestDto.customerId());
            RentHistory rentHistory = new RentHistory(
                    CarMapper.carDtoToCar(car),
                    CustomerMapper.customerDtoToCustomer(customer),
                    EmployeeMapper.EmployeeDtoToEmployee(employee),
                    timeProvider.now());
            carUpdater.changeAvailabilityCar(car.id());
            RentHistory newRentHistory = rentHistoryRepository.save(rentHistory);
            return RentHistoryMapper.rentHistoryToRentHistoryWithRentDataDto(newRentHistory);
        }else{
            throw new RentHistoryCarAvailableException("Car is not available");
        }

    }
}
