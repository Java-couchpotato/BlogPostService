package com.example.dto.response;

import com.example.entity.types.PostStatus;


public record BlogPostCreateResponseDTO(Long blogId,
                                        String blogTitle,
                                        String blogBody,
                                        PostStatus blogStatus,
                                        Long authorId) {



}
