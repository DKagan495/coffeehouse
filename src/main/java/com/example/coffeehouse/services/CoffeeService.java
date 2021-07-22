package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Coffee;
import com.example.coffeehouse.models.converters.CupCoefficients;
import com.example.coffeehouse.repositories.CoffeeCostsRepository;
import com.example.coffeehouse.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeCostsRepository coffeeCostsRepository;


    @Transactional
    public double getCostWithoutEmployeesRank(String coffeeName, String arabicaName, double sizeCost){
        double coffeeCost = coffeeRepository.findByName(coffeeName).getCost();
        double arabicaCost = coffeeRepository.getCostByArabicaName(arabicaName);// arabicaRepository.findByName(arabicaName).getCost();
        return (coffeeCost + arabicaCost) * sizeCost;
    }

    @Transactional
    public List<String> getArabicasNames(){
        return coffeeRepository.getAllArabica();
    }

    @Transactional
    public List<Coffee> getAllCoffies(){
       return (List<Coffee>) coffeeRepository.findAll();
    }

    @Transactional
    public double getArabicaCostByName(String arabicaName){
        return coffeeRepository.getCostByArabicaName(arabicaName);
    }
}
