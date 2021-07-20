package com.example.coffeehouse.controllers;

import com.example.coffeehouse.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public String allOrders(Model model){
        model.addAttribute("orderlist", orderService.getAllOrders());
        return "orderlist";
    }
    @GetMapping("/myorders")
    public String currentEmployeeOrders(Model model){
        model.addAttribute("myorders", orderService.getCurrentEmployeeOrders());
        return "myorders";
    }
    @GetMapping("/employees/{id}/orders")
    public String employeeOrders(@PathVariable int id, Model model) {
        model.addAttribute("orderlist", orderService.getEmployeeOrders(id));
        return "orderlist";
    }
    @GetMapping("/orders/{id}")
    public String employeeOrder(@PathVariable int id, Model model){
        model.addAttribute("order", orderService.getOrder(id));
        return "order";
    }
}
