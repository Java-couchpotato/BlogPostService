package com.example.dto.postDTO;

import com.example.entity.Tag;
import lombok.*;

import java.util.List;


@Builder
public record PostCreateResponseDTO(String title,
                                    String body,
                                    List<Tag> tagsList,
                                    Long authorId){


}
