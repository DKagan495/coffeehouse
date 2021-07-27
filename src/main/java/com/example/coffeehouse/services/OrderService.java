package com.example.coffeehouse.services;

import com.example.coffeehouse.dto.OrderDTO;
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
    public List<OrderDTO> getCurrentClientOrders(){
        return orderRepository.findByClientId((int) httpSession.getAttribute("USER_ID"));
    }

    @Transactional
    public List<OrderDTO> getCurrentClientCompleteOrders(){
        return orderRepository.findByClientIdAndStatus((int) httpSession.getAttribute("USER_ID"), OrderStatus.COMPLETE.getStatus());
    }

    @Transactional
    public List<OrderDTO> getCurrentClientTakenOrders(){
        return orderRepository.findByClientIdAndStatus((int) httpSession.getAttribute("USER_ID"), OrderStatus.TAKEN.getStatus());
    }

    @Transactional
    public OrderDTO getOrder(int id){
        return orderRepository.findById(id);
    }

    @Transactional
    public List<OrderDTO> getEmployeeOrders(int id){
        return orderRepository.findByEmployeesId(id);
    }

    @Transactional
    public void updOrder(int id, String name, String arabica, String cup, int employeesId, BigDecimal totalPrice){
        orderRepository.updOrder(id, name, arabica, cup, employeesId);
        orderRepository.updTotalPrice(id, totalPrice);
    }

    @Transactional
    public void setInProcessStatus(int id){
        OrderDTO orderDTO = orderRepository.findById(id);
        orderDTO.setStatus(OrderStatus.INPROCESS.getStatus());
        orderRepository.save(orderDTO);
    }

    @Transactional
    public void setCompleteStatus(int id){
        OrderDTO orderDTO = orderRepository.findById(id);
        BigDecimal totalPrice = orderDTO.getTotalPrice().add(employeeRepository.findById(orderDTO.getEmployeesId()).get().getRank().getAddition());
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_DOWN);
        orderDTO.setTotalPrice(totalPrice);

        orderDTO.setStatus(OrderStatus.COMPLETE.getStatus());
        orderRepository.save(orderDTO);
    }

    @Transactional
    public void setTakenStatus(int id ){
        OrderDTO orderDTO = orderRepository.findById(id);
        orderDTO.setStatus(OrderStatus.TAKEN.getStatus());
        orderRepository.save(orderDTO);
    }

    @Transactional
    public void deleteClientOrders(int clientId){
        orderRepository.deleteByClientId(clientId);
    }
}
