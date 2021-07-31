package com.example.coffeehouse.services;

import com.example.coffeehouse.repositories.EmployeeRepository;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoneyTransferService {

    private final ClientService clientService;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    public MoneyTransferService(ClientService clientService, OrderRepository orderRepository, EmployeeRepository employeeRepository) {
        this.clientService = clientService;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void clientToEmployeeMoneyTransferByOrderId(long id, int orderId){
        clientService.minusCurrnetClientMoney(id, orderRepository.findById(orderId).getTotalPrice());
        employeeRepository.updEmployeesMoney(orderRepository.findById(orderId).getEmployee().getId(), orderRepository.findById(orderId).getTotalPrice());
    }
}
