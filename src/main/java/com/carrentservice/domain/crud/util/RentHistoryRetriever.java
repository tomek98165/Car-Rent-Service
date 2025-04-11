package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.RentHistoryWithDataDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
class RentHistoryRetriever {
    private final RentHistoryRepository rentHistoryRepository;


    RentHistoryRetriever(RentHistoryRepository rentHistoryRepository) {
        this.rentHistoryRepository = rentHistoryRepository;
    }

    RentHistoryWithDataDto findRentHistoryById(Long id){
        RentHistory rentHistory = rentHistoryRepository.findById(id)
                .orElseThrow(() -> new RentHistoryNotFoundException("id: " + id));
        return RentHistoryMapper.rentHistoryToRentHistoryWithDataDto(rentHistory);
    }

    Set<RentHistoryWithDataDto> findAllRentHistory(){
        return rentHistoryRepository.findAll().stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }

    Set<RentHistoryWithDataDto> findALlRentHistoryWithRentedCars(){
        return rentHistoryRepository.findByReturnDateIsNull().stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }


}
