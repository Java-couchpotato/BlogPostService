package com.example.dto.authorDTO;

import lombok.*;

@Builder
public record AuthorResponseDTO (Long authorId,
                                 String authorFirstName,
                                 String authorLastName,
                                 String authorUsername,
                                 int blogsCount){

}
