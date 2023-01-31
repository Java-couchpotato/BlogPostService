package com.example.dto.postDTO;

import com.example.entity.Tag;
import lombok.*;
import org.springframework.core.metrics.StartupStep;

import java.util.List;


@Builder
public record PostCreateRequestDTO ( String title,
                                     String body,
                                     List<Tag> tagsList,
                                     Long authorId){


}
