package com.carrentservice.domain.crud.util;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
}
