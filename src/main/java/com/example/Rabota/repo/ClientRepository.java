package com.example.Rabota.repo;

import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,Long> {
    //List<Client> findByLastnamec(String lastnamec);
    List<Client> findByLastnamecContains (String lastnamec);
    Client findByLastnamec(String lastnamec);
}
