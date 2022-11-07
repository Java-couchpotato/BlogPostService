package com.example.entity.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum AccountStatus {
    ACTIVE(1, "active"),
    INACTIVE(2, "inactive");

    private final Integer id;
    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static AccountStatus getByName(String name){
        if (name==null){
            return AccountStatus.INACTIVE;
        }
        return Arrays.stream(values())
                .filter(x-> x.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }
}
