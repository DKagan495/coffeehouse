package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientCrudRepostirory extends CrudRepository<Client, Integer> {
    public Client findById(int id);

    @Modifying
    @Query("update Client c set c.money = :money where c.id = :id")
    public void updMoney(@Param("id") int id, @Param("money") double money);
}
