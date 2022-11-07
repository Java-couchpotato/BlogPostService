package com.example.dto.response;
import com.example.entity.role.Role;
import lombok.*;

import java.util.List;

@Builder
public record RegularAuthorsResponseDTO (
    Long userId,
    String name,
    String username,
    boolean isAdmin,
    boolean isRoAdmin,
    List<Role> roles){
}
