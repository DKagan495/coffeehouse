package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Order;
import com.example.coffeehouse.models.constkits.OrderStatus;
import com.example.coffeehouse.repositories.EmployeeRepository;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final EmployeeRepository employeeRepository;

    private final HttpSession httpSession;


    public OrderService(OrderRepository orderRepository, EmployeeRepository employeeRepository, HttpSession httpSession) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.httpSession = httpSession;
    }

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

    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getCurrentEmployeeOrders(){
        return orderRepository.findByEmployeesId((int) httpSession.getAttribute("USER_ID"));
    }

    public List<Order> getCurrentClientOrders(){
        return orderRepository.findByClientId((long) httpSession.getAttribute("USER_ID"));
    }

    public List<Order> getCurrentClientCompleteOrders(){
        return orderRepository.findByClientIdAndStatus((long) httpSession.getAttribute("USER_ID"), OrderStatus.COMPLETE.getStatus());
    }

    public List<Order> getCurrentClientTakenOrders(){
        return orderRepository.findByClientIdAndStatus((long) httpSession.getAttribute("USER_ID"), OrderStatus.TAKEN.getStatus());
    }

    public Order getOrder(int id){
        return orderRepository.findById(id);
    }

    public List<Order> getEmployeeOrders(int id){
        return orderRepository.findByEmployeesId(id);
    }

    public void updOrder(int id, String name, String arabica, String cup, int employeesId, BigDecimal totalPrice){
        orderRepository.updOrder(id, name, arabica, cup, employeesId);
        orderRepository.updTotalPrice(id, totalPrice);
    }

    public void setInProcessStatus(int id){
        Order order = orderRepository.findById(id);
        order.setStatus(OrderStatus.INPROCESS.getStatus());
        orderRepository.save(order);
    }

    public void setCompleteStatus(int id){
        Order order = orderRepository.findById(id);
        BigDecimal totalPrice = order.getTotalPrice().add(employeeRepository.findById(order.getEmployeesId()).get().getRank().getAddition());
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
