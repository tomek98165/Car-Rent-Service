package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.EmployeeDto;
import com.carrentservice.domain.crud.dto.EmployeeRequestDto;

public class EmployeeMapper {

    public static Employee EmployeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.id(),
                employeeDto.name(),
                employeeDto.lastName(),
                employeeDto.username(),
                employeeDto.password(),
                employeeDto.authorities());
    }

    public static Employee EmployeeRequestDtoToEmployee(EmployeeRequestDto employeeDto) {
        return new Employee(
                employeeDto.name(),
                employeeDto.lastName(),
                employeeDto.username(),
                employeeDto.password());
    }
    public static EmployeeDto EmployeeToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getLastName(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getAuthorities()















        );
    }
}
