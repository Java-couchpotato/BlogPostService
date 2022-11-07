package com.example.dto.response;
import com.example.entity.role.Role;
import lombok.*;

@Builder
public record UserRoleResponseDTO(Role role) {

}
