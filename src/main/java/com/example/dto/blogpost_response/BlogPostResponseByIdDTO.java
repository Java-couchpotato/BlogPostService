package com.example.dto.blogpost_response;

import com.example.entity.AccountStatus;
import com.example.entity.BlogUser;
import com.example.entity.PostStatus;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogPostResponseByIdDTO {

    public Long Id;
    public String title;
    public String body;
    public Set<String> tags;
    public String author;
    public AccountStatus blogStatus;
    Instant updatedDate;
    Instant createdDate;

    public BlogPostResponseByIdDTO(Long id, String title, String body, Set<String> tags, BlogUser author, PostStatus status, Instant updatedOn, Instant createdOn) {
    }
}
