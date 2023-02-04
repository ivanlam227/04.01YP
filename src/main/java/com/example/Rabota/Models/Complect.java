package com.example.Rabota.Models;



import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Complect")

public class Complect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compl;
    @OneToMany (mappedBy = "complect", fetch = FetchType.EAGER)
    private Collection<Car> tenants;

    public Complect() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    public Collection<Car> getTenants() {
        return tenants;
    }

    public Complect(String compl, Collection<Car> tenants) {
        this.compl = compl;
        this.tenants = tenants;
    }

    public void setTenants(Collection<Car> tenants) {
        this.tenants = tenants;
    }
}
