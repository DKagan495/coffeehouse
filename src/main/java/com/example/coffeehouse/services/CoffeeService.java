package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Arabica;
import com.example.coffeehouse.models.Coffee;
import com.example.coffeehouse.models.converters.CoffeeCosts;
import com.example.coffeehouse.models.converters.CupCoefficients;
import com.example.coffeehouse.repositories.ArabicaRepository;
import com.example.coffeehouse.repositories.CoffeeCostsRepository;
import com.example.coffeehouse.repositories.CoffeeRepository;
import com.example.coffeehouse.repositories.CupCoefficientsRepository;
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

    @Autowired
    private CupCoefficientsRepository cupCoefficientsRepository;

    @Autowired
    private ArabicaRepository arabicaRepository;

    @Transactional
    public float getCostWithoutEmployeesRank(String coffeeName, String arabicaName, String cupKind){
        float coffeeCost = coffeeRepository.findByName(coffeeName).getCost();
        float arabicaCost = arabicaRepository.findByName(arabicaName).getCost();
        float cupCoefficient = cupCoefficientsRepository.findByKind(cupKind).getCoefficient();
        return (coffeeCost + arabicaCost) * cupCoefficient;
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
    public List<CoffeeCosts> getAllCostsOfCoffie(){
        return (List<CoffeeCosts>) coffeeCostsRepository.findAll();
    }
    @Transactional
    public List<Arabica> getAllArabica(){
        return (List<Arabica>) arabicaRepository.findAll();
    }
    @Transactional
    public List<CupCoefficients> getAllCupCoefficients(){
        return (List<CupCoefficients>) cupCoefficientsRepository.findAll();
    }
    @Transactional
    public Double getArabicaCostByName(String arabicaName){
        return coffeeRepository.getCostByArabicaName(arabicaName);
    }
}
