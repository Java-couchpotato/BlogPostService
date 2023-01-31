package com.example.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorityFinder {

    public static String getAuthority() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }
    public static boolean containsAuthority(String authority){
        return AuthorityFinder.getAuthority().contains(authority);
    }

    public static void removeRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var updatedAuthorities = auth.getAuthorities().stream()
                .filter(r -> !role.equals(r.getAuthority()))
                .toList();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                auth.getPrincipal(),
                auth.getCredentials(),
                updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
