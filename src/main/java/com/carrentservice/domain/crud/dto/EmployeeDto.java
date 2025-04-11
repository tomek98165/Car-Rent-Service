package com.carrentservice.domain.crud.dto;

import com.carrentservice.domain.crud.util.Authorities;

import java.util.List;

public record EmployeeDto(
        Long id,
        String name,
        String lastName,
        String username,
        String password,
        List<Authorities> authorities
) {
}
