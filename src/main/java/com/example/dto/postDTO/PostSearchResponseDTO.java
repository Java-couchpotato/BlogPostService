package com.example.dto.postDTO;

import lombok.*;

@Builder
public record PostSearchResponseDTO(Long blogId,
                                    String blogTitle){

}
