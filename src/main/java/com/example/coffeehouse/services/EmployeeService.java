package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployee(int id){
        return employeeRepository.findById(id).get();
    }

}
