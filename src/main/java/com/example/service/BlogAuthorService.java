package com.example.service;

import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;


import java.util.List;

public interface BlogAuthorService {

    public List<AuthorResponseDTO> showAuthorsByArticlesCount();

    public AuthorWithArticlesResponseDTO showAuthorsWithArticles(Long id);
}
