package com.example.dto.entryDTO;

import lombok.Builder;

@Builder
public record LoginRequestDTO(
        String username,
        String password
) {

}

