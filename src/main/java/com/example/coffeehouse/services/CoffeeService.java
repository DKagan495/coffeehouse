package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Coffee;
import com.example.coffeehouse.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Transactional
    public BigDecimal getCostWithoutEmployeesRank(String coffeeName, String arabicaName, BigDecimal sizeCost){
        BigDecimal coffeeCost = new BigDecimal(coffeeRepository.findByName(coffeeName).getCost());
        BigDecimal arabicaCost = new BigDecimal(coffeeRepository.getCostByArabicaName(arabicaName));// arabicaRepository.findByName(arabicaName).getCost();
        coffeeCost = coffeeCost.setScale(2, RoundingMode.HALF_DOWN);
        System.out.println("costickboo: " + coffeeCost);
        arabicaCost = arabicaCost.setScale(2, RoundingMode.HALF_DOWN);
        sizeCost = sizeCost.setScale(2, RoundingMode.HALF_DOWN);
        System.out.println("costick: " + (coffeeCost.add(arabicaCost)).multiply(sizeCost));
        return (coffeeCost.add(arabicaCost)).multiply(sizeCost);
    }

    @Transactional
    public List<String> getArabicasNames(){
        return coffeeRepository.getAllArabica();
    }

    @Transactional
    public List<Coffee> getAllCoffies(){
       return (List<Coffee>) coffeeRepository.findAll();
    }
}
