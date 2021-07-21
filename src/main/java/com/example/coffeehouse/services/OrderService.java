package com.example.coffeehouse.services;

import com.example.coffeehouse.dto.OrderDTO;
import com.example.coffeehouse.models.Client;
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
        List<OrderDTO> orderList = (List<OrderDTO>) orderRepository.findAll();
        orderList.sort((o1, o2) -> o1.getId() - o2.getId());
        if(orderList.isEmpty())
            orderDTO.setId(0);
        else
            orderDTO.setId(orderList.get(orderList.size()-1).getId()+1);
        System.out.println("order id = " + orderDTO.getId());
        orderRepository.save(orderDTO);
    }

    @Transactional
    public List<OrderDTO> getAllOrders(){
        return (List<OrderDTO>) orderRepository.findAll();
    }

    @Transactional
    public List<OrderDTO> getCurrentEmployeeOrders(){
        return orderRepository.findByEmployeesId((int) httpSession.getAttribute("USER_ID"));
    }

    @Transactional
    public OrderDTO getOrder(int id){
        return orderRepository.findById(id);
    }

    @Transactional
    public List<OrderDTO> getEmployeeOrders(int id){
        return orderRepository.findByEmployeesId(id);
    }
}
