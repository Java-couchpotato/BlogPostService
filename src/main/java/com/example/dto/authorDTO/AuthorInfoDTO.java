package com.example.dto.authorDTO;

import com.example.entity.BlogAuthor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorInfoDTO {
    private String sessionId;
    private BlogAuthor author;
    private List<String> permission;
}
