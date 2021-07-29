package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.repositories.ClientCrudRepostirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientCrudRepostirory clientCrudRepostirory;

    private final HttpSession httpSession;

    private final OrderService orderService;

    public ClientService(ClientCrudRepostirory clientCrudRepostirory, HttpSession httpSession, OrderService orderService) {
        this.clientCrudRepostirory = clientCrudRepostirory;
        this.httpSession = httpSession;
        this.orderService = orderService;
    }

    public Client toClientPage(long id){
       return clientCrudRepostirory.findById(id);
    }

    public List<Client> toClientList(){
        return (List<Client>) clientCrudRepostirory.findAll();
    }

    public void moneyToCurrnetClient(BigDecimal money){
        clientCrudRepostirory.updMoney((long)httpSession.getAttribute("USER_ID"), money);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void minusCurrnetClientMoney(BigDecimal money){
        clientCrudRepostirory.updMoney((long)httpSession.getAttribute("USER_ID"), new BigDecimal(0).subtract(money));
    }

    public void updateClientGeneralInfo(String name, String surname, int age, String sex){
        clientCrudRepostirory.updClientGeneralInfo((long) httpSession.getAttribute("USER_ID"), name, surname, age, sex);
    }

    public void updateClientLogInInfo(String email, String password){
        clientCrudRepostirory.updClientLogInInfo((long) httpSession.getAttribute("USER_ID"), email, password);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void deleteCurrentUserAccount(){
        orderService.deleteClientOrders((long) httpSession.getAttribute("USER_ID"));
        clientCrudRepostirory.deleteById((long) httpSession.getAttribute("USER_ID"));
        httpSession.invalidate();
    }
}