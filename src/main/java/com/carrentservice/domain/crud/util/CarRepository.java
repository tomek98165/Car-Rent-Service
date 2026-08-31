package com.carrentservice.domain.crud.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.Optional;

@Repository
interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findAllByAvailabilityIsTrue(Pageable pageable);
    Optional<Car> findByLicensePlate(String licensePlate);
    Set<Car> findAllByMakeAndModel(String make, String model);
    Set<Car> findAllByMake(String make);
    


}
