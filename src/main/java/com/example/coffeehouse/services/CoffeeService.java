package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Coffee;
import com.example.coffeehouse.repositories.CoffeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public BigDecimal getCostWithoutEmployeesRank(String coffeeName, String arabicaName, BigDecimal sizeCost){
        BigDecimal coffeeCost = new BigDecimal(coffeeRepository.findByName(coffeeName).getCost());
        BigDecimal arabicaCost = new BigDecimal(coffeeRepository.getCostByArabicaName(arabicaName));
        coffeeCost = coffeeCost.setScale(2, RoundingMode.HALF_DOWN);
        arabicaCost = arabicaCost.setScale(2, RoundingMode.HALF_DOWN);
        sizeCost = sizeCost.setScale(2, RoundingMode.HALF_DOWN);
        return (coffeeCost.add(arabicaCost)).multiply(sizeCost);
    }

    public List<String> getArabicasNames(){
        return coffeeRepository.getAllArabica();
    }

    public List<Coffee> getAllCoffies(){
       return (List<Coffee>) coffeeRepository.findAll();
    }
}
