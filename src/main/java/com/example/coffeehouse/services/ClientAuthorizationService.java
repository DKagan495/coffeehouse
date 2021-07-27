package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.repositories.AuthorizationClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientAuthorizationService {

    @Autowired
    private AuthorizationClientCrudRepository authorizationClientCrudRepository;

    @Transactional
    public void addClientToDataBase(Client client){
        List<Client> clientList = (List<Client>) authorizationClientCrudRepository.findAll();
        clientList.sort((o1, o2) -> o1.getId() - o2.getId());
        System.out.println(clientList.size());
        if(clientList.size() == 0)
            client.setId(0);
        else
            client.setId(clientList.get(clientList.size()-1).getId()+1);
        client.setMoney(new BigDecimal(0));
        authorizationClientCrudRepository.save(client);
    }

    @Transactional
    public Client checkLogInParameters(String email, String password){
        Client client = authorizationClientCrudRepository.findByEmailAndPassword(email, password);
        if(client != null)
            return client;
        return null;
    }


}
