package com.example.dto.entry_request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequestDTO {

    public String password;
    public String authorUsername;
}
