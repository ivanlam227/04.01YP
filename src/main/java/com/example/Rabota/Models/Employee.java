package com.example.Rabota.Models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В фамилии могут быть только русские буквы")
    @Size(min=4, max=100)
    private String lastname;
    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В имени могут быть только русские буквы")
    @Size(min=2, max=100)
    @NotEmpty(message = "Заполните поле")
    private String name;
    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В отчество могут быть только русские буквы")
    @Size(min=4, max=100)
    @NotEmpty(message = "Заполните поле")
    private String middlename;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Заполните поле")
    private Date birthday;
    @Min(0)
    @NotNull(message = "Заполните поле стаж")
    private Integer expetienxs;
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Collection<Dogovor> tenants;

    public Collection<Dogovor> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Dogovor> tenants) {
        this.tenants = tenants;
    }

    public long getId() {return id;}

    public Employee(String lastname, String name, String middlename, Date birthday, Integer expetienxs, Collection<Dogovor> tenants) {
        this.lastname = lastname;
        this.name = name;
        this.middlename = middlename;
        this.birthday = birthday;
        this.expetienxs = expetienxs;
        this.tenants = tenants;
    }

    public void setId(long id) {
        this.id = id;}

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getExpetienxs() {
        return expetienxs;
    }

    public void setExpetienxs(Integer expetienxs) {
        this.expetienxs = expetienxs;
    }

    public Employee() {


    }


}

