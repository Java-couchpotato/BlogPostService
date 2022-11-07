package com.example.dto.request;

import lombok.*;

import java.util.Set;

@Builder
public record PostUpdateRequestDTO(String title,
                                   String body) {


}
