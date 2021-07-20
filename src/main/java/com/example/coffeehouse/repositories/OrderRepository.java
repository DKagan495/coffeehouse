package com.example.coffeehouse.repositories;

import com.example.coffeehouse.dto.OrderDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDTO, String> {
    public OrderDTO findById(int id);
    public List<OrderDTO> findByEmployeesId(int id);
    public List<OrderDTO> findByEmployeesName(String employeesName);
}
