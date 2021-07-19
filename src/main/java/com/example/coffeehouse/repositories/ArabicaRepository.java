package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Arabica;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArabicaRepository extends CrudRepository<Arabica, String> {
    public Arabica findByName(String name);
}
