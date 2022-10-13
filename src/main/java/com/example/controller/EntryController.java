package com.example.controller;

import com.example.dto.entry_request.LoginRequestDTO;
import com.example.dto.entry_request.RegistrationRequestDTO;
import com.example.dto.entry_response.LoginResponseDTO;
import com.example.entity.BlogSession;
import com.example.entity.BlogUser;
import com.example.repository.BlogSessionRepository;
import com.example.service.BlogUserPasswordService;
import com.example.service.EntryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private BlogSessionRepository sessionRepository;

    private BlogUserPasswordService passwordService;

    @PostMapping("/registration")
    public void register(RegistrationRequestDTO registrationRequestDTO) {
        entryService.creatUser(registrationRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        BlogUser blogUser = passwordService.getMatchedAccount(loginRequestDTO.getAuthorUsername(), loginRequestDTO.getPassword());
        BlogSession blogSession = BlogSession.builder()
                .id(blogUser.getId())
                .sessionId(UUID.randomUUID().toString())
                .blogUser(blogUser)
                .build();
        sessionRepository.save(blogSession);

        return LoginResponseDTO.builder()
                .sessionId(blogSession.getSessionId())
                .build();
    }

    @PutMapping
    public void logout() {

    }
}
