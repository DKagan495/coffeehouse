package com.example.coffeehouse.models;

import com.example.coffeehouse.models.constkits.Rank;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private String surname;
    private int age;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private BigDecimal money;
    private String login;
    private String password;
    @OneToMany(mappedBy = "employee")
    private List<Order> orderList;

    public Employee() {
    }

    public Employee(int id, String name, String surname, int age, Rank rank, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.rank = rank;
        this.login = login;
        this.password = password;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
