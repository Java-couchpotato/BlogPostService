package com.example.dto.postDTO;

import com.example.entity.Tag;
import com.example.entity.types.PostStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record PostCreateRequestDTO(UUID id,
                                   String title,
                                   String body,
                                   List<Tag> tags,
                                   PostStatus status,
                                   UUID authorId) {


}
