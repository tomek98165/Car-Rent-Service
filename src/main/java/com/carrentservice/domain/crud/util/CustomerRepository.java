package com.carrentservice.domain.crud.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPesel(String pesel);

}
