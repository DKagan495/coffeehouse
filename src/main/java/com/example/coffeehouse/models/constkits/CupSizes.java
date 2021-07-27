package com.example.coffeehouse.models.constkits;

import java.math.BigDecimal;

public enum CupSizes {
    LITTLE("Little", new BigDecimal(1.4)), MEDIUM("Medium", new BigDecimal(2.7)), BIG("Big", new BigDecimal(3.6));

    private final String size;
    private final BigDecimal cost;
    CupSizes(String size, BigDecimal cost) {
        this.size = size;
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
