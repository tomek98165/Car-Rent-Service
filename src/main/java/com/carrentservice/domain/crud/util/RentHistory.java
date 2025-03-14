package com.carrentservice.domain.crud.util;

import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
class RentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Employee employeeRent;
    @ManyToOne
    private Employee employeeReturn;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    public RentHistory() {
    }

    public RentHistory(Long id, Car car, Customer customer, Employee employeeRent, Employee employeeReturn, LocalDateTime rentDate, LocalDateTime returnDate) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
        this.employeeReturn = employeeReturn;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public RentHistory(Car car, Customer customer, Employee employeeRent, LocalDateTime rentDate) {
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
        this.rentDate = rentDate;
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

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
