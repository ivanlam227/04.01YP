package com.example.Rabota.Models;

import javax.persistence.*;

@Entity
@Table(name = "Testdrives")
public class Testdrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Car car;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Driver driver;

    public Testdrive() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    public Testdrive(String data, Car car, Driver driver) {
        this.data = data;
        this.car = car;
        this.driver = driver;
    }

    public Testdrive(String data, Driver driver) {
        this.data =data ;
        this.driver = driver;
    }
}
