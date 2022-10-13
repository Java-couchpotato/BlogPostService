package com.example.dto.user_response;

import com.example.dto.blogpost_response.BlogPostSearchResponseDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthorWithArticlesResponseDTO {

    public Long authorId;
    public String authorFirstName;
    public String authorLastName;
    public String authorUsername;
    public List<BlogPostSearchResponseDTO> blogs;
}
