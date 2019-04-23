package com.zsj.nacos.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {

    private int id;
    private String name;
    private int age;
    private BigDecimal money;

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


    public User(int id, String name, int age, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
