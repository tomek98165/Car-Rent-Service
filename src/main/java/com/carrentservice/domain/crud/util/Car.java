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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String vin) {
        this.licensePlate = vin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    Car(String make, String model, String licensePlate, String description, int year, boolean availability) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.description = description;
        this.year = year;
        this.availability = availability;
    }

    public Car() {
    }

    public Car(Long id, String make, String model, String licensePlate, String description, int year, boolean availability) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.description = description;
        this.year = year;
        this.availability = availability;
    }
}
