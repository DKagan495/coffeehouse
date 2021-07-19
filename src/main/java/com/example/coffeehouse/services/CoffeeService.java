package com.example.coffeehouse.services;

import com.example.coffeehouse.models.converters.CupCoefficients;
import com.example.coffeehouse.repositories.ArabicaRepository;
import com.example.coffeehouse.repositories.CoffeeCostsRepository;
import com.example.coffeehouse.repositories.CoffeeRepository;
import com.example.coffeehouse.repositories.CupCoefficientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public float getCostWithoutEmployeesRank(int coffeeId, String arabicaName, String cupKind){
        float coffeeCost = coffeeCostsRepository.findById(coffeeId).get().getCost();
        float arabicaCost = arabicaRepository.findByName(arabicaName).getCost();
        float cupCoefficient = cupCoefficientsRepository.findByKind(cupKind).getCost();
        return (coffeeCost + arabicaCost) * cupCoefficient;
    }
}
