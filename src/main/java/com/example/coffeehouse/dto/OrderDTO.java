package com.example.coffeehouse.dto;

import com.example.coffeehouse.models.constkits.CupSizes;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "orders")
public class OrderDTO {
    @Id
    private int id;
    private int employeesId;
    private int clientId;
    private String name;
    private String arabica;
    private String cupSize;
    private BigDecimal totalPrice;
    private String status;

    public OrderDTO() {
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

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public BigDecimal getTotalPrice() {
        totalPrice.setScale(2, RoundingMode.HALF_DOWN);
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
