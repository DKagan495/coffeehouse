package com.example.coffeehouse.models.constkits;

public enum UserSex {
    MALE("Male"), FEMALE("Female");
    private final String sex;

    UserSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
