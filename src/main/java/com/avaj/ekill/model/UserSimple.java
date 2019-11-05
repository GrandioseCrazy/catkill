package com.avaj.ekill.model;

import lombok.Data;

@Data
public class UserSimple {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "UserSimple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
