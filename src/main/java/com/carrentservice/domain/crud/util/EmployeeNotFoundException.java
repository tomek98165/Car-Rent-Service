package com.carrentservice.domain.crud.util;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super("Employee with " + message + " not found");
    }
}
