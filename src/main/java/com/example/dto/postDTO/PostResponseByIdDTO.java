package com.example.dto.postDTO;

import com.example.entity.types.AccountStatus;
import lombok.*;

import java.time.Instant;

@Builder
public record PostResponseByIdDTO(Long id,
                                  String title,
                                  String body,
                                  String author,
                                  AccountStatus status,
                                  Instant updatedDate,
                                  Instant createdDate) {



}
