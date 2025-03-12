package com.rentcarservice.RenCarService.crud.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
class RentHistory {
    private Long id;
    private Car car;
    private Customer customer;
    private Employee employeeRent;
    private Employee employeeReturn;
    private Date rentDate;
    private Date returnDate;

}
