package com.carrentservice.domain.crud.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    Page<RentHistory> findByReturnDateIsNull(Pageable pageable);
    Page<RentHistory> findByCustomerId(Long id, Pageable pageable);
    Page<RentHistory> findByCustomerIdAndReturnDateIsNull(Long id, Pageable pageable);
    Page<RentHistory> findByCarId(Long id, Pageable pageable);
    Optional<RentHistory> findByCarIdAndReturnDateIsNull(Long id);
    Optional<RentHistory> findTopByCarIdOrderByReturnDateDesc(Long id);






}
