package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class EmployeeRetriever {
    private final EmployeeRepository employeeRepository;

    EmployeeRetriever(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    Set<EmployeeDto> findAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::EmployeeToEmployeeDto)
                .collect(Collectors.toSet());
    }

    EmployeeDto findEmployeeById(long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("id: " + id));

        return EmployeeMapper.EmployeeToEmployeeDto(employee);
    }
    EmployeeDto findEmployeeByUsername(String username){
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFoundException("username: " + username));

        return EmployeeMapper.EmployeeToEmployeeDto(employee);
    }

}
