package com.carrentservice.domain.crud.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarRentServiceCrudFacadeTest {

    final static CarRentServiceCrudFacade carRentServiceCrudFacade = CarRentServiceCrudFacadeConfiguration.createCarRentServiceCrud(
            new InMemoryCarRepositoryImpl(),
            new InMemoryCustomerRepositoryImpl(),
            new InMemoryEmployeeRepositoryImpl(),
            new InMemoryRentHistoryRepositoryImpl());
    @Test
    public void test(){

    }
}

