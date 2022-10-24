package com.example.dto.response;

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
