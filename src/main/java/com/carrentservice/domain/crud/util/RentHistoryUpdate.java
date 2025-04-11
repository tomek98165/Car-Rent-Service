package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.RentHistoryReturnRequestDto;
import com.carrentservice.domain.crud.dto.RentHistoryWithDataDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
class RentHistoryUpdate {
    private final RentHistoryRepository rentHistoryRepository;
    private final RentHistoryRetriever rentHistoryRetriever;
    private final CarUpdater carUpdater;

    RentHistoryUpdate(RentHistoryRepository rentHistoryRepository, RentHistoryRetriever rentHistoryRetriever, CarUpdater carUpdater) {
        this.rentHistoryRepository = rentHistoryRepository;
        this.rentHistoryRetriever = rentHistoryRetriever;
        this.carUpdater = carUpdater;
    }

    RentHistoryWithDataDto returnCar(RentHistoryReturnRequestDto rentHistoryReturnRequestDto){
        RentHistoryWithDataDto data = rentHistoryRetriever.findRentHistoryById(rentHistoryReturnRequestDto.id());
        if(data.returnDate() == null){
            RentHistory rentedCar = RentHistoryMapper.rentHistoryWithDataToRentHistory(data);
            rentedCar.setReturnDate(Instant.now());
            carUpdater.changeAvailabilityCar(rentedCar.getCar().getId());
            return RentHistoryMapper.rentHistoryToRentHistoryWithDataDto(rentHistoryRepository.save(rentedCar));
        }else{
            throw new CarAlreadyReturnedException();
        }
    }
}
