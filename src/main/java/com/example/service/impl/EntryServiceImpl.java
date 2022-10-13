package com.example.service.impl;

import com.example.dto.entry_request.LoginRequestDTO;
import com.example.dto.entry_request.RegistrationRequestDTO;
import com.example.dto.entry_response.LoginResponseDTO;
import com.example.entity.BlogUser;
import com.example.repository.BlogUserPasswordRepository;
import com.example.repository.BlogUserRepository;
import com.example.service.BlogUserPasswordService;
import com.example.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@Service
public class EntryServiceImpl implements EntryService {

    //@Autowired
    //private BlogUserPasswordRepository passwordRepository;
    @Autowired
    private BlogUserRepository blogUserRepository;

    private BlogUserPasswordService passwordService;

    @Override
    public void creatUser(RegistrationRequestDTO registrationRequestDTO) {
        BlogUser blogUser = new BlogUser(
                registrationRequestDTO.authorFirstName,
                registrationRequestDTO.authorLastName,
                registrationRequestDTO.authorUsername
        );

        blogUserRepository.save(blogUser);
        passwordService.generateAndSavePassword(blogUser, registrationRequestDTO.getPassword());
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTORequest) {
        return null;
    }

    @Override
    public void logout() {

    }
}
