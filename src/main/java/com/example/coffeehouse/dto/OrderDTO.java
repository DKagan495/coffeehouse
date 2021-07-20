package com.example.coffeehouse.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderDTO {
    @Id
    private int id;
    private int employeesId;
    private String employeesName;
    private String employeesSurname;
    private String name;
    private String arabica;
    private String cupkind;
    private float totalPrice;

    public OrderDTO() {
    }

    public OrderDTO(String name, String arabica, String cupkind) {
        this.name = name;
        this.arabica = arabica;
        this.cupkind = cupkind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(int employeesId) {
        this.employeesId = employeesId;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    public String getEmployeesSurname() {
        return employeesSurname;
    }

    public void setEmployeesSurname(String employeesSurname) {
        this.employeesSurname = employeesSurname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArabica() {
        return arabica;
    }

    public void setArabica(String arabica) {
        this.arabica = arabica;
    }

    public String getCupkind() {
        return cupkind;
    }

    public void setCupkind(String cupkind) {
        this.cupkind = cupkind;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
