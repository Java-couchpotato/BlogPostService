package com.example.dto.blogpost_response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogPostSearchResponseDTO {

    public Long blogId;

    public String blogTitle;
}
