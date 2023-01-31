package com.example.controller;

import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;
import com.example.service.BlogAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final BlogAuthorService authorService;

    @GetMapping("/show_authors")
    //@PreAuthorize("#id==authentication.authorities.contains('admin.posts.ro')||#id==authentication.principal.blogAuthor.id")
    public List<AuthorResponseDTO>showAuthors(){
       return authorService.showAuthorsByArticlesCount();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public AuthorWithArticlesResponseDTO showAuthorsWithArticles( @PathVariable Long id){
       return authorService.showAuthorsWithArticles(id);
    }
}
