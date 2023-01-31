package com.example.dto.entryDTO;

import lombok.Builder;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotBlank;


@Builder
@NotBlank
public record EntryPasswordUpdateDTO(
        @Length(min = 5, max = 50)
        String username,
        @Length(min = 5, max = 25)
        String oldPassword,
        @Length(min = 5, max = 25)
        String newPassword) {


}
