package com.example.dto.adminDTO;

import com.example.entity.role.Permission;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder

public record AdminShowAuthorsDTO(Long id,
                                  String name,
                                  String username,
                                  boolean isAdmin,
                                  Set<Permission> permissions) {
}
