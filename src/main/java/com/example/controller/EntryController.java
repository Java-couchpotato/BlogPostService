package com.example.controller;

import com.example.dto.entryDTO.EntryPasswordUpdateDTO;
import com.example.dto.entryDTO.LoginRequestDTO;
import com.example.dto.entryDTO.RegistrationRequestDTO;
import com.example.dto.entryDTO.LoginResponseDTO;
import com.example.entity.BlogAuthorSession;
import com.example.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/entry")
public class EntryController {

    private final EntryService entryService;
//    private final BlogSessionRepository sessionRepository;
//    private final BlogUserPasswordService passwordService;

    @PostMapping("/registration")
    public void register(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        entryService.registerUser(registrationRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return entryService.login(loginRequestDTO);
    }

    @PutMapping("/logout")
    public void logout(@AuthenticationPrincipal BlogAuthorSession blogAuthorSession) {
        entryService.logout(blogAuthorSession);

    }

    @PreAuthorize("hasAuthority('USER')||#id==authentication.principal.blogAuthor.id")
    @PutMapping("/update-password")
    public void setNewPassword(@RequestBody @Valid EntryPasswordUpdateDTO passwordUpdateDTO) {
        entryService.updatePassword(passwordUpdateDTO);

    }


}
