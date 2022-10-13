package com.example.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum AccountStatus {
    ACTIVE(1, "active"),
    INACTIVE(2, "inactive");

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

//    public static AccountStatus getByName(String name){
//        if (name==null){
//            return AccountStatus.INACTIVE;
//        }
//        return Arrays.stream(values())
//                .filter(x->x.getName().toLowerCase().equals(name.toLowerCase()))
//                .findFirst()
//                .orElseThrow(()->new TypeNotFoundEcxeption(String.format()))
//    }
}
