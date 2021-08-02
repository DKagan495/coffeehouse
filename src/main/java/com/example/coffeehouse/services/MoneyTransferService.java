package com.example.coffeehouse.services;

import com.example.coffeehouse.repositories.ClientCrudRepostirory;
import com.example.coffeehouse.repositories.EmployeeRepository;
import com.example.coffeehouse.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class MoneyTransferService {

    private final ClientService clientService;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientCrudRepostirory clientCrudRepostirory;

    public MoneyTransferService(ClientService clientService, OrderRepository orderRepository, EmployeeRepository employeeRepository, ClientCrudRepostirory clientCrudRepostirory) {
        this.clientService = clientService;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.clientCrudRepostirory = clientCrudRepostirory;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void clientToEmployeeMoneyTransferByOrderId(long id, int orderId){
        clientService.minusCurrnetClientMoney(id, orderRepository.findById(orderId).getTotalPrice());
        employeeRepository.updEmployeesMoney(orderRepository.findById(orderId).getEmployee().getId(), orderRepository.findById(orderId).getTotalPrice());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void moneyToCurrentClient(long id, BigDecimal money){
        clientCrudRepostirory.updMoney(id, money);
    }
}
