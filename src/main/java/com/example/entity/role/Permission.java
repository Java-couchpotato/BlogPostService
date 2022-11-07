package com.example.entity.role;

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

    public String getPermission() {
        return permission;
    }
}
