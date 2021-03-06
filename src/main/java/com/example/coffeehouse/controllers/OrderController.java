package com.example.coffeehouse.controllers;

import com.example.coffeehouse.models.Order;
import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.models.constkits.AuthResult;
import com.example.coffeehouse.models.constkits.CupSizes;
import com.example.coffeehouse.models.constkits.OrderStatus;
import com.example.coffeehouse.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class OrderController{

    private final HttpSession httpSession;

    private final OrderService orderService;

    private final EmployeeService employeeService;

    private final ClientService clientService;

    private final CoffeeService coffeeService;

    private final MoneyTransferService moneyTransferService;

    public OrderController(HttpSession httpSession, OrderService orderService, EmployeeService employeeService, ClientService clientService, CoffeeService coffeeService, MoneyTransferService moneyTransferService) {
        this.httpSession = httpSession;
        this.orderService = orderService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.coffeeService = coffeeService;
        this.moneyTransferService = moneyTransferService;
    }

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
        if(httpSession.getAttribute("USER_ROLE").equals("employee")) {
           List<Order> employeeOrders = employeeService.getEmployee((int) httpSession.getAttribute("USER_ID")).getOrderList().stream().filter(order -> !order.getStatus().equals(OrderStatus.COMPLETE.getStatus()) && !order.getStatus().equals(OrderStatus.TAKEN.getStatus())).collect(Collectors.toList());
            model.addAttribute("myorders", employeeOrders);
        }
        if (httpSession.getAttribute("USER_ROLE").equals("client")) {
            List<Order> clientOrders = clientService.toClientPage((long) httpSession.getAttribute("USER_ID")).getOrderList().stream().filter(order -> !order.getStatus().equals(OrderStatus.COMPLETE.getStatus()) && !order.getStatus().equals(OrderStatus.TAKEN.getStatus())).collect(Collectors.toList());
            model.addAttribute("myorders", clientOrders);
        }
        return "myorders";
    }

    @GetMapping("/employees/{id}/orders")
    public String employeeOrders(@PathVariable int id, Model model) {
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        model.addAttribute("myorders", employeeService.getEmployee(id).getOrderList().stream().filter(order -> !order.getStatus().equals(OrderStatus.TAKEN.getStatus()) && !order.getStatus().equals(OrderStatus.COMPLETE.getStatus())).collect(Collectors.toList()));
        return "myorders";
    }

    @GetMapping("/orders/{id}")
    public String employeeOrder(@PathVariable int id, Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_EMPLOYEE") != AuthResult.VALID && httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        System.out.println(orderService.getOrder(id).getClient().getId() + "clientsId");
        Client client = clientService.toClientPage(orderService.getOrder(id).getClient().getId());
        Employee employee = employeeService.getEmployee(orderService.getOrder(id).getEmployee().getId());
        model.addAttribute("client", client);
        model.addAttribute("employee", employee);
        model.addAttribute("order", orderService.getOrder(id));
        return "order";
    }

    @GetMapping("/myorders/complete")
    public String getMyCompleteOrders(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        List<Order> completeOrders = clientService.toClientPage((long) httpSession.getAttribute("USER_ID")).getOrderList().stream().filter(o->o.getStatus().equals(OrderStatus.COMPLETE.getStatus())).collect(Collectors.toList());
        model.addAttribute("myorders", completeOrders);
        return "myorders";
    }

    @GetMapping("/myorders/taken")
    public String getMyTakenOrders(Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        List<Order> takenOrders = clientService.toClientPage((long) httpSession.getAttribute("USER_ID")).getOrderList().stream().filter(o->o.getStatus().equals(OrderStatus.TAKEN.getStatus())).collect(Collectors.toList());
        model.addAttribute("myorders", takenOrders);
        return "myorders";
    }

    @GetMapping("/orders/{id}/get")
    public String getOrderByClient(@PathVariable int id){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        if(clientService.toClientPage((long) httpSession.getAttribute("USER_ID")).getMoney().compareTo(orderService.getOrder(id).getTotalPrice()) < 0)
            return "redirect:/orders/" + id;
        orderService.setTakenStatus(id);
        moneyTransferService.clientToEmployeeMoneyTransferByOrderId((long)httpSession.getAttribute("USER_ID"), id);
        return "redirect:/me";
    }

    @GetMapping("/orders/{id}/edit")
    public String toEditOrderForm(@PathVariable int id, Model model){
        if(httpSession.getAttribute("AUTHORIZATION_RESULT_CLIENT") != AuthResult.VALID)
            return "redirect:/auth";
        if(orderService.getOrder(id).getStatus().equals(OrderStatus.NOTSTARTED.getStatus()) && orderService.getOrder(id).getClient().getId() == (long) httpSession.getAttribute("USER_ID") && httpSession.getAttribute("USER_ROLE").equals("client")) {
            model.addAttribute("order", orderService.getOrder(id));
            model.addAttribute("employees", employeeService.getAllEmployees());
            model.addAttribute("coffeelist", coffeeService.getAllCoffies());
            model.addAttribute("arabicalist", coffeeService.getArabicasNames());
            model.addAttribute("statuses", OrderStatus.values());
            return "editorderform";
        }
        return "redirect:/me";
    }

    @PatchMapping("/orders/{id}/edit")
    public String updateOrder(@PathVariable int id, @ModelAttribute("order") Order order){
        BigDecimal totalPrice = coffeeService.getCostWithoutEmployeesRank(order.getName(), order.getArabica(), Stream.of(CupSizes.values()).filter(c->c.getSize().equals(order.getCupSize())).findFirst().orElseThrow(IllegalArgumentException::new).getCost());
        orderService.updOrder(order.getId(), order.getName(), order.getArabica(), order.getCupSize(), order.getEmployee(), totalPrice);
        return "redirect:/orders/" + order.getId();
    }


}
