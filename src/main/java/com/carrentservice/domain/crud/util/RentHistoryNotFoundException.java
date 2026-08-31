package com.carrentservice.domain.crud.util;

public class RentHistoryNotFoundException extends RuntimeException {
    public RentHistoryNotFoundException(String message) {
        super("Car with " + message + " was never rented");
    }
}
