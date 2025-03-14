package com.carrentservice.infrastructure.car;

import com.carrentservice.domain.crud.dto.CarDto;
import com.carrentservice.domain.crud.dto.CarRequestDto;
import com.carrentservice.domain.crud.util.CarRentServiceCrudFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cars")
public class CarRestController {
    public CarRestController(CarRentServiceCrudFacade carRentServiceCrudFacade) {
        this.carRentServiceCrudFacade = carRentServiceCrudFacade;
    }

    private final CarRentServiceCrudFacade carRentServiceCrudFacade;

    @PostMapping
    ResponseEntity<CarDto> postCar(@RequestBody CarRequestDto carRequestDto){
        CarDto carDto = carRentServiceCrudFacade.addCar(carRequestDto);
        return ResponseEntity.ok(carDto);
    }

    @GetMapping
    ResponseEntity<AllCarsDto> getCars(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Set<CarDto> cars = carRentServiceCrudFacade.findAllCars(pageable);
        return ResponseEntity.ok(new AllCarsDto(cars));
    }
    @GetMapping("/available")
    ResponseEntity<AllCarsDto> getAvailableCars(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Set<CarDto> cars = carRentServiceCrudFacade.findAllAvailableCars(pageable);
        return ResponseEntity.ok(new AllCarsDto(cars));
    }

    @GetMapping("/id/{id}")
    ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carRentServiceCrudFacade.findCarById(id));
    }
    @GetMapping("/vin/{vin}")
    ResponseEntity<CarDto> getCarByVin(@PathVariable char[] vin){
        return ResponseEntity.ok(carRentServiceCrudFacade.findCarByVin(vin));
    }
    @GetMapping("/{make}/{model}")
    ResponseEntity<AllCarsDto> getAllCarsByMakeAndModel(@PathVariable("make") String make, @PathVariable("model") String model){
        Set<CarDto> cars = carRentServiceCrudFacade.findAllCarsByMakeAndModel(make, model);
        return ResponseEntity.ok(new AllCarsDto(cars));
    }
    @GetMapping("/model")
    ResponseEntity<AllCarsDto> getAllCarsByMakeAndModel(@RequestBody CarMakeModelDto carMakeModelDto) {
        Set<CarDto> cars = carRentServiceCrudFacade.findAllCarsByMakeAndModel(carMakeModelDto.make(), carMakeModelDto.model());
        return ResponseEntity.ok(new AllCarsDto(cars));
    }

    @PutMapping("/{id}")
    ResponseEntity<CarDto> updateAvailabilityCar(@PathVariable Long id){
        return ResponseEntity.ok(carRentServiceCrudFacade.updateAvailabilityCar(id));
    }

}
