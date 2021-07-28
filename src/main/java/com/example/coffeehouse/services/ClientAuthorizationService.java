package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.repositories.AuthorizationClientCrudRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientAuthorizationService {

    private final AuthorizationClientCrudRepository authorizationClientCrudRepository;

    public ClientAuthorizationService(AuthorizationClientCrudRepository authorizationClientCrudRepository) {
        this.authorizationClientCrudRepository = authorizationClientCrudRepository;
    }

    @Transactional
    public void addClientToDataBase(Client client){
        List<Client> clientList = (List<Client>) authorizationClientCrudRepository.findAll();
        System.out.println(clientList.size());
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
