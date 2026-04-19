package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.*;
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
    private final RentHistoryUpdater rentHistoryUpdater;
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
                                    RentHistoryUpdater rentHistoryUpdater,
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
        this.rentHistoryUpdater = rentHistoryUpdater;
        this.rentHistoryAdder = rentHistoryAdder;
    }
    //Cars
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
    public Set<CarDto> findAllCarsByMake(String make){
        return carRetriever.findAllCarsByMake(make);
    }


    //Customers
    public CustomerDto findCustomerById(Long id){
        return customerRetriever.findCustomerById(id);
    }
    public Set<CustomerDto> findAllCustomers(Pageable pageable){
        return customerRetriever.findAllCustomers(pageable);
    }
    public CustomerDto findCustomerByPesel(String pesel){
        return customerRetriever.findCustomerByPesel(pesel);
    }
    public CustomerDto addNewCustomer(CustomerRequestDto customerRequestDto){
        return customerAdder.addNewCustomer(customerRequestDto);
    }
    public CustomerDto updateCustomer(CustomerDto customerDto){
        return customerUpdater.updateCustomer(customerDto);
    }


    //Employee
    public EmployeeDto findEmployeeById(Long id){
        return employeeRetriever.findEmployeeById(id);
    }
    public EmployeeDto findEmployeeByUsername(String username){
        return employeeRetriever.findEmployeeByUsername(username);
    }
    public Set<EmployeeDto> findAllEmployees(Pageable pageable){
        return employeeRetriever.findAllEmployees(pageable);
    }
    public EmployeeDto addNewEmployee(EmployeeRequestDto employeeRequestDto){
        return employeeAdder.addEmployee(employeeRequestDto);
    }
    public EmployeeDto setAuthorities(Long id, Set<Authorities> authorities){
        return employeeUpdater.setAuthorities(id,authorities);
    }

    //Rent History
    public RentHistoryWithDataDto findRentHistoryById(Long id){
        return rentHistoryRetriever.findRentHistoryById(id);
    }
    public RentHistoryWithDataDto findLastReturnByCarId(Long id){
        return rentHistoryRetriever.findLastReturnHistoryByCarId(id);
    }
    public RentHistoryWithDataDto findLastRentCarByCarId(Long id) throws RentHistoryCarAvailableException {
        return rentHistoryRetriever.findLastRentHistoryByCarId(id);
    }
    public Set<RentHistoryWithDataDto> findAllRentHistory(Pageable pageable){
        return rentHistoryRetriever.findAllRentHistory(pageable);
    }
    public Set<RentHistoryWithDataDto> findAllRentHistoryByCarId(Long id,Pageable pageable){
        return rentHistoryRetriever.findRentHistoryByCarId(id, pageable);
    }
    public Set<RentHistoryWithDataDto> findAllRentHistoryByCustomerId(Long id,Pageable pageable){
        return rentHistoryRetriever.findRentHistoryByCustomerId(id, pageable);
    }
    public RentHistoryWithRentDataDto rentCar(RentHistoryRequestDto rentHistoryRequestDto) throws RentHistoryCarAvailableException {
        return rentHistoryAdder.rentCar(rentHistoryRequestDto);
    }
    public RentHistoryWithDataDto returnCar(RentHistoryReturnRequestDto rentHistoryReturnRequestDto) throws RentHistoryCarAvailableException {
        return rentHistoryUpdater.returnCar(rentHistoryReturnRequestDto);
    }
}
