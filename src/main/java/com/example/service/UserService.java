package com.example.service;

import com.example.dto.user_response.AuthorResponseDTO;
import com.example.dto.user_response.AuthorWithArticlesResponseDTO;


import java.util.List;

public interface UserService {

    public List<AuthorResponseDTO> showAuthorsByArticlesCount();

    public List<AuthorWithArticlesResponseDTO> showAuthorsWithArticles();
}
