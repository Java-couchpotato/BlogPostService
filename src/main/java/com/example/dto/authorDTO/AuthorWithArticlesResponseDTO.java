package com.example.dto.authorDTO;

import com.example.dto.postDTO.PostInfoResponseDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
public record AuthorWithArticlesResponseDTO(UUID authorId,
                                            String authorFirstName,
                                            String authorLastName,
                                            String authorUsername,
                                            List<PostInfoResponseDTO>blogs
) {

}
