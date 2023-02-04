package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Vys")
public class Vy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Size(max = 6)
    private String number;
    @OneToOne(optional = true, mappedBy = "vy", cascade = CascadeType.ALL)
    private Driver owner;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        if(number == null) return "";
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Driver getOwner() {
        return owner;
    }

    public void setOwner(Driver owner) {
        this.owner = owner;
    }
}
