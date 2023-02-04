package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
public class Avtosalon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 5)
    private String adress;
    @ManyToMany
    @JoinTable(name="carinavtosalon",
            joinColumns=@JoinColumn (name="avtosalon_id"),
            inverseJoinColumns=@JoinColumn(name="car_id"))
    private List<Car> carList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
