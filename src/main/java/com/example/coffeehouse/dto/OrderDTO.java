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
    private int clientId;
    private String name;
    private String arabica;
    private String cupkind;
    private double totalPrice;
    private String status;

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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
