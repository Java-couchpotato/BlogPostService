package com.example.dto.postDTO;

import com.example.entity.types.PostStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PostInfoResponseDTO(
        UUID id,
        String title,
        PostStatus status) {
}
