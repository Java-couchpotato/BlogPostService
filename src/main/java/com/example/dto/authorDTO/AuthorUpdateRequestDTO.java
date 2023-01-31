package com.example.dto.authorDTO;

import lombok.Builder;

@Builder
public record AuthorUpdateRequestDTO (String firstname,
                                      String lastname){
}
