package com.example.Rabota.repo;

import com.example.Rabota.Models.Car;
import com.example.Rabota.Models.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository   extends CrudRepository<Driver, Long> {
    Driver findByName(String name);
}
