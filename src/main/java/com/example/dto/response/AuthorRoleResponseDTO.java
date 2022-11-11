package com.example.dto.response;
import com.example.entity.role.Permission;
import com.example.entity.role.Role;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorRoleResponseDTO {
    private Role role;
    private List<String> permission;
}
