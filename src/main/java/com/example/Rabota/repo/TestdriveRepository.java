package com.example.Rabota.repo;

import com.example.Rabota.Models.Testdrive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestdriveRepository extends CrudRepository<Testdrive, Long> {
    List<Testdrive> findAll();
}
