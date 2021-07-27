package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ClientCrudRepostirory extends CrudRepository<Client, Integer> {
    public Client findById(int id);

    @Modifying
    @Query("update Client c set c.money = c.money + :money where c.id = :id")
    public void updMoney(@Param("id") int id, @Param("money") BigDecimal money);

    @Modifying
    @Query("update Client c set c.name = :name, c.surname = :surname, c.age = :age, c.sex = :sex where c.id = :id")
    public void updClientGeneralInfo(@Param("id") int id, @Param("name") String name, @Param("surname") String surname, @Param("age") int age, @Param("sex") String sex);

    @Modifying
    @Query("update Client c set c.email = :email, c.password = :password where c.id = :id")
    public void updClientLogInInfo(@Param("id") int id, @Param("email") String email, @Param("password") String password);

    public void deleteById(int id);
}
