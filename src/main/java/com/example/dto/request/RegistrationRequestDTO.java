package com.example.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {

    public String authorFirstName;
    public String authorLastName;
    public String authorUsername;
    public String password;

}
