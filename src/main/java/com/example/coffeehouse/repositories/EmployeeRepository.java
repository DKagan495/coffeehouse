package com.example.coffeehouse.repositories;

import com.example.coffeehouse.models.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Modifying
    @Query("update Employee e set e.money = e.money + :money where e.id = :id")
    public void updEmployeesMoney(@Param("id") int id, @Param("money") BigDecimal money);
}
