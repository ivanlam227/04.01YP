package com.example.Rabota.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
@Entity
public class Postavka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp ="^[А-Яа-яЁё]+$", message = "В фамилии могут быть только русские буквы")
    @Size(min=4, max=100)
    private String nameAvto;

    @Min(1)
    @Max(9999)
    @NotNull(message = "должна быть мин 1 машина")
    private Integer Col;

    @Min(1)
    @Max(9999999)
    @NotNull(message = "дожна быть мин 1 число")
    private Integer nomerP;

    public Postavka( String nameAvto, Integer col, Integer nomerP) {

        this.nameAvto = nameAvto;
        Col = col;
        this.nomerP = nomerP;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameAvto() {
        return nameAvto;
    }

    public void setNameAvto(String nameAvto) {
        this.nameAvto = nameAvto;
    }

    public Integer getCol() {
        return Col;
    }

    public void setCol(Integer col) {
        Col = col;
    }

    public Integer getNomerP() {
        return nomerP;
    }

    public void setNomerP(Integer nomerP) {
        this.nomerP = nomerP;
    }
    public Postavka(){}
}
