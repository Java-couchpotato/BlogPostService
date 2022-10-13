package com.example.service.impl;

import com.example.dto.entry_request.LoginRequestDTO;
import com.example.dto.entry_request.RegistrationRequestDTO;
import com.example.dto.entry_response.LoginResponseDTO;
import com.example.repository.BlogUserRepository;
import com.example.repository.PostRepository;
import com.example.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BlogUserRepository blogUserRepository;

    @Override
    public void creatUser(RegistrationRequestDTO registrationDTORequest) {

    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTORequest) {
        return null;
    }

    @Override
    public void logout() {

    }
}
