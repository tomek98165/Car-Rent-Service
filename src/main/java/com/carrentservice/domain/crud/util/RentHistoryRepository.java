package com.carrentservice.domain.crud.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    List<RentHistory> findByReturnDateIsNull();
    List<RentHistory> findByCustomerId(Long id);
    List<RentHistory> findByCustomerIdAndReturnDateIsNull(Long id);
    List<RentHistory> findByCarId(Long id);
    Optional<RentHistory> findByCarIdAndReturnDateIsNull(Long id);
    Optional<RentHistory> findTopByCarIdOrderByReturnDateDesc(Long id);






}
