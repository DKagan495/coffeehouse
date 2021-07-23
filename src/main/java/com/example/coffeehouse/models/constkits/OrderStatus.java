package com.example.coffeehouse.models.constkits;

public enum OrderStatus {
    NOTSTARTED("not started"), INPROCESS("in process"), COMPLETE("complete"), TAKEN("taken");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
