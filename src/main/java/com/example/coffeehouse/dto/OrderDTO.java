package com.example.coffeehouse.dto;

import com.example.coffeehouse.models.constkits.CupSizes;

import javax.persistence.*;

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
    @Transient
    @Enumerated(EnumType.STRING)
    private CupSizes cupSizes;
    private double totalPrice;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(String name, String arabica, String cupSize) {
        this.name = name;
        this.arabica = arabica;
        this.cupSize = cupSize;
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

    public CupSizes getCupSizes() {
        return cupSizes;
    }

    public void setCupSizes(CupSizes cupSizes) {
        this.cupSizes = cupSizes;
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
