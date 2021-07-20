package com.example.coffeehouse.services;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    HttpSession httpSession;

    @Transactional
    public void addToOrders(OrderDTO orderDTO){
        orderRepository.save(orderDTO);
    }

    @Transactional
    public List<OrderDTO> getAllOrders(){
        return (List<OrderDTO>) orderRepository.findAll();
    }

    @Transactional
    public List<OrderDTO> getCurrentEmployeeOrder(){
        return orderRepository.findByEmployeesName((String) httpSession.getAttribute("USER_NAME"));
    }
}
