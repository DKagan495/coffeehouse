package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.repositories.ClientCrudRepostirory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientCrudRepostirory clientCrudRepostirory;

    private final OrderService orderService;

    public ClientService(ClientCrudRepostirory clientCrudRepostirory, OrderService orderService) {
        this.clientCrudRepostirory = clientCrudRepostirory;
        this.orderService = orderService;
    }

    public Client toClientPage(long id){
       return clientCrudRepostirory.findById(id);
    }

    public List<Client> toClientList(){
        return (List<Client>) clientCrudRepostirory.findAll();
    }

    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.SERIALIZABLE)
    public void minusCurrnetClientMoney(long id, BigDecimal money){
        clientCrudRepostirory.updMoney(id, new BigDecimal(0).subtract(money));
    }

    public void updateClientGeneralInfo(long id, String name, String surname, int age, String sex){
        clientCrudRepostirory.updClientGeneralInfo(id, name, surname, age, sex);
    }

    public void updateClientLogInInfo(long id, String email, String password){
        clientCrudRepostirory.updClientLogInInfo(id, email, password);
    }

    public void deleteCurrentUserAccount(long id){
        orderService.deleteClientOrders(id);
        clientCrudRepostirory.deleteById(id);
    }
}