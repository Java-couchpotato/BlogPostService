package com.example.dto.request;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@Builder
public record PostSearchRequestDTO (String title,
                                    String authorsFirstName,
                                    String authorsLastName,
                                    Instant publishedFrom,
                                    Instant publishedTo){

}
