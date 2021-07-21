package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientCrudRepostirory extends CrudRepository<Client, Integer> {
    public Client findById(int id);
}
