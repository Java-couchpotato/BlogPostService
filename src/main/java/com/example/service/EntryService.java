package com.example.service;

import com.example.dto.entry_request.LoginRequestDTO;
import com.example.dto.entry_request.RegistrationRequestDTO;
import com.example.dto.entry_response.LoginResponseDTO;

public interface EntryService {

    void creatUser(RegistrationRequestDTO registrationDTORequest);

    LoginResponseDTO login(LoginRequestDTO loginDTORequest);

    void logout();

}
