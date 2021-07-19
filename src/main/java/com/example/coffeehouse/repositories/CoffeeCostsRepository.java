package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeCostsRepository extends CrudRepository<Coffee, Integer> {
}
