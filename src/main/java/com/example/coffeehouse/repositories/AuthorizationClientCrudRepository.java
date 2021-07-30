package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AuthorizationClientCrudRepository extends CrudRepository<Client, Integer> {
    Client findByEmailAndPassword(String email, String password);
}
