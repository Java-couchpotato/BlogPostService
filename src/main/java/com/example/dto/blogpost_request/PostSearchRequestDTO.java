package com.example.dto.blogpost_request;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostSearchRequestDTO {

    public String title;
    public String authorsFirstName;
    public String authorsLastName;
    public Set<String> tags;
    public Instant publishedFrom;
    public Instant publishedTo;
}
