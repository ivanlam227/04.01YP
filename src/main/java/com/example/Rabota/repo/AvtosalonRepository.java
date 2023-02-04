package com.example.Rabota.repo;

import com.example.Rabota.Models.Avtosalon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvtosalonRepository extends CrudRepository<Avtosalon,Long>

    {

        List<Avtosalon> findByAdress(String adress);
        List<Avtosalon> findByAdressContains(String adress);
}
