package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.Order;
import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.models.constkits.CupSizes;
import com.example.coffeehouse.models.constkits.OrderStatus;
import com.example.coffeehouse.services.ClientService;
import com.example.coffeehouse.services.CoffeeService;
import com.example.coffeehouse.services.EmployeeService;
import com.example.coffeehouse.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

@Controller
public class CoffeeController {

    private final HttpSession httpSession;

    private final CoffeeService coffeeService;

    private final OrderService orderService;

    private final EmployeeService employeeService;

    private final ClientService clientService;

    public CoffeeController(HttpSession httpSession, CoffeeService coffeeService, OrderService orderService, EmployeeService employeeService, ClientService clientService) {
        this.httpSession = httpSession;
        this.coffeeService = coffeeService;
        this.orderService = orderService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }


    @GetMapping("/getcoffee")
    public String toGetCoffeeForm(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") == null || !httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT").equals(AuthResult.VALID))
            return "redirect:/auth";
        Order order = new Order();
        model.addAttribute("orderdto", order);
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("coffeelist", coffeeService.getAllCoffies());
        model.addAttribute("arabicalist", coffeeService.getArabicasNames());
        model.addAttribute("cupsizes", CupSizes.values());
        return "getcoffee";
    }
    @PostMapping("/getcoffee")
    public String sendToEmployee(@ModelAttribute("orderdto") Order order){
        System.out.println(order.getCupSize());
        BigDecimal totalPrice = coffeeService.getCostWithoutEmployeesRank(order.getName(), order.getArabica(), Stream.of(CupSizes.values()).filter(c->c.getSize().equals(order.getCupSize())).findFirst().orElseThrow(IllegalArgumentException::new).getCost());
        totalPrice.setScale(2, RoundingMode.HALF_DOWN);
        System.out.println("Session user_id = " + (long) httpSession.getAttribute("USER_ID"));
        order.setClient(clientService.toClientPage((long) httpSession.getAttribute("USER_ID")));
        order.setStatus(OrderStatus.NOTSTARTED.getStatus());
        order.setTotalPrice(totalPrice);
        orderService.addToOrders(order);
        return "redirect:/me";
    }
}
