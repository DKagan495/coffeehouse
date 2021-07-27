package com.example.coffeehouse.models.constkits;

import java.math.BigDecimal;

public enum Rank {
    BEGINNER("Beginner", new BigDecimal(1.1)), MIDDLE("Middle", new BigDecimal(2.0)), MASTER("Master", new BigDecimal(3.6));
    private final String kind;
    private final BigDecimal addition;

    Rank(String kind, BigDecimal addition) {
        this.kind = kind;
        this.addition = addition;
    }

    public String getKind() {
        return kind;
    }

    public BigDecimal getAddition() {
        return addition;
    }
}
