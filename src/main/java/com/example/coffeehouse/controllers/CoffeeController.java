package com.example.coffeehouse.controllers;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.models.converters.CupSizes;
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
import java.util.Locale;
import java.util.stream.Stream;

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
        OrderDTO orderDTO = new OrderDTO();
        model.addAttribute("orderdto", orderDTO);
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("coffeelist", coffeeService.getAllCoffies());
        model.addAttribute("arabicalist", coffeeService.getArabicasNames());
        model.addAttribute("cupsizes", CupSizes.values());
        return "getcoffee";
    }
    @PostMapping("/getcoffee")
    public String sendToEmployee(@ModelAttribute("orderdto") OrderDTO orderDTO){
        double totalPrice = coffeeService.getCostWithoutEmployeesRank(orderDTO.getName(), orderDTO.getArabica(), Stream.of(orderDTO.getCupSizes()).filter(cs->cs.getSize().equals(orderDTO.getCupSize())).findFirst().orElseThrow(IllegalAccessError::new).getCost());
        System.out.println("Session user_id = " + (int) httpSession.getAttribute("USER_ID"));
        orderDTO.setClientId((int) httpSession.getAttribute("USER_ID"));
        orderDTO.setStatus("not started");
        orderDTO.setTotalPrice(totalPrice);
        orderService.addToOrders(orderDTO);
        return "redirect:/me";
    }
}
