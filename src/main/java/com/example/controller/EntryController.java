package com.example.controller;

import com.example.dto.entry_request.LoginRequestDTO;
import com.example.dto.entry_request.RegistrationRequestDTO;
import com.example.dto.entry_response.LoginResponseDTO;
import com.example.service.EntryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    EntryService entryService;

    @PostMapping("/registration")
    public void register(RegistrationRequestDTO registrationRequestDTO){
        entryService.creatUser(registrationRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        return entryService.login(loginDTO);
    }

    @PutMapping
    public void autorization(){

    }
}
