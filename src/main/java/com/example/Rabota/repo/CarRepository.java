package com.example.Rabota.repo;

import com.example.Rabota.Models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car,Long> {

    List<Car> findByMarksContains(String marks);

    Car findByMarks(String marks);


}
