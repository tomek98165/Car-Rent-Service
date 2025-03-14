package com.carrentservice.domain.crud.util;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(final String message) {
        super("Car with " + message + " not found");
    }
}
