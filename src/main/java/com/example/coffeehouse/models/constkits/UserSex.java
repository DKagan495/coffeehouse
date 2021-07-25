package com.example.coffeehouse.models.constkits;

public enum UserSex {
    MALE("Male"), FEMALE("Female"), TRANSGENDER("Transgender"), UNDECIDED("Undecided");
    private final String sex;

    UserSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
