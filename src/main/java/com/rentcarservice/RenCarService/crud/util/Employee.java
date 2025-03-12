package com.rentcarservice.RenCarService.crud.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
class Employee {
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private List<Authorities> authorities;



}
