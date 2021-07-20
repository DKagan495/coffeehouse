package com.example.coffeehouse.controllers;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.services.CoffeeService;
import com.example.coffeehouse.services.EmployeeService;
import com.example.coffeehouse.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CoffeeController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    CoffeeService coffeeService;
    @Autowired
    OrderService orderService;

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/getcoffee")
    public String toGetCoffeeForm(Model model){
        model.addAttribute("orderdto", new OrderDTO());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("coffeelist", coffeeService.getAllCoffies());
        model.addAttribute("arabicalist", coffeeService.getAllArabica());
        model.addAttribute("cupcoefficientslist", coffeeService.getAllCupCoefficients());
        return "getcoffee";
    }
    @PostMapping("/getcoffee")
    public String sendToEmployee(@ModelAttribute("orderdto") OrderDTO orderDTO){
        float totalPrice = coffeeService.getCostWithoutEmployeesRank(orderDTO.getName(), orderDTO.getArabica(), orderDTO.getCupkind());
        orderDTO.setTotalPrice(totalPrice);
        orderService.addToOrders(orderDTO);
        return "redirect:/me";
    }
}
