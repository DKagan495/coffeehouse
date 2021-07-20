package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Integer> {
    public Coffee findByName(String name);
}
