package com.example.coffeehouse.models.converters;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CupCoefficients {
    @Id
    private String kind;
    private float cost;

    public CupCoefficients() {
    }

    public CupCoefficients(String kind, float cost) {
        this.kind = kind;
        this.cost = cost;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
