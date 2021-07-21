package com.example.coffeehouse.controllers;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.services.ClientService;
import com.example.coffeehouse.services.EmployeeAuthorizationService;
import com.example.coffeehouse.services.EmployeeService;
import com.example.coffeehouse.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OrderService orderService;

    @GetMapping("/employees/{id}")
    public String toClientPage(@PathVariable int id, Model model){
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employee";
    }
    @GetMapping("/mycard")
    public String toMyPage(){
        System.out.println(httpSession.getAttribute("USER_ID"));
        return "redirect:/employees/" + httpSession.getAttribute("USER_ID");
    }
    @GetMapping("/employees")
    public String getClientList(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employeelist";
    }
    @GetMapping("/orders/{id}/take")
    public String takeOrder(@PathVariable int id){
        orderService.setInProcessStatus(id);
        return "redirect:/orders/" + id;
    }

    @GetMapping("/orders/{id}/complete")
    public String completeOrder(@PathVariable int id){
        orderService.setCompleteStatus(id);
        return "redirect:/orders/" + id;
    }

}
