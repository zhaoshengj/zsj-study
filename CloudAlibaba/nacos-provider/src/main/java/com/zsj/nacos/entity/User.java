package com.zsj.nacos.entity;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class User implements Serializable {

    private int id;

    private String name;

    private int age;

    private BigDecimal money;



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
