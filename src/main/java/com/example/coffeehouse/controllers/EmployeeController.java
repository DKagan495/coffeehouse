package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.services.EmployeeService;
import com.example.coffeehouse.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String toEmployeePage(@PathVariable int id, Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "employee";
    }
    @GetMapping("/mycard")
    public String toMyPage(){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID)
            return "redirect:/auth";
        System.out.println(httpSession.getAttribute("USER_ID"));
        return "redirect:/employees/" + httpSession.getAttribute("USER_ID");
    }
    @GetMapping("/employees")
    public String getEmployeesList(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employeelist";
    }
    @GetMapping("/orders/{id}/take")
    public String takeOrder(@PathVariable int id){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID)
            return "redirect:/auth";
        orderService.setInProcessStatus(id);
        return "redirect:/orders/" + id;
    }

    @GetMapping("/orders/{id}/complete")
    public String completeOrder(@PathVariable int id){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID)
            return "redirect:/auth";
        orderService.setCompleteStatus(id);
        return "redirect:/orders/" + id;
    }

}
