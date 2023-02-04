package com.example.Rabota.repo;

import com.example.Rabota.Models.Complect;
import org.springframework.data.repository.CrudRepository;

public interface ComplectRepository extends CrudRepository<Complect, Long> {
    Complect findByCompl(String compl);
}
