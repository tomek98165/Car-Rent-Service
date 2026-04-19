package com.carrentservice.infrastructure.customer;

import com.carrentservice.domain.crud.dto.CustomerDto;
import com.carrentservice.domain.crud.dto.CustomerRequestDto;
import com.carrentservice.domain.crud.util.CarRentServiceCrudFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    final private CarRentServiceCrudFacade carRentServiceCrudFacade;

    public CustomerRestController(CarRentServiceCrudFacade carRentServiceCrudFacade) {
        this.carRentServiceCrudFacade = carRentServiceCrudFacade;
    }

    @GetMapping
    ResponseEntity<AllCustomersDto> findAllCustomers(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Set<CustomerDto> customers = carRentServiceCrudFacade.findAllCustomers(pageable);
        return ResponseEntity.ok(new AllCustomersDto(customers));
    }
    @PostMapping
    ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerDto newCustomer = carRentServiceCrudFacade.addNewCustomer(customerRequestDto);
        return ResponseEntity.ok(newCustomer);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> findCustomerById(@PathVariable long id){
        CustomerDto customer = carRentServiceCrudFacade.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }
    @GetMapping("/{pesel}")
    ResponseEntity<CustomerDto> findCustomerById(@PathVariable String pesel){
        CustomerDto customer = carRentServiceCrudFacade.findCustomerByPesel(pesel);
        return ResponseEntity.ok(customer);
    }
    @PutMapping
    ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto newCustomer = carRentServiceCrudFacade.updateCustomer(customerDto);
        return ResponseEntity.ok(newCustomer);
    }




}
