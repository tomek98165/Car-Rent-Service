package com.carrentservice.domain.crud.dto;


public record EmployeeRequestDto(
        String name,
        String lastName,
        String username,
        String password
//        Set<Authorities> authorities

) {


}
