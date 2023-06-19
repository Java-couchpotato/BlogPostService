package com.example.service;

import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    List<AuthorResponseDTO> showAuthorsByArticlesCount();

    AuthorWithArticlesResponseDTO showAuthorsWithArticlesById(UUID id);
}
