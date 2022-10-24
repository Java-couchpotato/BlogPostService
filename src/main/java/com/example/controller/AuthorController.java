package com.example.controller;

import com.example.dto.response.AuthorResponseDTO;
import com.example.dto.response.AuthorWithArticlesResponseDTO;
import com.example.service.BlogAuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private BlogAuthorService userService;

    @GetMapping
    public List<AuthorResponseDTO>showAuthors(){
       return userService.showAuthorsByArticlesCount();
    }

    @PreAuthorize("hasAuthority('USER')||#id==authentication.principal.blogUser.id")
    @GetMapping("/{id}")
    public List<AuthorWithArticlesResponseDTO> showAuthorsWithArticles(){
       return userService.showAuthorsWithArticles();
    }
}
