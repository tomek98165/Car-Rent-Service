package com.carrentservice.domain.crud.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    List<RentHistory> findByReturnDateIsNull(Pageable pageable);
    List<RentHistory> findByCustomerId(Long id, Pageable pageable);
    List<RentHistory> findByCustomerIdAndReturnDateIsNull(Long id, Pageable pageable);
    List<RentHistory> findByCarId(Long id, Pageable pageable);
    Optional<RentHistory> findByCarIdAndReturnDateIsNull(Long id);
    Optional<RentHistory> findTopByCarIdOrderByReturnDateDesc(Long id);






}
