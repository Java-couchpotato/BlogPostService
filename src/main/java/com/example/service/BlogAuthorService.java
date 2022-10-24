package com.example.service;

import com.example.dto.response.AuthorResponseDTO;
import com.example.dto.response.AuthorWithArticlesResponseDTO;


import java.util.List;

public interface BlogAuthorService {

    public List<AuthorResponseDTO> showAuthorsByArticlesCount();

    public List<AuthorWithArticlesResponseDTO> showAuthorsWithArticles();
}
