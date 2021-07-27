package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.repositories.ClientCrudRepostirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientCrudRepostirory clientCrudRepostirory;

    @Autowired
    HttpSession httpSession;

    @Transactional
    public Client toClientPage(int id){
       return clientCrudRepostirory.findById(id);
    }
    @Transactional
    public List<Client> toClientList(){
        return (List<Client>) clientCrudRepostirory.findAll();
    }

    @Transactional
    public void moneyToCurrnetClient(BigDecimal money){
        clientCrudRepostirory.updMoney((int)httpSession.getAttribute("USER_ID"), money);
    }
    @Transactional
    public void updateClientGeneralInfo(String name, String surname, int age, String sex){
        clientCrudRepostirory.updClientGeneralInfo((int) httpSession.getAttribute("USER_ID"), name, surname, age, sex);
    }

    @Transactional
    public void updateClientLogInInfo(String email, String password){
        clientCrudRepostirory.updClientLogInInfo((int) httpSession.getAttribute("USER_ID"), email, password);
    }

    @Transactional
    public void deleteCurrentUserAccount(){
        clientCrudRepostirory.deleteById((int) httpSession.getAttribute("USER_ID"));
        httpSession.invalidate();
    }
}