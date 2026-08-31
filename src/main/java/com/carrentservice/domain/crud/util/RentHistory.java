package com.carrentservice.domain.crud.util;

import jakarta.persistence.*;


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

    private Instant rentDate;
    private Instant returnDate;

    protected RentHistory() {
    }

    RentHistory(Long id, Car car, Customer customer, Employee employeeRent, Employee employeeReturn, Instant rentDate, Instant returnDate) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
        this.employeeReturn = employeeReturn;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    RentHistory(Car car, Customer customer, Employee employeeRent, Instant rentDate) {
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
        this.rentDate = rentDate;
    }

    public RentHistory(Long id, Car car, Customer customer, Employee employeeRent, Instant rentDate) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.employeeRent = employeeRent;
        this.rentDate = rentDate;
    }


    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Car getCar() {
        return car;
    }

    void setCar(Car car) {
        this.car = car;
    }

    Customer getCustomer() {
        return customer;
    }

    void setCustomer(Customer customer) {
        this.customer = customer;
    }

    Employee getEmployeeRent() {
        return employeeRent;
    }

    void setEmployeeRent(Employee employeeRent) {
        this.employeeRent = employeeRent;
    }

    Employee getEmployeeReturn() {
        return employeeReturn;
    }

    void setEmployeeReturn(Employee employeeReturn) {
        this.employeeReturn = employeeReturn;
    }

    Instant getRentDate() {
        return rentDate;
    }

    void setRentDate(Instant rentDate) {
        this.rentDate = rentDate;
    }

    Instant getReturnDate() {
        return returnDate;
    }

    void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }
}
