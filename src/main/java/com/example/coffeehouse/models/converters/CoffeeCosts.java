package com.example.coffeehouse.models.converters;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CoffeeCosts {
    @Id
    private int id;
    private float cost;

    public CoffeeCosts() {
    }

    public CoffeeCosts(int id, float cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
