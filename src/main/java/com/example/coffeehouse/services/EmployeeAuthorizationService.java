package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.repositories.AuthorizationEmployeeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeAuthorizationService {

    private final AuthorizationEmployeeCrudRepository authorizationEmployeeCrudRepository;

    public EmployeeAuthorizationService(AuthorizationEmployeeCrudRepository authorizationEmployeeCrudRepository) {
        this.authorizationEmployeeCrudRepository = authorizationEmployeeCrudRepository;
    }

    @Transactional
    public Employee checkLogInParameters(String email, String password){
        Employee employee = authorizationEmployeeCrudRepository.findByLoginAndPassword(email, password);
        if(employee != null)
            return employee;
        return null;
    }
}
