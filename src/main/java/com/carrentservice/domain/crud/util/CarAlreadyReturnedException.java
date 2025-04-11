package com.carrentservice.domain.crud.util;

public class CarAlreadyReturnedException extends IllegalArgumentException {
    public CarAlreadyReturnedException() {
        super("Car is arleady returned");
    }
}
