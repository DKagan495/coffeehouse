package com.example.coffeehouse.models.converters;

public enum Rank {
    BEGINNER("Beginner", 1.1), MIDDLE("Middle", 2.0), MASTER("Master", 3.6);
    private final String kind;
    private final double addition;

    Rank(String kind, double addition) {
        this.kind = kind;
        this.addition = addition;
    }

    public String getKind() {
        return kind;
    }

    public double getAddition() {
        return addition;
    }
}
