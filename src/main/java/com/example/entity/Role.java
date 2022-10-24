package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {

    USER(1),
    ADMIN(2)
    ;

    private final Integer roleId;

    public static Role findBy(Integer id){
        if (id==null){
            return  null;
        }
        return Arrays.stream(Role.values())
                .filter(x-> Objects.equals(x.getRoleId(),id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
