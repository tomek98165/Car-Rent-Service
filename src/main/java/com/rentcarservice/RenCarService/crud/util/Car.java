package com.rentcarservice.RenCarService.crud.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Car {

    private Long id;
    private String make;
    private String model;
    private String vin;
    private CarInfo carInfo;
}
