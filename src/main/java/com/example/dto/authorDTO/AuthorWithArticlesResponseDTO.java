package com.example.dto.authorDTO;

import com.example.dto.postDTO.PostInfoResponseDTO;
import lombok.*;

import java.util.List;

@Builder
public record AuthorWithArticlesResponseDTO(Long authorId,
                                            String authorFirstName,
                                            String authorLastName,
                                            String authorUsername,
                                            List<PostInfoResponseDTO> blogs) {

}
