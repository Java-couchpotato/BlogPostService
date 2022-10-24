package com.example.dto.response;

import com.example.entity.PostStatus;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogPostCreateResponseDTO {

    public Long blogId;
    public String blogTitle;
    public String blogBody;
    public Set<String> tags;
    public PostStatus blogStatus;
    public Long authorId;


}
