package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.repositories.AuthorizationEmployeeCrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeAuthorizationService {

    private final AuthorizationEmployeeCrudRepository authorizationEmployeeCrudRepository;

    public EmployeeAuthorizationService(AuthorizationEmployeeCrudRepository authorizationEmployeeCrudRepository) {
        this.authorizationEmployeeCrudRepository = authorizationEmployeeCrudRepository;
    }

    public Employee checkLogInParameters(String email, String password){
        Employee employee = authorizationEmployeeCrudRepository.findByLoginAndPassword(email, password);
        if(employee != null)
            return employee;
        return null;
    }
}
