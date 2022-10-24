package com.example.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EntryPasswordUpdateDTO {

    public String username;
    public String oldPassword;
    public String newPassword;
}
