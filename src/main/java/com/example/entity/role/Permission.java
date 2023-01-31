package com.example.entity.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Permission {

    PUBLISH_POSTS("users.posts.rw"),
    ADMIN_SEE_ALL_POSTS("admin.posts.ro"),
    ADMIN_UPDATE_POSTS("admin.posts.rw"),
    ADMIN_SEE_ADMINS("admin.admins.ro"),
    ADMIN_MANIPULATE_ADMINS("admin.admins.rw"),
    ADMIN_MODIFY_USERS("admin.users.rw"),
    ADMIN_SEE_ADMIN_ROLES("admin.roles.ro"),
    ADMIN_MODIFY_ROLES("admin.roles.rw");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    @JsonCreator
    public static Permission findByExternalType(String type) {
        if (type == null) {
            return null;
        }
        return Arrays.stream(Permission.values()).
                filter(x -> x.getPermission().equals(type))
                .findFirst()
                .orElse(null);
    }


    public String getPermission() {
        return permission;
    }
}
