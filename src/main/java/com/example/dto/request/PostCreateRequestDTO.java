package com.example.dto.request;

import lombok.*;

import java.util.Set;

@Builder
public record PostCreateRequestDTO ( String title,
                                     String body,
                                     Long authorId){


}
