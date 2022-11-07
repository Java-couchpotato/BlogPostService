package com.example.dto.request;

import lombok.Builder;

@Builder
public record LoginRequestDTO(
        String username,
        String password
) {

}

