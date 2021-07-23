package com.example.coffeehouse.models.constkits;

public enum CupSizes {
    LITTLE("Little", 1.4), MEDIUM("Medium", 2.7), BIG("Big", 3.6);

    private final String size;
    private final double cost;
    CupSizes(String size, double cost) {
        this.size = size;
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public double getCost() {
        return cost;
    }
}
