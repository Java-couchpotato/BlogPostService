package com.example.entity.role;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(
            Set.of(
                    Permission.PUBLISH_POSTS
            )
    ),
    ADMIN(
            (
                    Set.of(
                            Permission.ADMIN_SEE_ALL_POSTS,
                            Permission.ADMIN_UPDATE_POSTS,
                            Permission.ADMIN_SEE_ADMINS,
                            Permission.ADMIN_MANIPULATE_ADMINS,
                            Permission.ADMIN_MODIFY_USERS,
                            Permission.ADMIN_SEE_ADMIN_ROLES,
                            Permission.ADMIN_MODIFY_ROLES
                    )
            )
    );

    private final Set<Permission> permissions;


    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    public Set<Permission> getPermissions() {
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }
}
