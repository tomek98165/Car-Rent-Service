package com.carrentservice.domain.crud.dto;

import java.util.Date;

public record CustomerDto(
         Long id,
         String name,
         String lastName,
         String address,
         String zipCode,
         String town,
         String pesel,
         Date birthDate,
         com.carrentservice.domain.crud.util.Gender Gender,
         String phoneNumber,
         String email
) {
    
}
