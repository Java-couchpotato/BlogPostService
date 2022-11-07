package com.example.entity.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum PostStatus {

    PUBLISHED(1, "published"),
    UNPUBLISHED(2, "unpublished"),
    BLOCKED(3, "blocked");

    private final Integer id;
    private final String name;

    @JsonCreator
    public static PostStatus findByName(String statusName) {
        if (statusName == null) {
            return UNPUBLISHED;
        }

        return Arrays.stream(PostStatus.values())
                .filter(x -> x.getName().equals(statusName))
                .findFirst()
                .orElse(UNPUBLISHED);
    }

    public Integer getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
