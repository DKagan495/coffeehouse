package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.repositories.AuthorizationClientCrudRepository;
import com.example.coffeehouse.repositories.AuthorizationEmployeeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeAuthorizationService {
    @Autowired
    private AuthorizationEmployeeCrudRepository authorizationEmployeeCrudRepository;


    @Transactional
    public Employee checkLogInParameters(String email, String password){
        Employee employee = authorizationEmployeeCrudRepository.findByLoginAndPassword(email, password);
        if(employee != null)
            return employee;
        return null;
    }
}