package com.example.dto.response;
import com.example.entity.Role;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRoleResponseDTO {

    public Role roleName;
}
