package com.carrentservice.domain.crud.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    Set<RentHistory> findByReturnDateIsNull();
}
