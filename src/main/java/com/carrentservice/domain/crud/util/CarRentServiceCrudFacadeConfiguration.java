package com.carrentservice.domain.crud.util;


import org.springframework.context.annotation.Configuration;

@Configuration
class CarRentServiceCrudFacadeConfiguration {
    public static CarRentServiceCrudFacade createCarRentServiceCrud(final CarRepository carRepository,
                                                                    final CustomerRepository customerRepository,
                                                                    final EmployeeRepository employeeRepository,
                                                                    final RentHistoryRepository rentHistoryRepository,
                                                                    final TimeProvider timeProvider){
        CarRetriever carRetriever = new CarRetriever(carRepository);
        CarAdder carAdder = new CarAdder(carRepository);
        CarUpdater carUpdater = new CarUpdater(carRepository, carRetriever);

        CustomerRetriever customerRetriever = new CustomerRetriever(customerRepository);
        CustomerAdder customerAdder = new CustomerAdder(customerRepository);
        CustomerUpdater customerUpdater = new CustomerUpdater(customerRepository, customerRetriever);

        EmployeeRetriever employeeRetriever = new EmployeeRetriever(employeeRepository);
        EmployeeAdder employeeAdder = new EmployeeAdder(employeeRepository);
        EmployeeUpdater employeeUpdater = new EmployeeUpdater(employeeRepository, employeeRetriever);

        RentHistoryRetriever rentHistoryRetriever = new RentHistoryRetriever(rentHistoryRepository);
        RentHistoryAdder rentHistoryAdder = new RentHistoryAdder(rentHistoryRepository, carRetriever, carUpdater, employeeRetriever, customerRetriever, timeProvider);
        RentHistoryUpdater rentHistoryUpdate = new RentHistoryUpdater(rentHistoryRepository, rentHistoryRetriever, carUpdater, employeeRetriever, timeProvider);

        return new CarRentServiceCrudFacade(
                carAdder,
                carRetriever,
//                carUpdater,
                employeeRetriever,
                employeeAdder,
                employeeUpdater,
                customerRetriever,
                customerAdder,
                customerUpdater,
                rentHistoryRetriever,
                rentHistoryUpdate,
                rentHistoryAdder
                );
    }
}
