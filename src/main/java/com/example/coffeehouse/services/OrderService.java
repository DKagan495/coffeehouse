package com.example.coffeehouse.services;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void addToOrders(OrderDTO orderDTO){
        orderRepository.save(orderDTO);
    }
}
