package com.carrentservice.domain.crud.util;

import com.carrentservice.domain.crud.dto.EmployeeDto;
import com.carrentservice.domain.crud.dto.RentHistoryReturnRequestDto;
import com.carrentservice.domain.crud.dto.RentHistoryWithDataDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
class RentHistoryUpdater {
    private final RentHistoryRepository rentHistoryRepository;
    private final RentHistoryRetriever rentHistoryRetriever;
    private final CarUpdater carUpdater;
    private final EmployeeRetriever employeeRetriever;
    private final TimeProvider timeProvider;

    RentHistoryUpdater(RentHistoryRepository rentHistoryRepository, RentHistoryRetriever rentHistoryRetriever, CarUpdater carUpdater, EmployeeRetriever employeeRetriever, TimeProvider timeProvider) {
        this.rentHistoryRepository = rentHistoryRepository;
        this.rentHistoryRetriever = rentHistoryRetriever;
        this.carUpdater = carUpdater;
        this.employeeRetriever = employeeRetriever;
        this.timeProvider = timeProvider;
    }

    RentHistoryWithDataDto returnCar(RentHistoryReturnRequestDto rentHistoryReturnRequestDto) throws RentHistoryCarAvailableException {
        RentHistoryWithDataDto data = rentHistoryRetriever.findRentHistoryById(rentHistoryReturnRequestDto.id());
        if(data.returnDate() == null){
            EmployeeDto employee = employeeRetriever.findEmployeeById(rentHistoryReturnRequestDto.employeeReturnId());
            RentHistoryWithDataDto result = new RentHistoryWithDataDto(
                    data.id(),
                    data.carDto(),
                    data.customer(),
                    data.employeeRent(),
                    employee,
                    data.rentDate(),
                    timeProvider.now()
            );
            RentHistory rentedCar = RentHistoryMapper.rentHistoryWithDataToRentHistory(result);
//            rentedCar.setEmployeeReturn(EmployeeMapper.EmployeeDtoToEmployee(employee));
//            rentedCar.setReturnDate(Instant.now());
            carUpdater.changeAvailabilityCar(rentedCar.getCar().getId());
            return RentHistoryMapper.rentHistoryToRentHistoryWithDataDto(rentHistoryRepository.save(rentedCar));
        }else{
            throw new RentHistoryCarAvailableException("Car already returned");
        }
    }
}
