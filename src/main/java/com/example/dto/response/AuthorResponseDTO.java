package com.example.dto.response;

import lombok.*;

@Builder
public record AuthorResponseDTO (Long authorId,
                                 String authorFirstName,
                                 String authorLastName,
                                 String authorUsername,
                                 int blogsCount){

}
