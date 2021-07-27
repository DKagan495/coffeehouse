package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Order;
import com.example.coffeehouse.models.constkits.OrderStatus;
import com.example.coffeehouse.repositories.EmployeeRepository;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    HttpSession httpSession;

    @Transactional
    public void addToOrders(Order order){
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        orderList.sort((o1, o2) -> o1.getId() - o2.getId());
        if(orderList.isEmpty())
            order.setId(0);
        else
            order.setId(orderList.get(orderList.size()-1).getId()+1);
        System.out.println("order id = " + order.getId());
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    @Transactional
    public List<Order> getCurrentEmployeeOrders(){
        return orderRepository.findByEmployeesId((int) httpSession.getAttribute("USER_ID"));
    }

    @Transactional
    public List<Order> getCurrentClientOrders(){
        return orderRepository.findByClientId((int) httpSession.getAttribute("USER_ID"));
    }

    @Transactional
    public List<Order> getCurrentClientCompleteOrders(){
        return orderRepository.findByClientIdAndStatus((int) httpSession.getAttribute("USER_ID"), OrderStatus.COMPLETE.getStatus());
    }

    @Transactional
    public List<Order> getCurrentClientTakenOrders(){
        return orderRepository.findByClientIdAndStatus((int) httpSession.getAttribute("USER_ID"), OrderStatus.TAKEN.getStatus());
    }

    @Transactional
    public Order getOrder(int id){
        return orderRepository.findById(id);
    }

    @Transactional
    public List<Order> getEmployeeOrders(int id){
        return orderRepository.findByEmployeesId(id);
    }

    @Transactional
    public void updOrder(int id, String name, String arabica, String cup, int employeesId, BigDecimal totalPrice){
        orderRepository.updOrder(id, name, arabica, cup, employeesId);
        orderRepository.updTotalPrice(id, totalPrice);
    }

    @Transactional
    public void setInProcessStatus(int id){
        Order order = orderRepository.findById(id);
        order.setStatus(OrderStatus.INPROCESS.getStatus());
        orderRepository.save(order);
    }

    @Transactional
    public void setCompleteStatus(int id){
        Order order = orderRepository.findById(id);
        BigDecimal totalPrice = order.getTotalPrice().add(employeeRepository.findById(order.getEmployeesId()).get().getRank().getAddition());
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_DOWN);
        order.setTotalPrice(totalPrice);

        order.setStatus(OrderStatus.COMPLETE.getStatus());
        orderRepository.save(order);
    }

    @Transactional
    public void setTakenStatus(int id ){
        Order order = orderRepository.findById(id);
        order.setStatus(OrderStatus.TAKEN.getStatus());
        orderRepository.save(order);
    }

    @Transactional
    public void deleteClientOrders(int clientId){
        orderRepository.deleteByClientId(clientId);
    }
}
