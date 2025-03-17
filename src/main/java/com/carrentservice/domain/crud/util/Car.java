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
    @Column(unique = true, length = 17)
    private String vin;
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    Car(String make, String model, String vin, String description, int year, boolean availability) {
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.description = description;
        this.year = year;
        this.availability = availability;
    }

    public Car() {
    }

    public Car(Long id, String make, String model, String vin, String description, int year, boolean availability) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.description = description;
        this.year = year;
        this.availability = availability;
    }
}
