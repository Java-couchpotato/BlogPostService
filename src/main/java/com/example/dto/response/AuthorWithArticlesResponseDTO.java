package com.example.dto.response;

import lombok.*;

import java.util.List;

@Builder
public record AuthorWithArticlesResponseDTO(Long authorId,
                                            String authorFirstName,
                                            String authorLastName,
                                            String authorUsername,
                                            List<BlogPostSearchResponseDTO> blogs) {

}
