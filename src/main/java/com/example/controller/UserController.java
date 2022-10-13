package com.example.controller;

import com.example.dto.user_response.AuthorResponseDTO;
import com.example.dto.user_response.AuthorWithArticlesResponseDTO;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<AuthorResponseDTO>showAuthors(){
       return userService.showAuthorsByArticlesCount();
    }
    @GetMapping("/{id}")
    public List<AuthorWithArticlesResponseDTO> showAuthorsWithArticles(){
       return userService.showAuthorsWithArticles();
    }
}
