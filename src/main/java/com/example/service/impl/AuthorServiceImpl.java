package com.example.service.impl;

import com.example.dto.authorDTO.AuthorResponseDTO;
import com.example.dto.authorDTO.AuthorWithArticlesResponseDTO;
import com.example.dto.postDTO.PostInfoResponseDTO;
import com.example.entity.BlogAuthor;
import com.example.entity.BlogPost;
import com.example.entity.types.PostStatus;
import com.example.repository.AuthorRepository;
import com.example.repository.PostRepository;
import com.example.service.AuthorService;
import com.example.utils.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    private PostRepository postRepository;

    private PostMapper postMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, PostRepository postRepository, PostMapper postMapper) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    public List<AuthorResponseDTO> showAuthorsByArticlesCount() {
        List<BlogAuthor> authors = authorRepository.findAll();
        List<AuthorResponseDTO> authorDTOs = new ArrayList<>();

        for (BlogAuthor author : authors) {
            int publishedArticlesCount = (int) author.getPosts().stream()
                    .filter(post -> post.getStatus() == PostStatus.PUBLISHED)
                    .count();
            authorDTOs.add(new AuthorResponseDTO(
                    author.getId(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getUsername(),
                    publishedArticlesCount
            ));
        }

        return authorDTOs;
    }


    public AuthorWithArticlesResponseDTO showAuthorsWithArticlesById(UUID id) {

        BlogAuthor author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + id));

        List<BlogPost> posts = postRepository.findByAuthorAndStatusOrderByCreatedOnDesc(author, PostStatus.PUBLISHED);

        List<PostInfoResponseDTO> postInfoDTOs = postMapper.mapToPostInfoResponseDTO(posts);

        return new AuthorWithArticlesResponseDTO(author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getUsername(),
                postInfoDTOs);
    }

}



