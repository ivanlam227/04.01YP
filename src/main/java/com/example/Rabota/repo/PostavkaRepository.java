package com.example.Rabota.repo;

import com.example.Rabota.Models.Employee;
import com.example.Rabota.Models.Postavka;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostavkaRepository extends CrudRepository<Postavka,Long> {
    List<Postavka> findByNameAvtoContains (String NameAvto);
}
