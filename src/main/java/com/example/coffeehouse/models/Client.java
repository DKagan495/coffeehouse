package com.example.coffeehouse.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    @NotEmpty(message = "Field name is empty")
    @Size(min = 2, max = 28, message = "Name is not valid")
    private String name;
    @NotEmpty(message = "Field surname is empty")
    @Size(min = 2, max = 30, message = "Surname is not valid")
    private String surname;
    @NotEmpty(message = "Field email is empty")
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Field password is empty")
    @Size(min = 8, message = "Invalid password (the password must contain 8 charachters at least)")
    private String password;
    private String sex;
    @Min(value = 0, message = "Age less than zero")
    @Min(value = 12, message = "Age should be more than 12")
    private int age;
    private BigDecimal money;
    @OneToMany(mappedBy = "client")
    private List<Order> orderList;
    public Client(){

    }
    public Client(long id, String name, String surname, String email, String password, String sex, int age, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
