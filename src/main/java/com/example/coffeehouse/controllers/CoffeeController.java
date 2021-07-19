package com.example.coffeehouse.controllers;

import com.example.coffeehouse.dto.CoffeeDTO;
import com.example.coffeehouse.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CoffeeController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    CoffeeService coffeeService;


    @GetMapping("/getcoffee")
    public String toGetCoffeeForm(Model model){
        model.addAttribute("coffeedto", new CoffeeDTO());
        model.addAttribute("coffeelist", coffeeService.getAllCoffies());
        model.addAttribute("arabicalist", coffeeService.getAllArabica());
        model.addAttribute("cupcoefficientslist", coffeeService.getAllCupCoefficients());
        return "getcoffee";
    }
}
