package com.example.coffeehouse.models;

import com.example.coffeehouse.models.constkits.UserSex;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Client {
    @Id
    private int id;
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
    public Client(){

    }
    public Client(int id, String name, String surname, String email, String password, String sex, int age, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.money = money;
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
}
