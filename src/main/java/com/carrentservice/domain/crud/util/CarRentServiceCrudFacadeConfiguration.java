package com.carrentservice.domain.crud.util;


public class CarRentServiceCrudFacadeConfiguration {
    public static CarRentServiceCrudFacade createCarRentServiceCrud(final CarRepository carRepository){
        CarRetriever carRetriever = new CarRetriever(carRepository);
        CarAdder carAdder = new CarAdder(carRepository);
        CarUpdater carUpdater = new CarUpdater(carRepository, carRetriever);

        return new CarRentServiceCrudFacade(carAdder, carRetriever, carUpdater);
    }
}
