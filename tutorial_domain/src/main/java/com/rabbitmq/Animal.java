package com.rabbitmq;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jimmy. 2018/2/9  23:47
 */
@Data
public class Animal implements Serializable{

    private Long id;
    private String name;
    private String color;

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
