package com.example.dto.postDTO;

import com.example.entity.Tag;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
public record PostUpdateRequestDTO(String title,
                                   String body,
                                   List<Tag> tags) {

}
