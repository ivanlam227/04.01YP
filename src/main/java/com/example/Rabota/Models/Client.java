package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В фамилии могут быть только русские буквы")
    @Size(min=4, max=100)
    private String lastnamec;
    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В имени могут быть только русские буквы")
    @Size(min=2, max=100)
    @NotEmpty(message = "Заполните поле")
    private String namec;
    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В отчество могут быть только русские буквы")
    @Size(min=4, max=100)
    @NotEmpty(message = "Заполните поле")
    private String middlenamec;
    @Min(1000)
    @Max(9999)
    @NotNull(message = "должно быть 4 цифры")
    private Integer seria;

    @Min(100000)
    @Max(9999999)
    @NotNull(message = "дожно быть 6 цифр")
    private Integer nomer;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Collection<Dogovor> tenants;

    public Client(String lastnamec, String namec, String middlenamec, Integer seria, Integer nomer, Collection<Dogovor> tenants) {
        this.lastnamec = lastnamec;
        this.namec = namec;
        this.middlenamec = middlenamec;
        this.seria = seria;
        this.nomer = nomer;
        this.tenants = tenants;
    }

    public Client(Collection<Dogovor> tenants) {
        this.tenants = tenants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastnamec() {
        return lastnamec;
    }

    public void setLastnamec(String lastnamec) {
        this.lastnamec = lastnamec;
    }

    public String getNamec() {
        return namec;
    }

    public void setNamec(String namec) {
        this.namec = namec;
    }

    public String getMiddlenamec() {
        return middlenamec;
    }

    public void setMiddlenamec(String middlenamec) {
        this.middlenamec = middlenamec;
    }

    public Integer getSeria() {
        return seria;
    }

    public void setSeria(Integer seria) {
        this.seria = seria;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }
    public Client(){}
}
