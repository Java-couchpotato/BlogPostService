package com.example.dto.authorDTO;
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
