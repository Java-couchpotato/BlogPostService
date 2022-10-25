package com.example.dto.response;
import com.example.entity.RoleName;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRoleResponseDTO {

    public RoleName roleName;
}
