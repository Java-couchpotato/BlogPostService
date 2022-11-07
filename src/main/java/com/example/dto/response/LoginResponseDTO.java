package com.example.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String sessionId) {
}

