package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizationEmployeeCrudRepository extends CrudRepository<Employee, Integer> {
    public Employee findByLoginAndPassword(String login, String password);
}
