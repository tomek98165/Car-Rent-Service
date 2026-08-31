package com.carrentservice.domain.crud.dto;

import com.carrentservice.domain.crud.util.Authorities;

import java.util.Set;

public record EmployeeRequestDto(
        String name,
        String lastName,
        String username,
        String password,
        Set<Authorities> authorities

) {


}
