package com.example.controller;

import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;
import com.example.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/show_authors")
    @PreAuthorize("#id==authentication.authorities.contains('admin.posts.ro')||#id==authentication.principal.blogAuthor.id")
    public List<AuthorResponseDTO>showAuthorsByArticlesCount(){
       return authorService.showAuthorsByArticlesCount();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public AuthorWithArticlesResponseDTO showAuthorsWithArticles( @PathVariable UUID id){
       return authorService.showAuthorsWithArticlesById(id);
    }
}
