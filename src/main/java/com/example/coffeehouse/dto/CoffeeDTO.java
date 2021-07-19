package com.example.coffeehouse.dto;

public class CoffeeDTO {
    private String name;
    private String arabica;
    private String cupkind;
    private float totalPrice;

    public CoffeeDTO() {
    }

    public CoffeeDTO(String name, String arabica, String cupkind) {
        this.name = name;
        this.arabica = arabica;
        this.cupkind = cupkind;
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
