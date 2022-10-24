package com.example.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PostStatus {
    PUBLISHED(1, "published"),
    UNPUBLISHED(2, "unpublished"),
    BLOCKED(3, "blocked");

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
