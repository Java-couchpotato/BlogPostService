package com.example.dto.request;

import com.example.entity.BlogAuthor;
import lombok.Builder;

import java.util.List;

@Builder
public record UserInfoDto(String sessionId,
                          BlogAuthor blogAuthor,
                          List<String> permissions) {
}
