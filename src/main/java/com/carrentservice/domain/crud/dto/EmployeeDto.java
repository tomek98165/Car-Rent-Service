package com.carrentservice.domain.crud.dto;

import com.carrentservice.domain.crud.util.Authorities;
import jakarta.persistence.ElementCollection;

import java.util.Set;

public record EmployeeDto(
        Long id,
        String name,
        String lastName,
        String username,
        String password,
        @ElementCollection
        Set<Authorities> authorities
) {
}
