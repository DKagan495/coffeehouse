package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Client;

import org.springframework.data.repository.CrudRepository;

public interface AuthorizationClientCrudRepository extends CrudRepository<Client, Integer> {
    Client findByEmailAndPassword(String email, String password);
}
