package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.RentHistoryWithDataDto;
import com.carrentservice.domain.crud.dto.RentHistoryWithRentDataDto;

public class RentHistoryMapper {

    public static RentHistoryWithDataDto rentHistoryToRentHistoryWithDataDto(RentHistory rentHistory){
        return new RentHistoryWithDataDto(
                rentHistory.getId(),
                CarMapper.CarToCartDto(rentHistory.getCar()),
                CustomerMapper.customerToCustomerDto(rentHistory.getCustomer()),
                EmployeeMapper.EmployeeToEmployeeDto(rentHistory.getEmployeeRent()),
                EmployeeMapper.EmployeeToEmployeeDto(rentHistory.getEmployeeReturn()),
                rentHistory.getRentDate(),
                rentHistory.getReturnDate());
    }
    public static RentHistoryWithRentDataDto rentHistoryToRentHistoryWithRentDataDto(RentHistory rentHistory){
        return new RentHistoryWithRentDataDto(
                rentHistory.getId(),
                CarMapper.CarToCartDto(rentHistory.getCar()),
                CustomerMapper.customerToCustomerDto(rentHistory.getCustomer()),
                EmployeeMapper.EmployeeToEmployeeDto(rentHistory.getEmployeeReturn()),
                rentHistory.getRentDate());

    }

    public static RentHistory rentHistoryWithDataToRentHistory(RentHistoryWithDataDto rentHistoryWithDataDto) {
        return new RentHistory(
                rentHistoryWithDataDto.id(),
                CarMapper.carDtoToCar(rentHistoryWithDataDto.carDto()),
                CustomerMapper.customerDtoToCustomer(rentHistoryWithDataDto.customer()),
                EmployeeMapper.EmployeeDtoToEmployee(rentHistoryWithDataDto.employeeRent()),
                EmployeeMapper.EmployeeDtoToEmployee(rentHistoryWithDataDto.employeeReturn()),
                rentHistoryWithDataDto.rentDate(),
                rentHistoryWithDataDto.returnDate());
    }
}
