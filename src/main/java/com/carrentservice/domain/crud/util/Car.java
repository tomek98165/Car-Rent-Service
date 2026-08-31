package com.carrentservice.domain.crud.util;

import jakarta.persistence.*;

@Entity
@Table(name = "Cars")
class Car{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    @Column(unique = true)
    private String licensePlate;
    private String description;
    private int year;
    private boolean availability;

     Long getId() {
        return id;
    }

     void setId(Long id) {
        this.id = id;
    }

     String getMake() {
        return make;
    }

     void setMake(String make) {
        this.make = make;
    }

     String getModel() {
        return model;
    }

     void setModel(String model) {
        this.model = model;
    }

     String getLicensePlate() {
        return licensePlate;
    }

     void setLicensePlate(String vin) {
        this.licensePlate = vin;
    }

     String getDescription() {
        return description;
    }

     void setDescription(String description) {
        this.description = description;
    }

     int getYear() {
        return year;
    }

     void setYear(int year) {
        this.year = year;
    }

     boolean isAvailability() {
        return availability;
    }

     void setAvailability(boolean availability) {
        this.availability = availability;
    }

    Car(String make, String model, String licensePlate, String description, int year) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.description = description;
        this.year = year;
    }

    protected Car() {
    }

     Car(Long id, String make, String model, String licensePlate, String description, int year, boolean availability) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.description = description;
        this.year = year;
        this.availability = availability;
    }
}
