package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Coffee;
import com.example.coffeehouse.models.converters.CoffeeCosts;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeCostsRepository extends CrudRepository<CoffeeCosts, Integer> {
}
