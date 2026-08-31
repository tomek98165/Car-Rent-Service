package com.carrentservice.domain.crud.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByAvailabilityIsTrue(Pageable pageable);
    Optional<Car> findByVin(String vin);

    List<Car> findAllByMakeAndModel(String make, String model);


}
