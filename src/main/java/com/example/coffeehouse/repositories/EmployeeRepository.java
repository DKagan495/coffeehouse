package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
