package com.example.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
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

    public static AccountStatus getByName(String name){
        if (name==null){
            return AccountStatus.INACTIVE;
        }
        return Arrays.stream(values())
                .filter(x->x.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow();
    }
}
