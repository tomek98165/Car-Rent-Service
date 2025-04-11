package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.EmployeeDto;
import com.carrentservice.domain.crud.dto.EmployeeRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
class EmployeeAdder {

    private final EmployeeRepository employeeRepository;

    EmployeeAdder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    EmployeeDto addEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = EmployeeMapper.EmployeeRequestDtoToEmployee(employeeRequestDto);
        employee.setAuthorities(null);
        Employee newEmployee = employeeRepository.save(employee);

        return EmployeeMapper.EmployeeToEmployeeDto(newEmployee);
    }
}
