package com.rentcarservice.RenCarService.crud.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
class Customer {
   private Long id;
    private String name;
    private String lastName;
    private String address;
    private String zipCode;
    private String town;
    private String pesel;
    private Date birthDate;
    private Gender Gender;
}
