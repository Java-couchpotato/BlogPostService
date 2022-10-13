package com.example.service.impl;

import com.example.dto.user_response.AuthorResponseDTO;
import com.example.dto.user_response.AuthorWithArticlesResponseDTO;
import com.example.repository.BlogUserRepository;
import com.example.repository.PostRepository;
import com.example.service.UserService;
import com.example.utils.BlogConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    BlogUserRepository blogUserRepository;

    @Autowired
    PostRepository postRepository;


    @Override
    public List<AuthorResponseDTO> showAuthorsByArticlesCount() {
        return null;
//                blogUserRepository.findAll().stream().
//                sorted(Comparator.comparing(user->postRepository.countArticleById(user.getId())))
//                .map(BlogConverter::mapToUserDto)
//                .toList();
    }

    @Override
    public List<AuthorWithArticlesResponseDTO> showAuthorsWithArticles() {
        return null;
    }
}
