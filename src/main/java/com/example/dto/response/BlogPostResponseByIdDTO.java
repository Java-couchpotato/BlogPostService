package com.example.dto.response;

import com.example.entity.AccountStatus;
import com.example.entity.BlogAuthor;
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

    public BlogPostResponseByIdDTO(Long id, String title, String body, Set<String> tags, BlogAuthor author, PostStatus status, Instant updatedOn, Instant createdOn) {
    }
}
