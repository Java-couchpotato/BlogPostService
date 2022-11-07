package com.example.service.impl;

import com.example.dto.request.EntryPasswordUpdateDTO;
import com.example.dto.request.LoginRequestDTO;
import com.example.dto.request.RegistrationRequestDTO;
import com.example.dto.response.LoginResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogAuthorSession;
import com.example.entity.role.Role;
import com.example.repository.AuthorRepository;
import com.example.repository.BlogSessionRepository;
import com.example.service.BlogUserPasswordService;
import com.example.service.EntryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final BlogUserPasswordService passwordService;

    private final AuthorRepository authorRepository;

    private final BlogSessionRepository sessionRepository;


    @Override
    public void registerUser(RegistrationRequestDTO registrationRequestDTO) {
        if (authorRepository.existsByUsername(registrationRequestDTO.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        BlogAuthor blogAuthor = BlogAuthor.builder()
                .firstName(registrationRequestDTO.firstName())
                .lastName(registrationRequestDTO.lastName())
                .username(registrationRequestDTO.username())
                .role(Role.ADMIN)
                .build();

        authorRepository.save(blogAuthor);
        passwordService.generateAndSavePassword(blogAuthor, registrationRequestDTO.password());

    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        BlogAuthor blogAuthor = passwordService.getMatchedAccount(loginRequestDTO.username(), loginRequestDTO.password());

        if (blogAuthor == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        BlogAuthorSession blogAuthorSession = BlogAuthorSession.builder()
                //.id(blogAuthor.getId())
                .sessionId(UUID.randomUUID().toString())
                .blogAuthor(blogAuthor)
                .build();
        sessionRepository.save(blogAuthorSession);

        return LoginResponseDTO.builder()
                .sessionId(blogAuthorSession.getSessionId())
                .build();
    }

    @Override
    public void logout(BlogAuthorSession blogAuthorSession) {

        sessionRepository.delete(blogAuthorSession);

    }

    @Override
    public void updatePassword(EntryPasswordUpdateDTO passwordUpdateDTO) {

        BlogAuthor blogAuthor = passwordService.getMatchedAccount(passwordUpdateDTO.username(), passwordUpdateDTO.oldPassword());

        if (blogAuthor == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        passwordService.generateAndSavePassword(blogAuthor, passwordUpdateDTO.newPassword());

    }
}
