package com.carrentservice.domain.crud.util;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;


import java.time.Instant;

@Entity
class RentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_rent_id")
    private Employee employeeRent;
    @ManyToOne
    @JoinColumn(name = "employee_return_id")
    private Employee employeeReturn;

    @CreatedDate
    private Instant rentDate;
    private Instant returnDate;

    public RentHistory() {
    }

    public RentHistory(Long id, Car car, Customer customer, Employee employeeRent, Employee employeeReturn, Instant rentDate, Instant returnDate) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
        this.employeeReturn = employeeReturn;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public RentHistory(Car car, Customer customer, Employee employeeRent) {
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployeeRent() {
        return employeeRent;
    }

    public void setEmployeeRent(Employee employeeRent) {
        this.employeeRent = employeeRent;
    }

    public Employee getEmployeeReturn() {
        return employeeReturn;
    }

    public void setEmployeeReturn(Employee employeeReturn) {
        this.employeeReturn = employeeReturn;
    }

    public Instant getRentDate() {
        return rentDate;
    }

    public void setRentDate(Instant rentDate) {
        this.rentDate = rentDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }
}
