package com.example.dto.user_response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorResponseDTO {

    public Long authorId;
    public String authorFirstName;
    public String authorLastName;
    public String authorUsername;
    public int blogsCount;
}
