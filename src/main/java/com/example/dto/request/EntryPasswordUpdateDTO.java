package com.example.dto.request;

import lombok.*;

@Builder
public record EntryPasswordUpdateDTO( String username,
                                      String oldPassword,
                                      String newPassword) {


}
