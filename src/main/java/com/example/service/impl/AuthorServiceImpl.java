package com.example.service.impl;

import com.example.dto.response.AuthorResponseDTO;
import com.example.dto.response.AuthorWithArticlesResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.role.Role;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.service.BlogAuthorService;
import com.example.utils.BlogConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Relation;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional
public class AuthorServiceImpl implements BlogAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
   private PostRepository postRepository;


    @Override
    public List<AuthorResponseDTO> showAuthorsByArticlesCount() {

        Map<BlogAuthor, List<BlogPost>> authorMapByPostCount = postRepository.findAll().stream()
                .filter(k->k.getStatus().equals(PostStatus.PUBLISHED))
                .collect(Collectors.groupingBy(BlogPost::getAuthor));

        return authorMapByPostCount.entrySet().stream()
                .sorted(Comparator.comparing(e -> postRepository.countArticleByAuthor(e.getKey().getId())))
                .map(a -> BlogConverter.mapToUserDto(a.getKey(), a.getValue().size())).toList();


    }


    @Override
    public List<AuthorWithArticlesResponseDTO> showAuthorsWithArticles() {
        return null;
    }
}
