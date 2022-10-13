package com.example.dto.blogpost_request;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostUpdateRequestDTO {

    public String title;
    public String body;
    public Set<String> tags;

}
