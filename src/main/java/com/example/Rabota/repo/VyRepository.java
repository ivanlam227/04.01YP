package com.example.Rabota.repo;

import com.example.Rabota.Models.Vy;
import org.springframework.data.repository.CrudRepository;

public interface VyRepository extends CrudRepository<Vy, Long> {
    Vy findByNumber(String number);
}
