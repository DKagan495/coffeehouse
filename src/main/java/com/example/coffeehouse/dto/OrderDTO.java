package com.example.coffeehouse.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderDTO {
    //private int coffeeId;
    @Id
    private String employeesName;
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

   /* public int getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }*/

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
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
