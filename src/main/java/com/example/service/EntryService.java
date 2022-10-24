package com.example.service;

import com.example.dto.request.EntryPasswordUpdateDTO;
import com.example.dto.request.LoginRequestDTO;
import com.example.dto.request.RegistrationRequestDTO;
import com.example.dto.response.LoginResponseDTO;
import com.example.entity.BlogAuthorSession;

public interface EntryService {

    void registerUser(RegistrationRequestDTO registrationDTORequest);

    LoginResponseDTO login(LoginRequestDTO loginDTORequest);

    void logout(BlogAuthorSession blogAuthorSession);

    void updatePassword(EntryPasswordUpdateDTO passwordUpdateDTO);

}
