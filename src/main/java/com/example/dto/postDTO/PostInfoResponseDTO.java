package com.example.dto.postDTO;

import com.example.entity.types.PostStatus;
import lombok.Builder;

@Builder
public record PostInfoResponseDTO(
        Long id,
        String title,
        PostStatus status) {
}
