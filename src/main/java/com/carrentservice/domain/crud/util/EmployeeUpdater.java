package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class EmployeeUpdater {
    private final EmployeeRepository employeeRepository;
    private final EmployeeRetriever employeeRetriever;

    EmployeeUpdater(EmployeeRepository employeeRepository, EmployeeRetriever employeeRetriever) {
        this.employeeRepository = employeeRepository;
        this.employeeRetriever = employeeRetriever;
    }

    EmployeeDto setAuthorities(long id, List<Authorities> authorities){
        Employee employee = EmployeeMapper.EmployeeDtoToEmployee(employeeRetriever.findEmployeeById(id));
        employee.setAuthorities(authorities);
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.EmployeeToEmployeeDto(updatedEmployee);
    }
}
