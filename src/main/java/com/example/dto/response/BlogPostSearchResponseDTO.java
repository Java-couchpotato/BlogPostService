package com.example.dto.response;

import lombok.*;

@Builder
public record BlogPostSearchResponseDTO (Long blogId,
                                        String blogTitle){

}
