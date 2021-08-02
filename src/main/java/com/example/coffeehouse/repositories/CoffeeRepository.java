package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Coffee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeRepository extends CrudRepository<Coffee, Integer> {
    Coffee findByName(String name);

    @Query(value = "select name from arabica", nativeQuery = true)
    List<String> getAllArabica();

    @Query(value = "select cost from arabica where name = ?1", nativeQuery = true)
    double getCostByArabicaName(String arabicaName);
}
