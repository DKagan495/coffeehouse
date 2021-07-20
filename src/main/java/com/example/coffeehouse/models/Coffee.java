package com.example.coffeehouse.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coffee {
    @Id
    private int id;
    private String name;
    private float cost;

    public Coffee() {
    }

    public Coffee(int id, String name, float cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
