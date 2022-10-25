package com.example.dto.response;
import com.example.entity.RoleName;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegularAuthorsResponseDTO {
    public Long userId;
    public String name;
    public String username;
    public boolean isAdmin;
    public boolean isRoAdmin;
    public List<RoleName> roleNames;
}
