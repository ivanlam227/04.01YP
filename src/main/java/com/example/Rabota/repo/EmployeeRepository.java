package com.example.Rabota.repo;

import com.example.Rabota.Models.Client;
import com.example.Rabota.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
  //  List<Employee> findByLastname(String lastname);
    List<Employee> findByLastnameContains (String lastname);
    Employee findByLastname(String lastname);
}
