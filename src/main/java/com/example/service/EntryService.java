package com.example.service;

import com.example.dto.entryDTO.EntryPasswordUpdateDTO;
import com.example.dto.entryDTO.LoginRequestDTO;
import com.example.dto.entryDTO.RegistrationRequestDTO;
import com.example.dto.entryDTO.LoginResponseDTO;
import com.example.entity.BlogAuthorSession;

public interface EntryService {

    void registerUser(RegistrationRequestDTO registrationDTORequest);

    LoginResponseDTO login(LoginRequestDTO loginDTORequest);

    void logout(BlogAuthorSession blogAuthorSession);

    void updatePassword(EntryPasswordUpdateDTO passwordUpdateDTO);

}
