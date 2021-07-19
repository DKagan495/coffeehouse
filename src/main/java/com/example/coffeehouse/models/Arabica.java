package com.example.coffeehouse.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Arabica {
    @Id
    private String name;
    private float cost;

    public Arabica(){

    }
    public Arabica(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
