package com.example.service.impl;

import com.example.dto.request.EntryPasswordUpdateDTO;
import com.example.dto.request.LoginRequestDTO;
import com.example.dto.request.RegistrationRequestDTO;
import com.example.dto.response.LoginResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogAuthorSession;
import com.example.repository.BlogAuthorRepository;
import com.example.repository.BlogSessionRepository;
import com.example.service.BlogUserPasswordService;
import com.example.service.EntryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class EntryServiceImpl implements EntryService {

    private BlogUserPasswordService passwordService;

    private BlogAuthorRepository blogAuthorRepository;

    private BlogSessionRepository sessionRepository;


    @Override
    public void registerUser(RegistrationRequestDTO registrationRequestDTO) {
        if (blogAuthorRepository.existsByUsername(registrationRequestDTO.authorUsername)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        BlogAuthor blogAuthor = new BlogAuthor(
                registrationRequestDTO.authorFirstName,
                registrationRequestDTO.authorLastName,
                registrationRequestDTO.authorUsername
        );


        passwordService.generateAndSavePassword(blogAuthor, registrationRequestDTO.getPassword());
        blogAuthorRepository.save(blogAuthor);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        BlogAuthor blogAuthor = passwordService.getMatchedAccount(loginRequestDTO.getAuthorUsername(), loginRequestDTO.getPassword());

        if (blogAuthor == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        BlogAuthorSession blogAuthorSession = BlogAuthorSession.builder()
                .id(blogAuthor.getId())
                .sessionId(UUID.randomUUID().toString())
                .build();
        sessionRepository.save(blogAuthorSession);

        return LoginResponseDTO.builder()
                .sessionId(blogAuthorSession.getSessionId())
                .build();
    }

    @Override
    public void logout(BlogAuthorSession blogAuthorSession) {
        BlogAuthorSession blogAuthorSessionDb = sessionRepository.findBySessionId(blogAuthorSession.getSessionId());
        if (blogAuthorSessionDb != null) {
            sessionRepository.save(blogAuthorSessionDb);
        } else {
            sessionRepository.delete(blogAuthorSession);
        }
    }

    @Override
    public void updatePassword(EntryPasswordUpdateDTO passwordUpdateDTO) {

        BlogAuthor blogAuthor = passwordService.getMatchedAccount(passwordUpdateDTO.getUsername(), passwordUpdateDTO.getOldPassword());

        if (blogAuthor == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        passwordService.generateAndSavePassword(blogAuthor, passwordUpdateDTO.getNewPassword());

    }
}
