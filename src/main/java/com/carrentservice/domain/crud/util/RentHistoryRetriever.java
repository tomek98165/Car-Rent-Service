package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.RentHistoryWithDataDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
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
    Set<RentHistoryWithDataDto> findAllRentHistory(Pageable pageable){
        return rentHistoryRepository.findAll(pageable).stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }
    Set<RentHistoryWithDataDto> findALlRentHistoryWithRentedCars(Pageable pageable){
        return rentHistoryRepository.findByReturnDateIsNull(pageable).stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }
    Set<RentHistoryWithDataDto> findRentHistoryByCustomerId(Long id, Pageable pageable){
        return rentHistoryRepository.findByCustomerId(id, pageable).stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }
    Set<RentHistoryWithDataDto> findNotReturnedCarsByCustomerId(Long id, Pageable pageable){
        return rentHistoryRepository.findByCustomerIdAndReturnDateIsNull(id, pageable).stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }
    Set<RentHistoryWithDataDto> findRentHistoryByCarId(Long id, Pageable pageable){
        return rentHistoryRepository.findByCarId(id, pageable).stream()
                .map(RentHistoryMapper::rentHistoryToRentHistoryWithDataDto)
                .collect(Collectors.toSet());
    }
    RentHistoryWithDataDto findLastReturnHistoryByCarId(Long id){
        RentHistory rentHistory = rentHistoryRepository.findTopByCarIdOrderByReturnDateDesc(id)
                .orElseThrow(()-> new RentHistoryNotFoundException("id: " + id));
        return RentHistoryMapper.rentHistoryToRentHistoryWithDataDto(rentHistory);
    }
    RentHistoryWithDataDto findLastRentHistoryByCarId(Long id) throws RentHistoryCarAvailableException {
        RentHistory rentHistory = rentHistoryRepository.findByCarIdAndReturnDateIsNull(id)
                .orElseThrow(()-> new RentHistoryCarAvailableException("Car is not rented"));
        return RentHistoryMapper.rentHistoryToRentHistoryWithDataDto(rentHistory);
    }




}
