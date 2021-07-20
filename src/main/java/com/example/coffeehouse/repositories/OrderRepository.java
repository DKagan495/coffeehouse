package com.example.coffeehouse.repositories;

import com.example.coffeehouse.dto.OrderDTO;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderDTO, String> {
    public OrderDTO findByEmployeesName(String employeesName);
}
