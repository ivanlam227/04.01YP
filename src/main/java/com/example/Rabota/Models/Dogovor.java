package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Dogovors")
public class Dogovor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//@Min(11111)
    private String number;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Client client;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Car carss;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Employee employee;

    public Dogovor() {

    }



    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCarss() {
        return carss;
    }

    public void setCarss(Car carss) {
        this.carss = carss;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Dogovor(String number, Client client, Car carss, Employee employee) {
        this.number = number;
        this.client = client;
        this.carss = carss;
        this.employee = employee;
    }
}
