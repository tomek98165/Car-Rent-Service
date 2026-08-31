package com.carrentservice.domain.crud.util;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super("Customer with " + message + " not found");
    }
}
