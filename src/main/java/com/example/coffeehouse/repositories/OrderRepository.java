package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Employee;
import com.example.coffeehouse.models.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OrderRepository extends CrudRepository<Order, String> {
    Order findById(int id);
    void deleteByClientId(long id);
    @Modifying
    @Query("update Order o set o.name = :coffee, o.arabica = :arabica, o.cupSize = :cup, o.employee = :employee where o.id = :id")
    void updOrder(@Param("id") int id, @Param("coffee") String coffee, @Param("arabica") String arabica, @Param("cup") String cup, @Param("employee") Employee employees);
    @Modifying
    @Query("update Order o set o.totalPrice = :totalPrice where o.id = :id")
    void updTotalPrice(@Param("id") int id, @Param("totalPrice") BigDecimal totalPrice);
}
