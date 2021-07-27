package com.example.coffeehouse.repositories;

import com.example.coffeehouse.dto.OrderDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDTO, String> {
    public OrderDTO findById(int id);
    public List<OrderDTO> findByEmployeesId(int id);
    public List<OrderDTO> findByClientId(int id);
    public List<OrderDTO> findByClientIdAndStatus(int id, String status);
    public void deleteByClientId(int id);
    @Modifying
    @Query("update OrderDTO o set o.name = :coffee, o.arabica = :arabica, o.cupSize = :cup, o.employeesId = :employeesId where o.id = :id")
    public void updOrder(@Param("id") int id, @Param("coffee") String coffee, @Param("arabica") String arabica, @Param("cup") String cup, @Param("employeesId") int employeesId);
    @Modifying
    @Query("update OrderDTO o set o.totalPrice = :totalPrice where o.id = :id")
    public void updTotalPrice(@Param("id") int id, @Param("totalPrice") BigDecimal totalPrice);
}
