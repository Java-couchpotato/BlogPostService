package com.example.dto.request;

import lombok.*;

import java.util.List;
import java.util.Set;


@Builder
public record PostCreateRequestDTO ( String title,
                                     String body,
                                     List<String> tagsList,
                                     Long authorId){


}
