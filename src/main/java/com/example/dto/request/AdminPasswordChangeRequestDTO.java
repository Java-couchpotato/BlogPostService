package com.example.dto.request;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminPasswordChangeRequestDTO {
    public String password;
}
