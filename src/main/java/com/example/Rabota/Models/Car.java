package com.example.Rabota.Models;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Complect complect;
    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = " В марке используйте английские, русские буквы и цифры")
    private String marks;
    @Min(10000)
    @Max(99999999)
    @NotNull(message = "Минимальная цена 10000")

    private Double sale;


   @Min(100000)
   @Max(9999999)
   @NotNull(message = "номер двигателя состоит из 6-7 цифр")
    private Integer numberengen;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
@NotNull(message = "Заполните поле")
    private Date year;
    @NotEmpty(message = "Заполните поле")
    @Size(max=25)
    private String color;
    @ManyToMany
    @JoinTable (name="carinavtosalon",
            joinColumns=@JoinColumn (name="car_id"),
            inverseJoinColumns=@JoinColumn(name="avtosalon_id"))
    private List<Avtosalon> avtosalonList;

    @OneToMany (mappedBy = "car", fetch = FetchType.EAGER)
    private Collection<Testdrive> tenants;
    @OneToMany (mappedBy = "carss", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Dogovor> tenant;



    public Car(Collection<Dogovor> tenant) {
        this.tenant = tenant;
    }

    public Car() {

    }


    public Collection<Dogovor> getTenant() {
        return tenant;
    }

    public void setTenant(Collection<Dogovor> tenant) {
        this.tenant = tenant;
    }

    public Collection<Testdrive> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Testdrive> tenants) {
        this.tenants = tenants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Integer getNumberengen() {
        return numberengen;
    }

    public void setNumberengen(Integer numberengen) {
        this.numberengen = numberengen;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Avtosalon> getAvtosalonList() {
        return avtosalonList;
    }

    public void setAvtosalonList(List<Avtosalon> avtosalonList) {
        this.avtosalonList = avtosalonList;
    }

    public Complect getComplect() {
        return complect;
    }

    public void setComplect(Complect complect) {
        this.complect = complect;
    }

    public Car(String name, Complect complect) {
    }

    public Car(Complect complect, String marks, Double sale, Integer numberengen, Date year, String color, List<Avtosalon> avtosalonList) {
        this.complect = complect;
        this.marks = marks;
        this.sale = sale;
        this.numberengen = numberengen;
        this.year = year;
        this.color = color;
        this.avtosalonList = avtosalonList;
    }
}
