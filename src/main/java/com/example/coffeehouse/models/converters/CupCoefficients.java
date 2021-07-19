package com.example.coffeehouse.models.converters;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CupCoefficients {
    @Id
    private String kind;
    private float coefficient;

    public CupCoefficients() {
    }

    public CupCoefficients(String kind, float coefficient) {
        this.kind = kind;
        this.coefficient = coefficient;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }
}
