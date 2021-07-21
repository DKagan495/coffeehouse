package com.example.coffeehouse.services;

import com.example.coffeehouse.models.Client;
import com.example.coffeehouse.repositories.ClientCrudRepostirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientCrudRepostirory clientCrudRepostirory;

    public Client toClientPage(int id){
       return clientCrudRepostirory.findById(id);
    }
    public List<Client> toClientList(){
        return (List<Client>) clientCrudRepostirory.findAll();
    }
}