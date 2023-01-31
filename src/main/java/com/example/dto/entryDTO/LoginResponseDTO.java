package com.example.dto.entryDTO;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String sessionId) {
}

