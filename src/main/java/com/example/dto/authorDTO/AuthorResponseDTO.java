package com.example.dto.authorDTO;

import lombok.*;

import java.util.UUID;

@Builder
public record AuthorResponseDTO(UUID authorId,
                                String authorFirstName,
                                String authorLastName,
                                String authorUsername,
                                int blogsCount){

}
