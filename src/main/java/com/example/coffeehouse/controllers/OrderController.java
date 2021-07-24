package com.example.coffeehouse.controllers;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.services.ClientService;
import com.example.coffeehouse.services.EmployeeService;
import com.example.coffeehouse.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController{
    @Autowired
    HttpSession httpSession;

    @Autowired
    OrderService orderService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ClientService clientService;

    @ModelAttribute("insfundsmsg")
    public String noPay(){
        return "INSUFFICIENT FUNDS";
    }

    @GetMapping("/orders")
    public String allOrders(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("clients", clientService.toClientList());
        model.addAttribute("orderlist", orderService.getAllOrders());
        return "orderlist";
    }
    @GetMapping("/myorders")
    public String currentUserOrders(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        if(httpSession.getAttribute("USER_ROLE").equals("employee"))
            model.addAttribute("myorders", orderService.getCurrentEmployeeOrders());
        if (httpSession.getAttribute("USER_ROLE").equals("client"))
            model.addAttribute("myorders", orderService.getCurrentClientOrders());
        return "myorders";
    }
    @GetMapping("/employees/{id}/orders")
    public String employeeOrders(@PathVariable int id, Model model) {
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("myorders", orderService.getEmployeeOrders(id));
        return "myorders";
    }
    @GetMapping("/orders/{id}")
    public String employeeOrder(@PathVariable int id, Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        System.out.println(orderService.getOrder(id).getClientId() + "clientsId");
        Client client = clientService.toClientPage(orderService.getOrder(id).getClientId());
        Employee employee = employeeService.getEmployee(orderService.getOrder(id).getEmployeesId());
        model.addAttribute("client", client);
        model.addAttribute("employee", employee);
        model.addAttribute("order", orderService.getOrder(id));
        return "order";
    }

    @GetMapping("/myorders/complete")
    public String getMyCompleteOrders(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("myorders", orderService.getCurrentClientCompleteOrders());
        return "myorders";
    }

    @GetMapping("/myorders/taken")
    public String getMyTakenOrders(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("myorders", orderService.getCurrentClientTakenOrders());
        return "myorders";
    }

    @GetMapping("/orders/{id}/get")
    public String getOrderByClient(@PathVariable int id){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        if(clientService.toClientPage((int) httpSession.getAttribute("USER_ID")).getMoney() < orderService.getOrder(id).getTotalPrice())
            return "redirect:/orders/" + id;
        orderService.setTakenStatus(id);
        clientService.moneyToCurrnetClient(- orderService.getOrder(id).getTotalPrice());
        return "redirect:/me";
    }


}
