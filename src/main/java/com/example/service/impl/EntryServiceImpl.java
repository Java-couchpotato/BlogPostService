package com.example.service.impl;

import com.example.dto.entryDTO.EntryPasswordUpdateDTO;
import com.example.dto.entryDTO.LoginRequestDTO;
import com.example.dto.entryDTO.RegistrationRequestDTO;
import com.example.dto.entryDTO.LoginResponseDTO;
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
                .build();
        blogAuthor.setRole(Role.ADMIN);

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
