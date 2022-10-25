package com.example.controller;

import com.example.dto.request.EntryPasswordUpdateDTO;
import com.example.dto.request.LoginRequestDTO;
import com.example.dto.request.RegistrationRequestDTO;
import com.example.dto.response.LoginResponseDTO;
import com.example.entity.BlogAuthorSession;
import com.example.repository.BlogSessionRepository;
import com.example.service.BlogUserPasswordService;
import com.example.service.EntryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/registration")
    public void register(RegistrationRequestDTO registrationRequestDTO) {
        entryService.registerUser(registrationRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return entryService.login(loginRequestDTO);
    }

    @PutMapping("/logout")
    public void logout(@AuthenticationPrincipal BlogAuthorSession blogAuthorSession) {
        entryService.logout(blogAuthorSession);

    }

    @PreAuthorize("hasAuthority('USER')||#id==authentication.principal.blogUser.id")
    @PutMapping("/update-password")
    public void setNewPassword(@AuthenticationPrincipal EntryPasswordUpdateDTO passwordUpdateDTO) {
        entryService.updatePassword(passwordUpdateDTO);

    }


}
