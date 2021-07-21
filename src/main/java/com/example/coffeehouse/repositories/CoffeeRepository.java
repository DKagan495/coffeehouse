package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Coffee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeRepository extends CrudRepository<Coffee, Integer> {
    public Coffee findByName(String name);

    @Query(value = "select name from arabica", nativeQuery = true)
    public List<String> getAllArabica();
}
