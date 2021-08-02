package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.models.Order;
import com.example.coffeehouse.models.constkits.OrderStatus;
import com.example.coffeehouse.repositories.EmployeeRepository;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final EmployeeRepository employeeRepository;


    public OrderService(OrderRepository orderRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    public void addToOrders(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrder(int id){
        return orderRepository.findById(id);
    }

    public void updOrder(int id, String name, String arabica, String cup, Employee employee, BigDecimal totalPrice){
        orderRepository.updOrder(id, name, arabica, cup, employee);
        orderRepository.updTotalPrice(id, totalPrice);
    }

    public void setInProcessStatus(int id){
        Order order = orderRepository.findById(id);
        order.setStatus(OrderStatus.INPROCESS.getStatus());
        orderRepository.save(order);
    }

    public void setCompleteStatus(int id){
        Order order = orderRepository.findById(id);
        BigDecimal totalPrice = order.getTotalPrice().add(employeeRepository.findById(order.getEmployee().getId()).get().getRank().getAddition());
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_DOWN);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.COMPLETE.getStatus());
        orderRepository.save(order);
    }

    public void setTakenStatus(int id){
        Order order = orderRepository.findById(id);
        order.setStatus(OrderStatus.TAKEN.getStatus());
        orderRepository.save(order);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteClientOrders(long clientId){
        orderRepository.deleteByClientId(clientId);
    }
}
